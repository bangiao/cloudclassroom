package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 课程评价表 实体类
 */
@TableName("ccr_class_evaluate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassEvaluate extends Model<ClassEvaluate> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 课程id
     */
    @NotNull(message="classId must not be null")
    private Integer classId;
    /**
     * 课程名字
     */
    @NotNull(message = "className must not be null")
    private String className;

    /**
     * 课程类型
     */
    @NotNull(message = "classType must not be null")
    private String classType;
    /**
     * 课程类型
     */
    @NotNull(message = "classTypeStr must not be null")
    private String classTypeStr;
    /**
     * 讲师id
     */
    @NotNull(message = "teacherId must not be null")
    private Integer teacherId;
    /**
     * 讲师姓名
     */
    @NotNull(message = "teacherName must not be null")
    private String teacherName;
    /**
     * 学习时长
     */
    private String studyLength;
    /**
     * 学生id
     */
    @NotNull(message = "studentId must not be null")
    private Integer studentId;
    /**
     * 学生姓名
     */
    @NotNull(message = "studentName must not be null")
    private String studentName;
    /**
     * 学生编号
     */
    @NotNull(message = "studentCode must not be null")
    private String studentCode;
    /**
     * 评价时间
     */
    private LocalDateTime evaluateTime;
    /**
     * 评价内容
     */
    @NotNull(message = "evaluateContent must not be null")
    private String evaluateContent;
    /**
     * 评价分数
     */
    private Double evaluateScore;
    /**
     * 点赞次数
     */
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
    private Integer status;
    /**
     * 是否删除
     */
    private Integer delFlag;

    @TableField(exist = false)
    private String queryStr;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}