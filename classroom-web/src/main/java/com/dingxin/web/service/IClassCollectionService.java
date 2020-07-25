package com.dingxin.web.service;
import com.dingxin.pojo.po.ClassCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 课程收藏表 服务接口
 */
public interface IClassCollectionService extends IService<ClassCollection> {

    List<ClassCollection> like(ClassCollection data);

}
