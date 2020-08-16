package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 成就用户中间表 实体类
 */
@TableName("ccr_achievement_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchievementUser extends Model<AchievementUser> {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 成就ID
     */
    private Integer achievementId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}