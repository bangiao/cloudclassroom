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
@Api("角色查询人员数据传输对象")
public class RoleIdRequest implements Serializable {
    @NotNull(message = "roleId must not be null")
    @ApiModelProperty(value = "角色Id")
    private Integer roleId;
}
