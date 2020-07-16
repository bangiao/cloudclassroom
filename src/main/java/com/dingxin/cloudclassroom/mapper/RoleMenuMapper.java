package com.dingxin.cloudclassroom.mapper;

import com.dingxin.cloudclassroom.entity.RoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色与菜单对应关系 Mapper 接口
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}