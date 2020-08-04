package com.dingxin.web.service.strategy.curriculum;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.request.CurriculumFuzzyQuery4List;
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


    public IPage<Curriculum> getPageList(CurriculumFuzzyQuery4List query){

        return curriculumService.getPage(query);
    }
}