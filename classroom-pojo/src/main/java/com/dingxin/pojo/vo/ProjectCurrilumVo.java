package com.dingxin.pojo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ya.nie
 * @date 2020/8/14 16:55
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("课程次数视图")
public class ProjectCurrilumVo {

    @ApiModelProperty(value = "专题id")
    private Integer projectId;

    @ApiModelProperty(value = "课程观看次数")
    private Long watchAmount;

    @ApiModelProperty(value = "课程数量统计")
    private Integer courseNum;


}
