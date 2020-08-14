package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 公共id request
 */
@Data
@Api("课程查询数据传输对象")
public class ClassIdRequest implements Serializable {
    @NotNull(message = "classId must not be null")
    @ApiModelProperty(value = "课程id")
    private Integer classId;
}
