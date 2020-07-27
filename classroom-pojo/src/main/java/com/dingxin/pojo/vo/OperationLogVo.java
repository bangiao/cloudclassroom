package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.OperationLog;
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
    private Integer id;
    /**
     * 操作时间
     */
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

    public OperationLogVo convertToVo(OperationLog operationLogPo){
        this.ipAddress = operationLogPo.getIpAddress();
        this.operateDesc = operationLogPo.getOperateDesc();
        this.operateTime = DateUtils.longToLocalDateTime(operationLogPo.getOperateTime());
        this.operateUsername = operationLogPo.getOperateUsername();
        return this;
    }

    public static IPage<OperationLogVo> convertToVoWithPage(IPage<OperationLogVo> operationLogVo,IPage<OperationLog> operationLogPo){
        operationLogVo.setTotal(operationLogPo.getTotal());
        operationLogVo.setCurrent(operationLogPo.getCurrent());
        operationLogVo.setPages(operationLogPo.getPages());
        operationLogVo.setSize(operationLogPo.getSize());
        List<OperationLog> operationLogList = operationLogPo.getRecords();
        ArrayList<OperationLogVo> operationLogVos = new ArrayList<>();
        if (operationLogList != null){
            operationLogList.forEach(perOperationLogPo -> {
                OperationLogVo perOperationLogVo = new OperationLogVo();
                perOperationLogVo.setIpAddress(perOperationLogPo.getIpAddress());
                perOperationLogVo.setOperateDesc(perOperationLogPo.getOperateDesc());
                perOperationLogVo.setOperateTime(DateUtils.longToLocalDateTime(perOperationLogPo.getOperateTime()));
                perOperationLogVo.setOperateUsername(perOperationLogPo.getOperateUsername());

                operationLogVos.add(perOperationLogVo);
            });

            operationLogVo.setRecords(operationLogVos);
        }

        return operationLogVo;
    }
}