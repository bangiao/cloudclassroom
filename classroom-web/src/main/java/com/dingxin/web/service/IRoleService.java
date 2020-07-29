package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Role;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;

/**
 * 角色 服务接口
 */
public interface IRoleService extends IService<Role> {

    List<Role> like(Role data);

    /**
     * 查询角色列表
     *
     * @param query
     * @return
     */
    IPage<Role> queryPage(CommQueryListRequest query);

    /**
     * 查询单个角色
     *
     * @param idRequest
     * @return
     */
    Role getOneSelf(IdRequest idRequest);

    /**
     * 新增或修改橘色信息
     *
     * @param convent
     * @return
     */
    int saveSelf(Role convent);

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    boolean delete(IdRequest id);

    /**
     * 批量删除角色
     *
     * @param list
     * @return
     */
    boolean deleteBatch(List<Integer> list);
}
