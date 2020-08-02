package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	 * 职位
	 */
	@TableField("zw")
	@ApiModelProperty(value = "职位")
	private String zw;
	/**
	 * 用户组
	 */
	@TableField("Group")
	@ApiModelProperty(value = "用户组")
	private String group;

	@TableField(exist = false)
	private String token;


	@Override
	protected Serializable pkVal() {
		return this.sid;
	}

}
