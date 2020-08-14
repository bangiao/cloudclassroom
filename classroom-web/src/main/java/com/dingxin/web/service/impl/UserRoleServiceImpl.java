package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.UserRoleMapper;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.pojo.request.IdRoleRequest;
import com.dingxin.pojo.request.RoleIdRequest;
import com.dingxin.pojo.request.UserRoleInsertRequest;
import com.dingxin.pojo.vo.TreeVo;
import com.dingxin.web.service.*;
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
    @Autowired
    private ICasDeptsService deptsService;
    @Autowired
    private ICasEmploysService employsService;


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
        casUser_id.eq(UserRole::getCasUserId, userRole.getSid())
        .eq(UserRole::getRoleId,userRole.getRoleId());
        remove(casUser_id);
        ArrayList<UserRole> userRoles = Lists.newArrayList();
        List<String> users = userRole.getSid();
        users.forEach(e -> {
            UserRole build = UserRole.builder().casUserId(e).roleId(userRole.getRoleId()).build();
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
        qw.eq(UserRole::getRoleId,request.getRoleId())
                .in(CollectionUtils.isNotEmpty(request.getSid()),UserRole::getCasUserId, request.getSid());
        return remove(qw);
    }

    /**
     * 部门树状结构
     * @return
     */
    @Override
    public List<TreeVo> depts() {
    /*    String userId = ShiroUtils.getUserId();
        LambdaQueryWrapper<UserRole> qw = Wrappers.lambdaQuery();
        qw.eq(UserRole::getCasUserId,"70000281");
        int count = count(qw);

        if (count==0){
            throw new BusinessException(ExceptionEnum.USER_NOT_CAN_BE_QUERY_USER);
        }*/
        List<String> departmentCodes = ShiroUtils.getDepartmentCodes();

        List<TreeVo> treeVoList =deptsService.queryTree(null);


        return treeVoList;
    }

    /**
     * 根据所选节点返回人员信息
     * @param id
     * @return
     */

    @Override
    public List<CasEmploys> employs(IdRoleRequest id) {

        return employsService.selectByDeptId(id);
    }

    /**
     * 获取角色对应的人员列表
     * @param id
     * @return
     */

    @Override
    public List<CasEmploys> havaPwoerList(RoleIdRequest id) {
        LambdaQueryWrapper<UserRole> qw = Wrappers.lambdaQuery();
        qw.eq(UserRole::getRoleId,id.getRoleId()).eq(UserRole::getDelFlag,CommonConstant.DEL_FLAG)
                .select(UserRole::getCasUserId);
        List<String> collect = list(qw).stream().map(userRole -> userRole.getCasUserId()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)){
            return null;
        }

        return employsService.queryByIds(collect);
    }

    /**
     * 根据登录人获取人员的菜单列表
     * @return
     */

    @Override
    public List<Menu> menus() {
        String userId = ShiroUtils.getUserId();
        if (StringUtils.isEmpty(userId)){
            throw new BusinessException(ExceptionEnum.PRIVILEGE_GET_USER_FAIL);
        }

        LambdaQueryWrapper<UserRole> qw = Wrappers.lambdaQuery();
        qw.eq(UserRole::getCasUserId,userId)
                .eq(UserRole::getDelFlag,CommonConstant.DEL_FLAG)
                .select(UserRole::getRoleId);
        List<Integer> collect = list(qw).stream().map(userRole -> userRole.getRoleId()).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)){
            throw new BusinessException(ExceptionEnum.USER_NOT_CAN_BE_QUERY_USER);
        }

        return roleMenuService.selectMenus(collect);

    }
    /**
     * 通过角色id获取角色下的人员id
     * @param roleId
     * @return
     */

    @Override
    public List<String> selectUserIdsByRoleId(Integer roleId) {
        if (Objects.isNull(roleId)){
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaQueryWrapper<UserRole> qw = Wrappers.lambdaQuery();
        qw.eq(UserRole::getRoleId,roleId);
        return list(qw).stream().map(userRole -> userRole.getCasUserId()).distinct().collect(Collectors.toList());


    }


}
