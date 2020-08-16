package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 公共id request
 */
@Data
@Api("编辑个人介绍requset")
public class PersonInfoRequest implements Serializable {
    @ApiModelProperty(value = "个人介绍")
    private String introduction;
}
