package com.dingxin.pojo.request;

import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.ClassCollection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 课程收藏表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("课程收藏数据接受对象")
public class ClassCollectionInsertRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    @NotNull(message = "classId must not be null")
    private Integer classId;





}