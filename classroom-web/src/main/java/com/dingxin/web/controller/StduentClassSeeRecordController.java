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
     * 获取学生记录表列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取学生记录表列表")
    public BaseResult<Page<StduentClassSeeRecordVo>> list(@RequestBody CommIdQueryListRequest query) {
        IPage<StduentClassSeeRecord> pageList = stduentClassSeeRecordService.queryPage(query);
        return BaseResult.success(StduentClassSeeRecordVo.convertToVoWithPage(pageList));
    }

    /**
     * 导出学生记录
     */
    @PostMapping("/exportExcel")
    @ApiOperation(value = "导出学生记录")
    public void exportExcel(@RequestBody(required = false) List<Integer> ids, HttpServletResponse response) throws IOException {
        stduentClassSeeRecordService.exportExcel(ids, response);
    }

    /**
     * 自己获取自己的记录列表
     */
    @PostMapping("/selfList")
    @ApiOperation(value = "自己获取自己的记录列表")
    public BaseResult<Page<StduentClassSeeRecordVo>> selfList(@RequestBody CommQueryListRequest query) {
        IPage<StduentClassSeeRecord> pageList = stduentClassSeeRecordService.selfList(query);
        return BaseResult.success(StduentClassSeeRecordVo.convertToVoWithPage(pageList));
    }

    /**
     * 获取学生记录表详情信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取学生记录表详情信息")
    public BaseResult<StduentClassSeeRecordVo> info(@RequestBody WidRequest id) {
        IPage page= stduentClassSeeRecordService.getOneSelf(id);
        return BaseResult.success(StudentRecordListVo.convertToVoWithPage(page).getRecords().get(0));
    }

    /**
     * 新增学生记录表信息
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增学生记录表信息")
    public BaseResult save(@Validated @RequestBody StduentClassSeeRecordInsertRequest stduentClassSeeRecord) {
        StduentClassSeeRecord convent = StduentClassSeeRecordInsertRequest.convent(stduentClassSeeRecord);
        boolean retFlag = stduentClassSeeRecordService.saveOrUpdateRecord(convent);
        return BaseResult.success(retFlag);
    }

    /**
     * 管理端 学生学习情况 删除学生记录表信息
     */
    @PostMapping("/delete")
    @ApiOperation(value = "管理端 学生学习情况 删除学生记录表信息")
    public BaseResult delete(@Validated@RequestBody StudentStudyCaseListRequest id) {
        boolean retFlag = stduentClassSeeRecordService.delete(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 管理端 学生学习情况 批量删除学生记录表信息
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "管理端 学生学习情况 批量删除学生记录表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        boolean retFlag = stduentClassSeeRecordService.deleteBatch(list);
        return BaseResult.success(retFlag);
    }


    /**
     * 管理端 学生学习情况 依课程Id删除学生记录表信息
     */
    @PostMapping("/deleteForClass")
    @ApiOperation(value = "管理端 学生学习情况 依课程Id删除学生记录表信息")
    public BaseResult deleteForClass(@Validated@RequestBody StudentStudyCaseClassRequest scid) {
        boolean retFlag = stduentClassSeeRecordService.deleteForClass(scid);
        return BaseResult.success(retFlag);
    }

    /**
     * 管理端 学生学习情况 批量删除学生记录表信息
     */
    @PostMapping("/deleteForClass/batch")
    @ApiOperation(value = "管理端 学生学习情况 批量删除学生记录表信息")
    public BaseResult deleteForClass(@RequestBody StudentStudyCaseClassBatchRequest list) {
        boolean retFlag = stduentClassSeeRecordService.deleteForClassBatch(list);
        return BaseResult.success(retFlag);
    }

    /**
     * 管理端 学生学习情况 学生列表
     */
    @PostMapping("/studentList")
    @ApiOperation(value = "管理端 学生学习情况 学生列表")
    public BaseResult<Page<StudentRecordListVo>> studentList(@RequestBody StudentStudyStudentListRequest query) {
        IPage<StudentRecordListVo> studentRecordListVoIPage = stduentClassSeeRecordService.studentList(query);
        return BaseResult.success(studentRecordListVoIPage);
    }

    /**
     * 管理端 学生学习情况 学习课程列表
     */
    @PostMapping("/courseList")
    @ApiOperation(value = " 管理端 学生学习情况 学习课程列表")
    public BaseResult<Page<StudentClassListVo>> courseList(@Validated @RequestBody StudentStudyCaseListRequest query) {
        IPage<StduentClassSeeRecord> iPage = stduentClassSeeRecordService.queryCoursePageList(query);

        return BaseResult.success(StudentClassListVo.convertToVoWithPage(iPage));
    }

}