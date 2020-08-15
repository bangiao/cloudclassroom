package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel(value = "审核请求模型")
public class VideoAuditRequest {

    private static final long serialVersionUID=1L;

    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer curriculumId;

    /**
     * 视频id/评价id
     */
    @ApiModelProperty(value = "视频id/评价id")
    private Integer id;
    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态(1通过-1未通过)")
    private Integer auditStatus;
    /**
     * 审核内容
     */
    @ApiModelProperty(value = "审核内容")
    private String auditComments;

}
