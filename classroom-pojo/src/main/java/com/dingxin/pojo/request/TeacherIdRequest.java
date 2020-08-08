package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 公共id request
 */
@Data
@Api("主键查询数据传输对象")
public class TeacherIdRequest implements Serializable {
    @NotBlank(message = "教师id不能为空")
    @ApiModelProperty(value = "主键id")
    private String jg0101id;
}
