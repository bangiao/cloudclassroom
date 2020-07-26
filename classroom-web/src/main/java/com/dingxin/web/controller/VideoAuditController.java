package com.dingxin.web.controller;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.po.VideoAudit;
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
}