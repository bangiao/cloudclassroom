package com.dingxin.cloudclassroom.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * controller 增强器
 *
 * @author jht
 * @since 2020/2/12
 */
@ControllerAdvice
public class MyControllerAdvice {
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
	private final static Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResult errorHandler(Exception ex) {
        ex.printStackTrace();
        BaseResult result = new BaseResult();
        result.exception(500,ex.getMessage());
        result.setMessage(ex.getMessage());
        return result;
    }

}