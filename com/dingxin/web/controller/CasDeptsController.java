package com.dingxin.web.controller;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.web.service.ICasDeptsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

/**
 * 
 */
@RestController
@RequestMapping("/casDepts")
@Api(tags = "接口")
public class CasDeptsController {


    @Autowired
    private ICasDeptsService casDeptsService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<CasDepts>>list(@RequestBody BaseQuery<CasDepts> query){
        //查询列表数据
        Page<CasDepts> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = casDeptsService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<CasDepts> search(@RequestBody  CasDepts casDepts){
        CasDepts result = casDeptsService.getOne(Wrappers.query(casDepts));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  CasDepts casDepts){
        boolean retFlag= casDeptsService.save(casDepts);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody CasDepts casDepts){
        boolean retFlag= casDeptsService.updateById(casDepts);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody CasDepts casDepts){
        boolean retFlag= casDeptsService.remove(Wrappers.query(casDepts));
        return BaseResult.success(retFlag);
    }
}