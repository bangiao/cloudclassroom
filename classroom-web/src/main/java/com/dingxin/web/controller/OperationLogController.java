package com.dingxin.web.controller;
import com.dingxin.pojo.po.OperationLog;
import com.dingxin.web.annotaion.OperationWatcher;
import com.dingxin.web.service.IOperationLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

/**
 * 
 */
@RestController
@RequestMapping("/operationLog")
@Api(value = "接口")
public class OperationLogController {


    @Autowired
    private IOperationLogService operationLogService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    @OperationWatcher(operateDesc = "获取日志列表")
    public BaseResult<Page<OperationLog>>list(@RequestBody BaseQuery<OperationLog> query){
        //查询列表数据
        Page<OperationLog> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = operationLogService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
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

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  OperationLog operationLog){
        boolean retFlag= operationLogService.save(operationLog);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody OperationLog operationLog){
        boolean retFlag= operationLogService.updateById(operationLog);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete}")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody OperationLog operationLog){
        boolean retFlag= operationLogService.remove(Wrappers.query(operationLog));
        return BaseResult.success(retFlag);
    }
}