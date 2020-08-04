package com.dingxin.web.service.impl;

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
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.VideoInsertRequest;
import com.dingxin.pojo.request.VideoListRequest;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
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
                    Objects.nonNull(data.getValidFlag()),
                    Video::getValidFlag,
                    data.getValidFlag())
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
    public IPage<Video> listQuery(BaseQuery<VideoListRequest> query) {
        LambdaQueryWrapper<Video> queryWrapper = Wrappers.lambdaQuery();
        VideoListRequest data = query.getData();
        if(Objects.nonNull(data)){
            String queryStr = data.getQueryStr();
            queryWrapper
                    .like(StringUtils.isNotEmpty(queryStr),
                        Video::getVideoName,queryStr)
                    .or()
                    .like(StringUtils.isNotEmpty(queryStr),
                            Video::getTeacherName,queryStr)
                    .eq(
                            Video::getDeleteFlag,
                            CommonConstant.DEL_FLAG)
                    .select(
                            Video::getId,
                            Video::getLiveVideo,
                            Video::getValidFlag,
                            Video::getVideoDuration,
                            Video::getVideoName,
                            Video::getVideoAttachment);
        }
        Page<Video> page = query.convertToPage();
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
                        Video::getValidFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .in(
                        Video::getId,
                        videoIds);

        update(disableQuery);
    }

    @Override
    public void deleteCurriculumRelatedVideo(List<Integer> curriculumIds) {
        if(CollectionUtils.isEmpty(curriculumIds)){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Video> disableQuery = Wrappers.<Video>lambdaUpdate().
                set(
                        Video::getValidFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .in(
                        Video::getCurriculumId,
                        curriculumIds);

        update(disableQuery);
    }

    @Override
    public void saveVideoRelated(VideoInsertRequest video) {
        Video videoWillSave = Video.builder()
                .auditFlag(CommonConstant.STATUS_NOAUDIT)
                .deleteFlag(CommonConstant.DEL_FLAG)
                .curriculumId(video.getCurriculumId())
                .videoDuration(video.getVideoDuration())
                .videoAttachment(video.getVideoAttachment())
                .validFlag(video.getValidFlag())
                .videoName(video.getVideoName())
                .liveVideo(video.getLiveVideo())
                .liveVideoId(video.getLiveVideoId())
                .build();
        Wrappers.<Video>lambdaQuery();
        //save to video
        save(videoWillSave);
        //同时更新课表表的总时长
        updateCurriculumVideoDuration(video.getCurriculumId());

    }
    //todo 需要改成异步的方法
    private void updateCurriculumVideoDuration(Integer curriculumId){
        if (curriculumId == null){
            return;
        }
        LambdaQueryWrapper<Video> videoDurationQuery = Wrappers.<Video>lambdaQuery()
                .eq(
                        Video::getCurriculumId, curriculumId)
                .select(
                        Video::getVideoDuration);

        List<Video> videos = list(videoDurationQuery);
        Long totalVideoDuration = 0L;
        //todo 线程安全问题
        for (Video video : videos) {
            Long videoDuration = video.getVideoDuration();
            totalVideoDuration +=videoDuration;
        }
        LambdaUpdateWrapper<Curriculum> updateCurriculumVideoDuration = Wrappers.<Curriculum>lambdaUpdate()
                .set(
                        Curriculum::getVideoDuration,
                        totalVideoDuration)
                .in(
                        Curriculum::getId,
                        curriculumId);

        curriculumService.update(updateCurriculumVideoDuration);
    }
}
