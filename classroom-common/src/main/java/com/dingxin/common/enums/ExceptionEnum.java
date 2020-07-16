package com.dingxin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author changxin.yuan
 * @date 2020/7/16 18:35
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    //1.权限
    PRIVILEGE_CAS_FAIL("1001","CAS验证失败"),

    //2.主数据配置

    //3.参数校验
    PARAMTER_ERROR("3001","参数校验失败"),

    //4.业务流程校验异常

    //5.系统级
    SYSTEM_ERROR("5001","系统内部异常");


    private String code;

    private String msg;

    public static ExceptionEnum getByCode(String code) {
        return Arrays.stream(values())
                .filter(exceptionEnum -> exceptionEnum.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
