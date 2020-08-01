package com.dingxin.web.service;
import com.dingxin.pojo.po.CasEmploys;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface ICasEmploysService extends IService<CasEmploys> {

    List<CasEmploys> like(CasEmploys data);

}
