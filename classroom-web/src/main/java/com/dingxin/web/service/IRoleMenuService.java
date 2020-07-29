package com.dingxin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.RoleMenu;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.RoleMenuInsertRequest;

import java.util.List;

/**
 * 角色与菜单对应关系 服务接口
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    List<RoleMenu> like(RoleMenu data);

    /**
     * 角色对应的所有菜单 包含选中的值
     * @param id
     * @return
     */
    List<Menu>  allMenu(IdRequest id);

    /**
     * 通过角色保存菜单
     * @param request
     * @return
     */
    boolean insertMenusByRole(RoleMenuInsertRequest request);
}
