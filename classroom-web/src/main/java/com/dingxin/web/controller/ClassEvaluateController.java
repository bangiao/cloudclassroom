package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.pojo.request.ClassEvaluateRequest;
import com.dingxin.pojo.request.VideoAutoRequest;
import com.dingxin.pojo.vo.Id;
import com.dingxin.pojo.vo.ThumbsUpVo;
import com.dingxin.web.service.IClassEvaluateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 课程评价表
 */
@UserTag
@RestController
@RequestMapping("/classEvaluate")
@Api(tags={"课程评价表接口"})
public class ClassEvaluateController {


    @Autowired
    private IClassEvaluateService classEvaluateService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程评价表列表")
    public BaseResult<Page<ClassEvaluate>>list(@RequestBody BaseQuery<ClassEvaluate> query){
        IPage pageList = classEvaluateService.queryPage(query);
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
        ClassEvaluate byId = classEvaluateService.getById(id.getId());
        if (null!=byId)byId.setDelFlag(1);
        boolean retFlag= classEvaluateService.updateById(byId);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程评价表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        UpdateWrapper<ClassEvaluate> update = Wrappers.update();
        update.set("del_falg",1).in("id",list);
        boolean retFlag= classEvaluateService.update(update);
        return BaseResult.success(retFlag);
    }

    /**
     * 审核
     */
    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public BaseResult audit(@RequestBody  ClassEvaluate classEvaluate){
        LambdaUpdateWrapper<ClassEvaluate> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ClassEvaluate::getStatus,classEvaluate.getStatus());
        wrapper.set(ClassEvaluate::getAuditComments,classEvaluate.getAuditComments());
        wrapper.eq(ClassEvaluate::getId,classEvaluate.getId());
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("审核成功！");
    }
    /**
     * 批量审核通过
     */
    @PostMapping("/auditBatch")
    @ApiOperation(value = "批量审核通过")
    public BaseResult auditBatch(@Validated @RequestBody ClassEvaluateRequest classEvaluateRequest){
        LambdaUpdateWrapper<ClassEvaluate> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT);
        wrapper.set(ClassEvaluate::getAuditComments,classEvaluateRequest.getAuditComments());
        wrapper.in(ClassEvaluate::getId,classEvaluateRequest.getIdList());
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }
    /**
     * 批量审核未通过
     */
    @PostMapping("/auditBatchUnapprove")
    @ApiOperation(value = "批量审核未通过")
    public BaseResult auditBatchUnapprove(@Validated @RequestBody ClassEvaluateRequest classEvaluateRequest){
        LambdaUpdateWrapper<ClassEvaluate> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ClassEvaluate::getStatus,CommonConstant.STATUS_UNAPPROVE);
        wrapper.set(ClassEvaluate::getAuditComments,classEvaluateRequest.getAuditComments());
        wrapper.in(ClassEvaluate::getId,classEvaluateRequest.getIdList());
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
        LambdaQueryWrapper<ClassEvaluate> qw = new LambdaQueryWrapper<>();
        ClassEvaluate queryData = query.getData();
        qw.eq(ClassEvaluate::getDelFlag,CommonConstant.DEL_FLAG);
        qw.and(Wrapper -> Wrapper.like(ClassEvaluate::getClassName,queryData.getQueryStr()).or().like(ClassEvaluate::getStudentName,queryData.getQueryStr()));
        qw.in(ClassEvaluate::getStatus,CommonConstant.LIST);
        IPage pageList = classEvaluateService.page(page, qw);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }
}