package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.CrrStudentStudyCase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingxin.pojo.request.StudentStudyCaseListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *  Mapper 学生学习情况接口
 */
@Mapper
public interface CrrStudentStudyCaseMapper extends BaseMapper<CrrStudentStudyCase> {

}