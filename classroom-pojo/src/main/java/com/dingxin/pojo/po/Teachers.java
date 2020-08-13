package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("sys_teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teachers extends Model<Teachers> {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private String zgh;
    /**
     * 是否禁用 0：启用 -1：禁用
     */
    @ApiModelProperty(value = "是否禁用 0：启用 -1：禁用")
    private Integer enable;
    /**
     * 个人介绍
     */
    @ApiModelProperty(value = "个人介绍")
    private String introduction;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String xm;
    @Override

    protected Serializable pkVal() {
        return this.zgh;
    }

}