package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
     * 学校
     */
    private String studentCode;
    /**
     * 院校
     */
    private String studentColleges;
    /**
     * 专业
     */
    private String studentMajor;
    /**
     * 班级
     */
    private String studentClass;
    /**
     * 教师ID
     */
    private Integer teacherId;
    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 课程id
     */
    private Integer classId;
    /**
     * 课程名字
     */
    private String className;
    /**
     * 学习时长
     */
    private String studyLength;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 是否有效
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}