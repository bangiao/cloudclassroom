package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.dao.mapper.UserRoleMapper;
import com.dingxin.pojo.vo.Id;
import com.dingxin.web.service.IMenuService;
import com.dingxin.web.service.IRoleMenuService;
import com.dingxin.web.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
                    data.getCasUserId())
;
        return userRoleMapper.selectList(query);


    }

    /**
     * 根据用户id获取菜单
     * @param id 用户id可从shrio中拿
     * @return
     */
    @Override
    public List<Menu> getMenus() {
        List<Menu> menus = Lists.newArrayList();
        String userId = ShiroUtils.getUserId();
        Assert.notNull(userId,"Login information detection failed");
        QueryWrapper<UserRole> qu = Wrappers.query();
        qu.eq("casUser_id",userId);
        List<Integer> roleIds = list(qu).stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList());
        if (roleIds.size()>0){
            QueryWrapper<RoleMenu> qrm = Wrappers.query();
            qrm.in("role_id",roleIds);
            List<Integer> menuList = roleMenuService.list(qrm).stream().map(RoleMenu::getMenuId).distinct().collect(Collectors.toList());
            if (menuList.size()>0){
                QueryWrapper<Menu> qm = Wrappers.query();
                qm.in("id",menuList).eq("del_falg",0).orderByAsc("order_num");
                menus = menuService.list(qm);
            }
        }
        return menus;
    }

}
