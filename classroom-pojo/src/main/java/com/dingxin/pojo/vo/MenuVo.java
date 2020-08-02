package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.ClassType;
import com.dingxin.pojo.po.Menu;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 菜单管理 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuVo  {

    private static final long serialVersionUID=1L;

    private Integer id;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String label;
    /**
     * 菜单URL
     */
    @ApiModelProperty(value = "菜单URL")
    private String url;
    /**
     *  1：菜单   2：按钮
     */
    @ApiModelProperty(value = "1：菜单   2：按钮")
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "是否被选中")
    private Boolean check;

    public static MenuVo convent(Menu menu){
        if (Objects.isNull(menu))
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        return MenuVo.builder().check(menu.getCheck()).orderNum(menu.getOrderNum())
                .icon(menu.getIcon()).type(menu.getType()).url(menu.getUrl())
                .label(menu.getName()).parentId(menu.getParentId()).id(menu.getId()).build();
    }
    public static IPage<MenuVo> convertToVoWithPage(IPage<Menu> menu) {
        return menu.convert(MenuVo::convent);
    }

}