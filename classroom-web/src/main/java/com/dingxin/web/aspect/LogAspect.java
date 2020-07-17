package com.dingxin.web.aspect;

import com.alibaba.fastjson.JSON;
import com.dingxin.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author changxin.yuan
 * @date 2020/7/17 13:28
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAspect {

    @Pointcut("execution(public * com.dingxin.web.controller.*.*(..))")
    public void controllerPoinCut() {
    }

    @Around("controllerPoinCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        List<Object> org = Stream.of(args).filter(i -> !i.getClass().getName().startsWith("org")).collect(Collectors.toList());
        LogUtils.initLogId();
        StringBuilder builder = new StringBuilder();
        builder.append(signature.getDeclaringType().toGenericString());
        builder.append("-");
        builder.append(targetMethod.getName());
        LogUtils.info(log,"%s访问日志,参数%s",builder.toString(), JSON.toJSONString(org));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = joinPoint.proceed();
        stopWatch.stop();
        LogUtils.info(log,"%s访问日志,返回参数%s,执行时间%s", builder.toString(),
                JSON.toJSONString(obj), stopWatch.getTotalTimeMillis());
        return obj;
    }

}