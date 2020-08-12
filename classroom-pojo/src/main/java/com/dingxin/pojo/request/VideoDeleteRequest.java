package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author changxin.yuan
 * @date 2020/8/11 22:54
 */
@Data
@ApiModel(value = "视频删除请求模型")
public class VideoDeleteRequest {

    @NotNull(message = "文件id不能为空")
    @ApiModelProperty(value = "文件id")
    private String fileId;

}
