package com.dingxin.web.shiro;

import com.dingxin.web.shiro.cas.MyCasFilter;
import com.dingxin.web.shiro.cas.MyCasRealm;
import com.dingxin.web.shiro.jwt.JwtAuthenticationFilter;
import com.dingxin.web.shiro.jwt.JwtRealm;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.apache.shiro.mgt.SecurityManager;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2019/6/13
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class ShiroConfiguration {

    // CasServerUrlPrefix
    public static final String casServerUrlPrefix = "https://cas.sustech.edu.cn/cas";
    // Cas登录页面地址
    public static final String casLoginUrl = casServerUrlPrefix + "/login";
    // Cas登出页面地址
    public static final String casLogoutUrl = casServerUrlPrefix + "/logout";
    // 当前工程对外提供的服务地址
    public static final String shiroServerUrlPrefix = "http://127.0.0.1:8091";
    // casFilter UrlPattern
    public static final String casFilterUrlPattern = "/cas";
    // 登录地址
    public static final String loginUrl = casLoginUrl + "?service=" + shiroServerUrlPrefix + casFilterUrlPattern;


    /**
     * Subject工厂管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory() {
        return new DefaultWebSubjectFactory();
    }

    /**
     * 会话管理器
     *
     * @return
     */
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        // 关闭session定时检查，通过setSessionValidationSchedulerEnabled禁用掉会话调度器
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }


    @Bean(name = "casFilter")
    public CasFilter getCasFilter() {
        CasFilter casFilter = new MyCasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
        return casFilter;
    }

    @Bean(name = "myjwtRealm")
    public JwtRealm getJwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCachingEnabled(false);
        return new JwtRealm();
    }

    @Bean
    public FilterRegistrationBean registration(JwtAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean(name = "jwtFilter")
    public JwtAuthenticationFilter getJwtFiter() {
        return new JwtAuthenticationFilter();
    }


    @Bean(name = "myShiroCasRealm")
    public MyCasRealm myShiroCasRealm() {
        MyCasRealm realm = new MyCasRealm();
        realm.setCachingEnabled(true);
        realm.setCasServerUrlPrefix(casServerUrlPrefix);
        realm.setCasService(shiroServerUrlPrefix + casFilterUrlPattern);
        return realm;
    }


    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(MyCasRealm myCasRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置多个realm
        ArrayList<Realm> realms = Lists.newArrayList();
        realms.add(myCasRealm);
        realms.add(getJwtRealm());
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealms(realms);
        ((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("cas", getCasFilter());
        filters.put("jwt", getJwtFiter());
//        filters.put("jwt", jwtAuthenticationFilter());

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<>();
//        map.put("/studentInfo", "anon");
        /**
         * /swagger-resources = anon
         *         /v2/api-docs = anon
         *         /v2/api-docs-ext = anon
         *         /doc.html = anon
         *         /webjars/** = anon
         */

        map.put("/swagger-resources","anon");
        map.put("/v2/api-docs","anon");
        map.put("/v2/api-docs-ext","anon");
        map.put("/doc.html","anon");
        map.put("/webjars/**","anon");
        //cas验证
        map.put("/cas", "cas");
        map.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
