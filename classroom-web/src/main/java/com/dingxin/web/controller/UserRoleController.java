package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.pojo.request.IdRoleRequest;
import com.dingxin.pojo.request.RoleIdRequest;
import com.dingxin.pojo.request.UserRoleInsertRequest;
import com.dingxin.pojo.vo.EmploysRoleVo;
import com.dingxin.pojo.vo.MenuVo;
import com.dingxin.pojo.vo.TreeVo;
import com.dingxin.web.service.IUserRoleService;
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
 * 用户与角色对应关系
 */
@ManTag
@RestController
@RequestMapping("/userRole")
@Api(tags = "用户与角色对应关系接口")
public class UserRoleController {


    @Autowired
    private IUserRoleService userRoleService;


    /**
     * 根据用户id获取菜单
     *
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "根据登录用户获取菜单列表")
    public BaseResult list() {
        List<Menu> list = userRoleService.getMenus();
        IPage<Menu> page = new Page<>();
        page.setRecords(list);
        IPage<MenuVo> menuVoIPage = MenuVo.convertToVoWithPage(page);
        return BaseResult.success(menuVoIPage.getRecords());
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取用户与角色对应关系详情信息")
    public BaseResult<UserRole> search(@RequestBody UserRole userRole) {
        UserRole result = userRoleService.getOne(Wrappers.query(userRole));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增用户与角色对应关系信息")
    public BaseResult save(@Validated @RequestBody UserRoleInsertRequest userRole) {
        boolean retFlag = userRoleService.saveSelf(userRole);
        return BaseResult.success(retFlag);
    }
    /**
     * 保存
     */
    @PostMapping("/havaPwoerList")
    @ApiOperation(value = "角色对应人员权限 ")
    public BaseResult havaPwoerList(@Validated @RequestBody RoleIdRequest id) {
        List<CasEmploys> list =userRoleService.havaPwoerList(id);
        List<EmploysRoleVo> employsRoleVos=null;
        if (CollectionUtils.isNotEmpty(list)){
            employsRoleVos = EmploysRoleVo.listConvent(list);
        }
        return BaseResult.success(employsRoleVos);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除用户与角色对应关系信息")
    public BaseResult deleteBatch(@Validated @RequestBody UserRoleInsertRequest request) {
        boolean retFlag = userRoleService.deleteBatch(request);
        return BaseResult.success(retFlag);
    }

    /**
     * 部门列表 树状结构
     */
    @PostMapping("/depts")
    @ApiOperation(value = "部门列表 树状结构")
    public BaseResult depts() {
        List<TreeVo> list = userRoleService.depts();
        return BaseResult.success(list);
    }

    /**
     * 部门列表 树状结构
     */
    @PostMapping("/employs")
    @ApiOperation(value = "部门下人员数据")
    public BaseResult employs(@Validated @RequestBody IdRoleRequest id) {
        List<CasEmploys> list = userRoleService.employs(id);
        List<EmploysRoleVo> employsRoleVos=null;
        if (CollectionUtils.isNotEmpty(list)) {
            employsRoleVos= EmploysRoleVo.listConvent(list);
        }
        return BaseResult.success(employsRoleVos);
    }

}