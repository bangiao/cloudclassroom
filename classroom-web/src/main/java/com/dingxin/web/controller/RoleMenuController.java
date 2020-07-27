package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.vo.RoleMenuVo;
import com.dingxin.pojo.vo.Id;
import com.dingxin.web.service.IRoleMenuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色与菜单对应关系
 */
@ManTag
@RestController
@RequestMapping("/roleMenu")
@Api(value = "角色与菜单对应关系接口")
public class RoleMenuController {


    @Autowired
    private IRoleMenuService roleMenuService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取角色与菜单对应关系列表")
    public BaseResult<Page<RoleMenu>>list(@RequestBody BaseQuery<RoleMenu> query){
        //查询列表数据
        Page<RoleMenu> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = roleMenuService.page(page,Wrappers.query(query.getData()).eq("del_falg",0));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }
    /**
     * 列表查询
     */
    @PostMapping("/listAll")
    @ApiOperation(value = "菜单所有菜单且被选中的也标记")
    public BaseResult<Page<RoleMenu>>list(@RequestBody Id id){
        return BaseResult.success(roleMenuService.allMenu(id));
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取角色与菜单对应关系详情信息")
    public BaseResult<RoleMenu> search(@RequestBody  RoleMenu roleMenu){
        RoleMenu result = roleMenuService.getOne(Wrappers.query(roleMenu));
        return BaseResult.success(result);
    }

    /**
     * 保存  真删除
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增角色与菜单对应关系信息")
    public BaseResult save(@RequestBody RoleMenuVo vo){
        QueryWrapper<RoleMenu> qe = Wrappers.query();
        qe.eq("role_id",vo.getRoleId());
        roleMenuService.remove(qe);
        List<Integer> menus = vo.getMenus();
        ArrayList<RoleMenu> rms = Lists.newArrayList();
        menus.forEach(e->{
            RoleMenu build = RoleMenu.builder().roleId(vo.getRoleId()).menuId(e).build();
            rms.add(build);
        });
        boolean retFlag= roleMenuService.saveOrUpdateBatch(rms);
        return BaseResult.success(retFlag);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除角色与菜单对应关系信息")
    public BaseResult delete(@RequestBody Id id){
        RoleMenu byId = roleMenuService.getById(id.getId());
        if (null!=id)byId.setDelFlag(1);
        boolean retFlag= roleMenuService.updateById(byId);
        return BaseResult.success(retFlag);
    }
    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除角色与菜单对应关系信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        UpdateWrapper<RoleMenu> update = Wrappers.update();
        update.set("del_falg",1).in("id",list);
        boolean retFlag= roleMenuService.update(update);
        return BaseResult.success(retFlag);
    }
}