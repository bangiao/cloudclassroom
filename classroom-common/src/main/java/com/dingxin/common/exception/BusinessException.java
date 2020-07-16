package com.dingxin.common.exception;

import com.dingxin.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author changxin.yuan
 * @date 2020/7/16 18:35
 */
@Getter
public class BusinessException  extends RuntimeException {

    /**
     * code
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    public BusinessException(ExceptionEnum em) {
        super();
        this.code = em.getCode();
        this.msg = em.getMsg();
    }

}
