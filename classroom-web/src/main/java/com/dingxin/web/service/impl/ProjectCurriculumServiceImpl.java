package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.dao.mapper.ProjectCurriculumMapper;
import com.dingxin.web.service.IProjectCurriculumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class ProjectCurriculumServiceImpl extends ServiceImpl<ProjectCurriculumMapper, ProjectCurriculum> implements IProjectCurriculumService {

    @Autowired
    private ProjectCurriculumMapper projectCurriculumMapper;


    @Override
    public List<ProjectCurriculum> like(ProjectCurriculum data) {
        LambdaQueryWrapper<ProjectCurriculum> query = Wrappers.<ProjectCurriculum>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    ProjectCurriculum::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getProjectId()),
                    ProjectCurriculum::getProjectId,
                    data.getProjectId())
                .like(
                    Objects.nonNull(data.getCurriculumId()),
                    ProjectCurriculum::getCurriculumId,
                    data.getCurriculumId())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    ProjectCurriculum::getDelFlag,
                    data.getDelFlag())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    ProjectCurriculum::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    ProjectCurriculum::getModifyTime,
                    data.getModifyTime())
;
        return projectCurriculumMapper.selectList(query);


    }

}
