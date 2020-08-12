package com.dingxin.pojo.request;

import com.dingxin.pojo.vo.ChapterAndVideoInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "课程名称不能为空")
    private String curriculumName;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型",example = "大力学")
    @NotNull(message = "课程类型不能为空")
    private String curriculumType;
    /**
     * 课程类型主键
     */
    @ApiModelProperty(value = "课程类型主键",example = "1")
    @NotNull(message = "课程类型主键不能为空")
    private Integer classTypeId;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师",example = "LBWNB")
    @NotNull(message = "讲师不能为空")
    private String teacherName;
    /**
     * 讲师id
     */
    @ApiModelProperty(value = "讲师id",example = "156295612da")
    @NotNull(message = "讲师id不能为空")
    private String teacherId;
    /**
     * 课程图片
     */
    @ApiModelProperty(value = "课程图片路径",example = "dev/static/154641effs.jpg")
    private String curriculumImage;
    /**
     * 所属专题
     */
    @ApiModelProperty(value = "所属专题",example = "1")
    private Integer topicId;
    /**
     * 专题名
     */
    @ApiModelProperty(value = "专题名",example = "vue从入门到放弃")
    private String topicName;


    /**
     * 章节和视频信息
     */
    @ApiModelProperty(value = "章节和视频信息",example = "[\n" +
            "\n" +
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
            "}}]",dataType = "List")
    private List<ChapterAndVideoInfo> chapterAndVideoInfo;
}