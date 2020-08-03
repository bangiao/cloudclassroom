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
 * author: cuteG <br>
 * date: 2020/7/28 0:30 <br>
 * description: 课程管理返回视图 <br>
 */
@Data
@ApiModel("课程管理返回视图")
@Builder
public class CurriculumVo {
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
     * 课程介绍
     */
    @ApiModelProperty(value = "课程介绍",example = "一天不吃，浑身难受")
    private String curriculumDesc;
    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师",example = "LBWNB")
    private String teacherName;
    /**
     * 观看次数(该课程下所有视频的观看次数)
     */
    @ApiModelProperty(value = "观看次数(该课程下所有视频的观看次数)",example = "10")
    private Integer watchAmount;

    /**
     * 视频时长(该课程下所有视频的累计时长)
     */
    @ApiModelProperty(value = "视频时长(该课程下所有视频的累计时长)",example = "5小时30分钟")
    private String videoDuration;

//    @ApiModelProperty(value = "审核状态(该课程下所有视频审核通过才会显示为通过)",example = "已审核上线")
    @ApiModelProperty(value = "是否有效(课程被禁用则课程下的所有视频被禁用)",example = "有效")
    private Integer validFlag;
    /**
     * 审核状态(-1 未通过，0未审核，1审核通过）
     */
    @ApiModelProperty("审核状态(-1 未通过，0未审核，1审核通过)")
    private Integer auditFlag;
    /**
     * 专题名
     */
    @ApiModelProperty(value = "专题名",example = "vue从入门到精通")
    private String topicName;


    public static CurriculumVo convertToVo(Curriculum curriculumVoPo){
        if (Objects.isNull(curriculumVoPo))
            return null;
        return CurriculumVo.builder()
                .id(curriculumVoPo.getId())
                .curriculumName(curriculumVoPo.getCurriculumName())
                .curriculumType(curriculumVoPo.getCurriculumType())
                .curriculumDesc(curriculumVoPo.getCurriculumDesc())
                .teacherName(curriculumVoPo.getTeacherName())
                .watchAmount(curriculumVoPo.getWatchAmount())
                .videoDuration(DateUtils.secondsToTime(curriculumVoPo.getVideoDuration()))
                .validFlag(curriculumVoPo.getDisableFlag())
                .auditFlag(curriculumVoPo.getAuditFlag())
                .topicName(curriculumVoPo.getTopicName())
                .build();

    }

    public static IPage<CurriculumVo> convertToVoWithPage(IPage<Curriculum> curriculumPo){

        return curriculumPo.convert(CurriculumVo::convertToVo);
    }

}