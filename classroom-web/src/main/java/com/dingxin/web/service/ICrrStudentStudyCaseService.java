package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.CrrStudentStudyCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Student;
import com.dingxin.pojo.request.StudentStudyCaseListRequest;

import java.util.List;
import java.util.Map;

/**
 *  学生学习情况服务接口
 */
public interface ICrrStudentStudyCaseService extends IService<CrrStudentStudyCase> {

    List<CrrStudentStudyCase> like(CrrStudentStudyCase data);

    /**
     * 学习课程列表
     */
    IPage queryCoursePageList(StudentStudyCaseListRequest student);


}
