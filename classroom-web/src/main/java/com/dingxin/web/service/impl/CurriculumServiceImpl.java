package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.CurriculumMapper;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类(公共实现类，该类的实现方法不会根据角色不同而差异化功能)
 */
@Service
@Transactional
public abstract class CurriculumServiceImpl extends ServiceImpl<CurriculumMapper, Curriculum> implements ICurriculumService {

    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private IVideoService videoService;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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
    }

    @Override
    public void deleteCurriculum(List<Integer> curriculumIds) {
        if(curriculumIds == null || curriculumIds.isEmpty()){

            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        //将课程表本身flag设置为删除
        LambdaUpdateWrapper<Curriculum> disableQuery = Wrappers.<Curriculum>lambdaUpdate().
                set(
                        Curriculum::getDisableFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .in(
                        Curriculum::getId,
                        curriculumIds);

        update(disableQuery);
    }

    @Override//这里用两个方法删除因为需要事务,两个方法需要同时成功
    public void deleteCurriculumAndRelated(List<Integer> curriculumIds) {

        deleteCurriculum(curriculumIds);
        videoService.deleteCurriculumRelatedVideo(curriculumIds);
    }

    @Override
    public Curriculum loadCurriculumDetails(IdRequest id) {
        LambdaQueryWrapper<Curriculum> deleteQuery = Wrappers.<Curriculum>lambdaQuery()
                .ne(
                        Curriculum::getDeleteFlag,
                        CommonConstant.DEL_FLAG_TRUE)
                .eq(
                        Curriculum::getId,id)
                .select(
                        Curriculum::getId,
                        Curriculum::getTeacherName,
                        Curriculum::getCurriculumName,
                        Curriculum::getCurriculumType,
                        Curriculum::getCurriculumDesc,
                        Curriculum::getVideoDuration,
                        Curriculum::getWatchAmount);

        return getOne(deleteQuery);
    }
}
