package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.dao.mapper.ClassCollectionMapper;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.request.ClassCollectionListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IClassCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.web.shiro.ShiroUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
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
    /**
     * 查询收场课程列表
     * @param query
     * @return
     */
    @Override
    public IPage queryList(ClassCollectionListRequest query) {

//        获取登录人Id
        LambdaQueryWrapper<ClassCollection> qw = Wrappers.lambdaQuery();
        /*String userId = ShiroUtils.getUserId();
        qw.eq(ClassCollection::getPersonId,userId);*/
        qw.eq(ClassCollection::getDelFlag,CommonConstant.DEL_FLAG);
        if (StringUtils.isNotEmpty("userId")&&StringUtils.isNotEmpty(query.getQueryStr())) {
            qw.like(ClassCollection::getClassName, query.getQueryStr())
                    .or().like( ClassCollection::getTeacherName, query.getQueryStr())
                    .or().like(ClassCollection::getClassTypeStr, query.getQueryStr())
                    .orderByDesc(ClassCollection::getCreateTime);
        }
        Page<ClassCollection> page = new Page(query.getCurrentPage(),query.getPageSize());
        return page(page,qw);
    }
    /**
     * 新增课程收藏
     * @param classCollection
     * @return
     */
    @Override
    public boolean insert(ClassCollection classCollection) {
        boolean falg=false;
     /*   String userId = ShiroUtils.getUserId();
        if (StringUtils.isNotEmpty(userId)){
            classCollection.setPersonId(Integer.parseInt(userId));

        }*/
        falg = saveOrUpdate(classCollection);
        return falg;
    }
    /**
     * 删除课程收藏
     * @param id 课程收藏id
     * @return
     */
    @Override
    public boolean deleteById(IdRequest id) {
        LambdaUpdateWrapper<ClassCollection> qw = Wrappers.lambdaUpdate();
        qw.set(ClassCollection::getDelFlag, CommonConstant.NOT_DEL_FLAG).eq(!Objects.isNull(id.getId()),ClassCollection::getId,id.getId());
        return update(qw);
    }

    /**
     * 批量删除
     * @param list
     * @return
     */
    @Override
    public boolean deleteByBatch(List<Integer> list) {
        boolean retFlag=false;
        LambdaUpdateWrapper<ClassCollection> qw = Wrappers.lambdaUpdate();
        if (!Objects.isNull(list)&&list.size()>0) {
            qw.set(ClassCollection::getDelFlag,CommonConstant.NOT_DEL_FLAG).in(ClassCollection::getId,list);
            retFlag = update(qw);
        }
        return retFlag;
    }

}
