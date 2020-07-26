package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.Menu;
import com.dingxin.dao.mapper.MenuMapper;
import com.dingxin.web.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * 菜单管理 服务接口实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> like(Menu data) {
        LambdaQueryWrapper<Menu> query = Wrappers.<Menu>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Menu::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getParentId()),
                    Menu::getParentId,
                    data.getParentId())
                .like(
                    Objects.nonNull(data.getName()),
                    Menu::getName,
                    data.getName())
                .like(
                    Objects.nonNull(data.getUrl()),
                    Menu::getUrl,
                    data.getUrl())
                .like(
                    Objects.nonNull(data.getPerms()),
                    Menu::getPerms,
                    data.getPerms())
                .like(
                    Objects.nonNull(data.getType()),
                    Menu::getType,
                    data.getType())
                .like(
                    Objects.nonNull(data.getIcon()),
                    Menu::getIcon,
                    data.getIcon())
                .like(
                    Objects.nonNull(data.getOrderNum()),
                    Menu::getOrderNum,
                    data.getOrderNum())
;
        return menuMapper.selectList(query);


    }

}
