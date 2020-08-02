package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.dao.mapper.CurriculumSetMapper;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.CurriculumIntermediate;
import com.dingxin.pojo.po.CurriculumSet;
import com.dingxin.web.service.ICurriculumIntermediateService;
import com.dingxin.web.service.ICurriculumSetService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *  服务接口实现类
 */
@Service
public class CurriculumSetServiceImpl extends ServiceImpl<CurriculumSetMapper, CurriculumSet> implements ICurriculumSetService {

    @Autowired
    private CurriculumSetMapper curriculumSetMapper;

    @Autowired
    private ICurriculumIntermediateService curriculumIntermediateService;
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

    /**
     * 新增课程表
     * @param curriculumSet
     * @return
     */
    @Override
    public BaseResult saveCurriculumSet(CurriculumSet curriculumSet) {
        curriculumSet.setModifyTime(LocalDateTime.now());
        this.save(curriculumSet);
        //新增信息到中间表
        List<Curriculum> curriculums = curriculumSet.getCurriculums();
        if (CollectionUtils.isNotEmpty(curriculums)){
            List<CurriculumIntermediate> curriculumIntermediates = curriculums.stream().map(s -> {
                CurriculumIntermediate curriculumIntermediate = new CurriculumIntermediate();
                curriculumIntermediate.setCurriculumSetId(curriculumSet.getId());
                curriculumIntermediate.setCurriculumId(s.getId());
                curriculumIntermediate.setClassTime(s.getClassTime());
                curriculumIntermediate.setModifyTime(LocalDateTime.now());
                return curriculumIntermediate;
            }).collect(Collectors.toList());
            curriculumIntermediateService.saveBatch(curriculumIntermediates);
        }
        return BaseResult.success().setMsg("新增课程表成功");
    }

    /**
     * 修改课程表
     * @param curriculumSet
     * @return
     */
    @Override
    public BaseResult updateCurriculumSet(CurriculumSet curriculumSet) {
        LambdaUpdateWrapper<CurriculumSet> qw = Wrappers.lambdaUpdate();
        qw
                .set(StringUtils.isNotEmpty(curriculumSet.getCurriculumName()),CurriculumSet::getCurriculumName,curriculumSet.getCurriculumName())
                .set(StringUtils.isNotEmpty(curriculumSet.getRemark()),CurriculumSet::getRemark,curriculumSet.getRemark())
                .set(CurriculumSet::getModifyTime,LocalDateTime.now());
        this.update(curriculumSet,qw);
        LambdaQueryWrapper<CurriculumIntermediate> curriculumIntermediateQw = Wrappers.lambdaQuery();
        curriculumIntermediateQw.eq(null != curriculumSet.getId(),CurriculumIntermediate::getId,curriculumSet.getId());
        curriculumIntermediateService.remove(curriculumIntermediateQw);
        List<Curriculum> curriculums = curriculumSet.getCurriculums();
        if (CollectionUtils.isNotEmpty(curriculums)){
            List<CurriculumIntermediate> curriculumIntermediates = curriculums.stream().map(s -> {
                CurriculumIntermediate curriculumIntermediate = new CurriculumIntermediate();
                curriculumIntermediate.setCurriculumId(s.getId());
                curriculumIntermediate.setCurriculumSetId(curriculumSet.getId());
                curriculumIntermediate.setClassTime(s.getClassTime());
                return curriculumIntermediate;
            }).collect(Collectors.toList());
            curriculumIntermediateService.saveBatch(curriculumIntermediates);
        }
        return BaseResult.success().setMsg("修改课程表成功");
    }

}
