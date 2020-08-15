package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.Video;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
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
    @ApiModelProperty("视频field")
    private String videoField;
    /**
     * 直播视频
     */
    @ApiModelProperty("直播视频Field")
    private String liveVideoField;

    /**
     * 是否有效(禁用之后为无效)
     */
    @ApiModelProperty("是否有效(禁用之后为无效)")
    private Integer disableFlag;
    /**
     * 视频大小
     */
    @ApiModelProperty(value = "视频大小",example = "5.4G")
    private Long videoSize;


    public static VideoVo convertToVo(Video videoPo){
        if (Objects.isNull(videoPo))
            return null;
        return VideoVo.builder()
                .id(videoPo.getId())
                .liveVideoField(videoPo.getLiveVideoField())
                .disableFlag(videoPo.getDisableFlag())
                .videoDuration(videoPo.getVideoDuration())
                .videoName(videoPo.getVideoName())
                .videoField(videoPo.getVideoField())
                .videoSize(videoPo.getVideoSize())
                .build();


    }
    public static Video convertToPoWhileInsert(VideoVo videoVo){
        if (Objects.isNull(videoVo))
            return null;
        return Video.builder()
                .id(videoVo.getId())
                .liveVideoField(videoVo.getLiveVideoField())
                .videoDuration(videoVo.getVideoDuration())
                .videoName(videoVo.getVideoName())
                .videoField(videoVo.getVideoField())
                .videoSize(videoVo.getVideoSize())
                .deleteFlag(CommonConstant.DEL_FLAG)
                .disableFlag(CommonConstant.DISABLE_FALSE)
                .auditFlag(CommonConstant.STATUS_NOAUDIT)
                .createTime(LocalDateTime.now())
                .build();


    }

    public static IPage<VideoVo> convertToVoWithPage(IPage<Video> videoPo){

        return videoPo.convert(VideoVo::convertToVo);
    }


}