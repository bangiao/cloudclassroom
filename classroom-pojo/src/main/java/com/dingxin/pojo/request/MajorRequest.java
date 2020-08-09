package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Api("专业查询对象")
public class MajorRequest {

    @ApiModelProperty("部门Id")
    @NotNull(message = "院系ID不能为null")
    private String deptId;
}
