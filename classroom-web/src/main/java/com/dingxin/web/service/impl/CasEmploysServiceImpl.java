package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.CasEmploysMapper;
import com.dingxin.pojo.po.CasEmploys;
import com.dingxin.pojo.request.IdRoleRequest;
import com.dingxin.web.service.ICasEmploysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.web.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *  服务接口实现类
 */
@Service
@Slf4j
public class CasEmploysServiceImpl extends ServiceImpl<CasEmploysMapper, CasEmploys> implements ICasEmploysService {

    @Autowired
    private CasEmploysMapper casEmploysMapper;
    @Autowired
    private IUserRoleService userRoleService;


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
     * @param idRoleRequest
     * @return
     */
    @Override
    public List<CasEmploys> selectByDeptId(IdRoleRequest idRoleRequest) {
        LambdaQueryWrapper<CasEmploys> qw = Wrappers.lambdaQuery();
        String queryStr = idRoleRequest.getQueryStr();
        if (StringUtils.isNotEmpty(queryStr)){
            qw.and(Wrappers->Wrappers.like(CasEmploys::getName,queryStr).or().like(CasEmploys::getSid,queryStr)
            );
        }else {
            qw.eq(CasEmploys::getDisable, CommonConstant.DEL_FLAG);
            qw.like(CasEmploys::getDepts,idRoleRequest.getDeptId());
        }
        List<String> uids=userRoleService.selectUserIdsByRoleId(idRoleRequest.getRoleId());
        qw.notIn(CollectionUtils.isNotEmpty(uids),CasEmploys::getSid,uids);
        return list(qw);
    }
    /**
     * 通过用户ids 获取用户
     * @param collect
     * @return
     */
    @Override
    public List<CasEmploys> queryByIds(List<String> collect) {
        if (CollectionUtils.isEmpty(collect)){
            log.error("查询人员信息所需ids为空");
            throw new BusinessException(ExceptionEnum.REQUIRED_PARAM_IS_NULL);
        }
        LambdaQueryWrapper<CasEmploys> qw = Wrappers.lambdaQuery();
        qw.eq(CasEmploys::getDisable,CommonConstant.DEL_FLAG)
                .in(CasEmploys::getSid,collect);
        return list(qw);
    }

}
