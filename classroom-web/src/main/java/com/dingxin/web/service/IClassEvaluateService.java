package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.request.ClassEvaluateListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.request.ThumbsUpRequest;

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
}
