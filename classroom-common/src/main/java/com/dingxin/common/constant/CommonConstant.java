package com.dingxin.common.constant;


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
	 * 删除状态-删除
	 */
	Integer DEL_FLAG_TRUE = 1;
	/**
	 * 已禁用
	 */
	Integer DISABLE_TRUE = 1;
	/**
	 * 未禁用
	 */
	Integer DISABLE_FALSE = 0;

	/**
	 * localDateTime转long 参数为空时 默认值
	 */
	Long LONG_DEFAULT = -1L;

}