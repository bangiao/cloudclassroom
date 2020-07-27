package com.dingxin.web.service.strategy.curriculum;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.dao.mapper.CurriculumMapper;
import com.dingxin.pojo.vo.CurriculumVo;
import com.dingxin.web.service.impl.CurriculumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * author: cuteG <br>
 * date: 2020/7/28 0:13 <br>
 * description: todo <br>
 */
@Service("AdministratorStrategy")
public class AdministratorStrategy extends CurriculumServiceImpl {

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Override
    public IPage<CurriculumVo> getPage() {
        return null;
    }
}