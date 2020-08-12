package com.dingxin.web.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.CurriculumSet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.request.CurriculumSetListRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface ICurriculumSetService extends IService<CurriculumSet> {

    List<CurriculumSet> like(CurriculumSet data);

    BaseResult saveCurriculumSet(CurriculumSet curriculumSet);

    BaseResult updateCurriculumSet(CurriculumSet curriculumSet);

    BaseResult<Page<CurriculumSet>> pageList(CurriculumSetListRequest query);
}

