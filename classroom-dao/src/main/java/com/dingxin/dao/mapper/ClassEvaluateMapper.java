package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.ClassEvaluate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 课程评价表 Mapper 接口
 */
@Mapper
@Repository
public interface ClassEvaluateMapper extends BaseMapper<ClassEvaluate> {

}