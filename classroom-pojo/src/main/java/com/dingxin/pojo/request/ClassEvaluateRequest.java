package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "审核请求模型")
public class ClassEvaluateRequest {

    private static final long serialVersionUID=1L;

    /**
     * 主键ids
     */
    @NotEmpty(message = "id不能为空")
    @ApiModelProperty(value = "主键ids")
    private List<Integer> idList;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String auditComments;

    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer classId;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态(1通过-1未通过)")
    private Integer auditStatus;

}
