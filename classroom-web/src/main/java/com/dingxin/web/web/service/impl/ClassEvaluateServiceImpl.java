package com.dingxin.web.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.dao.mapper.ClassEvaluateMapper;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.web.service.IClassEvaluateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * 课程评价表 服务接口实现类
 */
@Service
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
                    data.getDelFlag())
;
        return classEvaluateMapper.selectList(query);


    }

    @Override
    public boolean updateUp(Boolean upOrDown, Integer id) {
        return false;
    }
}
