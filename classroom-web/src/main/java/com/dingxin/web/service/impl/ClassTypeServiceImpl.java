package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.dao.mapper.ClassTypeMapper;
import com.dingxin.web.service.IClassTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class ClassTypeServiceImpl extends ServiceImpl<ClassTypeMapper, ClassType> implements IClassTypeService {

    @Autowired
    private ClassTypeMapper classTypeMapper;


    @Override
    public List<ClassType> like(ClassType data) {
        LambdaQueryWrapper<ClassType> query = Wrappers.<ClassType>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    ClassType::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getTypeName()),
                    ClassType::getTypeName,
                    data.getTypeName())
                .like(
                    Objects.nonNull(data.getDataName()),
                    ClassType::getDataName,
                    data.getDataName())
                .like(
                    Objects.nonNull(data.getCreatePersonId()),
                    ClassType::getCreatePersonId,
                    data.getCreatePersonId())
                .like(
                    Objects.nonNull(data.getCreatePersonName()),
                    ClassType::getCreatePersonName,
                    data.getCreatePersonName())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    ClassType::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    ClassType::getModifyTime,
                    data.getModifyTime())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    ClassType::getDelFlag,
                    data.getDelFlag())
;
        return classTypeMapper.selectList(query);


    }

}
