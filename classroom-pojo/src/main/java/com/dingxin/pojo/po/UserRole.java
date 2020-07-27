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
import java.time.LocalDateTime;

/**
 * 用户与角色对应关系 实体类
 */
@TableName("sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * CAS用户ID
     */
    private String casUserId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}