package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseQuery4List;
import com.dingxin.pojo.po.OperationLog;
import com.dingxin.pojo.request.OperationLogRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface IOperationLogService extends IService<OperationLog> {

    List<OperationLog> like(OperationLog data);

    IPage<OperationLog> queryPageAll(BaseQuery4List query);

    IPage<OperationLog> queryPage(BaseQuery<OperationLogRequest> query);

}
