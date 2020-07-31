package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.Viewpager;
import com.dingxin.web.service.IViewpagerService;
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
@UserTag
@RestController
@RequestMapping("/viewpager")
@Api(tags = "轮播图片接口")
public class ViewpagerController {


    @Autowired
    private IViewpagerService viewpagerService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<Viewpager>>list(@RequestBody BaseQuery<Viewpager> query){
        //查询列表数据
        Page<Viewpager> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<Viewpager> qw = Wrappers.lambdaQuery();
        IPage pageList = viewpagerService.page(page,qw.eq(Viewpager::getDelFlag, CommonConstant.DEL_FLAG));
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<Viewpager> search(@RequestBody  Viewpager viewpager){
        Viewpager result = viewpagerService.getOne(Wrappers.query(viewpager));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  Viewpager viewpager){
        boolean retFlag= viewpagerService.save(viewpager);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody Viewpager viewpager){
        boolean retFlag= viewpagerService.updateById(viewpager);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody Viewpager viewpager){
        boolean retFlag= viewpagerService.remove(Wrappers.query(viewpager));
        return BaseResult.success(retFlag);
    }
}