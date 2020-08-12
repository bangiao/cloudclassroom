package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.dao.mapper.VideoMapper;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.CurriculumAuditListRequest;
import com.dingxin.web.service.ICurriculumService;
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
                    Objects.nonNull(data.getVideoField()),
                    Video::getVideoField,
                    data.getVideoField())
                .like(
                    Objects.nonNull(data.getLiveVideoField()),
                    Video::getLiveVideoField,
                    data.getLiveVideoField())
                .like(
                    Objects.nonNull(data.getDisableFlag()),
                    Video::getDisableFlag,
                    data.getDisableFlag())
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

    @Override
    public IPage queryPageList(CurriculumAuditListRequest query) {
        Page<Curriculum> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<Curriculum> wrapper = Wrappers.lambdaQuery();
        wrapper.like(Curriculum::getCurriculumName,query.getCurriculumName());
        wrapper.eq(Curriculum::getAuditFlag,query.getAuditFlag()).or(Wrappers -> Wrappers.eq(Curriculum::getEvaluateStatus,query.getAuditFlag()));
        IPage<Curriculum> iPage = curriculumService.page(page);
        return iPage;
    }
}
