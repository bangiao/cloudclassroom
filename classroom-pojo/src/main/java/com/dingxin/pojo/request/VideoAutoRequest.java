package com.dingxin.pojo.request;

import com.dingxin.pojo.po.VideoAudit;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class VideoAutoRequest {

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
