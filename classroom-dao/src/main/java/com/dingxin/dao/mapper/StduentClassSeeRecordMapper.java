package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 学生记录表 Mapper 接口
 */
@Mapper
@Repository
public interface StduentClassSeeRecordMapper extends BaseMapper<StduentClassSeeRecord> {

}