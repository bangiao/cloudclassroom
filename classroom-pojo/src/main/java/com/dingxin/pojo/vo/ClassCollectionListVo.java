package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.po.ClassEvaluate;
import com.dingxin.pojo.po.Curriculum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 课程收藏表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("课程收藏数据传输对象")
public class ClassCollectionListVo {

    private static final long serialVersionUID=1L;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer classId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String className;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型")
    private Integer classType;
    /**
     * 课程类型字符串
     */
    @ApiModelProperty(value = "课程类型字符串")
    private String classTypeStr;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
    /**
     * url
     */
    @ApiModelProperty(value = "课程封面")
    private String url;
    /**
     * videoDuration
     */
    @ApiModelProperty(value = "课时")
    private String videoDuration;

    @ApiModelProperty(value = "课程收藏次数")
    private Object collectionNum;

    public static ClassCollectionListVo convertToVo(Curriculum curriculum){
        if (Objects.isNull(curriculum))
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        return ClassCollectionListVo.builder()
                .classId(curriculum.getId())
                .teacherName(curriculum.getTeacherName())
               .classTypeStr(curriculum.getCurriculumName())
                .classType(curriculum.getClassTypeId())
                .className(curriculum.getCurriculumName())
                .url(curriculum.getCurriculumImage())
                .videoDuration(DateUtils.formatDateTimeStr(curriculum.getVideoDuration()==null?0:curriculum.getVideoDuration()))
                .build();
    }

    public static IPage<ClassCollectionListVo> convertToVoWithPage(IPage<Curriculum> classEvaluate){

        return classEvaluate.convert(ClassCollectionListVo::convertToVo);
    }

}