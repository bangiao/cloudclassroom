package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("ccr_curriculum_intermediate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumIntermediate extends Model<CurriculumIntermediate> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 课程对应id
     */
    @ApiModelProperty(value = "课程管理id")
    private Integer curriculumId;
    /**
     * 上课时间
     */
    @ApiModelProperty(value = "上课时间")
    private LocalDateTime classTime;
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
     * 是否删除 0：未删除 1 ：删
     */
    @ApiModelProperty(value = "是否删除 0：未删除 1 ：删除")
    private Boolean delFlag;

    /**
     * 课程表关联id
     */
    @ApiModelProperty(value = "课程表关联id")
    private Integer curriculumSetId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}