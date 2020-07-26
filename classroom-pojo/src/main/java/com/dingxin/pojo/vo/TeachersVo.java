package com.dingxin.pojo.vo;

import com.dingxin.pojo.po.Teachers;
import lombok.Data;

import java.util.List;
@Data
public class TeachersVo extends Teachers {
    /**
     * 课程名称集合
     */
    private List<String> courseList;

    /**
     * 专题名称集合
     */
    private List<String> projectList;
    /**
     * 关键字查询
     */
    private String queryStr;
}
