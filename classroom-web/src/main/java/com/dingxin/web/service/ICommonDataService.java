package com.dingxin.web.service;


import com.dingxin.pojo.basic.BaseResult;

public interface ICommonDataService{
    BaseResult photo(String id);

    BaseResult courses(String id);
}
