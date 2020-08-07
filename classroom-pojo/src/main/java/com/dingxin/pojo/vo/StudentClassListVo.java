package com.dingxin.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.dingxin.common.enums.ExceptionEnum;
import com.dingxin.common.exception.BusinessException;
import com.dingxin.pojo.po.StduentClassSeeRecord;
import com.dingxin.pojo.po.Student;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 学生信息表 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api("学生学习情况 课程列表")
public class StudentClassListVo extends Model<StudentClassListVo> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键 查看学习记录传这个Id过去 和classId 过去")
    private Integer studentId;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer classId;

    /**
     * 学习的课程名字
     */
    @ApiModelProperty(value = "学习的课程名字")
    private String className;
    /**
     * 课程类型
     */
    @ApiModelProperty(value = "课程类型")
    private String classType;

    /**
     * 讲师
     */
    @ApiModelProperty(value = "讲师")
    private String teacherName;

    /**
     * 学习开始时间
     */
    @ApiModelProperty(value = "学习开始时间")
    private String startStudentTime;


    /**
     * 学习时长
     */
    @ApiModelProperty(value = "学习时长")
    private String xxsc;


    public static StudentClassListVo convent(Map<String,String> source) {
        if (Objects.isNull(source)) {
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        return StudentClassListVo.builder().teacherName(source.get("teacher_name")).classType(source.get("class_type_name"))
                .className(source.get("class_name")).classId(Integer.parseInt(source.get("class_id"))).studentId(Integer.parseInt(source.get("student_id"))).xxsc(source.get("xxsc"))
                .startStudentTime(source.get("startStudentTime")).build();
    }

    public static List<StudentClassListVo> convertToVoWithPage(List<Map<String, Object>> record) {
        if (Objects.isNull(record)||record.size()==0){
            throw new BusinessException(ExceptionEnum.COVENT_NULLPOINT);
        }
        ArrayList<StudentClassListVo> objects = Lists.newArrayList();
        for (Map map : record) {
            objects.add(StudentClassListVo.convent(map));
        }
        return  objects;
    }


}