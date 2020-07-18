package com.dingxin.web.controller;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.web.service.IClassEvaluateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
     * 列表
     */
    @GetMapping
    @ApiOperation(value = "获取课程评价表列表")
    public BaseResult<Page<ClassEvaluate>>list(BaseQuery baseQuery){
        //查询列表数据
        Page page=new Page(baseQuery.getCurrentPage(),baseQuery.getPageSize());
        IPage pageList = classEvaluateService.page(page);
        if(CollectionUtils.isEmpty(pageList.getRecords())){
            return BaseResult.success();
        }
        return BaseResult.success(pageList);
    }

    /**
     * 信息
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取课程评价表详情信息")
    public BaseResult<ClassEvaluate> info(@PathVariable("id") Integer id){
        ClassEvaluate classEvaluate = classEvaluateService.getById(id);
        return BaseResult.success(classEvaluate);
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
    @PutMapping
    @ApiOperation(value = "修改课程评价表信息")
    public BaseResult update(@RequestBody @PathVariable("classEvaluate") ClassEvaluate classEvaluate){
        BaseResult<ClassEvaluate> baseResult=new BaseResult<>();
        boolean retFlag= classEvaluateService.updateById(classEvaluate);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除课程评价表信息")
    public BaseResult delete(@PathVariable("id") Integer id){
        boolean retFlag= classEvaluateService.removeById(id);
        return BaseResult.success(retFlag);
    }
}