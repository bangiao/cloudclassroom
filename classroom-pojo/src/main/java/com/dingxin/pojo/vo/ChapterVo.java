package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: cuteG <br>
 * date: 2020/8/11 17:34 <br>
 * description: todo <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("章节视图")
public class ChapterVo {
    /**
     * 章节表主键
     */
    private Integer id;
    /**
     * 章节描述
     */
    private String chapterDesc;
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
}