package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.request.ClassEvaluateInsertRequest;
import com.dingxin.pojo.request.ClassEvaluateListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.ThumbsUpRequest;
import com.dingxin.pojo.vo.ClassEvaluateVo;
import com.dingxin.web.service.IClassEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程评价表
 */
@UserTag
@RestController
@RequestMapping("/classEvaluate")
@Api(tags = {"课程评价表接口"})
public class ClassEvaluateController {


    @Autowired
    private IClassEvaluateService classEvaluateService;

    /**
     * 列表查询
     */
    @PostMapping("/alllist")
    @ApiOperation(value = "获取所有课程评价表列表")
    public BaseResult<Page<ClassEvaluate>> list(@RequestBody ClassEvaluateListRequest query) {
        IPage<ClassEvaluate> pageList = classEvaluateService.queryPage(query);
        return BaseResult.success(ClassEvaluateVo.convertToVoWithPage(pageList));
    }

    /**
     * 信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取课程评价表详情信息")
    public BaseResult<ClassEvaluate> info(@RequestBody IdRequest id) {
        ClassEvaluate classEvaluate = classEvaluateService.getByIdSelf(id);
        return BaseResult.success(ClassEvaluateVo.convertToVo(classEvaluate));
    }

    /**
     * 保存 收藏只会有保存不会有修改
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增课程评价表信息")
    public BaseResult save(@Validated @RequestBody ClassEvaluateInsertRequest insertRequest) {
        ClassEvaluate covent = ClassEvaluateInsertRequest.covent(insertRequest);
        boolean retFlag = classEvaluateService.save(covent);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改  点赞数
     */
    @PostMapping(value = "/upOrDown")
    @ApiOperation(value = "修改课程评价表点赞信息")
    public BaseResult updateUp(@RequestBody ThumbsUpRequest request) {
        boolean retFlag = classEvaluateService.updateUp(request);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程评价表信息")
    public BaseResult delete(@Validated @RequestBody IdRequest id) {
        boolean retFlag = classEvaluateService.delete(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程评价表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        boolean retFlag = classEvaluateService.deleteBatch(list);
        return BaseResult.success(retFlag);
    }

}