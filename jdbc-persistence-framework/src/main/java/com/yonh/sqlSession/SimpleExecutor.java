package com.yonh.sqlSession;

import com.yonh.config.BoundSql;
import com.yonh.pojo.Configuration;
import com.yonh.pojo.MappedStatement;
import com.yonh.utils.GenericTokenParser;
import com.yonh.utils.ParameterMapping;
import com.yonh.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {

        Connection conn = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        // 获取参数class对象
        String paramType = mappedStatement.getParameterType();
        Class<?> paramTypeClass = getClassType(paramType);
        // 预处理语句
        PreparedStatement preparedStatement = conn.prepareStatement(boundSql.getSql());
        // 参数列表
        List<ParameterMapping> parameterMappingList = boundSql.getParams();
        for (int i=0; i<parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String fieldName = parameterMapping.getContent();
            // 通过反射获取属性值
            Field declaredField = paramTypeClass.getDeclaredField(fieldName);
            // 防止属性私有不可访问，设置可访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]); // 获取参数对象对应的属性值

            // 设置参数
            preparedStatement.setObject(i+1, o);
        }

        // 执行SQL
        ResultSet resultSet = preparedStatement.executeQuery();

        // 封装返回值
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);


        List list = new ArrayList<Object>();

        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            Object resultObject = resultTypeClass.newInstance();

            // 遍历返回值封装实体对象
            for (int i=1; i<=metaData.getColumnCount(); i++) {

                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(columnName);

                // 写入对象属性值
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(resultObject, columnValue);
            }
            list.add(resultObject);
        }

        return list;
    }

    public boolean execute(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Connection conn = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        // 获取参数class对象
        String paramType = mappedStatement.getParameterType();
        Class<?> paramTypeClass = getClassType(paramType);

        PreparedStatement preparedStatement = conn.prepareStatement(boundSql.getSql());
        // 参数列表
        List<ParameterMapping> parameterMappingList = boundSql.getParams();

        for (int i=0; i<parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String fieldName = parameterMapping.getContent();
            // 通过反射获取属性值
            Field declaredField = paramTypeClass.getDeclaredField(fieldName);
            // 防止属性私有不可访问，设置可访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]); // 获取参数对象对应的属性值

            // 设置参数
            preparedStatement.setObject(i+1, o);
        }

        boolean flag = preparedStatement.execute();

        return flag;
    }

    private Class<?> getClassType(String paramType) throws ClassNotFoundException {
        if (paramType==null || paramType.isEmpty()) {
            return null;
        }

        return Class.forName(paramType);
    }

    /**
     * 完成对 #{属性} 使用 ? 替代，解析出#{}里面的内容进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        // 标记处理类，配置标记解析器完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 解析出来的sql，将#{} 替换为 ？
        String parseSql = genericTokenParser.parse(sql);
        // 解析出来的参数
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);

        return boundSql;
    }
}
