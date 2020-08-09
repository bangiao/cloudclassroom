package com.dingxin.web.shiro.cas;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.utils.BeanUtils;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.pojo.po.Role;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.web.service.impl.RoleServiceImpl;
import com.dingxin.web.service.impl.UserRoleServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 重写casFilter,验证成功后返回前端页面
 */
public class MyCasFilter extends CasFilter {

    private static final String STUDENT_PREFEX = "1";
    private static final String TICKET_PARAMETER = "ticket";
    private static final String URL_PARAMETER = "url";
    private static final Integer STUDENT_ROLE = 3;
    private static final Integer TEACHER_PREFEX = 3;
    private static final Integer TEACHER_ROLE = 4;


    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ticket = httpRequest.getParameter(TICKET_PARAMETER);
        String url = httpRequest.getParameter(URL_PARAMETER);
        return new MyCasToken(ticket, url);
    }

    /**
     * 登录成功后返回用户信息
     * 如果当前用户没有角色 则根据其学号、职工号分配角色
     *
     * @param token    token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        UserRoleServiceImpl userRoleService = BeanUtils.getBean(UserRoleServiceImpl.class);
        RoleServiceImpl roleService = BeanUtils.getBean(RoleServiceImpl.class);
        List<Role> roles = new ArrayList<>();
        Role role = null;

        CasEmploys principal = (CasEmploys) subject.getPrincipal();
        String sid = principal.getSid();

        String s = sid.substring(0, 1);
//        if (STUDENT_PREFEX.equals(s)||TEACHER_PREFEX.equals(s)){
        List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>lambdaQuery()
                .eq(UserRole::getCasUserId, sid));
        if (CollectionUtils.isNotEmpty(userRoles)) {
            roles = (List<Role>) roleService
                    .listByIds(userRoles
                            .stream()
                            .map(UserRole::getRoleId)
                            .collect(Collectors.toList()));
        }
//        }
//        List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getCasUserId, sid));
//
//        if (CollectionUtils.isEmpty(userRoles)) {
//            String s = sid.substring(0, 1);
//            UserRole userRole = new UserRole();
//            userRole.setCasUserId(sid);
//
//            if (STUDENT_PREFEX.equals(s)) {
//                userRole.setRoleId(STUDENT_ROLE);
//                role = roleService.getById(STUDENT_ROLE);
//            } else {
//                userRole.setRoleId(TEACHER_ROLE);
//                role = roleService.getById(TEACHER_ROLE);
//            }
//            userRoleService.save(userRole);
//            roles.add(role);
//
//        } else {
//            roles = roleService.list(Wrappers.<Role>lambdaQuery()
//                    .in(
//                            CollectionUtils.isNotEmpty(userRoles),
//                            Role::getId,
//                            userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList())
//                    )
//            );
//        }
        principal.setRoles(roles);
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().println(JSON.toJSON(principal));
        return false;
    }

    /**
     * 登录失败时的返回信息
     *
     * @param token
     * @param ae
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request,
                                     ServletResponse response) {
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        try {
            httpresponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpresponse.setCharacterEncoding("UTF-8");
            BaseResult result = new BaseResult();
            result.failed(ExceptionEnum.PRIVILEGE_CAS_FAIL);
            response.getWriter().println(JSON.toJSON(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
