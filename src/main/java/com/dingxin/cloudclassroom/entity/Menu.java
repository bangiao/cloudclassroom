package com.dingxin.cloudclassroom.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 菜单管理 实体类
 */
@TableName("sys_menu")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 父菜单ID，一级菜单为0
     */
	@TableField("parent_id")
	@ApiModelProperty(value = "父菜单ID，一级菜单为0")
	private String parentId;
    /**
     * 菜单名称
     */
	private String name;
    /**
     * 菜单URL
     */
	private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
	private String perms;
    /**
     *  1：菜单   2：按钮
     */
	private Integer type;
    /**
     * 菜单图标
     */
	private String icon;
    /**
     * 排序
     */
	@TableField("order_num")
	@ApiModelProperty(value = "排序")
	private Integer orderNum;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
