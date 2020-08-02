package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生信息表 Mapper 接口
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}