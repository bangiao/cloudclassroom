package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.dao.mapper.RoleMenuMapper;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.RoleMenuInsertRequest;
import com.dingxin.web.service.IMenuService;
import com.dingxin.web.service.IRoleMenuService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色与菜单对应关系 服务接口实现类
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private IMenuService menuService;


    @Override
    public List<RoleMenu> like(RoleMenu data) {
        LambdaQueryWrapper<RoleMenu> query = Wrappers.<RoleMenu>lambdaQuery()
                .like(
                        Objects.nonNull(data.getId()),
                        RoleMenu::getId,
                        data.getId())
                .like(
                        Objects.nonNull(data.getRoleId()),
                        RoleMenu::getRoleId,
                        data.getRoleId())
                .like(
                        Objects.nonNull(data.getMenuId()),
                        RoleMenu::getMenuId,
                        data.getMenuId());
        return roleMenuMapper.selectList(query);


    }

    /**
     * 角色对应的所有菜单 包含选中的值
     *
     * @param id
     * @return
     */
    @Override
    public List<Menu> allMenu(IdRequest id) {
        LambdaQueryWrapper<Menu> qm = Wrappers.lambdaQuery();
        qm.eq(Menu::getDelFlag, CommonConstant.DEL_FLAG);
        LambdaQueryWrapper<RoleMenu> qr = Wrappers.lambdaQuery();
        qr.eq(RoleMenu::getId, id.getId());
        List<Integer> collect = list(qr).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Menu> menus = menuService.list(qm);
        for (Menu menu : menus) {
            menu.setCheck(collect.contains(menu.getId()) ? Boolean.TRUE : Boolean.FALSE);
        }
        return menus;
    }

    /**
     * 通过角色保存菜单
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertMenusByRole(RoleMenuInsertRequest request) {
        LambdaQueryWrapper<RoleMenu> qe = Wrappers.lambdaQuery();
        qe.eq(RoleMenu::getRoleId, request.getRoleId());
        remove(qe);
        List<Integer> menus = request.getMenus();
        ArrayList<RoleMenu> rms = Lists.newArrayList();
        menus.forEach(e -> {
            RoleMenu build = RoleMenu.builder().roleId(request.getRoleId()).menuId(e).build();
            rms.add(build);
        });
        return saveBatch(rms);
    }

}
