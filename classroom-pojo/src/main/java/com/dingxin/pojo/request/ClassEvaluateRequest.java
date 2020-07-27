package com.dingxin.pojo.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ClassEvaluateRequest {

    private static final long serialVersionUID=1L;

    /**
     * 主键ids
     */
    @NotNull(message = "id不能为空")
    private List<Integer> idList;
    /**
     * 审核意见
     */
    private String auditComments;
}
