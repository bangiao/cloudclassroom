package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("nkd_cas_employs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CasEmploys extends Model<CasEmploys> {

    private static final long serialVersionUID=1L;

    @TableField("SID")
    @ApiModelProperty(value = "")
    private String sid;
    @TableField("CASID")
    @ApiModelProperty(value = "")
    private String casid;
    @TableField("DISABLE")
    @ApiModelProperty(value = "")
    private String disable;
    @TableField("DEPTS")
    @ApiModelProperty(value = "")
    private String depts;
    @TableField("Group")
    @ApiModelProperty(value = "")
    private String Group;
    @TableField("NAME")
    @ApiModelProperty(value = "")
    private String name;
    private String email;
    private String phonenum;
    /**
     * 职位
     */
    private String zw;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}