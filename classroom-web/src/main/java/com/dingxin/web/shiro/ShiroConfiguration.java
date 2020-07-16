package com.dingxin.web.shiro;

import com.dingxin.web.shiro.cas.MyCasFilter;
import com.dingxin.web.shiro.cas.MyCasRealm;
import com.dingxin.web.shiro.jwt.JwtAuthenticationFilter;
import com.dingxin.web.shiro.jwt.JwtRealm;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.apache.shiro.mgt.SecurityManager;

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
//@Configuration
public class ShiroConfiguration {

    // CasServerUrlPrefix
    public static final String casServerUrlPrefix = "https://cas.sustech.edu.cn/cas";
    // Cas登录页面地址
    public static final String casLoginUrl = casServerUrlPrefix + "/login";
    // Cas登出页面地址
    public static final String casLogoutUrl = casServerUrlPrefix + "/logout";
    // 当前工程对外提供的服务地址
    public static final String shiroServerUrlPrefix = "http://localhost:8882";
    // casFilter UrlPattern
    public static final String casFilterUrlPattern = "/cas";
    // 登录地址
    public static final String loginUrl = casLoginUrl + "?service=" + shiroServerUrlPrefix + casFilterUrlPattern;


//    @Bean
//    public EhCacheManager ehCacheManager(){
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
//        return cacheManager;
//    }

    /**
     * Shiro redis
     * @param template
     * @return
     */
    @Bean(name = "shiroRedisCacheManager")
    public ShiroRedisCacheManager cacheManager(@Qualifier("redisTemplate") RedisTemplate template){
        return new ShiroRedisCacheManager(template);
    }


    @Bean(name = "myShiroCasRealm")
    public MyCasRealm myShiroCasRealm(ShiroRedisCacheManager cacheManager) {
        MyCasRealm realm = new MyCasRealm();
        realm.setCacheManager(cacheManager);
        realm.setCachingEnabled(true);
        realm.setCasServerUrlPrefix(casServerUrlPrefix);
        realm.setCasService(shiroServerUrlPrefix+casFilterUrlPattern);
        return realm;
    }

    @Bean(name = "casFilter")
    public CasFilter getCasFilter() {
        CasFilter casFilter = new MyCasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
        // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
//        casFilter.setFailureUrl(loginUrl);// 我们选择认证失败后再打开登录页面
        return casFilter;
    }

    /**
     * 自定义Realm
     * @return
     */
    @Bean(name = "jwtRealm")
    public JwtRealm jwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        // jwtRealm.setCredentialsMatcher(credentialsMatcher());
        jwtRealm.setCachingEnabled(false);
        return jwtRealm;
    }

    @Bean(name = "jwtAuthenticationFilter")
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        return jwtAuthenticationFilter;
    }


    /**
     * Subject工厂管理器
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory(){
        DefaultWebSubjectFactory subjectFactory = new StatelessDefaultSubjectFactory();
        return subjectFactory;
    }

    /**
     * 会话管理器
     * @return
     */
    public DefaultSessionManager sessionManager(){
        DefaultSessionManager sessionManager =new DefaultSessionManager();
        // 关闭session定时检查，通过setSessionValidationSchedulerEnabled禁用掉会话调度器
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return  sessionManager;
    }

    @Bean(name = "securityManager")
    public SecurityManager getDefaultWebSecurityManager(MyCasRealm myShiroCasRealm,RedisTemplate<String, Object> template) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        List<Realm> realms = new ArrayList<Realm>();
        realms.add(jwtRealm());
        realms.add(myShiroCasRealm);
        dwsm.setRealms(realms);
//      <!-- 用户授权/认证信息Cache, 采用Redis 缓存 -->
        dwsm.setCacheManager(cacheManager(template));
        // 指定 SubjectFactory
        dwsm.setSubjectFactory(subjectFactory());
        dwsm.setSessionManager(sessionManager());
        //
        ((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO)dwsm.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);
        //
        SecurityUtils.setSecurityManager(dwsm);
        return dwsm;
    }

    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        // 配置登录的url和登录成功的url
        bean.setLoginUrl(loginUrl);
//        bean.setSuccessUrl("/swagger-ui.html");
        // 配置访问权限
        loadFilterChain(bean);
        loadFilter(bean);
        return bean;
    }

    private void loadFilter(ShiroFilterFactoryBean bean) {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("cas", getCasFilter());
        filters.put("jwt", jwtAuthenticationFilter());
        bean.setFilters(filters);
    }

    private void loadFilterChain(ShiroFilterFactoryBean bean) {
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc：该过滤器下的页面必须登录后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        // anon: 可以理解为不拦截
        // user: 登录了就不拦截
        // roles["admin"] 用户拥有admin角色
        // perms["permission1"] 用户拥有permission1权限
        // filter顺序按照定义顺序匹配，匹配到就验证，验证完毕结束。
        // url匹配通配符支持：? * **,分别表示匹配1个，匹配0-n个（不含子路径），匹配下级所有路径
        filterChainDefinitionMap.put("/act", "anon");
        filterChainDefinitionMap.put("/external/setProjectsOAStatus", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html","anon");
        filterChainDefinitionMap.put("/kafka/**","anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/cas", "cas"); //cas验证 需要 cas登陆
        filterChainDefinitionMap.put("/api/**","jwt");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    // 注册过滤器 到 ServletContainer
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    // Spring Mvc 支持shiro 注解 begin //
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }
}
