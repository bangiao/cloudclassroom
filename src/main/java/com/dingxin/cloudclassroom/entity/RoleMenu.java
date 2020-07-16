package com.dingxin.cloudclassroom.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 角色与菜单对应关系 实体类
 */
@TableName("sys_role_menu")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * 角色ID
     */
	@TableField("role_id")
	@ApiModelProperty(value = "角色ID")
	private Integer roleId;
    /**
     * 菜单ID
     */
	@TableField("menu_id")
	@ApiModelProperty(value = "菜单ID")
	private String menuId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
