package com.dingxin.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class TokenApiService {
    @Value("${api.client.id}")
    private String clientId;
    @Value("${api.client.secret}")
    private String clientSecret;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    public String getToken() {
        String url = "https://api.sustech.edu.cn//authorization/oauth/token?grant_type=client_credentials&client_id="+clientId+
                "&client_secret="+clientSecret;
        String uri = "https://api.sustech.edu.cn//authorization/oauth/token?grant_type=client_credentials&client_id=C2V5KinfGvkr6re81Gg6UICs&client_secret=VVOH8DT3hnWw8bxnu2yxG7E9A2IEQnCV";


        System.out.println(url.equals(uri));

        String accessToken = "";
        JSONObject jsonResult = restTemplate.getForObject(url, JSONObject.class);
        if (jsonResult != null) {
            accessToken = jsonResult.getString("access_token");
        }
        return accessToken;
    }
}
