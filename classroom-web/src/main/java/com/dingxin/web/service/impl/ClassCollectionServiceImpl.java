package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.ClassCollectionInsertRequest;
import com.dingxin.pojo.request.ClassIdRequest;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ClassCollectionListVo;
import com.dingxin.web.service.IClassCollectionService;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.shiro.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 课程收藏表 服务接口实现类
 */
@Service
@Slf4j
public class ClassCollectionServiceImpl extends ServiceImpl<ClassCollectionMapper, ClassCollection> implements IClassCollectionService {

    @Autowired
    private ICurriculumService curriculumService;


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
            log.error(ExceptionEnum.PRIVILEGE_GET_USER_FAIL.getMsg());
            throw new BusinessException(ExceptionEnum.PRIVILEGE_GET_USER_FAIL);
        }
        LambdaQueryWrapper<ClassCollection> qw = Wrappers.lambdaQuery();
        qw.eq(ClassCollection::getPersonId,userId);
        qw.eq(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG);
        List<Integer> classIds = list(qw).stream().map(e -> e.getClassId()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(classIds)){
            throw new BusinessException(ExceptionEnum.NO_COLLECTION);
        }

//        查询课程信息
        LambdaQueryWrapper<Curriculum> qc = Wrappers.lambdaQuery();
        qc.in(Curriculum::getId,classIds).eq(Curriculum::getDeleteFlag,CommonConstant.DEL_FLAG)
                .eq(Curriculum::getDisableFlag,CommonConstant.DISABLE_FALSE);
        if ( StringUtils.isNotEmpty(query.getQueryStr())) {
            qc.and(Wrappers->Wrappers.like(Curriculum::getCurriculumName, query.getQueryStr())
                    .or().like(Curriculum::getTeacherName, query.getQueryStr())
                    .or().like(Curriculum::getCurriculumType, query.getQueryStr()));
        }
        Page<Curriculum> page = new Page(query.getCurrentPage(), query.getPageSize());
        curriculumService.page(page, qc);
        IPage<ClassCollectionListVo> classCollectionListVoIPage = ClassCollectionListVo.convertToVoWithPage(page);
        Map<String, Object> stringObjectMap = selectCountByCurriculumIds(classIds);

        classCollectionListVoIPage.getRecords().forEach(e->{
            e.setCollectionNum(stringObjectMap.get(String.valueOf(e.getClassId())));
        });


        return classCollectionListVoIPage;
    }

    /**
     * 新增课程收藏
     *
     * @param request
     * @return
     */
    @Override
    public boolean insert(ClassCollectionInsertRequest request) {
        String userId = ShiroUtils.getUserId();
        if (StringUtils.isEmpty(userId)) {
            log.error(ExceptionEnum.PRIVILEGE_GET_USER_FAIL.getMsg());
            throw new BusinessException(ExceptionEnum.PRIVILEGE_GET_USER_FAIL);
        }
        LambdaQueryWrapper<ClassCollection> qw = Wrappers.lambdaQuery();
        qw.eq(ClassCollection::getDelFlag,CommonConstant.DEL_FLAG)
                .eq(ClassCollection::getPersonId,userId)
                .eq(ClassCollection::getClassId,request.getClassId())
                .select(ClassCollection::getId);
        int count = count(qw);
        if (count>0){
            throw new BusinessException(ExceptionEnum.DUPLICATE_DATA);
        }
        ClassCollection build = ClassCollection.builder()
                .personId(userId)
                .modifyTime(LocalDateTime.now())
                .classId(request.getClassId())
                .build();
        return save(build);

    }

    /**
     * 删除课程收藏
     *
     * @param id 课程收藏id
     * @return
     */
    @Override
    public boolean deleteById(ClassIdRequest id) {
        String userId = ShiroUtils.getUserId();
        if (StringUtils.isEmpty(userId)){
            throw new BusinessException(ExceptionEnum.PRIVILEGE_GET_USER_FAIL);
        }
        LambdaUpdateWrapper<ClassCollection> qw = Wrappers.lambdaUpdate();
        qw.set(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getClassId()), ClassCollection::getPersonId, userId);
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
    public Curriculum getByIdSelf(ClassIdRequest id) {
        LambdaQueryWrapper<Curriculum> qw = Wrappers.lambdaQuery();
        qw.eq(Curriculum::getId, id.getClassId()).eq(Curriculum::getDeleteFlag, CommonConstant.DEL_FLAG)
                .eq(Curriculum::getDisableFlag,CommonConstant.DISABLE_FALSE);
        return curriculumService.getOne(qw);
    }


    /**
     * 根据课程id获取课程id收藏次数
     * @param cids
     * @return
     */

    @Override
    public Map<String, Object> selectCountByCurriculumIds(List<Integer> cids) {
        if (CollectionUtils.isEmpty(cids)){
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        QueryWrapper<ClassCollection> qw = Wrappers.query();
        qw.in("class_id",cids).eq("del_flag",CommonConstant.DEL_FLAG)
                .select("class_id","count(class_id) counts")
                .groupBy("class_id");
        List<Map<String, Object>> maps = listMaps(qw);
        return maps.stream().collect(Collectors.toMap(map -> map.get("class_id").toString(), map -> map.get("counts")));

    }

}
