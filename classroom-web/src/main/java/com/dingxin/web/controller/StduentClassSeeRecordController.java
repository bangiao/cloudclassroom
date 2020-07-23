package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.vo.Id;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.List;

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
        QueryWrapper<StduentClassSeeRecord> qw = new QueryWrapper<>();
        qw.like("student_name",query.getData()).or().like("student_code",query.getData()).or().like("student_class",query.getData());
        IPage pageList = stduentClassSeeRecordService.page(page, qw);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取学生记录表详情信息")
    public BaseResult<StduentClassSeeRecord> info(@RequestBody Id id){
        StduentClassSeeRecord stduentClassSeeRecord = stduentClassSeeRecordService.getById(id.getId());
        return BaseResult.success(stduentClassSeeRecord);
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
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
    public BaseResult update(@RequestBody  StduentClassSeeRecord stduentClassSeeRecord){
        boolean retFlag= stduentClassSeeRecordService.saveOrUpdate(stduentClassSeeRecord);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除学生记录表信息")
    public BaseResult delete(@RequestBody Id id){
        boolean retFlag= stduentClassSeeRecordService.removeById(id.getId());
        return BaseResult.success(retFlag);
    }
    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "删除学生记录表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list){
        boolean retFlag= stduentClassSeeRecordService.removeByIds(list);
        return BaseResult.success(retFlag);
    }

}