package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface ITeachersService extends IService<Teachers> {

    List<Teachers> like(Teachers data);

    IPage<Teachers> queryPage(CommQueryListRequest query);

    IPage<Teachers> queryPCPage(CommQueryListRequest query);

    Teachers queryById(IdRequest idRequest);
}
