package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 公共id request
 */
@Data
@Api("人员角色权限数据传输对象")
public class IdRoleRequest implements Serializable {
    @NotNull(message = "id must not be null")
    @ApiModelProperty(value = "主键id")
    private List<Integer> deptIds;

    @NotNull(message = "roleId must not be null")
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @NotNull(message = "queryStr must not be null")
    @ApiModelProperty(value = "模糊查询字段")
    private String queryStr;

}
