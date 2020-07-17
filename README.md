# 南科大云课堂

## CAS登陆

## 数据权限控制

## 测试账号角色分配（仅限开发测试环境）
## 开发时可使用这两个账号进行测试

| 角色       | 账号     | 密码   |
| ---------- | -------- | ------ |
| 暂无 | 70000281 | xiao516724 |
| 暂无 | 70000235 | Hnie@123 |



## 项目架构

- [x] Spring boot 2.0X
  - [x] Log(后续集成)
- [x] Shrio
  - [x] 集成C A S
  - [x] 无状态 J W T 
- [x] Redis（缓存）
- [x] Swagger-ui
- [x] ORM
  - [x] MybatisPlus
- [x] swagger-ui.html

---

**可修改ShiroConfiguration禁用拦截器方便测试**

### Shiro 认证过程

1. CAS 认证  

   URL : http://ip:port/cas?ticket =xx
   1. 使用Shiro Cas Relam 进行验证 CAS 验证。验证成功后重定向到前端页面
      认证.

      ```java
      protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
              CasToken casToken = (CasToken) token;
              if (token == null) {
                  return null;
              }
              
              String ticket = (String)casToken.getCredentials();
              if (!StringUtils.hasText(ticket)) {
                  return null;
              }
              
              TicketValidator ticketValidator = ensureTicketValidator();
              
              try {
                  // contact CAS server to validate service ticket
                  Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
                  // get principal, user id and attributes
                  AttributePrincipal casPrincipal = casAssertion.getPrincipal();           
                  Map<String, Object> attributes = casPrincipal.getAttributes();
                  // refresh authentication token (user id + remember me)
                  String rememberMeAttributeName = getRememberMeAttributeName();
                  String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
                  boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
                  if (isRemembered) {
                      casToken.setRememberMe(true);
                  }
                  //
                  String sid = (String) casPrincipal.getAttributes().get("sid");
                  String name = (String) casPrincipal.getAttributes().get("name");
                  String id = (String) casPrincipal.getAttributes().get("id");
                  UserProfilesService upService = SpringUtil.getBean(UserProfilesService.class);
                  UserProfileModule upm = upService.login(sid, name, id);
                  //
                  return new SimpleAuthenticationInfo(upm,ticket ,sid);
              } catch (Exception e) { 
              	e.printStackTrace();
                  throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
              }
          }
      ```

      2.J W T加密解密过程

      ​	JSON Web Token (JWT)是一个开放标准(RFC 7519)，它定义了一种紧凑的、自包含的方式，用于作为JSON对象在各方之间安全地传输信息。该信息可以被验证和信任，因为它是数字签名的

      ​	加密

      ​		这里将使用sid 作为载体payload。签名密钥使用的this.secret

      ```java
          /**
           * 生成token
           * @param username 用户名
           * @return
           */
          public String generateToken(String username) {
              Map<String, Object> claims = new HashMap<>();
              claims.put(CLAIM_KEY_USERNAME, username);
              claims.put(CLAIM_KEY_CREATED, new Date());
              return generateToken(claims);
          }
      
          private String generateToken(Map<String, Object> claims) {
              return Jwts.builder()
                      .setClaims(claims)
                      .setExpiration(generateExpirationDate())
                      .signWith(SignatureAlgorithm.HS512, this.secret)
                      .compact();
          }
          /**
           * 生成token时间 = 当前时间 + expiration（properties中配置的失效时间）
           * @return
           */
          private Date generateExpirationDate() {
              return new Date(System.currentTimeMillis() + expiration * 1000);
          }
      ```

      ​	解密

      ```java
          private Claims getClaimsFromToken(String token) {
              Claims claims;
              try {
                  claims = Jwts.parser()
                          .setSigningKey(secret)
                          .parseClaimsJws(token)
                          .getBody();
              } catch (Exception e) {
                  claims = null;
              }
              return claims;
          }
          public String getUsernameFromToken(String token) {
              String username;
              try {
                  final Claims claims = getClaimsFromToken(token);
                  username = claims.getSubject();
              } catch (Exception e) {
                  username = null;
              }
              return username;
          }
      ```

      

      3.认证成功回调

      ```java
      	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
      			ServletResponse response) throws Exception {
      		// TODO Auto-generated method stub
      		CasToken casToken = (CasToken) token;
      		
      		TokenUtil tokenUtil = SpringUtil.getBean(TokenUtil.class);
      		UserProfileModule principal = (UserProfileModule)subject.getPrincipal();
      		
              HttpServletResponse servletResponse = (HttpServletResponse) response;
              // 将token写出到cookie
              servletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
              servletResponse.setCharacterEncoding("UTF-8");
              ObjectMapper objectMapper = new ObjectMapper();
              servletResponse.getWriter().println(JsonUtils.toJson(principal));
              //
              Cookie cookie = new Cookie("token",principal.getToken());
              cookie.setHttpOnly(true);
              cookie.setMaxAge(3600 * 5);
              cookie.setPath("/");
              servletResponse.addCookie(cookie);
              servletResponse.flushBuffer();
              
      		return true;
      	}
      ```



​	2. J W T 验证

```java
 protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 先从Header里面获取
        String token = httpRequest.getHeader(TOKEN);
        if(StringUtils.isEmpty(token)){
            // 获取不到再从Parameter中拿
            token = httpRequest.getParameter(TOKEN);
            // 还是获取不到再从Cookie中拿
            if(StringUtils.isEmpty(token)){
                Cookie[] cookies = httpRequest.getCookies();
                if(cookies != null){
                    for (Cookie cookie : cookies) {
                        if(TOKEN.equals(cookie.getName())){
                            token = cookie.getValue();
                            break;
                        }
                    }
                }
            }
        }
        return JwtToken.builder()
                .token(token)
                .build();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        // 获取token
        String token = jwtToken.getToken();

        // 从token中获取用户名
        String sid = tokenUtil.getUsernameFromToken(token);
        UserProfilesService upService = SpringUtil.getBean(UserProfilesService.class);
        UserProfileModule upm = upService.login(sid, null, null);
        
        return new SimpleAuthenticationInfo(upm,token,sid);
    }
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request,
                                     ServletResponse response) {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        jsonObject.put("msg","登录失败，无权访问");
        jsonObject.put("timestamp", System.currentTimeMillis());
        try {
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.setHeader("Access-Control-Allow-Origin","*");
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(jsonObject));
        } catch (IOException e) {
        }
        return false;
    }
``` 

***

