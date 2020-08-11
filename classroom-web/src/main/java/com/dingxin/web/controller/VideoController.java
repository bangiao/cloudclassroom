package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.VideoInsertRequest;
import com.dingxin.pojo.request.VideoListRequest;
import com.dingxin.pojo.request.VideoUpdateRequest;
import com.dingxin.pojo.vo.VideoVo;
import com.dingxin.web.service.IVideoService;
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
 * 
 */
@ManTag
@RestController
@RequestMapping("/video")
@Api(tags = "视频接口")
public class VideoController {


    @Autowired
    private IVideoService videoService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取视频列表",response = VideoVo.class)
    public BaseResult<Page<VideoVo>>list(@RequestBody VideoListRequest query){

        IPage<Video> videoPoPage = videoService.listQuery(query);
        IPage<VideoVo> videoVoPage = VideoVo.convertToVoWithPage(videoPoPage);

        return BaseResult.success(videoVoPage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存视频")
    public BaseResult save(@Validated @RequestBody VideoInsertRequest video){
        videoService.saveVideoRelated(video);
        //todo 同时更新课表表的总时长
//        videoService.updateCurriculumVideoDuration(video.getCurriculumId());

        return BaseResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除视频")
    public BaseResult delete(@RequestBody List<Integer> videoIds){
        videoService.deleteVideo(videoIds);

        return BaseResult.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新视频")
    public BaseResult<VideoVo> update(@Validated@RequestBody VideoUpdateRequest video){
        videoService.updateVideo(video);

        return BaseResult.success();
    }

    @PostMapping("/search")
    @ApiOperation(value = "获取视频详情")
    public BaseResult<VideoVo> loadVideoDetails(@RequestBody IdRequest id){
        Video video = videoService.loadVideoDetails(id);
        VideoVo videoVo = VideoVo.convertToVo(video);

        return BaseResult.success(videoVo);
    }
    @PostMapping("/update/watchAmount")
    @ApiOperation(value = "更新当前视频观看次数")
    public BaseResult updateWatchAmount(@RequestBody IdRequest currentVideoId){
        videoService.updateCurrentVideoWatchAmount(currentVideoId);

        return BaseResult.success();
    }

    @PostMapping("/disable")
    @ApiOperation(value = "禁用视频[支持批量]")
    public BaseResult disableVideos(@RequestBody List<Integer> videoIds){
        videoService.disableVideos(videoIds);

        return BaseResult.success();
    }
    @PostMapping("/enable")
    @ApiOperation(value = "启用视频[支持批量]")
    public BaseResult enableVideos(@RequestBody List<Integer> videoIds){
        videoService.enableVideos(videoIds);

        return BaseResult.success();
    }
}