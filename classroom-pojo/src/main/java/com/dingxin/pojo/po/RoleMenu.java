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
 * 角色与菜单对应关系 实体类
 */
@TableName("sys_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 菜单ID
     */
    private Integer menuId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public static RoleMenu insertValue(int var,int var2){
        return RoleMenu.builder().roleId(var).menuId(var2).build();
    }

}