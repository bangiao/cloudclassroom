package com.dingxin.common.constant;


public interface CommonConstant {
	/**
	 * 审核状态-未通过
	 */
	Integer STATUS_UNAPPROVED = -1;
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
	/**
	 * 视频每次观看自增值
	 */
	Long PER_WATCH_INCREASE = 1L;
	/**
	 * 视频观看初始值
	 */
	Long WATCH_AMOUNT_INITIAL_VALUE = 0L;

	/**
	 * 普通用户  也就是学生
	 */
	Integer NORMAL_USER=1;
	/**
	 * 老师
	 */
	Integer TEACHER=3;
	/**
	 * 管理员
	 */
	Integer ADMINISTRATOR=7;
	/**
	 * 热门返回数量
	 */
	Integer HOTSIZE=4;
	/**
	 * 热门专题
	 */
	String HOTPROJECT="热门专题";

	/**
	 * 热门课程
	 */
	String HOTCURRICULUM="热门课程";

	/**
	 * 最新课程
	 */
	String LATESTCURRICULUM="最新课程";
	/**
	 * 热门专题类型
	 */
	Integer HOTPROJECTTYPE=0;
	/**
	 * 热门课程类型
	 */
	Integer HOTCURRICULUMTYPE=1;
	/**
	 *最近课程类型
	 */
	Integer LATESTCURRICULUMTYPE=2;
}