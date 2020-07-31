package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.ProjectManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ProjectManagementVo;

import java.util.List;

/**
 *  服务接口
 */
public interface IProjectManagementService extends IService<ProjectManagement> {

    List<ProjectManagement> like(ProjectManagement data);

    IPage<ProjectManagement> queryPage(CommQueryListRequest query);

    IPage<ProjectManagement> queryPCPage(CommQueryListRequest query);

    boolean deleteByIds(List<Integer> idList);

    ProjectManagement searchOneById(IdRequest idRequest);

    IPage<ProjectManagement> queryPCPageByCount(CommQueryListRequest query);

    IPage<ProjectManagementVo> searchProjectNameList(CommQueryListRequest query);

    IPage<ProjectManagement> searchByProjectName(CommQueryListRequest query);
}

