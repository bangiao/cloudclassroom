package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.BannerManage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.po.BannerManage;
import com.dingxin.pojo.request.BannerRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IBannerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

/**
 * 首页banner
 */
@ManTag
@RestController
@RequestMapping("/bannerManage")
@Api(tags = {"首页banner"})
public class BannerManageController {


    @Autowired
    private IBannerManageService bannerManageService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<BannerManage>>list(@RequestBody BaseQuery<BannerManage> query){
        //查询列表数据
        IPage pageList = bannerManageService.queryPageList(query);
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<BannerManage> search(@RequestBody  BannerManage bannerManage){
        BannerManage result = bannerManageService.getOne(Wrappers.query(bannerManage));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  BannerManage bannerManage){
        boolean retFlag= bannerManageService.save(bannerManage);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody BannerManage bannerManage){
        boolean retFlag= bannerManageService.updateBannerManage(bannerManage);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody IdRequest id){
        boolean retFlag = bannerManageService.deleteBannerManage(id);
        return BaseResult.success(retFlag);
    }
    /**
     * 启用/禁用接口
     */
    @PostMapping("/enableStatus")
    @ApiOperation(value = "启用/禁用接口")
    public BaseResult enableStatus(@RequestBody BannerRequest bannerRequest){
        boolean enableStatus = bannerManageService.enableStatus(bannerRequest);
        return BaseResult.success(enableStatus);
    }
}