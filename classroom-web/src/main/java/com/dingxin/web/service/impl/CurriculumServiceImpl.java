package com.dingxin.web.service.impl;

import com.alibaba.druid.pool.WrapperAdapter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.dao.mapper.CurriculumMapper;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.CurriculumInsertRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.TeacherIdRequest;
import com.dingxin.pojo.vo.ChapterAndVideoInfo;
import com.dingxin.pojo.vo.CurriculumDetailsVo;
import com.dingxin.pojo.vo.VideoVo;
import com.dingxin.web.service.IChapterService;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IProjectCurriculumService;
import com.dingxin.web.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类(公共实现类，该类的实现方法不会根据角色不同而差异化功能)
 */
@Service
@Slf4j
public abstract class CurriculumServiceImpl extends ServiceImpl<CurriculumMapper, Curriculum> implements ICurriculumService {

    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private IVideoService videoService;
    @Autowired
    private IChapterService chapterService;
    @Autowired
    private IProjectCurriculumService projectCurriculumService;

    @Override
    public List<Curriculum> like(Curriculum data) {
        LambdaQueryWrapper<Curriculum> query = Wrappers.<Curriculum>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Curriculum::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getCurriculumName()),
                    Curriculum::getCurriculumName,
                    data.getCurriculumName())
                .like(
                    Objects.nonNull(data.getCurriculumType()),
                    Curriculum::getCurriculumType,
                    data.getCurriculumType())
                .like(
                    Objects.nonNull(data.getCurriculumDesc()),
                    Curriculum::getCurriculumDesc,
                    data.getCurriculumDesc())
                .like(
                    Objects.nonNull(data.getVideoName()),
                    Curriculum::getVideoName,
                    data.getVideoName())
                .like(
                    Objects.nonNull(data.getVideoDuration()),
                    Curriculum::getVideoDuration,
                    data.getVideoDuration())
                .like(
                    Objects.nonNull(data.getVideoAttachment()),
                    Curriculum::getVideoAttachment,
                    data.getVideoAttachment())
                .like(
                    Objects.nonNull(data.getLiveVideo()),
                    Curriculum::getLiveVideo,
                    data.getLiveVideo())
                .like(
                    Objects.nonNull(data.getAddVideoFlag()),
                    Curriculum::getAddVideoFlag,
                    data.getAddVideoFlag())
                .like(
                    Objects.nonNull(data.getDisableFlag()),
                    Curriculum::getDisableFlag,
                    data.getDisableFlag())
                .like(
                    Objects.nonNull(data.getDepartmentId()),
                    Curriculum::getDepartmentId,
                    data.getDepartmentId())
                .like(
                    Objects.nonNull(data.getTopicId()),
                    Curriculum::getTopicId,
                    data.getTopicId())
                .like(
                    Objects.nonNull(data.getTeacherName()),
                    Curriculum::getTeacherName,
                    data.getTeacherName())
                .like(
                    Objects.nonNull(data.getWatchAmount()),
                    Curriculum::getWatchAmount,
                    data.getWatchAmount())
                .like(
                    Objects.nonNull(data.getDeleteFlag()),
                    Curriculum::getDeleteFlag,
                    data.getDeleteFlag())
