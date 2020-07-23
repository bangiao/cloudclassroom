package com.dingxin.web.service;
import com.dingxin.pojo.po.OperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IOperationLogService extends IService<OperationLog> {

    List<OperationLog> like(OperationLog data);

}
