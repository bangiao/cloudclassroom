package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dingxin.common.constant.CommonConstant;
import com.dingxin.pojo.po.Chapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * author: cuteG <br>
 * date: 2020/8/11 1:47 <br>
 * description: todo <br>
 */
@Data
@ApiModel("混和章节和课程请求信息")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterAndVideoInfo {
    /**
     * 章节描述
     */
    @ApiModelProperty(value = "章节描述",example = "LBWNB")
    private String chapterDesc;
//    /**
////     * 所属课程
////     */
////    @ApiModelProperty(value = "所属课程",example = "LBWNB")
////    private String curriculumId;
//////    /**
//     * 是否为根章节
//     */
//    @ApiModelProperty(value = "讲师",example = "LBWNB")
//    private Integer rootChapterFlag;
    /**
     * 父章节
     */
    @ApiModelProperty(value = "父章节",example = "LBWNB")
    private Integer parentId;
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
     * 所属课程
     */
    @ApiModelProperty(value = "所属课程",example = "1")
    private Integer curriculumId;

    @ApiModelProperty(value = "子章节",example = "LBWNB")
    private List<ChapterAndVideoInfo> childChapter;

    /**
     * 视频信息
     */
    @ApiModelProperty(value = "视频信息",example = "{\n" +
            "\t\"curriculumId\": 0,\n" +
            "\t\"disableFlag\": 0,\n" +
            "\t\"id\": 1,\n" +
            "\t\"liveVideo\": \"\",\n" +
            "\t\"liveVideoId\": 0,\n" +
            "\t\"videoAttachment\": \"\",\n" +
            "\t\"videoDuration\": 0,\n" +
            "\t\"videoName\": \"\",\n" +
            "\t\"videoSize\": \"5.4G\"\n" +
            "}")
    private VideoVo videoInfo;

    public static ChapterAndVideoInfo convertToVo(Chapter chapterPo){
        if (Objects.isNull(chapterPo))
            return null;
        return ChapterAndVideoInfo.builder()
                .chapterDesc(chapterPo.getChapterDesc())
                .chapterName(chapterPo.getChapterName())
                .chapterOrderNumber(chapterPo.getChapterOrderNumber())
                .parentId(chapterPo.getParentId())
                .curriculumId(chapterPo.getCurriculumId())
                .build();
    }
    public static Chapter convertToPoWhileInsert(ChapterAndVideoInfo chapterDto){
        if (Objects.isNull(chapterDto))
            return null;
        return Chapter.builder()
                .chapterDesc(chapterDto.getChapterDesc())
                .chapterName(chapterDto.getChapterName())
                .chapterOrderNumber(chapterDto.getChapterOrderNumber())
                .parentId(chapterDto.getParentId())
                .deleteFlag(CommonConstant.DEL_FLAG)
                .build();
    }

    public static IPage<ChapterAndVideoInfo> convertToVoWithPage(IPage<Chapter> chapterPo){

        return chapterPo.convert(ChapterAndVideoInfo::convertToVo);
    }
}