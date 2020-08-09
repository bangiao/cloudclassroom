package com.dingxin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.CasEmploys;

import java.util.List;

/**
 *  服务接口
 */
public interface ICasEmploysService extends IService<CasEmploys> {

    List<CasEmploys> like(CasEmploys data);


    /**
     * 根据部门id查询下面所有人员信息
     * @param id 部门id
     * @return
     */
    List<CasEmploys> selectByDeptId(Integer id);
}
