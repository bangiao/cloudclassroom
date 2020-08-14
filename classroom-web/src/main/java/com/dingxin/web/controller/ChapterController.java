package com.dingxin.web.controller;

import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ChapterSelectVo;
import com.dingxin.web.service.IChapterService;
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
 * author: pulei2 <br>
 * date: 2020/8/14 20:57 <br>
 * description: 章节接口层 <br>
 */
@RestController
@RequestMapping("/chapter")
@ManTag
@Api(tags = "章节接口")
public class ChapterController {

    @Autowired
    private IChapterService chapterService;

    /**
     * 列表查询
     */
    @PostMapping("/list/select")
    @ApiOperation(value = "获取所有课程列表下拉")
    public BaseResult<List<ChapterSelectVo>> chapterSelectList(@RequestBody@Validated IdRequest id){

        return BaseResult.success(chapterService.loadChapterAndChildren(id));
    }
}
