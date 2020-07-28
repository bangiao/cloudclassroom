package com.dingxin.common.enums;

/**
 * author: cuteG <br>
 * date: 2020/7/28 15:24 <br>
 * description: todo <br>
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum  RoleEnum {

    NORMAL_USER(1,"普通用户"),

    TEACHER(2,"老师"),

    ADMINISTRATOR(3,"管理员");




    private int code;
    private String desc;

    public static RoleEnum getByCode(int code) {
        return Arrays.stream(values())
                .filter(roleEnum -> roleEnum.getCode()==code)
                .findFirst()
                .orElse(NORMAL_USER);
    }
}