package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * banner首页request
 */
@Data
@Api("banner首页数据传输对象")
public class BannerRequest implements Serializable {

    @NotNull(message = "id must not be null")
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "启用禁用状态（0启用1禁用）")
    private Integer disable;
}
