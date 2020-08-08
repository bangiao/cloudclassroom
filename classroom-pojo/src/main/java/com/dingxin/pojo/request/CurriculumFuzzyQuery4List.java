package com.dingxin.pojo.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: cuteG <br>
 * date: 2020/8/4 16:27 <br>
 * description: 课程分页和模糊查询对象 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("课程分页和模糊查询对象")
public class CurriculumFuzzyQuery4List extends BaseQuery4List {

    /**
     * 模糊查询查询专用字段
     */
    @ApiModelProperty(value = "模糊查询查询专用字段")
    private String queryStr;

    /**
     * 审核状态(-1 未通过，0未审核，1审核通过）
     */
    @ApiModelProperty("审核状态(-1 未通过，0未审核，1审核通过)")
    private Integer auditFlag;
    /**
     * 课程类型
     */
    @ApiModelProperty("课程类型")
    private Integer classType;

}