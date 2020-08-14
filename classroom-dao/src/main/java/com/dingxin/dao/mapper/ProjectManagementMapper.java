package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.ProjectManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 *  Mapper 接口
 */
@Mapper
public interface ProjectManagementMapper extends BaseMapper<ProjectManagement> {
    @Update("update ccr_project_management set project_watch_num = project_watch_num+1 where id = #{id}")
    void watchAmout(@Param("id") Integer id);
}