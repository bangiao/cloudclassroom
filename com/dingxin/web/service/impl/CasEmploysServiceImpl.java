package com.dingxin.web.service.impl;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.dao.mapper.CasEmploysMapper;
import com.dingxin.web.service.ICasEmploysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class CasEmploysServiceImpl extends ServiceImpl<CasEmploysMapper, CasEmploys> implements ICasEmploysService {

    @Autowired
    private CasEmploysMapper casEmploysMapper;


    @Override
    public List<CasEmploys> like(CasEmploys data) {
        LambdaQueryWrapper<CasEmploys> query = Wrappers.<CasEmploys>lambdaQuery()
                .like(
                    Objects.nonNull(data.getSid()),
                    CasEmploys::getSid,
                    data.getSid())
                .like(
                    Objects.nonNull(data.getCasid()),
                    CasEmploys::getCasid,
                    data.getCasid())
                .like(
                    Objects.nonNull(data.getDisable()),
                    CasEmploys::getDisable,
                    data.getDisable())
                .like(
                    Objects.nonNull(data.getDepts()),
                    CasEmploys::getDepts,
                    data.getDepts())
                .like(
                    Objects.nonNull(data.getGroup()),
                    CasEmploys::getGroup,
                    data.getGroup())
                .like(
                    Objects.nonNull(data.getName()),
                    CasEmploys::getName,
                    data.getName())
                .like(
                    Objects.nonNull(data.getEmail()),
                    CasEmploys::getEmail,
                    data.getEmail())
                .like(
                    Objects.nonNull(data.getPhonenum()),
                    CasEmploys::getPhonenum,
                    data.getPhonenum())
                .like(
                    Objects.nonNull(data.getZw()),
                    CasEmploys::getZw,
                    data.getZw())
;
        return casEmploysMapper.selectList(query);


    }

}
