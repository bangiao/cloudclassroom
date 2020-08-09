package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.dao.mapper.CasDeptsMapper;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.BannerManage;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.web.service.ICasDeptsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 服务接口实现类
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
                        data.getDisable());
        return casDeptsMapper.selectList(query);


    }

    /**
     * 查询部门列表
     *
     * @return
     */
    @Override
    public BaseResult<Page<CasDepts>> queryPageList() {
        List<CasDepts> list = list();
        List<CasDepts> parent = list.stream().filter(d -> "1".equals(d.getCasfjdw())).collect(Collectors.toList());

        for (CasDepts casDepts : parent) {
            casDepts.setSubDepts(tree(casDepts.getZsjdwid(),list));
        }
        return BaseResult.success(parent);
    }

    private List<CasDepts> tree(String id,List<CasDepts> root) {
        List<CasDepts> childList = new ArrayList<>();
        for (CasDepts parDept : root) {
            if (StringUtils.isNotEmpty(parDept.getCasfjdw())){
                if (parDept.getCasfjdw().equals(id)){
                    childList.add(parDept);
                }
            }
        };
        for (CasDepts depts : childList) {
            if (depts.getZsjdwid().length()>id.length()){
                depts.setSubDepts(tree(depts.getZsjdwid(),root));
            }
        }
        if (CollectionUtils.isEmpty(childList)){
            return null;
        }
        return childList;
    }
}
