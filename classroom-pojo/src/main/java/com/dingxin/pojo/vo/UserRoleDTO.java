package com.dingxin.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Data
public class UserRoleDTO {

    /**
     * 用户id
     */
    @NotNull(message = "casUserId must not be null")
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
