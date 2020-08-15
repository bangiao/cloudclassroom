package com.dingxin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.dao.mapper.ClassTypeMapper;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.pojo.request.CommQueryListRequest;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.web.service.IClassTypeService;
import com.dingxin.web.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 服务接口实现类
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
                        data.getDelFlag());
        return classTypeMapper.selectList(query);


    }

    /**
     * 查询列表
     *
     * @param query
     * @return
     */
    @Override
    public IPage<ClassType> queryPage(CommQueryListRequest query) {
        Page<ClassType> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        LambdaQueryWrapper<ClassType> qw = Wrappers.lambdaQuery();
        qw.eq(ClassType::getDelFlag, CommonConstant.DEL_FLAG);
        if (StringUtils.isNotEmpty(query.getQueryStr())) {
            qw.like(ClassType::getTypeName, query.getQueryStr())
                    .or()
                    .like(ClassType::getDataName, query.getQueryStr())
                    .or()
                    .like(ClassType::getCreatePersonName, query.getQueryStr());
        }
        return page(page, qw);

    }

    /**
     * 获取课程类型下拉框数据
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> listMapself() {
        LambdaQueryWrapper<ClassType> qw = Wrappers.lambdaQuery();
        qw.eq(ClassType::getDelFlag, CommonConstant.DEL_FLAG).select(ClassType::getId, ClassType::getTypeName);
        return listMaps(qw);
    }

    /**
     * 获取单个课程类型
     *
     * @param idRequest
     * @return
     */
    @Override
    public ClassType getOneSelf(IdRequest idRequest) {
        LambdaQueryWrapper<ClassType> qw = Wrappers.lambdaQuery();
        qw.eq(ClassType::getId, idRequest.getId()).eq(ClassType::getDelFlag, CommonConstant.DEL_FLAG);
        return getOne(qw);
    }

    /**
     * 新增 新增成功返回0 重复返回1 失败返回2
     *
     * @param convent
     * @return
     */
    @Override
    public boolean saveSelf(ClassType convent) {
        LambdaQueryWrapper<ClassType> qw = Wrappers.lambdaQuery();
        qw.eq(ClassType::getTypeName, convent.getTypeName())
                .eq(ClassType::getDelFlag, CommonConstant.DEL_FLAG)
                .eq(ClassType::getDataName,convent.getDataName());
        int count = count(qw);
        if (count >= 1) {
            throw new BusinessException(ExceptionEnum.DUPLICATE_DATA);
        }
        convent.setModifyTime(LocalDateTime.now());
        convent.setCreatePersonId(ShiroUtils.getUserId());
        convent.setCreatePersonName(ShiroUtils.getUserName());
        return saveOrUpdate(convent);

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(IdRequest id) {
        LambdaUpdateWrapper<ClassType> qw = Wrappers.lambdaUpdate();
        qw.set(ClassType::getDelFlag, CommonConstant.DEL_FLAG_TRUE).eq(!Objects.isNull(id.getId()), ClassType::getId, id.getId());
        return update(qw);
    }

    /**
     * 批量删除课程评价
     *
     * @param list
     * @return
     */
    @Override
    public boolean deleteBatch(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionEnum.PARAMTER_ERROR);
        }
        LambdaUpdateWrapper<ClassType> qw = Wrappers.lambdaUpdate();
        qw.set(ClassType::getDelFlag, CommonConstant.DEL_FLAG_TRUE).in(ClassType::getId, list);
        return update(qw);
    }

}
