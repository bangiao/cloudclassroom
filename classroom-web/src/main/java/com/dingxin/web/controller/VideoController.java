package com.dingxin.web.controller;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.po.Video;
import com.dingxin.web.service.IVideoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

/**
 * 
 */
@UserTag
@RestController
@RequestMapping("/video")
@Api(value = "视频接口")
public class VideoController {


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
        IPage pageList = videoService.page(page,Wrappers.query(query.getData()));
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
    public BaseResult<Video> search(@RequestBody  Video video){
        Video result = videoService.getOne(Wrappers.query(video));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  Video video){
        boolean retFlag= videoService.save(video);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody Video video){
        boolean retFlag= videoService.updateById(video);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody Video video){
        boolean retFlag= videoService.remove(Wrappers.query(video));
        return BaseResult.success(retFlag);
    }
}