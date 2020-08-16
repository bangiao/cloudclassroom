package com.dingxin.web.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Achievement;

import java.util.List;

/**
 * 成就 服务接口
 */
public interface IAchievementService extends IService<Achievement> {


    BaseResult<List<Achievement>> search();
}
