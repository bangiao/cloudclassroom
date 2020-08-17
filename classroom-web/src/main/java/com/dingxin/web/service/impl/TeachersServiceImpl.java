package com.dingxin.web.service.impl;
import com.alibaba.druid.pool.WrapperAdapter;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.dao.mapper.TeachersMapper;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.PersonInfoRequest;
import com.dingxin.pojo.request.WidRequest;
import com.dingxin.pojo.vo.TeacherVo;
import com.dingxin.web.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.web.shiro.ShiroUtils;
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
    @Autowired
    private ICommonDataService commonDataService;
    @Autowired
    private ICurriculumService curriculumService;
    @Autowired
    private IProjectManagementService projectManagementService;
    @Autowired
    private IStduentClassSeeRecordService stduentClassSeeRecordService;
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
    public IPage queryPage(CommQueryListRequest query) {
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
            List<LinkedHashMap<String,String>> records = (List) data.get("records");
            Map<String, String> deptMap = iCasDeptsService.list()
                    .stream()
                    .collect(Collectors.toMap(CasDepts::getZsjdwid, CasDepts::getZsjmc));
            records.stream().forEach(re->{
                String szdwdm = re.get("szdwdm");
                if (StringUtils.isNotEmpty(szdwdm)){
                    if (deptMap.containsKey(szdwdm)){
                        re.put("szdwmc",deptMap.get(szdwdm));
                    }
                }
            });
            //获取是否禁用
            List<Object> zghList = records.stream().map(p->p.get("zgh")).collect(Collectors.toList());
            LambdaUpdateWrapper<Teachers> qw = Wrappers.<Teachers>lambdaUpdate()
                    .in(Teachers::getZgh, zghList);
            List<Teachers> teachers = this.teachersMapper.selectList(qw);
            Map<String, Integer> teacherMap = teachers.stream().collect(Collectors.toMap(Teachers::getZgh, Teachers::getEnable));
            records.stream().forEach(s->{
                if (teacherMap.containsKey(s.get("zgh"))){
                    s.put("enable",teacherMap.get(s.get("zgh")).toString());
                }else {
                    s.put("enanle","0");
                }
            });
            int total = Integer.parseInt((String)data.get("total"));
            int size = Integer.parseInt((String)data.get("size"));
            int current = Integer.parseInt((String)data.get("current"));
            page.setCurrent(current);
            page.setRecords(records);
            page.setTotal(total);
            page.setSize(size);
        }
        return page;
    }

    @Override
    public IPage<Teachers> queryPCPage(CommQueryListRequest query) {
        IPage teachersIPage = this.queryPage(query);
        List<LinkedHashMap<String,Object>> records = teachersIPage.getRecords();
        List<Object> zghList = records.stream().map(p->p.get("zgh")).collect(Collectors.toList());
        LambdaUpdateWrapper<Teachers> qw = Wrappers.<Teachers>lambdaUpdate()
                .in(Teachers::getZgh, zghList);
        List<Teachers> teachers = this.teachersMapper.selectList(qw);
        Map<String, Integer> teacherMap = teachers.stream().collect(Collectors.toMap(Teachers::getZgh, Teachers::getEnable));
        records.stream().forEach(s->{
            if (teacherMap.containsKey(s.get("zgh"))){
                s.put("enable",teacherMap.get(s.get("zgh")));
            }else {
                s.put("enanle",0);
            }
        });

        return teachersIPage;
    }

    @Override
    public TeacherVo queryById(WidRequest idRequest) {
        TeacherVo teacherVo = new TeacherVo();
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.eq(Teachers::getZgh,idRequest.getWid());
        Teachers getOne = getOne(qw);
        BaseResult photo = commonDataService.photo(idRequest.getWid().toString());

        if (Objects.isNull(getOne)){
            teacherVo.setZgh(idRequest.getWid());
            teacherVo.setIntroduction(null);
            teacherVo.setEnable(CommonConstant.DISABLE_FALSE);
        }else {
            teacherVo.setZgh(getOne.getZgh());
            teacherVo.setIntroduction(getOne.getIntroduction());
            teacherVo.setEnable(getOne.getEnable());
        }
        teacherVo.setUrl(photo.getData().toString());
        //查出对应的课程列表
        LambdaQueryWrapper<Curriculum> curriculumQw = Wrappers.<Curriculum>lambdaQuery()
                .eq(Curriculum::getTeacherId, idRequest.getWid());
        teacherVo.setCurriculumList(curriculumService.list(curriculumQw));
        //查出对应的专题列表
        LambdaQueryWrapper<ProjectManagement> projectQw = Wrappers.<ProjectManagement>lambdaQuery()
                .eq(ProjectManagement::getLecturerId, idRequest.getWid())
                .eq(ProjectManagement::getDelFlag,CommonConstant.DEL_FLAG);
        teacherVo.setProjectManagementList(projectManagementService.list(projectQw));
        return teacherVo;
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

    @Override
    public BaseResult updateIntroduction(Teachers teachers) {
        teachers.setModifyTime(LocalDateTime.now());
        return BaseResult.success(this.saveOrUpdate(teachers));
    }

    @Override
    public BaseResult disEnable(Teachers teachers) {
        teachers.setModifyTime(LocalDateTime.now());
        this.saveOrUpdate(teachers);
        /*//禁用讲师相关课程
        LambdaUpdateWrapper<Curriculum> curriculumQw = Wrappers.<Curriculum>lambdaUpdate()
                .set(Curriculum::getDisableFlag, CommonConstant.DISABLE_TRUE)
                .eq(Curriculum::getTeacherId, teachers.getZgh());
        curriculumService.update(curriculumQw);
        //禁用讲师相关专题
        LambdaUpdateWrapper<ProjectManagement> projectQw = Wrappers.<ProjectManagement>lambdaUpdate()
                .set(ProjectManagement::getEnable, CommonConstant.DISABLE_TRUE)
                .eq(ProjectManagement::getLecturerId, teachers.getZgh());
        projectManagementService.update(projectQw);*/
        return BaseResult.success().setMsg("禁用讲师成功");
    }

    @Override
    public BaseResult enable(Teachers teachers) {
        teachers.setModifyTime(LocalDateTime.now());
        this.saveOrUpdate(teachers);
        //启用讲师相关课程
        /*LambdaUpdateWrapper<Curriculum> curriculumQw = Wrappers.<Curriculum>lambdaUpdate()
                .set(Curriculum::getDisableFlag, CommonConstant.DISABLE_FALSE)
                .eq(Curriculum::getTeacherId, teachers.getZgh());
        curriculumService.update(curriculumQw);*/
        //启用讲师相关专题
       /* LambdaUpdateWrapper<ProjectManagement> projectQw = Wrappers.<ProjectManagement>lambdaUpdate()
                .set(ProjectManagement::getEnable, CommonConstant.DISABLE_FALSE)
                .eq(ProjectManagement::getLecturerId, teachers.getZgh());
        projectManagementService.update(projectQw);*/
        return BaseResult.success().setMsg("启用讲师成功");
    }

    @Override
    public Map<String,Object> techerInformation(String teachersId) {
        CommQueryListRequest qu = new CommQueryListRequest();
        qu.setQueryStr(teachersId);
        IPage iPage = this.queryPage(qu);
        List<LinkedHashMap<String,Object>> records = iPage.getRecords();
        LinkedHashMap<String, Object> map=null;
        if (CollectionUtils.isNotEmpty(records)){
             map = records.get(0);
        }

        return map;
    }

    @Override
    public String queryInfo() {
        LambdaUpdateWrapper<Teachers> qw = Wrappers.<Teachers>lambdaUpdate()
                .eq(Teachers::getZgh, ShiroUtils.getUserId());
        Teachers one = this.getOne(qw);
        return Objects.isNull(one)? " ":one.getIntroduction();
    }

    @Override
    public Boolean UpdatePerson(PersonInfoRequest personInfoRequest) {
        Teachers teachers = new Teachers();
        teachers.setZgh(ShiroUtils.getUserId());
        teachers.setIntroduction(personInfoRequest.getIntroduction());
        teachers.setModifyTime(LocalDateTime.now());
        return saveOrUpdate(teachers);
    }


}
