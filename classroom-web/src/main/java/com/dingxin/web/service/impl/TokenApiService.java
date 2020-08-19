package com.dingxin.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingxin.dao.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static com.dingxin.common.constant.CommonConstant.API_TOKEN;

@Service
public class TokenApiService {
    @Value("${api.client.id}")
    private String clientId;
    @Value("${api.client.secret}")
    private String clientSecret;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取API网关token 使用Redis缓存
     *
     * @return
     */
    public String getToken() {
        String accessToken = (String) redisUtils.get(API_TOKEN);
        if (StringUtils.isNotBlank(accessToken)) {
            return accessToken;
        }
        String url = "https://api.sustech.edu.cn//authorization/oauth/token?grant_type=client_credentials&client_id=" + clientId +
                "&client_secret=" + clientSecret;
        String uri = "https://api.sustech.edu.cn//authorization/oauth/token?grant_type=client_credentials&client_id=C2V5KinfGvkr6re81Gg6UICs&client_secret=VVOH8DT3hnWw8bxnu2yxG7E9A2IEQnCV";
        JSONObject jsonResult = restTemplate.getForObject(url, JSONObject.class);
        if (jsonResult != null) {
            accessToken = jsonResult.getString("access_token");
            redisUtils.set(API_TOKEN,accessToken,1800);
        }
        return accessToken;
    }
}
