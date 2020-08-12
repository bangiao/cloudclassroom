package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("ccr_video_audit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoAudit extends Model<VideoAudit> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频时长
     */
    private Long videoDuration;
    /**
     * 视频附件
     */
    private String videoField;
    /**
     * 直播视频
     */
    private String liveVideoField;
    /**
     * 是否有效(讲道理默认值为有效)
     */
    private Integer disableFlag;
    /**
     * 审批意见
     */
    private String auditComments;
    /**
     * 审批状态0为未审核1为已审核-1为未通过
     */
    private Integer auditFlag;

    /**
     * 所属课程
     */
    @ApiModelProperty("所属课程")
    private Integer curriculumId;

    @TableField(exist = false)
    private String queryStr;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}