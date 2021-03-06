package com.dingxin.web.service.strategy.curriculum;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.dao.mapper.CurriculumMapper;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumFuzzyQuery4List;
import com.dingxin.web.service.impl.CurriculumServiceImpl;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * author: cuteG <br>
 * date: 2020/7/28 0:13 <br>
 * description: todo <br>
 */
@Service("AdministratorStrategy")
public class AdministratorStrategy extends CurriculumServiceImpl {

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Override
    public IPage<Curriculum> getPage(CurriculumFuzzyQuery4List query) {

        Page<Curriculum> page = new Page<Curriculum>(query.getCurrentPage(), query.getPageSize());
        String queryStr = query.getQueryStr();
        LambdaQueryWrapper<Curriculum> curriculumQuery = Wrappers.<Curriculum>lambdaQuery()
                .and(StringUtils.isNotBlank(queryStr),q->q
                .like(
                        StringUtils.isNotBlank(queryStr),
                        Curriculum::getCurriculumName,
                        queryStr)
                .or().like(
                        StringUtils.isNotBlank(queryStr),
                        Curriculum::getCurriculumType,
                        queryStr)
                .or().like(
                        StringUtils.isNotBlank(queryStr),
                        Curriculum::getTeacherName,
                        queryStr)
                .or().like(
                        StringUtils.isNotBlank(queryStr),
                        Curriculum::getTopicName,
                        queryStr));
        curriculumQuery.and(q->q
                .eq(    //选取未删除的课程
                        Curriculum::getDeleteFlag,
                        CommonConstant.DEL_FLAG))
                .eq(
                        query.getAuditFlag()!=null,
                        Curriculum::getAuditFlag,
                        query.getAuditFlag())
                .eq(
                        query.getClassTypeId()!=null,
                        Curriculum::getClassTypeId,
                        query.getClassTypeId()).select(
                Curriculum::getId,
                Curriculum::getTeacherName,
                Curriculum::getCurriculumName,
                Curriculum::getCurriculumType,
                Curriculum::getCurriculumDesc,
                Curriculum::getVideoDuration,
                Curriculum::getWatchAmount,
                Curriculum::getAuditFlag,
                Curriculum::getDisableFlag);


        return curriculumMapper.selectPage(page, curriculumQuery);
    }
}