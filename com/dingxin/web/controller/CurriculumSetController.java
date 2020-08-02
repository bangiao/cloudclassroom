package com.dingxin.web.controller;
import com.dingxin.pojo.po.CurriculumSet;
import com.dingxin.web.service.ICurriculumSetService;
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
@RequestMapping("/curriculumSet")
@Api(tags = "接口")
public class CurriculumSetController {


    @Autowired
    private ICurriculumSetService curriculumSetService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<CurriculumSet>>list(@RequestBody BaseQuery<CurriculumSet> query){
        //查询列表数据
        Page<CurriculumSet> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = curriculumSetService.page(page,Wrappers.query(query.getData()));
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
    public BaseResult<CurriculumSet> search(@RequestBody  CurriculumSet curriculumSet){
        CurriculumSet result = curriculumSetService.getOne(Wrappers.query(curriculumSet));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  CurriculumSet curriculumSet){
        boolean retFlag= curriculumSetService.save(curriculumSet);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody CurriculumSet curriculumSet){
        boolean retFlag= curriculumSetService.updateById(curriculumSet);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody CurriculumSet curriculumSet){
        boolean retFlag= curriculumSetService.remove(Wrappers.query(curriculumSet));
        return BaseResult.success(retFlag);
    }
}