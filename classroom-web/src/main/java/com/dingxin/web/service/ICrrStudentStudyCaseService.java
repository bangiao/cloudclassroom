package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.CrrStudentStudyCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Student;
import com.dingxin.pojo.request.StudentStudyCaseListRequest;

import java.util.List;

/**
 *  学生学习情况服务接口
 */
public interface ICrrStudentStudyCaseService extends IService<CrrStudentStudyCase> {

    List<CrrStudentStudyCase> like(CrrStudentStudyCase data);

    IPage queryCoursePageList(StudentStudyCaseListRequest student);

}
