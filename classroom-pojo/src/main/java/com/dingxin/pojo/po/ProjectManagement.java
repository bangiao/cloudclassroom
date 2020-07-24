package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("ccr_project_management")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectManagement extends Model<ProjectManagement> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 专题名称
     */
    @NotBlank(message = "专题名称不能为空")
    private String projectName;
    /**
     * 讲师
     */
    @NotBlank(message = "讲师不能为空")
    private Integer lecturerId;
    /**
     * 专题描述
     */
    @NotBlank(message = "专题描述不能为空")
    private String projectDescription;
    /**
     * 课程数量统计
     */
    private Integer courseNum;
    /**
     * 观看次数
     */
    private Integer watchNum;
    /**
     * 是否禁用 0：启用 -1：禁用
     */
    private Boolean enable;
    /**
     * 审核状态 0：待审核 1：审核通过 -1：审核未通过
     */
    private Boolean auditStatus;
    /**
     * 是否删除 0：未删除 -1 ：删除
     */
    private Boolean delFlag;
    /**
     * 课程关联id
     */
    private String courseId;
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