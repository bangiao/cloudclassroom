package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色 Mapper 接口
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

}