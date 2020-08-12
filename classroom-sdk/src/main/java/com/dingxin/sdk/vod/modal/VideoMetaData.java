package com.dingxin.sdk.vod.modal;

import com.tencentcloudapi.vod.v20180717.models.MediaInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 视频基本元数据
 *
 * @author changxin.yuan
 * @date 2020/8/11 22:06
 */
@Data
public class VideoMetaData {

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 名称
     */
    private String name;

    /**
     * 时长
     */
    private BigDecimal duration;

    /**
     * url
     */
    @Deprecated
    private String url;

    /**
     * 大小
     */
    private Long size;

    /**
     * 创建时间
     */
    private String createTime;



    public static VideoMetaData of(MediaInfo info){
        if(Objects.isNull(info)){
            return null;
        }
        VideoMetaData data = new VideoMetaData();
        data.setName(info.getBasicInfo().getName());
        data.setFileId(info.getFileId());
        data.setCreateTime(info.getBasicInfo().getCreateTime());
        data.setDuration(BigDecimal.valueOf(info.getMetaData().getDuration()));
        data.setSize(info.getMetaData().getSize());
        return data;
    }

}
