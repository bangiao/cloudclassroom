package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseQuery;
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

    List<Video> like(Video data);

    IPage<Video> listQuery(BaseQuery<VideoListRequest> query);
    /**
     * 删除课程(假删除)
     */
    void deleteVideo(List<Integer> videoIds);
    /**
     * 删除课程(假删除删除课程的同时也会删除课程下的所有视频)
     */
    void deleteCurriculumRelatedVideo(List<Integer> curriculumIds);

    void saveVideoRelated(VideoInsertRequest video);

    void updateCurriculumVideoDuration(Integer curriculumId);

    void updateCurrentVideoWatchAmount(IdRequest videoId);

    List<Video> loadAllValidVideoForCurrentCurriculum(Integer curriculumId);

    void updateCurriculumWatchAmount(Integer curriculumId);

    Video loadVideoDetails(IdRequest id);

    void updateVideo(VideoUpdateRequest video);

}
