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
    VERIFY_EXCEL_ERROR("3002","EXCEL内容错误"),
    REQUIRED_PARAM_IS_NULL("3003","所需参数不能为空"),

    //4.业务流程校验异常

    DUPLICATE_DATA("4001","重复的数据"),
    COVENT_NULLPOINT("4002","转换源对象为空"),

        //5.系统级
    SYSTEM_ERROR("5001","系统内部异常"),


    //unknown
    UNKNOWN_ERROR("9999","未知异常");

    private String code;

    private String msg;

    public static ExceptionEnum getByCode(String code) {
        return Arrays.stream(values())
                .filter(exceptionEnum -> exceptionEnum.getCode().equals(code))
                .findFirst()
                .orElse(UNKNOWN_ERROR);
    }

}
