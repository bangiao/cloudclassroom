package com.dingxin.pojo.request;

import com.dingxin.pojo.po.VideoAudit;
import lombok.Data;

import java.util.List;

@Data
public class VideoAutoRequest {

    private static final long serialVersionUID=1L;

    /**
     * 主键ids
     */
    private List<Integer> idList;
    /**
     * 审核意见
     */
    private String auditComments;
}
