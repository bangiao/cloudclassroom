package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.po.Student;
import com.dingxin.pojo.request.*;
import com.dingxin.pojo.vo.StduentClassSeeRecordVo;
import com.dingxin.pojo.vo.StudentClassListVo;
import com.dingxin.pojo.vo.StudentRecordListVo;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 学生记录表
 */
@ManTag
@UserTag
@RestController
@RequestMapping("/stduentClassSeeRecord")
@Api(tags = "学生记录表接口")
public class StduentClassSeeRecordController {


    @Autowired
    private IStduentClassSeeRecordService stduentClassSeeRecordService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取学生记录表列表")
    public BaseResult<Page<StduentClassSeeRecordVo>> list(@RequestBody CommIdQueryListRequest query) {
        IPage<StduentClassSeeRecord> pageList = stduentClassSeeRecordService.queryPage(query);
        return BaseResult.success(StduentClassSeeRecordVo.convertToVoWithPage(pageList));
    }

    /**
     * 列表查询
     */
    @PostMapping("/exportExcel")
    @ApiOperation(value = "导出学生记录")
    public void exportExcel(@RequestBody(required = false) List<Integer> ids, HttpServletResponse response) throws IOException {
        stduentClassSeeRecordService.exportExcel(ids, response);
    }

    /**
     * 列表查询
     */
    @PostMapping("/selfList")
    @ApiOperation(value = "自己获取自己的记录列表")
    public BaseResult<Page<StduentClassSeeRecordVo>> selfList(@RequestBody CommQueryListRequest query) {
        IPage<StduentClassSeeRecord> pageList = stduentClassSeeRecordService.selfList(query);
        return BaseResult.success(StduentClassSeeRecordVo.convertToVoWithPage(pageList));
    }

    /**
     * 信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取学生记录表详情信息")
    public BaseResult<StduentClassSeeRecordVo> info(@RequestBody IdRequest id) {
        StduentClassSeeRecord result = stduentClassSeeRecordService.getOneSelf(id);
        return BaseResult.success(StduentClassSeeRecordVo.convent(result));
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增学生记录表信息")
    public BaseResult save(@Validated @RequestBody StduentClassSeeRecordInsertRequest stduentClassSeeRecord) {
        StduentClassSeeRecord convent = StduentClassSeeRecordInsertRequest.convent(stduentClassSeeRecord);
        boolean retFlag = stduentClassSeeRecordService.saveOrUpdateRecord(convent);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除学生记录表信息")
    public BaseResult delete(@RequestBody IdRequest id) {
        boolean retFlag = stduentClassSeeRecordService.delete(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除学生记录表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        boolean retFlag = stduentClassSeeRecordService.deleteBatch(list);
        return BaseResult.success(retFlag);
    }

    /**
     * 学生信息列表查询
     */
    @PostMapping("/studentList")
    @ApiOperation(value = "管理端 学生学习情况 学生列表")
    public BaseResult<Page<Student>> studentList(@RequestBody StudentStudyStudentListRequest query) {
        IPage<StudentRecordListVo> studentRecordListVoIPage = stduentClassSeeRecordService.studentList(query);
        return BaseResult.success(studentRecordListVoIPage);
    }

    /**
     * 学习课程列表
     */
    @PostMapping("/courseList")
    @ApiOperation(value = "学习课程列表")
    public BaseResult<Page<StudentClassListVo>> courseList(@Validated @RequestBody StudentStudyCaseListRequest query) {
        List<Map<String, Object>> maps = stduentClassSeeRecordService.queryCoursePageList(query);

        return BaseResult.success(StudentClassListVo.convertToVoWithPage(maps));
    }

}