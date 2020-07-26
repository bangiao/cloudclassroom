package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}