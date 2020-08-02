package com.dingxin.web.service;
import com.dingxin.pojo.po.CrrStudentStudyCase;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  学生学习情况服务接口
 */
public interface ICrrStudentStudyCaseService extends IService<CrrStudentStudyCase> {

    List<CrrStudentStudyCase> like(CrrStudentStudyCase data);

}
