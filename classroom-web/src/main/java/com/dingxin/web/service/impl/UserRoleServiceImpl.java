package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.dao.mapper.UserRoleMapper;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.pojo.request.UserRoleInsertRequest;
import com.dingxin.web.service.IMenuService;
import com.dingxin.web.service.IRoleMenuService;
import com.dingxin.web.service.IUserRoleService;
import com.dingxin.web.shiro.ShiroUtils;
import com.google.common.collect.Lists;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户与角色对应关系 服务接口实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleMenuService roleMenuService;


    @Override
    public List<UserRole> like(UserRole data) {
        LambdaQueryWrapper<UserRole> query = Wrappers.<UserRole>lambdaQuery()
                .like(
                        Objects.nonNull(data.getId()),
                        UserRole::getId,
                        data.getId())
                .like(
                        Objects.nonNull(data.getRoleId()),
                        UserRole::getRoleId,
                        data.getRoleId())
                .like(
                        Objects.nonNull(data.getCasUserId()),
                        UserRole::getCasUserId,
                        data.getCasUserId());
        return userRoleMapper.selectList(query);


    }

    /**
     * 根据用户id获取菜单
     * 用户id可从shrio中拿
     *
     * @return
     */
    @Override
    public List<Menu> getMenus() {
        List<Menu> menus = Lists.newArrayList();
        String userId = ShiroUtils.getUserId();
        Assert.notNull(userId, "Login information detection failed");
        LambdaQueryWrapper<UserRole> qu = Wrappers.lambdaQuery();
        qu.eq(UserRole::getCasUserId, userId);
        List<Integer> roleIds = list(qu).stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList());
        if (roleIds.size() > 0) {
            LambdaQueryWrapper<RoleMenu> qrm = Wrappers.lambdaQuery();
            qrm.in(RoleMenu::getRoleId, roleIds);
            List<Integer> menuList = roleMenuService.list(qrm).stream().map(RoleMenu::getMenuId).distinct().collect(Collectors.toList());
            if (menuList.size() > 0) {
                LambdaQueryWrapper<Menu> qm = Wrappers.lambdaQuery();
                qm.in(Menu::getId, menuList).eq(Menu::getDelFlag, CommonConstant.DEL_FLAG).orderByAsc(Menu::getOrderNum);
                menus = menuService.list(qm);
            }
        }
        return menus;
    }

    /**
     * 新增角色与菜单的关系
     *
     * @param userRole
     * @return
     */
    @Override
    public boolean saveSelf(UserRoleInsertRequest userRole) {
        LambdaQueryWrapper<UserRole> casUser_id = Wrappers.lambdaQuery();
        casUser_id.eq(UserRole::getCasUserId, userRole.getCasUserId());
        remove(casUser_id);
        ArrayList<UserRole> userRoles = Lists.newArrayList();
        List<Integer> roles = userRole.getRoles();
        roles.forEach(e -> {
            UserRole build = UserRole.builder().casUserId(userRole.getCasUserId()).roleId(e).build();
            userRoles.add(build);
        });
        return saveBatch(userRoles);
    }

    /**
     * 批量删除用户的角色
     *
     * @param request
     * @return
     */
    @Override
    public boolean deleteBatch(UserRoleInsertRequest request) {
        LambdaQueryWrapper<UserRole> qw = Wrappers.lambdaQuery();
        qw.eq(UserRole::getCasUserId, request.getCasUserId())
                .in(UserRole::getRoleId, request.getRoles());
        return remove(qw);
    }


}
