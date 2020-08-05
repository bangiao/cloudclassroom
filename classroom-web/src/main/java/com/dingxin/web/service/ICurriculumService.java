package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumFuzzyQuery4List;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface ICurriculumService extends IService<Curriculum> {

    List<Curriculum> like(Curriculum data);

    /**
     * 根据不同登录身份，获取课程列表
     */
    IPage<Curriculum> getPage(CurriculumFuzzyQuery4List query);

    /**
     *  禁用课程
     */
    void disableCurriculum(List<Integer> curriculumIds);

    /**
     * 删除课程
     */
    void deleteCurriculum(List<Integer> curriculumIds);

    /**
     * 删除课程及相关视频
     */
    void deleteCurriculumAndRelated(List<Integer> curriculumIds);

    /**
     * 查询详细课程信息，不查询已经删除的信息
     */
    Curriculum loadCurriculumDetails(IdRequest id);

    void updateCurrentCurriculumVideoDurationOrWatchAmount(Long videoDuration,Integer curriculumId,Long watchTimes);
}
