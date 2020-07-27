package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.OperationLog;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * author: cuteG <br>
 * date: 2020/7/27 11:39 <br>
 * description: 操作日志返回model<br>
 */
@Data
public class OperationLogVo {

    /**
     * 操作日志表主键
     */
    @ApiModelProperty(value = "角色名称,一般页面不会显示",example = "10")
    private Integer id;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间",example = "10")
    private LocalDateTime operateTime;
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

    public static OperationLogVo convertToVo(OperationLog operationLogPo){
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