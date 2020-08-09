package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.po.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 学生信息表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("返回学生信息")
public class StudentRecordListVo extends Model<StudentRecordListVo> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键 查看课程列表传这个Id过去")
    private Integer id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String xm;

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号	")
    private String xh;
    /**
     * 院系名称
     */
    @ApiModelProperty(value = "学院")
    private String yxmc;

    /**
     * 专业名称
     */
    @ApiModelProperty(value = "专业")
    private String zymc;
    /**
     * 书院名称
     */
    @ApiModelProperty(value = "书院名称")
    private String symc;


    /**
     * 班级名称
     */
    @ApiModelProperty(value = "班级")
    private String bjmc;
    @ApiModelProperty(value = "学习时长")
    private String xxsc="0";


    public static StudentRecordListVo convent(Student source) {
        if (Objects.isNull(source)) {
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return StudentRecordListVo.builder().bjmc(source.getBjmc()).zymc(source.getZymc()).yxmc(source.getYxmc()).xh(source.getXh()).xm(source.getXm()).id(source.getId()).build();
    }

    public static IPage<StudentRecordListVo> convertToVoWithPage(IPage<Student> record) {
        return record.convert(StudentRecordListVo::convent);
    }


}