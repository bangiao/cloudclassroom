package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author: cuteG <br>
 * date: 2020/8/11 1:47 <br>
 * description: todo <br>
 */
@Data
@ApiModel("混和章节和课程请求信息")
public class ChapterAndVideoInsertRequest {
    /**
     * 章节描述
     */
    @ApiModelProperty(value = "章节描述",example = "LBWNB")
    private String chapterDesc;
    /**
     * 所属课程
     */
    @ApiModelProperty(value = "所属课程",example = "LBWNB")
    private String curriculumId;
//    /**
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
     * 视频信息
     */
    @ApiModelProperty(value = "讲师",example = "{\n" +
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
    private VideoInsertRequest videoInfo;
}