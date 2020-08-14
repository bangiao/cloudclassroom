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
    /**
     * 删除父章节及其对应的子章节
     */
    void removeChapterRecursively(List<Integer> parentChapterIds);
    /**
     * 只删除子章节
     */
    void onlyRemoveChildChapterSelf(List<Integer> childChapterIds);
    /**
     * 根据父章节获取其对应的子章节id
     */
    List<Integer> loadChildrenIdByParentIds(List<Integer> childChapterIds);

}
