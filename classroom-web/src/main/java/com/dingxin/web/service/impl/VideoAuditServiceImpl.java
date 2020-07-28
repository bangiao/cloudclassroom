package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.VideoAudit;
import com.dingxin.dao.mapper.VideoAuditMapper;
import com.dingxin.web.service.IVideoAuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class VideoAuditServiceImpl extends ServiceImpl<VideoAuditMapper, VideoAudit> implements IVideoAuditService {

    @Autowired
    private VideoAuditMapper videoAuditMapper;


    @Override
    public List<VideoAudit> like(VideoAudit data) {
        LambdaQueryWrapper<VideoAudit> query = Wrappers.<VideoAudit>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    VideoAudit::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getVideoName()),
                    VideoAudit::getVideoName,
                    data.getVideoName())
                .like(
                    Objects.nonNull(data.getVideoDuration()),
                    VideoAudit::getVideoDuration,
                    data.getVideoDuration())
                .like(
                    Objects.nonNull(data.getVideoAttachment()),
                    VideoAudit::getVideoAttachment,
                    data.getVideoAttachment())
                .like(
                    Objects.nonNull(data.getLiveVideo()),
                    VideoAudit::getLiveVideo,
                    data.getLiveVideo())
                .like(
                    Objects.nonNull(data.getValidFlag()),
                    VideoAudit::getValidFlag,
                    data.getValidFlag())
                .like(
                    Objects.nonNull(data.getAuditComments()),
                    VideoAudit::getAuditComments,
                    data.getAuditComments())
                .like(
                    Objects.nonNull(data.getAuditFlag()),
                    VideoAudit::getAuditFlag,
                    data.getAuditFlag())
                .like(
                    Objects.nonNull(data.getCurriculumId()),
                    VideoAudit::getCurriculumId,
                    data.getCurriculumId())
;
        return videoAuditMapper.selectList(query);


    }

}
