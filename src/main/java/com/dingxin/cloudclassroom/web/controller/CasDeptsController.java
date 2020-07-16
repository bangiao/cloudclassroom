package com.dingxin.cloudclassroom.web.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dingxin.cloudclassroom.entity.CasDepts;
import com.dingxin.cloudclassroom.service.ICasDeptsService;
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
@RequestMapping("/casDepts")
@Api(value = "部门接口")
public class CasDeptsController {


    @Autowired
    private ICasDeptsService iCasDeptsService;

    @Autowired
    private MessageSource messageSource;

    /**
     * 列表
     */
    @GetMapping
    //@RequiresPermissions("casDepts:list" )
    @ApiOperation(value = "获取列表" )
    public BaseResult<Page<CasDepts>>  list(BaseQuery baseQuery){
        BaseResult<Page<CasDepts>> baseResult = new BaseResult<>();
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        Page pageList=iCasDeptsService.selectPage(page,new EntityWrapper<CasDepts>());
        if (CollectionUtils.isEmpty(pageList.getRecords())) {
            return baseResult.notFound();
        }
        baseResult.setData(pageList);
        return baseResult;
    }

    /**
     * 信息
     */
    @GetMapping("/{id}" )
    //@RequiresPermissions("casDepts:info" )
    @ApiOperation(value = "获取详情信息" )
    public BaseResult<CasDepts> info(@PathVariable("id" ) Integer id){
        BaseResult<CasDepts> baseResult = new BaseResult<>();

        CasDepts casDepts = iCasDeptsService.selectById(id);
        if (casDepts == null) {
            return baseResult.notFound();
        }
        baseResult.setData(casDepts);
        return baseResult;
    }

    /**
     * 保存
     */
    @PostMapping
    //@RequiresPermissions("casDepts:save" )
    @ApiOperation(value = "新增信息" )
    public BaseResult save(@RequestBody  CasDepts casDepts){

        BaseResult<CasDepts> baseResult = new BaseResult<>();

        boolean retFlag = iCasDeptsService.insert(casDepts);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        baseResult.setData(casDepts);
        return baseResult;
    }

    /**
     * 修改
     */
    @PutMapping
    //@RequiresPermissions("casDepts:update" )
    @ApiOperation(value = "修改信息" )
    public BaseResult update(@RequestBody @PathVariable("casDepts" ) CasDepts casDepts){
        BaseResult<CasDepts> baseResult = new BaseResult<>();

        boolean retFlag = iCasDeptsService.updateById(casDepts);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        baseResult.setData(casDepts);
        return baseResult;
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}" )
    //@RequiresPermissions("casDepts:delete" )
    @ApiOperation(value = "删除信息" )
    public BaseResult delete(@PathVariable("id" ) Integer id){
        BaseResult<CasDepts> baseResult = new BaseResult<>();
        boolean retFlag = iCasDeptsService.deleteById(id);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        return baseResult;
    }
}
