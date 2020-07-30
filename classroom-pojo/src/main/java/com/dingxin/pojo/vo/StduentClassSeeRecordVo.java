package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 学生记录表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("学生记录数据传输返回对象")
public class StduentClassSeeRecordVo {

    private static final long serialVersionUID = 1L;
    /**
     * 逐渐
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    /**
     * 学校
     */
    @ApiModelProperty(value = "学号")
    private String studentCode;
    /**
     * 院校
     */
    @ApiModelProperty(value = "院校")
    private String studentColleges;
    /**
     * 专业
     */
    @ApiModelProperty(value = "院校")
    private String studentMajor;
    /**
     * 班级
     */
    @ApiModelProperty(value = "班级")
    private String studentClass;
    /**
     * 教师姓名
     */
    @NotNull(message = "teacherName must not be null")
    @ApiModelProperty(value = "教师姓名")
    private String teacherName;
    /**
     * 课程名字
     */
    @NotNull(message = "className must not be null")
    @ApiModelProperty(value = "课程名字")
    private String className;
    /**
     * 学习时长字符串
     */
    @ApiModelProperty(value = "学习时长字符串")
    private String studyLengthStr;

    public static StduentClassSeeRecordVo convent(StduentClassSeeRecord vo) {
        if (Objects.isNull(vo)) {
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return StduentClassSeeRecordVo.builder().studyLengthStr(vo.getStudyLengthStr())
                .className(vo.getClassName()).teacherName(vo.getTeacherName())
                .studentClass(vo.getStudentClass()).studentMajor(vo.getStudentMajor())
                .studentColleges(vo.getStudentColleges()).studentCode(vo.getStudentCode())
                .studentName(vo.getStudentName()).id(vo.getId()).build();
    }

    public static IPage<StduentClassSeeRecordVo> convertToVoWithPage(IPage<StduentClassSeeRecord> record) {
        return record.convert(StduentClassSeeRecordVo::convent);
    }

}