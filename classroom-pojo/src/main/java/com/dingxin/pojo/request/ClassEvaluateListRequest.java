package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Api("课程评价列表请求数据传输对象")
public class ClassEvaluateListRequest extends BaseQuery4List {

    private static final long serialVersionUID=1L;


    /**
     * 模糊查询查询专用字段
     */
    @ApiModelProperty(value = "模糊查询查询专用字段")
    private String queryStr;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    @NotNull(message = "classId must not be null")
    private Integer classId;

}
