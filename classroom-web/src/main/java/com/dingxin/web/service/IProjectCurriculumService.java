package com.dingxin.web.service;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IProjectCurriculumService extends IService<ProjectCurriculum> {

    List<ProjectCurriculum> like(ProjectCurriculum data);

}
