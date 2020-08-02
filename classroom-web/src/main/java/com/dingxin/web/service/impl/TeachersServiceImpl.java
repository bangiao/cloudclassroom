package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.Teachers;
import com.dingxin.dao.mapper.TeachersMapper;
import com.dingxin.pojo.po.CasDepts;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.ICasDeptsService;
import com.dingxin.web.service.ITeachersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *  服务接口实现类
 */
@Service
public class TeachersServiceImpl extends ServiceImpl<TeachersMapper, Teachers> implements ITeachersService {

    @Autowired
    private TeachersMapper teachersMapper;
    @Autowired
    private ICasDeptsService iCasDeptsService;
    @Override
    public List<Teachers> like(Teachers data) {
        LambdaQueryWrapper<Teachers> query = Wrappers.<Teachers>lambdaQuery()
                .like(
                    Objects.nonNull(data.getJg0101id()),
                    Teachers::getJg0101id,
                    data.getJg0101id())
                .like(
                    Objects.nonNull(data.getJgh()),
                    Teachers::getJgh,
                    data.getJgh())
                .like(
                    Objects.nonNull(data.getXm()),
                    Teachers::getXm,
                    data.getXm())
                .like(
                    Objects.nonNull(data.getXmpy()),
                    Teachers::getXmpy,
                    data.getXmpy())
                .like(
                    Objects.nonNull(data.getCym()),
                    Teachers::getCym,
                    data.getCym())
                .like(
                    Objects.nonNull(data.getXbm()),
                    Teachers::getXbm,
                    data.getXbm())
                .like(
                    Objects.nonNull(data.getCsrq()),
                    Teachers::getCsrq,
                    data.getCsrq())
                .like(
                    Objects.nonNull(data.getCsdm()),
                    Teachers::getCsdm,
                    data.getCsdm())
                .like(
                    Objects.nonNull(data.getJg()),
                    Teachers::getJg,
                    data.getJg())
                .like(
                    Objects.nonNull(data.getGjdqm()),
                    Teachers::getGjdqm,
                    data.getGjdqm())
                .like(
                    Objects.nonNull(data.getMzm()),
                    Teachers::getMzm,
                    data.getMzm())
                .like(
                    Objects.nonNull(data.getSfzjlxm()),
                    Teachers::getSfzjlxm,
                    data.getSfzjlxm())
                .like(
                    Objects.nonNull(data.getSfzjh()),
                    Teachers::getSfzjh,
                    data.getSfzjh())
                .like(
                    Objects.nonNull(data.getSfzjyxq()),
                    Teachers::getSfzjyxq,
                    data.getSfzjyxq())
                .like(
                    Objects.nonNull(data.getBzlbm()),
                    Teachers::getBzlbm,
                    data.getBzlbm())
                .like(
                    Objects.nonNull(data.getJzglbm()),
                    Teachers::getJzglbm,
                    data.getJzglbm())
                .like(
                    Objects.nonNull(data.getDqztm()),
                    Teachers::getDqztm,
                    data.getDqztm())
                .like(
                    Objects.nonNull(data.getWhcdm()),
                    Teachers::getWhcdm,
                    data.getWhcdm())
                .like(
                    Objects.nonNull(data.getCjgzny()),
                    Teachers::getCjgzny,
                    data.getCjgzny())
                .like(
                    Objects.nonNull(data.getLxrq()),
                    Teachers::getLxrq,
                    data.getLxrq())
                .like(
                    Objects.nonNull(data.getCjny()),
                    Teachers::getCjny,
                    data.getCjny())
                .like(
                    Objects.nonNull(data.getDwh()),
                    Teachers::getDwh,
                    data.getDwh())
;
        return teachersMapper.selectList(query);


    }

    @Override
    public IPage<Teachers> queryPage(CommQueryListRequest query) {
        Page<Teachers> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.like(StringUtils.isNotEmpty(query.getQueryStr()), Teachers::getXm, query.getQueryStr());
        IPage<Teachers> teachers = page(page, qw);
        List<String> dwhList = teachers.getRecords().stream().map(Teachers::getDwh).collect(Collectors.toList());
        LambdaQueryWrapper<CasDepts> deptQw = Wrappers.lambdaQuery();
        deptQw.in(dwhList.size() > 0,CasDepts::getZsjdwid,dwhList);
        List<CasDepts> deptsList = iCasDeptsService.list(deptQw);
        Map<String, String> collect = deptsList.stream().collect(Collectors.toMap(CasDepts::getZsjdwid, CasDepts::getZsjmc));
        for (Teachers teacher: teachers.getRecords()) {
           if (collect.containsKey(teacher.getDwh())){
               teacher.setZsjmc(collect.get(teacher.getDwh()));
           }
        }
        return teachers;
    }

    @Override
    public IPage<Teachers> queryPCPage(CommQueryListRequest query) {
        Page<Teachers> page = new Page(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.like(StringUtils.isNotEmpty(query.getQueryStr()), Teachers::getXm, query.getQueryStr()).eq(Teachers::getEnable,CommonConstant.DISABLE_FALSE);
        IPage<Teachers> teachers = page(page, qw);
        List<String> dwhList = teachers.getRecords().stream().map(Teachers::getDwh).collect(Collectors.toList());
        LambdaQueryWrapper<CasDepts> deptQw = Wrappers.lambdaQuery();
        deptQw.in(dwhList.size() > 0,CasDepts::getZsjdwid,dwhList);
        List<CasDepts> deptsList = iCasDeptsService.list(deptQw);
        Map<String, String> collect = deptsList.stream().collect(Collectors.toMap(CasDepts::getZsjdwid, CasDepts::getZsjmc));
        for (Teachers teacher: teachers.getRecords()) {
            if (collect.containsKey(teacher.getDwh())){
                teacher.setZsjmc(collect.get(teacher.getDwh()));
            }
        }
        return teachers;
    }

    @Override
    public Teachers queryById(IdRequest idRequest) {
        LambdaQueryWrapper<Teachers> qw = Wrappers.lambdaQuery();
        qw.eq(Teachers::getJg0101id,idRequest.getId()).eq(Teachers::getEnable,CommonConstant.DISABLE_FALSE);
        return getOne(qw);
    }

}
