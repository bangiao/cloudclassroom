package com.dingxin.cloudclassroom.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 角色 实体类
 */
@TableName("sys_role")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * 角色名称
     */
	@TableField("role_name")
	@ApiModelProperty(value = "角色名称")
	private String roleName;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建者ID
     */
	@TableField("create_user_id")
	@ApiModelProperty(value = "创建者ID")
	private Integer createUserId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
    /**
     * 数据字典id
     */
	@TableField("dic_id")
	@ApiModelProperty(value = "数据字典id")
	private Integer dicId;
    /**
     * 排序
     */
	private Integer sort;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
