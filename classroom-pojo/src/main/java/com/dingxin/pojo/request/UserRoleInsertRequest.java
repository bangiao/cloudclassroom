package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
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
    @Min(value = 1)
    private List<String> sid;
    /**
     * 角色id集合
     */
    @NotNull(message = "roles must not be null")
    @ApiModelProperty(value = "角色Id")
    private Integer roleid;
}
