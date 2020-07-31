package com.dingxin.web.service;
import com.dingxin.pojo.po.Viewpager;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IViewpagerService extends IService<Viewpager> {

    List<Viewpager> like(Viewpager data);

}
