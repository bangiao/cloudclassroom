package com.dingxin.web.service;
import com.dingxin.pojo.po.ClassEvaluate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 课程评价表 服务接口
 */
public interface IClassEvaluateService extends IService<ClassEvaluate> {
    /**
     * 修改点赞数
     * @param upOrDown
     * @param id
     * @return
     */
    boolean updateUp(Boolean upOrDown, Integer id);
}
