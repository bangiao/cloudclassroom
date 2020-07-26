package com.dingxin.web.controller;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.web.service.ICurriculumService;
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
@UserTag
@RestController
@RequestMapping("/curriculum")
@Api(tags = "课程接口")
public class CurriculumController {


    @Autowired
    private ICurriculumService curriculumService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<Curriculum>>list(@RequestBody BaseQuery<Curriculum> query){
        //查询列表数据
        Page<Curriculum> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = curriculumService.page(page,Wrappers.query(query.getData()));
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
    public BaseResult<Curriculum> search(@RequestBody  Curriculum curriculum){
        Curriculum result = curriculumService.getOne(Wrappers.query(curriculum));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  Curriculum curriculum){
        boolean retFlag= curriculumService.save(curriculum);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody Curriculum curriculum){
        boolean retFlag= curriculumService.updateById(curriculum);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody Curriculum curriculum){
        boolean retFlag= curriculumService.remove(Wrappers.query(curriculum));
        return BaseResult.success(retFlag);
    }
}