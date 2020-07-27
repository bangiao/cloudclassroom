package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.pojo.vo.Id;
import com.dingxin.web.service.IClassTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 课程类型表
 */
@UserTag
@RestController
@RequestMapping("/classType")
@Api(tags = "课程类型接口")
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
     * 课程类型列表不分页 用于下拉列表
     */
    @PostMapping("/listDic")
    @ApiOperation(value = "获取课程类型列表,用于类型选择")
    public BaseResult<Page<ClassType>>listDic(){
        QueryWrapper<ClassType> q = Wrappers.query();
        q.eq("del_flag",0);
        q.select("id as id","type_name as value");
        List<Map<String, Object>> maps = classTypeService.listMaps(q);
        return BaseResult.success(maps);
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
        QueryWrapper<ClassType> type_name = Wrappers.query();
        type_name.eq("type_name",classType.getTypeName()).eq("del_flag",0);
        int count = classTypeService.count(type_name);
        if (count>0)return BaseResult.success("重复的课程类型");
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
    public BaseResult delete(@RequestBody Id id){
        ClassType byId = classTypeService.getById(id.getId());
        if (null!=byId)byId.setDelFlag(1);
        boolean retFlag= classTypeService.updateById(byId);
        return BaseResult.success(retFlag);
    }
    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程类型信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        UpdateWrapper<ClassType> update = Wrappers.update();
        update.set("del_falg",1).in("id",list);
        boolean retFlag= classTypeService.update(update);
        return BaseResult.success(retFlag);
    }
}