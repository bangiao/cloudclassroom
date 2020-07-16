package com.dingxin.cloudclassroom.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by herui
 * 结果返回集
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseResult<T> {
    private int code = 200;
    private boolean success = true;
    private String errorInfo = "default success info";
    private T data = null;
    private T sec = null;
    @ApiModelProperty(value = "接口返回消息")
    private String message;
    private int status = 0;

    public BaseResult<T> success() {
        this.success = true;
        this.code = 200;
        this.errorInfo = "default success info";
        this.message = "default success info";
        return this;
    }

    public BaseResult<T> success(T data) {
        this.success();
        this.data = data;
        return this;
    }

    public BaseResult<T> success(T data, String success) {
        this.success();
        this.data = data;
        this.errorInfo = success;
        this.message = success;
        return this;
    }

    public BaseResult<T> failed() {
        this.success = false;
        this.code = 500;
        this.errorInfo = "default failed info";
        this.message = "default failed info";
        return this;
    }

    public BaseResult<T> failed(String errorInfo) {
        this.success = false;
        this.code = 500;
        this.errorInfo = errorInfo;
        this.message = errorInfo;
        return this;
    }
    public BaseResult<T> failed(String errorInfo,Integer code) {
        this.success = false;
        this.code = code;
        this.errorInfo = errorInfo;
        this.message = errorInfo;
        return this;
    }

    public BaseResult<T> exception(int code, String errorInfo) {
        this.success = false;
        this.code = code;
        this.errorInfo = errorInfo;
        return this;
    }
    public static BaseResult ok(String message){
        BaseResult result = new BaseResult<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage(message);
        return result;
    }
    public static BaseResult error(String message){
        BaseResult result = new BaseResult<>();
        result.success = false;
        result.setCode(500);
        result.setMessage(message);
        result.setData(message);
        return result;
    }


    /**
     * 错误请求针对参数错误的返回
     *
     * @return
     */
    public BaseResult notFound() {
        this.success = false;
        this.code = 404;
        this.errorInfo = "未查询到资源";
        return this;
    }
}
