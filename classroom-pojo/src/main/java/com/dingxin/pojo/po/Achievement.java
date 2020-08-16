package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 成就 实体类
 */
@TableName("ccr_achievement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Achievement extends Model<Achievement> {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 成就名称
     */
    private String name;
    /**
     * 成就描述
     */
    @ApiModelProperty(value = "成就描述")
    private String descs;

    @ApiModelProperty(value = "是否点亮")
    @TableField(exist = false)
    private boolean checked = false;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}