package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.Video;
import com.dingxin.dao.mapper.VideoMapper;
import com.dingxin.web.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;


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
                        data.getCurriculumId())
;
        return videoMapper.selectList(query);


    }

}
