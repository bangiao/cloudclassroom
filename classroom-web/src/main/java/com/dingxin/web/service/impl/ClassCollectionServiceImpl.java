package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.ClassCollectionMapper;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IClassCollectionService;
import com.dingxin.web.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 课程收藏表 服务接口实现类
 */
@Service
public class ClassCollectionServiceImpl extends ServiceImpl<ClassCollectionMapper, ClassCollection> implements IClassCollectionService {

    @Autowired
    private ClassCollectionMapper classCollectionMapper;


    @Override
    public List<ClassCollection> like(ClassCollection data) {
        LambdaQueryWrapper<ClassCollection> query = Wrappers.<ClassCollection>lambdaQuery()
                .like(
                        Objects.nonNull(data.getId()),
                        ClassCollection::getId,
                        data.getId())
                .like(
                        Objects.nonNull(data.getPersonId()),
                        ClassCollection::getPersonId,
                        data.getPersonId())
                .like(
                        Objects.nonNull(data.getClassId()),
                        ClassCollection::getClassId,
                        data.getClassId())
                .like(
                        Objects.nonNull(data.getClassName()),
                        ClassCollection::getClassName,
                        data.getClassName())
                .like(
                        Objects.nonNull(data.getClassType()),
                        ClassCollection::getClassType,
                        data.getClassType())
                .like(
                        Objects.nonNull(data.getClassTypeStr()),
                        ClassCollection::getClassTypeStr,
                        data.getClassTypeStr())
                .like(
                        Objects.nonNull(data.getTeacherId()),
                        ClassCollection::getTeacherId,
                        data.getTeacherId())
                .like(
                        Objects.nonNull(data.getTeacherName()),
                        ClassCollection::getTeacherName,
                        data.getTeacherName())
                .like(
                        Objects.nonNull(data.getCreateTime()),
                        ClassCollection::getCreateTime,
                        data.getCreateTime())
                .like(
                        Objects.nonNull(data.getModifyTime()),
                        ClassCollection::getModifyTime,
                        data.getModifyTime())
                .like(
                        Objects.nonNull(data.getDelFlag()),
                        ClassCollection::getDelFlag,
                        data.getDelFlag());
        return classCollectionMapper.selectList(query);


    }

    /**
     * 查询收藏课程列表
     *
     * @param query
     * @return
     */
    @Override
    public IPage queryList(CommQueryListRequest query) {
        String userId = ShiroUtils.getUserId();
        if (StringUtils.isEmpty(userId)){
            throw new BusinessException(ExceptionEnum.PRIVILEGE_CAS_FAIL);
        }
        LambdaQueryWrapper<ClassCollection> qw = Wrappers.lambdaQuery();
        qw.eq(ClassCollection::getPersonId,userId);
        qw.eq(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG);
        if ( StringUtils.isNotEmpty(query.getQueryStr())) {
            qw.like(ClassCollection::getClassName, query.getQueryStr())
                    .or().like(ClassCollection::getTeacherName, query.getQueryStr())
                    .or().like(ClassCollection::getClassTypeStr, query.getQueryStr())
                    .orderByDesc(ClassCollection::getCreateTime);
        }
        Page<ClassCollection> page = new Page(query.getCurrentPage(), query.getPageSize());
        return page(page, qw);
    }

    /**
     * 新增课程收藏
     *
     * @param classCollection
     * @return
     */
    @Override
    public boolean insert(ClassCollection classCollection) {
        String userId = ShiroUtils.getUserId();
        if (StringUtils.isEmpty(userId))
            throw new BusinessException(ExceptionEnum.PRIVILEGE_CAS_FAIL);
        classCollection.setPersonId(Integer.parseInt(userId));
        classCollection.setModifyTime(LocalDateTime.now());
        return saveOrUpdate(classCollection);

    }

    /**
     * 删除课程收藏
     *
     * @param id 课程收藏id
     * @return
     */
    @Override
    public boolean deleteById(IdRequest id) {
        LambdaUpdateWrapper<ClassCollection> qw = Wrappers.lambdaUpdate();
        qw.set(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getId()), ClassCollection::getId, id.getId());
        return update(qw);
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    @Override
    public boolean deleteByBatch(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        LambdaUpdateWrapper<ClassCollection> qw = Wrappers.lambdaUpdate();
        qw.set(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(ClassCollection::getId, list);
        return update(qw);
    }

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    @Override
    public ClassCollection getByIdSelf(IdRequest id) {
        LambdaQueryWrapper<ClassCollection> qw = Wrappers.lambdaQuery();
        qw.eq(ClassCollection::getId, id.getId()).eq(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG);
        return getOne(qw);
    }

}
