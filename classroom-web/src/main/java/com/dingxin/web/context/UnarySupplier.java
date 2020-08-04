package com.dingxin.web.context;

/**
 * @author changxin.yuan
 * @date 2020/8/4 10:31
 */
@FunctionalInterface
public interface UnarySupplier<R,T> {

    R get(T t);

}
