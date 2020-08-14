package com.dingxin.web.controller;

import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.request.LiveVideoInsertRequest;
import com.dingxin.pojo.request.VideoInsertRequest;
import com.dingxin.web.service.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: pulei2 <br>
 * date: 2020/8/14 23:18 <br>
 * description: 直播视频管理接口 <br>
 */
@ManTag
@RestController
@RequestMapping("/liveVideo")
@Api(tags = "直播视频管理接口")
public class LiveVideoController {
    @Autowired
    private IVideoService videoService;

    @PostMapping("/save")
    @ApiOperation(value = "保存直播视频视频")
    public BaseResult save(@Validated @RequestBody LiveVideoInsertRequest video) {
        videoService.addLiveVideoInfo(video);
        return BaseResult.success();
    }
}
