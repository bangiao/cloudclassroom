package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 课程收藏表 实体类
 */
@Data
@Api("课程收藏数据传输对象")
public class ClassCollectionRequest extends BaseQuery4List {

    private static final long serialVersionUID=1L;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    @NotBlank(message = "className must not be blank")
    private String className;
    /**
     * 课程类型
     */
    @NotBlank(message = "classType must not be blank")
    @ApiModelProperty(value = "课程类型")
    private Integer classType;
    /**
     * 课程类型字符串
     */
    @NotBlank(message = "classTypeStr must not be blank")
    @ApiModelProperty(value = "课程类型字符串")
    private String classTypeStr;
    /**
     * 讲师姓名
     */
    @NotBlank(message = "teacherName must not be blank")
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
}