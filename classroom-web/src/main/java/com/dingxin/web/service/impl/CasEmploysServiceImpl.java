package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.dao.mapper.CasEmploysMapper;
import com.dingxin.pojo.po.CasEmploys;
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
                    Objects.nonNull(data.getGroups()),
                    CasEmploys::getGroups,
                    data.getGroups())
                .like(
                    Objects.nonNull(data.getName()),
                    CasEmploys::getName,
                    data.getName())
                .like(
                    Objects.nonNull(data.getEmail()),
                    CasEmploys::getEmail,
                    data.getEmail())
                .like(
                    Objects.nonNull(data.getPhoneNum()),
                    CasEmploys::getPhoneNum,
                    data.getPhoneNum())
                .like(
                    Objects.nonNull(data.getZw()),
                    CasEmploys::getZw,
                    data.getZw())
;
        return casEmploysMapper.selectList(query);


    }

    /**
     * 根据部门id查询下面所有人员信息
     * @param ids 部门id
     * @return
     */
    @Override
    public List<CasEmploys> selectByDeptId(List<Integer> ids,String queryStr) {
        LambdaQueryWrapper<CasEmploys> qw = Wrappers.lambdaQuery();
        qw.eq(CasEmploys::getDisable, CommonConstant.DEL_FLAG);

        if (CollectionUtils.isNotEmpty(ids)){
            LambdaQueryWrapper<CasEmploys> qe = Wrappers.lambdaQuery();
            for (int i = 0; i < ids.size(); i++) {
                Integer integer = ids.get(i);
                if(i==0){
                    qe.like(CasEmploys::getDepts,integer);
                }else {
                    qe.or().like(CasEmploys::getDepts,integer);
                }

            }
            qw.and(Wrappers->qe);

        }

        if (StringUtils.isNotEmpty(queryStr)){
            qw.and(Wrappers->Wrappers.like(CasEmploys::getName,queryStr).or().like(CasEmploys::getSid,queryStr)
            );
        }
        return list(qw);
    }

}
