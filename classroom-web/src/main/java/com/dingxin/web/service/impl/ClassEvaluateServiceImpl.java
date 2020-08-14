package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.enums.RoleEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.ClassEvaluateMapper;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.ClassEvaluateListRequest;
import com.dingxin.pojo.request.ClassEvaluateRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.ThumbsUpRequest;
import com.dingxin.pojo.request.VideoAuditRequest;
import com.dingxin.web.service.IClassEvaluateService;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 课程评价表 服务接口实现类
 */
@Service
@Transactional
@Slf4j
public class ClassEvaluateServiceImpl extends ServiceImpl<ClassEvaluateMapper, ClassEvaluate> implements IClassEvaluateService {
    @Autowired
    private ClassEvaluateMapper classEvaluateMapper;

    @Autowired
    private ICurriculumService curriculumService;

    @Override
    public List<ClassEvaluate> like(ClassEvaluate data) {
        LambdaQueryWrapper<ClassEvaluate> query = Wrappers.<ClassEvaluate>lambdaQuery()
                .like(
                        Objects.nonNull(data.getId()),
                        ClassEvaluate::getId,
                        data.getId())
                .like(
                        Objects.nonNull(data.getClassId()),
                        ClassEvaluate::getClassId,
                        data.getClassId())
                .like(
                        Objects.nonNull(data.getClassName()),
                        ClassEvaluate::getClassName,
                        data.getClassName())
                .like(
                        Objects.nonNull(data.getTeacherId()),
                        ClassEvaluate::getTeacherId,
                        data.getTeacherId())
                .like(
                        Objects.nonNull(data.getTeacherName()),
                        ClassEvaluate::getTeacherName,
                        data.getTeacherName())
                .like(
                        Objects.nonNull(data.getStudyLength()),
                        ClassEvaluate::getStudyLength,
                        data.getStudyLength())
                .like(
                        Objects.nonNull(data.getStudentId()),
                        ClassEvaluate::getStudentId,
                        data.getStudentId())
                .like(
                        Objects.nonNull(data.getStudentName()),
                        ClassEvaluate::getStudentName,
                        data.getStudentName())
                .like(
                        Objects.nonNull(data.getStudentCode()),
                        ClassEvaluate::getStudentCode,
                        data.getStudentCode())
                .like(
                        Objects.nonNull(data.getEvaluateTime()),
                        ClassEvaluate::getEvaluateTime,
                        data.getEvaluateTime())
                .like(
                        Objects.nonNull(data.getEvaluateContent()),
                        ClassEvaluate::getEvaluateContent,
                        data.getEvaluateContent())
                .like(
                        Objects.nonNull(data.getEvaluateScore()),
                        ClassEvaluate::getEvaluateScore,
                        data.getEvaluateScore())
                .like(
                        Objects.nonNull(data.getEvaluateCount()),
                        ClassEvaluate::getEvaluateCount,
                        data.getEvaluateCount())
                .like(
                        Objects.nonNull(data.getCreateTime()),
                        ClassEvaluate::getCreateTime,
                        data.getCreateTime())
                .like(
                        Objects.nonNull(data.getModifyTime()),
                        ClassEvaluate::getModifyTime,
                        data.getModifyTime())
                .like(
                        Objects.nonNull(data.getStatus()),
                        ClassEvaluate::getStatus,
                        data.getStatus())
                .like(
                        Objects.nonNull(data.getDelFlag()),
                        ClassEvaluate::getDelFlag,
                        data.getDelFlag());
        return classEvaluateMapper.selectList(query);


    }

    /**
     * 修改点赞数
     *
     * @return
     */
    @Override
    public boolean updateUp(ThumbsUpRequest thumbsUpVo) {
        ClassEvaluate classEvaluate = getById(thumbsUpVo.getId());
        if (thumbsUpVo.getUpOrDown()) classEvaluate.setEvaluateCount(classEvaluate.getEvaluateCount() + 1);
        else
            classEvaluate.setEvaluateCount(classEvaluate.getEvaluateCount() > 0 ? classEvaluate.getEvaluateCount() - 1 : 0);
        return updateById(classEvaluate);
    }

    /**
     * 查询数据列表根据不同的条件
     *
     * @param query
     * @return
     */
    @Override
    public IPage queryPage(ClassEvaluateListRequest query) {
        //查询列表数据
        Page<ClassEvaluate> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ClassEvaluate> qw = Wrappers.lambdaQuery();
        qw.select(ClassEvaluate::getId, ClassEvaluate::getClassId, ClassEvaluate::getTeacherName, ClassEvaluate::getStudyLength, ClassEvaluate::getEvaluateTime,
                ClassEvaluate::getEvaluateContent, ClassEvaluate::getStudentName, ClassEvaluate::getStudentCode, ClassEvaluate::getClassName);
        if (StringUtils.isNotBlank(query.getQueryStr())) {
            qw.and(Wrappers->Wrappers.like(ClassEvaluate::getStudentName, query.getQueryStr())
                    .or()
                    .like(ClassEvaluate::getStudentCode, query.getQueryStr())
                    .or()
                    .like(ClassEvaluate::getClassName, query.getQueryStr()));
        }
//        伪代码
        RoleEnum userType = ShiroUtils.getUserType();
        if (Objects.isNull(userType)) {
            log.error(ExceptionEnum.PRIVILEGE_GET_USER_FAIL.getMsg());
            throw new BusinessException(ExceptionEnum.PRIVILEGE_GET_USER_FAIL);
        }

        //        管理员
        if (userType.getCode()==CommonConstant.NORMAL_USER){
            qw.eq(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT);
        }
//        老师查看老师的评价且已经审核通过的
        else if (userType.getCode()==CommonConstant.TEACHER) {
            qw.eq(ClassEvaluate::getTeacherId, ShiroUtils.getUserId()).eq(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT).eq(ClassEvaluate::getClassId, query.getClassId());
        }
//        学生查看课程相关的 评价不管有没有
        else if (userType.getCode()==CommonConstant.ADMINISTRATOR) {
            qw.eq(ClassEvaluate::getClassId, query.getClassId()).and((a) -> {
                        LambdaQueryWrapper<ClassEvaluate> q = Wrappers.lambdaQuery();
                        return q.eq(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT).or((b) -> {
                            LambdaQueryWrapper<ClassEvaluate> qe = Wrappers.lambdaQuery();
                            return qe.eq(ClassEvaluate::getStudentId, ShiroUtils.getUserId()).eq(ClassEvaluate::getStatus, CommonConstant.STATUS_NOAUDIT);
                        });
                    }
            );
        } else {
            return new Page();
        }
        IPage pageList = page(page, qw.eq(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG).orderByDesc(ClassEvaluate::getCreateTime));
        return pageList;
    }

