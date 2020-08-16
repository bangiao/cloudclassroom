package com.dingxin.web.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingxin.dao.mapper.AchievementUserMapper;
import com.dingxin.pojo.po.AchievementUser;
import com.dingxin.web.service.IAchievementUserService;
import org.springframework.stereotype.Service;

/**
 * 成就用户中间表 服务接口实现类
 */
@Service
public class AchievementUserServiceImpl extends ServiceImpl<AchievementUserMapper, AchievementUser> implements IAchievementUserService {

}
