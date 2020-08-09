package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("视频审核列表请求数据传输对象")
public class VideoListRequest extends BaseQuery4List {

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
    private Integer curriculumId;


}
