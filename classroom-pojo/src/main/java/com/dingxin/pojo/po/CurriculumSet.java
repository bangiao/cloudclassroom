package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *  实体类
 */
@TableName("ccr_curriculum_set")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurriculumSet extends Model<CurriculumSet> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 课程表名称
     */
    @ApiModelProperty(value = "课程表名称")
    @NotBlank(message = "curriculumName can not be null")
    private String curriculumName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @NotBlank(message = "remark can not be null")
    private String remark;
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
     * 是否删除 0：未删除 1 ：删除
     */
    @ApiModelProperty(value = "是否删除 0：未删除 1 ：删除")
    private Boolean delFlag;

    /**
     * 课程id集合
     */
    @TableField(exist = false)
    private List<Curriculum> curriculums;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}