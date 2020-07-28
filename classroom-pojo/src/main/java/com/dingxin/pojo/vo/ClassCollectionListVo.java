package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.pojo.po.ClassCollection;
import com.dingxin.pojo.po.ClassEvaluate;
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
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
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

    public static ClassCollectionListVo convertToVo(ClassCollection classCollection){
        if (Objects.isNull(classCollection))
            return null;
        return ClassCollectionListVo.builder().id(classCollection.getId()).teacherName(classCollection.getTeacherName())
               .classTypeStr(classCollection.getClassTypeStr()).classType(classCollection.getClassType())
                .className(classCollection.getClassName()).classId(classCollection.getClassId())
                .build();
    }

    public static IPage<ClassCollectionListVo> convertToVoWithPage(IPage<ClassCollection> classEvaluate){

        return classEvaluate.convert(ClassCollectionListVo::convertToVo);
    }

}