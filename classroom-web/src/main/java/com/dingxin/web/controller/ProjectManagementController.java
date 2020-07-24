package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.vo.ProjectManagementVo;
import com.dingxin.web.service.IProjectManagementService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.web.service.ITeachersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
@RestController
@RequestMapping("/projectManagement")
@Api(value = "专题管理接口")
public class ProjectManagementController {


    @Autowired
    private IProjectManagementService projectManagementService;
    @Autowired
    private ITeachersService teachersService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<ProjectManagement>>list(@RequestBody BaseQuery<ProjectManagement> query){
        //查询列表数据
        Page<ProjectManagement> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage<ProjectManagement> pageList = projectManagementService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        ArrayList<ProjectManagementVo> resultVo = new ArrayList<>();
        List<ProjectManagement> records = pageList.getRecords();
        for (ProjectManagement projectManagement: records ) {
            ProjectManagementVo projectManagementVo = new ProjectManagementVo();
            Integer lecturerId = projectManagement.getLecturerId();
            QueryWrapper<Teachers> teacherQuery = Wrappers.query();
            Teachers teachers = teachersService.getOne(teacherQuery.eq("JG0101ID", lecturerId));
            projectManagementVo.setId(projectManagement.getId());
            projectManagementVo.setLecturerName(teachers.getXm());
            projectManagementVo.setModifyTime(projectManagement.getModifyTime());
            projectManagementVo.setCreateTime(projectManagement.getCreateTime());
            projectManagementVo.setAuditStatus(projectManagement.getAuditStatus());
            projectManagementVo.setCourseId(projectManagement.getCourseId());
            projectManagementVo.setCourseNum(projectManagement.getCourseNum());
            projectManagementVo.setDelFlag(projectManagement.getDelFlag());
            projectManagementVo.setEnable(projectManagement.getEnable());
            projectManagementVo.setLecturerId(projectManagement.getLecturerId());
            projectManagementVo.setProjectDescription(projectManagement.getProjectDescription());
            projectManagementVo.setProjectName(projectManagement.getProjectName());
            projectManagementVo.setWatchNum(projectManagement.getWatchNum());
            resultVo.add(projectManagementVo);
        }
        Page<ProjectManagementVo> voPage = new Page<>();
        voPage.setRecords(resultVo);
        voPage.setSize(pageList.getSize());
        voPage.setTotal(pageList.getTotal());
        voPage.setPages(pageList.getPages());
        return BaseResult.success(voPage);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取专题详情信息")
    public BaseResult<ProjectManagement> search(@RequestBody  ProjectManagement projectManagement){
        ProjectManagement result = projectManagementService.getOne(Wrappers.query(projectManagement));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增专题管理信息")
    public BaseResult save( @Validated @RequestBody ProjectManagement projectManagement){
        projectManagement.setCreateTime(LocalDateTime.now());
        projectManagement.setModifyTime(LocalDateTime.now());
        boolean retFlag= projectManagementService.save(projectManagement);
        return BaseResult.success(retFlag).setMsg("新增专题成功");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改专题信息")
    public BaseResult update(@RequestBody ProjectManagement projectManagement){
        projectManagement.setModifyTime(LocalDateTime.now());
        boolean retFlag= projectManagementService.updateById(projectManagement);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除专题信息")
    public BaseResult delete(@RequestBody List<Integer> idList){
        boolean retFlag = projectManagementService.removeByIds(idList);
        return BaseResult.success(retFlag).setMsg("删除成功");
    }
}