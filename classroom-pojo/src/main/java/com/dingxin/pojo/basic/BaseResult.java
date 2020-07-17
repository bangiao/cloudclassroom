package com.dingxin.pojo.basic;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 结果返回集
 */
@Data
@Accessors(chain=true)
@NoArgsConstructor
public class BaseResult<T> {
    private String code = "0000";
    private String msg = "";
    private T data = null;

    private static final BaseResult SUCCESS = new BaseResult();

    public static BaseResult success() {
        return SUCCESS;
    }

    public static BaseResult success(Object data) {
        return new BaseResult().setData(data);
    }

    public static BaseResult success(Object data, String success) {
        return new BaseResult().setData(data).setMsg(success);
    }

    public static BaseResult failed(ExceptionEnum exceptionEnum) {
        return new BaseResult().setCode(exceptionEnum.getCode()).setMsg(exceptionEnum.getMsg());
    }

    public static BaseResult failed(BusinessException e) {
        return new BaseResult().setCode(e.getCode()).setMsg(e.getMsg());
    }

    public static BaseResult failed(ExceptionEnum exceptionEnum,Object data) {
        return new BaseResult()
                .setCode(exceptionEnum.getCode())
                .setMsg(exceptionEnum.getMsg())
                .setData(data);
    }

}
