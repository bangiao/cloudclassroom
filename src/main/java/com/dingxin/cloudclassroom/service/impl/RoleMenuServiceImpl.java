package com.dingxin.cloudclassroom.service.impl;
import com.dingxin.cloudclassroom.entity.RoleMenu;
import com.dingxin.cloudclassroom.mapper.RoleMenuMapper;
import com.dingxin.cloudclassroom.service.IRoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色与菜单对应关系 服务接口实现类
 */
@Service
@Transactional
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
