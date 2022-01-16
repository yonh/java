package com.yonh.dao;

import com.yonh.pojo.User;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    public List<User> findAll() throws PropertyVetoException, DocumentException, SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;
    public User findByCondition(User user) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, PropertyVetoException, DocumentException;
    public boolean insert(User user);
    public boolean update(User user);
}
