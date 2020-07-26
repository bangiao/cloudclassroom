package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingxin.common.annotation.ManTag;
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
import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private ITeachersService teachersService;
    @Autowired
    private ICurriculumService curriculumService;
    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取专题管理列表")
    public BaseResult<Page<ProjectManagementVo>>list(@RequestBody BaseQuery<ProjectManagementVo> query ){
        //查询列表数据
        Page<ProjectManagement> page = new Page(query.getCurrentPage(),query.getPageSize());
        ProjectManagementVo queryData = query.getData();
        IPage<ProjectManagement> pageList = null;
        if (null != queryData){
            QueryWrapper<Teachers> teacherQuery = Wrappers.query();
            QueryWrapper<ProjectManagement> getLikeQuery = Wrappers.query();
            List<Teachers> teachersList = teachersService.list(teacherQuery.like("XM", queryData.getQueryStr()));
            if (teachersList.size()>0){
                ArrayList<String> teacherIdList = new ArrayList<>();
                for (Teachers teacher:teachersList) {
                    teacherIdList.add(teacher.getJg0101id());
                }
                pageList = projectManagementService.page(page, getLikeQuery.like("project_Name", queryData.getQueryStr()).or().in("lecturer_Id", teacherIdList).eq("del_Flag",0));
            }else {
                pageList = projectManagementService.page(page, getLikeQuery.like("project_Name", queryData.getQueryStr()).eq("del_Flag",0));
            }
        }else {
            QueryWrapper<ProjectManagement> queryAll = Wrappers.query();
            pageList = projectManagementService.page(page,queryAll.eq("del_Flag",0));
        }
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
            String courseId = projectManagement.getCourseId();
            int watchAmount = 0;
            if (StringUtils.isNotEmpty(courseId) && !"0".equalsIgnoreCase(courseId)){
                String[] courseIdList = courseId.split(",");
                for (int i = 0; i < courseIdList.length; i++) {
                    QueryWrapper<Curriculum> curriculumQuery = Wrappers.query();
                    Curriculum curriculum = curriculumService.getOne(curriculumQuery.eq("delete_flag", 0).eq("id",courseIdList[i]));
                    watchAmount+=curriculum.getWatchAmount();
                }
            }
            projectManagementVo.setWatchNum(watchAmount);
            resultVo.add(projectManagementVo);
        }
        Page<ProjectManagementVo> voPage = new Page<>();
        voPage.setRecords(resultVo);
        voPage.setSize(pageList.getSize());
        voPage.setTotal(pageList.getTotal());
        voPage.setPages(pageList.getPages());
        voPage.setCurrent(pageList.getCurrent());
        return BaseResult.success(voPage);
    }
    /**
     * 列表查询
     */
    @PostMapping("/pc/list")
    @ApiOperation(value = "获取PC专题管理列表")
    public BaseResult<Page<ProjectManagementVo>>PClist(@RequestBody BaseQuery<ProjectManagementVo> query ){
        //查询列表数据
        Page<ProjectManagement> page = new Page(query.getCurrentPage(),query.getPageSize());
        ProjectManagementVo queryData = query.getData();
        IPage<ProjectManagement> pageList = null;
        if (null != queryData){
            QueryWrapper<Teachers> teacherQuery = Wrappers.query();
            QueryWrapper<ProjectManagement> getLikeQuery = Wrappers.query();
            List<Teachers> teachersList = teachersService.list(teacherQuery.like("XM", queryData.getQueryStr()));
            if (teachersList.size()>0){
                ArrayList<String> teacherIdList = new ArrayList<>();
                for (Teachers teacher:teachersList) {
                    teacherIdList.add(teacher.getJg0101id());
                }
                pageList = projectManagementService.page(page, getLikeQuery.like("project_Name", queryData.getQueryStr()).or().in("lecturer_Id", teacherIdList).eq("del_Flag",0).eq("enable",0));
            }else {
                pageList = projectManagementService.page(page, getLikeQuery.like("project_Name", queryData.getQueryStr()).eq("del_Flag",0).eq("enable",0));
            }
        }else {
            QueryWrapper<ProjectManagement> queryAll = Wrappers.query();
            pageList = projectManagementService.page(page,queryAll.eq("del_Flag",0).eq("enable",0));
        }
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
            String courseId = projectManagement.getCourseId();
            int watchAmount = 0;
            if (StringUtils.isNotEmpty(courseId) && !"0".equalsIgnoreCase(courseId)){
                String[] courseIdList = courseId.split(",");
                for (int i = 0; i < courseIdList.length; i++) {
                    QueryWrapper<Curriculum> curriculumQuery = Wrappers.query();
                    Curriculum curriculum = curriculumService.getOne(curriculumQuery.eq("delete_flag", 0).eq("id",courseIdList[i]));
                    watchAmount+=curriculum.getWatchAmount();
                }
            }
            projectManagementVo.setWatchNum(watchAmount);
            resultVo.add(projectManagementVo);
        }
        Page<ProjectManagementVo> voPage = new Page<>();
        voPage.setRecords(resultVo);
        voPage.setSize(pageList.getSize());
        voPage.setTotal(pageList.getTotal());
        voPage.setPages(pageList.getPages());
        voPage.setCurrent(pageList.getCurrent());
        return BaseResult.success(voPage);
    }
    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取专题详情信息")
    public BaseResult<ProjectManagement> search(@RequestBody  ProjectManagement projectManagement){
        QueryWrapper<Object> query = Wrappers.query();
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
        String courseId = projectManagement.getCourseId();
        if (StringUtils.isNotEmpty(courseId)){
            String[] courseIdList = courseId.split(",");
            projectManagement.setCourseNum(courseIdList.length);
        }
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
        UpdateWrapper<ProjectManagement> update = Wrappers.update();
        update.set("del_flag",-1).in("id",idList);
        boolean retFlag = projectManagementService.update(update);
        return BaseResult.success(retFlag).setMsg("删除成功");
    }
}