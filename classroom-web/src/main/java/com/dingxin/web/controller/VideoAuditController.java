package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.CurriculumAuditListRequest;
import com.dingxin.pojo.request.VideoAuditRequest;
import com.dingxin.pojo.request.VideoListRequest;
import com.dingxin.web.service.IVideoAuditService;
import com.dingxin.web.service.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private IVideoService videoService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程审核列表")
    public BaseResult<Page<Curriculum>>list(@RequestBody CurriculumAuditListRequest query){
        //查询列表数据
        IPage pageList = videoAuditService.queryPageList(query);
        return BaseResult.success(pageList);
    }

    /**
     * 根据课程ID获取章节列表
     * @param query
     * @return
     */
    @PostMapping("/searchchapterbycurrid")
    @ApiOperation(value = "根据课程ID获取章节列表")
    public BaseResult<List<Chapter>>searchchapterbycurrid(@RequestBody CurriculumAuditListRequest query){
        return videoAuditService.searchchapterbycurrid(query);
    }

    @PostMapping("/searchevaluatebycurrid")
    @ApiOperation(value = "根据课程ID获取评论")
    public BaseResult<List<ClassEvaluate>>searchevaluatebycurrid(@RequestBody CurriculumAuditListRequest query){
        return videoAuditService.searchevaluatebycurrid(query);
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