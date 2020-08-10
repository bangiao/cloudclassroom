package com.dingxin.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * author: cuteG <br>
 * date: 2020/8/11 0:52 <br>
 * description: 新增课程数据传输对象 <br>
 */
@Data
@ApiModel("新增课程数据传输对象")
public class CurriculumInsertRequest {


    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称",example = "大力怎么吃最好")
    private String curriculumName;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型",example = "大力学")
    private String curriculumType;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师",example = "LBWNB")
    private String teacherName;


    /**
     * 章节和视频信息
     */
    @ApiModelProperty(value = "章节和视频信息",example = "[\n" +
            "\n" +
            "    {\"curriculumName\":\"\",\"curriculumType\":\"\",\"teacherName\":\"\",\"chapterAndVideoInfo\": \n" +
            "        {\"chapterDesc\":\"\",\"curriculumId\":\"\",\"parentId\":\"\",\"chapterName\":\"\",\"chapterOrderNumber\":\"\",\"videoInfo\":{\n" +
            "\t\"curriculumId\": 0,\n" +
            "\t\"disableFlag\": 0,\n" +
            "\t\"id\": 1,\n" +
            "\t\"liveVideo\": \"\",\n" +
            "\t\"liveVideoId\": 0,\n" +
            "\t\"videoAttachment\": \"\",\n" +
            "\t\"videoDuration\": 0,\n" +
            "\t\"videoName\": \"\",\n" +
            "\t\"videoSize\": \"5.4G\"\n" +
            "}}}]",dataType = "List")
    private List<ChapterAndVideoInsertRequest> chapterAndVideoInfo;
}