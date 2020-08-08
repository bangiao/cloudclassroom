package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Major;
import com.dingxin.pojo.request.StudentStudyStudentListRequest;

import java.util.List;

/**
 * 专业管理 服务接口
 */
public interface IMajorService extends IService<Major> {

    List<Major> like(Major data);

    IPage queryPageList(StudentStudyStudentListRequest query);
}
