package com.dingxin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.UserRole;
import com.dingxin.pojo.request.IdRoleRequest;
import com.dingxin.pojo.request.RoleIdRequest;
import com.dingxin.pojo.request.UserRoleInsertRequest;
import com.dingxin.pojo.vo.TreeVo;

import java.util.List;

/**
 * 用户与角色对应关系 服务接口
 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> like(UserRole data);

    /**
     * 根据用户id获取菜单
     *
     * @return
     */
    List<Menu> getMenus();

    /**
     * 新增角色与菜单的关系
     *
     * @param userRole
     * @return
     */
    boolean saveSelf(UserRoleInsertRequest userRole);

    /**
     * 批量删除用户的角色
     *
     * @param request
     * @return
     */
    boolean deleteBatch(UserRoleInsertRequest request);


    /**
     * 部门树状结构
     * @return
     */
    List<TreeVo> depts();

    /**
     * 根据所选节点返回人员信息
     * @param id
     * @return
     */
    List<CasEmploys> employs(IdRoleRequest id);

    /**
     * 获取角色对应的人员列表
     * @param id
     * @return
     */
    List<CasEmploys> havaPwoerList(RoleIdRequest id);

    /**
     * 根据登录人获取对应的菜单信息
     * @return
     */

    List<Menu> menus();

    /**
     * 通过角色id获取角色下的人员id
     * @param roleId
     * @return
     */
    List<String> selectUserIdsByRoleId(Integer roleId);
}
