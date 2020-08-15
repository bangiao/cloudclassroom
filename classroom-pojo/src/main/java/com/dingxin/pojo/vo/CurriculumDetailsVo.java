package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.Chapter;
import com.dingxin.pojo.po.Curriculum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author: cuteG <br>
 * date: 2020/8/11 21:36 <br>
 * description: todo <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("课程详情返回视图")
public class CurriculumDetailsVo {
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
     * 课程类型主键
     */
    @ApiModelProperty(value = "课程类型id",example = "1")
    private Integer classTypeId;
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
     * 讲师id
     */
    @ApiModelProperty(value = "讲师id",example = "LBWNB")
    private String teacherId;
    /**
     * 专题名
     */
    @ApiModelProperty(value = "专题名",example = "vue从入门到精通")
    private String topicName;
    /**
     * 所属专题id
     */
    @ApiModelProperty(value = "所属专题id",example = "vue从入门到精通")
    private Integer topicId;
    /**
     * 课程图片
     */
    @ApiModelProperty(value = "课程图片url",example = "dev/static/134afd.png")
    private String curriculumImage;
    /**
     * 是否禁用(是否有效)
     */
    @ApiModelProperty(value = "是否禁用",example = "0 未禁用，1 禁用")
    private Integer disableFlag;
    /**
     * 章节信息
     */
    @ApiModelProperty(value = "章节信息",example = "xxxx",dataType = "List")
    private List<ChapterAndVideoInfo> chapter = new ArrayList<>();

    public static CurriculumDetailsVo convertToVo(Curriculum curriculumPo){
        if (Objects.isNull(curriculumPo))
            return null;
        return CurriculumDetailsVo.builder()
                .id(curriculumPo.getId())
                .curriculumDesc(curriculumPo.getCurriculumDesc())
                .curriculumName(curriculumPo.getCurriculumName())
                .curriculumType(curriculumPo.getCurriculumType())
                .classTypeId(curriculumPo.getClassTypeId())
                .teacherName(curriculumPo.getTeacherName())
                .teacherId(curriculumPo.getTeacherId())
                .topicName(curriculumPo.getTopicName())
                .topicId(curriculumPo.getTopicId())
                .curriculumImage(curriculumPo.getCurriculumImage())
                .disableFlag(curriculumPo.getDisableFlag())
                .build();

    }

    public static IPage<ChapterAndVideoInfo> convertToVoWithPage(IPage<Chapter> chapterPo){

        return chapterPo.convert(ChapterAndVideoInfo::convertToVo);
    }
}