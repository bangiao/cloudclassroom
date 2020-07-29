package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.RoleMenuInsertRequest;
import com.dingxin.pojo.vo.MenuVo;
import com.dingxin.web.service.IRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/listAll")
    @ApiOperation(value = "菜单所有菜单且被选中的也标记")
    public BaseResult<Page<RoleMenu>> list(@RequestBody IdRequest id) {
        List<Menu> menus = roleMenuService.allMenu(id);
        IPage<Menu> page = new Page<>();
        page.setRecords(menus);
        IPage<MenuVo> menuVoIPage = MenuVo.convertToVoWithPage(page);
        return BaseResult.success(menuVoIPage.getRecords());
    }

    /**
     * 保存  真删除
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增角色与菜单对应关系信息")
    public BaseResult save(@RequestBody RoleMenuInsertRequest request) {
        boolean retFlag = roleMenuService.insertMenusByRole(request);
        return BaseResult.success(retFlag);
    }
}