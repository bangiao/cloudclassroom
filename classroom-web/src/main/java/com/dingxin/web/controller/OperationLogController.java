package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.OperationWatcher;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.OperationLog;
import com.dingxin.pojo.po.VideoAudit;
import com.dingxin.web.service.IOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

/**
 * 
 */
@ManTag
@RestController
@RequestMapping("/operationLog")
@Api(tags = "操作日志接口")
public class OperationLogController {


    @Autowired
    private IOperationLogService operationLogService;


    @PostMapping("/list")
    @ApiOperation(value = "获取所有列表")
    @OperationWatcher(operateDesc = "获取所有日志列表")
    public BaseResult<Page<OperationLog>>list(@RequestBody BaseQuery<OperationLog> query){
        Page<OperationLog> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = operationLogService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 列表条件及模糊查询
     */
    @PostMapping("/list/query")
    @ApiOperation(value = "条件查询")
    @OperationWatcher(operateDesc = "获取满足条件的所有日志列表")
    public BaseResult<Page<OperationLog>>listByQuery(@RequestBody BaseQuery<OperationLog> query){
        //查询列表数据
        Page<OperationLog> page = new Page(query.getCurrentPage(),query.getPageSize());
//        IPage pageList = operationLogService.page(page,Wrappers.query(query.getData()));
        OperationLog operationLogQuery = query.getData();
        if (operationLogQuery!=null){
            LambdaQueryWrapper<OperationLog> optQuery = new QueryWrapper<OperationLog>().lambda()
                    .ge(
                            Objects.nonNull(operationLogQuery.getStartTime()),
                            OperationLog::getOperateTime,
                            DateUtils.localDateTimeToLong(operationLogQuery.getStartTime()))
                    .le(
                            Objects.nonNull(operationLogQuery.getEndTime()),
                            OperationLog::getOperateTime,
                            DateUtils.localDateTimeToLong(operationLogQuery.getEndTime()))
                    .or().like(Objects.nonNull(operationLogQuery.getOperateUsername()),
                            OperationLog::getOperateUsername,
                            operationLogQuery.getOperateUsername());
            IPage pageList = operationLogService.page(page,optQuery);
            if(CollectionUtils.isEmpty(pageList.getRecords())){
                return BaseResult.success();
            }
            return BaseResult.success(pageList);
        }
        return BaseResult.failed(ExceptionEnum.PARAMTER_ERROR);

    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<OperationLog> search(@RequestBody  OperationLog operationLog){
        OperationLog result = operationLogService.getOne(Wrappers.query(operationLog));
        return BaseResult.success(result);
    }

//    /**
//     * 保存
//     */
//    @PostMapping
//    @ApiOperation(value = "新增信息")
//    public BaseResult save(@RequestBody  OperationLog operationLog){
//        boolean retFlag= operationLogService.save(operationLog);
//        return BaseResult.success(retFlag);
//    }

//    /**
//     * 修改
//     */
//    @PostMapping("/update")
//    @ApiOperation(value = "修改信息")
//    public BaseResult update(@RequestBody OperationLog operationLog){
//        boolean retFlag= operationLogService.updateById(operationLog);
//        return BaseResult.success(retFlag);
//    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody OperationLog operationLog){
        boolean retFlag= operationLogService.remove(Wrappers.query(operationLog));
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "删除信息")
    public BaseResult batchDelete(@RequestBody List<Integer> operationLogs){
        boolean retFlag= operationLogService.removeByIds(operationLogs);
        return BaseResult.success(retFlag);
    }
}