package com.dingxin.web.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.dao.mapper.TeachersMapper;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.ICasDeptsService;
import com.dingxin.web.service.ITeachersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  服务接口实现类
 */
@SuppressWarnings("Duplicates")
@Service
public class TeachersServiceImpl extends ServiceImpl<TeachersMapper, Teachers> implements ITeachersService {

    @Autowired
    private TeachersMapper teachersMapper;
    @Autowired
    private ICasDeptsService iCasDeptsService;
    @Autowired
    private TokenApiService tokenApiService;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Teachers> like(Teachers data) {
        LambdaQueryWrapper<Teachers> query = Wrappers.<Teachers>lambdaQuery()
                .like(
                    Objects.nonNull(data.getZgh()),
                    Teachers::getZgh,
                    data.getZgh())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    Teachers::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getXm()),
                    Teachers::getXm,
                    data.getXm())
                .like(
                    Objects.nonNull(data.getIntroduction()),
                    Teachers::getIntroduction,
                    data.getIntroduction())
                .like(
                    Objects.nonNull(data.getEnable()),
                    Teachers::getEnable,
                    data.getEnable())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    Teachers::getModifyTime,
                    data.getModifyTime());
        return teachersMapper.selectList(query);


    }

    /**
     * 通过api网关获取讲师信息
     * @param query
     * @return
     */
    @Override
    public IPage<Teachers> queryPage(CommQueryListRequest query) {
        String queryStr = query.getQueryStr();
        String token = tokenApiService.getToken();
        String url = "https://api.sustech.edu.cn/api/" +
                "studentStudyInfo/educational/teacher/page?" +
                "currentPage="+query.getCurrentPage()
                +"&pageSize="+ query.getPageSize();
        if (StringUtils.isNotEmpty(queryStr)){
            url = url+"&searchParam="+queryStr;
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        JSONObject jsonObject = response.getBody();
        Page page = new Page<>();
        if (Objects.nonNull(jsonObject)&&Objects.nonNull(jsonObject.get("data"))){
            LinkedHashMap<String,Object> data = (LinkedHashMap<String, Object>) jsonObject.get("data");
            List<Map<String,String>> records = (List) data.get("records");
            //将远程接口中数据存入中间表
            ArrayList<Teachers> teacherList = Lists.newArrayList();
            ArrayList<Object> szdwdmList = Lists.newArrayList();
            for (Map<String,String> map:records) {
                szdwdmList.add(map.get("szdwdm"));
                Teachers teachers = new Teachers();
                teachers.setZgh(map.get("zgh"));
                teachers.setXm(map.get("xm"));
                teachers.setModifyTime(LocalDateTime.now());
                teacherList.add(teachers);
            }
            this.saveOrUpdateBatch(teacherList);
            //用学员号查出学院名称重新放入map中
            LambdaQueryWrapper<CasDepts> deptQw = Wrappers.lambdaQuery();
            deptQw.in(CasDepts::getZsjdwid,szdwdmList);
            List<CasDepts> deptsList = iCasDeptsService.list(deptQw);
            Map<String, String> collect = deptsList.stream().collect(Collectors.toMap(CasDepts::getZsjdwid, CasDepts::getZsjmc));
            for (Map<String,String> map:records) {
                if (collect.containsKey(map.get("szdwdm"))){
                    map.put("szdwdm",collect.get(map.get("szdwdm")));
                }
            }
            int total = Integer.parseInt((String)data.get("total"));
            int size = Integer.parseInt((String)data.get("size"));
            int current = Integer.parseInt((String)data.get("current"));
            page.setCurrent(current);
            page.setRecords(records);
            page.setTotal(total);
            page.setSize(size);
        }
        return page;
//
//
//
//
//        Page<Teachers> page = new Page(query.getCurrentPage(), query.getPageSize());
//        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
//        qw.like(StringUtils.isNotEmpty(query.getQueryStr()), Teachers::getXm, query.getQueryStr());
//        IPage<Teachers> teachers = page(page, qw);
//        List<String> dwhList = teachers.getRecords().stream().map(Teachers::getDwh).collect(Collectors.toList());
//        LambdaQueryWrapper<CasDepts> deptQw = Wrappers.lambdaQuery();
//        deptQw.in(dwhList.size() > 0,CasDepts::getZsjdwid,dwhList);
//        List<CasDepts> deptsList = iCasDeptsService.list(deptQw);
//        Map<String, String> collect = deptsList.stream().collect(Collectors.toMap(CasDepts::getZsjdwid, CasDepts::getZsjmc));
//        for (Teachers teacher: teachers.getRecords()) {
//           if (collect.containsKey(teacher.getDwh())){
//               teacher.setZsjmc(collect.get(teacher.getDwh()));
//           }
//        }
//        return teachers;
    }

    @Override
    public IPage<Teachers> queryPCPage(CommQueryListRequest query) {
        Page<Teachers> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.like(StringUtils.isNotEmpty(query.getQueryStr()), Teachers::getXm, query.getQueryStr()).eq(Teachers::getEnable,CommonConstant.DISABLE_FALSE);
        IPage<Teachers> teachers = page(page, qw);
       /* List<String> dwhList = teachers.getRecords().stream().map(Teachers::getDwh).collect(Collectors.toList());
        LambdaQueryWrapper<CasDepts> deptQw = Wrappers.lambdaQuery();
        deptQw.in(dwhList.size() > 0,CasDepts::getZsjdwid,dwhList);
        List<CasDepts> deptsList = iCasDeptsService.list(deptQw);
        Map<String, String> collect = deptsList.stream().collect(Collectors.toMap(CasDepts::getZsjdwid, CasDepts::getZsjmc));
        for (Teachers teacher: teachers.getRecords()) {
            if (collect.containsKey(teacher.getDwh())){
                teacher.setZsjmc(collect.get(teacher.getDwh()));
            }
        }*/
        return teachers;
    }

    @Override
    public Teachers queryById(IdRequest idRequest) {
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.eq(Teachers::getZgh,idRequest.getId()).eq(Teachers::getEnable,CommonConstant.DISABLE_FALSE);
        return getOne(qw);
    }
    /**
     * 获取所有讲师的下拉列表值
     * @param
     * @return
     */
    @Override
    public List<Map<String,Object>> queryAll() {
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.select(Teachers::getZgh,Teachers::getXm);
        return listMaps(qw);

    }

}
