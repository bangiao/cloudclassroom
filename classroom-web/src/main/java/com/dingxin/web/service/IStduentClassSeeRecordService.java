package com.dingxin.web.service;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 学生记录表 服务接口
 */
public interface IStduentClassSeeRecordService extends IService<StduentClassSeeRecord> {
    List<StduentClassSeeRecord> like(StduentClassSeeRecord data);
}
