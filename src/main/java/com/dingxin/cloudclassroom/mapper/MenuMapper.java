package com.dingxin.cloudclassroom.mapper;

import com.dingxin.cloudclassroom.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 菜单管理 Mapper 接口
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    Set<String> getCasUserPermissions(String id);
}