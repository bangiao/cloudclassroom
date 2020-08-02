package com.dingxin.web.service;
import com.dingxin.pojo.po.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 学生信息表 服务接口
 */
public interface IStudentService extends IService<Student> {

    List<Student> like(Student data);

}
