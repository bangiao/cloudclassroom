package com.dingxin.web.controller;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.web.service.ICasEmploysService;
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
@RequestMapping("/casEmploys")
@Api(tags = "接口")
public class CasEmploysController {


    @Autowired
    private ICasEmploysService casEmploysService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<CasEmploys>>list(@RequestBody BaseQuery<CasEmploys> query){
        //查询列表数据
        Page<CasEmploys> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = casEmploysService.page(page,Wrappers.query(query.getData()));
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
    public BaseResult<CasEmploys> search(@RequestBody  CasEmploys casEmploys){
        CasEmploys result = casEmploysService.getOne(Wrappers.query(casEmploys));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  CasEmploys casEmploys){
        boolean retFlag= casEmploysService.save(casEmploys);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody CasEmploys casEmploys){
        boolean retFlag= casEmploysService.updateById(casEmploys);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody CasEmploys casEmploys){
        boolean retFlag= casEmploysService.remove(Wrappers.query(casEmploys));
        return BaseResult.success(retFlag);
    }
}