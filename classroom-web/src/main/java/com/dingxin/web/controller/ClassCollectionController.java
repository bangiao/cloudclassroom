package com.dingxin.web.controller;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.web.service.IClassCollectionService;
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
 * 课程收藏表
 */
@RestController
@RequestMapping("/classCollection")
@Api(value = "课程收藏表接口")
public class ClassCollectionController {


    @Autowired
    private IClassCollectionService classCollectionService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程收藏表列表")
    public BaseResult<Page<ClassCollection>>list(@RequestBody BaseQuery<ClassCollection> query){
        //查询列表数据
        Page<ClassCollection> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = classCollectionService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程收藏表详情信息")
    public BaseResult<ClassCollection> search(@RequestBody  ClassCollection classCollection){
        ClassCollection result = classCollectionService.getOne(Wrappers.query(classCollection));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增课程收藏表信息")
    public BaseResult save(@RequestBody  ClassCollection classCollection){
        boolean retFlag= classCollectionService.save(classCollection);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改课程收藏表信息")
    public BaseResult update(@RequestBody ClassCollection classCollection){
        boolean retFlag= classCollectionService.updateById(classCollection);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程收藏表信息")
    public BaseResult delete(@RequestBody ClassCollection classCollection){
        boolean retFlag= classCollectionService.remove(Wrappers.query(classCollection));
        return BaseResult.success(retFlag);
    }
}