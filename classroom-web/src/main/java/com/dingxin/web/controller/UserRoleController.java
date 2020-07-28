package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.UserRoleDTO;
import com.dingxin.web.service.IUserRoleService;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户与角色对应关系
 */
@ManTag
@RestController
@RequestMapping("/userRole")
@Api(value = "用户与角色对应关系接口")
public class UserRoleController {


    @Autowired
    private IUserRoleService userRoleService;


    /**
     * 根据用户id获取菜单
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "根据登录用户获取菜单列表")
    public BaseResult list(){
        List<Menu> list =userRoleService.getMenus();
        return BaseResult.success();
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取用户与角色对应关系详情信息")
    public BaseResult<UserRole> search(@RequestBody  UserRole userRole){
        UserRole result = userRoleService.getOne(Wrappers.query(userRole));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增用户与角色对应关系信息")
    public BaseResult save(@RequestBody UserRoleDTO userRole){
        QueryWrapper<UserRole> casUser_id = Wrappers.query();
        casUser_id.eq("casUser_id", userRole.getCasUserId());
        userRoleService.remove(casUser_id);
        ArrayList<UserRole> userRoles = Lists.newArrayList();
        List<Integer> roles = userRole.getRoles();
        roles.forEach(e->{
            UserRole build = UserRole.builder().casUserId(userRole.getCasUserId()).roleId(e).build();
            userRoles.add(build);
        });
        boolean retFlag= userRoleService.saveOrUpdateBatch(userRoles);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改用户与角色对应关系信息")
    public BaseResult update(@RequestBody UserRole userRole){
        boolean retFlag= userRoleService.updateById(userRole);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除用户与角色对应关系信息")
    public BaseResult delete(@RequestBody IdRequest id){
        UserRole byId = userRoleService.getById(id.getId());
        if (null!=byId)byId.setDelFlag(1);
        boolean retFlag= userRoleService.updateById(byId);
        return BaseResult.success(retFlag);
    }
    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除用户与角色对应关系信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        UpdateWrapper<UserRole> update = Wrappers.update();
        update.set("del_falg",1).in("id",list);
        boolean retFlag= userRoleService.update(update);
        return BaseResult.success(retFlag);
    }
}