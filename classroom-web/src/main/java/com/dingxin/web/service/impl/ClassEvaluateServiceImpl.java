package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.dao.mapper.ClassEvaluateMapper;
import com.dingxin.pojo.vo.ThumbsUpVo;
import com.dingxin.web.service.IClassEvaluateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * 课程评价表 服务接口实现类
 */
@Service
@Transactional
public class ClassEvaluateServiceImpl extends ServiceImpl<ClassEvaluateMapper, ClassEvaluate> implements IClassEvaluateService {
    @Autowired
    private ClassEvaluateMapper classEvaluateMapper;
    @Override
    public List<ClassEvaluate> like(ClassEvaluate data) {
        LambdaQueryWrapper<ClassEvaluate> query = Wrappers.<ClassEvaluate>lambdaQuery()
                .like(
                        Objects.nonNull(data.getId()),
                        ClassEvaluate::getId,
                        data.getId())
                .like(
                        Objects.nonNull(data.getClassId()),
                        ClassEvaluate::getClassId,
                        data.getClassId())
                .like(
                        Objects.nonNull(data.getClassName()),
                        ClassEvaluate::getClassName,
                        data.getClassName())
                .like(
                        Objects.nonNull(data.getTeacherId()),
                        ClassEvaluate::getTeacherId,
                        data.getTeacherId())
                .like(
                        Objects.nonNull(data.getTeacherName()),
                        ClassEvaluate::getTeacherName,
                        data.getTeacherName())
                .like(
                        Objects.nonNull(data.getStudyLength()),
                        ClassEvaluate::getStudyLength,
                        data.getStudyLength())
                .like(
                        Objects.nonNull(data.getStudentId()),
                        ClassEvaluate::getStudentId,
                        data.getStudentId())
                .like(
                        Objects.nonNull(data.getStudentName()),
                        ClassEvaluate::getStudentName,
                        data.getStudentName())
                .like(
                        Objects.nonNull(data.getStudentCode()),
                        ClassEvaluate::getStudentCode,
                        data.getStudentCode())
                .like(
                        Objects.nonNull(data.getEvaluateTime()),
                        ClassEvaluate::getEvaluateTime,
                        data.getEvaluateTime())
                .like(
                        Objects.nonNull(data.getEvaluateContent()),
                        ClassEvaluate::getEvaluateContent,
                        data.getEvaluateContent())
                .like(
                        Objects.nonNull(data.getEvaluateScore()),
                        ClassEvaluate::getEvaluateScore,
                        data.getEvaluateScore())
                .like(
                        Objects.nonNull(data.getEvaluateCount()),
                        ClassEvaluate::getEvaluateCount,
                        data.getEvaluateCount())
                .like(
                        Objects.nonNull(data.getCreateTime()),
                        ClassEvaluate::getCreateTime,
                        data.getCreateTime())
                .like(
                        Objects.nonNull(data.getModifyTime()),
                        ClassEvaluate::getModifyTime,
                        data.getModifyTime())
                .like(
                        Objects.nonNull(data.getStatus()),
                        ClassEvaluate::getStatus,
                        data.getStatus())
                .like(
                        Objects.nonNull(data.getDelFlag()),
                        ClassEvaluate::getDelFlag,
                        data.getDelFlag())
                ;
        return classEvaluateMapper.selectList(query);


    }

    /**
     * 修改点赞数
     * @return
     */
    @Override
    public boolean updateUp(ThumbsUpVo thumbsUpVo) {
        ClassEvaluate classEvaluate = getById(thumbsUpVo.getId());
        if (thumbsUpVo.getUpOrDown())classEvaluate.setEvaluateCount(classEvaluate.getEvaluateCount()+1);
        else classEvaluate.setEvaluateCount(classEvaluate.getEvaluateCount()>0?classEvaluate.getEvaluateCount()-1:0);
        return updateById(classEvaluate);
    }

    /**
     * 查询数据列表根据不同的条件
     * @param query
     * @return
     */
    @Override
    public IPage queryPage(BaseQuery<ClassEvaluate> query) {
        //查询列表数据
        Page<ClassEvaluate> page = new Page(query.getCurrentPage(),query.getPageSize());
        QueryWrapper<ClassEvaluate> qw = new QueryWrapper<>();
        ClassEvaluate data = query.getData();
        if (null!=data) {
            String queryStr = data.getQueryStr();
            if (StringUtils.isNoneBlank(queryStr))qw.like("student_name", queryStr).or().like("student_code", queryStr).or().like("class_name", queryStr);
        }
//        伪代码
        int type=1;
//        管理员
        if (1==type){
            qw.eq("status",1);
        }
//        老师查看老师的评价且已经审核通过的
        else if (2==type){
            qw.eq("teacher_id",data.getTeacherId()).eq("status",1).eq("class_id",data.getClassId());
        }
//        学生查看课程相关的 评价不管有没有
        else if (3==type){
            qw.eq("class_id",0).and((a)-> {
                    QueryWrapper<ClassEvaluate> q = Wrappers.query();
                    return  q.eq("status",1).or(true,(b)->{
                        QueryWrapper<ClassEvaluate> qe = Wrappers.query();
                        return qe.eq("student_id",5).eq("status",0);
                    });
                }
            );
        }else {
            return new Page();
        }
        IPage pageList = page(page, qw.eq("del_flag",0).orderByDesc("modify_time"));
        pageList.setTotal(pageList.getRecords().size());
        return pageList;
    }
}
