package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.CrrStudentStudyCase;
import com.dingxin.dao.mapper.CrrStudentStudyCaseMapper;
import com.dingxin.pojo.po.Student;
import com.dingxin.pojo.request.StudentStudyCaseListRequest;
import com.dingxin.web.service.ICrrStudentStudyCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  学生学习情况服务接口实现类
 */
@Service
public class CrrStudentStudyCaseServiceImpl extends ServiceImpl<CrrStudentStudyCaseMapper, CrrStudentStudyCase> implements ICrrStudentStudyCaseService {

    @Autowired
    private CrrStudentStudyCaseMapper crrStudentStudyCaseMapper;
    @Autowired
    private ICrrStudentStudyCaseService crrStudentStudyCaseService;


    @Override
    public List<CrrStudentStudyCase> like(CrrStudentStudyCase data) {
        LambdaQueryWrapper<CrrStudentStudyCase> query = Wrappers.<CrrStudentStudyCase>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    CrrStudentStudyCase::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getStudentId()),
                    CrrStudentStudyCase::getStudentId,
                    data.getStudentId())
                .like(
                    Objects.nonNull(data.getStudentName()),
                    CrrStudentStudyCase::getStudentName,
                    data.getStudentName())
                .like(
                    Objects.nonNull(data.getStudentNum()),
                    CrrStudentStudyCase::getStudentNum,
                    data.getStudentNum())
                .like(
                    Objects.nonNull(data.getCollegeName()),
                    CrrStudentStudyCase::getCollegeName,
                    data.getCollegeName())
                .like(
                    Objects.nonNull(data.getMajor()),
                    CrrStudentStudyCase::getMajor,
                    data.getMajor())
                .like(
                    Objects.nonNull(data.getClass()),
                    CrrStudentStudyCase::getClass,
                    data.getClass())
                .like(
                    Objects.nonNull(data.getTeacherId()),
                    CrrStudentStudyCase::getTeacherId,
                    data.getTeacherId())
                .like(
                    Objects.nonNull(data.getTeacherName()),
                    CrrStudentStudyCase::getTeacherName,
                    data.getTeacherName())
                .like(
                    Objects.nonNull(data.getCourseId()),
                    CrrStudentStudyCase::getCourseId,
                    data.getCourseId())
                .like(
                    Objects.nonNull(data.getCourseName()),
                    CrrStudentStudyCase::getCourseName,
                    data.getCourseName())
                .like(
                    Objects.nonNull(data.getStudyTime()),
                    CrrStudentStudyCase::getStudyTime,
                    data.getStudyTime())
                .like(
                    Objects.nonNull(data.getStudyTimeStr()),
                    CrrStudentStudyCase::getStudyTimeStr,
                    data.getStudyTimeStr())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    CrrStudentStudyCase::getDelFlag,
                    data.getDelFlag())
;
        return crrStudentStudyCaseMapper.selectList(query);


    }

    @Override
    public IPage queryCoursePageList(StudentStudyCaseListRequest query) {
        Page<CrrStudentStudyCase> page = new Page(query.getCurrentPage(),query.getPageSize());
        LambdaQueryWrapper<CrrStudentStudyCase> qw = new LambdaQueryWrapper<>();
        qw.eq(CrrStudentStudyCase::getDelFlag, CommonConstant.DEL_FLAG);
        qw.eq(CrrStudentStudyCase::getStudentId,query.getStudentId());
        IPage<CrrStudentStudyCase> caseIPage = crrStudentStudyCaseService.page(page, qw);
        return caseIPage;
    }
}
