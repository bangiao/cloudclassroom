package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.ClassCollectionInsertRequest;
import com.dingxin.pojo.request.ClassIdRequest;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;

/**
 * 课程收藏表 服务接口
 */
public interface IClassCollectionService extends IService<ClassCollection> {



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
    boolean insert(ClassCollectionInsertRequest classCollection);

    /**
     * 删除课程收藏
     *
     * @param id
     * @return
     */
    boolean deleteById(ClassIdRequest id);

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
    Curriculum getByIdSelf(ClassIdRequest id);
}
