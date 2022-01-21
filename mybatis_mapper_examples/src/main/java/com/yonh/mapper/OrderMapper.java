package com.yonh.mapper;

import com.yonh.pojo.Order;
import com.yonh.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.io.IOException;
import java.util.List;

public interface OrderMapper {
    @Select("select * from orders")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "total",column = "total"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "com.yonh.mapper.UserMapper.findById"))
    })
    List<Order> findAll() throws IOException;
}