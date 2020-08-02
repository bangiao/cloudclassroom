package com.dingxin.web.controller;
import com.dingxin.pojo.po.CurriculumIntermediate;
import com.dingxin.web.service.ICurriculumIntermediateService;
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
@RequestMapping("/curriculumIntermediate")
@Api(tags = "接口")
public class CurriculumIntermediateController {


    @Autowired
    private ICurriculumIntermediateService curriculumIntermediateService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<CurriculumIntermediate>>list(@RequestBody BaseQuery<CurriculumIntermediate> query){
        //查询列表数据
        Page<CurriculumIntermediate> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = curriculumIntermediateService.page(page,Wrappers.query(query.getData()));
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
    public BaseResult<CurriculumIntermediate> search(@RequestBody  CurriculumIntermediate curriculumIntermediate){
        CurriculumIntermediate result = curriculumIntermediateService.getOne(Wrappers.query(curriculumIntermediate));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  CurriculumIntermediate curriculumIntermediate){
        boolean retFlag= curriculumIntermediateService.save(curriculumIntermediate);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody CurriculumIntermediate curriculumIntermediate){
        boolean retFlag= curriculumIntermediateService.updateById(curriculumIntermediate);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody CurriculumIntermediate curriculumIntermediate){
        boolean retFlag= curriculumIntermediateService.remove(Wrappers.query(curriculumIntermediate));
        return BaseResult.success(retFlag);
    }
}