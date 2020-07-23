package com.dingxin.web.web.controller;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.web.service.IStduentClassSeeRecordService;
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
 * 学生记录表
 */
@RestController
@RequestMapping("/stduentClassSeeRecord")
@Api(value = "学生记录表接口")
public class StduentClassSeeRecordController {


    @Autowired
    private IStduentClassSeeRecordService stduentClassSeeRecordService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取学生记录表列表")
    public BaseResult<Page<StduentClassSeeRecord>>list(@RequestBody BaseQuery<StduentClassSeeRecord> query){
        //查询列表数据
        Page<StduentClassSeeRecord> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = stduentClassSeeRecordService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取学生记录表详情信息")
    public BaseResult<StduentClassSeeRecord> search(@RequestBody  StduentClassSeeRecord stduentClassSeeRecord){
        StduentClassSeeRecord result = stduentClassSeeRecordService.getOne(Wrappers.query(stduentClassSeeRecord));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增学生记录表信息")
    public BaseResult save(@RequestBody  StduentClassSeeRecord stduentClassSeeRecord){
        boolean retFlag= stduentClassSeeRecordService.save(stduentClassSeeRecord);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改学生记录表信息")
    public BaseResult update(@RequestBody StduentClassSeeRecord stduentClassSeeRecord){
        boolean retFlag= stduentClassSeeRecordService.updateById(stduentClassSeeRecord);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete}")
    @ApiOperation(value = "删除学生记录表信息")
    public BaseResult delete(@RequestBody StduentClassSeeRecord stduentClassSeeRecord){
        boolean retFlag= stduentClassSeeRecordService.remove(Wrappers.query(stduentClassSeeRecord));
        return BaseResult.success(retFlag);
    }
}