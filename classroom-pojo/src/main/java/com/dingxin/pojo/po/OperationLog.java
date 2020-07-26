package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.common.utils.DateUtils;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("ccr_operation_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID=1L;

    /**
     * 操作日志表主键
     */
    private Integer id;
    /**
     * 操作人
     */
    private String userId;
    /**
     * 操作时间
     */
    private long operateTime;
    /**
     * 操作内容
     */
    private String operateDesc;
    /**
     * ip地址
     */
    private String ipAddress;
    /**
     * 操作人姓名
     */
    private String operateUsername;

    /**
     * 日志查询 开始时间
     */
    @TableField(exist = false)
    private LocalDateTime startTime;

    /**
     * 日志查询 结束时间
     */
    @TableField(exist = false)
    private LocalDateTime endTime;

    public LocalDateTime getOperateTime() {
        return DateUtils.longToLocalDateTime(this.operateTime);
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}