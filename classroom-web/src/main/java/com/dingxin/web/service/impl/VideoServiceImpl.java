package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.dao.mapper.VideoMapper;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.VideoListRequest;
import com.dingxin.web.service.IVideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    @Resource
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
                            Video::getTeacherName,queryStr);
        }
        Page<Video> page = query.convertToPage();
        IPage<Video> iPage = videoMapper.selectPage(page,queryWrapper);
        return iPage;
    }

}
