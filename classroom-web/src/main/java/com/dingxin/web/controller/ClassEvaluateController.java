package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.AuditStatusEnum;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.request.ClassEvaluateListRequest;
import com.dingxin.pojo.request.ClassEvaluateRequest;
import com.dingxin.pojo.vo.ClassEvaluateVo;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ThumbsUpVo;
import com.dingxin.web.service.IClassEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程评价表
 */
@UserTag
@RestController
@RequestMapping("/classEvaluate")
@Api(tags = {"课程评价表接口"})
public class ClassEvaluateController {


    @Autowired
    private IClassEvaluateService classEvaluateService;

    /**
     * 列表查询
     */
    @PostMapping("/alllist")
    @ApiOperation(value = "获取所有课程评价表列表")
    public BaseResult<Page<ClassEvaluate>> list(@RequestBody ClassEvaluateListRequest query) {
        IPage<ClassEvaluate> pageList = classEvaluateService.queryPage(query);
        return BaseResult.success(ClassEvaluateVo.convertToVoWithPage(pageList));
    }

    /**
     * 信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取课程评价表详情信息")
    public BaseResult<ClassEvaluate> info(@RequestBody IdRequest id) {
        ClassEvaluate classEvaluate = classEvaluateService.getById(id.getId());
        return BaseResult.success(classEvaluate);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增课程评价表信息")
    public BaseResult save(@Validated @RequestBody ClassEvaluate classEvaluate) {
        boolean retFlag = classEvaluateService.save(classEvaluate);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改课程评价表信息")
    public BaseResult update(@RequestBody ClassEvaluate classEvaluate) {
        boolean retFlag = classEvaluateService.updateById(classEvaluate);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改  点赞数
     */
    @PostMapping(value = "/upOrDown")
    @ApiOperation(value = "修改课程评价表点赞信息")
    public BaseResult updateUp(@RequestBody ThumbsUpVo thumbsUpVo) {
        boolean retFlag = classEvaluateService.updateUp(thumbsUpVo);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程评价表信息")
    public BaseResult delete(@RequestBody IdRequest id) {
        ClassEvaluate byId = classEvaluateService.getById(id.getId());
        if (null != byId) {
            byId.setDelFlag(1);
        }
        boolean retFlag = classEvaluateService.updateById(byId);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程评价表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        LambdaUpdateWrapper<ClassEvaluate> update = Wrappers.lambdaUpdate();
        update.set(ClassEvaluate::getDelFlag, 1).in(ClassEvaluate::getId, list);
        boolean retFlag = classEvaluateService.update(update);
        return BaseResult.success(retFlag);
    }

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
    public BaseResult<Page<ClassEvaluate>> auditList(@RequestBody BaseQuery<ClassEvaluate> query) {

        Page<ClassEvaluate> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ClassEvaluate> qw = new LambdaQueryWrapper<>();
        ClassEvaluate queryData = query.getData();
        qw.eq(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG);
        qw.and(Wrapper -> Wrapper.like(ClassEvaluate::getClassName, queryData.getQueryStr()).or().like(ClassEvaluate::getStudentName, queryData.getQueryStr()));
        qw.in(ClassEvaluate::getStatus, AuditStatusEnum.getAllStatus());
        IPage pageList = classEvaluateService.page(page, qw);
        if (CollectionUtils.isEmpty(pageList.getRecords())) {
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }
}