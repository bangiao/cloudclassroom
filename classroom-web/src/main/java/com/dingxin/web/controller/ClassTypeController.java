package com.dingxin.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.pojo.request.ClassTypeInsertRequest;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ClassTypeVo;
import com.dingxin.web.service.IClassTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 课程类型表
 */
@ManTag
@RestController
@RequestMapping("/classType")
@Api(tags = "课程类型接口")
public class ClassTypeController {


    @Autowired
    private IClassTypeService classTypeService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程类型列表")
    public BaseResult<Page<ClassType>> list(@RequestBody CommQueryListRequest query) {
        IPage<ClassType> pageList = classTypeService.queryPage(query);
        return BaseResult.success(ClassTypeVo.convertToVoWithPage(pageList));
    }

    /**
     * 课程类型列表不分页 用于下拉列表
     */
    @PostMapping("/listDic")
    @ApiOperation(value = "获取课程类型列表,用于类型选择")
    public BaseResult<Page<ClassType>> listDic() {
        List<Map<String, Object>> maps = classTypeService.listMapself();
        return BaseResult.success(maps);
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程类型详情信息")
    public BaseResult<ClassType> search(@RequestBody IdRequest idRequest) {
        ClassType result = classTypeService.getOneSelf(idRequest);
        return BaseResult.success(ClassTypeVo.convent(result));
    }

    /**
     * 保存
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增/修改课程类型信息")
    public BaseResult save(@Validated @RequestBody ClassTypeInsertRequest classType) {
        ClassType convent = ClassTypeInsertRequest.convent(classType);
        boolean falg = classTypeService.saveSelf(convent);
        return BaseResult.success(falg);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程类型信息")
    public BaseResult delete(@Validated @RequestBody IdRequest id) {
        boolean retFlag = classTypeService.delete(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程类型信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        boolean retFlag = classTypeService.deleteBatch(list);
        return BaseResult.success(retFlag);
    }

}