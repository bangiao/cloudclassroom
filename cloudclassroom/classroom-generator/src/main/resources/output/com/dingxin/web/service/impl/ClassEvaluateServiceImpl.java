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

}
