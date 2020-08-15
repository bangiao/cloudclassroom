package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectManagement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@ApiModel("讲师个人信息返回视图")
public class TeacherVo {
    @ApiModelProperty(value = "id")
    private String zgh;
    /**
     * 个人介绍
     */
    @ApiModelProperty(value = "个人介绍")
    private String introduction;

    @ApiModelProperty(value = "头像url")
    private String Url;

    /**
     * 是否禁用 0：启用 -1：禁用
     */
    @ApiModelProperty(value = "是否禁用 0：启用 -1：禁用")
    private Integer enable;

    @ApiModelProperty(value = "课程相关列表")
    private List<Curriculum> curriculumList;

    @ApiModelProperty(value = "专题相关列表")
    private List<ProjectManagement> projectManagementList;
}
