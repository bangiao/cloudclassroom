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
 *  实体类
 */
@TableName("ccr_curriculum")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Boolean disableFlag;
    /**
     * 所属院系
     */
    private Integer departmentId;
    /**
     * 所属专题
     */
    private Integer topicId;
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
    private Integer watchAmount;
    /**
     * 删除标志(目前采用假删除的方式)
     */
    private Integer deleteFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}