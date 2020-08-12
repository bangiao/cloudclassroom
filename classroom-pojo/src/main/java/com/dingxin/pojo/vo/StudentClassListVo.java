package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.Role;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.po.Student;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 学生信息表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("学生学习情况 课程列表")
public class StudentClassListVo extends Model<StudentClassListVo> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键 查看学习记录传这个Id过去 和classId 过去")
    private String studentId;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer classId;

    /**
     * 学习的课程名字
     */
    @ApiModelProperty(value = "学习的课程名字")
    private String className;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型")
    private String classType;

    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师")
    private String teacherName;

    /**
     * 学习开始时间
     */
    @ApiModelProperty(value = "学习开始时间")
    private LocalDateTime startStudentTime;


    /**
     * 学习时长字符串
     */
    @ApiModelProperty(value = "学习时长字符串")
    private String studyLengthStr;


    public static StudentClassListVo convent(StduentClassSeeRecord record) {
        if (Objects.isNull(record)) {
            return null;
        }
        return StudentClassListVo.builder().teacherName(record.getTeacherName()).classType(record.getClassName())
                .className(record.getClassName()).studyLengthStr(record.getStudyLengthStr())
                .startStudentTime(record.getCreateTime()).classId(record.getClassId()).studentId(record.getStudentId()).build();
    }

    public static IPage<StudentClassListVo> convertToVoWithPage(IPage<StduentClassSeeRecord> record) {
        return record.convert(StudentClassListVo::convent);
    }

}