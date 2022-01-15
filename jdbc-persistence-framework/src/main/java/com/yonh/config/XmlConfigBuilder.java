package com.yonh.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yonh.io.Resources;
import com.yonh.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XmlConfigBuilder {
    private Configuration configuration;

    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }

    // 解析配置文件，返回Configuration对象
    public Configuration parseConfig(InputStream in) throws DocumentException, PropertyVetoException {
        // 解析xml获取数据源配置
        Document doc = new SAXReader().read(in);
        Element root =doc.getRootElement();
        List<Element> list = root.selectNodes("//property");

        Properties properties = new Properties();

        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }

        // 创建连接池对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);


        // 解析mapper.xml
        list = root.selectNodes("//mapper");
        for (Element element : list) {
            String mapperPath = element.attributeValue("resource");
            InputStream is = Resources.getResourceAsStream(mapperPath);

            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(is);
        }


        return this.configuration;
    }
}
