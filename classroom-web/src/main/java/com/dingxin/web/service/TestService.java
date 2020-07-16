package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.pojo.po.Test1;
import com.dingxin.dao.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author changxin.yuan
 * @date 2020/7/16 22:29
 */
@Service
public class TestService extends ServiceImpl<TestMapper, Test1> {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Test1 test(String name){
        LambdaQueryWrapper<Test1> query = Wrappers.<Test1>lambdaQuery()
                .select(Test1::getId,
                        Test1::getName)
                .eq(Test1::getName, name);
        Test1 test1 = testMapper.selectOne(query);
        redisTemplate.opsForValue().setIfAbsent("test",test1);
        return test1;
    }

}
