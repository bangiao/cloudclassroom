package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;

/**
 * 课程收藏表 服务接口
 */
public interface IClassCollectionService extends IService<ClassCollection> {

    List<ClassCollection> like(ClassCollection data);

    /**
     * 查询收场课程列表
     *
     * @param query
     * @return
     */
    IPage queryList(CommQueryListRequest query);

    /**
     * 新增课程收藏
     *
     * @param classCollection
     * @return
     */
    boolean insert(ClassCollection classCollection);

    /**
     * 删除课程收藏
     *
     * @param id
     * @return
     */
    boolean deleteById(IdRequest id);

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    boolean deleteByBatch(List<Integer> list);

    /**
     * 获取单个
     *
     * @param id
     * @return
     */
    ClassCollection getByIdSelf(IdRequest id);
}
