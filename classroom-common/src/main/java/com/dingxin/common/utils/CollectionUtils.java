package com.dingxin.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * author: cuteG <br>
 * date: 2020/8/4 23:08 <br>
 * description: 集合工具类 <br>
 */
@Slf4j
public class CollectionUtils {

    public static boolean isEmpty(Collection collection){

        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection){

        return !isEmpty(collection);
    }
}