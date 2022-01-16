package com.yonh.sqlSession;

import com.yonh.pojo.Configuration;
import com.yonh.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = this.configuration.getMappedStatementMap().get(statementId);
        List<E> list = executor.query(this.configuration, mappedStatement, params);
        return list;
    }

    public <T> T selectOne(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Object> list = selectList(statementId, params);
        if (list.size() == 1) {
            return (T)list.get(0);
        }

        throw new RuntimeException("返回结果为空或记录超过一条");
    }
}
