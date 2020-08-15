package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@Api("学号、工号查询数据传输对象")
@Builder
public class WidRequest implements Serializable {
    @NotNull(message = "id must not be null")
    @ApiModelProperty(value = "学号||职工号")
    private String wid;
}
