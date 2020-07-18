package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
    private Integer classId;
    /**
     * 课程名字
     */
    private String className;
    /**
     * 讲师id
     */
    private Integer tId;
    /**
     * 讲师姓名
     */
    private String tName;
    /**
     * 学习时长
     */
    private String studyLength;
    /**
     * 学生id
     */
    private Integer sId;
    /**
     * 学生姓名
     */
    private String sName;
    /**
     * 学生编号
     */
    private String sCode;
    /**
     * 评价时间
     */
    private LocalDateTime evaluateTime;
    /**
     * 评价内容
     */
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
     * 是否删除
     */
    private Integer yn;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}