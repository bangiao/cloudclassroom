package com.dingxin.web.service.strategy.curriculum;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumRequest;
import com.dingxin.web.service.impl.CurriculumServiceImpl;
import org.springframework.stereotype.Component;

/**
 * author: cuteG <br>
 * date: 2020/7/28 0:24 <br>
 * description: todo <br>
 */
@Component
public class CurriculumStrategyContext {

    private CurriculumServiceImpl curriculumService;

    public CurriculumStrategyContext() {
    }

    public CurriculumStrategyContext(CurriculumServiceImpl strategy) {
        this.curriculumService = strategy;
    }


    public IPage<Curriculum> getPageList(BaseQuery<CurriculumRequest> query){

        return curriculumService.getPage(query);
    }
}