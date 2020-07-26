package com.dingxin.web.service;
import com.dingxin.pojo.po.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IVideoService extends IService<Video> {

    List<Video> like(Video data);

}
