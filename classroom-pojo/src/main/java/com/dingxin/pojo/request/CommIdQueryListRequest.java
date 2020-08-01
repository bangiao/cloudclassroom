package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共关键字查询request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommIdQueryListRequest extends BaseQuery4List {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "专用查询字符串")
    private String queryStr;


}