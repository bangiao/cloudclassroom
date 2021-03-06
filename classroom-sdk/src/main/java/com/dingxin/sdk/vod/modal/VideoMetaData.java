package com.dingxin.sdk.vod.modal;

import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.Video;
import com.tencentcloudapi.vod.v20180717.models.MediaInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
     * 时长  时-分-秒
     */
    private String duration;

    /**
     * 时长  long   second
     */
    private Long durationNum;

    /**
     * url
     */
    @Deprecated
    private String url;

    /**
     * 大小  GB-MB-KB
     */
    private String size;

    /**
     * 大小    long    byte
     */
    private Long sizeNum;

    /**
     * 创建时间
     */
    private String createTime;

    private static final String UNKNOWN = "未知";


    private static final BigDecimal SCALE = BigDecimal.valueOf(1024);



    public static VideoMetaData of(MediaInfo info) {
        if (Objects.isNull(info)) {
            return null;
        }
        VideoMetaData data = new VideoMetaData();
        data.setName(info.getBasicInfo().getName());
        data.setFileId(info.getFileId());
        data.setCreateTime(info.getBasicInfo().getCreateTime());
        Float duration = info.getMetaData().getDuration();
        if (Objects.isNull(duration)) {
            data.setDuration(UNKNOWN);
        } else {
            data.setDuration(LocalDateTime
                    .ofInstant(Instant.ofEpochMilli(new Float((duration * 1000f)).intValue()),
                            ZoneId.of("UTC"))
                    .format(DateTimeFormatter.ofPattern("HH时mm分ss秒")));
        }
        data.setDurationNum(duration.longValue());
        Long size = info.getMetaData().getSize();
        if(Objects.isNull(size)){
            data.setSize(UNKNOWN);
        }else{
            data.setSize(formatSize(BigDecimal.valueOf(size),0));
        }
        data.setSizeNum(size);
        return data;
    }


    public static VideoMetaData of(Video video) {
        if (Objects.isNull(video)) {
            return null;
        }
        VideoMetaData data = new VideoMetaData();
        data.setName(video.getVideoName());
        data.setCreateTime(DateUtils.localDatetimeToString(video.getCreateTime()));
        if (video.getLiveVideoField()!=null){
            data.setFileId(video.getLiveVideoField());
        }else {
            data.setFileId(video.getVideoField());
        }
        Long duration = video.getVideoDuration();
        if (Objects.isNull(duration)) {
            data.setDuration(UNKNOWN);
        } else {
            data.setDuration(LocalDateTime
                    .ofInstant(Instant.ofEpochMilli(duration * 1000),
                            ZoneId.of("UTC"))
                    .format(DateTimeFormatter.ofPattern("HH时mm分ss秒")));
        }
        data.setDurationNum(duration);
        if(Objects.isNull(video.getVideoSize())){
            data.setSize(UNKNOWN);
        }else{
            Long size = video.getVideoSize();
            data.setSize(formatSize(BigDecimal.valueOf(size),0));
            data.setSizeNum(video.getVideoSize());
        }
        return data;
    }


    public static VideoMetaData of(VideoMetaData video) {
        if (Objects.isNull(video)) {
            return null;
        }
        VideoMetaData data = new VideoMetaData();
        data.setName(video.getName());
        data.setFileId(video.getFileId());
        Long duration = video.getDurationNum();
        if (Objects.isNull(duration)) {
            data.setDuration(UNKNOWN);
        } else {
            data.setDuration(LocalDateTime
                    .ofInstant(Instant.ofEpochMilli(duration * 1000),
                            ZoneId.of("UTC"))
                    .format(DateTimeFormatter.ofPattern("HH时mm分ss秒")));
        }
        data.setDurationNum(duration);
        if(Objects.isNull(video.getSizeNum())){
            data.setSize(UNKNOWN);
        }else{
            Long size = video.getSizeNum();
            data.setSize(formatSize(BigDecimal.valueOf(size),0));
            data.setSizeNum(video.getSizeNum());
        }
        return data;
    }


    private static String formatSize(BigDecimal size, int i){
        if(size.divide(SCALE).doubleValue()>1){
            return formatSize(size.divide(SCALE),++i);
        }else{
            double doubleValue = size.setScale(2, BigDecimal.ROUND_UP).doubleValue();
            switch (i){
                case 0:
                    return doubleValue+"b";
                case 1:
                    return doubleValue+"kb";
                case 2:
                    return doubleValue+"mb";
                case 3:
                    return doubleValue+"gb";
                case 4:
                    return doubleValue+"tb";
                default:
                    return UNKNOWN;
            }
        }
    }

}
