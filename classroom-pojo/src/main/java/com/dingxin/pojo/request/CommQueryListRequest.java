package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程收藏表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommQueryListRequest extends BaseQuery4List {

    private static final long serialVersionUID=1L;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "专用查询字符串")
    private String queryStr;


}