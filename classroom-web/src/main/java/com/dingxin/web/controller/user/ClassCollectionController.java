package com.dingxin.web.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.ClassCollectionInsertRequest;
import com.dingxin.pojo.request.ClassIdRequest;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ClassCollectionListVo;
import com.dingxin.web.service.IClassCollectionService;
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
 * 课程收藏表
 */
@UserTag
@RestController
@RequestMapping("/classCollection")
@Api(tags = {"课程收藏接口"})
public class ClassCollectionController {


    @Autowired
    private IClassCollectionService classCollectionService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取课程收藏表列表")
    public BaseResult<Page<ClassCollectionListVo>> list(@RequestBody CommQueryListRequest query) {
        //查询列表数据
        IPage<Curriculum> pageList = classCollectionService.queryList(query);
        return BaseResult.success(ClassCollectionListVo.convertToVoWithPage(pageList));
    }

    /**
     * 单个查询
     */
    @PostMapping("/search")
    @ApiOperation(value = "获取课程收藏表详情信息")
    public BaseResult<ClassCollectionListVo> search(@RequestBody ClassIdRequest id) {
        Curriculum result = classCollectionService.getByIdSelf(id);
        return BaseResult.success(ClassCollectionListVo.convertToVo(result));
    }

    /**
     * 保存
     */
    @PostMapping("/insertOrUpdate")
    @ApiOperation(value = "新增或者修改课程收藏表信息")
    public BaseResult save(@Validated @RequestBody ClassCollectionInsertRequest classCollection) {
        boolean retFlag = classCollectionService.insert(classCollection);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程收藏表信息")
    public BaseResult delete(@RequestBody ClassIdRequest id) {
        boolean retFlag = classCollectionService.deleteById(id);
        return BaseResult.success(retFlag);
    }

    /**
     * 批量删除删除
     */
    @PostMapping("/delete/batch")
    @ApiOperation(value = "批量删除课程收藏表信息")
    public BaseResult deleteBatch(@RequestBody List<Integer> list) {
        boolean retFlag = classCollectionService.deleteByBatch(list);
        return BaseResult.success(retFlag);
    }
}