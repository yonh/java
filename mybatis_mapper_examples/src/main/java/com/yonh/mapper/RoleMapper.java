package com.yonh.mapper;

import com.yonh.pojo.Order;
import com.yonh.pojo.Role;
import com.yonh.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.io.IOException;
import java.util.List;

public interface RoleMapper {
    @Select("select * from sys_role r, sys_user_role ur where r.id = ur.roleid and ur.userid = #{uid}")
    List<Role> findByUid(int uid);
}