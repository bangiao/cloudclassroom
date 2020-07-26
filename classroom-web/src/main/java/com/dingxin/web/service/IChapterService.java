package com.dingxin.web.service;
import com.dingxin.pojo.po.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *  服务接口
 */
public interface IChapterService extends IService<Chapter> {

    List<Chapter> like(Chapter data);

}
