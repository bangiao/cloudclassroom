package com.dingxin.web.service;
import com.dingxin.pojo.po.ClassEvaluate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.OperationLog;
import com.dingxin.pojo.vo.ThumbsUpVo;

import java.util.List;

/**
 * 课程评价表 服务接口
 */
public interface IClassEvaluateService extends IService<ClassEvaluate> {

    List<ClassEvaluate> like(ClassEvaluate data);

    /**
     * 修改点赞数
     * @return
     */
    boolean updateUp(ThumbsUpVo thumbsUpVo);
}
