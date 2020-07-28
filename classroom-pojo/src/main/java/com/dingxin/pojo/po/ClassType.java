package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  实体类
 */
@TableName("ccr_class_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassType extends Model<ClassType> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 类别名称
     */
    @NotNull(message = "typeName must not be null")
    @ApiModelProperty(value = "类别名称")
    private String typeName;
    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    @NotNull(message = "dataName must not be null")
    private String dataName;
    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Integer createPersonId;
    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createPersonName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 是否有效
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}