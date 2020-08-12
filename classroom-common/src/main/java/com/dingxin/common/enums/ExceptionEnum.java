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
    PRIVILEGE_GET_USER_FAIL("1002","获取当前登录用户失败"),
    USER_NOT_CAN_BE_QUERY_USER("1003","权限不足"),

    //2.主数据配置

    //3.参数校验
    PARAMTER_ERROR("3001","参数校验失败"),
    VERIFY_EXCEL_ERROR("3002","EXCEL内容错误"),
    REQUIRED_PARAM_IS_NULL("3003","所需参数不能为空"),
    MULTIPARTFILE_TYPE_ERROR("3004","上传图片类型不正确"),


    //4.业务流程异常
    DUPLICATE_DATA("4001","重复的数据"),
    COVENT_NULLPOINT("4002","转换源对象为空"),
    DATA_ZERO("4003","查询对象不存在"),
    BANNER_MSG("4004","启用banner图不能超过3个，请先禁用"),



    //5.rpc,
    VOD_UPLOAD_ERROR("5001","vod上传失败"),
    VOD_SEARCH_ERROR("5002","调用vod搜索视频失败"),
    VOD_DELETE_ERROR("5003","调用vod删除视频失败"),



    //6.系统级
    SYSTEM_ERROR("6001","系统内部异常"),


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
