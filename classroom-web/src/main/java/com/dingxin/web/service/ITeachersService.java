package com.dingxin.web.service;
import com.dingxin.pojo.po.Teachers;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface ITeachersService extends IService<Teachers> {

    List<Teachers> like(Teachers data);

}
