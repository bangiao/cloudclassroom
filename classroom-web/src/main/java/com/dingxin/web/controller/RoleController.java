package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.Role;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IRoleService;
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
 * 角色
 */
@ManTag
@RestController
@RequestMapping("/role")
@Api(value = "角色接口")
public class RoleController {


    @Autowired
    private IRoleService roleService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取角色列表")
    public BaseResult<Page<Role>>list(@RequestBody BaseQuery<Role> query){
        //查询列表数据
        Page<Role> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = roleService.page(page,Wrappers.query(query.getData()).eq("del_falg",0));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取角色详情信息")
    public BaseResult<Role> search(@RequestBody  Role role){
        Role result = roleService.getOne(Wrappers.query(role));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增角色信息")
    public BaseResult save(@RequestBody  Role role){
        QueryWrapper<Role> type_name = Wrappers.query();
        type_name.eq("type_name",role.getRoleName()).eq("del_flag",0);
        int count = roleService.count(type_name);
        if (count>0)return BaseResult.success("角色名重复");
        boolean retFlag= roleService.save(role);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改角色信息")
    public BaseResult update(@RequestBody Role role){
        boolean retFlag= roleService.updateById(role);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除角色信息")
    public BaseResult delete(@RequestBody IdRequest id){
        Role byId = roleService.getById(id.getId());
        if (null!=id)byId.setDelFlag(1);
        boolean retFlag= roleService.updateById(byId);
        return BaseResult.success(retFlag);
    }
    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除角色信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        UpdateWrapper<Role> update = Wrappers.update();
        update.set("del_falg",1).in("id",list);
        boolean retFlag= roleService.update(update);
        return BaseResult.success(retFlag);
    }
}