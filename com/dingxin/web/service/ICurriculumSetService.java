package com.dingxin.web.service;
import com.dingxin.pojo.po.CurriculumSet;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface ICurriculumSetService extends IService<CurriculumSet> {

    List<CurriculumSet> like(CurriculumSet data);

}
