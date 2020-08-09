package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.CurriculumAuditListRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface IVideoAuditService extends IService<Video> {

    List<Video> like(Video data);

    IPage queryPageList(CurriculumAuditListRequest query);
}
