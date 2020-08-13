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
@TableName("ccr_project_curriculum")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCurriculum extends Model<ProjectCurriculum> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @ApiModelProperty(value = "专题id")
    private Integer id;
    @ApiModelProperty(value = "专题id")
    private Integer projectId;
    @ApiModelProperty(value = "课程id")
    private Integer curriculumId;
    @ApiModelProperty(value = "是否删除")
    private Integer delFlag;
    @ApiModelProperty(value = "课程观看次数")
    private Long watchAmount;
    @ApiModelProperty(value = "是否禁用")
    private Integer enable;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}