package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("ccr_viewpager")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Viewpager extends Model<Viewpager> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 图片名称
     */
    @ApiModelProperty(value = "图片名称")
    private String picName;
    /**
     * 图片类型
     */
    @ApiModelProperty(value = "图片类型")
    private String picType;
    /**
     * 图片大小
     */
    @ApiModelProperty(value = "图片大小")

    private Long picSize;
    /**
     * 图片存放地址
     */
    @ApiModelProperty(value = "图片存放地址")
    private String picUrl;
    /**
     * 创建时间
     */
    @TableField("createTime")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField("modifyTime")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;
    /**
     * 时候删除
     */
    @TableField("del_Flag")
    @ApiModelProperty(value = "是否删除")
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}