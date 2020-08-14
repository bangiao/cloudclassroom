package com.dingxin.web.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.pojo.po.Menu;
import com.dingxin.pojo.request.WidRequest;
import com.dingxin.pojo.vo.MenuVo;
import com.dingxin.web.service.ICasDeptsService;
import com.dingxin.web.service.ICommonDataService;
import com.dingxin.web.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/common")
@Api(tags = {"公共数据接口"})
@ManTag
public class CommonDataController {
    @Autowired
    private ICasDeptsService casDeptsService;
    @Autowired
    private ICommonDataService commonDataService;
    @Autowired
    private IUserRoleService userRoleService;


    @PostMapping("/deptList")
    @ApiOperation(value = "获取组织架构列表")
    public BaseResult<Page<CasDepts>> list(){
        return casDeptsService.queryPageList();
    }

    @PostMapping("/photo")
    @ApiOperation(value = "获取学生或老师照片")
    public BaseResult photo(@RequestBody WidRequest request){
        return commonDataService.photo(request.getWid());
    }

    @PostMapping("/courses")
    @ApiOperation(value = "获取学生课程表")
    public BaseResult courses(@RequestBody WidRequest request){
        return commonDataService.courses(request.getWid());
    }

    @PostMapping("/menu")
    @ApiOperation(value = "获取人员菜单")
    public BaseResult menus(){
        List<Menu> menus = userRoleService.menus();
        return BaseResult.success(MenuVo.conventList(menus));
    }



}
