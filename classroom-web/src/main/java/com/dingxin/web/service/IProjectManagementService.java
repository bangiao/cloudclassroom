package com.dingxin.web.service;
import com.dingxin.pojo.po.ProjectManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IProjectManagementService extends IService<ProjectManagement> {

    List<ProjectManagement> like(ProjectManagement data);

}
