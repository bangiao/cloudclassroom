package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.vo.Id;
import com.dingxin.pojo.vo.ThumbsUpVo;
import com.dingxin.web.service.IClassEvaluateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.Arrays;
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
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程评价表列表")
    public BaseResult<Page<ClassEvaluate>>list(@RequestBody BaseQuery<ClassEvaluate> query){
        //查询列表数据
        Page<ClassEvaluate> page = new Page(query.getCurrentPage(),query.getPageSize());
        QueryWrapper<ClassEvaluate> qw = new QueryWrapper<>();
        ClassEvaluate data = query.getData();
        if (null!=data) {
            String queryStr = data.getQueryStr();
            qw.like("student_name", queryStr).or().like("student_code", queryStr).or().like("class_name", queryStr);
        }
        qw.eq("del_flag",0);
        IPage pageList = classEvaluateService.page(page, qw);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }
    /**
     * 信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取课程评价表详情信息")
    public BaseResult<ClassEvaluate> info(@RequestBody Id id){
        ClassEvaluate classEvaluate = classEvaluateService.getById(id.getId());
        return BaseResult.success(classEvaluate);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增课程评价表信息")
    public BaseResult save(@RequestBody  ClassEvaluate classEvaluate){
        boolean retFlag= classEvaluateService.save(classEvaluate);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改课程评价表信息")
    public BaseResult update(@RequestBody  ClassEvaluate classEvaluate){
        boolean retFlag= classEvaluateService.updateById(classEvaluate);
        return BaseResult.success(retFlag);
    }
    /**
     * 修改  点赞数
     */
    @PostMapping(value = "/upOrDown")
    @ApiOperation(value = "修改课程评价表点赞信息")
    public BaseResult updateUp(@RequestBody ThumbsUpVo thumbsUpVo){
        boolean retFlag=classEvaluateService.updateUp(thumbsUpVo);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程评价表信息")
    public BaseResult delete(@RequestBody Id id){
        boolean retFlag= classEvaluateService.removeById(id.getId());
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程评价表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        boolean retFlag= classEvaluateService.removeByIds(list);
        return BaseResult.success(retFlag);
    }

    /**
     * 审核
     */
    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public BaseResult audit(@RequestBody  ClassEvaluate classEvaluate){
        UpdateWrapper<ClassEvaluate> wrapper = new UpdateWrapper<>();
        wrapper.set("status",classEvaluate.getStatus());
        wrapper.eq("id",classEvaluate.getId());
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("审核成功！");
    }
    /**
     * 批量审核通过
     */
    @PostMapping("/auditBatch")
    @ApiOperation(value = "批量审核通过")
    public BaseResult auditBatch(@RequestBody List<Integer> ids){
        UpdateWrapper<ClassEvaluate> wrapper = new UpdateWrapper<>();
        wrapper.set("status",1);
        wrapper.in("id",ids);
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }
    /**
     * 批量审核未通过
     */
    @PostMapping("/auditBatchUnapprove")
    @ApiOperation(value = "批量审核未通过")
    public BaseResult auditBatchUnapprove(@RequestBody List<Integer> ids){
        UpdateWrapper<ClassEvaluate> wrapper = new UpdateWrapper<>();
        wrapper.set("status",-1);
        wrapper.in("id",ids);
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }
    /**
     * 评价审核列表查询
     */
    @PostMapping("/auditList")
    @ApiOperation(value = "评价审核列表查询")
    public BaseResult<Page<ClassEvaluate>>auditList(@RequestBody BaseQuery<ClassEvaluate> query){

        Page<ClassEvaluate> page = new Page(query.getCurrentPage(),query.getPageSize());
        QueryWrapper<ClassEvaluate> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        List<String> list = Arrays.asList("0,-1".split(","));
        qw.in("status",list);
        IPage pageList = classEvaluateService.page(page, qw);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }
}