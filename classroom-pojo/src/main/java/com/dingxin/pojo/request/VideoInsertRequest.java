package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author: cuteG <br>
 * date: 2020/8/4 23:03 <br>
 * description: 视频接收前端数据model <br>
 */
@Data
@ApiModel("课程数据传输对象")
public class VideoInsertRequest {

//    /**
//     * 视频表主键
//     */
//    @ApiModelProperty("主键id")
//    private Integer id;
    /**
     * 视频名称
     */
    @ApiModelProperty("视频名称")
    @NotBlank(message = "视频名称不能为空")
    private String videoName;
    /**
     * 视频时长
     */
    @ApiModelProperty("视频时长")
    @NotNull(message = "视频时长不能为空")
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
     * 所属课程
     */
    @ApiModelProperty("所属课程")
    private Integer curriculumId;
//    /**
//     * 讲师
//     */
//    @ApiModelProperty("讲师")
//    private String teacherName;

    /**
     * 是否有效(禁用之后为无效)
     */
    @ApiModelProperty("是否有效(禁用之后为无效)")
    private Integer validFlag;
}