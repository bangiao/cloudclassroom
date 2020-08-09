package com.dingxin.web.service.strategy.curriculum;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.CurriculumMapper;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumFuzzyQuery4List;
import com.dingxin.web.service.impl.CurriculumServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
    public IPage<Curriculum> getPage(CurriculumFuzzyQuery4List query) {
        //todo
//        String userId = ShiroUtils.getUserId();
//        String teacherName = ShiroUtils.getUserName();
        Page<Curriculum> page = new Page<Curriculum>(query.getCurrentPage(), query.getPageSize());
        String queryStr = query.getQueryStr();
        //todo 获取登录者姓名
        //获取当前登录的教师，根据教师的姓名查询教师对应的所有课程
        String teacherName = "LBW";
        if (StringUtils.isBlank(teacherName)){

            throw new BusinessException(ExceptionEnum.PRIVILEGE_GET_USER_FAIL);
        }
        LambdaQueryWrapper<Curriculum> curriculumQuery = Wrappers.<Curriculum>lambdaQuery()
                .like(
                        StringUtils.isNotBlank(queryStr),
                        Curriculum::getCurriculumName,
                        queryStr)
                .or().like(
                        StringUtils.isNotBlank(queryStr),
                        Curriculum::getCurriculumType,
                        queryStr)
                .or().eq(

                        Curriculum::getAuditFlag,
                        queryStr)
                .or().like(
                        Curriculum::getTopicName,
                        queryStr)
                .or().eq(Curriculum::getClassTypeId,query.getClassType())
                .and(
                        StringUtils.isNotBlank(teacherName),
                        q->q
                                .eq(
                                        //获取当前登录讲师的课程
                                        Curriculum::getTeacherName,
                                        teacherName))
                                .eq(    //选取未删除的课程
                                        Curriculum::getDeleteFlag,
                                        CommonConstant.DEL_FLAG)
                .select(
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