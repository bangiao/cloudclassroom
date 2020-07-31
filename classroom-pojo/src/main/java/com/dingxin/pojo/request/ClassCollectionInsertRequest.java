package com.dingxin.pojo.request;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.ClassCollection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 课程收藏表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("课程收藏数据接受对象")
public class ClassCollectionInsertRequest {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID传修改 不传新增
     */
    @ApiModelProperty(value = "主键id,修改 不传新增")
    private Integer id;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    @NotNull(message = "classId must not be null")
    private Integer classId;
    /**
     * 课程名称
     */
    @NotBlank(message = "className must not be null")
    @ApiModelProperty(value = "课程名称")
    private String className;
    /**
     * 课程类型
     */
    @NotNull(message = "classType must not be null")
    @ApiModelProperty(value = "课程类型")
    private Integer classType;
    /**
     * 课程类型字符串
     */
    @NotBlank(message = "classTypeStr must not be null")
    @ApiModelProperty(value = "课程类型字符串")
    private String classTypeStr;
    /**
     * 讲师Id
     */
    @NotNull(message = "teacherId must not be null")
    @ApiModelProperty(value = "讲师Id")
    private Integer teacherId;
    /**
     * 讲师姓名
     */
    @NotBlank(message = "teacherName must not be null")
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;


    public static ClassCollection convent(ClassCollectionInsertRequest request) {
        if (Objects.isNull(request))
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        return ClassCollection.builder().classId(request.getClassId()).className(request.getClassName())
                .classType(request.getClassType()).classTypeStr(request.getClassTypeStr())
                .teacherId(request.getTeacherId()).teacherName(request.getTeacherName()).modifyTime(LocalDateTime.now()).id(request.getId()).build();
    }


}