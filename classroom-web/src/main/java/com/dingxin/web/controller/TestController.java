package com.dingxin.web.controller;

import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Test1;
import com.dingxin.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changxin.yuan
 * @date 2020/7/16 22:33
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public BaseResult<Test1> test(String name){
        return BaseResult.success(testService.test(name));
    }

}
