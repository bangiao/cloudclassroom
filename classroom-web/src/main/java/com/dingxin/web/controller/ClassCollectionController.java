package com.dingxin.web.controller;
<<<<<<< HEAD
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
=======
import com.dingxin.common.annotation.UserTag;
>>>>>>> 8bf7b1d016ba79126c5a2b4cd47433390299b05d
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.vo.Id;
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

import java.util.List;

/**
 * 课程收藏表
 */
@UserTag
@RestController
@RequestMapping("/classCollection")
@Api(tags={"课程收藏接口"})
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
        IPage pageList = classCollectionService.page(page,Wrappers.query(query.getData()).eq("del_flag",0).orderByDesc("create_time").select(""));
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
    @ApiOperation(value = "获取课程收藏表详情信息")
    public BaseResult<ClassCollection> search(@RequestBody  Id id){
        ClassCollection result = classCollectionService.getById(id.getId());
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增课程收藏表信息")
    public BaseResult save(@RequestBody  ClassCollection classCollection){
        ClassCollection entity = classCollectionService.query().eq("class_id", classCollection.getClassId()).eq("person_id", classCollection.getClassId()).getEntity();
        if (null!=entity){
            entity.setModifyTime(null);
            classCollection=entity;
        }
        boolean retFlag= classCollectionService.saveOrUpdate(classCollection);
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
    public BaseResult delete(@RequestBody Id id){
        ClassCollection byId = classCollectionService.getById(id.getId());
        if (null!=byId)byId.setDelFlag(1);
        boolean retFlag= classCollectionService.updateById(byId);
        return BaseResult.success(retFlag);
    }
    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程收藏表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        UpdateWrapper<ClassCollection> update = Wrappers.update();
        update.set("del_falg",1).in("id",list);
        boolean retFlag= classCollectionService.update(update);
        return BaseResult.success(retFlag);
    }
}