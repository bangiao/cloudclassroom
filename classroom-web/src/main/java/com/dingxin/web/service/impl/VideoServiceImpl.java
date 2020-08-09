package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.dao.mapper.VideoMapper;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.*;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
@Slf4j
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Resource
    private VideoMapper videoMapper;

    @Autowired
    private ICurriculumService curriculumService;


    @Override
    public List<Video> like(Video data) {
        LambdaQueryWrapper<Video> query = Wrappers.<Video>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Video::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getVideoName()),
                    Video::getVideoName,
                    data.getVideoName())
                .like(
                    Objects.nonNull(data.getVideoDuration()),
                    Video::getVideoDuration,
                    data.getVideoDuration())
                .like(
                    Objects.nonNull(data.getVideoAttachment()),
                    Video::getVideoAttachment,
                    data.getVideoAttachment())
                .like(
                    Objects.nonNull(data.getLiveVideoId()),
                    Video::getLiveVideoId,
                    data.getLiveVideoId())
                .like(
                    Objects.nonNull(data.getTeacherId()),
                    Video::getTeacherId,
                    data.getTeacherId())
                .like(
                    Objects.nonNull(data.getDisableFlag()),
                    Video::getDisableFlag,
                    data.getDisableFlag())
                .like(
                    Objects.nonNull(data.getAuditFlag()),
                    Video::getAuditFlag,
                    data.getAuditFlag())
                .like(
                    Objects.nonNull(data.getChapterId()),
                    Video::getChapterId,
                    data.getChapterId())
                .like(
                        Objects.nonNull(data.getCurriculumId()),
                        Video::getCurriculumId,
                        data.getCurriculumId());
        return videoMapper.selectList(query);


    }

    @Override
    public IPage<Video> listQuery(VideoListRequest query) {
        LambdaQueryWrapper<Video> queryWrapper = Wrappers.lambdaQuery();
        String queryStr = query.getQueryStr();
        queryWrapper
                .like(StringUtils.isNotEmpty(queryStr),
                        Video::getVideoName, queryStr)
                .or()
                .like(StringUtils.isNotEmpty(queryStr),
                        Video::getTeacherName, queryStr)
                .eq(
                        Video::getDeleteFlag,
                        CommonConstant.DEL_FLAG)
                .select(
                        Video::getId,
                        Video::getLiveVideo,
                        Video::getDisableFlag,
                        Video::getVideoDuration,
                        Video::getVideoName,
                        Video::getVideoAttachment);
        Page<Video> page = new Page(query.getCurrentPage(),query.getPageSize());
        IPage<Video> iPage = videoMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public void deleteVideo(List<Integer> videoIds) {
        if(CollectionUtils.isEmpty(videoIds)){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Video> disableQuery = Wrappers.<Video>lambdaUpdate().
                set(
                        Video::getDeleteFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .in(
                        Video::getId,
                        videoIds);

        update(disableQuery);

        //更新对应课程的观看次数和观看时长
        updateRelatedCurriculumDurationAndWatchAmount(videoIds);
    }

    @Override
    public void deleteCurriculumRelatedVideo(List<Integer> curriculumIds) {
        if(CollectionUtils.isEmpty(curriculumIds)){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Video> disableQuery = Wrappers.<Video>lambdaUpdate().
                set(
                        Video::getDisableFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .in(
                        Video::getCurriculumId,
                        curriculumIds);

        update(disableQuery);
    }

    @Override
    public void saveVideoRelated(VideoInsertRequest video) {
        //todo 记得视频的大小这里还没有保存
        Video videoWillSave = Video.builder()
                .auditFlag(CommonConstant.STATUS_NOAUDIT)
                .deleteFlag(CommonConstant.DEL_FLAG)
                .curriculumId(video.getCurriculumId())
                .videoDuration(video.getVideoDuration())
                .videoAttachment(video.getVideoAttachment())
                .disableFlag(video.getDisableFlag())
                .videoName(video.getVideoName())
                .liveVideo(video.getLiveVideo())
                .liveVideoId(video.getLiveVideoId())
                .watchAmount(CommonConstant.WATCH_AMOUNT_INITIAL_VALUE)
                .build();
        //save to video
        save(videoWillSave);

    }
    //todo 需要改成异步的方法
    @Override
    public void updateCurriculumVideoDuration(Integer curriculumId){
        if (curriculumId!=null){
            if (log.isWarnEnabled())
                log.warn("更新当前视频时长失败，传参为 curriculumIds:{}",curriculumId);
            return;
        }
        List<Video> videos = loadAllValidVideoForCurrentCurriculum(curriculumId);
        Long totalVideoDuration = 0L;
        //todo 线程安全问题
        for (Video video : videos) {//遍历根据当前课程id查出的所有视频，累加课程时长，最终的结果更新到对应的课程总时长
            Long videoDuration = video.getVideoDuration();
            totalVideoDuration +=(videoDuration == null ? CommonConstant.WATCH_AMOUNT_INITIAL_VALUE : videoDuration);
        }

        curriculumService.updateCurrentCurriculumVideoDurationOrWatchAmount(totalVideoDuration,curriculumId,null);
    }

    @Override
    public void updateCurrentVideoWatchAmount(IdRequest videoId) {
        if (Objects.isNull(videoId)){
            if (log.isWarnEnabled())
                log.warn("更新当前视频观看次数失败");
            return;
        }
        LambdaQueryWrapper<Video> getByIdQuery = Wrappers.<Video>lambdaQuery()
                .eq(
                        Video::getId,
                        videoId.getId())
                .eq(
                        Video::getDisableFlag,
                        CommonConstant.DISABLE_FALSE)
                .eq(
                        Video::getDeleteFlag,
                        CommonConstant.DEL_FLAG)
                .eq(
                        Video::getAuditFlag,
                        CommonConstant.STATUS_AUDIT)
                .select(
                        Video::getWatchAmount);
        //查出当前视频
        Video currentVideo = getOne(getByIdQuery);

        if (Objects.isNull(currentVideo)){
            if (log.isWarnEnabled())
                log.warn("更新当前视频观看次数失败,没有查到有关视频");
            return;
        }
        Long watchAmount = currentVideo.getWatchAmount();
        watchAmount =  watchAmount==null ? CommonConstant.WATCH_AMOUNT_INITIAL_VALUE : watchAmount+CommonConstant.PER_WATCH_INCREASE;
        LambdaUpdateWrapper<Video> updateCurrentVideoQuery = Wrappers.<Video>lambdaUpdate()
                .set(
                        Video::getWatchAmount,
                        watchAmount)
                .in(
                        Video::getId,
                        videoId.getId());

        //跟新当前视频观看次数
        update(updateCurrentVideoQuery);
        //同时更新当前视频对应课程的总观看次数
        updateCurriculumWatchAmount(currentVideo.getCurriculumId());

    }


    @Override
    public List<Video> loadAllValidVideoForCurrentCurriculum(Integer curriculumId) {
        if (curriculumId == null){
            if (log.isWarnEnabled())
                log.warn("loadAllValidVideoForCurrentCurriculum 失败");
            return null;
        }
        LambdaQueryWrapper<Video> videoDurationQuery = Wrappers.<Video>lambdaQuery()
                .eq(
                        Video::getCurriculumId, curriculumId)
                .eq(
                        Video::getDeleteFlag,
                        CommonConstant.DEL_FLAG)
                .eq(
                        Video::getAuditFlag,
                        CommonConstant.STATUS_AUDIT)
                .eq(
                        Video::getDisableFlag,
                        CommonConstant.DISABLE_FALSE)
                .select(
                        Video::getVideoDuration);

        return list(videoDurationQuery);
    }

    @Override
    public void updateCurriculumWatchAmount(Integer curriculumId) {
        if (curriculumId == null){
            if (log.isWarnEnabled())
                log.warn("updateCurriculumWatchAmount 失败");
            return;
        }
        //获取当前视频对应课程的所有视频
        List<Video> videos = loadAllValidVideoForCurrentCurriculum(curriculumId);
        Long totalWatchAmount = 0L;
        //todo 线程安全问题
        for (Video video : videos) {//遍历根据当前课程id查出的所有视频，累加课程时长，最终的结果更新到对应的课程总时长
            Long watchAmount = video.getWatchAmount();
            totalWatchAmount += (watchAmount == null ? CommonConstant.WATCH_AMOUNT_INITIAL_VALUE : watchAmount);
        }

        //更新当前视频对应课程的总观看次数
        curriculumService.updateCurrentCurriculumVideoDurationOrWatchAmount(null,curriculumId,totalWatchAmount);

    }

    //单独提出来，之后做成异步执行
    private void updateRelatedCurriculumDurationAndWatchAmount(List<Integer> videoIds){

        LambdaQueryWrapper<Video> listQueryParams = Wrappers.<Video>lambdaQuery()
                .in(
                        Video::getId,
                        videoIds)
                .select(
                        Video::getCurriculumId,
                        Video::getChapterId);
        List<Video> listVideos = list(listQueryParams);
        for (Video video : listVideos) {
            Integer curriculumId = video.getCurriculumId();
            //更新视频对应课程的观看次数
            updateCurriculumWatchAmount(curriculumId);
            //更新对应课程的时长
            updateCurriculumVideoDuration(curriculumId);
        }
    }

    @Override
    public Video loadVideoDetails(IdRequest id) {
        LambdaQueryWrapper<Video> loadOneQuery = Wrappers.<Video>lambdaQuery()
                .eq(
                        Video::getDeleteFlag,
                        CommonConstant.DEL_FLAG)
                .eq(
                        Video::getAuditFlag,
                        CommonConstant.STATUS_AUDIT)
                .select(
                        Video::getId,
                        Video::getVideoName,
                        Video::getVideoDuration,
                        Video::getVideoAttachment,
                        Video::getLiveVideo,
                        Video::getDisableFlag,
                        Video::getVideoSize);
        return getOne(loadOneQuery);
    }

    @Override
    public void updateVideo(VideoUpdateRequest video) {

        Video videoWillUpdate = Video.builder()
                .auditFlag(CommonConstant.STATUS_NOAUDIT)
                .curriculumId(video.getCurriculumId())
                .videoDuration(video.getVideoDuration())
                .videoAttachment(video.getVideoAttachment())
                .disableFlag(video.getDisableFlag())
                .videoName(video.getVideoName())
                .liveVideo(video.getLiveVideo())
                .liveVideoId(video.getLiveVideoId())
                .videoSize(video.getVideoSize())
                .build();
        LambdaUpdateWrapper<Video> updateCase = Wrappers.<Video>lambdaUpdate().eq(Video::getId, video.getId());

        update(videoWillUpdate,updateCase);
    }

    @Override
    public void updateVideoRelatedCurriculumAuditFlag(Integer curriculumId) {
        if (curriculumId == null){
            if (log.isWarnEnabled())
                log.warn("loadAllValidVideoForCurrentCurriculum 失败");
            return ;
        }
        LambdaQueryWrapper<Video> videoDurationQuery = Wrappers.<Video>lambdaQuery()
                .eq(
                        Video::getCurriculumId, curriculumId)
                .eq(
                        Video::getDeleteFlag,
                        CommonConstant.DEL_FLAG)
                .eq(
                        Video::getAuditFlag,
                        CommonConstant.STATUS_UNAPPROVED)
                .eq(
                        Video::getDisableFlag,
                        CommonConstant.DISABLE_FALSE)
                .select(
                        Video::getVideoDuration);

        list(videoDurationQuery);

    }

    /**
     * 获取视频审核列表
     * @param query
     * @return
     */
    @Override
    public IPage queryPageList(VideoListRequest query) {
        Page<Video> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<Video> qw = Wrappers.lambdaQuery();
        qw.eq(Video::getDeleteFlag,CommonConstant.DEL_FLAG);
        qw.eq(Video::getCurriculumId,query.getCurriculumId());
        IPage<Video> iPage = this.page(page);
        return iPage;
    }

    /**
     * 视频审核-单个
     * @param videoAudit
     */
    @Override
    public void audit(VideoAuditRequest videoAudit) {
        LambdaUpdateWrapper<Video> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(Video::getAuditFlag,videoAudit.getAuditStatus());
        wrapper.eq(Video::getId,videoAudit.getId());
        wrapper.eq(Video::getCurriculumId,videoAudit.getCurriculumId());
        this.update(wrapper);
    }
}
