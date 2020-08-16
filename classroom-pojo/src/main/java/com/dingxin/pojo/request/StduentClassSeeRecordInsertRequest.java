package com.dingxin.pojo.request;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.CasEmploys;
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
     * 教师ID
     */
    @NotNull(message = "teacherId must not be null")
    @ApiModelProperty(value = "教师ID")
    private String teacherId;
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
     * 课程名字
     */
    @NotBlank(message = "className must not be null")
    @ApiModelProperty(value = "课程名字")
    private String classTypeName;
    /**
     * 课程名字
     */
    @NotBlank(message = "className must not be null")
    @ApiModelProperty(value = "课程名字")
    private String classTypeId;
    /**
     * 学习时长
     */
    @ApiModelProperty(value = "学习时长")
    @NotNull
    private Long studyLength;

    public static StduentClassSeeRecord convent(StduentClassSeeRecordInsertRequest request){
        if (Objects.isNull(request)){
           throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return StduentClassSeeRecord.builder()
                .studyLength(request.getStudyLength())
                .className(request.getClassName())
                .classId(request.getClassId())
                .teacherName(request.getTeacherName())
                .teacherId(request.getTeacherId())
                .classTypeName(request.getClassTypeName())
                .classTypeId(request.getClassTypeId())
                .build();
    }

}