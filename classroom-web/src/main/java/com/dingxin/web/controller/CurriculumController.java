package com.dingxin.web.controller;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.vo.CurriculumVo;
import com.dingxin.web.service.ICurriculumService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.web.service.strategy.curriculum.AdministratorStrategy;
import com.dingxin.web.service.strategy.curriculum.CurriculumStrategyContext;
import com.dingxin.web.service.strategy.curriculum.TeacherStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

/**
 * 后台管理课程管理接口
 */
@ManTag
@RestController
@RequestMapping("/curriculum")
@Api(tags = "课程接口")
public class CurriculumController {


    @Autowired
    private ICurriculumService curriculumService;

    @Autowired
    @Qualifier("AdministratorStrategy")
    private AdministratorStrategy administratorStrategy;

    @Autowired
    @Qualifier("TeacherStrategy")
    private TeacherStrategy teacherStrategy;



    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取所有课程列表")
    public BaseResult<Page<Curriculum>>list(@RequestBody BaseQuery<Curriculum> query){

        int a = 1;
        IPage<CurriculumVo> pageList;
        switch (a){
            case 1 :
                pageList = new CurriculumStrategyContext(administratorStrategy).getPageList();break;
            case 2:
                pageList = new CurriculumStrategyContext(teacherStrategy).getPageList();break;
        }
//        //查询列表数据
//        Page<Curriculum> page = new Page(query.getCurrentPage(),query.getPageSize());
//        IPage pageList = curriculumService.page(page,Wrappers.query(query.getData()));
//        if(CollectionUtils.isEmpty(pageList.getRecords())){
//            return BaseResult.success();
//        }
//        return BaseResult.success(pageList);
        return null;
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程详情信息")
    public BaseResult<Curriculum> search(@RequestBody  Curriculum curriculum){
        Curriculum result = curriculumService.getOne(Wrappers.query(curriculum));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增课程信息")
    public BaseResult save(@RequestBody  Curriculum curriculum){
        boolean retFlag= curriculumService.save(curriculum);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改课程信息")
    public BaseResult update(@RequestBody Curriculum curriculum){
        boolean retFlag= curriculumService.updateById(curriculum);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程信息")
    public BaseResult delete(@RequestBody Curriculum curriculum){
        boolean retFlag= curriculumService.remove(Wrappers.query(curriculum));
        return BaseResult.success(retFlag);
    }
}