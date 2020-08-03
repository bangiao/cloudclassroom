package com.dingxin.pojo.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuery<T> implements Serializable {

    @ApiModelProperty("当前页:默认值1")
    private int currentPage = 1;

    @ApiModelProperty("每页条数:默认值10")
    private int pageSize = 10;

    @ApiModelProperty("查询对象")
    private T data;


    public <R> Page<R> convertToPage(){
        return new Page<R>(currentPage,pageSize);
    }

}
