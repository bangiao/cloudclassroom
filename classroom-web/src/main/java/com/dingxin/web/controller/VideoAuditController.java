package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.VideoAudit;
import com.dingxin.pojo.request.VideoAutoRequest;
import com.dingxin.web.service.IVideoAuditService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.Arrays;
import java.util.List;

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
    public BaseResult<Page<VideoAudit>>list(@RequestBody BaseQuery<VideoAudit> query){
        //查询列表数据
        Page<VideoAudit> page = new Page(query.getCurrentPage(),query.getPageSize());
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
    public BaseResult<VideoAudit> search(@RequestBody  VideoAudit videoAudit){
        VideoAudit result = videoAuditService.getOne(Wrappers.query(videoAudit));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  VideoAudit videoAudit){
        boolean retFlag= videoAuditService.save(videoAudit);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody VideoAudit videoAudit){
        boolean retFlag= videoAuditService.updateById(videoAudit);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody VideoAudit videoAudit){
        boolean retFlag= videoAuditService.remove(Wrappers.query(videoAudit));
        return BaseResult.success(retFlag);
    }
    /**
     * 视频审核列表查询
     */
    @PostMapping("/auditList")
    @ApiOperation(value = "视频审核列表查询")
    public BaseResult<Page<VideoAudit>>auditList(@RequestBody BaseQuery<VideoAudit> query){

        Page<VideoAudit> page = new Page(query.getCurrentPage(),query.getPageSize());
        QueryWrapper<VideoAudit> qw = new QueryWrapper<>();
//        qw.eq("del_flag",0);
        List<String> list = Arrays.asList("0,-1".split(","));
        qw.in("audit_flag",list);
        VideoAudit queryData = query.getData();
        qw.like("video_name",queryData.getVideoName());
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
    public BaseResult audit(@RequestBody  VideoAudit videoAudit){
        UpdateWrapper<VideoAudit> wrapper = new UpdateWrapper<>();
        wrapper.set("audit_flag",videoAudit.getAuditFlag());
        wrapper.set("audit_comments",videoAudit.getAuditComments());
        wrapper.eq("id",videoAudit.getId());
        videoAuditService.update(wrapper);
        return BaseResult.success().setMsg("审核成功！");
    }
    /**
     * 批量审核通过
     */
    @PostMapping("/auditBatch")
    @ApiOperation(value = "批量审核通过")
    public BaseResult auditBatch(@RequestBody VideoAutoRequest videoAutoRequest){
        UpdateWrapper<VideoAudit> wrapper = new UpdateWrapper<>();
        wrapper.set("audit_flag",1);
        wrapper.set("audit_comments",videoAutoRequest.getAuditComments());
        wrapper.in("id",videoAutoRequest.getIdList());
        videoAuditService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }
    /**
     * 批量审核未通过
     */
    @PostMapping("/auditBatchUnapprove")
    @ApiOperation(value = "批量审核未通过")
    public BaseResult auditBatchUnapprove(@RequestBody VideoAutoRequest videoAutoRequest){
        UpdateWrapper<VideoAudit> wrapper = new UpdateWrapper<>();
        wrapper.set("audit_flag",-1);
        wrapper.set("audit_comments",videoAutoRequest.getAuditComments());
        wrapper.in("id",videoAutoRequest.getIdList());
        videoAuditService.update(wrapper);
        return BaseResult.success().setMsg("批量审核成功！");
    }
}