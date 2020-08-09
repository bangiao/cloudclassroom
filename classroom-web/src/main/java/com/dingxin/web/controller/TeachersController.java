package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.ITeachersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 */
@ManTag
@RestController
@RequestMapping("/teachers")
@Api(tags = "讲师管理接口")
public class TeachersController {


    @Autowired
    private ITeachersService teachersService;

    /**
     * 讲师管理列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<Teachers>> list(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        return BaseResult.success(teachersService.queryPage(query));
    }
    /**
     * 讲师管理列表查询
     */
    @PostMapping("/listall")
    @ApiOperation(value = "获取列表下拉")
    public BaseResult<Map> listall() {
        //查询列表数据
        return BaseResult.success(teachersService.queryAll());
    }
    /**
     * 讲师管理Pc列表查询
     */
    @PostMapping("pc/list")
    @ApiOperation(value = "获取Pc列表")
    public BaseResult<Page<Teachers>> PClist(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        return BaseResult.success(teachersService.queryPCPage(query));
    }
    /**
     * 单个查询
     */
    @PostMapping("/searchById")
    @ApiOperation(value = "获取讲师详情信息")
    public BaseResult<Teachers> search(@RequestBody IdRequest idRequest) {
        return BaseResult.success(teachersService.queryById(idRequest));
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改讲师信息")
    public BaseResult update(@RequestBody Teachers teachers) {
        boolean retFlag = teachersService.updateById(teachers);
        return BaseResult.success(retFlag);
    }

}