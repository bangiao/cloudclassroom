package com.dingxin.web.controller;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.web.service.IClassTypeService;
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
 * 课程类型表
 */
@RestController
@RequestMapping("/classType")
@Api(value = "课程类型接口")
public class ClassTypeController {


    @Autowired
    private IClassTypeService classTypeService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程类型列表")
    public BaseResult<Page<ClassType>>list(@RequestBody BaseQuery<ClassType> query){
        //查询列表数据
        Page<ClassType> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = classTypeService.page(page,Wrappers.query(query.getData()).eq("del_flag",0));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        pageList.setTotal(pageList.getRecords().size());
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程类型详情信息")
    public BaseResult<ClassType> search(@RequestBody  ClassType classType){
        ClassType result = classTypeService.getOne(Wrappers.query(classType));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增课程类型信息")
    public BaseResult save(@RequestBody  ClassType classType){
        boolean retFlag= classTypeService.saveOrUpdate(classType);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改课程类型信息")
    public BaseResult update(@RequestBody ClassType classType){
        boolean retFlag= classTypeService.updateById(classType);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程类型信息")
    public BaseResult delete(@RequestBody ClassType classType){
        boolean retFlag= classTypeService.remove(Wrappers.query(classType));
        return BaseResult.success(retFlag);
    }
}