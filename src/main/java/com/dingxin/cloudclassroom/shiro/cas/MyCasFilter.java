package com.dingxin.cloudclassroom.shiro.cas;

import com.alibaba.fastjson.JSON;
import com.dingxin.cloudclassroom.common.BaseResult;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 重写casFilter,验证成功后返回前端页面
 * @author weifuchow
 *
 */
public class MyCasFilter extends CasFilter {
	
	
    private static final String TICKET_PARAMETER = "ticket";
    private static final String URL_PARAMETER = "url";

	
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ticket = httpRequest.getParameter(TICKET_PARAMETER);
        String url = httpRequest.getParameter(URL_PARAMETER);
//        //TODO 测试完成后下面中间的删除
//        String wid = httpRequest.getParameter("wid");
//        return new MyCasToken(wid,wid);
//        //TODO 测试完成后上面中间的删除
        return new MyCasToken(ticket,url);
	}


	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {


	    //登录成功后将用户信息返回给前端
        Object principal = (Object)subject.getPrincipal();
		
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().println(JSON.toJSON(principal));
		return false;
	}


	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request,
			ServletResponse response) {
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        try {
            httpresponse.setHeader("Content-Type","application/json;charset=UTF-8");
            httpresponse.setCharacterEncoding("UTF-8");
            BaseResult result = new BaseResult();
            result.failed("CAS验证失败", HttpStatus.SC_UNAUTHORIZED);
            response.getWriter().println(JSON.toJSON(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
        return false;
	}
	
	
	

	
	
}
