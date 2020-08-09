package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  首页banner实体类
 */
@TableName("ccr_banner_manage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerManage extends Model<BannerManage> {

    private static final long serialVersionUID=1L;


    private Integer id;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer sortId;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private String fileName;
    /**
     * 附件流
     */
    @ApiModelProperty(value = "附件流")
    private String fileUrl;
    /**
     * 使用途径
     */
    @ApiModelProperty(value = "使用途径")
    private Integer useType;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createPerson;
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
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer delFlag;
    /**
     * 是否有效
     */
    @ApiModelProperty(value = "是否有效(0启用1禁用)")
    private Integer disable;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}