package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;

/**
 * 菜单管理 服务接口
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> like(Menu data);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    IPage<Menu> queryPage(CommQueryListRequest query);

    /**
     * 获取单个菜单
     *
     * @param idRequest
     * @return
     */
    Menu getOneSelf(IdRequest idRequest);

    /**
     * 保存或者修改
     *
     * @param convent
     * @return
     */
    boolean saveSelf(Menu convent);

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    boolean delete(IdRequest id);

    /**
     * 批量删除菜单
     *
     * @param list
     * @return
     */
    boolean deleteBatch(List<Integer> list);

    /**
     * 根据菜单id获取菜单
     * @param menus
     * @return
     */
    List<Menu> menus(List<Integer> menus);
}
