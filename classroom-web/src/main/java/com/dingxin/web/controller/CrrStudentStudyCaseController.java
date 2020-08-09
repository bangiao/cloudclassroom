package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.CrrStudentStudyCase;
import com.dingxin.pojo.po.Student;
import com.dingxin.pojo.request.StudentStudyCaseListRequest;
import com.dingxin.web.service.ICrrStudentStudyCaseService;
import com.dingxin.web.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生学习情况
 */
@ManTag
@RestController
@RequestMapping("/crrStudentStudyCase")
@Api(tags = {"学生学习情况接口"})
public class CrrStudentStudyCaseController {


    @Autowired
    private ICrrStudentStudyCaseService crrStudentStudyCaseService;
    @Autowired
    private IStudentService studentService;



    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<CrrStudentStudyCase> search(@RequestBody CrrStudentStudyCase crrStudentStudyCase) {
        CrrStudentStudyCase result = crrStudentStudyCaseService.getOne(Wrappers.query(crrStudentStudyCase));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody CrrStudentStudyCase crrStudentStudyCase) {
        boolean retFlag = crrStudentStudyCaseService.save(crrStudentStudyCase);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody CrrStudentStudyCase crrStudentStudyCase) {
        boolean retFlag = crrStudentStudyCaseService.updateById(crrStudentStudyCase);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody CrrStudentStudyCase crrStudentStudyCase) {
        LambdaUpdateWrapper<CrrStudentStudyCase> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CrrStudentStudyCase::getId, crrStudentStudyCase.getId());
        wrapper.set(CrrStudentStudyCase::getDelFlag, CommonConstant.DEL_FLAG_TRUE);
        boolean retFlag = crrStudentStudyCaseService.update(wrapper);
        return BaseResult.success(retFlag);
    }

    /**
     * 学习课程列表
     */
    @PostMapping("/courseList")
    @ApiOperation(value = "学习课程列表")
    public BaseResult<Page<Student>> courseList(@RequestBody StudentStudyCaseListRequest query) {
        IPage pageList = crrStudentStudyCaseService.queryCoursePageList(query);
        return BaseResult.success(pageList);
    }
}