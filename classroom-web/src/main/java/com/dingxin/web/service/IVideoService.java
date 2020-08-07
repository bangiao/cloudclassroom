package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.VideoInsertRequest;
import com.dingxin.pojo.request.VideoListRequest;
import com.dingxin.pojo.request.VideoUpdateRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface IVideoService extends IService<Video> {

    /**
     * 模板生产的模糊查询，目前没有使用
     * @param data
     * @return
     */
    List<Video> like(Video data);

    /**
     * 视频列表查询
     * @param query
     * @return
     */
    IPage<Video> listQuery(VideoListRequest query);
    /**
     * 删除课程(假删除)
     */
    void deleteVideo(List<Integer> videoIds);
    /**
     * 删除课程下的所有视频
     */
    void deleteCurriculumRelatedVideo(List<Integer> curriculumIds);
    /**
     * 保存视频
     * @param video
     */
    void saveVideoRelated(VideoInsertRequest video);
    /**
     * 更新视频对应课程的课程时长
     * @param curriculumId
     */
    void updateCurriculumVideoDuration(Integer curriculumId);
    /**
     * 更新视频的观看次数,视频每点击一次，次数增加 1
     * @param videoId
     */
    void updateCurrentVideoWatchAmount(IdRequest videoId);
    /**
     * 加在当前课程下所有有效的视频
     * @param curriculumId
     * @return
     */
    List<Video> loadAllValidVideoForCurrentCurriculum(Integer curriculumId);
    /**
     * 更新当前视频对应课程的观看次数
     * @param curriculumId
     */
    void updateCurriculumWatchAmount(Integer curriculumId);
    /**
     * 获取当前视频详细信息
     * @param id
     * @return
     */
    Video loadVideoDetails(IdRequest id);
    /**
     * 更新当前视频
     * @param video
     */
    void updateVideo(VideoUpdateRequest video);

}
