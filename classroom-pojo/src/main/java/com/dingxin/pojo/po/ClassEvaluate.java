package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程评价表 实体类
 */
@TableName("ccr_class_evaluate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassEvaluate extends Model<ClassEvaluate> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    @NotNull(message="classId must not be null")
    private Integer classId;
    /**
     * 课程名字
     */
    @ApiModelProperty(value = "课程名字")
    @NotNull(message = "className must not be null")
    private String className;

    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型Id")
    @NotNull(message = "classType must not be null")
    private Integer classType;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型")
    @NotNull(message = "classTypeStr must not be null")
    private String classTypeStr;
    /**
     * 讲师id
     */
    @ApiModelProperty(value = "讲师id")
    @NotNull(message = "teacherId must not be null")
    private Integer teacherId;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师姓名")
    @NotNull(message = "teacherName must not be null")
    private String teacherName;
    /**
     * 学习时长
     */
    @ApiModelProperty(value = "学习时长")
    private String studyLength;
    /**
     * 学生id
     */
    @ApiModelProperty(value = "学生id")
    @NotNull(message = "studentId must not be null")
    private Integer studentId;
    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    @NotNull(message = "studentName must not be null")
    private String studentName;
    /**
     * 学生编号
     */
    @ApiModelProperty(value = "学生编号")
    @NotNull(message = "studentCode must not be null")
    private String studentCode;
    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间")
    private LocalDateTime evaluateTime;
    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    @NotNull(message = "evaluateContent must not be null")
    private String evaluateContent;
    /**
     * 评价分数
     */
    @ApiModelProperty(value = "评价分数")
    private Double evaluateScore;
    /**
     * 点赞次数
     */
    @ApiModelProperty(value = "点赞次数")
    private Integer evaluateCount;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;
    /**
     * 最后修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 审核状态0为未审核1为已审核-1为未通过
     */
    @ApiModelProperty(value = "审核状态0为未审核1为已审核-1为未通过")
    private Integer status;
    /**
     * 审批意见
     */
    private String auditComments;
    /**
     * 是否删除
     */
    private Integer delFlag;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}