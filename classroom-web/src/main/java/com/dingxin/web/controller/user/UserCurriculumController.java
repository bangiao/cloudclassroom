package com.dingxin.web.controller.user;

/**
 * @author ya.nie
 * @date 2020/8/15 14:23
 * @Description
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.CurriculumPcVo;
import com.dingxin.pojo.vo.CurriculumVo;
import com.dingxin.pojo.vo.ProjectManagementVo;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IProjectCurriculumService;
import com.dingxin.web.service.IProjectManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@UserTag
@RestController
@RequestMapping("/User/curriculum")
@Api(tags = "接口")
public class UserCurriculumController {

    @Autowired
    private ICurriculumService curriculumService;
    @Autowired
    private IProjectManagementService projectManagementService;
    @Autowired
    private IProjectCurriculumService projectCurriculumService;
    /**
     * 列表查询
     */
    @PostMapping("/ListByType")
    @ApiOperation(value = "根据type查询课程列表")
    public BaseResult<Page<CurriculumPcVo>> list(@RequestBody IdRequest idRequest){
        //查询列表数据
    return curriculumService.leatestList(idRequest);
    }

    /**
     * 根据部门查询课程列表
     */
    @PostMapping("/ListbyDept")
    @ApiOperation(value = "部门获取列表")
    public BaseResult<Page<CurriculumVo>> ListbyDept(@RequestBody @Validated IdRequest idRequest){
        //查询列表数据
        return curriculumService.ListbyDept(idRequest);
    }


    /**
     * 专题名称列表
     */
    @PostMapping("/projectNameList")
    @ApiOperation(value = "专题名称列表")
    public BaseResult<ProjectManagementVo> projectNameList(@RequestBody BaseQuery<ProjectManagement> query){
        //查询列表数据
        return projectManagementService.projectNameList(query);
    }

    /**
     * 专题名称列表
     */
    @PostMapping("/ListByProjectName")
    @ApiOperation(value = "专题名称获取课程列表")
    public BaseResult<CurriculumPcVo> ListByProjectName(@RequestBody @Validated IdRequest idRequest){
        //查询列表数据
        return projectCurriculumService.ListByProjectName(idRequest);
    }

}
