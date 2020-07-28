package com.dingxin.common.constant;

import com.google.common.collect.Lists;

import java.util.List;

public interface CommonConstant {
	/**
	 * 审核状态-未通过
	 */
	Integer STATUS_UNAPPROVE = -1;
	/**
	 * 审核状态-待审核
	 */
	Integer STATUS_NOAUDIT = 0;
	/**
	 * 审核状态-已审核
	 */
	Integer STATUS_AUDIT = 1;
	/**
	 * 删除状态-未删除
	 */
	Integer DEL_FLAG = 0;
	/**
	 * 删除状态-已删除
	 */
	Integer NOT_DEL_FLAG = 1;

	List<Integer> LIST = Lists.newArrayList(0,-1);
	/**
	 * localDateTime转long 参数为空时 默认值
	 */
	Long LONG_DEFAULT = -1L;
}