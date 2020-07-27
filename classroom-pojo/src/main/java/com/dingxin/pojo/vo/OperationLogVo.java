package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.OperationLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author: cuteG <br>
 * date: 2020/7/27 11:39 <br>
 * description: 操作日志返回model<br>
 */
@Data
@ApiModel("日志返回对象")
public class OperationLogVo {

    /**
     * 操作日志表主键
     */
    @ApiModelProperty(value = "一般页面不会显示,日志表主键",example = "10")
    private Integer id;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间",example = "2020-07-27T14:59:33.423")
    private LocalDateTime operateTime;
    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作描述",example = "查询学生评价")
    private String operateDesc;
    /**
     * ip地址
     */
    @ApiModelProperty(value = "操作者IP地址",example = "127.0.0.1")
    private String ipAddress;
    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名",example = "LBWNB")
    private String operateUsername;

    public static OperationLogVo convertToVo(OperationLog operationLogPo){
        if (Objects.isNull(operationLogPo))
            return null;
        OperationLogVo operationLogVo = new OperationLogVo();
        operationLogVo.setId(operationLogPo.getId());
        operationLogVo.setIpAddress(operationLogPo.getIpAddress());
        operationLogVo.setOperateDesc(operationLogPo.getOperateDesc());
        operationLogVo.setOperateTime(DateUtils.longToLocalDateTime(operationLogPo.getOperateTime()));
        operationLogVo.setOperateUsername(operationLogPo.getOperateUsername());

        return operationLogVo;
    }

    public static IPage<OperationLogVo> convertToVoWithPage(IPage<OperationLog> operationLogPo){

        return operationLogPo.convert(OperationLogVo::convertToVo);
    }
}