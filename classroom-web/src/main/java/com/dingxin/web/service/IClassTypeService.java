package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;
import java.util.Map;

/**
 * 服务接口
 */
public interface IClassTypeService extends IService<ClassType> {

    List<ClassType> like(ClassType data);

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    IPage<ClassType> queryPage(CommQueryListRequest query);

    /**
     * 获取课程类型下拉框
     *
     * @return
     */
    List<Map<String, Object>> listMapself();

    /**
     * 获取单个课程类型
     *
     * @param idRequest
     * @return
     */
    ClassType getOneSelf(IdRequest idRequest);

    /**
     * 新增 新增成功返回0 重复返回1 失败返回2
     *
     * @param convent
     * @return
     */
    boolean saveSelf(ClassType convent);

    /**
     * 删除
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
}
