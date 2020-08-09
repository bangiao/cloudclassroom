package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.AuditStatusEnum;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.VideoAuditRequest;
import com.dingxin.pojo.request.VideoAutoRequest;
import com.dingxin.pojo.request.VideoListRequest;
import com.dingxin.web.service.IVideoAuditService;
import com.dingxin.web.service.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private IVideoService videoService;


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
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  Video videoAudit){
        boolean retFlag= videoAuditService.saveOrUpdate(videoAudit);
        return BaseResult.success(retFlag);
    }


    /**
     * 视频审核列表查询
     */
    @PostMapping("/auditList")
    @ApiOperation(value = "视频审核列表查询")
    public BaseResult<Page<Video>>auditList(@RequestBody VideoListRequest query){
        IPage pageList = videoService.queryPageList(query);
        return BaseResult.success(pageList);
    }
    /**
     * 审核
     */
    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public BaseResult audit(@RequestBody VideoAuditRequest videoAudit){
        videoService.audit(videoAudit);
        return BaseResult.success().setMsg("审核成功！");
    }
//    /**
//     * 批量审核通过
//     */
//    @PostMapping("/auditBatch")
//    @ApiOperation(value = "批量审核通过")
//    public BaseResult auditBatch(@Validated @RequestBody VideoAutoRequest videoAutoRequest){
//        LambdaUpdateWrapper<Video> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.set(Video::getAuditFlag, CommonConstant.STATUS_AUDIT);
//        wrapper.set(Video::getAuditComments,videoAutoRequest.getAuditComments());
//        wrapper.in(Video::getId,videoAutoRequest.getIdList());
//        videoService.update(wrapper);
//        return BaseResult.success().setMsg("批量审核成功！");
//    }
//    /**
//     * 批量审核未通过
//     */
//    @PostMapping("/auditBatchUnapprove")
//    @ApiOperation(value = "批量审核未通过")
//    public BaseResult auditBatchUnapprove(@Validated @RequestBody VideoAutoRequest videoAutoRequest){
//        LambdaUpdateWrapper<Video> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.set(Video::getAuditFlag,CommonConstant.STATUS_UNAPPROVED);
//        wrapper.set(Video::getAuditComments,videoAutoRequest.getAuditComments());
//        wrapper.in(Video::getId,videoAutoRequest.getIdList());
//        videoService.update(wrapper);
//        return BaseResult.success().setMsg("批量审核成功！");
//    }
}