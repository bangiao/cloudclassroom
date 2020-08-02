package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface ICurriculumService extends IService<Curriculum> {

    List<Curriculum> like(Curriculum data);

    /**
     * 根据不同登录身份，获取课程列表
     */
    IPage<Curriculum> getPage(BaseQuery<CurriculumRequest> query);

    /**
     *  禁用课程
     */
    void disableCurriculum(List<Integer> curriculumIds);

}
