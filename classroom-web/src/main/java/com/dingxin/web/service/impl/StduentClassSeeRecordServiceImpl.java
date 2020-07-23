package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.dao.mapper.StduentClassSeeRecordMapper;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 学生记录表 服务接口实现类
 */
@Service
@Transactional
public class StduentClassSeeRecordServiceImpl extends ServiceImpl<StduentClassSeeRecordMapper, StduentClassSeeRecord> implements IStduentClassSeeRecordService {

    @Autowired
    private StduentClassSeeRecordMapper stduentClassSeeRecordMapper;


    @Override
    public List<StduentClassSeeRecord> like(StduentClassSeeRecord data) {
        LambdaQueryWrapper<StduentClassSeeRecord> query = Wrappers.<StduentClassSeeRecord>lambdaQuery()
                .like(
                        Objects.nonNull(data.getId()),
                        StduentClassSeeRecord::getId,
                        data.getId())
                .like(
                        Objects.nonNull(data.getStudentId()),
                        StduentClassSeeRecord::getStudentId,
                        data.getStudentId())
                .like(
                        Objects.nonNull(data.getStudentName()),
                        StduentClassSeeRecord::getStudentName,
                        data.getStudentName())
                .like(
                        Objects.nonNull(data.getStudentCode()),
                        StduentClassSeeRecord::getStudentCode,
                        data.getStudentCode())
                .like(
                        Objects.nonNull(data.getStudentColleges()),
                        StduentClassSeeRecord::getStudentColleges,
                        data.getStudentColleges())
                .like(
                        Objects.nonNull(data.getStudentMajor()),
                        StduentClassSeeRecord::getStudentMajor,
                        data.getStudentMajor())
                .like(
                        Objects.nonNull(data.getStudentClass()),
                        StduentClassSeeRecord::getStudentClass,
                        data.getStudentClass())
                .like(
                        Objects.nonNull(data.getTeacherId()),
                        StduentClassSeeRecord::getTeacherId,
                        data.getTeacherId())
                .like(
                        Objects.nonNull(data.getTeacherName()),
                        StduentClassSeeRecord::getTeacherName,
                        data.getTeacherName())
                .like(
                        Objects.nonNull(data.getClassId()),
                        StduentClassSeeRecord::getClassId,
                        data.getClassId())
                .like(
                        Objects.nonNull(data.getClassName()),
                        StduentClassSeeRecord::getClassName,
                        data.getClassName())
                .like(
                        Objects.nonNull(data.getStudyLength()),
                        StduentClassSeeRecord::getStudyLength,
                        data.getStudyLength())
                .like(
                        Objects.nonNull(data.getCreateTime()),
                        StduentClassSeeRecord::getCreateTime,
                        data.getCreateTime())
                .like(
                        Objects.nonNull(data.getModifyTime()),
                        StduentClassSeeRecord::getModifyTime,
                        data.getModifyTime())
                .like(
                        Objects.nonNull(data.getDelFlag()),
                        StduentClassSeeRecord::getDelFlag,
                        data.getDelFlag())
                ;
        return stduentClassSeeRecordMapper.selectList(query);


    }
}
