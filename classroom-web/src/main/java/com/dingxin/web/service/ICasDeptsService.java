package com.dingxin.web.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.privilege.CasDepts;

import java.util.List;

/**
 *  服务接口
 */
public interface ICasDeptsService extends IService<CasDepts> {

    List<CasDepts> like(CasDepts data);

}
