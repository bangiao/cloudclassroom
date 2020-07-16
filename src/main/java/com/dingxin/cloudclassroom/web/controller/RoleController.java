package com.dingxin.cloudclassroom.web.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dingxin.cloudclassroom.entity.Role;
import com.dingxin.cloudclassroom.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.dingxin.cloudclassroom.common.BaseResult;
import com.dingxin.cloudclassroom.common.BaseQuery;

import java.util.Locale;

/**
* 角色
*/
@RestController
@RequestMapping("/role")
@Api(value = "角色接口")
public class RoleController {


    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private MessageSource messageSource;

    /**
     * 列表
     */
    @GetMapping
    //@RequiresPermissions("role:list" )
    @ApiOperation(value = "获取角色列表" )
    public BaseResult<Page<Role>>  list(BaseQuery baseQuery){
        BaseResult<Page<Role>> baseResult = new BaseResult<>();
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        Page pageList=iRoleService.selectPage(page,new EntityWrapper<Role>());
        if (CollectionUtils.isEmpty(pageList.getRecords())) {
            return baseResult.notFound();
        }
        baseResult.setData(pageList);
        return baseResult;
    }

    /**
     * 信息
     */
    @GetMapping("/{id}" )
    //@RequiresPermissions("role:info" )
    @ApiOperation(value = "获取角色详情信息" )
    public BaseResult<Role> info(@PathVariable("id" ) Integer id){
        BaseResult<Role> baseResult = new BaseResult<>();

        Role role = iRoleService.selectById(id);
        if (role == null) {
            return baseResult.notFound();
        }
        baseResult.setData(role);
        return baseResult;
    }

    /**
     * 保存
     */
    @PostMapping
    //@RequiresPermissions("role:save" )
    @ApiOperation(value = "新增角色信息" )
    public BaseResult save(@RequestBody  Role role){

        BaseResult<Role> baseResult = new BaseResult<>();

        boolean retFlag = iRoleService.insert(role);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        baseResult.setData(role);
        return baseResult;
    }

    /**
     * 修改
     */
    @PutMapping
    //@RequiresPermissions("role:update" )
    @ApiOperation(value = "修改角色信息" )
    public BaseResult update(@RequestBody @PathVariable("role" ) Role role){
        BaseResult<Role> baseResult = new BaseResult<>();

        boolean retFlag = iRoleService.updateById(role);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        baseResult.setData(role);
        return baseResult;
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}" )
    //@RequiresPermissions("role:delete" )
    @ApiOperation(value = "删除角色信息" )
    public BaseResult delete(@PathVariable("id" ) Integer id){
        BaseResult<Role> baseResult = new BaseResult<>();
        boolean retFlag = iRoleService.deleteById(id);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        return baseResult;
    }
}
