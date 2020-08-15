package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.request.*;
import com.dingxin.pojo.vo.CurriculumDetailsVo;
import com.dingxin.pojo.vo.CurriculumPcVo;
import com.dingxin.pojo.vo.CurriculumVo;

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
     *  启用课程(如果课程对应的讲师被禁用，则当前课程启用失败)
     */
    void enableCurriculum(List<Integer> curriculumIds);
    /**
     * 删除课程
     */
    void deleteCurriculum(List<Integer> curriculumIds);
    /**
     * 删除课程及相关视频及相关章节
     */
    void deleteCurriculumAndRelated(List<Integer> curriculumIds);
    /**
     * 查询详细课程信息，不查询已经删除的信息
     */
    CurriculumDetailsVo loadCurriculumDetails(IdRequest id);
    /**
     * 更新课程的观看时长或观看次数
     * @param videoDuration
     * @param curriculumId
     * @param watchTimes
     */
    void updateCurrentCurriculumVideoDurationOrWatchAmount(Long videoDuration,Integer curriculumId,Long watchTimes);
    /**
     * 课程列表全部数据
     * @return
     */
    List<Curriculum> listAll(TeacherIdRequest idRequest);
    /**
     * 根据讲师查询课程
     * @param id
     * @return
     */
    List<Curriculum> searchByTeacher(IdRequest id);
    /**
     * 保存课程信息及对应的章节及视频信息
     * @param curriculumChapterAndVideoInfo
     */
    void saveCurriculumInfo(CurriculumInsertRequest curriculumChapterAndVideoInfo);
    /**
     * 修改课程数据，和对应章节视频信息
     * @param curriculumUpdateRequest
     */
    void updateCurriculumInfo(CurriculumUpdateRequest curriculumUpdateRequest);
    /**
     * 修改课程的审核状态
     * @param curriculumId
     * @param statusCode
     */
    void updateCurriculumAuditFlag(Integer curriculumId,Integer statusCode);

    /**
     * pc查询最新课程列表
     * @param
     */
    BaseResult<Page<CurriculumPcVo>> leatestList(IdRequest idRequest);

    BaseResult<Page<CurriculumVo>> ListbyDept(IdRequest idRequest);
    /**
     * 根据课程id获取当前课程下的教师信息
     * @param curriculumId
     * @return
     */
    Teachers loadCurrentCurriculumTeacherInfo(Integer curriculumId);
}
