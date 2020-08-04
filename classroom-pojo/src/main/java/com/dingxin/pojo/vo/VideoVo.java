package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.Video;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 * author: cuteG <br>
 * date: 2020/8/4 11:00 <br>
 * description: 视频返回数据model <br>
 */
@Data
@ApiModel("视频返回数据model")
@Builder
public class VideoVo {

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

    public static VideoVo convertToVo(Video videoPo){
        if (Objects.isNull(videoPo))
            return null;
        return VideoVo.builder()
                .id(videoPo.getId())
                .liveVideo(videoPo.getLiveVideo())
                .validFlag(videoPo.getValidFlag())
                .videoDuration(videoPo.getVideoDuration())
                .videoName(videoPo.getVideoName())
                .videoAttachment(videoPo.getVideoAttachment())
                .build();


    }

    public static IPage<VideoVo> convertToVoWithPage(IPage<Video> videoPo){

        return videoPo.convert(VideoVo::convertToVo);
    }


}