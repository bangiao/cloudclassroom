package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.StduentClassSeeRecordInsertRequest;
import com.dingxin.pojo.vo.StduentClassSeeRecordVo;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public BaseResult<Page<StduentClassSeeRecord>> list(@RequestBody CommQueryListRequest query) {
        IPage<StduentClassSeeRecord> pageList = stduentClassSeeRecordService.queryPage(query);
        return BaseResult.success(StduentClassSeeRecordVo.convertToVoWithPage(pageList));
    }

    /**
     * 列表查询
     */
    @PostMapping("/selfList")
    @ApiOperation(value = "获取学生记录表列表")
    public BaseResult<Page<StduentClassSeeRecord>> selfList(@RequestBody CommQueryListRequest query) {
        IPage<StduentClassSeeRecord> pageList = stduentClassSeeRecordService.selfList(query);
        return BaseResult.success(StduentClassSeeRecordVo.convertToVoWithPage(pageList));
    }

    /**
     * 信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取学生记录表详情信息")
    public BaseResult<StduentClassSeeRecord> info(@RequestBody IdRequest id) {
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

}