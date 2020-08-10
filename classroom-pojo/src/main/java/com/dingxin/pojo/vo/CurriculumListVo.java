package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.utils.DateUtils;
import com.dingxin.pojo.po.Curriculum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * author: cuteG <br>
 * date: 2020/7/28 0:30 <br>
 * description: 课程管理返回视图 <br>
 */
@Data
@ApiModel("课程管理返回视图")
@Builder
public class CurriculumListVo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "一般页面不会显示,课程主键",example = "10")
    private Integer id;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称",example = "大力怎么吃最好")
    private String curriculumName;



    public static CurriculumListVo convertToVo(Curriculum curriculumVoPo){
        if (Objects.isNull(curriculumVoPo))
            return null;
        return CurriculumListVo.builder()
                .id(curriculumVoPo.getId())
                .curriculumName(curriculumVoPo.getCurriculumName())
                .build();

    }

    public static List<CurriculumListVo> convertToVoWithPage(List<Curriculum> curriculumPo){
        Page<Curriculum> p = new Page<>();
        p.setRecords(curriculumPo);
        IPage<CurriculumListVo> convert = p.convert(CurriculumListVo::convertToVo);
        return convert.getRecords();
    }

}