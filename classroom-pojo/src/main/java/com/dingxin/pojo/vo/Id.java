package com.dingxin.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 专门用来接受id
 */
@Data
public class Id {
    @NotNull
    private Integer id;
}
