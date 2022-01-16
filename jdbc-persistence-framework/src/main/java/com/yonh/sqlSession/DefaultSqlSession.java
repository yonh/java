package com.yonh.sqlSession;

import com.yonh.pojo.Configuration;
import com.yonh.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
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

    public <T> T getMapper(Class<T> clazz) {
        // 使用jdk动态代理生成dao对象并返回
        Object proxy = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            // proxy: 当前代理对象的引用
            // method: 当前被调用方法的引用
            // args: 传递的参数
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 获取statementId
                // 注意mapper.xml的namespace需要配置为实现接口的全限定名，id配置为接口的实现方法名
                String className = method.getDeclaringClass().getName();
                String methodName = method.getName();
                String statementId = className + "." + methodName;

                // 获取当前方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行了泛型类型参数化
                if (genericReturnType instanceof ParameterizedType) {
                    List<Object> list = selectList(statementId, args);
                    return list;
                } else {
                    Object o = selectOne(statementId, args);
                    return o;
                }
            }
        });

        return (T) proxy;
    }
}
