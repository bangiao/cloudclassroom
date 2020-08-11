package com.dingxin.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.web.service.ICommonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("DuplicatedCode")
@Service
public class CommonDataServiceImpl implements ICommonDataService {
    @Autowired
    private TokenApiService tokenApiService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取老师或学生头像头像
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult photo(Integer id) {
        String token = tokenApiService.getToken();
        String url = "https://api.sustech.edu.cn/api/" +
                "/api/studentStudyInfo/educational/photograph/{" + id + "}";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        JSONObject jsonObject = response.getBody();
        if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get("data"))) {
            LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) jsonObject.get("data");
            return BaseResult.success(data.get("photoUrl"),"查询成功");
        }
        return BaseResult.success();
    }

    @Override
    public BaseResult courses(Integer id) {
        String token = tokenApiService.getToken();
        String url = "https://api.sustech.edu.cn/api/" +
                "/api/studentStudyInfo/educational/courses/student/{"+id+"}";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        JSONObject jsonObject = response.getBody();
        if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get("data"))) {
            LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) jsonObject.get("data");
            List educationalCourses = (List) data.get("educationalCourses");
            return BaseResult.success(educationalCourses,"查询成功");
        }
        return BaseResult.success();
    }
}
