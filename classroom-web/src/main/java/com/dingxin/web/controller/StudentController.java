package com.dingxin.web.controller;
import com.dingxin.pojo.po.Student;
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
    @PostMapping("/search")
    @ApiOperation(value = "获取学生信息表详情信息")
    public BaseResult<Student> search(@RequestBody  Student student){
        Student result = studentService.getOne(Wrappers.query(student));
        return BaseResult.success(result);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation(value = "新增学生信息表信息")
    public BaseResult save(@RequestBody  Student student){
        boolean retFlag= studentService.save(student);
        return BaseResult.success(retFlag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改学生信息表信息")
    public BaseResult update(@RequestBody Student student){
        boolean retFlag= studentService.updateById(student);
        return BaseResult.success(retFlag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除学生信息表信息")
    public BaseResult delete(@RequestBody Student student){
        boolean retFlag= studentService.remove(Wrappers.query(student));
        return BaseResult.success(retFlag);
    }
}