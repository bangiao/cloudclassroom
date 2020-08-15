package com.dingxin.web.service;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.CurriculumPcVo;
import com.dingxin.pojo.vo.CurriculumVo;

import java.util.List;

/**
 *  服务接口
 */
public interface IProjectCurriculumService extends IService<ProjectCurriculum> {

    List<ProjectCurriculum> like(ProjectCurriculum data);

    BaseResult<CurriculumPcVo> ListByProjectName(IdRequest idRequest);
}
