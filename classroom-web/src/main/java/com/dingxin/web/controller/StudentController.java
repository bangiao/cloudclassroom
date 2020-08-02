package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dingxin.common.annotation.ManTag;
import com.dingxin.pojo.po.Student;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IStudentService;
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
 * 学生信息表
 */
@ManTag
@RestController
@RequestMapping("/student")
@Api(tags = "学生信息表接口")
public class StudentController {


    @Autowired
    private IStudentService studentService;


    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取学生信息表列表")
    public BaseResult<Page<Student>>list(@RequestBody BaseQuery<Student> query){
        //查询列表数据
        Page<Student> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage pageList = studentService.page(page,Wrappers.query(query.getData()));
        return BaseResult.success(pageList);
    }

    /**
     * 单个查询
     */
    @PostMapping("/searchById")
    @ApiOperation(value = "获取学生信息表详情信息")
    public BaseResult<Student> search(@RequestBody IdRequest idRequest){
        LambdaQueryWrapper<Student> qw = Wrappers.lambdaQuery();
        qw.eq(null != idRequest.getId(),Student::getId,idRequest.getId());
        Student result = studentService.getOne(qw);
        return BaseResult.success(result);
    }


}