package com.dingxin.web.controller;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.web.service.ITeachersService;
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
@RequestMapping("/teachers")
@Api(value = "接口")
public class TeachersController {


    @Autowired
    private ITeachersService teachersService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取列表")
    public BaseResult<Page<Teachers>>list(@RequestBody BaseQuery<Teachers> query){
        //查询列表数据
        Page<Teachers> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = teachersService.page(page,Wrappers.query(query.getData()));
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
    public BaseResult<Teachers> search(@RequestBody  Teachers teachers){
        Teachers result = teachersService.getOne(Wrappers.query(teachers));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增信息")
    public BaseResult save(@RequestBody  Teachers teachers){
        boolean retFlag= teachersService.save(teachers);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改信息")
    public BaseResult update(@RequestBody Teachers teachers){
        boolean retFlag= teachersService.updateById(teachers);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除信息")
    public BaseResult delete(@RequestBody Teachers teachers){
        boolean retFlag= teachersService.remove(Wrappers.query(teachers));
        return BaseResult.success(retFlag);
    }
}