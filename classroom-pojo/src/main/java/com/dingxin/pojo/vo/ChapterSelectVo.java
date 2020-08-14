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
 * author: pulei2 <br>
 * date: 2020/8/14 21:00 <br>
 * description: 显示课程对应已有父章节及子章节 <br>
 */
@Data
@ApiModel("章节和对应子章节视图，用于展示下拉")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterSelectVo {
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
    /**
     * 子章节及信息
     */
    @ApiModelProperty(value = "子章节",example = "LBWNB")
    private List<ChildChapterVo> childrenChapter;

    public static ChapterSelectVo convertToVo(Chapter chapterPo){
        if (Objects.isNull(chapterPo)){
            return ChapterSelectVo.builder().build();
        }
        return ChapterSelectVo.builder()
                .id(chapterPo.getId())
                .chapterDesc(chapterPo.getChapterDesc())
                .chapterName(chapterPo.getChapterName())
                .chapterOrderNumber(chapterPo.getChapterOrderNumber()).build();
    }
    public static List<ChapterSelectVo> convertToVos(List<Chapter> chapterPos){
        if (CollectionUtils.isEmpty(chapterPos)){
            return new ArrayList<>();
        }
        return chapterPos.stream().map(chapterPo-> ChapterSelectVo.builder()
                .id(chapterPo.getId())
                .chapterDesc(chapterPo.getChapterDesc())
                .chapterName(chapterPo.getChapterName())
                .chapterOrderNumber(chapterPo.getChapterOrderNumber()).build()).collect(Collectors.toList());
    }

}
