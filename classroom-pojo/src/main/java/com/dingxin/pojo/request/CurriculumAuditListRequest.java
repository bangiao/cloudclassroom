package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Api("课程审核列表请求数据传输对象")
public class CurriculumAuditListRequest extends BaseQuery4List {

    private static final long serialVersionUID=1L;


    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String curriculumName;
    /**
     * 课程状态
     */
    @ApiModelProperty(value = "课程状态（1通过-1未通过0审核中）")
    private Integer auditFlag;

}
