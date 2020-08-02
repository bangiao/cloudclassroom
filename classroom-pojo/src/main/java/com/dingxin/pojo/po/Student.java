package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 学生信息表 实体类
 */
@TableName("sys_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Model<Student> {

    private static final long serialVersionUID=1L;

    @TableId
    private Integer id;
    /**
     * 学生编号	
     */
    @TableField("XSBH")
    @ApiModelProperty(value = "学生编号	")
    private String xsbh;
    /**
     * 学号	
     */
    @TableField("XH")
    @ApiModelProperty(value = "学号	")
    private String xh;
    /**
     * 姓名
     */
    @TableField("XM")
    @ApiModelProperty(value = "姓名")
    private String xm;
    /**
     * 性别
     */
    @TableField("XBMC")
    @ApiModelProperty(value = "性别")
    private String xbmc;
    /**
     * 学生类别代码
     */
    @TableField("XSLBDM")
    @ApiModelProperty(value = "学生类别代码")
    private String xslbdm;
    /**
     * 学生类型名称
     */
    @TableField("XSLBMC")
    @ApiModelProperty(value = "学生类型名称")
    private String xslbmc;
    /**
     * 书院代码
     */
    @TableField("SYDM")
    @ApiModelProperty(value = "书院代码")
    private String sydm;
    /**
     * 书院名称
     */
    @TableField("SYMC")
    @ApiModelProperty(value = "书院名称")
    private String symc;
    /**
     * 院系代码
     */
    @TableField("YXDM")
    @ApiModelProperty(value = "院系代码")
    private String yxdm;
    /**
     * 院系名称
     */
    @TableField("YXMC")
    @ApiModelProperty(value = "院系名称")
    private String yxmc;
    /**
     * 专业大类代码
     */
    @TableField("ZYDLDM")
    @ApiModelProperty(value = "专业大类代码")
    private String zydldm;
    /**
     * 专业大类名称
     */
    @TableField("ZYDLMC")
    @ApiModelProperty(value = "专业大类名称")
    private String zydlmc;
    /**
     * 专业代码
     */
    @TableField("ZYDM")
    @ApiModelProperty(value = "专业代码")
    private String zydm;
    /**
     * 专业名称
     */
    @TableField("ZYMC")
    @ApiModelProperty(value = "专业名称")
    private String zymc;
    /**
     * 班级代码
     */
    @TableField("BJDM")
    @ApiModelProperty(value = "班级代码")
    private String bjdm;
    /**
     * 班级名称
     */
    @TableField("BJMC")
    @ApiModelProperty(value = "班级名称")
    private String bjmc;
    /**
     * 辅导员职工号
     */
    @TableField("FDYZGH")
    @ApiModelProperty(value = "辅导员职工号")
    private String fdyzgh;
    /**
     * 辅导员姓名
     */
    @TableField("FDYXM")
    @ApiModelProperty(value = "辅导员姓名")
    private String fdyxm;
    /**
     * 手机号
     */
    @TableField("SJH")
    @ApiModelProperty(value = "手机号")
    private String sjh;
    /**
     * 电子信箱
     */
    @TableField("DZXX")
    @ApiModelProperty(value = "电子信箱")
    private String dzxx;
    /**
     * 联系电话
     */
    @TableField("LXDH")
    @ApiModelProperty(value = "联系电话")
    private String lxdh;
    /**
     * 预计毕业日期
     */
    @TableField("YJBYRQ")
    @ApiModelProperty(value = "预计毕业日期")
    private LocalDate yjbyrq;
    /**
     * 入学年月
     */
    @TableField("RXNY")
    @ApiModelProperty(value = "入学年月")
    private LocalDate rxny;
    /**
     * 实际毕业日期
     */
    @TableField("SJBYRQ")
    @ApiModelProperty(value = "实际毕业日期")
    private String sjbyrq;
    /**
     * 同步日期
     */
    @TableField("TBRQ")
    @ApiModelProperty(value = "同步日期")
    private LocalDateTime tbrq;
    /**
     * 同步操作类型
     */
    @TableField("TBLX")
    @ApiModelProperty(value = "同步操作类型")
    private String tblx;
    /**
     * 操作者姓名
     */
    @TableField("CZZXM")
    @ApiModelProperty(value = "操作者姓名")
    private String czzxm;
    /**
     * 现在年级
     */
    @TableField("XZNJ")
    @ApiModelProperty(value = "现在年级")
    private String xznj;
    /**
     * 导师姓名
     */
    @TableField("DSXM")
    @ApiModelProperty(value = "导师姓名")
    private String dsxm;
    /**
     * 导师职工号
     */
    @TableField("DSZGH")
    @ApiModelProperty(value = "导师职工号")
    private String dszgh;
    /**
     * 户口所在地
     */
    @TableField("HKSZD")
    @ApiModelProperty(value = "户口所在地")
    private String hkszd;
    /**
     * 照片
     */
    @TableField("ZP")
    @ApiModelProperty(value = "照片")
    private String zp;
    /**
     * 学籍状态代码
     */
    @TableField("XJZTDM")
    @ApiModelProperty(value = "学籍状态代码")
    private String xjztdm;
    /**
     * 学生类型(0本科1研究生)
     */
    @TableField("XSLX")
    @ApiModelProperty(value = "学生类型(0本科1研究生)")
    private String xslx;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}