    /**
     * 删除课程评价
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(IdRequest id) {
        LambdaUpdateWrapper<ClassEvaluate> qw = Wrappers.lambdaUpdate();
        qw.set(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getId()), ClassEvaluate::getId, id.getId());
        return update(qw);
    }

    /**
     * 批量删除课程评价
     *
     * @param list
     * @return
     */
    @Override
    public boolean deleteBatch(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        LambdaUpdateWrapper<ClassEvaluate> qw = Wrappers.lambdaUpdate();
        qw.set(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(ClassEvaluate::getId, list);
        return update(qw);
    }

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    @Override
    public ClassEvaluate getByIdSelf(IdRequest id) {
        LambdaQueryWrapper<ClassEvaluate> qw = Wrappers.lambdaQuery();
        qw.eq(ClassEvaluate::getId, id.getId()).eq(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG);
        return getOne(qw);
    }

    /**
     * 获取评价审核列表
     * @param query
     * @return
     */
    @Override
    public IPage queryAuditPageList(ClassEvaluateListRequest query) {
        Page<ClassEvaluate> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ClassEvaluate> qw = Wrappers.lambdaQuery();
        qw.eq(ClassEvaluate::getDelFlag, CommonConstant.DEL_FLAG);
        qw.eq(ClassEvaluate::getClassId,query.getClassId());
        IPage<ClassEvaluate> iPage = this.page(page);
        return iPage;
    }

    /**
     *批量审核评价通过
     * @param classEvaluateRequest
     */

    @Override
    public void auditBatch(ClassEvaluateRequest classEvaluateRequest) {
        LambdaUpdateWrapper<ClassEvaluate> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(ClassEvaluate::getStatus, classEvaluateRequest.getAuditStatus());
        wrapper.set(ClassEvaluate::getAuditComments, classEvaluateRequest.getAuditComments());
        wrapper.eq(ClassEvaluate::getClassId,classEvaluateRequest.getClassId());
        wrapper.in(ClassEvaluate::getId, classEvaluateRequest.getIdList());
        this.update(wrapper);

        updateCurriculumRelatedEvaluateStatus(classEvaluateRequest.getClassId());
    }

    /**
     * 单个审核
     * @param classEvaluate
     */
    @Override
    public void audit(VideoAuditRequest classEvaluate) {
        LambdaUpdateWrapper<ClassEvaluate> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(ClassEvaluate::getStatus, classEvaluate.getAuditStatus());
        wrapper.eq(ClassEvaluate::getId, classEvaluate.getId());
        wrapper.eq(ClassEvaluate::getClassId,classEvaluate.getCurriculumId());
        this.update(wrapper);
        updateCurriculumRelatedEvaluateStatus(classEvaluate.getCurriculumId());
    }

    //保存的时候同时更新对应课程的课程评价状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveEvaluation(ClassEvaluate classEvaluate) {
        save(classEvaluate);
        updateCurriculumRelatedEvaluateStatus(classEvaluate.getClassId());
    }

    @Override
    public void updateCurriculumRelatedEvaluateStatus(Integer curriculumId) {
        if (curriculumId == null){
            log.error("updateCurriculumRelatedEvaluateStatus 失败,当前评价对应课程ID为空，小朋友");
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaQueryWrapper<ClassEvaluate> validEvaluateQuery = Wrappers.<ClassEvaluate>lambdaQuery()
                .eq(
                        ClassEvaluate::getClassId,
                        curriculumId)
                .eq(
                        ClassEvaluate::getDelFlag,
                        CommonConstant.DEL_FLAG)
                .eq(
                        ClassEvaluate::getStatus,
                        CommonConstant.STATUS_UNAPPROVED)
                .select(
                        ClassEvaluate::getId);

        List<ClassEvaluate> videoList = list(validEvaluateQuery);
        //如果查询当前课程下存在未删除且未审核的评价，则设置课程的审核状态为未审核
        if (com.dingxin.common.utils.CollectionUtils.isNotEmpty(videoList)) {
            LambdaUpdateWrapper<Curriculum> updateCurriculumAuditFlag = Wrappers.<Curriculum>lambdaUpdate()
                    .set(
                            Curriculum::getEvaluateStatus,
                            CommonConstant.STATUS_NOAUDIT)
                    .in(
                            Curriculum::getId,
                            curriculumId);

            curriculumService.update(updateCurriculumAuditFlag);
        }
    }
}
