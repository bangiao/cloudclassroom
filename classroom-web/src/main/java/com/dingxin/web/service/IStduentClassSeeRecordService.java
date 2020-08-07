package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.request.*;
import com.dingxin.pojo.vo.StudentRecordListVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 学生记录表 服务接口
 */
public interface IStduentClassSeeRecordService extends IService<StduentClassSeeRecord> {
    List<StduentClassSeeRecord> like(StduentClassSeeRecord data);

    /**
     * 记录新增
     *
     * @param stduentClassSeeRecord
     * @return
     */
    boolean saveOrUpdateRecord(StduentClassSeeRecord stduentClassSeeRecord);

    /**
     * 查询学生记录
     *
     * @param query
     * @return
     */
    IPage<StduentClassSeeRecord> queryPage(CommIdQueryListRequest query);

    /**
     * 获取单条记录
     *
     * @param id
     * @return
     */
    StduentClassSeeRecord getOneSelf(IdRequest id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(IdRequest id);

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    boolean deleteBatch(List<Integer> list);

    /**
     * 查询自己的列表
     *
     * @param query
     * @return
     */
    IPage<StduentClassSeeRecord> selfList(CommQueryListRequest query);

    /**
     * 导出
     *
     * @param ids
     */
    void exportExcel(List<Integer> ids, HttpServletResponse response) throws IOException;

    /**
     * 管理端 学生学习情况 学生信息列表
     *
     * @param query
     */
    IPage<StudentRecordListVo> studentList(StudentStudyStudentListRequest query);

    /**
     * 管理端 学生学习情况学习课程列表
     *
     * @param query
     * @return
     */
    List<Map<String, Object>> queryCoursePageList(StudentStudyCaseListRequest query);
}
