package com.yonh.sqlSession;

import com.yonh.pojo.Configuration;
import com.yonh.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException;
    boolean execute(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;
}
