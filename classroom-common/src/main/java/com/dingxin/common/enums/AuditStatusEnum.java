package com.dingxin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 未审核状态
     */
    NO_AuDIT_STATUS(0),
    /**
     * 审核未通过状态
     */
    AUDIT_UNAPPROVE_STATUS(-1);

    private Integer status;

    public static List<Integer> getAllStatus(){
        return Arrays.stream(values()).map(AuditStatusEnum::getStatus).collect(Collectors.toList());
    }
}
