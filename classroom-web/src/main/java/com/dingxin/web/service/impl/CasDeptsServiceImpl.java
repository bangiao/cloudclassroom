package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.dao.mapper.CasDeptsMapper;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.pojo.vo.TreeVo;
import com.dingxin.web.service.ICasDeptsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


    /**
     *  根据人员部门编号获取机构数
     * @param departmentCodes 人员所属部门编号集合
     * @return
     */
    @Override
    public List<TreeVo> queryTree(List<String> departmentCodes) {

        LambdaQueryWrapper<CasDepts> qw = Wrappers.lambdaQuery();
        if (CollectionUtils.isEmpty(departmentCodes)||departmentCodes.size()==0){
            qw.in(CollectionUtils.isNotEmpty(departmentCodes)&&departmentCodes.size()>0,CasDepts::getZsjdwid,departmentCodes);
        }
        List<CasDepts> list = list(qw);
        if (CollectionUtils.isEmpty(list)||list.size()==0){
            throw new BusinessException(ExceptionEnum.DATA_ZERO);
        }
//        没有子节点
        List<CasDepts> noChildrenList = list.stream().filter(e -> !e.getCasfjdw().equals("1")).collect(Collectors.toList());
        List<TreeVo> transformation = transformation(noChildrenList);

        //        获取有子节点的数组id
        List<String> parentids = list.stream().filter(e -> e.getCasfjdw().equals("1")).map(e -> e.getZsjdwid()).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(parentids)&&parentids.size()>0) {
            LambdaQueryWrapper<CasDepts> qws = Wrappers.lambdaQuery();
            qws.in(CasDepts::getZsjdwid,parentids).or().in(CasDepts::getCasfjdw,parentids);
            List<CasDepts> parDepts = list(qws);
            List<TreeVo> treeVoList = queryTreeByDepts(parDepts);
            transformation.addAll(treeVoList);
        }

        return transformation;
    }

    /**
     *  获取结构tree
     * @param depts 部门集合
     * @return
     */
    @Override
    public List<TreeVo> queryTreeByDepts(List<CasDepts> depts) {
        if (CollectionUtils.isEmpty(depts)||depts.size()==0){
            throw  new NullPointerException("CasDepts 集合为空,转换tree失败");
        }
        List<CasDepts> par = depts.stream().filter(e -> e.getCasfjdw().equals("1")).collect(Collectors.toList());
        List<TreeVo> partree = transformation(par);
        for (TreeVo treeVo : partree) {
            ArrayList<CasDepts> var = Lists.newArrayList();
            for (CasDepts dept : depts) {
                if (dept.getCasfjdw().equals(treeVo.getId())){
                    var.add(dept);
                }
            }
            List<TreeVo> var1 = transformation(var);
            treeVo.setChildren(var1);
        }

        return partree;
    }


    /**
     * 将CasDepts 转换成treevo
     * @param depts
     * @return
     */
    @Override
    public List<TreeVo> transformation(List<CasDepts> depts) {
        if (CollectionUtils.isEmpty(depts)||depts.size()==0){
            throw  new NullPointerException("CasDepts 集合为空,转换tree失败");
        }
        ArrayList<TreeVo> tree = Lists.newArrayList();
        for (CasDepts dept : depts) {
            TreeVo build = TreeVo.builder().id(dept.getZsjdwid()).label(dept.getZsjmc()).build();
            tree.add(build);
        }
        return tree;
    }

}
