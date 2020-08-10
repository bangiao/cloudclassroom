package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  实体类
 */
@TableName("ccr_chapter")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String curriculumId;
    /**
     * 是否为根章节
     */
    private Integer rootChapterFlag;
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



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}