package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.dao.mapper.CasDeptsMapper;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.web.service.ICasDeptsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class CasDeptsServiceImpl extends ServiceImpl<CasDeptsMapper, CasDepts> implements ICasDeptsService {

    @Autowired
    private CasDeptsMapper casDeptsMapper;


    @Override
    public List<CasDepts> like(CasDepts data) {
        LambdaQueryWrapper<CasDepts> query = Wrappers.<CasDepts>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    CasDepts::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getZsjdwid()),
                    CasDepts::getZsjdwid,
                    data.getZsjdwid())
                .like(
                    Objects.nonNull(data.getZsjmc()),
                    CasDepts::getZsjmc,
                    data.getZsjmc())
                .like(
                    Objects.nonNull(data.getCasdwid()),
                    CasDepts::getCasdwid,
                    data.getCasdwid())
                .like(
                    Objects.nonNull(data.getCasdwmc()),
                    CasDepts::getCasdwmc,
                    data.getCasdwmc())
                .like(
                    Objects.nonNull(data.getCasfjdw()),
                    CasDepts::getCasfjdw,
                    data.getCasfjdw())
                .like(
                    Objects.nonNull(data.getDisable()),
                    CasDepts::getDisable,
                    data.getDisable())
;
        return casDeptsMapper.selectList(query);


    }

}
