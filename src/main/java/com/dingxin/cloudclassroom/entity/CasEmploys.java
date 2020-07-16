package com.dingxin.cloudclassroom.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("nkd_cas_employs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CasEmploys extends Model<CasEmploys> {

	private static final long serialVersionUID = 1L;

	/**
	 * 工号/学号
	 */
	@TableId(type = IdType.AUTO)
	private String sid;
	/**
	 * cas id
	 */
	@TableField("CASID")
	@ApiModelProperty(value = "cas id")
	private String casid;
	/**
	 * 是否禁用
	 */
	@TableField("DISABLE")
	@ApiModelProperty(value = "是否禁用")
	private Integer disable;
	/**
	 * 部门
	 */
	@TableField("DEPTS")
	@ApiModelProperty(value = "部门")
	private String depts;
	/**
	 * 姓名
	 */
	@TableField("NAME")
	@ApiModelProperty(value = "姓名")
	private String name;
	/**
	 * 电话号码
	 */
	@TableField("phonenum")
	@ApiModelProperty(value = "电话号码")
	private String phoneNum;
	/**
	 * 邮箱
	 */
	@TableField("email")
	@ApiModelProperty(value = "邮箱")
	private String email;
	/**
	 * 用户组
	 */
	private String group;

	@TableField(exist = false)
	private String token;


	@Override
	protected Serializable pkVal() {
		return this.sid;
	}

}
