package com.yonh.sqlSession;

import com.yonh.config.XmlConfigBuilder;
import com.yonh.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {
        // 1. 解析配置文件，将解析出来的内容封装成Configuration对象
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration config = xmlConfigBuilder.parseConfig(in);

        // 2. 创建SqlSessionFactory对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(config);

        return defaultSqlSessionFactory;
    }

}
