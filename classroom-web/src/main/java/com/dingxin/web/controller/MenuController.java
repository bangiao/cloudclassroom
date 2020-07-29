package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.MenuInsertRequest;
import com.dingxin.pojo.vo.MenuVo;
import com.dingxin.web.service.IMenuService;
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
 * 菜单管理
 */
@ManTag
@RestController
@RequestMapping("/menu")
@Api(value = "菜单管理接口")
public class MenuController {


    @Autowired
    private IMenuService menuService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取菜单管理列表")
    public BaseResult<Page<Menu>> list(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        IPage<Menu> pageList = menuService.queryPage(query);
        return BaseResult.success(MenuVo.convertToVoWithPage(pageList));
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取菜单管理详情信息")
    public BaseResult<Menu> search(@RequestBody IdRequest idRequest) {
        Menu result = menuService.getOneSelf(idRequest);
        return BaseResult.success(MenuVo.convent(result));
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增/修改菜单管理信息")
    public BaseResult save(@Validated @RequestBody MenuInsertRequest request) {
        Menu convent = MenuInsertRequest.convent(request);
        int con = menuService.saveSelf(convent);
        if (con == 1) {
            return BaseResult.failed(ExceptionEnum.DUPLICATE_DATA);
        } else if (con == 2) {
            return BaseResult.success(ExceptionEnum.SYSTEM_ERROR);
        }
        return BaseResult.success(true);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除菜单管理信息")
    public BaseResult delete(@Validated @RequestBody IdRequest id) {
        boolean retFlag = menuService.delete(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除菜单管理信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        boolean retFlag = menuService.deleteBatch(list);
        return BaseResult.success(retFlag);
    }
}