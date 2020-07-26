package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.vo.TeachersVo;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IProjectManagementService;
import com.dingxin.web.service.ITeachersService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/teachers")
@Api(value = "讲师管理接口")
public class TeachersController {


    @Autowired
    private ITeachersService teachersService;
    @Autowired
    private ICurriculumService curriculumService;
    @Autowired
    private IProjectManagementService projectManagementService;

    /**
     * 讲师管理列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<TeachersVo>> list(@RequestBody BaseQuery<TeachersVo> query) {
        //查询列表数据
        Page<Teachers> page = new Page(query.getCurrentPage(), query.getPageSize());
        IPage pageList = null;
        if (null != query.getData()) {
            QueryWrapper<Teachers> getLikeQuery = Wrappers.query();
            pageList = teachersService.page(page, getLikeQuery.like("XM", query.getData().getQueryStr()));
        } else {
            pageList = teachersService.page(page, Wrappers.query(query.getData()));
        }
        List<Teachers> teacherList = pageList.getRecords();
        if (CollectionUtils.isEmpty(teacherList)) {
            return BaseResult.success();
        }
        ArrayList<TeachersVo> resultVo = new ArrayList<>();
        for (Teachers teacher : teacherList) {
            TeachersVo teachersVo = new TeachersVo();
            QueryWrapper<Curriculum> curricumQuery = Wrappers.query();
            List<Curriculum> curriculumList = curriculumService.list(curricumQuery.eq("teacherId", teacher.getJg0101id()));
            ArrayList<String> curriculumNameList = new ArrayList<>();
            ArrayList<String> projectNameList = new ArrayList<>();
            for (Curriculum curriculum : curriculumList) {
                curriculumNameList.add(curriculum.getCurriculumName());
            }
            QueryWrapper<ProjectManagement> projectQuery = Wrappers.query();
            List<ProjectManagement> projectList = projectManagementService.list(projectQuery.eq("lecturerId", teacher.getJg0101id()));
            for (ProjectManagement projectManagement : projectList) {
                projectNameList.add(projectManagement.getProjectName());
            }
            teachersVo.setCourseList(curriculumNameList);
            teachersVo.setProjectList(projectNameList);
            teachersVo.setBzlbm(teacher.getBzlbm());
            teachersVo.setCjgzny(teacher.getCjgzny());
            teachersVo.setCjny(teacher.getCjny());
            teachersVo.setCsdm(teacher.getCsdm());
            teachersVo.setCsrq(teacher.getCsrq());
            teachersVo.setCym(teacher.getCym());
            teachersVo.setDqztm(teacher.getDqztm());
            teachersVo.setDwh(teacher.getDwh());
            teachersVo.setEnable(teacher.getEnable());
            teachersVo.setGjdqm(teacher.getGjdqm());
            teachersVo.setJg(teacher.getJg());
            teachersVo.setJg0101id(teacher.getJg0101id());
            teachersVo.setJgh(teacher.getJgh());
            teachersVo.setJzglbm(teacher.getJzglbm());
            teachersVo.setLxrq(teacher.getLxrq());
            teachersVo.setMzm(teacher.getMzm());
            teachersVo.setSfzjh(teacher.getSfzjh());
            teachersVo.setSfzjlxm(teacher.getSfzjlxm());
            teachersVo.setSfzjyxq(teacher.getSfzjyxq());
            teachersVo.setWhcdm(teacher.getWhcdm());
            teachersVo.setXbm(teacher.getXbm());
            teachersVo.setXm(teacher.getXm());
            teachersVo.setXmpy(teacher.getXmpy());
            resultVo.add(teachersVo);
        }
        Page<TeachersVo> voPage = new Page<>();
        voPage.setRecords(resultVo);
        voPage.setPages(pageList.getPages());
        voPage.setTotal(pageList.getTotal());
        voPage.setSize(pageList.getSize());
        voPage.setCurrent(pageList.getCurrent());
        return BaseResult.success(voPage);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<Teachers> search(@RequestBody Teachers teachers) {
        Teachers result = teachersService.getOne(Wrappers.query(teachers));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody Teachers teachers) {
        boolean retFlag = teachersService.save(teachers);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody Teachers teachers) {
        boolean retFlag = teachersService.updateById(teachers);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody Teachers teachers) {
        boolean retFlag = teachersService.remove(Wrappers.query(teachers));
        return BaseResult.success(retFlag);
    }
}