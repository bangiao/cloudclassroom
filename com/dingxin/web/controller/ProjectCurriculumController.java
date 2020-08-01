package com.dingxin.web.controller;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.web.service.IProjectCurriculumService;
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
@RequestMapping("/projectCurriculum")
@Api(tags = "接口")
public class ProjectCurriculumController {


    @Autowired
    private IProjectCurriculumService projectCurriculumService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<ProjectCurriculum>>list(@RequestBody BaseQuery<ProjectCurriculum> query){
        //查询列表数据
        Page<ProjectCurriculum> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = projectCurriculumService.page(page,Wrappers.query(query.getData()));
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
    public BaseResult<ProjectCurriculum> search(@RequestBody  ProjectCurriculum projectCurriculum){
        ProjectCurriculum result = projectCurriculumService.getOne(Wrappers.query(projectCurriculum));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  ProjectCurriculum projectCurriculum){
        boolean retFlag= projectCurriculumService.save(projectCurriculum);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody ProjectCurriculum projectCurriculum){
        boolean retFlag= projectCurriculumService.updateById(projectCurriculum);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody ProjectCurriculum projectCurriculum){
        boolean retFlag= projectCurriculumService.remove(Wrappers.query(projectCurriculum));
        return BaseResult.success(retFlag);
    }
}