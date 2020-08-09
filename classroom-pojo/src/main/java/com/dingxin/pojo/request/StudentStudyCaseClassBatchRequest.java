package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Api("学生学习情况课程删除数据传输对象")
public class StudentStudyCaseClassBatchRequest {

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
    @NotNull(message = "classIdList")
    @ApiModelProperty(value = "课程id集合")
    private List<Integer> classIdList;

}
