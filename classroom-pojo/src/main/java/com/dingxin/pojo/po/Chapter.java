package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *  实体类
 */
@TableName("ccr_chapter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chapter extends Model<Chapter> {

    private static final long serialVersionUID=1L;

    /**
     * 章节表主键
     */
    private Integer id;
    /**
     * 章节描述
     */
    private String chapterDesc;
    /**
     * 所属课程
     */
    private Integer curriculumId;
    /**
     * 父章节
     */
    private Integer parentId;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 章节序号
     */
    private Integer chapterOrderNumber;
    /**
     * 删除标志位
     */
    private Integer deleteFlag;

    @ApiModelProperty("视频名称")
    @TableField(exist = false)
    private String videoName;

    @ApiModelProperty("文件ID")
    @TableField(exist = false)
    private String fileId;

    @ApiModelProperty("审核状态")
    @TableField(exist = false)
    private Integer auditFlag;

    @ApiModelProperty("节")
    @TableField(exist = false)
    private List<Chapter> childChapter;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}