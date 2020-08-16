package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @ApiModelProperty(value = "专题描述")
    private String projectDescription;
    public static ProjectManagementVo convent(ProjectManagement projectManagement){
        if (Objects.isNull(projectManagement)){
            throw  new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        ProjectManagementVo build = ProjectManagementVo
                .builder()
                .projectName(projectManagement.getProjectName())
                .id(projectManagement.getId())
                .projectDescription(projectManagement.getProjectDescription())
                .build();
        return build;
    }

    public static List<ProjectManagementVo> convertToVoWithPage(List<ProjectManagement> menu) {
        return menu.stream().map(ProjectManagementVo::convent).collect(Collectors.toList());
    }
}
