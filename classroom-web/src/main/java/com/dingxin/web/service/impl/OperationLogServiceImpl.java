package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.OperationLog;
import com.dingxin.dao.mapper.OperationLogMapper;
import com.dingxin.pojo.request.OperationLogRequest;
import com.dingxin.pojo.vo.OperationLogVo;
import com.dingxin.web.service.IOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .like(
                    Objects.nonNull(data.getOperateUsername()),
                    OperationLog::getOperateUsername,
                    data.getOperateUsername())
;
        return operationLogMapper.selectList(query);


    }

    @Override
    public IPage<OperationLogVo> queryPageAll(BaseQuery<OperationLogRequest> query) {

        Page<OperationLog> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage<OperationLogVo> operationLogVo = new Page<OperationLogVo>();
        IPage<OperationLog> operationLogIPage = operationLogMapper.selectPage(page, Wrappers.query());

        return OperationLogVo.convertToVoWithPage(operationLogVo,operationLogIPage);
    }

    @Override
    public IPage<OperationLogVo> queryPage(BaseQuery<OperationLogRequest> query) {
        //条件查询列表数据
        Page<OperationLog> page = new Page<OperationLog>(query.getCurrentPage(), query.getPageSize());
        OperationLogRequest operationLogQuery = query.getData();
        if (operationLogQuery != null){
            LambdaQueryWrapper<OperationLog> optQuery = new QueryWrapper<OperationLog>().lambda()
                        .ge(
                                operationLogQuery.getStartTime()!=null,
                                OperationLog::getOperateTime,
                                DateUtils.localDateTimeToLong(operationLogQuery.getStartTime()))
                        .le(
                                operationLogQuery.getEndTime() != null,
                                OperationLog::getOperateTime,
                                DateUtils.localDateTimeToLong(operationLogQuery.getEndTime()))
                        .or().like(StringUtils.isNotBlank(operationLogQuery.getOperateUsername()),
                                OperationLog::getOperateUsername,
                                operationLogQuery.getOperateUsername());

            IPage<OperationLog> operationLogPo = operationLogMapper.selectPage(page, optQuery);
            IPage<OperationLogVo> operationLogVo = new Page<OperationLogVo>();

            return OperationLogVo.convertToVoWithPage(operationLogVo,operationLogPo);
        }
        return null;
    }
}
