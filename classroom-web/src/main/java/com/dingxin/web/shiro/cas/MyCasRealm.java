package com.dingxin.web.shiro.cas;

import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.TokenUtil;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.web.service.ICasEmploysService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

import static com.dingxin.common.enums.ExceptionEnum.PRIVILEGE_CAS_FAIL;

/**
 * cas认证
 */
public class MyCasRealm extends CasRealm {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ICasEmploysService casEmploysService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set roles = new HashSet<String>();
        Set permission = new HashSet<String>();
        roles.add("user");
        permission.add("user");
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permission);
        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//    	MyCasToken casToken = (MyCasToken) token;
//        if (token == null) {
//            return null;
//        }
//        String ticket = (String)casToken.getCredentials();
//        String casService = casToken.getUrl();
//        if (!StringUtils.hasText(ticket)) {
//            return null;
//        }
//        TicketValidator ticketValidator = ensureTicketValidator();
//
//        try {
//            // 连接cas验证ticket
//            Assertion casAssertion = ticketValidator.validate(ticket, casService);
//            // 获取参数
//            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
//            Map<String, Object> attributes = casPrincipal.getAttributes();
//            String rememberMeAttributeName = getRememberMeAttributeName();
//            String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
//            boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
//            if (isRemembered) {
//                casToken.setRememberMe(true);
//            }
//            String sid = (String) casPrincipal.getAttributes().get("sid");
//            String name = (String) casPrincipal.getAttributes().get("name");
//            String id = (String) casPrincipal.getAttributes().get("id");
//            //通过用户的sid构造出token
//            String generateToken = tokenUtil.generateToken(sid);
//            //将token保存进用户对象并返回给前端
//            CasEmploys casEmploys = new CasEmploys();
//            casEmploys.setSid(sid);
//            casEmploys.setName(name);
//
//            casEmploys.setToken(generateToken);
//            //构造用户对象
////            UserProfilesService upService = SpringUtil.getBean(UserProfilesService.class);
////            UserProfileModule upm = upService.login(sid, name, id);
//
//
//            return new SimpleAuthenticationInfo(casEmploys,ticket ,sid);
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new CasAuthenticationException("验证ticket失败", e);
//        }
        MyCasToken casToken = (MyCasToken) token;
        if (token==null){
            return null;
        }
        String wid = casToken.getUrl();
        String generateToken = tokenUtil.generateToken(wid);
        CasEmploys employs = casEmploysService.getById(wid);
        if (employs == null){
            throw new BusinessException(PRIVILEGE_CAS_FAIL);
        }
        employs.setToken(generateToken);
        return new SimpleAuthenticationInfo(employs,wid,wid);
    }


}