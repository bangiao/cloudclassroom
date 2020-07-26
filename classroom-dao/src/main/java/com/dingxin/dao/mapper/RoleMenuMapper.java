package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色与菜单对应关系 Mapper 接口
 */
@Mapper
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}