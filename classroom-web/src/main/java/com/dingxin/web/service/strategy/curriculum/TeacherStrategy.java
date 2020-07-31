package com.dingxin.web.service.strategy.curriculum;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.dao.mapper.CurriculumMapper;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumRequest;
import com.dingxin.web.service.impl.CurriculumServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * author: cuteG <br>
 * date: 2020/7/28 0:38 <br>
 * description: todo <br>
 */
@Service("TeacherStrategy")
@Primary
public class TeacherStrategy extends CurriculumServiceImpl {

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Override
    public IPage<Curriculum> getPage(BaseQuery<CurriculumRequest> query) {
        //todo
//        String userId = ShiroUtils.getUserId();
        Page<Curriculum> page = new Page<Curriculum>(query.getCurrentPage(), query.getPageSize());
        CurriculumRequest requestData = query.getData();
        if (Objects.isNull(requestData)){

            return curriculumMapper.selectPage(page, Wrappers.query());
        }
        LambdaQueryWrapper<Curriculum> curriculumQuery = Wrappers.<Curriculum>lambdaQuery()
                .like(
                        StringUtils.isNotBlank(requestData.getCurriculumName()),
                        Curriculum::getCurriculumName,
                        requestData.getCurriculumName())
                .like(
                        StringUtils.isNotBlank(requestData.getCurriculumType()),
                        Curriculum::getCurriculumType,
                        requestData.getCurriculumType())
                .eq(
                        requestData.getAuditFlag()!=null,
                        Curriculum::getAuditFlag,
                        requestData.getAuditFlag())
                .like(
                        StringUtils.isNotBlank(requestData.getTopicName()),
                        Curriculum::getTopicName,
                        requestData.getTopicName())
                .and(
                        StringUtils.isNotBlank(requestData.getTeacherName()),
                        q->q.eq(Curriculum::getTeacherName,requestData.getTeacherName()))
                .select(
                        Curriculum::getId,
                        Curriculum::getTeacherName,
                        Curriculum::getCurriculumName,
                        Curriculum::getCurriculumType,
                        Curriculum::getCurriculumDesc,
                        Curriculum::getVideoDuration,
                        Curriculum::getWatchAmount);

        return curriculumMapper.selectPage(page, curriculumQuery);
    }
}