package com.dingxin.pojo.request;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 学生记录表接受表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("学生记录数据接受对象")
public class StduentClassSeeRecordInsertRequest {

    private static final long serialVersionUID = 1L;
    /**
     * 学生id
     */
    @NotNull(message = "studentId must not be null")
    @ApiModelProperty(value = "学生id")
    private Integer studentId;
    /**
     * 学生姓名
     */
    @NotBlank(message = "studentName must not be null")
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    /**
     * 学校
     */
    @NotBlank(message = "studentCode must not be null")
    @ApiModelProperty(value = "学校")
    private String studentCode;
    /**
     * 院校
     */
    @NotBlank(message = "studentColleges must not be null")
    @ApiModelProperty(value = "院校")
    private String studentColleges;
    /**
     * 专业
     */
    @NotBlank(message = "studentMajor must not be null")
    @ApiModelProperty(value = "专业")
    private String studentMajor;
    /**
     * 班级
     */
    @NotBlank(message = "studentClass must not be null")
    @ApiModelProperty(value = "班级")
    private String studentClass;
    /**
     * 教师ID
     */
    @NotNull(message = "teacherId must not be null")
    @ApiModelProperty(value = "教师ID")
    private Integer teacherId;
    /**
     * 教师姓名
     */
    @NotBlank(message = "teacherName must not be null")
    @ApiModelProperty(value = "教师姓名")
    private String teacherName;
    /**
     * 课程id
     */
    @NotNull(message = "classId must not be null")
    @ApiModelProperty(value = "课程id")
    private Integer classId;
    /**
     * 课程名字
     */
    @NotBlank(message = "className must not be null")
    @ApiModelProperty(value = "课程名字")
    private String className;
    /**
     * 学习时长
     */
    @ApiModelProperty(value = "学习时长")
    @NotNull
    @Min(1)
    private Long studyLength;

    public static StduentClassSeeRecord convent(StduentClassSeeRecordInsertRequest request){
        if (Objects.isNull(request)){
           throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return StduentClassSeeRecord.builder().studyLength(request.getStudyLength()).className(request.getClassName()).classId(request.getClassId())
                .teacherName(request.getTeacherName()).teacherId(request.getTeacherId()).studentClass(request.getStudentClass())
                .studentMajor(request.getStudentMajor()).studentColleges(request.getStudentColleges())
                .studentCode(request.getStudentCode()).studentName(request.getStudentName()).studentId(request.getStudentId()).build();
    }

}