package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.OperationWatcher;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.OperationLog;
import com.dingxin.pojo.request.OperationLogRequest;
import com.dingxin.pojo.vo.OperationLogVo;
import com.dingxin.web.service.IOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public BaseResult<Page<OperationLog>>list(@RequestBody BaseQuery query){

        IPage pageList = operationLogService.queryPageAll(query);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 列表条件及模糊查询
     */
    @PostMapping("/list/query")
    @ApiOperation(value = "列表条件查询")
    @OperationWatcher(operateDesc = "获取满足条件的所有日志列表")
    public BaseResult<Page<OperationLogVo>>listByQuery(@RequestBody BaseQuery<OperationLogRequest> query){

        IPage<OperationLogVo> pageList = operationLogService.queryPage(query);
        if (query.getData()!=null) {
            if(CollectionUtils.isEmpty(pageList.getRecords())){
                return BaseResult.success();
            }
            return BaseResult.success(pageList);
        }
        return BaseResult.success().setMsg("传参不能为空");

    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取单条数据详情信息")
    public BaseResult<OperationLog> search(@RequestBody  OperationLog operationLog){
        OperationLog result = operationLogService.getOne(Wrappers.query(operationLog));
        return BaseResult.success(result);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    @OperationWatcher(operateDesc = "删除单条日志")
    public BaseResult delete(@RequestBody OperationLog operationLog){
        boolean retFlag= operationLogService.remove(Wrappers.query(operationLog));
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除信息")
    @OperationWatcher(operateDesc = "批量删除日志")
    public BaseResult batchDelete(@RequestBody List<Integer> operationLogs){
        boolean retFlag= operationLogService.removeByIds(operationLogs);
        return BaseResult.success(retFlag);
    }
}