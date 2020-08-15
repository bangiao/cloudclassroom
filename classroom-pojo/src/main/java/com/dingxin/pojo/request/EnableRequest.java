package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ya.nie
 * @date 2020/8/15 19:50
 * @Description
 */
@Data
public class EnableRequest {
    @NotNull(message = "id can not be null")
    @ApiModelProperty("id")
    private Integer id;
    @NotNull(message = "zgh can not be null")
    @ApiModelProperty("讲师id")
    private Integer zgh;

    @NotNull(message = "enableState can not be null")
    @ApiModelProperty("禁用状态")
    private Integer enableState;
}
