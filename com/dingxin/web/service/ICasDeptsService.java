package com.dingxin.web.service;
import com.dingxin.pojo.po.CasDepts;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface ICasDeptsService extends IService<CasDepts> {

    List<CasDepts> like(CasDepts data);

}
