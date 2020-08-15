package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.ProjectManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 * @author ya.nie
 * @date 2020/7/31 16:16
 * @Description
 */
@Data
@Builder
public class ProjectManagementVo {
    /**
     * id
     */
    @ApiModelProperty(value = "专题id")
    private Integer id;
    /**
     * 专题名称
     */
    @ApiModelProperty(value = "专题名称")
    private String projectName;

    public static ProjectManagementVo convent(ProjectManagement projectManagement){
        if (Objects.isNull(projectManagement)){
            throw  new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        ProjectManagementVo build = ProjectManagementVo
                .builder()
                .projectName(projectManagement.getProjectName())
                .id(projectManagement.getId())
                .build();
        return build;
    }

    public static IPage<ProjectManagementVo> convertToVoWithPage(IPage<ProjectManagement> menu) {
        return menu.convert(ProjectManagementVo::convent);
    }
}
