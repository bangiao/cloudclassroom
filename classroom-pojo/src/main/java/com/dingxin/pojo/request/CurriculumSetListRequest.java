package com.dingxin.pojo.request;

import com.dingxin.pojo.basic.BaseQuery4List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2020/8/12
 */
@Data
@ApiModel("课程表列表查询对象")
public class CurriculumSetListRequest extends BaseQuery4List {

    @ApiModelProperty("模糊查询专用字段")
    private String queryStr;

}
