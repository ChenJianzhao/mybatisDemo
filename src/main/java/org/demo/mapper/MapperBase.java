package org.demo.mapper;

/**
 * Created by jzchen on 2017/4/15.
 */
public abstract interface MapperBase<T> {

    T get(Integer id);

    Integer add(T t);

    Integer update(T t);

    Integer delete(Integer id);
}
