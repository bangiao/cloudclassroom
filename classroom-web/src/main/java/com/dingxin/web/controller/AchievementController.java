package com.dingxin.web.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dingxin.common.annotation.UserTag;
import com.dingxin.pojo.basic.BaseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.po.Achievement;
import com.dingxin.web.service.IAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import com.dingxin.pojo.basic.BaseResult;

import java.util.List;

/**
 * 成就
 */
@RestController
@RequestMapping("/achievement")
@Api(tags = "成就接口")
@UserTag
public class AchievementController {
    @Autowired
    private IAchievementService achievementService;
    @PostMapping("/search")
    @ApiOperation(value = "根据当前用户ID获取当前用户的成就")
    public BaseResult<List<Achievement>> search(){
        return achievementService.search();
    }
}