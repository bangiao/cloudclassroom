package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.VideoAutoRequest;
import com.dingxin.web.service.IVideoAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@ManTag
@RestController
@RequestMapping("/videoAudit")
@Api(tags = "视频审核接口")
public class VideoAuditController {


    @Autowired
    private IVideoAuditService videoAuditService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<Video>>list(@RequestBody BaseQuery<Video> query){
        //查询列表数据
        Page<Video> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = videoAuditService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<Video> search(@RequestBody  Video videoAudit){
        Video result = videoAuditService.getOne(Wrappers.query(videoAudit));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  Video videoAudit){
        boolean retFlag= videoAuditService.save(videoAudit);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody Video videoAudit){
        boolean retFlag= videoAuditService.updateById(videoAudit);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody Video videoAudit){
        boolean retFlag= videoAuditService.remove(Wrappers.query(videoAudit));
        return BaseResult.success(retFlag);
    }
    /**
     * 视频审核列表查询
     */
    @PostMapping("/auditList")
    @ApiOperation(value = "视频审核列表查询")
    public BaseResult<Page<Video>>auditList(@RequestBody BaseQuery<Video> query){

        Page<Video> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<Video> qw = new LambdaQueryWrapper<>();
//        qw.eq("del_flag",0);
        qw.in(Video::getAuditFlag,CommonConstant.LIST);
        Video queryData = query.getData();
        qw.and(Wrapper -> Wrapper.like(Video::getVideoName,queryData.getQueryStr()));
        IPage pageList = videoAuditService.page(page, qw);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }
    /**
     * 审核
     */
    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public BaseResult audit(@RequestBody  Video videoAudit){
        LambdaUpdateWrapper<Video> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Video::getAuditFlag,videoAudit.getAuditFlag());
        wrapper.set(Video::getAuditComments,videoAudit.getAuditComments());
        wrapper.eq(Video::getId,videoAudit.getId());
        videoAuditService.update(wrapper);
        return BaseResult.success().setMsg("审核成功！");
    }
    /**
     * 批量审核通过
     */
    @PostMapping("/auditBatch")
    @ApiOperation(value = "批量审核通过")
    public BaseResult auditBatch(@Validated @RequestBody VideoAutoRequest videoAutoRequest){
        LambdaUpdateWrapper<Video> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Video::getAuditFlag, CommonConstant.STATUS_AUDIT);
        wrapper.set(Video::getAuditComments,videoAutoRequest.getAuditComments());
        wrapper.in(Video::getId,videoAutoRequest.getIdList());
        videoAuditService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }
    /**
     * 批量审核未通过
     */
    @PostMapping("/auditBatchUnapprove")
    @ApiOperation(value = "批量审核未通过")
    public BaseResult auditBatchUnapprove(@Validated @RequestBody VideoAutoRequest videoAutoRequest){
        LambdaUpdateWrapper<Video> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Video::getAuditFlag,CommonConstant.STATUS_UNAPPROVE);
        wrapper.set(Video::getAuditComments,videoAutoRequest.getAuditComments());
        wrapper.in(Video::getId,videoAutoRequest.getIdList());
        videoAuditService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }
}