package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  课程管理实体类
 */
@TableName("ccr_curriculum")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Curriculum extends Model<Curriculum> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 课程名称
     */
    private String curriculumName;
    /**
     * 课程类型
     */
    private String curriculumType;
    /**
     * 课程类型主键
     */
    private Integer classTypeId;
    /**
     * 课程介绍
     */
    private String curriculumDesc;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频时长(时长为该课程下所有视频的累计时长)
     */
    private Long videoDuration;
    /**
     * 视频附件
     */
    private String videoAttachment;
    /**
     * 直播视频
     */
    private String liveVideo;
    /**
     * 是否添加视频
     */
    private Integer addVideoFlag;
    /**
     * 是否禁用(是否有效)
     */
    private Integer disableFlag;
    /**
     * 所属院系
     */
    private String departmentId;
    /**
     * 所属专题
     */
    private Integer topicId;
    /**
     * 专题名
     */
    private String topicName;
    /**
     * 讲师
     */
    private String teacherName;
    /**
     * 讲师id
     */
    private String teacherId;
    /**
     * 观看次数(课程下的观看次数为该课程下所有视频的观看次数)
     */
    private Long watchAmount;
    /**
     * 删除标志(目前采用假删除的方式)
     */
    private Integer deleteFlag;
    /**
     * 课程审核状态(-1 未通过，0未审核，1审核通过)
     */
    private Integer auditFlag;
    /**
     * 课程评价审核状态 (-1 未通过，0未审核，1审核通过)
     */
    private Integer evaluateStatus;
    /**
     * 课程图片
     */
    private String curriculumImage;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 最近一次修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;


    /**
     * 上课时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime classTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}