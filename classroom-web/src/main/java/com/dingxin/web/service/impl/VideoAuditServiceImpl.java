package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.VideoMapper;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.CurriculumAuditListRequest;
import com.dingxin.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dingxin.common.enums.ExceptionEnum.PARAMTER_ERROR;

/**
 *  服务接口实现类
 */
@SuppressWarnings("unchecked")
@Service
public class VideoAuditServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoAuditService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ICurriculumService curriculumService;
    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IVideoService videoService;
    /**
     * 评论service
     */
    @Autowired
    private IClassEvaluateService evaluateService;



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

    /**
     * 获取课程、章节、评论、视频
     * @param query
     * @return
     */
    @Override
    public IPage queryPageList(CurriculumAuditListRequest query) {
        Page<Curriculum> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<Curriculum> wrapper = Wrappers.lambdaQuery();
        wrapper.like(Curriculum::getCurriculumName,query.getCurriculumName());
        wrapper.eq(Curriculum::getAuditFlag,query.getAuditFlag())
                .or(Wrappers -> Wrappers.eq(Curriculum::getEvaluateStatus,query.getAuditFlag()));
        IPage<Curriculum> iPage = curriculumService.page(page);
        return iPage;
    }

    @Override
    public BaseResult<List<Chapter>> searchchapterbycurrid(CurriculumAuditListRequest query) {
        if (Objects.isNull(query.getCurriculumId())){
            throw new BusinessException(PARAMTER_ERROR);
        }
        LambdaQueryWrapper<Chapter> eq = Wrappers.<Chapter>lambdaQuery().eq(Chapter::getCurriculumId, query.getCurriculumId())
                .eq(Chapter::getDeleteFlag, CommonConstant.DEL_FLAG);
        List<Chapter> list = treeChapter(chapterService.list(eq));
        return BaseResult.success(list,"查询成功");
    }

    @Override
    public BaseResult<List<ClassEvaluate>> searchevaluatebycurrid(CurriculumAuditListRequest query) {
        if (Objects.isNull(query.getCurriculumId())){
            throw new BusinessException(PARAMTER_ERROR);
        }
        LambdaQueryWrapper<ClassEvaluate> eq = Wrappers.<ClassEvaluate>lambdaQuery().eq(ClassEvaluate::getClassId, query.getCurriculumId())
                .eq(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG);
        List<ClassEvaluate> list = evaluateService.list(eq);
        return BaseResult.success(list,"查询评论成功");
    }

    private List<Chapter> treeChapter(List<Chapter> list){
        List<Chapter> parents = list.stream().filter(c -> Objects.isNull(null)).collect(Collectors.toList());
        parents.forEach(c->{
            List<Chapter> child = new ArrayList<>();
            for (Chapter chapter : list) {
                if (chapter.getParentId()!=null && chapter.getParentId().equals(c.getId())){
                    LambdaQueryWrapper<Video> eq = Wrappers.<Video>lambdaQuery()
                            .eq(Video::getChapterId, chapter.getId())
                            .eq(Video::getDeleteFlag,CommonConstant.DEL_FLAG);
                    Video one = videoService.getOne(eq);
                    chapter.setVideoName(one.getVideoName());
                    chapter.setAuditFlag(one.getAuditFlag());
                    chapter.setFileId(one.getVideoField());
                    child.add(chapter);
                }

            }
            c.setChildChapter(child);
        });
        return parents;
    }



}
