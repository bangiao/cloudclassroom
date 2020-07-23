package com.dingxin.web.service.impl;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.dao.mapper.ClassEvaluateMapper;
import com.dingxin.web.service.IClassEvaluateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程评价表 服务接口实现类
 */
@Service
@Transactional
public class ClassEvaluateServiceImpl extends ServiceImpl<ClassEvaluateMapper, ClassEvaluate> implements IClassEvaluateService {
    /**
     * 修改点赞数
     * @param upOrDown true 点赞数加1，false 点赞数减1
     * @param id    评价表id
     * @return
     */
    @Override
    public boolean updateUp(Boolean upOrDown, Integer id) {
        ClassEvaluate classEvaluate = getById(id);
        if (upOrDown)classEvaluate.setEvaluateCount(classEvaluate.getEvaluateCount()+1);
        else classEvaluate.setEvaluateCount(classEvaluate.getEvaluateCount()>0?classEvaluate.getEvaluateCount()-1:0);
        return updateById(classEvaluate);
    }
}
