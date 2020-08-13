package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.dao.mapper.ChapterMapper;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.vo.ChapterAndVideoInfo;
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
                .select(Chapter::getId,Chapter::getChapterName,Chapter::getChapterDesc,Chapter::getChapterOrderNumber);

        List<Chapter> allParents = list(parentChapterQuery);
        List<ChapterAndVideoInfo> chapterAndVideoVos = new ArrayList<>();
        for (Chapter parent : allParents) {
            ChapterAndVideoInfo perChapterAndVideoInfo = ChapterAndVideoInfo.builder()
                    .chapterDesc(parent.getChapterDesc())
                    .chapterName(parent.getChapterName())
                    .chapterOrderNumber(parent.getChapterOrderNumber())
                    .childChapter(new ArrayList<>()).build();
            Integer parentId = parent.getId();
            //获取子章节
            LambdaQueryWrapper<Chapter> childrenChapterQuery = Wrappers.<Chapter>lambdaQuery()
                    .eq(Chapter::getParentId, parentId)
                    .eq(Chapter::getDeleteFlag, CommonConstant.DEL_FLAG)
                    .orderBy(true,true,Chapter::getChapterOrderNumber)
                    .select(Chapter::getId,Chapter::getChapterName,Chapter::getChapterDesc,Chapter::getChapterOrderNumber,Chapter::getParentId,Chapter::getCurriculumId);
            List<Chapter> childrenChapter = list(childrenChapterQuery);
            List<ChapterAndVideoInfo> childChapter = perChapterAndVideoInfo.getChildChapter();
            //获取子章节对应视频或者是直播视频
            for (Chapter child : childrenChapter) {
                LambdaQueryWrapper<Video> videoQuery = Wrappers.<Video>lambdaQuery()
                        .eq(Video::getChapterId,child.getId())
                        .eq(Video::getDeleteFlag,CommonConstant.DEL_FLAG)
                        .select(Video::getId,Video::getVideoName,Video::getVideoDuration,Video::getVideoSize,Video::getDisableFlag,Video::getVideoField,Video::getLiveVideoField);
                //目前业务是只支持一节对应一个视频
                Video videoPo = videoService.getOne(videoQuery);
                VideoVo videoVo = VideoVo.convertToVo(videoPo);
                ChapterAndVideoInfo chapterAndVideoInfo = ChapterAndVideoInfo.convertToVo(child);
                if (chapterAndVideoInfo!=null){

                    chapterAndVideoInfo.setVideoInfo(videoVo);
                    childChapter.add(chapterAndVideoInfo);
                }
            }

            chapterAndVideoVos.add(perChapterAndVideoInfo);
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
            return null;
        }

        //可以支持递归，如果之后有更多层级的章节
        LambdaQueryWrapper<Chapter> queryWrapper = Wrappers.<Chapter>lambdaQuery().in(Chapter::getParentId, childChapterIds).select(Chapter::getId);
        List<Chapter> chapters = list(queryWrapper);
        if (chapters==null) {
            return null;
        }

        return chapters.stream().map(Chapter::getId).collect(Collectors.toList());
    }
}
