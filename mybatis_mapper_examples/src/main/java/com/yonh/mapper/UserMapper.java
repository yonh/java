package com.yonh.mapper;

import com.yonh.pojo.User;
import org.apache.ibatis.annotations.*;

import java.io.IOException;
import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "orders", column = "id", javaType = List.class, many = @Many(select = "com.yonh.mapper.OrderMapper.findByUid"))
    })
    List<User> findAll() throws IOException;
    // 多条件组合查询示例，if
    List<User> findByCondition(User user);
    // 多值查询示例, foreach
    List<User> findByIds(int[] ids);
    // 一对多映射示例
    List<User> findAllAndOrders();
    // 多对多映射示例
    List<User> findAllAndRoles();

    @Select("select * from user where id=#{id}")
    User findById(int id);

    @Insert("insert into user values(#{id}, #{username},#{password})")
    boolean saveUser(User user) throws IOException;
    @Update("update user set username=#{username},password=#{password} where id = #{id}")
    boolean updateUser(User user) throws IOException;
    @Delete("delete from user where id = #{id}")
    boolean deleteUser(int id) throws IOException;
}