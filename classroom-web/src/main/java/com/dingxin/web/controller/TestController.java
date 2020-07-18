package com.dingxin.web.controller;

import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Test1;
import com.dingxin.web.service.TestService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxin.yuan
 * @date 2020/7/16 22:33
 */
@Api(value = "接口文档1",tags = "接口文档1")
@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @ApiOperationSupport(author = "我叫袁烟枪")
    @ApiOperation(value = "接口1",notes = "rua rtuarua")
    @GetMapping("/test")
    public BaseResult<Test1> test(String name){
        return BaseResult.success(testService.test(name));
    }

}
