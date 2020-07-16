package com.dingxin.cloudclassroom.service;
import com.dingxin.cloudclassroom.entity.Menu;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

/**
 * 菜单管理 服务接口
 */
public interface IMenuService extends IService<Menu> {

    Set<String> getCasUserPermission(String id);
}
