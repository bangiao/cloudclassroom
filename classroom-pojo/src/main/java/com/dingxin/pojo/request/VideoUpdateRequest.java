package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author: cuteG <br>
 * date: 2020/8/7 0:05 <br>
 * description: 视频更新数据传输对象 <br>
 */
@Data
@ApiModel("课程数据传输对象")
public class VideoUpdateRequest {

    /**
     * 视频表主键
     */
    @ApiModelProperty("主键id")
    @NotNull(message = "视频id不能为空哦,好兄弟")
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
     * 所属课程
     */
    @ApiModelProperty("所属课程")
    private Integer curriculumId;
    /**
     * 是否有效(禁用之后为无效)
     */
    @ApiModelProperty("是否有效(禁用之后为无效)")
    private Integer disableFlag;
    /**
     * 视频大小
     */
    private String videoSize;
}