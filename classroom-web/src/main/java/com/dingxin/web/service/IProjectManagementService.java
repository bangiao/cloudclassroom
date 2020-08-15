package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ProjectManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.WidRequest;
import com.dingxin.pojo.vo.HotListVo;
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

    List<HotListVo> queryPCPageByCount(CommQueryListRequest query);

    IPage<ProjectManagementVo> searchProjectNameList(CommQueryListRequest query);

    IPage<ProjectManagement> searchByProjectName(CommQueryListRequest query);

    BaseResult insertOne(ProjectManagement projectManagement);

    BaseResult updateProject(ProjectManagement projectManagement);

    BaseResult logicDeleteByIds(List<Integer> idList);

    List<ProjectManagement> listall(WidRequest idRequest);

    BaseResult watchAmout(IdRequest idRequest);

    BaseResult<ProjectManagementVo> projectNameList(BaseQuery<ProjectManagement> query);
}



