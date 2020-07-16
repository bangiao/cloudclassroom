package com.dingxin.cloudclassroom.web.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dingxin.cloudclassroom.entity.UserRole;
import com.dingxin.cloudclassroom.service.IUserRoleService;
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
* 用户与角色对应关系
*/
@RestController
@RequestMapping("/userRole")
@Api(value = "用户与角色对应关系接口")
public class UserRoleController {


    @Autowired
    private IUserRoleService iUserRoleService;

    @Autowired
    private MessageSource messageSource;

    /**
     * 列表
     */
    @GetMapping
    //@RequiresPermissions("userRole:list" )
    @ApiOperation(value = "获取用户与角色对应关系列表" )
    public BaseResult<Page<UserRole>>  list(BaseQuery baseQuery){
        BaseResult<Page<UserRole>> baseResult = new BaseResult<>();
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        Page pageList=iUserRoleService.selectPage(page,new EntityWrapper<UserRole>());
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
    //@RequiresPermissions("userRole:info" )
    @ApiOperation(value = "获取用户与角色对应关系详情信息" )
    public BaseResult<UserRole> info(@PathVariable("id" ) Integer id){
        BaseResult<UserRole> baseResult = new BaseResult<>();

        UserRole userRole = iUserRoleService.selectById(id);
        if (userRole == null) {
            return baseResult.notFound();
        }
        baseResult.setData(userRole);
        return baseResult;
    }

    /**
     * 保存
     */
    @PostMapping
    //@RequiresPermissions("userRole:save" )
    @ApiOperation(value = "新增用户与角色对应关系信息" )
    public BaseResult save(@RequestBody  UserRole userRole){

        BaseResult<UserRole> baseResult = new BaseResult<>();

        boolean retFlag = iUserRoleService.insert(userRole);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        baseResult.setData(userRole);
        return baseResult;
    }

    /**
     * 修改
     */
    @PutMapping
    //@RequiresPermissions("userRole:update" )
    @ApiOperation(value = "修改用户与角色对应关系信息" )
    public BaseResult update(@RequestBody @PathVariable("userRole" ) UserRole userRole){
        BaseResult<UserRole> baseResult = new BaseResult<>();

        boolean retFlag = iUserRoleService.updateById(userRole);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        baseResult.setData(userRole);
        return baseResult;
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}" )
    //@RequiresPermissions("userRole:delete" )
    @ApiOperation(value = "删除用户与角色对应关系信息" )
    public BaseResult delete(@PathVariable("id" ) Integer id){
        BaseResult<UserRole> baseResult = new BaseResult<>();
        boolean retFlag = iUserRoleService.deleteById(id);
        if (!retFlag) {
            return baseResult.failed(messageSource.getMessage("10001", null, Locale.CHINESE));
        }
        return baseResult;
    }
}
