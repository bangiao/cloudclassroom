package com.dingxin.cloudclassroom.service.impl;
import com.dingxin.cloudclassroom.entity.Role;
import com.dingxin.cloudclassroom.mapper.RoleMapper;
import com.dingxin.cloudclassroom.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色 服务接口实现类
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
