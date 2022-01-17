package com.yonh.mapper;

import com.yonh.pojo.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {
    List<User> findAll() throws IOException;
    // 多条件组合查询示例，if
    List<User> findByCondition(User user);
    // 多值查询示例, foreach
    List<User> findByIds(int[] ids);
    boolean saveUser(User user) throws IOException;
    boolean updateUser(User user) throws IOException;
    boolean deleteUser(int id) throws IOException;
}