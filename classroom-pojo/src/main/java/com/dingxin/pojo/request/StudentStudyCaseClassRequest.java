package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Api("学生学习情况课程删除数据传输对象")
public class StudentStudyCaseClassRequest{

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    @NotNull(message = "studentId")
    @ApiModelProperty(value = "学生id")
    private Integer studentId;

    /**
     * 课程id
     */
    @NotNull(message = "classId")
    @ApiModelProperty(value = "课程id")
    private Integer classId;

}
