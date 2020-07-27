package com.dingxin.pojo.request;

import com.dingxin.pojo.po.VideoAudit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class VideoAutoRequest {

    private static final long serialVersionUID=1L;

    /**
     * 主键ids
     */
    @NotEmpty(message = "id不能为空")
    @ApiModelProperty(value = "主键ids")
    private List<Integer> idList;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String auditComments;
}
