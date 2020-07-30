package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 角色 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleVo extends Model<RoleVo> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建者姓名
     */
    private String createUserName;

    public static RoleVo convent(Role role) {
        if (Objects.isNull(role))
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        return RoleVo.builder().id(role.getId()).roleName(role.getRoleName())
                .remark(role.getRemark()).createTime(role.getCreateTime()).createUserName(role.getCreateUserName()).build();
    }

    public static IPage<RoleVo> convertToVoWithPage(IPage<Role> menu) {
        return menu.convert(RoleVo::convent);
    }

}