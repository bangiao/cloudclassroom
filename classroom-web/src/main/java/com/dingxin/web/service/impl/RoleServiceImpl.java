package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.Role;
import com.dingxin.dao.mapper.RoleMapper;
import com.dingxin.web.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * 角色 服务接口实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> like(Role data) {
        LambdaQueryWrapper<Role> query = Wrappers.<Role>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Role::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getRoleName()),
                    Role::getRoleName,
                    data.getRoleName())
                .like(
                    Objects.nonNull(data.getRemark()),
                    Role::getRemark,
                    data.getRemark())
                .like(
                    Objects.nonNull(data.getCreateUserId()),
                    Role::getCreateUserId,
                    data.getCreateUserId())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    Role::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getDicId()),
                    Role::getDicId,
                    data.getDicId())
                .like(
                    Objects.nonNull(data.getSort()),
                    Role::getSort,
                    data.getSort())
;
        return roleMapper.selectList(query);


    }

}
