package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.Role;
import com.dingxin.pojo.vo.Id;
import com.dingxin.web.service.IMenuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

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
    public BaseResult<Page<Menu>>list(@RequestBody BaseQuery<Menu> query){
        //查询列表数据
        Page<Menu> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = menuService.page(page,Wrappers.query(query.getData()).eq("del_falg",0));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取菜单管理详情信息")
    public BaseResult<Menu> search(@RequestBody  Menu menu){
        Menu result = menuService.getOne(Wrappers.query(menu));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增菜单管理信息")
    public BaseResult save(@RequestBody  Menu menu){
        QueryWrapper<Menu> type_name = Wrappers.query();
        type_name.eq("type_name",menu.getName()).eq("del_flag",0);
        int count = menuService.count(type_name);
        if (count>0)return BaseResult.success("菜单名重复");
        boolean retFlag= menuService.save(menu);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改菜单管理信息")
    public BaseResult update(@RequestBody Menu menu){
        boolean retFlag= menuService.updateById(menu);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除菜单管理信息")
    public BaseResult delete(@RequestBody Id id){
        Menu byId = menuService.getById(id.getId());
        if (null!=byId)byId.setDelFlag(1);
        boolean retFlag= menuService.updateById(byId);
        return BaseResult.success(retFlag);
    }
    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除菜单管理信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        UpdateWrapper<Menu> update = Wrappers.update();
        update.set("del_falg",1).in("id",list);
        boolean retFlag= menuService.update(update);
        return BaseResult.success(retFlag);
    }
}