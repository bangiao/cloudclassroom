package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.OperationLog;
import com.dingxin.dao.mapper.OperationLogMapper;
import com.dingxin.web.service.IOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;


    @Override
    public List<OperationLog> like(OperationLog data) {
        LambdaQueryWrapper<OperationLog> query = Wrappers.<OperationLog>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    OperationLog::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getUserId()),
                    OperationLog::getUserId,
                    data.getUserId())
                .like(
                    Objects.nonNull(data.getOperateTime()),
                    OperationLog::getOperateTime,
                    data.getOperateTime())
                .like(
                    Objects.nonNull(data.getOperateDesc()),
                    OperationLog::getOperateDesc,
                    data.getOperateDesc())
                .like(
                    Objects.nonNull(data.getIpAddress()),
                    OperationLog::getIpAddress,
                    data.getIpAddress())
;
        return operationLogMapper.selectList(query);


    }

}
