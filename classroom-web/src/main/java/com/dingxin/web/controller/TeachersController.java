package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
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
        Page<Teachers> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.like(StringUtils.isNotEmpty(query.getQueryStr()), Teachers::getXm, query.getQueryStr());
        IPage pageList = teachersService.page(page, qw);
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/searchById")
    @ApiOperation(value = "获取讲师详情信息")
    public BaseResult<Teachers> search(@RequestBody IdRequest idRequest) {
        Teachers result = teachersService.getById(idRequest.getId());
        return BaseResult.success(result);
    }


}