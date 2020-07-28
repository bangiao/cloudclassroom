package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.pojo.po.ClassType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程类型实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassTypeVo extends Model<ClassTypeVo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称")
    private String typeName;
    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String dataName;
    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createPersonName;

    public static ClassTypeVo convent(ClassType type) {
        return ClassTypeVo.builder().id(type.getId()).typeName(type.getTypeName()).dataName(type.getDataName()).createPersonName(type.getCreatePersonName()).build();
    }

    public static IPage<ClassTypeVo> convertToVoWithPage(IPage<ClassType> type) {
        return type.convert(ClassTypeVo::convent);
    }


}