;
        return curriculumMapper.selectList(query);


    }

    @Override
    @Transactional
    public void disableCurriculum(List<Integer> curriculumIds) {

        if(curriculumIds == null || curriculumIds.isEmpty()){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Curriculum> disableQuery = Wrappers.<Curriculum>lambdaUpdate().
                set(
                        Curriculum::getDisableFlag,
                        CommonConstant.DISABLE_TRUE)
                .in(
                        Curriculum::getId,
                        curriculumIds);

        update(disableQuery);
        //将禁用课程更新至专题中间表  Author by nieya
        LambdaUpdateWrapper<ProjectCurriculum> projectQw = Wrappers.<ProjectCurriculum>lambdaUpdate()
                .set(ProjectCurriculum::getEnable, CommonConstant.DISABLE_TRUE)
                .in(ProjectCurriculum::getCurriculumId, curriculumIds);
        projectCurriculumService.update(projectQw);
    }

    @Override
    public void enableCurriculum(List<Integer> curriculumIds) {
        if(CollectionUtils.isEmpty(curriculumIds)){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Curriculum> disableQuery = Wrappers.<Curriculum>lambdaUpdate().
                set(
                        Curriculum::getDisableFlag,
                        CommonConstant.DISABLE_FALSE)
                .in(
                        Curriculum::getId,
                        curriculumIds);

        update(disableQuery);
    }

    @Override
    @Transactional
    public void deleteCurriculum(List<Integer> curriculumIds) {
        if(curriculumIds == null || curriculumIds.isEmpty()){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        //将课程表本身flag设置为删除
        LambdaUpdateWrapper<Curriculum> disableQuery = Wrappers.<Curriculum>lambdaUpdate().
                set(
                        Curriculum::getDeleteFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .in(
                        Curriculum::getId,
                        curriculumIds);

        update(disableQuery);
    }

    @Override//这里用两个方法删除因为需要事务,两个方法需要同时成功
    @Transactional
    public void deleteCurriculumAndRelated(List<Integer> curriculumIds) {

        deleteCurriculum(curriculumIds);
        videoService.deleteCurriculumRelatedVideo(curriculumIds);
    }

    @Override
    public CurriculumDetailsVo loadCurriculumDetails(IdRequest id) {
        //查出当前课程信息
        LambdaQueryWrapper<Curriculum> deleteQuery = Wrappers.<Curriculum>lambdaQuery()
                .ne(
                        Curriculum::getDeleteFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .eq(
                        Curriculum::getId,id.getId())
                .select(
                        Curriculum::getId,
                        Curriculum::getTeacherName,
                        Curriculum::getCurriculumName,
                        Curriculum::getCurriculumType,
                        Curriculum::getCurriculumDesc,
                        Curriculum::getClassTypeId,
                        Curriculum::getTopicName,
                        Curriculum::getTeacherName,
                        Curriculum::getCurriculumImage);
        //查询出当前课程对应的章节及章节对应的视频信息
        List<ChapterAndVideoInfo> chapterAndVideoInfos = chapterService.loadChapterAndVideoInfo(id.getId());
        //课程信息
        Curriculum curriculum = getOne(deleteQuery);
        CurriculumDetailsVo curriculumDetailsVo = CurriculumDetailsVo.convertToVo(curriculum);
        if (curriculumDetailsVo != null)
            curriculumDetailsVo.setChapter(chapterAndVideoInfos);

        return curriculumDetailsVo;
    }

    @Override
    public void updateCurrentCurriculumVideoDurationOrWatchAmount(Long videoDuration,Integer curriculumId,Long watchTimes) {
        if (curriculumId ==null){
            log.error("更新当前课程课程总时长失败，传参为 videoDuration:{},curriculumId:{}",videoDuration,curriculumId);
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Curriculum> updateCurriculumVideoDuration = Wrappers.<Curriculum>lambdaUpdate()
                .set(
                        !(videoDuration==null || videoDuration < 0),
                        Curriculum::getVideoDuration,
                        videoDuration)
                .set(
                        !(watchTimes==null || watchTimes<0) ,
                        Curriculum::getWatchAmount,
                        watchTimes)
                .in(
                        Curriculum::getId,
                        curriculumId);

        update(updateCurriculumVideoDuration);
        //将次数更新到专题中间表中  Author by nieya
        LambdaUpdateWrapper<ProjectCurriculum> projectQw = Wrappers.<ProjectCurriculum>lambdaUpdate()
                .set(
                        !(watchTimes == null || watchTimes < 0),
                        ProjectCurriculum::getWatchAmount,
                        watchTimes)
                .in(
                        ProjectCurriculum::getId,
                        curriculumId);
        projectCurriculumService.update(projectQw);
    }

    /**
     * 课程列表全部数据
     * @return
     */
    @Override
    public List<Curriculum> listall(TeacherIdRequest idRequest) {
        LambdaQueryWrapper<Curriculum> qw = Wrappers.lambdaQuery();
        qw.select(Curriculum::getId,Curriculum::getCurriculumName)
        .eq(Curriculum::getTeacherId,idRequest.getJg0101id())
        .eq(Curriculum::getDeleteFlag,CommonConstant.DEL_FLAG);
        //List<Map<String, Object>> maps = listMaps(qw);
        List<Curriculum> list = this.list(qw);
        return list;
    }

    @Override
    public List<Curriculum> searchByTeacher(IdRequest id) {
        LambdaQueryWrapper<Curriculum> deleteQuery = Wrappers.<Curriculum>lambdaQuery()
                .eq(
                        Curriculum::getDeleteFlag,
                        CommonConstant.DEL_FLAG)
                .eq(
                        Curriculum::getTeacherId,id.getId())
                .select(
                        Curriculum::getId,
                        Curriculum::getCurriculumName);


        return list(deleteQuery);
    }

    /**
     * 新增课程，包括新增课程信息，章节信息，和视频信息
     * @param curriculumChapterAndVideoInfo
     */
    @Override
    @Transactional
    public void saveCurriculumInfo(CurriculumInsertRequest curriculumChapterAndVideoInfo) {
        Curriculum curriculumInfo = Curriculum.builder()
                .curriculumImage(curriculumChapterAndVideoInfo.getCurriculumImage())
                .classTypeId(curriculumChapterAndVideoInfo.getClassTypeId())
                .createTime(LocalDateTime.now())
                .curriculumName(curriculumChapterAndVideoInfo.getCurriculumName())
                .curriculumType(curriculumChapterAndVideoInfo.getCurriculumType())
                .deleteFlag(CommonConstant.DEL_FLAG)
                .topicName(curriculumChapterAndVideoInfo.getTopicName())
                .topicId(curriculumChapterAndVideoInfo.getTopicId())
                .auditFlag(CommonConstant.STATUS_NOAUDIT)
                .disableFlag(CommonConstant.DISABLE_FALSE)
                .teacherName(curriculumChapterAndVideoInfo.getTeacherName())
                .teacherId(curriculumChapterAndVideoInfo.getTeacherId())
                .watchAmount(CommonConstant.WATCH_AMOUNT_INITIAL_VALUE)
                .build();

        //1.保存课程基本信息
         save(curriculumInfo);
        //获取保存的课程的id
        Integer curriculumId = curriculumInfo.getId();
        //保存章节和对应视频信息
        saveChapterAndVideo(curriculumChapterAndVideoInfo.getChapterAndVideoInfo(), curriculumId,true,null);
    }

    private void saveChapterAndVideo(List<ChapterAndVideoInfo> chapterAndVideoInfo, Integer curriculumId,boolean firstLayer,Integer parentChapterId) {
        if (CollectionUtils.isEmpty(chapterAndVideoInfo)){
            if (log.isWarnEnabled())
                log.warn("保存的当前视频没有对应的章节及视频信息 课程id为 {}",curriculumId);
            return;
        }
        //2.保存章节信息
        for (ChapterAndVideoInfo perChapterAndVideoInfo : chapterAndVideoInfo) {
            Chapter chapter = ChapterAndVideoInfo.convertToPoWhileInsert(perChapterAndVideoInfo);
            VideoVo videoInfo = perChapterAndVideoInfo.getVideoInfo();
            //保存章节
            if (chapter!=null){
                chapter.setCurriculumId(curriculumId);
                if (!firstLayer && parentChapterId!=null)
                    chapter.setParentId(parentChapterId);
                chapterService.saveOrUpdate(chapter);
                Video video = VideoVo.convertToPoWhileInsert(videoInfo);
                //保存视频
                if (video!=null){
                    video.setChapterId(chapter.getId());
                    video.setCurriculumId(curriculumId);
                    videoService.saveVideoRelated(video);
                }
                List<ChapterAndVideoInfo> childChapter = perChapterAndVideoInfo.getChildChapter();
                if (childChapter!=null){
                    //如果还存在子章节递归保存
                    saveChapterAndVideo(childChapter,curriculumId,false,chapter.getId());
                }
            }
        }
    }

    @Override
    public void updateCurriculumAuditFlag(Integer curriculumId,Integer statusCode) {
        if (curriculumId ==null || statusCode == null){
            log.error("更新课程状态失败，所需id为空");
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Curriculum> updateCurriculumAuditFlag = Wrappers.<Curriculum>lambdaUpdate()
                .set(
                        Curriculum::getAuditFlag,
                        statusCode)
                .in(
                        Curriculum::getId,
                        curriculumId);

        update(updateCurriculumAuditFlag);
    }
}
