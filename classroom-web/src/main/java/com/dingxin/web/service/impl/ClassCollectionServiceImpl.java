package com.dingxin.web.service.impl;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.dao.mapper.ClassCollectionMapper;
import com.dingxin.web.service.IClassCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * 课程收藏表 服务接口实现类
 */
@Service
public class ClassCollectionServiceImpl extends ServiceImpl<ClassCollectionMapper, ClassCollection> implements IClassCollectionService {

    @Autowired
    private ClassCollectionMapper classCollectionMapper;


    @Override
    public List<ClassCollection> like(ClassCollection data) {
        LambdaQueryWrapper<ClassCollection> query = Wrappers.<ClassCollection>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    ClassCollection::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getPersonId()),
                    ClassCollection::getPersonId,
                    data.getPersonId())
                .like(
                    Objects.nonNull(data.getClassId()),
                    ClassCollection::getClassId,
                    data.getClassId())
                .like(
                    Objects.nonNull(data.getClassName()),
                    ClassCollection::getClassName,
                    data.getClassName())
                .like(
                    Objects.nonNull(data.getClassType()),
                    ClassCollection::getClassType,
                    data.getClassType())
                .like(
                    Objects.nonNull(data.getClassTypeStr()),
                    ClassCollection::getClassTypeStr,
                    data.getClassTypeStr())
                .like(
                    Objects.nonNull(data.getTeacherId()),
                    ClassCollection::getTeacherId,
                    data.getTeacherId())
                .like(
                    Objects.nonNull(data.getTeacherName()),
                    ClassCollection::getTeacherName,
                    data.getTeacherName())
                .like(
                    Objects.nonNull(data.getCreateTime()),
                    ClassCollection::getCreateTime,
                    data.getCreateTime())
                .like(
                    Objects.nonNull(data.getModifyTime()),
                    ClassCollection::getModifyTime,
                    data.getModifyTime())
                .like(
                    Objects.nonNull(data.getDelFlag()),
                    ClassCollection::getDelFlag,
                    data.getDelFlag())
;
        return classCollectionMapper.selectList(query);


    }

}
