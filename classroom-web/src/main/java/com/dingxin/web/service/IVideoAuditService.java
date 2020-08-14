package com.dingxin.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.Video;
import com.dingxin.pojo.request.CurriculumAuditListRequest;

import java.util.List;

/**
 *  服务接口
 */
public interface IVideoAuditService extends IService<Video> {

    List<Video> like(Video data);

    IPage queryPageList(CurriculumAuditListRequest query);

    BaseResult<List<Chapter>> searchchapterbycurrid(CurriculumAuditListRequest query);

    BaseResult<Page<ClassEvaluate>> searchevaluatebycurrid(CurriculumAuditListRequest query);
}
