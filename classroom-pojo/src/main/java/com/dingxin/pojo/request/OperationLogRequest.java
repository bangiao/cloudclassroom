package com.dingxin.pojo.request;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author: cuteG <br>
 * date: 2020/7/27 10:44 <br>
 * description: 日志管理查询条件 <br>
 */
@Data
public class OperationLogRequest {
    /**
     * 日志查询 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 日志查询 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 操作人姓名
     */
    private String operateUsername;
}