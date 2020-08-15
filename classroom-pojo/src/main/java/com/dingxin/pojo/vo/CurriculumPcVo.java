package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.Curriculum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 * @author ya.nie
 * @date 2020/8/15 16:45
 * @Description
 */
@Data
@ApiModel("pc课程返回视图")
@Builder
public class CurriculumPcVo {

    /**
     * 主键
     */
    @ApiModelProperty(value = "一般页面不会显示,课程主键",example = "10")
    private Integer id;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称",example = "大力怎么吃最好")
    private String curriculumName;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型",example = "大力学")
    private String curriculumType;

    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师",example = "LBWNB")
    private String teacherName;
    /**
     * 观看次数(该课程下所有视频的观看次数)
     */
    @ApiModelProperty(value = "观看次数(该课程下所有视频的观看次数)",example = "10")
    private Long watchAmount;

    /**
     * 视频时长(该课程下所有视频的累计时长)
     */
    @ApiModelProperty(value = "视频时长(该课程下所有视频的累计时长)",example = "5小时30分钟")
    private String videoDuration;

    /**
     * 课程图片
     */
    @ApiModelProperty(value = "课程图片")
    private String curriculumImage;

    @ApiModelProperty(value = "是否已经收藏")
    private Boolean isCollection;

    @ApiModelProperty(value = "是否已经收藏")
    private String  collectionNum;

    public static CurriculumPcVo convertToVo(Curriculum curriculumVoPo){
        if (Objects.isNull(curriculumVoPo))
            return null;
        return CurriculumPcVo.builder()
                .id(curriculumVoPo.getId())
                .curriculumName(curriculumVoPo.getCurriculumName())
                .curriculumType(curriculumVoPo.getCurriculumType())
                .teacherName(curriculumVoPo.getTeacherName())
                .watchAmount(curriculumVoPo.getWatchAmount())
                .videoDuration(DateUtils.secondsToTime(curriculumVoPo.getVideoDuration()))
                .curriculumImage(curriculumVoPo.getCurriculumImage())
                .build();

    }

    public static IPage<CurriculumPcVo> convertToVoWithPage(IPage<Curriculum> curriculumPo){

        return curriculumPo.convert(CurriculumPcVo::convertToVo);
    }

}
