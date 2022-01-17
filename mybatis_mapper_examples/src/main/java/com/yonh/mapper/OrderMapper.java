package com.yonh.mapper;

import com.yonh.pojo.Order;
import com.yonh.pojo.User;

import java.io.IOException;
import java.util.List;

public interface OrderMapper {
    List<Order> findAll() throws IOException;
}