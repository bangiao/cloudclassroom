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

/**
 * 专业管理 实体类
 */
@TableName("ccr_major")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major extends Model<Major> {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 专业代码
     */
    @ApiModelProperty(value = "专业代码")
    private String majorCode;
    /**
     * 专业名称
     */
    @ApiModelProperty(value = "专业名称")
    private String majorName;
    /**
     * 院系代码
     */
    @ApiModelProperty(value = "院系代码")
    private String collegeCode;
    /**
     * 院系名称
     */
    @ApiModelProperty(value = "院系名称")
    private String collegeName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}