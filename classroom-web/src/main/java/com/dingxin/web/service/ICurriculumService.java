package com.dingxin.web.service;
import com.dingxin.pojo.po.Curriculum;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface ICurriculumService extends IService<Curriculum> {

    List<Curriculum> like(Curriculum data);

}
