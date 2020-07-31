package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.AuditStatusEnum;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.request.*;
import com.dingxin.pojo.vo.ClassEvaluateVo;
import com.dingxin.web.service.IClassEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程评价审核
 */
@UserTag
@RestController
@RequestMapping("/classEvaluateAudit")
@Api(tags = {"课程评价审核接口"})
public class ClassEvaluateAuditController {


    @Autowired
    private IClassEvaluateService classEvaluateService;

    /**
     * 审核
     */
    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public BaseResult audit(@RequestBody ClassEvaluate classEvaluate) {
        LambdaUpdateWrapper<ClassEvaluate> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ClassEvaluate::getStatus, classEvaluate.getStatus());
        wrapper.set(ClassEvaluate::getAuditComments, classEvaluate.getAuditComments());
        wrapper.eq(ClassEvaluate::getId, classEvaluate.getId());
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("审核成功！");
    }

    /**
     * 批量审核通过
     */
    @PostMapping("/auditBatch")
    @ApiOperation(value = "批量审核通过")
    public BaseResult auditBatch(@Validated @RequestBody ClassEvaluateRequest classEvaluateRequest) {
        LambdaUpdateWrapper<ClassEvaluate> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT);
        wrapper.set(ClassEvaluate::getAuditComments, classEvaluateRequest.getAuditComments());
        wrapper.in(ClassEvaluate::getId, classEvaluateRequest.getIdList());
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }

    /**
     * 批量审核未通过
     */
    @PostMapping("/auditBatchUnapprove")
    @ApiOperation(value = "批量审核未通过")
    public BaseResult auditBatchUnapprove(@Validated @RequestBody ClassEvaluateRequest classEvaluateRequest) {
        LambdaUpdateWrapper<ClassEvaluate> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(ClassEvaluate::getStatus, CommonConstant.STATUS_UNAPPROVE);
        wrapper.set(ClassEvaluate::getAuditComments, classEvaluateRequest.getAuditComments());
        wrapper.in(ClassEvaluate::getId, classEvaluateRequest.getIdList());
        classEvaluateService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }

    /**
     * 评价审核列表查询
     */
    @PostMapping("/auditList")
    @ApiOperation(value = "评价审核列表查询")
    public BaseResult<Page<ClassEvaluate>> auditList(@RequestBody ClassEvaluateListRequest query) {

        Page<ClassEvaluate> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ClassEvaluate> qw = new LambdaQueryWrapper<>();
        String queryStr = query.getQueryStr();
        if(StringUtils.isNotEmpty(queryStr)){
            qw.and(Wrapper -> Wrapper.like(ClassEvaluate::getClassName, queryStr).or().like(ClassEvaluate::getStudentName, queryStr));
        }
        qw.eq(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG);
        qw.in(ClassEvaluate::getStatus, AuditStatusEnum.getAllStatus());
        IPage pageList = classEvaluateService.page(page, qw);
        return BaseResult.success(pageList);
    }
}