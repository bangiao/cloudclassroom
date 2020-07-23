package com.dingxin.web.web.controller;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.web.service.IClassEvaluateService;
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
 * 课程评价表
 */
@RestController
@RequestMapping("/classEvaluate")
@Api(value = "课程评价表接口")
public class ClassEvaluateController {


    @Autowired
    private IClassEvaluateService classEvaluateService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程评价表列表")
    public BaseResult<Page<ClassEvaluate>>list(@RequestBody BaseQuery<ClassEvaluate> query){
        //查询列表数据
        Page<ClassEvaluate> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = classEvaluateService.page(page,Wrappers.query(query.getData()));
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程评价表详情信息")
    public BaseResult<ClassEvaluate> search(@RequestBody  ClassEvaluate classEvaluate){
        ClassEvaluate result = classEvaluateService.getOne(Wrappers.query(classEvaluate));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增课程评价表信息")
    public BaseResult save(@RequestBody  ClassEvaluate classEvaluate){
        boolean retFlag= classEvaluateService.save(classEvaluate);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改课程评价表信息")
    public BaseResult update(@RequestBody ClassEvaluate classEvaluate){
        boolean retFlag= classEvaluateService.updateById(classEvaluate);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete}")
    @ApiOperation(value = "删除课程评价表信息")
    public BaseResult delete(@RequestBody ClassEvaluate classEvaluate){
        boolean retFlag= classEvaluateService.remove(Wrappers.query(classEvaluate));
        return BaseResult.success(retFlag);
    }
}