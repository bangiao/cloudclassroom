package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.request.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 课程评价表 服务接口
 */
public interface IClassEvaluateService extends IService<ClassEvaluate> {

    List<ClassEvaluate> like(ClassEvaluate data);

    /**
     * 修改点赞数
     *
     * @return
     */
    boolean updateUp(ThumbsUpRequest thumbsUpVo);

    /**
     * 查询数据列表
     *
     * @param query
     * @return
     */
    IPage queryPage(ClassEvaluateListRequest query);

    /**
     * 删除课程评价
     *
     * @param id
     * @return
     */
    boolean delete(IdRequest id);

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    boolean deleteBatch(List<Integer> list);

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    ClassEvaluate getByIdSelf(IdRequest id);

    /**
     * 获取评价列表
     * @param query
     * @return
     */
    IPage queryAuditPageList(ClassEvaluateListRequest query);

    /**
     * 批量审核评价
     * @param classEvaluateRequest
     */
    void auditBatch(ClassEvaluateRequest classEvaluateRequest);

    /**
     * 单个审核评价
     * @param videoAuditRequest
     */
    void audit(VideoAuditRequest videoAuditRequest);
    /**
     *
     * @param classEvaluate
     */
    void saveEvaluation(ClassEvaluate classEvaluate);
    /**
     * 更新课程对应的课程评价状态
     */
    void updateCurriculumRelatedEvaluateStatus(Integer curriculumId);

    /**
     * PC端获取课程评价
     * @param query
     * @return
     */
    IPage<ClassEvaluate> queryUserPage(ClassEvaluateListRequest query);

    /**
     * 通过课程id导出评价
     * @param request
     */
    void export(ClassIdRequest request, HttpServletResponse response);
}
