package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.VideoListRequest;
import com.dingxin.pojo.vo.VideoVo;
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
@RequestMapping("/video")
@Api(tags = "视频接口")
public class VideoController {


    @Autowired
    private IVideoService videoService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表",response = Video.class)
    public BaseResult<Page<VideoVo>>list(@RequestBody BaseQuery<VideoListRequest> query){

        IPage<Video> videoPoPage = videoService.listQuery(query);
        IPage<VideoVo> videoVoPage = VideoVo.convertToVoWithPage(videoPoPage);

        return BaseResult.success(videoVoPage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存视频")
    public BaseResult save(@RequestBody  Video video){
        videoService.saveOrUpdate(video);
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
}