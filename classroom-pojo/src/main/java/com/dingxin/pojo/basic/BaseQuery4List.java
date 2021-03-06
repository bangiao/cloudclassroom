package com.dingxin.pojo.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * author: cuteG <br>
 * date: 2020/7/27 17:41 <br>
 * description: 查询所有列表时，只有分页参数 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("带分页的全量查询对象")
public class BaseQuery4List implements Serializable {

    @ApiModelProperty(value = "当前页:默认值1",example = "1")
    private int currentPage = 1;

    @ApiModelProperty(value = "每页条数:默认值10",example = "10")
    private int pageSize = 10;
}