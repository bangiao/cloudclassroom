package com.dingxin.cloudclassroom.service.impl;
import com.dingxin.cloudclassroom.entity.UserRole;
import com.dingxin.cloudclassroom.mapper.UserRoleMapper;
import com.dingxin.cloudclassroom.service.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户与角色对应关系 服务接口实现类
 */
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
