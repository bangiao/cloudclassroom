package com.dingxin.web.controller;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
     * 列表
     */
    @GetMapping
    @ApiOperation(value = "获取学生记录表列表")
    public BaseResult<Page<StduentClassSeeRecord>>list(BaseQuery baseQuery){
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        IPage pageList = stduentClassSeeRecordService.page(page);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取学生记录表详情信息")
    public BaseResult<StduentClassSeeRecord> info(@PathVariable("id") Integer id){
        StduentClassSeeRecord stduentClassSeeRecord = stduentClassSeeRecordService.getById(id);
        return BaseResult.success(stduentClassSeeRecord);
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
    @PutMapping
    @ApiOperation(value = "修改学生记录表信息")
    public BaseResult update(@RequestBody @PathVariable("stduentClassSeeRecord") StduentClassSeeRecord stduentClassSeeRecord){
        BaseResult<StduentClassSeeRecord> baseResult=new BaseResult<>();
        boolean retFlag= stduentClassSeeRecordService.updateById(stduentClassSeeRecord);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除学生记录表信息")
    public BaseResult delete(@PathVariable("id") Integer id){
        boolean retFlag= stduentClassSeeRecordService.removeById(id);
        return BaseResult.success(retFlag);
    }
}