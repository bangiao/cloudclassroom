package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IProjectManagementService;
import com.dingxin.web.service.ITeachersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
@ManTag
@RestController
@RequestMapping("/projectManagement")
@Api(tags = "专题管理接口")
public class ProjectManagementController {


    @Autowired
    private IProjectManagementService projectManagementService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取专题管理列表,关键字查询列表")
    public BaseResult<Page<ProjectManagement>> list(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        Page<ProjectManagement> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ProjectManagement> projectQw = new LambdaQueryWrapper<>();
        projectQw.eq(ProjectManagement::getDelFlag, CommonConstant.DEL_FLAG).and(Wrappers -> Wrappers
                .like(
                        ProjectManagement::getProjectName,
                        query.getQueryStr())
                .or().like(
                        ProjectManagement::getLecturerName,
                        query.getQueryStr()));
        IPage<ProjectManagement> pageList = projectManagementService.page(page, projectQw);
        return BaseResult.success(pageList);
    }

    /**
     * 列表查询
     */
    @PostMapping("/pc/list")
    @ApiOperation(value = "获取专题管理列表,关键字查询列表")
    public BaseResult<Page<ProjectManagement>> PCList(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        Page<ProjectManagement> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ProjectManagement> qw = Wrappers.lambdaQuery();
        IPage<ProjectManagement> pageList = projectManagementService.page(page, qw
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
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/searchById")
    @ApiOperation(value = "获取讲师详情信息")
    public BaseResult<Teachers> search(@RequestBody IdRequest idRequest) {
        ProjectManagement result = projectManagementService.getById(idRequest.getId());
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增专题管理信息")
    public BaseResult save(@Validated @RequestBody ProjectManagement projectManagement) {
        projectManagement.setCreateTime(LocalDateTime.now());
        projectManagement.setModifyTime(LocalDateTime.now());
        String courseId = projectManagement.getCourseId();
        if (StringUtils.isNotEmpty(courseId)) {
            String[] courseIdList = courseId.split(",");
            projectManagement.setCourseNum(courseIdList.length);
        }
        boolean retFlag = projectManagementService.save(projectManagement);
        return BaseResult.success(retFlag).setMsg("新增专题成功");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改专题信息")
    public BaseResult update(@RequestBody ProjectManagement projectManagement) {
        projectManagement.setModifyTime(LocalDateTime.now());
        boolean retFlag = projectManagementService.updateById(projectManagement);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除专题信息")
    public BaseResult delete(@RequestBody List<Integer> idList) {
        LambdaUpdateWrapper<ProjectManagement> update = Wrappers.lambdaUpdate();
        update.set(ProjectManagement::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(ProjectManagement::getId, idList);
        boolean retFlag = projectManagementService.update(update);
        return BaseResult.success(retFlag).setMsg("删除成功");
    }
}