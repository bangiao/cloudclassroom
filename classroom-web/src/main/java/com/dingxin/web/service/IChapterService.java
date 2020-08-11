package com.dingxin.web.service;
import com.dingxin.pojo.po.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.vo.ChapterAndVideoInfo;

import java.util.List;

/**
 *  服务接口
 */
public interface IChapterService extends IService<Chapter> {

    List<Chapter> like(Chapter data);
    /**
     * 查询出课程对应的章节及其下面的视频
     */
    List<ChapterAndVideoInfo> loadChapterAndVideoInfo(Integer curriculumId);

}
