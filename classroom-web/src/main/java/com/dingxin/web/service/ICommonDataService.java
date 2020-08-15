package com.dingxin.web.service;


import com.dingxin.pojo.basic.BaseResult;
import com.dingxin.pojo.request.CoursesStudentRequest;

public interface ICommonDataService{
    BaseResult photo(String id);

    BaseResult courses(CoursesStudentRequest request);
}
