package com.dingxin.web.web.service;
import com.dingxin.pojo.po.ClassEvaluate;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 课程评价表 服务接口
 */
public interface IClassEvaluateService extends IService<ClassEvaluate> {

    List<ClassEvaluate> like(ClassEvaluate data);

}
