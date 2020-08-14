package com.dingxin.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.request.IdRequest;
import com.dingxin.pojo.vo.ChapterAndVideoInfo;
import com.dingxin.pojo.vo.ChapterSelectVo;

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
    /**
     * 根据课程id获取章节及子章节信息
     */
    List<ChapterSelectVo> loadChapterAndChildren(IdRequest curriculumId);
    /**
     * 根据课程获取所有没有添加视频的章节列表下拉(用于直播视频管理获取章节下拉，直播视频不能选择修改课程章节挂靠的是视频或者直播视频)
     */
    List<ChapterSelectVo> loadNoVideoChapterAndChildren(IdRequest curriculumId);


}
