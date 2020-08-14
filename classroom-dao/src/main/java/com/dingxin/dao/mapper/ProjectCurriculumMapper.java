package com.dingxin.dao.mapper;

import com.dingxin.pojo.po.ProjectCurriculum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingxin.pojo.vo.ProjectCurrilumVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *  Mapper 接口
 */
@Mapper
public interface ProjectCurriculumMapper extends BaseMapper<ProjectCurriculum> {

    @Select({"<script>",
                    "select project_id,sum(watch_amount) watch_amount,count(curriculum_id) courseNum  from ccr_project_curriculum where project_id in",
                    "<foreach collection=\"ids\" item=\"projectId\" index=\"index\" open=\"(\" separator=\",\" close=\")\">",
                         "#{projectId}",
                     "</foreach>",
                    "GROUP BY project_id",
            "</script>"
    })
    List<ProjectCurrilumVo> watchAmoutByIds(@Param("ids") List<Integer> projectIdList);

    @Select({"<script>",
            "select project_id,sum(watch_amount) watch_amount ,count(curriculum_id) courseNum from ccr_project_curriculum where del_flag = 0 and enable = 0  and project_id in",
            "<foreach collection=\"ids\" item=\"projectId\" index=\"index\" open=\"(\" separator=\",\" close=\")\">",
            "#{projectId}",
            "</foreach>",
            "GROUP BY project_id",
            "</script>"
    })
    List<ProjectCurrilumVo> PCwatchAmoutByIds(@Param("ids") List<Integer> projectIdList);
}