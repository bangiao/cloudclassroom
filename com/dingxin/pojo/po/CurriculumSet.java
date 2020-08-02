package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 *  实体类
 */
@TableName("ccr_curriculum_set")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumSet extends Model<CurriculumSet> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 课程表名称
     */
    private String curriculumName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 是否删除 0：未删除 1 ：删除
     */
    private Boolean delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}