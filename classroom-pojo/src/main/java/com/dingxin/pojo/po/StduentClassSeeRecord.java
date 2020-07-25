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
 * 学生记录表 实体类
 */
@TableName("ccr_stduent_class_see_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StduentClassSeeRecord extends Model<StduentClassSeeRecord> {

    private static final long serialVersionUID=1L;

    /**
     * 逐渐
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
     * 学生id
     */
    @NotNull(message="studentId must not be null")
    @ApiModelProperty(value = "学生id")
    private Integer studentId;
    /**
     * 学生姓名
     */
    @NotNull(message="studentName must not be null")
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    /**
     * 学校
     */
    @NotNull(message="studentCode must not be null")
    @ApiModelProperty(value = "学校")
    private String studentCode;
    /**
     * 院校
     */
    @NotNull(message="studentColleges must not be null")
    @ApiModelProperty(value = "院校")
    private String studentColleges;
    /**
     * 专业
     */
    @NotNull(message="studentMajor must not be null")
    @ApiModelProperty(value = "院校")
    private String studentMajor;
    /**
     * 班级
     */
    @NotNull(message="studentClass must not be null")
    @ApiModelProperty(value = "班级")
    private String studentClass;
    /**
     * 教师ID
     */
    @NotNull(message="teacherId must not be null")
    @ApiModelProperty(value = "教师ID")
    private Integer teacherId;
    /**
     * 教师姓名
     */
    @NotNull(message="teacherName must not be null")
    @ApiModelProperty(value = "教师姓名")
    private String teacherName;
    /**
     * 课程id
     */
    @NotNull(message="classId must not be null")
    @ApiModelProperty(value = "课程id")
    private Integer classId;
    /**
     * 课程名字
     */
    @NotNull(message="className must not be null")
    @ApiModelProperty(value = "课程名字")
    private String className;
    /**
     * 学习时长
     */
    @ApiModelProperty(value = "学习时长")
    private Long studyLength;
    /**
     * 学习时长字符串
     */
    @ApiModelProperty(value = "学习时长字符串")
    private String studyLengthStr;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;
    /**
     * 是否有效
     */
    @ApiModelProperty(value = "是否有效")
    private Integer delFlag;

    @TableField(exist = false)
    private String queryStr;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}