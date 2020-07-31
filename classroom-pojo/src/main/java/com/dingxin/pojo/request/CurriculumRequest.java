package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author: cuteG <br>
 * date: 2020/7/31 16:24 <br>
 * description: todo <br>
 */
@Data
@ApiModel("课程数据传输对象")
public class CurriculumRequest {
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称",example = "大力怎么吃最好")
    private String curriculumName;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型",example = "大力学")
    private String curriculumType;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师",example = "LBWNB")
    private String teacherName;
    /**
     * 审核状态(-1 未通过，0未审核，1审核通过）
     */
    @ApiModelProperty("审核状态(-1 未通过，0未审核，1审核通过)")
    private Integer auditFlag;
}