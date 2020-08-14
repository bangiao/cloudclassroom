package com.dingxin.pojo.vo;

import com.dingxin.pojo.po.Chapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * author: cuteG <br>
 * date: 2020/8/11 17:34 <br>
 * description: todo <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("章节视图")
@Builder
public class ChildChapterVo {
    /**
     * 章节表主键
     */
    @ApiModelProperty(value = "章节表主键",example = "1")
    private Integer id;
    /**
     * 章节描述
     */
    @ApiModelProperty(value = "章节描述",example = "假如超人会飞")
    private String chapterDesc;
    /**
     * 章节名称
     */
    @ApiModelProperty(value = "章节名称",example = "LBWNB")
    private String chapterName;
    /**
     * 章节序号
     */
    @ApiModelProperty(value = "章节序号",example = "LBWNB")
    private Integer chapterOrderNumber;

    public static ChildChapterVo convertToVo(Chapter chapterPo){
        if (Objects.isNull(chapterPo)){
            return ChildChapterVo.builder().build();
        }
        return ChildChapterVo.builder()
                .id(chapterPo.getId())
                .chapterDesc(chapterPo.getChapterDesc())
                .chapterName(chapterPo.getChapterName())
                .chapterOrderNumber(chapterPo.getChapterOrderNumber())
                .build();
    }
    public static List<ChildChapterVo> convertToVos(List<Chapter> chapterPos){
        if (CollectionUtils.isEmpty(chapterPos)){
            return new ArrayList<>();
        }
        return chapterPos.stream().map(chapterPo-> ChildChapterVo.builder()
                .id(chapterPo.getId())
                .chapterDesc(chapterPo.getChapterDesc())
                .chapterName(chapterPo.getChapterName())
                .chapterOrderNumber(chapterPo.getChapterOrderNumber()).build()).collect(Collectors.toList());
    }
}