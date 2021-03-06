package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程收藏表 实体类
 */
@TableName("ccr_class_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("课程收藏实体")
@Builder
public class ClassCollection extends Model<ClassCollection> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String personId;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer classId;

    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}