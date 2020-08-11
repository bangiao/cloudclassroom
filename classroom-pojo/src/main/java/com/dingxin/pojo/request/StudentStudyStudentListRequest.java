package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("学生学习情况列表请求数据传输对象")
public class StudentStudyStudentListRequest extends BaseQuery4List {

    private static final long serialVersionUID = 1L;


    /**
     * 模糊查询查询专用字段
     */
    @ApiModelProperty(value = "模糊查询查询专用字段")
    private String queryStr;

    @ApiModelProperty(value = "本科生-1，研究生-2")
    private Integer type;


}
