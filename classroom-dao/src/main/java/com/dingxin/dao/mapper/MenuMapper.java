package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 菜单管理 Mapper 接口
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

}