package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.dao.mapper.CurriculumMapper;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.*;
import com.dingxin.pojo.request.CurriculumInsertRequest;
import com.dingxin.pojo.request.CurriculumUpdateRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.TeacherIdRequest;
import com.dingxin.pojo.vo.ChapterAndVideoInfo;
import com.dingxin.pojo.vo.CurriculumDetailsVo;
import com.dingxin.pojo.vo.VideoVo;
import com.dingxin.web.service.IChapterService;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IProjectCurriculumService;
import com.dingxin.web.service.ITeachersService;
import com.dingxin.web.service.IVideoService;
import com.dingxin.pojo.vo.*;
import com.dingxin.web.service.*;
import com.dingxin.web.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.logging.Logger;

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
    @Autowired
    private IClassCollectionService classCollectionService;
    @Autowired
    private ITeachersService teachersService;

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
    @Transactional
    public void enableCurriculum(List<Integer> curriculumIds) {
        if(CollectionUtils.isEmpty(curriculumIds)){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        curriculumIds.forEach(perCurriculumId->{
            //获取当前课程的讲师信息
            Teachers teachers = loadCurrentCurriculumTeacherInfo(perCurriculumId);
            //1.当前课程的讲师为空 可以启用
            if (Objects.isNull(teachers)){
                enableCurriculum(perCurriculumId);
            }//当前课程有教师信息且教师没有被禁用则也可以启用
            else if (teachers.getEnable()==CommonConstant.DISABLE_FALSE){
                enableCurriculum(perCurriculumId);
            }//当教师不为空且当前讲师为禁用状态时，当前课程不能被启用
            else {
               throw new BusinessException(ExceptionEnum.CANNOT_ENABLE_CAUSE_TEACHER_DISABLE);
            }
        });
    }

    private void enableCurriculum(Integer curriculumId) {
        if (curriculumId==null){
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaUpdateWrapper<Curriculum> disableQuery = Wrappers.<Curriculum>lambdaUpdate().
                set(
                        Curriculum::getDisableFlag,
                        CommonConstant.DISABLE_FALSE)
                .eq(
                        Curriculum::getId,
                        curriculumId);

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

    @Override
    @Transactional
    public void deleteCurriculumAndRelated(List<Integer> curriculumIds) {

        //课程信息删除
        deleteCurriculum(curriculumIds);

        //课程相关视频删除
        videoService.deleteCurriculumRelatedVideo(curriculumIds);

        //对应章节删除
        LambdaUpdateWrapper<Chapter> deleteChapterWrapper = Wrappers.<Chapter>lambdaUpdate()
                .set(Chapter::getDeleteFlag, CommonConstant.DEL_FLAG_TRUE)
                .in(Chapter::getCurriculumId, curriculumIds);

        chapterService.update(deleteChapterWrapper);
    }

    @Override
    public CurriculumDetailsVo loadCurriculumDetails(IdRequest id) {
        //查出当前课程信息
        LambdaQueryWrapper<Curriculum> loadCurriculumQuery = Wrappers.<Curriculum>lambdaQuery()
                .ne(
                        Curriculum::getDeleteFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .eq(
                        Curriculum::getId,id.getId())
                .select(
                        Curriculum::getId,
                        Curriculum::getTeacherName,
                        Curriculum::getTeacherId,
                        Curriculum::getCurriculumName,
                        Curriculum::getCurriculumType,
                        Curriculum::getClassTypeId,
                        Curriculum::getCurriculumDesc,
                        Curriculum::getTopicName,
                        Curriculum::getTopicId,
                        Curriculum::getCurriculumImage,
                        Curriculum::getDisableFlag);
        //查询出当前课程对应的章节及章节对应的视频信息
        List<ChapterAndVideoInfo> chapterAndVideoInfos = chapterService.loadChapterAndVideoInfo(id.getId());
        //课程信息
        Curriculum curriculum = getOne(loadCurriculumQuery);
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
    public List<Curriculum> listAll(TeacherIdRequest idRequest) {
        LambdaQueryWrapper<Curriculum> qw = Wrappers.lambdaQuery();
        qw.select(Curriculum::getId,Curriculum::getCurriculumName)
        .eq(Objects.nonNull(idRequest.getJg0101id()),Curriculum::getTeacherId,idRequest.getJg0101id())
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
                .curriculumDesc(curriculumChapterAndVideoInfo.getCurriculumDesc())
                .teacherName(curriculumChapterAndVideoInfo.getTeacherName())
                .teacherId(curriculumChapterAndVideoInfo.getTeacherId())
                .watchAmount(CommonConstant.WATCH_AMOUNT_INITIAL_VALUE)
                .build();

        //1.保存课程基本信息
         save(curriculumInfo);
        //获取保存的课程的id
        Integer curriculumId = curriculumInfo.getId();
        //保存章节和对应视频信息
        saveCurrentCurriculumChapterAndVideo(curriculumChapterAndVideoInfo.getChapterAndVideoInfo(), curriculumId,true,null);
        //更新对应课程的观看时长
        videoService.updateCurriculumVideoDuration(curriculumId);
    }

    private void saveCurrentCurriculumChapterAndVideo(List<ChapterAndVideoInfo> chapterAndVideoInfo, Integer curriculumId, boolean firstLayer, Integer parentChapterId) {
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
                    //一个章节下只能选择挂一个视频或直播视频，不能同时拥有多个视频
                    if(video.getVideoField()!=null && video.getLiveVideoField()!=null){
                      throw new BusinessException(ExceptionEnum.CHAPTER_CAN_ONLY_WITH_ONE_VIDEO_INFO);
                    }
                    video.setChapterId(chapter.getId());
                    video.setCurriculumId(curriculumId);
                    videoService.saveOrUpdate(video);
                }
                List<ChapterAndVideoInfo> childChapter = perChapterAndVideoInfo.getChildChapter();
                if (childChapter!=null){
                    //如果还存在子章节递归保存
                    saveCurrentCurriculumChapterAndVideo(childChapter,curriculumId,false,chapter.getId());
                }
            }
        }
    }
    @Override
    public void updateCurriculumInfo(CurriculumUpdateRequest curriculumUpdateRequest) {
        Curriculum curriculumInfo = Curriculum.builder()
                .id(curriculumUpdateRequest.getCurriculumId())
                .curriculumImage(curriculumUpdateRequest.getCurriculumImage())
                .classTypeId(curriculumUpdateRequest.getClassTypeId())
                .curriculumName(curriculumUpdateRequest.getCurriculumName())
                .curriculumType(curriculumUpdateRequest.getCurriculumType())
                .topicName(curriculumUpdateRequest.getTopicName())
                .topicId(curriculumUpdateRequest.getTopicId())
                .teacherName(curriculumUpdateRequest.getTeacherName())
                .teacherId(curriculumUpdateRequest.getTeacherId())
                .build();
        //更新课程本身的信息
        updateById(curriculumInfo);
        //删除要被递归删除的章节
        chapterService.removeChapterRecursively(curriculumUpdateRequest.getDeleteParentChapterIds());
        //删除只删除子章节的章节
        chapterService.onlyRemoveChildChapterSelf(curriculumUpdateRequest.getDeleteChildChapterIds());
        //删除章节对应的视频信息
        List<Integer> allDeleteVideoChapterIds = chapterService.loadChildrenIdByParentIds(curriculumUpdateRequest.getDeleteParentChapterIds());
        allDeleteVideoChapterIds.addAll(curriculumUpdateRequest.getDeleteChildChapterIds());
        videoService.deleteVideoByChapterId(allDeleteVideoChapterIds,curriculumUpdateRequest.getCurriculumId());
        //保存新增的章节及视频信息
        List<ChapterAndVideoInfo> chapterAndVideoWillBeSaved = curriculumUpdateRequest.getChapterAndVideoWillBeSaved();
        saveCurrentCurriculumChapterAndVideo(chapterAndVideoWillBeSaved,curriculumUpdateRequest.getCurriculumId(),true,null);
        //更新对应课程的观看时长
        videoService.updateCurriculumVideoDuration(curriculumUpdateRequest.getCurriculumId());

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

    /**
     * pc获取最新课程列表
     * @param
     */
    @Override
    public BaseResult<Page<CurriculumPcVo>> leatestList(IdRequest idRequest){
        Page<Curriculum> page = new Page(idRequest.getCurrentPage(),idRequest.getPageSize());
        LambdaQueryWrapper<Curriculum> qw = null;
        if (idRequest.getId().equals(CommonConstant.LATESTCURRICULUMTYPE)){
            qw = Wrappers.<Curriculum>lambdaQuery()
                    .eq(Curriculum::getDeleteFlag, CommonConstant.DEL_FLAG)
                    .eq(Curriculum::getDisableFlag, CommonConstant.DISABLE_FALSE)
                    .orderByDesc(Curriculum::getCreateTime);
        }else if (idRequest.getId().equals(CommonConstant.DURATIONCURRICULUMTYPE)){
            qw = Wrappers.<Curriculum>lambdaQuery()
                    .eq(Curriculum::getDeleteFlag, CommonConstant.DEL_FLAG)
                    .eq(Curriculum::getDisableFlag, CommonConstant.DISABLE_FALSE)
                    .orderByDesc(Curriculum::getVideoDuration);
        }else {
            log.error("获取课程失败，参数出错");
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        IPage<Curriculum> curriculumIPage = this.page(page, qw);
        return BaseResult.success((getCollection(curriculumIPage)));
    }

    /**
     * pc根据院系获取课程列表
     * @param
     */
    @Override
    public BaseResult<Page<CurriculumVo>> ListbyDept(IdRequest idRequest){
        Page<Curriculum> page = new Page(idRequest.getCurrentPage(),idRequest.getPageSize());
        LambdaQueryWrapper<Curriculum> qw = Wrappers.<Curriculum>lambdaQuery()
                .eq(Curriculum::getDeleteFlag, CommonConstant.DEL_FLAG)
                .eq(Curriculum::getDisableFlag, CommonConstant.DISABLE_FALSE)
                .eq(null != idRequest.getId(),Curriculum::getDepartmentId,idRequest.getId())
                .orderByDesc(Curriculum::getCreateTime);
        IPage<Curriculum> curriculumIPage = this.page(page, qw);
        return BaseResult.success((getCollection(curriculumIPage)));
    }


    @Override
    public Teachers loadCurrentCurriculumTeacherInfo(Integer curriculumId) {
        if (curriculumId ==null){
            log.error("根据课程id获取当前课程下的教师信息失败，当前课程id为空");
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        // 根据课程id获取当前课程下的教师id
        LambdaQueryWrapper<Curriculum> getTeacherId = Wrappers.<Curriculum>lambdaQuery()
                .eq(Curriculum::getDeleteFlag, CommonConstant.DEL_FLAG)
                .eq(Curriculum::getId,curriculumId)
                .select(Curriculum::getTeacherId);
        Curriculum curriculumTeacherInfo = getOne(getTeacherId);
        String teacherId = curriculumTeacherInfo.getTeacherId();

        LambdaQueryWrapper<Teachers> loadTeacherQuery = Wrappers.<Teachers>lambdaQuery()
                .eq(Teachers::getZgh, teacherId);
        return teachersService.getOne(loadTeacherQuery);
    }


    @Override
    public IPage<CurriculumPcVo> getCollection( IPage<Curriculum> curriculumIPage){
        List<Curriculum> records = curriculumIPage.getRecords();
        if (CollectionUtils.isEmpty(records)){
            return null;
        }
        IPage<CurriculumPcVo> curriculumPcVoIPage = CurriculumPcVo.convertToVoWithPage(curriculumIPage);
        //查询收藏信息
        List<Integer> idList = curriculumIPage.getRecords().stream().map(Curriculum::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(idList)){
            Map<String, Object> map = classCollectionService.selectCountByCurriculumIds(idList);
            curriculumPcVoIPage.getRecords().stream().forEach(s->{
                if (map.containsKey(s.getId())){
                    s.setIsCollection(true);
                    s.setCollectionNum(map.get(s.getId()).toString());
                }else {
                    s.setIsCollection(false);
                    s.setCollectionNum(CommonConstant.DEFAULTCOLLECTIONNUM);
                }
            });
        }
        return curriculumPcVoIPage;
    }
}
