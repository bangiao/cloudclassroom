package com.dingxin.pojo.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.Serializable;

/**
 * 课程收藏表 实体类
 */
@TableName("ccr_class_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCollection extends Model<ClassCollection> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer personId;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer classId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String className;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型")
    private Integer classType;
    /**
     * 课程类型字符串
     */
    @ApiModelProperty(value = "课程类型字符串")
    private String classTypeStr;
    /**
     * 讲师Id
     */
    @ApiModelProperty(value = "讲师Id")
    private Integer teacherId;
    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}