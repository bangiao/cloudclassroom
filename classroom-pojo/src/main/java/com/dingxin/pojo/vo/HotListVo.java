package com.dingxin.pojo.vo;

import com.dingxin.pojo.po.Curriculum;
import com.dingxin.pojo.po.ProjectManagement;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ya.nie
 * @date 2020/8/14 19:25
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotListVo {
    @ApiModelProperty("类别类型 0：热门专题 1：热门课程 2：最近课程")
    private Integer type;

    @ApiModelProperty("类别名称")
    private String name;

    @ApiModelProperty("类别数据")
    private List list;
    ;
}
