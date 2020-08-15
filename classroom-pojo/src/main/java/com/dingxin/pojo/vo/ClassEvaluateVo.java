package com.dingxin.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.pojo.po.ClassEvaluate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Api("课程评价请求数据传输对象")
@Builder
public class ClassEvaluateVo extends BaseRowModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 讲师姓名
     */
    @ExcelProperty(value = "讲师姓名", index = 6)
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
    /**
     * 学习时长
     */
    @ExcelProperty(value = "学习时长", index = 5)
    @ApiModelProperty(value = "学习时长")
    private String studyLength;
    /**
     * 评价时间
     */
    @ExcelProperty(value = "评价时间", index = 4)
    @ApiModelProperty(value = "评价时间")
    private LocalDateTime evaluateTime;

    /**
     * 评价内容
     */
    @ExcelProperty(value = "评价内容", index = 3)
    @ApiModelProperty(value = "评价内容")
    private String evaluateContent;

    /**
     * 课程名字
     */
    @ExcelProperty(value = "课程名字", index = 2)
    @ApiModelProperty(value = "课程名字")
    private String className;

    /**
     * 学生编号
     */
    @ExcelProperty(value = "学生编号", index = 1)
    @ApiModelProperty(value = "学生编号")
    private String studentCode;

    /**
     * 学生姓名
     */
    @ExcelProperty(value = "学生姓名", index = 0)
    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    public static ClassEvaluateVo convertToVo(ClassEvaluate classEvaluate) {
        if (Objects.isNull(classEvaluate))
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        return ClassEvaluateVo.builder().id(classEvaluate.getId()).teacherName(classEvaluate.getTeacherName())
                .studyLength(classEvaluate.getStudyLength()).evaluateTime(classEvaluate.getEvaluateTime())
                .evaluateContent(classEvaluate.getEvaluateContent()).className(classEvaluate.getClassName())
                .studentCode(classEvaluate.getStudentCode()).studentName(classEvaluate.getStudentName()).build();
    }


    public static IPage<ClassEvaluateVo> convertToVoWithPage(IPage<ClassEvaluate> classEvaluate) {

        return classEvaluate.convert(ClassEvaluateVo::convertToVo);
    }

    public static List<ClassEvaluateVo> conventList(List<ClassEvaluate> list){
        if (CollectionUtils.isEmpty(list)){
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return list.stream().map(ClassEvaluateVo::convertToVo).collect(Collectors.toList());
    }

}
