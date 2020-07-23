package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.dao.mapper.ProjectManagementMapper;
import com.dingxin.web.service.IProjectManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class ProjectManagementServiceImpl extends ServiceImpl<ProjectManagementMapper, ProjectManagement> implements IProjectManagementService {

    @Autowired
    private ProjectManagementMapper projectManagementMapper;


    @Override
    public List<ProjectManagement> like(ProjectManagement data) {
        LambdaQueryWrapper<ProjectManagement> query = Wrappers.<ProjectManagement>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    ProjectManagement::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getProjectName()),
                    ProjectManagement::getProjectName,
                    data.getProjectName())
                .like(
                    Objects.nonNull(data.getLecturerId()),
                    ProjectManagement::getLecturerId,
                    data.getLecturerId())
                .like(
                    Objects.nonNull(data.getProjectDescription()),
                    ProjectManagement::getProjectDescription,
                    data.getProjectDescription())
                .like(
                    Objects.nonNull(data.getCourseNum()),
                    ProjectManagement::getCourseNum,
                    data.getCourseNum())
                .like(
                    Objects.nonNull(data.getWatchNum()),
                    ProjectManagement::getWatchNum,
                    data.getWatchNum())
                .like(
                    Objects.nonNull(data.getEnable()),
                    ProjectManagement::getEnable,
                    data.getEnable())
                .like(
                    Objects.nonNull(data.getAuditStatus()),
                    ProjectManagement::getAuditStatus,
                    data.getAuditStatus())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    ProjectManagement::getDelFlag,
                    data.getDelFlag())
                .like(
                    Objects.nonNull(data.getCourseId()),
                    ProjectManagement::getCourseId,
                    data.getCourseId())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    ProjectManagement::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    ProjectManagement::getModifyTime,
                    data.getModifyTime())
;
        return projectManagementMapper.selectList(query);


    }

}
