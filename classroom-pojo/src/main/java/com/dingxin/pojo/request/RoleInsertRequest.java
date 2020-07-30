package com.dingxin.pojo.request;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 角色 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("角色新增数据传输对象")
public class RoleInsertRequest extends Model<RoleInsertRequest> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "roleName must not be null")
    private String roleName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public static Role convent(RoleInsertRequest request) {
        if (Objects.isNull(request)){
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return Role.builder().id(request.getId()).roleName(request.getRoleName()).remark(request.getRemark()).build();
    }
}