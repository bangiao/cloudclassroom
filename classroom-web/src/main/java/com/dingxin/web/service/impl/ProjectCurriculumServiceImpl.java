package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectCurriculum;
import com.dingxin.dao.mapper.ProjectCurriculumMapper;
import com.dingxin.pojo.po.ProjectManagement;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.CurriculumPcVo;
import com.dingxin.pojo.vo.CurriculumVo;
import com.dingxin.web.service.IClassCollectionService;
import com.dingxin.web.service.ICurriculumService;
import com.dingxin.web.service.IProjectCurriculumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.web.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *  服务接口实现类
 */
@Service
public class ProjectCurriculumServiceImpl extends ServiceImpl<ProjectCurriculumMapper, ProjectCurriculum> implements IProjectCurriculumService {

    @Autowired
    private ProjectCurriculumMapper projectCurriculumMapper;
    @Autowired
    private ICurriculumService curriculumService;
    @Autowired
    private IClassCollectionService classCollectionService;
    @Override
    public List<ProjectCurriculum> like(ProjectCurriculum data) {
        LambdaQueryWrapper<ProjectCurriculum> query = Wrappers.<ProjectCurriculum>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    ProjectCurriculum::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getProjectId()),
                    ProjectCurriculum::getProjectId,
                    data.getProjectId())
                .like(
                    Objects.nonNull(data.getCurriculumId()),
                    ProjectCurriculum::getCurriculumId,
                    data.getCurriculumId())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    ProjectCurriculum::getDelFlag,
                    data.getDelFlag())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    ProjectCurriculum::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    ProjectCurriculum::getModifyTime,
                    data.getModifyTime())
;
        return projectCurriculumMapper.selectList(query);


    }

    @Override
    public BaseResult<CurriculumPcVo> ListByProjectName(IdRequest idRequest) {
        Page<Curriculum> page = new Page(idRequest.getCurrentPage(), idRequest.getPageSize());
        LambdaQueryWrapper<ProjectCurriculum> projectQw = Wrappers.<ProjectCurriculum>lambdaQuery()
                .eq(ProjectCurriculum::getProjectId, idRequest.getId())
                .eq(ProjectCurriculum::getDelFlag, CommonConstant.DEL_FLAG)
                .eq(ProjectCurriculum::getEnable, CommonConstant.DISABLE_FALSE)
                .select(ProjectCurriculum::getCurriculumId);
        List<ProjectCurriculum> list = this.list(projectQw);
        if (CollectionUtils.isNotEmpty(list)){
            List<Integer> collect = list.stream().map(ProjectCurriculum::getCurriculumId).collect(Collectors.toList());
            LambdaQueryWrapper<Curriculum> curriculumQw = Wrappers.<Curriculum>lambdaQuery()
                    .eq(Curriculum::getDisableFlag, CommonConstant.DISABLE_FALSE)
                    .eq(Curriculum::getDeleteFlag, CommonConstant.DEL_FLAG)
                    .in(Curriculum::getId, collect);
            IPage<Curriculum> curriculumIPage = curriculumService.page(page, curriculumQw);
            List<Curriculum> records = curriculumIPage.getRecords();
            if (com.dingxin.common.utils.CollectionUtils.isEmpty(records)){
                return BaseResult.success().setMsg("查询课程成功");
            }
            IPage<CurriculumPcVo> curriculumPcVoIPage = CurriculumPcVo.convertToVoWithPage(curriculumIPage);
            //查询收藏信息
            LambdaQueryWrapper<ClassCollection> callssQw = Wrappers.<ClassCollection>lambdaQuery()
                    .eq(ClassCollection::getPersonId, ShiroUtils.getUserId())
                    .eq(ClassCollection::getDelFlag, CommonConstant.DEL_FLAG);
            List<ClassCollection> collectionList = classCollectionService.list(callssQw);
            if (com.dingxin.common.utils.CollectionUtils.isNotEmpty(list)){
                curriculumPcVoIPage.getRecords().stream().forEach(s->{
                    collectionList.stream().forEach(f->{
                        if (s.getId().equals(f.getClassId())){
                            s.setIsCollection(true);
                        }else {
                            s.setIsCollection(false);
                        }

                    });
                });
            }

            return BaseResult.success((curriculumPcVoIPage));
        }
        return BaseResult.success().setMsg("根据专题名称获取课程成功");
    }

}
