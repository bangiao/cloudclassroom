package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.common.annotation.ManTag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.basic.BaseQuery4List;
import com.dingxin.pojo.po.Major;
import com.dingxin.pojo.request.MajorRequest;
import com.dingxin.pojo.request.StudentStudyStudentListRequest;
import com.dingxin.web.service.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.dingxin.pojo.basic.BaseResult;

import java.util.List;

/**
 * 专业管理
 */
@ManTag
@RestController
@RequestMapping("/major")
@Api(tags = "专业管理接口")
public class MajorController {


    @Autowired
    private IMajorService majorService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取专业管理列表")
    public BaseResult<Page<Major>>list(@RequestBody StudentStudyStudentListRequest query){
        IPage pageList = majorService.queryPageList(query);
        return BaseResult.success(pageList);
    }
    /**
     * 列表查询
     */
    @PostMapping("/selectList")
    @ApiOperation(value = "用于前端获取下拉列表")
    public BaseResult<List<Major>> selectlist(@RequestBody MajorRequest request){
        return majorService.selectList(request);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取专业管理详情信息")
    public BaseResult<Major> search(@RequestBody  Major major){
        Major result = majorService.getOne(Wrappers.query(major));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增专业管理信息")
    public BaseResult save(@RequestBody  Major major){
        boolean retFlag= majorService.save(major);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改专业管理信息")
    public BaseResult update(@RequestBody Major major){
        boolean retFlag= majorService.updateById(major);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除专业管理信息")
    public BaseResult delete(@RequestBody Major major){
        boolean retFlag= majorService.remove(Wrappers.query(major));
        return BaseResult.success(retFlag);
    }
}