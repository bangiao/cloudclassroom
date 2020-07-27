package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IClassCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程收藏表
 */
@UserTag
@RestController
@RequestMapping("/classCollection")
@Api(tags = {"课程收藏接口"})
public class ClassCollectionController {


    @Autowired
    private IClassCollectionService classCollectionService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程收藏表列表")
    public BaseResult<Page<ClassCollection>> list(@RequestBody BaseQuery<ClassCollection> query) {
        //查询列表数据
        Page<ClassCollection> page = new Page(query.getCurrentPage(), query.getPageSize());
        IPage pageList = classCollectionService.page(page, Wrappers.lambdaQuery(query.getData()).eq(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG).orderByDesc(ClassCollection::getCreateTime));
        pageList.setTotal(pageList.getRecords().size());
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程收藏表详情信息")
    public BaseResult<ClassCollection> search(@RequestBody IdRequest id) {
        ClassCollection result = classCollectionService.getById(id.getId());
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增课程收藏表信息")
    public BaseResult save(@Validated @RequestBody ClassCollection classCollection) {
        ClassCollection entity = classCollectionService.lambdaQuery().eq(ClassCollection::getClassId, classCollection.getClassId()).eq(ClassCollection::getPersonId, classCollection.getClassId()).getEntity();
        if (null != entity) {
            entity.setModifyTime(null);
            classCollection = entity;
        }
        boolean retFlag = classCollectionService.saveOrUpdate(classCollection);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改课程收藏表信息")
    public BaseResult update(@RequestBody ClassCollection classCollection) {
        boolean retFlag = classCollectionService.updateById(classCollection);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程收藏表信息")
    public BaseResult delete(@RequestBody IdRequest id) {
        ClassCollection byId = classCollectionService.getById(id.getId());
        if (null != byId) byId.setDelFlag(1);
        boolean retFlag = classCollectionService.updateById(byId);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程收藏表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        UpdateWrapper<ClassCollection> update = Wrappers.update();
        update.set("del_falg", 1).in("id", list);
        boolean retFlag = classCollectionService.update(update);
        return BaseResult.success(retFlag);
    }
}