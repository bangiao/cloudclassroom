package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * author: cuteG <br>
 * date: 2020/7/27 10:44 <br>
 * description: 日志管理查询条件 <br>
 */
@Data
@ApiModel("日志请求数据传输对象")
public class OperationLogRequest {
    /**
     * 日志查询 开始时间
     */
    @ApiModelProperty(value = "操作时间",example = "2020-07-27T14:59:33.423")
    private LocalDateTime startTime;

    /**
     * 日志查询 结束时间
     */
    @ApiModelProperty(value = "操作时间",example = "2020-07-27T14:59:34.423")
    private LocalDateTime endTime;

    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名",example = "LBWNB")
    private String operateUsername;
}