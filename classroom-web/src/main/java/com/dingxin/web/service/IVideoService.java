package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.request.VideoListRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface IVideoService extends IService<Video> {

    List<Video> like(Video data);

    IPage<Video> listQuery(BaseQuery<VideoListRequest> query);

}
