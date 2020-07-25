package com.dingxin.web.service;
import com.dingxin.pojo.po.VideoAudit;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IVideoAuditService extends IService<VideoAudit> {

    List<VideoAudit> like(VideoAudit data);

}
