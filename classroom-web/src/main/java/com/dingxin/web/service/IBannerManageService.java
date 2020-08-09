package com.dingxin.web.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.po.BannerManage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.BannerManage;
import com.dingxin.pojo.request.BannerRequest;
import com.dingxin.pojo.request.IdRequest;

import java.util.List;

/**
 *  首页banner服务接口
 */
public interface IBannerManageService extends IService<BannerManage> {

    List<BannerManage> like(BannerManage data);

    IPage queryPageList(BaseQuery<BannerManage> query);

    boolean deleteBannerManage(IdRequest id);

    boolean enableStatus(BannerRequest bannerRequest);

    boolean updateBannerManage(BannerManage bannerManage);
}
