package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 公共id request
 */
@Data
@Api("主键查询数据传输对象")
public class IdRequest {
    @NotNull(message = "id must not be null")
    @ApiModelProperty(value = "主键id")
    private Integer id;
}
