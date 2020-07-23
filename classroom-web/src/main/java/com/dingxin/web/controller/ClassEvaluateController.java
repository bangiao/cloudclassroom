package com.dingxin.web.controller;
<<<<<<< HEAD
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
=======
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
>>>>>>> f6099f4f7da655bb4ea8ea0f1f74a5590c5c7c37
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.web.service.IClassEvaluateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.List;

/**
 * 课程评价表
 */
@RestController
@RequestMapping("/classEvaluate")
@Api(value = "课程评价表接口")
public class ClassEvaluateController {


    @Autowired
    private IClassEvaluateService classEvaluateService;


    /**
     * 列表
     */
    @GetMapping
    @ApiOperation(value = "获取课程评价表列表")
    public BaseResult<Page<ClassEvaluate>>list(BaseQuery baseQuery){
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        IPage pageList = classEvaluateService.page(page,new QueryWrapper<ClassEvaluate>().eq("yn",0));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取课程评价表详情信息")
    public BaseResult<ClassEvaluate> info(@PathVariable("id") Integer id){
        ClassEvaluate classEvaluate = classEvaluateService.getById(id);
        return BaseResult.success(classEvaluate);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增课程评价表信息")
    public BaseResult save(@RequestBody  ClassEvaluate classEvaluate){
        boolean retFlag= classEvaluateService.save(classEvaluate);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PutMapping
    @ApiOperation(value = "修改课程评价表信息")
    public BaseResult update(@RequestBody @PathVariable("classEvaluate") ClassEvaluate classEvaluate){
        BaseResult<ClassEvaluate> baseResult=new BaseResult<>();
        boolean retFlag= classEvaluateService.updateById(classEvaluate);
        return BaseResult.success(retFlag);
    }
    /**
     * 修改  点赞数
     */    @PutMapping(value = "/upOrDown")
    @ApiOperation(value = "修改课程评价表点赞信息")
    public BaseResult updateUp(Boolean upOrDown,Integer id){
        boolean retFlag=classEvaluateService.updateUp(upOrDown,id);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除课程评价表信息")
    public BaseResult delete(@PathVariable("id") Integer id){
        boolean retFlag= classEvaluateService.removeById(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 审核
     */
    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public BaseResult audit(@RequestBody  ClassEvaluate classEvaluate){
        UpdateWrapper<ClassEvaluate> wrapper = new UpdateWrapper<>();
        wrapper.set("status",1);
        wrapper.eq("id",classEvaluate.getId());
        classEvaluateService.update(wrapper);
        return BaseResult.success("审核成功！");
    }
    /**
     * 批量审核
     */
    @PostMapping("/auditBatch")
    @ApiOperation(value = "批量审核")
    public BaseResult auditBatch(@RequestBody List<Integer> ids){
        UpdateWrapper<ClassEvaluate> wrapper = new UpdateWrapper<>();
        wrapper.set("status",1);
        wrapper.in("id",ids);
        classEvaluateService.update(wrapper);
        return BaseResult.success("批量审核成功！");
    }
}