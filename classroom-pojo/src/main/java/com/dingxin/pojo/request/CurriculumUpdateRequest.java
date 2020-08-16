package com.dingxin.pojo.request;

import com.dingxin.pojo.vo.ChapterAndVideoInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author: pulei2 <br>
 * date: 2020/8/13 21:53 <br>
 * description: 课程修改数据传输model <br>
 */
@Data
@ApiModel("编辑课程数据传输对象")
public class CurriculumUpdateRequest {

    /**
     * 主键
     */
    @ApiModelProperty(value = "课程主键",example = "1")
    @NotNull(message = "课程表id不能为空")
    private Integer id;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称",example = "大力怎么吃最好")
    @NotBlank(message = "课程名称不能为空")
    private String curriculumName;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型",example = "大力学")
    @NotBlank(message = "课程类型不能为空")
    private String curriculumType;
    /**
     * 课程介绍
     */
    @ApiModelProperty(value = "课程介绍",example = "大力学")
    @NotBlank(message = "课程介绍不能为空")
    private String curriculumDesc;
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
    @NotBlank(message = "讲师不能为空")
    private String teacherName;
    /**
     * 讲师id
     */
    @ApiModelProperty(value = "讲师id",example = "156295612da")
//    @NotBlank(message = "讲师id不能为空")
    private String teacherId;
    /**
     * 课程图片
     */
    @ApiModelProperty(value = "课程图片路径",example = "dev/static/154641effs.jpg")
    private String curriculumImage;
    /**
     * 所属专题
     */
    @ApiModelProperty(value = "所属专题id",example = "1")
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
            "\t{\n" +
            "\t\t\"chapterDesc\":\"spring的认识\",\n" +
            "\t\t\"parentId\":null,\n" +
            "\t\t\"chapterName\":\"第一章\",\n" +
            "\t\t\"chapterOrderNumber\":1,\n" +
            "\t\t\"curriculumId\":null,\n" +
            "\t\t\"childChapter\":[\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"chapterDesc\":\"IOC的学习2\",\n" +
            "\t\t\t\t\"parentId\":13,\n" +
            "\t\t\t\t\"chapterName\":\"第一节\",\n" +
            "\t\t\t\t\"chapterOrderNumber\":1,\n" +
            "\t\t\t\t\"curriculumId\":13,\n" +
            "\t\t\t\t\"childChapter\":null,\n" +
            "\t\t\t\t\"videoInfo\":{\n" +
            "\t\t\t\t\t\"id\":28,\n" +
            "\t\t\t\t\t\"videoName\":\"五五开\",\n" +
            "\t\t\t\t\t\"videoDuration\":null,\n" +
            "\t\t\t\t\t\"videoField\":null,\n" +
            "\t\t\t\t\t\"liveVideoField\":\"5285890806165313327\",\n" +
            "\t\t\t\t\t\"disableFlag\":0,\n" +
            "\t\t\t\t\t\"videoSize\":null\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"chapterDesc\":\"aop的学习2\",\n" +
            "\t\t\t\t\"parentId\":13,\n" +
            "\t\t\t\t\"chapterName\":\"第二节\",\n" +
            "\t\t\t\t\"chapterOrderNumber\":2,\n" +
            "\t\t\t\t\"curriculumId\":13,\n" +
            "\t\t\t\t\"childChapter\":null,\n" +
            "\t\t\t\t\"videoInfo\":{\n" +
            "\t\t\t\t\t\"id\":29,\n" +
            "\t\t\t\t\t\"videoName\":\"欢欢大战空空\",\n" +
            "\t\t\t\t\t\"videoDuration\":null,\n" +
            "\t\t\t\t\t\"videoField\":null,\n" +
            "\t\t\t\t\t\"liveVideoField\":null,\n" +
            "\t\t\t\t\t\"disableFlag\":0,\n" +
            "\t\t\t\t\t\"videoSize\":null\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"videoInfo\":null\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"chapterDesc\":\"spring的认识\",\n" +
            "\t\t\"parentId\":null,\n" +
            "\t\t\"chapterName\":\"第二章\",\n" +
            "\t\t\"chapterOrderNumber\":2,\n" +
            "\t\t\"curriculumId\":null,\n" +
            "\t\t\"childChapter\":[\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"chapterDesc\":\"IOC的学习3\",\n" +
            "\t\t\t\t\"parentId\":16,\n" +
            "\t\t\t\t\"chapterName\":\"第一节\",\n" +
            "\t\t\t\t\"chapterOrderNumber\":1,\n" +
            "\t\t\t\t\"curriculumId\":13,\n" +
            "\t\t\t\t\"childChapter\":null,\n" +
            "\t\t\t\t\"videoInfo\":{\n" +
            "\t\t\t\t\t\"id\":30,\n" +
            "\t\t\t\t\t\"videoName\":\"五五开\",\n" +
            "\t\t\t\t\t\"videoDuration\":null,\n" +
            "\t\t\t\t\t\"videoField\":null,\n" +
            "\t\t\t\t\t\"liveVideoField\":\"5285890806165313327\",\n" +
            "\t\t\t\t\t\"disableFlag\":0,\n" +
            "\t\t\t\t\t\"videoSize\":null\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"chapterDesc\":\"aop的学习4\",\n" +
            "\t\t\t\t\"parentId\":16,\n" +
            "\t\t\t\t\"chapterName\":\"第二节\",\n" +
            "\t\t\t\t\"chapterOrderNumber\":2,\n" +
            "\t\t\t\t\"curriculumId\":13,\n" +
            "\t\t\t\t\"childChapter\":null,\n" +
            "\t\t\t\t\"videoInfo\":{\n" +
            "\t\t\t\t\t\"id\":31,\n" +
            "\t\t\t\t\t\"videoName\":\"欢欢大战空空\",\n" +
            "\t\t\t\t\t\"videoDuration\":null,\n" +
            "\t\t\t\t\t\"videoField\":null,\n" +
            "\t\t\t\t\t\"liveVideoField\":null,\n" +
            "\t\t\t\t\t\"disableFlag\":0,\n" +
            "\t\t\t\t\t\"videoSize\":null\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"videoInfo\":null\n" +
            "\t}\n" +
            "]",dataType = "List")
    private List<ChapterAndVideoInfo> chapterAndVideoInfo;
}