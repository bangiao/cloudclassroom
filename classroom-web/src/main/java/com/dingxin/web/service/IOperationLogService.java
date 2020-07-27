package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.OperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.request.OperationLogRequest;
import com.dingxin.pojo.vo.OperationLogVo;

import java.util.List;

/**
 *  服务接口
 */
public interface IOperationLogService extends IService<OperationLog> {

    List<OperationLog> like(OperationLog data);

    IPage<OperationLogVo> queryPageAll(BaseQuery<OperationLogRequest> query);

    IPage<OperationLogVo> queryPage(BaseQuery<OperationLogRequest> query);

}
