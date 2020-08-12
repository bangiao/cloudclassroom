package com.dingxin.pojo.request;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.ClassEvaluate;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 课程评价表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassEvaluateInsertRequest {

    private static final long serialVersionUID = 1L;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    @NotNull(message = "classId must not be null")
    private Integer classId;
    /**
     * 课程名字
     */
    @ApiModelProperty(value = "课程名字")
    @NotBlank(message = "className must not be null")
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
    @NotBlank(message = "classTypeStr must not be null")
    private String classTypeStr;
    /**
     * 讲师id
     */
    @ApiModelProperty(value = "讲师id")
    @NotNull(message = "teacherId must not be null")
    private String teacherId;
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
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    @NotBlank(message = "evaluateContent must not be null")
    private String evaluateContent;

    public static ClassEvaluate covent(ClassEvaluateInsertRequest request) {
        if (Objects.isNull(request)){
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return ClassEvaluate.builder().classId(request.getClassId()).className(request.getClassName())
                .classType(request.getClassType())
                .classTypeStr(request.getClassTypeStr()).teacherId(request.getTeacherId()).teacherName(request.getTeacherName())
                .studyLength(request.getStudyLength()).studentId("1")
                .studentName("杨大少").studentCode("0000").evaluateTime(LocalDateTime.now())
                .evaluateContent(request.getEvaluateContent()).build();
    }
}