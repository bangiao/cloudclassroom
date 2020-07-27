package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单管理 实体类
 */
@TableName("sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends Model<Menu> {

    private static final long serialVersionUID=1L;

    private String id;
    /**
     * 父菜单ID，一级菜单为0
     */
    private String parentId;
    /**
     * 菜单名称
     */
    @NotNull(message = "name must not be null")
    @ApiModelProperty(value = "菜单名称")
    private String name;
    /**
     * 菜单URL
     */
    @NotNull(message = "url must not be null")
    @ApiModelProperty(value = "菜单URL")
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     *  1：菜单   2：按钮
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
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer delFlag;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否被选中")
    private Boolean check;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}