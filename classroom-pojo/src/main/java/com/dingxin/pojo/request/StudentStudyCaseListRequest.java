package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Api("学生学习情况课程列表请求数据传输对象")
public class StudentStudyCaseListRequest extends BaseQuery4List {

    private static final long serialVersionUID = 1L;


    /**
     * 学生id
     */
    @NotNull(message = "学生id不能为空")
    @ApiModelProperty(value = "学生id")
    private String studentId;

}
