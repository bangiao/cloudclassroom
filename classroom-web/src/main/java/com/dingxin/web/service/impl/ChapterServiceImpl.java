package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.dao.mapper.ChapterMapper;
import com.dingxin.web.service.IChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 *  服务接口实现类
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {

    @Autowired
    private ChapterMapper chapterMapper;


    @Override
    public List<Chapter> like(Chapter data) {
        LambdaQueryWrapper<Chapter> query = Wrappers.<Chapter>lambdaQuery()
                .like(
                    Objects.nonNull(data.getId()),
                    Chapter::getId,
                    data.getId())
                .like(
                    Objects.nonNull(data.getChapterDesc()),
                    Chapter::getChapterDesc,
                    data.getChapterDesc())
                .like(
                    Objects.nonNull(data.getCurriculumId()),
                    Chapter::getCurriculumId,
                    data.getCurriculumId())
                .like(
                    Objects.nonNull(data.getRootChapterFlag()),
                    Chapter::getRootChapterFlag,
                    data.getRootChapterFlag())
                .like(
                    Objects.nonNull(data.getParentId()),
                    Chapter::getParentId,
                    data.getParentId())
;
        return chapterMapper.selectList(query);


    }

}
