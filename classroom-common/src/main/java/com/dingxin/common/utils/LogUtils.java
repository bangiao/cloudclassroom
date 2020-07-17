package com.dingxin.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Somnus丶y
 * @description 日志工具类
 * @date 2019/9/2 10:42
 */
public class LogUtils {

    /**
     *
     */
    public static final String LOGID = "logId";

    /**
     * @return void
     * @throws
     * @description 初始化logid
     * @author Somnus丶y
     * @date 2019/9/12
     */
    public static void initLogId() {
        MDC.put(LOGID, UUID.randomUUID().toString().replaceAll("-", ""));
    }

    /**
     * @return void
     * @throws
     * @description 如果不存在，初始化logid
     * @author Somnus丶y
     * @date 2019/9/12
     */
    public static void initLogIdIfAbsent() {
        String logId = MDC.get(LOGID);
        if (StringUtils.isEmpty(logId)) {
            logId = UUID.randomUUID().toString().replaceAll("-", "");
            MDC.put(LOGID, logId);
        }
    }

    /**
     * @param logId
     * @return void
     * @throws
     * @description 设置logid
     * @author Somnus丶y
     * @date 2019/9/12
     */
    public static void putLogId(String logId) {
        MDC.put(LOGID, logId);
    }

    /**
     * @return java.lang.String
     * @throws
     * @description 获取logid
     * @author Somnus丶y
     * @date 2019/9/12
     */
    public static String getLogId() {
        return MDC.get(LOGID);
    }

    /**
     * @return java.lang.StackTraceElement
     * @throws
     * @description 获取真正的caller堆栈
     * @author Somnus丶y
     * @date 2019/9/2
     */
    private static StackTraceElement findCaller() {
        //获取当前线程的所有堆栈信息
        StackTraceElement[] callerStackArrays = Thread.currentThread().getStackTrace();
        if (Objects.isNull(callerStackArrays)) {
            return null;
        }
        //日志类名称
        String logClassName = LogUtils.class.getName();

        //最终要返回的堆栈信息
        StackTraceElement caller = null;

        //标志获取到当前日志类标志
        boolean isLogClassName = false;

        for (StackTraceElement stackTraceElement : callerStackArrays) {
            //获取到当前类的地方
            if (logClassName.equals(stackTraceElement.getClassName())) {
                isLogClassName = true;
            }
            //下一个不是本类的就是原始的调用的类的堆栈信息
            if (!isLogClassName) {
                continue;
            }
            if (!logClassName.equals(stackTraceElement.getClassName())) {
                isLogClassName = false;
                caller = stackTraceElement;
                break;
            }
        }
        return caller;
    }

    /**
     * @return org.slf4j.Logger
     * @throws
     * @description 获取caller栈上的logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    private static Logger getLogger() {
        StackTraceElement caller = findCaller();
        if (Objects.isNull(caller)) {
            return LoggerFactory.getLogger(LogUtils.class);
        }
        return LoggerFactory.getLogger(caller.getClassName() + "." +
                                       caller.getMethodName() + "() [line:" + caller.getLineNumber() + " ]");
    }

    /**
     * @param message
     * @return void
     * @throws
     * @description warn
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(String message) {
        getLogger().warn(message);
    }

    /**
     * @param message, obj
     * @return void
     * @throws
     * @description warn 含参
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(String message, Object... obj) {
        getLogger().warn(String.format(message, obj));
    }

    /**
     * @param logger  外部logger
     * @param message
     * @return void
     * @throws
     * @description warn   接收外部logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(Logger logger, String message) {
        logger.warn(message);
    }

    /**
     * @param logger, reg, obj
     * @return void
     * @throws
     * @description format warn
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void warn(Logger logger, String reg, Object... obj) {
        logger.warn(String.format(reg, obj));
    }

    /**
     * @param message
     * @return void
     * @throws
     * @description info
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(String message) {
        getLogger().info(message);
    }

    /**
     * @param message, obj
     * @return void
     * @throws
     * @description 含参info
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(String message, Object... obj) {
        getLogger().info(String.format(message, obj));
    }

    /**
     * @param logger  外部logger
     * @param message
     * @return void
     * @throws
     * @description info   接收外部logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(Logger logger, String message) {
        logger.info(message);
    }

    /**
     * @param logger, reg, obj
     * @return void
     * @throws
     * @description format info
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void info(Logger logger, String reg, Object... obj) {
        logger.info(String.format(reg, obj));
    }

    /**
     * @param message
     * @return void
     * @throws
     * @description debug
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(String message) {
        getLogger().debug(message);
    }

    /**
     * @param message, obj
     * @return void
     * @throws
     * @description 警告日志, 含参
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(String message, Object... obj) {
        getLogger().debug(String.format(message, obj));
    }

    /**
     * @param logger  外部logger
     * @param message
     * @return void
     * @throws
     * @description debug   接收外部logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(Logger logger, String message) {
        logger.warn(message);
    }

    /**
     * @param logger, reg, obj
     * @return void
     * @throws
     * @description format debug
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void debug(Logger logger, String reg, Object... obj) {
        logger.warn(String.format(reg, obj));
    }

    /**
     * @param message
     * @return void
     * @throws
     * @description 错误日志，不含异常
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(String message) {
        getLogger().error(message);
    }

    /**
     * @param message, obj
     * @return void
     * @throws
     * @description 警告日志, 含参
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(String message, Object... obj) {
        getLogger().error(String.format(message, obj));
    }

    /**
     * @param message, e
     * @return void
     * @throws
     * @description 错误日志，含异常
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(String message, Exception e) {
        getLogger().error(message, e);
    }

    /**
     * @param message, e, obj
     * @return void
     * @throws
     * @description 警告日志, 含参
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Throwable e, String message, Object... obj) {
        getLogger().error(String.format(message, obj), e);
    }

    /**
     * @param message
     * @return void
     * @throws
     * @description 错误日志，不含异常,外部logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger, String message) {
        logger.error(message);
    }

    /**
     * @param message, obj
     * @return void
     * @throws
     * @description 警告日志, 含参, 外部logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger, String message, Object... obj) {
        logger.error(String.format(message, obj));
    }

    /**
     * @param message, e
     * @return void
     * @throws
     * @description 错误日志，含异常,外部logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger, Throwable e, String message) {
        logger.error(message, e);
    }

    /**
     * @param message, e, obj
     * @return void
     * @throws
     * @description 警告日志, 含参, 外部logger
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static void error(Logger logger, Throwable e, String message, Object... obj) {
        logger.error(String.format(message, obj), e);
    }
}

