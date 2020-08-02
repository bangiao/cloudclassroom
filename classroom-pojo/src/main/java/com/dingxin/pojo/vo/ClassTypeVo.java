package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.ClassType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 课程类型实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassTypeVo {

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
    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    public static ClassTypeVo convent(ClassType type) {
        if (Objects.isNull(type))
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        return ClassTypeVo.builder().id(type.getId()).typeName(type.getTypeName()).dataName(type.getDataName()).createPersonName(type.getCreatePersonName()).createTime(type.getCreateTime()).build();
    }

    public static IPage<ClassTypeVo> convertToVoWithPage(IPage<ClassType> type) {
        return type.convert(ClassTypeVo::convent);
    }


}