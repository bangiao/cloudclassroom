package com.dingxin.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 点赞vo 接受参数
 */
@Data
public class ThumbsUpRequest {

    /**
     * true 为点赞 false 取消赞
     */
    @NotNull
    private Boolean upOrDown;
    /**
     * 课程评价id
     */
    @NotNull
    private Integer id;

}
