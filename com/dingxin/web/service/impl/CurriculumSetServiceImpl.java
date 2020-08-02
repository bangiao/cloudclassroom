package com.dingxin.web.service.impl;
import com.dingxin.pojo.po.CurriculumSet;
import com.dingxin.dao.mapper.CurriculumSetMapper;
import com.dingxin.web.service.ICurriculumSetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class CurriculumSetServiceImpl extends ServiceImpl<CurriculumSetMapper, CurriculumSet> implements ICurriculumSetService {

    @Autowired
    private CurriculumSetMapper curriculumSetMapper;


    @Override
    public List<CurriculumSet> like(CurriculumSet data) {
        LambdaQueryWrapper<CurriculumSet> query = Wrappers.<CurriculumSet>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    CurriculumSet::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getCurriculumName()),
                    CurriculumSet::getCurriculumName,
                    data.getCurriculumName())
                .like(
                    Objects.nonNull(data.getRemark()),
                    CurriculumSet::getRemark,
                    data.getRemark())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    CurriculumSet::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    CurriculumSet::getModifyTime,
                    data.getModifyTime())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    CurriculumSet::getDelFlag,
                    data.getDelFlag())
;
        return curriculumSetMapper.selectList(query);


    }

}
