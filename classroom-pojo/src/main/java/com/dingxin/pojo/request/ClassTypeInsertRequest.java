package com.dingxin.pojo.request;

import com.dingxin.pojo.po.ClassType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassTypeInsertRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "新增无，修改有")
    private Integer id;
    /**
     * 类别名称
     */
    @NotBlank(message = "typeName must not be null")
    @ApiModelProperty(value = "类别名称")
    private String typeName;
    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    @NotBlank(message = "dataName must not be null")
    private String dataName;

    public static ClassType convent(ClassTypeInsertRequest request) {
        return ClassType.builder().createPersonId(1).id(request.getId()).createPersonName("先写死").modifyTime(LocalDateTime.now())
                .dataName(request.getDataName()).typeName(request.getTypeName()).build();

    }


}