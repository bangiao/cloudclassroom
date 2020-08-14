package com.dingxin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.pojo.request.IdRoleRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface ICasEmploysService extends IService<CasEmploys> {

    List<CasEmploys> like(CasEmploys data);


    /**
     * 根据部门id查询下面所有人员信息
     * @param idRoleRequest 部门id
     * @return
     */
    List<CasEmploys> selectByDeptId(IdRoleRequest idRoleRequest);

    /**
     * 通过用户ids 获取用户
     * @param collect
     * @return
     */
    List<CasEmploys> queryByIds(List<String> collect);
}
