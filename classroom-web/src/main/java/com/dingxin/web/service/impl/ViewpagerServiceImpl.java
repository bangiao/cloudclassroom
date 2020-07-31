package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.Viewpager;
import com.dingxin.dao.mapper.ViewpagerMapper;
import com.dingxin.web.service.IViewpagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class ViewpagerServiceImpl extends ServiceImpl<ViewpagerMapper, Viewpager> implements IViewpagerService {

    @Autowired
    private ViewpagerMapper viewpagerMapper;


    @Override
    public List<Viewpager> like(Viewpager data) {
        LambdaQueryWrapper<Viewpager> query = Wrappers.<Viewpager>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Viewpager::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getPicName()),
                    Viewpager::getPicName,
                    data.getPicName())
                .like(
                    Objects.nonNull(data.getPicType()),
                    Viewpager::getPicType,
                    data.getPicType())
                .like(
                    Objects.nonNull(data.getPicSize()),
                    Viewpager::getPicSize,
                    data.getPicSize())
                .like(
                    Objects.nonNull(data.getPicUrl()),
                    Viewpager::getPicUrl,
                    data.getPicUrl())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    Viewpager::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    Viewpager::getModifyTime,
                    data.getModifyTime())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    Viewpager::getDelFlag,
                    data.getDelFlag())
;
        return viewpagerMapper.selectList(query);


    }

}
