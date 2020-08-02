package com.dingxin.web.service;
import com.dingxin.pojo.po.CurriculumIntermediate;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface ICurriculumIntermediateService extends IService<CurriculumIntermediate> {

    List<CurriculumIntermediate> like(CurriculumIntermediate data);

}
