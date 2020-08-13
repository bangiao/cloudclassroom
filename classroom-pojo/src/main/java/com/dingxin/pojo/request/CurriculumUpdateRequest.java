package com.dingxin.pojo.request;

import com.dingxin.pojo.vo.ChapterAndVideoInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private Integer curriculumId;
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
     * 将被删除的父章节，其下面的所有子章节及视频将被删除
     */
    @ApiModelProperty(value = "[本条数据可以为空]说明:将被删除的父章节，其下面的所有子章节及视频将被删除",example = "[1 ,2]",dataType = "List")
    private List<Integer> deleteParentChapterIds;
    /**
     * 将被删除的子章节，其下面的所有视频将被删除,但是其父章节和其他兄弟章节不会被删除
     */
    @ApiModelProperty(value = "[本条数据可以为空]说明:将被删除的子章节，其下面的所有视频将被删除,但是其父章节和其他兄弟章节不会被删除,重新上传视频也被理解为这种情况",example = "[1 ,2]",dataType = "List")
    private List<Integer> deleteChildChapterIds;
    /**
     * 将要被新增的章节及视频信息
     */
    @ApiModelProperty(value = "[本条数据可以为空]说明:将要被新增的章节及视频信息. 新增子章节，请带上他的父章节信息",example = "难得写",dataType = "List")
    private List<ChapterAndVideoInfo> chapterAndVideoWillBeSaved;
}