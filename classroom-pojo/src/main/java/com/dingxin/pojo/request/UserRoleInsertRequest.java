package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Api("用户与角色关联新增传输对象")
public class UserRoleInsertRequest {

    /**
     * 用户id
     */
    @NotBlank(message = "casUserId must not be null")
    @ApiModelProperty(value = "用户id")
    private String casUserId;
    /**
     * 角色id集合
     */
    @NotNull(message = "roles must not be null")
    @Size(min = 1)
    @ApiModelProperty(value = "角色Id集合")
    private List<Integer> roles;
}
