package com.dingxin.web.service;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.po.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.vo.Id;

import java.util.List;

/**
 * 用户与角色对应关系 服务接口
 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> like(UserRole data);

    /**
     * 根据用户id获取菜单
     * @return
     */
    List<Menu> getMenus();
}
