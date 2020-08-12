package com.dingxin.pojo.dto;

import com.dingxin.pojo.basic.BaseQuery;
import lombok.Data;

import java.util.List;

/**
 * @author changxin.yuan
 * @date 2020/8/11 21:39
 */
@Data
public class VodSearchDTO extends BaseQuery<List> {

    private String text;

    private Long total;


    public Integer offset(){
        return (getCurrentPage()-1)*getPageSize();
    }

}
