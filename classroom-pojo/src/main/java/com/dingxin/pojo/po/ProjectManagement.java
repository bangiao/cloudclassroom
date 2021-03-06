package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  实体类
 */
@TableName("ccr_project_management")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ProjectManagement extends Model<ProjectManagement> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 专题名称
     */
    @ApiModelProperty(value = "专题名称")
    @NotBlank(message = "projectName can not be null")
    private String projectName;
    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师关联id")
    @NotNull(message = "lecturerId can not be null")
    private String lecturerId;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师姓名")
    private String lecturerName;
    /**
     * 专题描述
     */
    @ApiModelProperty(value = "专题描述")
    @NotBlank(message = "projectDescription can not be null")
    private String projectDescription;
    /**
     * 课程数量统计
     */
    @ApiModelProperty(value = "课程数量统计")
    private Integer courseNum;
    /**
     * 观看次数
     */
    @ApiModelProperty(value = "观看次数")
    private Long watchNum;
    /**
     * 是否禁用 0：启用 -1：禁用
     */
    @ApiModelProperty(value = "是否禁用 0：启用 -1：禁用")
    private Integer enable;
    /**
     * 审核状态 0：待审核 1：审核通过 -1：审核未通过
     */
    @ApiModelProperty(value = "审核状态 0：待审核 1：审核通过 -1：审核未通过")
    private Boolean auditStatus;
    /**
     * 是否删除 0：未删除 -1 ：删除
     */
    @ApiModelProperty(value = "是否删除 0：未删除 -1 ：删除")
    private Boolean delFlag;

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

    @ApiModelProperty("专题图片URL")
    @TableField(value = "pic_url")
    private String picUrl;

    @ApiModelProperty("部门ID")
    @NotNull(message = "院系ID不能为null")
    private String deptId;

    @ApiModelProperty("专业ID")
    @NotNull(message = "专业ID不能为null")
    private String majorId;

    @ApiModelProperty("专业名称")
    private String majorName;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("专题观看次数")
    private String projectWatchNum;


    @TableField(exist = false)
    private List<Integer> courseIds;

    @TableField(exist = false)
    private List<Curriculum> curriculumList;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}