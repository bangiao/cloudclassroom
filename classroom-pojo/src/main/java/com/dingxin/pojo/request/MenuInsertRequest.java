package com.dingxin.pojo.request;

import com.dingxin.pojo.po.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单管理 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuInsertRequest {

    private static final long serialVersionUID = 1L;


    private Integer id;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    @NotBlank(message = "name must not be null")
    @ApiModelProperty(value = "菜单名称")
    private String name;
    /**
     * 菜单URL
     */
    @NotBlank(message = "url must not be null")
    @ApiModelProperty(value = "菜单URL")
    private String url;
    /**
     * 1：菜单   2：按钮
     */
    @NotNull(message = "type must not be null")
    @ApiModelProperty(value = "1：菜单   2：按钮")
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    @NotNull(message = "orderNum must not be null")
    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    public static Menu convent(MenuInsertRequest request) {
        return Menu.builder().id(request.getId()).parentId(request.getParentId())
                .name(request.getName()).url(request.getUrl())
                .type(request.getType()).icon(request.getIcon()).build();
    }
}