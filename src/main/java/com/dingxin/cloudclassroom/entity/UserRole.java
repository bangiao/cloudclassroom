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
 * 用户与角色对应关系 实体类
 */
@TableName("sys_user_role")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends Model<UserRole> {

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
     * CAS用户ID
     */
	@TableField("cas_user_id")
	@ApiModelProperty(value = "CAS用户ID")
	private String casUserId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
