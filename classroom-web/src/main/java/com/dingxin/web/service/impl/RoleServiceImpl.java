package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.RoleMapper;
import com.dingxin.pojo.po.Role;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IRoleMenuService;
import com.dingxin.web.service.IRoleService;
import com.dingxin.web.service.IUserRoleService;
import com.dingxin.web.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 角色 服务接口实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;


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
                        data.getSort());
        return roleMapper.selectList(query);


    }

    /**
     * 查询角色列表
     *
     * @param query
     * @return
     */
    @Override
    public IPage<Role> queryPage(CommQueryListRequest query) {
        LambdaQueryWrapper<Role> qw = Wrappers.lambdaQuery();
        if (StringUtils.isNotEmpty(query.getQueryStr())) {
            qw.select(Role::getId, Role::getRoleName, Role::getRemark, Role::getCreateTime, Role::getCreateUserName).eq(Role::getRemark, query.getQueryStr()).or()
                    .eq(Role::getRoleName, query.getQueryStr());
        }
        qw.eq(Role::getDelFlag, CommonConstant.DEL_FLAG);
        Page<Role> page = new Page(query.getCurrentPage(), query.getPageSize());
        return page(page, qw);

    }

    /**
     * 获取单个角色
     *
     * @param idRequest
     * @return
     */
    @Override
    public Role getOneSelf(IdRequest idRequest) {
        LambdaQueryWrapper<Role> qw = Wrappers.lambdaQuery();
        qw.eq(Role::getId, idRequest.getId()).eq(Role::getDelFlag, CommonConstant.DEL_FLAG);
        return getOne(qw);
    }

    /**
     * 新增或修改角色信息
     *
     * @param convent
     * @return
     */
    @Override
    public boolean saveSelf(Role convent) {
        LambdaQueryWrapper<Role> qw = Wrappers.lambdaQuery();
        qw.eq(Role::getId, convent.getId()).eq(Role::getDelFlag, CommonConstant.DEL_FLAG)
                .eq(Role::getRoleName, convent.getRoleName())
                .eq(Role::getRemark,convent.getRemark());
        int count = count(qw);
        if (count >=1) {
            throw new BusinessException(ExceptionEnum.DUPLICATE_DATA);
        }
        convent.setModifyTime(LocalDateTime.now());
        convent.setCreateUserId(ShiroUtils.getUserId());
        convent.setCreateUserName(ShiroUtils.getUserName());
        return saveOrUpdate(convent);

    }

    /**
     * 删除角色信息
     *  真删除
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(IdRequest id) {
        LambdaUpdateWrapper<Role> qw = Wrappers.lambdaUpdate();
        qw.set(Role::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getId()), Role::getId, id.getId());
//        删除角色对应的菜单信息
        LambdaQueryWrapper<RoleMenu> qe = Wrappers.lambdaQuery();
        qe.eq(RoleMenu::getRoleId, id.getId());
        roleMenuService.remove(qe);
//        删除角色对应的用户信息
        LambdaQueryWrapper<UserRole> casUser_id = Wrappers.lambdaQuery();
        casUser_id.eq(UserRole::getRoleId, id.getId());
        userRoleService.remove(casUser_id);
        return remove(qw);
    }

    /**
     * 批量删除角色
     *
     * @param list
     * @return
     */
    @Override
    public boolean deleteBatch(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        LambdaUpdateWrapper<Role> qw = Wrappers.lambdaUpdate();
        qw.set(Role::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(Role::getId, list);
        return update(qw);
    }

}
