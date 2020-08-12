package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  实体类
 */
@TableName("sys_teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teachers extends Model<Teachers> {

    private static final long serialVersionUID=1L;

    /**
     * 教工ID
     */
    @TableId
    private String zgh;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String xm;

    @ApiModelProperty(value = "个人介绍")
    private String introduction;

    @ApiModelProperty(value = "是否禁用 0：启用 -1：禁用")
    private Integer enable;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;
    @Override
    protected Serializable pkVal() {
        return this.zgh;
    }

}