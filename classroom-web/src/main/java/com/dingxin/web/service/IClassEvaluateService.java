package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
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

    /**
     * 查询数据列表
     * @param query
     * @return
     */
    IPage queryPage(BaseQuery<ClassEvaluate> query);
}
