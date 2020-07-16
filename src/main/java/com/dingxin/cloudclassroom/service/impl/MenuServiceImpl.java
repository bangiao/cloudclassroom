package com.dingxin.cloudclassroom.service.impl;
import com.dingxin.cloudclassroom.entity.Menu;
import com.dingxin.cloudclassroom.mapper.MenuMapper;
import com.dingxin.cloudclassroom.service.IMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 菜单管理 服务接口实现类
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public Set<String> getCasUserPermission(String id) {
        return menuMapper.getCasUserPermissions(id);
    }
}
