package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.ClassEvaluate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Api("课程评价请求数据传输对象")
@Builder
public class ClassEvaluateVo {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
    /**
     * 学习时长
     */
    @ApiModelProperty(value = "学习时长")
    private String studyLength;
    /**
     * 评价时间
     */
    @ApiModelProperty(value = "评价时间")
    private LocalDateTime evaluateTime;

    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容")
    private String evaluateContent;

    /**
     * 课程名字
     */
    @ApiModelProperty(value = "课程名字")
    private String className;

    /**
     * 学生编号
     */
    @ApiModelProperty(value = "学生编号")
    private String studentCode;

    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    public static ClassEvaluateVo convertToVo(ClassEvaluate classEvaluate) {
        if (Objects.isNull(classEvaluate))
            return null;
        return ClassEvaluateVo.builder().id(classEvaluate.getId()).teacherName(classEvaluate.getTeacherName())
                .studyLength(classEvaluate.getStudyLength()).evaluateTime(classEvaluate.getEvaluateTime())
                .evaluateContent(classEvaluate.getEvaluateContent()).className(classEvaluate.getClassName())
                .studentCode(classEvaluate.getStudentCode()).studentName(classEvaluate.getStudentName()).build();
    }


    public static IPage<ClassEvaluateVo> convertToVoWithPage(IPage<ClassEvaluate> classEvaluate) {

        return classEvaluate.convert(ClassEvaluateVo::convertToVo);
    }

}
