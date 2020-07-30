package com.dingxin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Video;

import java.util.List;

/**
 *  服务接口
 */
public interface IVideoAuditService extends IService<Video> {

    List<Video> like(Video data);

}
