package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 角色 实体类
 */
@TableName("sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 角色名称
     */
    @NotNull(message = "roleName must not be null")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者ID
     */
    private Integer createUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 数据字典id
     */
    private Integer dicId;
    /**
     * 排序
     */
    private Integer sort;
    private LocalDateTime modifyTime;
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}