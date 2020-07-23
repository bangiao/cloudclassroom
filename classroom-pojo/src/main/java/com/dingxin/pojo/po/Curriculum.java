package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
     * 课程名
     */
    private String curriculumName;
    /**
     * 课程类型
     */
    private String curriculumType;
    /**
     * 课程介绍
     */
    private String curriculumDesc;
    /**
     * 所属专题
     */
    private Integer topicId;
    /**
     * 讲师
     */
    private Integer teacherId;
    /**
     * 是否添加视频
     */
    private Integer addVideoFlag;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频时长
     */
    private String videoDuration;
    /**
     * 视频附件
     */
    private String videoAttachment;
    /**
     * 直播视频
     */
    private String liveVideo;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}