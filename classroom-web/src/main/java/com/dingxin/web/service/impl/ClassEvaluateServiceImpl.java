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
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.ClassEvaluateMapper;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.request.ClassEvaluateListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.ThumbsUpRequest;
import com.dingxin.web.service.IClassEvaluateService;
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
public class ClassEvaluateServiceImpl extends ServiceImpl<ClassEvaluateMapper, ClassEvaluate> implements IClassEvaluateService {
    @Autowired
    private ClassEvaluateMapper classEvaluateMapper;

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
            qw.like(ClassEvaluate::getStudentName, query.getQueryStr())
                    .or()
                    .like(ClassEvaluate::getStudentCode, query.getQueryStr())
                    .or()
                    .like(ClassEvaluate::getClassName, query.getQueryStr());
        }
//        伪代码
        int type = 3;
//        管理员
        if (1 == type) {
            qw.eq(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT);
        }
//        老师查看老师的评价且已经审核通过的
        else if (2 == type) {
            qw.eq(ClassEvaluate::getTeacherId, 1).eq(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT).eq(ClassEvaluate::getClassId, 1);
        }
//        学生查看课程相关的 评价不管有没有
        else if (3 == type) {
            qw.eq(ClassEvaluate::getClassId, 1).and((a) -> {
                        LambdaQueryWrapper<ClassEvaluate> q = Wrappers.lambdaQuery();
                        return q.eq(ClassEvaluate::getStatus, CommonConstant.STATUS_AUDIT).or((b) -> {
                            LambdaQueryWrapper<ClassEvaluate> qe = Wrappers.lambdaQuery();
                            return qe.eq(ClassEvaluate::getStudentId, 1).eq(ClassEvaluate::getStatus, CommonConstant.STATUS_NOAUDIT);
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
}
