package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.request.StudentStudyStudentListRequest;

import java.util.List;

/**
 * 学生信息表 服务接口
 */
public interface IStudentService extends IService<Student> {

    List<Student> like(Student data);

    IPage queryPageList(StudentStudyStudentListRequest query);
}
