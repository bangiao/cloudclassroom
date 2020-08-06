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
    @ApiModelProperty(value = "主键id",example = "1")
    @NotNull(message = "视频id不能为空哦,好兄弟")
    private Integer id;
    /**
     * 视频名称
     */
    @ApiModelProperty(value = "视频名称",example = "大战空空")
    private String videoName;
    /**
     * 视频时长
     */
    @ApiModelProperty(value = "视频时长",example = "51651651561651")
    private Long videoDuration;
    /**
     * 视频附件
     */
    @ApiModelProperty(value = "视频附件",example = "h哈哈")
    private String videoAttachment;
    /**
     * 直播视频
     */
    @ApiModelProperty(value = "直播视频",example = "哈哈哈哈")
    private String liveVideo;
    /**
     * 直播视频id
     */
    @ApiModelProperty(value = "直播视频",example = "1")
    private Integer liveVideoId;
    /**
     * 所属课程
     */
    @ApiModelProperty(value = "所属课程id",example = "1")
    private Integer curriculumId;
    /**
     * 是否有效(禁用之后为无效)
     */
    @ApiModelProperty(value = "是否有效(禁用之后为无效)",example = "1")
    private Integer disableFlag;
    /**
     * 视频大小
     */
    @ApiModelProperty(value = "视频大小",example = "5.4G")
    private String videoSize;
}