package com.dingxin.sdk.exception;

import com.dingxin.common.enums.ExceptionEnum;

/**
 * @author changxin.yuan
 * @date 2020/7/30 15:17
 */
public class VodUploadException extends RuntimeException {

    /**
     * code
     */
    private String code;

    /**
     * 信息
     */
    private String msg;


    public VodUploadException(ExceptionEnum em) {
        super();
        this.code = em.getCode();
        this.msg = em.getMsg();
    }

}