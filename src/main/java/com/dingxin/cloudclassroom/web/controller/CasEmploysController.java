package com.dingxin.cloudclassroom.web.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dingxin.cloudclassroom.entity.CasEmploys;
import com.dingxin.cloudclassroom.service.ICasEmploysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.dingxin.cloudclassroom.common.BaseResult;
import com.dingxin.cloudclassroom.common.BaseQuery;

import java.util.Locale;

/**
* 
*/
@RestController
@RequestMapping("/casEmploys")
@Api(value = "CAS用户接口")
public class CasEmploysController {


    @Autowired
    private ICasEmploysService iCasEmploysService;

    @Autowired
    private MessageSource messageSource;

    /**
     * 列表
     */
    @GetMapping
    //@RequiresPermissions("casEmploys:list" )
    @ApiOperation(value = "获取列表" )
    public BaseResult<Page<CasEmploys>>  list(BaseQuery baseQuery){
        BaseResult<Page<CasEmploys>> baseResult = new BaseResult<>();
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        Page pageList=iCasEmploysService.selectPage(page,new EntityWrapper<CasEmploys>());
        if (CollectionUtils.isEmpty(pageList.getRecords())) {
            return baseResult.notFound();
        }
        baseResult.setData(pageList);
        return baseResult;
    }

    /**
     * 信息
     */
    @GetMapping("/{sid}" )
    //@RequiresPermissions("casEmploys:info" )
    @ApiOperation(value = "获取详情信息" )
    public BaseResult<CasEmploys> info(@PathVariable("sid" ) String sid){
        BaseResult<CasEmploys> baseResult = new BaseResult<>();
        CasEmploys casEmploys = iCasEmploysService.selectById(sid);
        if (casEmploys == null) {
            return baseResult.notFound();
        }
        baseResult.setData(casEmploys);
        return baseResult;
    }
}
