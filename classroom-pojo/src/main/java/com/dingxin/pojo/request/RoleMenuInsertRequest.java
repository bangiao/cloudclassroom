package com.dingxin.pojo.request;

import io.swagger.annotations.Api;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 角色菜单业务类
 */
@Data
@Api("角色新增菜单数据传输类")
public class RoleMenuInsertRequest {
    /**
     * 角色Id
     */
    @NotNull(message = "roleId must not be null")
    private Integer roleId;

    /**
     * 菜单Id集合
     */
    @NotNull(message = "menus must not be null")
    @Size(min = 1)
    private List<Integer> menus;
}
