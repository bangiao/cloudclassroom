package com.dingxin.pojo.vo;

import com.dingxin.pojo.po.ProjectManagement;
import lombok.Builder;
import lombok.Data;

/**
 * @author ya.nie
 * @date 2020/7/24 16:43
 * @Description
 */
@Data
public class ProjectManagementVo extends ProjectManagement {
    /**
     * 讲师名字
     */
    private String lecturerName;
    /**
     * 关键字查询
     */
    private String queryStr;
}
