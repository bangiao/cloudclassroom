package com.dingxin.web.controller.user;

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
import com.dingxin.web.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 课程评价表
 */
@UserTag
@RestController
@RequestMapping("/userclassEvaluate")
@Api(tags = {"课程评价表接口"})
public class ClassUserEvaluateController {


    @Autowired
    private IClassEvaluateService classEvaluateService;

    /**
     * 列表查询
     */
    @PostMapping("/alllist")
    @ApiOperation(value = "PC端 获取课程评价表列表")
    public BaseResult<Page<ClassEvaluateVo>> list(@RequestBody ClassEvaluateListRequest query) {
        IPage<ClassEvaluate> pageList = classEvaluateService.queryUserPage(query);
        return BaseResult.success(ClassEvaluateVo.convertToVoWithPage(pageList));
    }

    /**
     * 信息
     */
    @PostMapping("/get")
    @ApiOperation(value = "获取课程评价表详情信息")
    public BaseResult<ClassEvaluateVo> info(@RequestBody IdRequest id) {
        ClassEvaluate classEvaluate = classEvaluateService.getByIdSelf(id);
        ClassEvaluateVo classEvaluateVo=null;
        if (!Objects.isNull(classEvaluate)){
            classEvaluateVo = ClassEvaluateVo.convertToVo(classEvaluate);
        }
        return BaseResult.success(classEvaluateVo);
    }

    /**
     * 保存 收藏只会有保存不会有修改
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增课程评价表信息")
    public BaseResult save(@Validated @RequestBody ClassEvaluateInsertRequest insertRequest) {
        ClassEvaluate covent = ClassEvaluateInsertRequest.covent(insertRequest, ShiroUtils.getUser());
        classEvaluateService.saveEvaluation(covent);
        return BaseResult.success();
    }

}