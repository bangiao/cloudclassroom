package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.dao.mapper.VideoMapper;
import com.dingxin.pojo.po.Video;
import com.dingxin.web.service.IVideoAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class VideoAuditServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoAuditService {

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
                    Objects.nonNull(data.getLiveVideo()),
                    Video::getLiveVideo,
                    data.getLiveVideo())
                .like(
                    Objects.nonNull(data.getValidFlag()),
                    Video::getValidFlag,
                    data.getValidFlag())
                .like(
                    Objects.nonNull(data.getAuditComments()),
                    Video::getAuditComments,
                    data.getAuditComments())
                .like(
                    Objects.nonNull(data.getAuditFlag()),
                    Video::getAuditFlag,
                    data.getAuditFlag())
                .like(
                    Objects.nonNull(data.getCurriculumId()),
                    Video::getCurriculumId,
                    data.getCurriculumId())
;
        return videoMapper.selectList(query);


    }

}
