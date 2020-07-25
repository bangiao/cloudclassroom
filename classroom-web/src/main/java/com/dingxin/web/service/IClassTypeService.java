package com.dingxin.web.service;
import com.dingxin.pojo.po.ClassType;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IClassTypeService extends IService<ClassType> {

    List<ClassType> like(ClassType data);

}
