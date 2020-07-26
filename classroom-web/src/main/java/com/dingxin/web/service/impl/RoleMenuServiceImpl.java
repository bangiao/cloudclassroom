package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.dao.mapper.RoleMenuMapper;
import com.dingxin.pojo.vo.Id;
import com.dingxin.web.service.IMenuService;
import com.dingxin.web.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                    data.getMenuId())
;
        return roleMenuMapper.selectList(query);


    }
    /**
     * 角色对应的所有菜单 包含选中的值
     * @param id
     * @return
     */
    @Override
    public List<Menu> allMenu(Id id) {
        QueryWrapper<Menu> qm = Wrappers.query();
        qm.eq("del_falg",0);
        QueryWrapper<RoleMenu> qr = Wrappers.query();
        qr.eq("id", id.getId());
        List<Integer> collect = list(qr).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Menu> menus = menuService.list(qm);
        for (Menu menu : menus) {
            menu.setCheck(collect.contains(menu.getId()) ? Boolean.TRUE: Boolean.FALSE);
        }
        return menus;
    }

}
