package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2020/8/15
 */
@Api("获取时间段内某学生的课程列表请求对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesStudentRequest extends WidRequest {

    @ApiModelProperty("结束时间")
    @NotBlank(message = "结束时间不能为null")
    private String endDate;

    @ApiModelProperty("开始时间")
    @NotBlank(message = "结束时间不能为null")
    private String startDate;
}
