package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;

/**
 *  学生学习情况实体类
 */
@TableName("crr_student_study_case")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrrStudentStudyCase extends Model<CrrStudentStudyCase> {

    private static final long serialVersionUID=1L;

    @TableId
    private Integer id;
    /**
     * 学生id
     */
    private Integer studentId;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 学号
     */
    private String studentNum;
    /**
     * 学院
     */
    private String collegeName;
    /**
     * 专业
     */
    private String major;
    /**
     * 班级
     */
    private String className;
    /**
     * 讲师id
     */
    private Integer teacherId;
    /**
     * 讲师
     */
    private String teacherName;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 课程
     */
    private String courseName;
    /**
     * 课程类型
     */
    private String courseType;
    /**
     * 课时
     */
    private String courseHour;
    /**
     * 学习时长
     */
    private Double studyTime;
    /**
     * 学习时长转换
     */
    private String studyTimeStr;
    /**
     * 是否删除
     */
    private Integer delFlag;

    /**
     * 新增时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}