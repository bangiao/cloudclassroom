package com.dingxin.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 角色菜单业务类
 */
@Data
public class RoleMenuVo {
    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 菜单Id集合
     */
    @NotNull(message = "mens must not be null")
    @Size(min=1)
    private List<Integer> menus;
}
