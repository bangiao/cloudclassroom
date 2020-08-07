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
import com.dingxin.common.enums.RoleEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.common.utils.ExcelUtils;
import com.dingxin.common.utils.LogUtils;
import com.dingxin.dao.mapper.StduentClassSeeRecordMapper;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.po.Student;
import com.dingxin.pojo.request.*;
import com.dingxin.pojo.vo.StduentClassSeeRecordVo;
import com.dingxin.pojo.vo.StudentRecordListVo;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import com.dingxin.web.service.IStudentService;
import com.dingxin.web.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 学生记录表 服务接口实现类
 */
@Service
@Transactional
public class StduentClassSeeRecordServiceImpl extends ServiceImpl<StduentClassSeeRecordMapper, StduentClassSeeRecord> implements IStduentClassSeeRecordService {

    @Autowired
    private StduentClassSeeRecordMapper stduentClassSeeRecordMapper;

    @Autowired
    private IStudentService studentService;

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
                        data.getDelFlag());
        return stduentClassSeeRecordMapper.selectList(query);


    }

    /**
     * 查询学生记录列表 管理端
     *
     * @param query
     * @return
     */
    @Override
    public IPage<StduentClassSeeRecord> queryPage(CommIdQueryListRequest query) {
        LambdaQueryWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaQuery();
        qw.eq(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG);
        String queryStr = query.getQueryStr();
        if (StringUtils.isNotEmpty(queryStr)) {
            qw.like(StduentClassSeeRecord::getStudentName, query.getQueryStr())
                    .or().like(StduentClassSeeRecord::getStudentCode, query.getQueryStr())
                    .or().like(StduentClassSeeRecord::getStudentClass, query.getQueryStr());
        }
        qw.eq(!Objects.isNull(query.getId()), StduentClassSeeRecord::getClassId, query.getId());
        Page<StduentClassSeeRecord> page = new Page(query.getCurrentPage(), query.getPageSize());
        IPage pageList = page(page, qw);
        return pageList;
    }

    /**
     * 获取单条记录
     *
     * @param id
     * @return
     */
    @Override
    public StduentClassSeeRecord getOneSelf(IdRequest id) {
        LambdaQueryWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaQuery();
        qw.eq(StduentClassSeeRecord::getId, id.getId()).eq(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG);
        return getOne(qw);
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(IdRequest id) {
        LambdaUpdateWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaUpdate();
        qw.set(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getId()), StduentClassSeeRecord::getId, id.getId());
        return update(qw);
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    @Override
    public boolean deleteBatch(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        LambdaUpdateWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaUpdate();
        qw.set(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(StduentClassSeeRecord::getId, list);
        return update(qw);
    }

    /**
     * 查询自己的观看记录
     *
     * @param query
     * @return
     */
    @Override
    public IPage<StduentClassSeeRecord> selfList(CommQueryListRequest query) {
        try {
            String userId = ShiroUtils.getUserId();
            if (StringUtils.isEmpty(userId)) {
                throw new BusinessException(ExceptionEnum.PRIVILEGE_GET_USER_FAIL);
            }

            LambdaQueryWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaQuery();
            qw.eq(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG);
            RoleEnum userType = ShiroUtils.getUserType();
            if (userType.getCode() == CommonConstant.NORMAL_USER) {
                qw.eq(StduentClassSeeRecord::getStudentId, userId);
            } else if (userType.getCode() == CommonConstant.TEACHER) {
                qw.eq(StduentClassSeeRecord::getTeacherId, userId);
            } else {
                throw new BusinessException(ExceptionEnum.PRIVILEGE_CAS_FAIL);
            }
            String queryStr = query.getQueryStr();
            if (StringUtils.isNotEmpty(queryStr)) {
                qw.and(Wrappers -> Wrappers.like(StduentClassSeeRecord::getStudentName, query.getQueryStr())
                        .or().like(StduentClassSeeRecord::getStudentCode, query.getQueryStr())
                        .or().like(StduentClassSeeRecord::getStudentClass, query.getQueryStr()));
            }
            Page<StduentClassSeeRecord> page = new Page(query.getCurrentPage(), query.getPageSize());
            IPage pageList = page(page, qw);
            return pageList;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            throw new BusinessException(ExceptionEnum.SYSTEM_ERROR);
        }


    }

    /**
     * 导出
     *
     * @param ids
     */
    @Override
    public void exportExcel(List<Integer> ids, HttpServletResponse response) throws IOException {
        LambdaQueryWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaQuery();
//        全部导出
        List list = null;
        if (ids.size() == 0) {
            list = list();
        } else {
//            选中导出
            qw.in(StduentClassSeeRecord::getId, ids).eq(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG);
            list = list(qw);
        }

        if (Objects.isNull(list) && list.size() == 0) {
            throw new BusinessException(ExceptionEnum.DATA_ZERO);
        }
        ExcelUtils.exportXlsx(response, "学生记录", StduentClassSeeRecordVo.class, StduentClassSeeRecordVo.convertToVoList(list));


    }

    /**
     * 管理端 学生学习情况 学生信息列表
     *
     * @param query
     */
    @Override
    public IPage<StudentRecordListVo> studentList(StudentStudyStudentListRequest query) {
        //查询列表数据
        Page<Student> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<Student> qw = Wrappers.lambdaQuery();
        String queryStr = query.getQueryStr();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(queryStr)) {
            qw.and(Wrapper -> Wrapper.like(Student::getXm, queryStr).or()
                    .like(Student::getXsbh, queryStr).or().like(Student::getBjmc, queryStr));
        }
        IPage<Student> studentIPage = studentService.page(page, qw);
        IPage<StudentRecordListVo> studentRecordListVoIPage = StudentRecordListVo.convertToVoWithPage(studentIPage);
        List<Integer> list = studentRecordListVoIPage.getRecords().stream().map(StudentRecordListVo::getId).collect(Collectors.toList());
        if (!Objects.isNull(list) || list.size() > 0) {
            QueryWrapper<StduentClassSeeRecord> qs = Wrappers.query();
            qs.select("student_id", "sum(study_length)").eq("del_flag", CommonConstant.DEL_FLAG).in(list.size() > 0, "studentId", list).groupBy("studentId");
            Map<Integer, Long> map = list(qs).stream().collect(Collectors.toMap(StduentClassSeeRecord::getStudentId, StduentClassSeeRecord::getStudyLength));
            for (StudentRecordListVo record : studentRecordListVoIPage.getRecords()) {
                record.setXxsc((DateUtils.formatDateTimeStr(map.get(record.getId()))));
            }
        }
        return studentRecordListVoIPage;

    }

    /**
     * 管理端 学生学习情况学习课程列表
     *
     * @param query
     * @return
     */

    @Override
    public List<Map<String, Object>> queryCoursePageList(StudentStudyCaseListRequest query) {
        QueryWrapper<StduentClassSeeRecord> qw = Wrappers.query();
        qw.select("student_id", "class_id", "class_name", "class_type_name", "teacher_name", "sum(study_length) xxsc", "min(create_time) startStudentTime").eq("student_id", query.getStudentId())
                .eq("calss_id", query.getClassId()).groupBy("student_id", "calss_id").orderByAsc("create_time desc");
        List<Map<String, Object>> maps = listMaps(qw);
        for (Map<String, Object> map : maps) {
            map.put("xxsc", DateUtils.formatDateTimeStr(Long.parseLong(map.get("xxsc").toString())));
        }


        return maps;
    }

    /**
     * 观看视频记录新增
     *
     * @param stduentClassSeeRecord
     * @return
     */
    @Override
    public boolean saveOrUpdateRecord(StduentClassSeeRecord stduentClassSeeRecord) {
//        学习时长格式化
        stduentClassSeeRecord.setStudyLengthStr(DateUtils.formatDateTimeStr(stduentClassSeeRecord.getStudyLength()));
        return save(stduentClassSeeRecord);

    }


}
