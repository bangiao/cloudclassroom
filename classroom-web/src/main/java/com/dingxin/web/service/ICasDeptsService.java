package com.dingxin.web.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseQuery;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.BannerManage;
import com.dingxin.pojo.po.CasDepts;

import java.util.List;

/**
 *  服务接口
 */
public interface ICasDeptsService extends IService<CasDepts> {

    List<CasDepts> like(CasDepts data);

    BaseResult<Page<CasDepts>> queryPageList();
}
