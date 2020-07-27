package com.dingxin.pojo.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Api("课程评价新增请求数据传输对象")
public class ClassEvaluateInsertRequest extends BaseQuery4List {

    private static final long serialVersionUID=1L;


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
    private String classType;
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

}
