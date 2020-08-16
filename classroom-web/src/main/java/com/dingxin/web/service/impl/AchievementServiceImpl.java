package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.common.utils.CollectionUtils;
import com.dingxin.dao.mapper.AchievementMapper;
import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.po.Achievement;
import com.dingxin.pojo.po.AchievementUser;
import com.dingxin.web.service.IAchievementService;
import com.dingxin.web.service.IAchievementUserService;
import com.dingxin.web.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 成就 服务接口实现类
 */
@Service
public class AchievementServiceImpl extends ServiceImpl<AchievementMapper, Achievement> implements IAchievementService {

    @Autowired
    private IAchievementUserService achievementUserService;

    /**
     * 获取当前用户的成就
     * @return
     */
    @Override
    public BaseResult<List<Achievement>> search() {
        String userId = ShiroUtils.getUserId();
        List<AchievementUser> list = achievementUserService
                .list(
                        Wrappers.<AchievementUser>lambdaQuery()
                                .eq(AchievementUser::getUserId, userId)
                );
        List<Integer> achIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            achIds = this.listByIds(
                    list.stream().map(au -> au.getAchievementId()).collect(Collectors.toList())
            )
                    .stream()
                    .map(Achievement::getId)
                    .collect(Collectors.toList());
        }
        List<Achievement> allList = this.list();
        for (Achievement achievement : allList) {
            if (achIds.contains(achievement.getId())){
                achievement.setChecked(true);
            }else {
                achievement.setChecked(false);
            }
        }
        return BaseResult.success(allList,"查询成功");
    }
}
