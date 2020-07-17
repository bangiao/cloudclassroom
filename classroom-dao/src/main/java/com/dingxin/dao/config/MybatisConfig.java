package com.dingxin.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author changxin.yuan
 * @date 2020/7/16 22:28
 */
@Configuration
@MapperScan(basePackages = "com.dingxin.dao.mapper")
public class MybatisConfig {
}
