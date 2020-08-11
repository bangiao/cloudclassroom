package com.dingxin.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ComponetConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
