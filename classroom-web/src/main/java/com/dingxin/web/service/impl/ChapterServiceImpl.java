package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.ChapterMapper;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ChapterAndVideoInfo;
import com.dingxin.pojo.vo.ChapterSelectVo;
import com.dingxin.pojo.vo.ChildChapterVo;
import com.dingxin.pojo.vo.VideoVo;
import com.dingxin.web.service.IChapterService;
import com.dingxin.web.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *  服务接口实现类
 */
@Service
@Slf4j
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {

    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private IVideoService videoService;


    @Override
    public List<Chapter> like(Chapter data) {
        LambdaQueryWrapper<Chapter> query = Wrappers.<Chapter>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Chapter::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getChapterDesc()),
                    Chapter::getChapterDesc,
                    data.getChapterDesc())
                .like(
                    Objects.nonNull(data.getCurriculumId()),
                    Chapter::getCurriculumId,
                    data.getCurriculumId())
                .like(
                    Objects.nonNull(data.getParentId()),
                    Chapter::getParentId,
                    data.getParentId());

        return chapterMapper.selectList(query);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ChapterAndVideoInfo> loadChapterAndVideoInfo(Integer curriculumId) {
        if (curriculumId==null){
            log.error("查询课程对应章节和视频信息失败，当前课程id为空");
        }
        //父章节
        LambdaQueryWrapper<Chapter> parentChapterQuery = Wrappers.<Chapter>lambdaQuery()
                .eq(Chapter::getCurriculumId, curriculumId)
                .eq(Chapter::getDeleteFlag, CommonConstant.DEL_FLAG)
                .isNull(Chapter::getParentId)
                .orderBy(true,true,Chapter::getChapterOrderNumber)
                .select(
                        Chapter::getId,
                        Chapter::getChapterName,
                        Chapter::getChapterDesc,
                        Chapter::getChapterOrderNumber,
                        Chapter::getCurriculumId);
        //查询出所有父章节信息
        List<Chapter> allParents = list(parentChapterQuery);
        List<ChapterAndVideoInfo> chapterAndVideoVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(allParents)){
            for (Chapter parent : allParents) {
                ChapterAndVideoInfo perChapterAndVideoInfo = ChapterAndVideoInfo.builder()
                        .id(parent.getId())
                        .chapterDesc(parent.getChapterDesc())
                        .chapterName(parent.getChapterName())
                        .chapterOrderNumber(parent.getChapterOrderNumber())
                        .curriculumId(parent.getCurriculumId())
                        .childChapter(new ArrayList<>()).build();
                Integer parentId = parent.getId();
                //获取子章节
                LambdaQueryWrapper<Chapter> childrenChapterQuery = Wrappers.<Chapter>lambdaQuery()
                        .eq(Chapter::getParentId, parentId)
                        .eq(Chapter::getDeleteFlag, CommonConstant.DEL_FLAG)
                        .orderBy(true,true,Chapter::getChapterOrderNumber)
                        .select(
                                Chapter::getId,
                                Chapter::getChapterName,
                                Chapter::getChapterDesc,
                                Chapter::getChapterOrderNumber,
                                Chapter::getParentId,
                                Chapter::getCurriculumId);
                List<Chapter> childrenChapter = list(childrenChapterQuery);
                List<ChapterAndVideoInfo> childChapter = perChapterAndVideoInfo.getChildChapter();
                //获取子章节对应视频或者是直播视频
                if (CollectionUtils.isNotEmpty(childrenChapter)){
                    for (Chapter child : childrenChapter) {
                        LambdaQueryWrapper<Video> videoQuery = Wrappers.<Video>lambdaQuery()
                                .eq(Video::getChapterId,child.getId())
                                .eq(Video::getDeleteFlag,CommonConstant.DEL_FLAG)
                                .select(
                                        Video::getId,
                                        Video::getVideoName,
                                        Video::getVideoDuration,
                                        Video::getVideoSize,
                                        Video::getDisableFlag,
                                        Video::getVideoField,
                                        Video::getLiveVideoField);
                        //目前业务是只支持一节对应一个视频
                        Video videoPo = videoService.getOne(videoQuery);
                        VideoVo videoVo = VideoVo.convertToVo(videoPo);
                        ChapterAndVideoInfo chapterAndVideoInfo = ChapterAndVideoInfo.convertToVo(child);
                        if (chapterAndVideoInfo!=null){

                            chapterAndVideoInfo.setVideoInfo(videoVo);
                            childChapter.add(chapterAndVideoInfo);
                        }
                    }
                }

                chapterAndVideoVos.add(perChapterAndVideoInfo);
            }
        }

        return chapterAndVideoVos;
    }

    @Override
    public void removeChapterRecursively(List<Integer> parentChapterIds) {
        if (CollectionUtils.isEmpty(parentChapterIds)){
            if (log.isInfoEnabled())
                log.info("当前没有要被递归删除的章节");
            return;
        }
        LambdaUpdateWrapper<Chapter> updateWrapper = Wrappers.<Chapter>lambdaUpdate()
                .set(Chapter::getDeleteFlag, CommonConstant.DEL_FLAG_TRUE)
                .in(Chapter::getId, parentChapterIds)
                .or().in(Chapter::getParentId, parentChapterIds);

        update(updateWrapper);
    }

    @Override
    public void onlyRemoveChildChapterSelf(List<Integer> childChapterIds) {
        if (CollectionUtils.isEmpty(childChapterIds)){
            if (log.isInfoEnabled())
                log.info("当前没有要被删除的字章节");
            return;
        }
        LambdaUpdateWrapper<Chapter> updateWrapper = Wrappers.<Chapter>lambdaUpdate()
                .set(Chapter::getDeleteFlag, CommonConstant.DEL_FLAG_TRUE)
                .in(Chapter::getId, childChapterIds);

        update(updateWrapper);
    }
    @Override
    public List<Integer> loadChildrenIdByParentIds(List<Integer> childChapterIds) {
        if (CollectionUtils.isEmpty(childChapterIds)){
            if (log.isInfoEnabled())
                log.info("[loadChildrenIdByParentIds]:当前父章节id为空，不能查询其对应的子章节id");
            return Collections.emptyList();
        }

        //可以支持递归，如果之后有更多层级的章节
        LambdaQueryWrapper<Chapter> queryWrapper = Wrappers.<Chapter>lambdaQuery()
                .eq(Chapter::getDeleteFlag,CommonConstant.DEL_FLAG)
                .in(Chapter::getParentId, childChapterIds)
                .select(Chapter::getId);
        List<Chapter> chapters = list(queryWrapper);
        if (chapters==null) {
            return Collections.emptyList();
        }

        return chapters.stream().map(Chapter::getId).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ChapterSelectVo> loadChapterAndChildren(Integer curriculumId) {
        if (curriculumId == null){
            log.error("查询课程对应章节和子章节信息失败，当前课程id为空");
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        List<Chapter> parentChapters = loadValidParentChapters(curriculumId);
        if (CollectionUtils.isEmpty(parentChapters)){
            return Collections.emptyList();
        }
        return parentChapters.stream().map(parent->{
            ChapterSelectVo parentChapter = ChapterSelectVo.convertToVo(parent);
            // 查询出对应的子章节及信息
            List<Chapter> childChapters = loadChildChapterInfo(curriculumId, parent.getId());
            parentChapter.setChildrenChapter(ChildChapterVo.convertToVos(childChapters));
            return parentChapter;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Integer> loadCurrentCurriculumAllChapter(Integer curriculumId) {
        if (curriculumId == null){
            log.error("查询课程对应章节和子章节信息失败，当前课程id为空");
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaQueryWrapper<Chapter> chapterAll = Wrappers.<Chapter>lambdaQuery()
                .eq(Chapter::getCurriculumId, curriculumId)
                .eq(Chapter::getDeleteFlag,CommonConstant.DEL_FLAG)
                .select(Chapter::getId);
        List<Chapter> chapters = list(chapterAll);

        return chapters.stream().map(Chapter::getId).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private List<Chapter> loadValidParentChapters(Integer curriculumId) {
        // 获取所有有效父章节
        LambdaQueryWrapper<Chapter> loadParentChapterQuery = Wrappers.<Chapter>lambdaQuery()
                .eq(Chapter::getDeleteFlag, CommonConstant.DISABLE_FALSE)
                .eq(Chapter::getCurriculumId, curriculumId)
                .isNull(Chapter::getParentId).orderByAsc(Chapter::getChapterOrderNumber)
                .select(
                        Chapter::getId,
                        Chapter::getChapterDesc,
                        Chapter::getChapterName,
                        Chapter::getChapterOrderNumber);

        return list(loadParentChapterQuery);
    }

    @SuppressWarnings("unchecked")
    private List<Chapter> loadChildChapterInfo(Integer curriculumId, Integer parentId) {
        if (parentId==null){
            return Collections.emptyList();
        }
        LambdaQueryWrapper<Chapter> loadChildrenChapterQuery = Wrappers.<Chapter>lambdaQuery()
                .eq(Chapter::getDeleteFlag, CommonConstant.DISABLE_FALSE)
                .eq(Chapter::getCurriculumId, curriculumId)
                .eq(Chapter::getParentId,parentId)
                .orderBy(true,true,Chapter::getChapterOrderNumber)
                .select(
                        Chapter::getId,
                        Chapter::getChapterDesc,
                        Chapter::getChapterName,
                        Chapter::getChapterOrderNumber);
        return list(loadChildrenChapterQuery);
    }

    @Override
    public List<ChapterSelectVo> loadNoVideoChapterAndChildren(Integer curriculumId) {
        // 根据课程id获取有效的父章节
        List<Chapter> validParentChapters = loadValidParentChapters(curriculumId);
        return null;
    }

    @Override
    public void audit(Integer id, Integer auditStatus) {
        LambdaUpdateWrapper<Chapter> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.set(Chapter::getAuditFlag,auditStatus)
                .eq(Chapter::getId,id);
        chapterMapper.update(null,updateWrapper);
    }

    @SuppressWarnings("unchecked")
    //todo 这个方法暂时不需要，但是请不要删除，业务扩展的时候有用
    private List<Chapter> loadNoVideoChildChapterInfo(IdRequest curriculumId, Integer parentId) {
        if (parentId==null){
            return Collections.emptyList();
        }
        return Collections.emptyList();
        // 获取所有没有添加视频field和直播视频live_video_field的视频，可以理解为没有关联视频和直播视频的视频表数据
//        LambdaQueryWrapper<Video> withoutVideoInfoVideo = Wrappers.<Video>lambdaQuery()
//                .eq(Video::getDeleteFlag,CommonConstant.DEL_FLAG)
//                .eq(Video::getCurriculumId,curriculumId)
//                .isNull(Video::getVideoField)
//                .isNull(Video::getLiveVideoField)
//                .select(Video::getChapterId);
//        List<Video> emptyVideos = videoService.list(withoutVideoInfoVideo);
//        LambdaQueryWrapper<Chapter> loadChildrenChapterQuery = Wrappers.<Chapter>lambdaQuery()
//                .eq(Chapter::getDeleteFlag, CommonConstant.DISABLE_FALSE)
//                .eq(Chapter::getCurriculumId, curriculumId.getId())
//                .eq(Chapter::getParentId,parentId)
//                .orderBy(true,true,Chapter::getChapterOrderNumber)
//                .select(
//                        Chapter::getId,
//                        Chapter::getChapterDesc,
//                        Chapter::getChapterName,
//                        Chapter::getChapterOrderNumber);
//        return list(loadChildrenChapterQuery);
    }
}
