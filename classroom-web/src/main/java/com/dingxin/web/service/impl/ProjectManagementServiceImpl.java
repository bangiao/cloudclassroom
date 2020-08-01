package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.dao.mapper.ProjectManagementMapper;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ProjectManagementVo;
import com.dingxin.web.service.IProjectCurriculumService;
import com.dingxin.web.service.IProjectManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.web.service.ITeachersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 服务接口实现类
 */
@Service
@Transactional
public class ProjectManagementServiceImpl extends ServiceImpl<ProjectManagementMapper, ProjectManagement> implements IProjectManagementService {


    @Autowired
    private ITeachersService teachersService;
    @Autowired
    private IProjectCurriculumService projectCurriculumService;


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
//                .like(
//                        Objects.nonNull(data.getCourseId()),
//                        ProjectManagement::getCourseId,
//                        data.getCourseId())
                .like(
                        Objects.nonNull(data.getCreateTime()),
                        ProjectManagement::getCreateTime,
                        data.getCreateTime())
                .like(
                        Objects.nonNull(data.getModifyTime()),
                        ProjectManagement::getModifyTime,
                        data.getModifyTime());
        return this.baseMapper.selectList(query);


    }

    /**
     * 管理端列表查询
     *
     * @param query
     * @return
     */
    @Override
    public IPage<ProjectManagement> queryPage(CommQueryListRequest query) {
        Page<ProjectManagement> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ProjectManagement> projectQw = new LambdaQueryWrapper<>();
        projectQw.eq(ProjectManagement::getDelFlag, CommonConstant.DEL_FLAG).and(StringUtils.isNotBlank(query.getQueryStr()), Wrappers -> Wrappers
                .like(
                        ProjectManagement::getProjectName,
                        query.getQueryStr())
                .or().like(
                        ProjectManagement::getLecturerName,
                        query.getQueryStr()));

        return this.baseMapper.selectPage(page, projectQw);
    }

    /**
     * pc列表查询
     *
     * @param query
     * @return
     */
    @Override
    public IPage<ProjectManagement> queryPCPage(CommQueryListRequest query) {
        Page<ProjectManagement> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ProjectManagement> qw = Wrappers.lambdaQuery();
        IPage<ProjectManagement> pageList = this.baseMapper.selectPage(page, qw
                .like(
                        StringUtils.isNotBlank(query.getQueryStr()),
                        ProjectManagement::getProjectName,
                        query.getQueryStr())
                .eq(
                        ProjectManagement::getDelFlag,
                        CommonConstant.DEL_FLAG)
                .eq(
                        ProjectManagement::getEnable,
                        CommonConstant.DISABLE_FALSE)
                .eq(
                        ProjectManagement::getAuditStatus,
                        CommonConstant.STATUS_AUDIT));
        return pageList;
    }

    /**
     * 删除
     *
     * @param idList
     * @return
     */
    @Override
    public boolean deleteByIds(List<Integer> idList) {
        LambdaUpdateWrapper<ProjectManagement> update = Wrappers.lambdaUpdate();
        update.set(ProjectManagement::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(ProjectManagement::getId, idList);
        return update(update);
    }

    /**
     * 单个查询
     *
     * @param idRequest
     * @return
     */
    @Override
    public ProjectManagement searchOneById(IdRequest idRequest) {
        LambdaQueryWrapper<ProjectManagement> qw = Wrappers.lambdaQuery();
        qw.eq(ProjectManagement::getId, idRequest.getId()).eq(ProjectManagement::getDelFlag, CommonConstant.DEL_FLAG);
        return getOne(qw);
    }

    /**
     * pc依靠次数列表查询
     *
     * @param query
     * @return
     */
    @Override
    public IPage<ProjectManagement> queryPCPageByCount(CommQueryListRequest query) {
        Page<ProjectManagement> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ProjectManagement> qw = Wrappers.lambdaQuery();
        qw.orderByDesc(ProjectManagement::getWatchNum);
        return page(page, qw);
    }

    /**
     * 查询pc专题名称列表
     *
     * @param query
     * @return
     */
    @Override
    public IPage<ProjectManagementVo> searchProjectNameList(CommQueryListRequest query) {
        Page<ProjectManagement> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ProjectManagement> qw = Wrappers.lambdaQuery();
        qw
                .groupBy(ProjectManagement::getProjectName)
                .select(ProjectManagement::getProjectName)
                .eq(ProjectManagement::getDelFlag, CommonConstant.DEL_FLAG)
                .eq(ProjectManagement::getEnable, CommonConstant.DISABLE_FALSE);
        return ProjectManagementVo.convertToVoWithPage(page(page, qw));
    }

    /**
     * 根据专题名称查询列表
     *
     * @param query
     * @return
     */
    @Override
    public IPage<ProjectManagement> searchByProjectName(CommQueryListRequest query) {
        Page<ProjectManagement> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ProjectManagement> qw = Wrappers.lambdaQuery();
        qw.eq(
                StringUtils.isNotBlank(query.getQueryStr()),
                ProjectManagement::getProjectName,
                query.getQueryStr())
                .eq(
                        ProjectManagement::getEnable,
                        CommonConstant.DISABLE_FALSE)
                .eq(
                        ProjectManagement::getDelFlag,
                        CommonConstant.DEL_FLAG);
        return page(page, qw);
    }

    @Override
    public BaseResult insertOne(ProjectManagement projectManagement) {
        LocalDateTime now = LocalDateTime.now();
        projectManagement.setModifyTime(now);
        projectManagement.setWatchNum(0);
        projectManagement.setLecturerName(teachersService.getById(projectManagement.getLecturerId()).getXm());
        List<Integer> courseIds = projectManagement.getCourseIds();
        if (CollectionUtils.isNotEmpty(courseIds)) {
            projectManagement.setCourseNum(courseIds.size());
            this.save(projectManagement);
            // 保存专题课程中间表
            List<ProjectCurriculum> projectCurriculums = courseIds.stream().map(c -> {
                ProjectCurriculum projectCurriculum = new ProjectCurriculum();
                projectCurriculum.setCreateTime(now);
                projectCurriculum.setCurriculumId(c);
                projectCurriculum.setDelFlag(false);
                projectCurriculum.setModifyTime(now);
                projectCurriculum.setProjectId(projectManagement.getId());
                return projectCurriculum;
            }).collect(Collectors.toList());
            projectCurriculumService.saveBatch(projectCurriculums);
        } else {
            projectManagement.setCourseNum(0);
            this.save(projectManagement);
        }
        return BaseResult.success().setMsg("新增专题成功");
    }


}
