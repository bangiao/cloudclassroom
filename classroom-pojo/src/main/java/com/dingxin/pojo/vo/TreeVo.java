package com.dingxin.pojo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Api("树状结构实体对象")
@Builder
public class TreeVo {
    /** key */
    @ApiModelProperty(value = "id")
    private String id;

    /** value */
    @ApiModelProperty(value = "名字")
    private String label;

    /** children */
    @ApiModelProperty(value = "子节点")
    private List<TreeVo> children;

}
