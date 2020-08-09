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
import javax.validation.constraints.NotNull;
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
    public boolean delete(StudentStudyCaseListRequest id) {
        LambdaUpdateWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaUpdate();
        qw.set(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getStudentId()), StduentClassSeeRecord::getStudentId, id.getStudentId());
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
            qs.select("student_id", "sum(study_length) as sum ").eq("del_flag", CommonConstant.DEL_FLAG).in(list.size() > 0, "student_id", list).groupBy("student_id");
            List<Map<String, Object>> maps = listMaps(qs);
            if (!com.dingxin.common.utils.CollectionUtils.isEmpty(maps)) {
                for (StudentRecordListVo record : studentRecordListVoIPage.getRecords()) {
                    for (Map<String, Object> map : maps) {
                        if (Integer.parseInt(map.get("student_id").toString()) == record.getId()) {
                            record.setXxsc((DateUtils.formatDateTimeStr(Long.parseLong(map.get("sum").toString()))));
                        }
                    }

                }
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
    public IPage queryCoursePageList(StudentStudyCaseListRequest query) {
        LambdaQueryWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaQuery();
        qw.eq(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG);
        qw.eq(StduentClassSeeRecord::getStudentId,query.getStudentId());

        Page<StduentClassSeeRecord> page = new Page(query.getCurrentPage(), query.getPageSize());
        IPage pageList = page(page, qw);


        return pageList;
    }
    /**
     * 通过学生id课程id精准删除一条学习记录
     * @param scid
     * @return
     */
    @Override
    public boolean deleteForClass(StudentStudyCaseClassRequest scid) {
        LambdaUpdateWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaUpdate();
        qw.set(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(scid.getStudentId()), StduentClassSeeRecord::getStudentId, scid.getStudentId())
        .eq(StduentClassSeeRecord::getClassId,scid.getClassId());
        return update(qw);
    }
    /**
     * 批量删除通过过学生id课程id精准删除一条学习记录
     * @param
     * @return
     */
    @Override
    public boolean deleteForClassBatch(StudentStudyCaseClassBatchRequest list) {
        if (Objects.isNull(list)) {
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        LambdaUpdateWrapper<StduentClassSeeRecord> qw = Wrappers.lambdaUpdate();
        qw.set(StduentClassSeeRecord::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(StduentClassSeeRecord::getStudentId,list.getStudentId()).in(StduentClassSeeRecord::getClassId, list.getClassIdList());
        return update(qw);
    }

    /**
     * 观看视频记录新增
     *
     * @param stduentClassSeeRecord
     * @return
     */
    @Override
    public boolean saveOrUpdateRecord(StduentClassSeeRecord stduentClassSeeRecord) {
        LambdaQueryWrapper<StduentClassSeeRecord>  qw= Wrappers.lambdaQuery();
        qw.eq(StduentClassSeeRecord::getDelFlag,CommonConstant.DEL_FLAG)
                .eq(StduentClassSeeRecord::getStudentId,stduentClassSeeRecord.getStudentId())
                .eq(StduentClassSeeRecord::getClassId,stduentClassSeeRecord.getClassId());
        StduentClassSeeRecord one = getOne(qw);
        if (!Objects.isNull(one)){
            one.setStudyLength(stduentClassSeeRecord.getStudyLength());
            one.setStudyLengthStr(DateUtils.formatDateTimeStr(stduentClassSeeRecord.getStudyLength()));
            stduentClassSeeRecord=one;
        }else {
//        学习时长格式化
            stduentClassSeeRecord.setStudyLengthStr(DateUtils.formatDateTimeStr(stduentClassSeeRecord.getStudyLength()));
        }

        return saveOrUpdate(stduentClassSeeRecord);

    }


}
