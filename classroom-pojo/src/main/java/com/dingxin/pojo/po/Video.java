package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  实体类
 */
@ApiModel("视频对象")
@TableName("ccr_video")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video extends Model<Video> {

    private static final long serialVersionUID=1L;

    /**
     * 视频表主键
     */
    @ApiModelProperty("主键id")
    private Integer id;
    /**
     * 视频名称
     */
    @ApiModelProperty("视频名称")
    private String videoName;
    /**
     * 视频时长
     */
    @ApiModelProperty("视频时长")
    private Long videoDuration;
    /**
     * 视频附件
     */
    @ApiModelProperty("视频附件")
    private String videoAttachment;
    /**
     * 直播视频
     */
    @ApiModelProperty("直播视频")
    private String liveVideo;
    /**
     * 直播视频id
     */
    @ApiModelProperty("直播视频")
    private String liveVideoId;
    /**
     * 讲师
     */
    @ApiModelProperty("讲师")
    private String teacherId;


    private String teacherName;

    /**
     * 是否有效(禁用之后为无效)
     */
    @ApiModelProperty("是否有效(禁用之后为无效)")
    private Integer disableFlag;
    /**
     * 审核状态(审核中,已通过),审核状态决定能不能在页面看见该视频
     */
    @ApiModelProperty("审核状态(审核中,已通过),审核状态决定能不能在页面看见该视频")
    private Integer auditFlag;
    /**
     * 所属章节
     */
    @ApiModelProperty("所属章节")
    private Integer chapterId;
    /**
     * 所属课程
     */
    @ApiModelProperty("所属课程")
    private Integer curriculumId;
    /**
     * 审批意见
     */
    private String auditComments;
    /**
     * 删除标志位
     */
    private Integer deleteFlag;
    /**
     * 视频观看次数
     */
    private Long   watchAmount;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}