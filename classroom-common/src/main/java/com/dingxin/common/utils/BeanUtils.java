package com.dingxin.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2019/9/24
 */
@Component
@Slf4j
public class BeanUtils implements ApplicationContextAware {

    // 上下文对象
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，注入上下文对象
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    /**
     * 获取上下文对象
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 判断上下文对象是否为空
     *
     * @return
     */
    public static boolean checkapplicationContext() {
        boolean flag = getApplicationContext() != null;
        if (!flag) {
            log.error("applicaitonContext未注入,实现ApplicationContextAware的类必须被spring管理");
        }
        return flag;
    }

    /**
     * 获取当前访问请求对象
     * @return
     */
    public static HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    /**
     * 获取当前访问返回对象
     * @return
     */
    public static HttpServletResponse getCurrentHttpResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getResponse();
        }
        return null;
    }

    /**
     * 根据name获取bean
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        if (checkapplicationContext()) {
            return (T) getApplicationContext().getBean(name);
        } else {
            return null;
        }
    }

    /**
     * 根据class 获取bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        if (checkapplicationContext()) {
            return getApplicationContext().getBean(clazz);
        } else {
            return null;
        }
    }

    /**
     * 根据name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        if (checkapplicationContext()) {
            return getApplicationContext().getBean(name, clazz);
        } else {
            return null;
        }
    }
}
