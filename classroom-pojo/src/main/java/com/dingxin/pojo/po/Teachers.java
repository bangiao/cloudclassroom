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
    @TableId(type = IdType.AUTO)
    private String jg0101id;
    /**
     * 教职工号
     */
    @TableField("JGH")
    @ApiModelProperty(value = "教职工号")
    private String jgh;
    /**
     * 姓名
     */
    @TableField("XM")
    @ApiModelProperty(value = "姓名")
    private String xm;
    /**
     * 姓名拼音
     */
    @TableField("XMPY")
    @ApiModelProperty(value = "姓名拼音")
    private String xmpy;
    /**
     * 曾用名
     */
    @TableField("CYM")
    @ApiModelProperty(value = "曾用名")
    private String cym;
    /**
     * 性别
     */
    @TableField("XBM")
    @ApiModelProperty(value = "性别")
    private String xbm;
    /**
     * 出生日期
     */
    @TableField("CSRQ")
    @ApiModelProperty(value = "出生日期")
    private String csrq;
    /**
     * 出生地
     */
    @TableField("CSDM")
    @ApiModelProperty(value = "出生地")
    private String csdm;
    /**
     * 籍贯
     */
    @TableField("JG")
    @ApiModelProperty(value = "籍贯")
    private String jg;
    /**
     * 国籍
     */
    @TableField("GJDQM")
    @ApiModelProperty(value = "国籍")
    private String gjdqm;
    /**
     * 民族
     */
    @TableField("MZM")
    @ApiModelProperty(value = "民族")
    private String mzm;
    /**
     * 证件类型
     */
    @TableField("SFZJLXM")
    @ApiModelProperty(value = "证件类型")
    private String sfzjlxm;
    /**
     * 证件号码
     */
    @TableField("SFZJH")
    @ApiModelProperty(value = "证件号码")
    private String sfzjh;
    /**
     * 证件有效期
     */
    @TableField("SFZJYXQ")
    @ApiModelProperty(value = "证件有效期")
    private String sfzjyxq;
    /**
     * 编制类别
     */
    @TableField("BZLBM")
    @ApiModelProperty(value = "编制类别")
    private String bzlbm;
    /**
     * 教职工类别
     */
    @TableField("JZGLBM")
    @ApiModelProperty(value = "教职工类别")
    private String jzglbm;
    /**
     * 当前状态
     */
    @TableField("DQZTM")
    @ApiModelProperty(value = "当前状态")
    private String dqztm;
    /**
     * 文化程度
     */
    @TableField("WHCDM")
    @ApiModelProperty(value = "文化程度")
    private String whcdm;
    /**
     * 参加工作时间
     */
    @TableField("CJGZNY")
    @ApiModelProperty(value = "参加工作时间")
    private String cjgzny;
    /**
     * 来校日期
     */
    @TableField("LXRQ")
    @ApiModelProperty(value = "来校日期")
    private String lxrq;
    /**
     * 从教时间
     */
    @TableField("CJNY")
    @ApiModelProperty(value = "从教时间")
    private String cjny;
    /**
     * 单位
     */
    @TableField("DWH")
    @ApiModelProperty(value = "单位")
    private String dwh;
    /**
     * 是否禁用 0：启用 -1：禁用
     */
    @TableField("enable")
    @ApiModelProperty(value = "是否禁用")
    private Boolean enable;
    @Override
    protected Serializable pkVal() {
        return this.jg0101id;
    }

}