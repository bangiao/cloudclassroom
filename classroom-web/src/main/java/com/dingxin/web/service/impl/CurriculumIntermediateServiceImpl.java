package com.dingxin.web.service.impl;
import com.dingxin.pojo.po.CurriculumIntermediate;
import com.dingxin.dao.mapper.CurriculumIntermediateMapper;
import com.dingxin.web.service.ICurriculumIntermediateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class CurriculumIntermediateServiceImpl extends ServiceImpl<CurriculumIntermediateMapper, CurriculumIntermediate> implements ICurriculumIntermediateService {

    @Autowired
    private CurriculumIntermediateMapper curriculumIntermediateMapper;


    @Override
    public List<CurriculumIntermediate> like(CurriculumIntermediate data) {
        LambdaQueryWrapper<CurriculumIntermediate> query = Wrappers.<CurriculumIntermediate>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    CurriculumIntermediate::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getCurriculumId()),
                    CurriculumIntermediate::getCurriculumId,
                    data.getCurriculumId())
                .like(
                    Objects.nonNull(data.getClassTime()),
                    CurriculumIntermediate::getClassTime,
                    data.getClassTime())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    CurriculumIntermediate::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    CurriculumIntermediate::getModifyTime,
                    data.getModifyTime())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    CurriculumIntermediate::getDelFlag,
                    data.getDelFlag())
;
        return curriculumIntermediateMapper.selectList(query);


    }

}
