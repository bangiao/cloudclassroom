package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("nkd_cas_depts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CasDepts extends Model<CasDepts> {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 主数据单位ID
     */
    @TableField("ZSJDWID")
    @ApiModelProperty(value = "主数据单位ID")
    private String zsjdwid;
    /**
     * 主数据名称
     */
    @TableField("ZSJMC")
    @ApiModelProperty(value = "主数据名称")
    private String zsjmc;
    /**
     * CAS单位ID
     */
    @TableField("CASDWID")
    @ApiModelProperty(value = "CAS单位ID")
    private String casdwid;
    /**
     * CAS单位名称
     */
    @TableField("CASDWMC")
    @ApiModelProperty(value = "CAS单位名称")
    private String casdwmc;
    /**
     * CAS父级ID
     */
    @TableField("CASFJDW")
    @ApiModelProperty(value = "CAS父级ID")
    private String casfjdw;
    /**
     * 是否禁用
     */
    @TableField("DISABLE")
    @ApiModelProperty(value = "是否禁用")
    private Integer disable;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}