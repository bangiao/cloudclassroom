package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.pojo.po.CasEmploys;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 *  实体类
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmploysRoleVo extends Model<EmploysRoleVo> {

	private static final long serialVersionUID = 1L;

	/**
	 * 工号/学号
	 */
	private String sid;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;
	/**
	 * 电话号码
	 */
	@ApiModelProperty(value = "电话号码")
	private String phoneNum;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "职位")
	private String zw;
	/**
	 * 是否被选中
	 */
	@ApiModelProperty(value = "是否被选中")
	private boolean check=false;

	public static EmploysRoleVo convent(CasEmploys source){
		return EmploysRoleVo.builder().zw(source.getZw()).email(source.getEmail())
				.phoneNum(source.getPhoneNum())
				.name(source.getName())
				.sid(source.getSid()).build();
	}

	public static List<EmploysRoleVo> listConvent(List<CasEmploys> source){
		if (CollectionUtils.isEmpty(source)){
			throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
		}
		ArrayList<EmploysRoleVo> var = Lists.newArrayList();
		for (CasEmploys casEmploys : source) {
			var.add(EmploysRoleVo.convent(casEmploys));
		}
		return var;
	}

}
