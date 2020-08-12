package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.OperationWatcher;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.enums.RoleEnum;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumFuzzyQuery4List;
import com.dingxin.pojo.request.CurriculumInsertRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.TeacherIdRequest;
import com.dingxin.pojo.vo.CurriculumDetailsVo;
import com.dingxin.pojo.vo.CurriculumListVo;
import com.dingxin.pojo.vo.CurriculumVo;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IUserRoleService;
import com.dingxin.web.service.strategy.curriculum.AdministratorStrategy;
import com.dingxin.web.service.strategy.curriculum.CurriculumStrategyContext;
import com.dingxin.web.service.strategy.curriculum.TeacherStrategy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    private IUserRoleService userRoleService;

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
    public BaseResult<Page<CurriculumVo>>list(@RequestBody CurriculumFuzzyQuery4List query){
        //todo
//        String userId = ShiroUtils.getUserId();
//        UserRole userRole = userRoleService.getById(userId);
//        Integer roleId = userRole.getRoleId();
//        RoleEnum role = RoleEnum.getByCode(roleId);
        RoleEnum role = RoleEnum.ADMINISTRATOR;
        IPage<Curriculum> pageList;
        switch (role){
            case TEACHER :
                pageList = new CurriculumStrategyContext(teacherStrategy).getPageList(query);break;
            case ADMINISTRATOR :
                pageList = new CurriculumStrategyContext(administratorStrategy).getPageList(query);break;
            default:
                return BaseResult.failed(ExceptionEnum.PARAMTER_ERROR);
        }

        return BaseResult.success(CurriculumVo.convertToVoWithPage(pageList));
    }
    /**
     * 列表查询
     */
    @PostMapping("/listall")
    @ApiOperation(value = "获取所有课程列表下拉")
    public BaseResult<Page<CurriculumVo>>listall(TeacherIdRequest idRequest){
        List<Curriculum> list=curriculumService.listall(idRequest);
        return BaseResult.success(CurriculumListVo.convertToVoWithPage(list));
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程详情信息",response = CurriculumDetailsVo.class)
    public BaseResult<CurriculumDetailsVo> search(@Validated @RequestBody IdRequest id){

        CurriculumDetailsVo curriculumDetailsVo = curriculumService.loadCurriculumDetails(id);

        return BaseResult.success(curriculumDetailsVo);
    }
    /**
     * 单个查询
     */
    @PostMapping("/searchByTeacher")
    @ApiOperation(value = "根据讲师获取课程详情信息")
    public BaseResult<CurriculumListVo> searchByTeacher(@Validated @RequestBody IdRequest id){
        List<Curriculum> curriculumList = curriculumService.searchByTeacher(id);
        List<CurriculumListVo> curriculumListVos = CurriculumListVo.convertToVoWithPage(curriculumList);
        return BaseResult.success(curriculumListVos);
    }
    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增课程信息")
    @OperationWatcher(operateDesc = "新增课程信息")
    public BaseResult save(@RequestBody CurriculumInsertRequest curriculum){
//        boolean retFlag= curriculumService.save(curriculum);
//        return BaseResult.success(retFlag);
        return null;
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
    @OperationWatcher(operateDesc = "删除课程信息")
    public BaseResult delete(@RequestBody List<Integer> curriculumIds){
        curriculumService.deleteCurriculumAndRelated(curriculumIds);
        return BaseResult.success();
    }

    @PostMapping("/disable")
    @ApiOperation(value = "禁用课程")
    public BaseResult disable(@RequestBody List<Integer> curriculumIds){

        curriculumService.disableCurriculum(curriculumIds);
        return BaseResult.success();
    }

    @PostMapping("/enable")
    @ApiOperation(value = "启用课程")
    public BaseResult enable(@RequestBody List<Integer> curriculumIds){

        curriculumService.enableCurriculum(curriculumIds);
        return BaseResult.success();
    }
}