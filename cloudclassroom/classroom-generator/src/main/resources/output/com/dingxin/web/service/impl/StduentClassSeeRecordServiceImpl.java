package com.dingxin.web.service.impl;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.dao.mapper.StduentClassSeeRecordMapper;
import com.dingxin.web.service.IStduentClassSeeRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生记录表 服务接口实现类
 */
@Service
@Transactional
public class StduentClassSeeRecordServiceImpl extends ServiceImpl<StduentClassSeeRecordMapper, StduentClassSeeRecord> implements IStduentClassSeeRecordService {

}
