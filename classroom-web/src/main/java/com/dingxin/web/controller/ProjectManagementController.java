package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ProjectManagementVo;
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
@UserTag
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
        return BaseResult.success(projectManagementService.queryPage(query));
    }

    /**
     * 列表查询
     */
    @PostMapping("/pc/list")
    @ApiOperation(value = "获取pc专题管理列表,关键字查询列表")
    public BaseResult<Page<ProjectManagement>> PCList(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        return BaseResult.success(projectManagementService.queryPCPage(query));
    }
    /**
     * 列表查询
     */
    @PostMapping("/pc/listByCount")
    @ApiOperation(value = "pc根据观看次数获取列表")
    public BaseResult<Page<ProjectManagement>> PCListByCount(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        return BaseResult.success(projectManagementService.queryPCPageByCount(query));
    }
    /**
     * pc专题名称列表查询
     */
    @PostMapping("/pc/searchProjectNameList")
    @ApiOperation(value = "获取pc专题管理专题名称列表")
    public BaseResult<Page<ProjectManagementVo>> searchProjectNameList(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        return BaseResult.success(projectManagementService.searchProjectNameList(query));
    }
    /**
     * pc专题名称列表查询
     */
    @PostMapping("/pc/searchByProjectName")
    @ApiOperation(value = "pc专题名称列表查询")
    public BaseResult<Page<ProjectManagementVo>> searchByProjectName(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        return BaseResult.success(projectManagementService.searchByProjectName(query));
    }
    /**
     * 单个查询
     */
    @PostMapping("/searchById")
    @ApiOperation(value = "获取讲师详情信息")
    public BaseResult<ProjectManagement> search(@RequestBody IdRequest idRequest) {
        return BaseResult.success(projectManagementService.searchOneById(idRequest));
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增专题管理信息")
    public BaseResult save(@Validated @RequestBody ProjectManagement projectManagement) {
        return projectManagementService.insertOne(projectManagement);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改专题信息")
    public BaseResult update(@RequestBody @Validated ProjectManagement projectManagement) {
        return BaseResult.success(projectManagementService.updateProject(projectManagement));
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除专题信息")
    public BaseResult delete(@RequestBody List<Integer> idList) {
        return projectManagementService.logicDeleteByIds(idList);
    }
}