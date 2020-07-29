package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Role;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.RoleInsertRequest;
import com.dingxin.pojo.vo.RoleVo;
import com.dingxin.web.service.IRoleService;
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
    public BaseResult<Page<Role>> list(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        IPage<Role> pageList = roleService.queryPage(query);
        return BaseResult.success(RoleVo.convertToVoWithPage(pageList));
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取角色详情信息")
    public BaseResult<Role> search(@Validated @RequestBody IdRequest idRequest) {
        Role result = roleService.getOneSelf(idRequest);
        return BaseResult.success(RoleVo.convent(result));
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增角色信息")
    public BaseResult save(@Validated @RequestBody RoleInsertRequest request) {
        Role convent = RoleInsertRequest.convent(request);
        int con = roleService.saveSelf(convent);
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
    @ApiOperation(value = "删除角色信息")
    public BaseResult delete(@Validated @RequestBody IdRequest id) {
        boolean retFlag = roleService.delete(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除角色信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        boolean retFlag = roleService.deleteBatch(list);
        return BaseResult.success(retFlag);
    }
}