package com.dingxin.cloudclassroom.shiro.jwt;

import com.dingxin.cloudclassroom.entity.CasEmploys;
import com.dingxin.cloudclassroom.service.ICasEmploysService;
import com.dingxin.cloudclassroom.service.IMenuService;
import com.dingxin.cloudclassroom.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ICasEmploysService casEmploysService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //表示此Realm只支持JwtToken类型
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户名查找角色，请根据需求实现  userName就是user_infos表里面的wid
        Object principal = principals.getPrimaryPrincipal();

        CasEmploys casEmploys = (CasEmploys) principal;
        String id = casEmploys.getSid();

        Set<String> permsSet = menuService.getCasUserPermission(id);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        // 获取token
        String token = jwtToken.getToken();

        // 从token中获取用户名
        String sid = tokenUtil.getUsernameFromToken(token);
        //根据sid查询CAS用户信息
        CasEmploys casEmploys = null;
        try {
            casEmploys = casEmploysService.selectById(sid);
            casEmploys.setToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            new IncorrectCredentialsException("系统没有该用户,请等待");
        }

        return new SimpleAuthenticationInfo(casEmploys, token, sid);
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
