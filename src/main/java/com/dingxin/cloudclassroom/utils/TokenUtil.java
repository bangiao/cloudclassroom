package com.dingxin.cloudclassroom.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private Long expiration;


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

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
        return new Date(System.currentTimeMillis() + expiration*1000 );
    }

    /**
     * 通过spring-mobile-device的device检测访问主体
     * @param device
     * @return
     */
//    private  String generateAudience(Device device) {
//        String audience = AUDIENCE_UNKNOWN;
//        if (device.isNormal()) {
//            audience = AUDIENCE_WEB;//PC端
//        } else if (device.isTablet()) {
//            audience = AUDIENCE_TABLET;//平板
//        } else if (device.isMobile()) {
//            audience = AUDIENCE_MOBILE;//手机
//        }
//        return audience;
//    }

    /**
     * 根据token获取用户名
     * @param token
     * @return
     */
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

    /**
     * 判断token失效时间是否到了
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        System.out.println(expiration);
        return expiration.before(new Date());
    }

    /**
     * 获取设置的token失效时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    // /**
    //  * Token失效校验
    //  * @param token token字符串
    //  * @param loginInfo 用户信息
    //  * @return
    //  */
    // public Boolean validateToken(String token, LoginInfo loginInfo) {
    //     final String username = getUsernameFromToken(token);
    //     return (
    //             username.equals(loginInfo.getUsername())
    //                     && !isTokenExpired(token));
    // }

    public String refreshToken(String token) {
        final Claims claims = this.getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    
    // 测试达到超时时间能否验证成功
    public static void main(String[] args) {
		
    	String userName = "weifuchow";
    	TokenUtil token = new TokenUtil();
    	token.setSecret("mySecret");
    	token.setExpiration(86400l);
    	String tokenStr = token.generateToken(userName);
    	System.out.println("-->已tokenStr<--" + tokenStr);
		boolean isExpired = token.isTokenExpired(tokenStr);
		System.out.println("-->是否过期<--" + isExpired );
    	try {
    		System.out.println("-->正休眠1000ms<--" );
			Thread.sleep(10000);
    		System.out.println("-->已睡醒<--" );
			String name = token.getUsernameFromToken(tokenStr);
			System.out.println("-->name<--" + name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	
	}
}
