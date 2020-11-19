package com.xubo.collection;

/**
 * 条件过滤接口
 * @param <T>
 */
public interface MyFilterByAge<T> {
    boolean test(T t);
}
