package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.CrrStudentStudyCase;
import com.dingxin.pojo.request.StudentStudyCaseListRequest;
import com.dingxin.web.service.ICrrStudentStudyCaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

/**
 * 学生学习情况
 */
@ManTag
@RestController
@RequestMapping("/crrStudentStudyCase")
@Api(tags = {"学生学习情况接口"})
public class CrrStudentStudyCaseController {


    @Autowired
    private ICrrStudentStudyCaseService crrStudentStudyCaseService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<CrrStudentStudyCase>>list(@RequestBody StudentStudyCaseListRequest query){
        //查询列表数据
        Page<CrrStudentStudyCase> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<CrrStudentStudyCase> qw = new LambdaQueryWrapper<>();
        qw.eq(CrrStudentStudyCase::getDelFlag, CommonConstant.DEL_FLAG);
        String queryStr = query.getQueryStr();
        if(StringUtils.isNotEmpty(queryStr)){
            qw.and(Wrapper -> Wrapper.like(CrrStudentStudyCase::getClassName, queryStr).or()
                    .like(CrrStudentStudyCase::getStudentName, queryStr).or().like(CrrStudentStudyCase::getStudentNum, queryStr));
        }
        IPage pageList = crrStudentStudyCaseService.page(page,qw);
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取详情信息")
    public BaseResult<CrrStudentStudyCase> search(@RequestBody  CrrStudentStudyCase crrStudentStudyCase){
        CrrStudentStudyCase result = crrStudentStudyCaseService.getOne(Wrappers.query(crrStudentStudyCase));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  CrrStudentStudyCase crrStudentStudyCase){
        boolean retFlag= crrStudentStudyCaseService.save(crrStudentStudyCase);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody CrrStudentStudyCase crrStudentStudyCase){
        boolean retFlag= crrStudentStudyCaseService.updateById(crrStudentStudyCase);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody CrrStudentStudyCase crrStudentStudyCase){
        LambdaUpdateWrapper<CrrStudentStudyCase> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CrrStudentStudyCase::getId,crrStudentStudyCase.getId());
        wrapper.set(CrrStudentStudyCase::getDelFlag,CommonConstant.DEL_FLAG_TRUE);
        boolean retFlag= crrStudentStudyCaseService.update(wrapper);
        return BaseResult.success(retFlag);
    }
}