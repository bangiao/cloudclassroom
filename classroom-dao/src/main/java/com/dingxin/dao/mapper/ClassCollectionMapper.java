package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.ClassCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 课程收藏表 Mapper 接口
 */
@Mapper
@Repository
public interface ClassCollectionMapper extends BaseMapper<ClassCollection> {

}