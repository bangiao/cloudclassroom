package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.TeacherVo;

import java.util.List;
import java.util.Map;

/**
 *  服务接口
 */
public interface ITeachersService extends IService<Teachers> {

    List<Teachers> like(Teachers data);

    IPage queryPage(CommQueryListRequest query);

    IPage<Teachers> queryPCPage(CommQueryListRequest query);

    TeacherVo queryById(IdRequest idRequest);

    /**
     * 获取所有讲师的下拉列表值
     * @param
     * @return
     */
    List<Map<String,Object>> queryAll();

    BaseResult updateIntroduction(Teachers teachers);

    BaseResult disEnable(Teachers teachers);

    BaseResult enable(Teachers teachers);
}
