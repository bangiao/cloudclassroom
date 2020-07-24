package com.dingxin.web.interceptors;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.exception.ExcelGenerateException;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.basic.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author changxin.yuan
 * @date 2020/7/16 21:51
 */
@Slf4j
@Component
@ControllerAdvice
public class ExceptionHandlerAdvice {



    @ResponseBody
    @ExceptionHandler({ BusinessException.class })
    public BaseResult businessException(BusinessException e) {
        log.error("ExceptionHandler获取businessException异常",e);
        return BaseResult.failed(e);
    }


    @ResponseBody
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public BaseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("ExceptionHandler获取MethodArgumentNotValidException异常",e);
        String field = e.getBindingResult().getFieldError().getField();
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return BaseResult.failed(ExceptionEnum.PARAMTER_ERROR).setMsg(field + ":" + defaultMessage);
    }


    @ResponseBody
    @ExceptionHandler(value = { BindException.class})
    public BaseResult bindException(BindException e) {
        log.error("参数校验异常", e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (CollectionUtils.isEmpty(allErrors)) {
            return BaseResult.failed(ExceptionEnum.PARAMTER_ERROR).setMsg(e.getMessage());
        }
        StringBuilder msg = new StringBuilder(128);
        for (ObjectError err : allErrors) {
            msg.append(err.getDefaultMessage()).append(",");
        }
        if (msg.length() > 0) {
            msg.deleteCharAt(msg.lastIndexOf(","));
        }
        return BaseResult.failed(ExceptionEnum.PARAMTER_ERROR).setMsg(msg.toString());
    }



    @ResponseBody
    @ExceptionHandler(value = { ExcelAnalysisException.class, ExcelGenerateException.class})
    public <T> BaseResult<T> excelException(Exception e) {
        log.error("excel异常", e);
        return BaseResult.failed(ExceptionEnum.VERIFY_EXCEL_ERROR).setMsg(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({ Exception.class })
    public BaseResult exception(HttpServletRequest request, HttpServletResponse response,
                                Exception e) {
        log.error("系统异常",e);
        BaseResult success = BaseResult.success();
        return BaseResult.failed(ExceptionEnum.SYSTEM_ERROR);
    }

}
