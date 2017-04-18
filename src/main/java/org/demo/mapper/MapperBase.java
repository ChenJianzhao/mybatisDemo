package org.demo.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by jzchen on 2017/4/15.
 */
public  interface MapperBase<T> {

    T get(Integer id);

    Integer add(T t);

    Integer update(T t);

    Integer delete(Integer id);


    List<T> selectAllBySQL(@Param("sql") String sql, @Param("param") Map<String,Object> param);
}
