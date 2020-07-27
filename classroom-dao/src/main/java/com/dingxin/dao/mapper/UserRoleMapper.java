package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户与角色对应关系 Mapper 接口
 */
@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

}