package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author: pulei2 <br>
 * date: 2020/8/14 23:19 <br>
 * description: 新增直播视频数据传输model <br>
 */
@Data
@ApiModel("新增直播视频数据传输model")
@AllArgsConstructor
@NoArgsConstructor
public class LiveVideoInsertRequest {
    /**
     * 课程主键
     */
    @ApiModelProperty(value = "课程主键",example = "1")
    @NotNull(message = "课程表id不能为空")
    private Integer curriculumId;

    /**
     * 课程主键
     */
    @ApiModelProperty(value = "章节id，当前章节下将添加视频",example = "1")
    @NotNull(message = "章节id不能为空")
    private Integer chapterId;

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
     * 直播视频 field token
     */
    @ApiModelProperty("直播视频field信息")
    private String liveVideoField;
    /**
     * 视频大小
     */
    @ApiModelProperty(value = "视频大小",example = "5.4G")
    private String videoSize;

}
