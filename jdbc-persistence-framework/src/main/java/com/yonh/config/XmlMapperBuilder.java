package com.yonh.config;

import com.yonh.pojo.Configuration;
import com.yonh.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XmlMapperBuilder {
    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream in) throws DocumentException {
        Document doc = new SAXReader().read(in);
        Element root = doc.getRootElement();
        String namespace = root.attributeValue("namespace");

        List<Element> list = root.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            MappedStatement stmt = new MappedStatement();
            stmt.setId(id);
            stmt.setResultType(resultType);
            stmt.setParameterType(parameterType);
            stmt.setSql(sql);

            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put( key, stmt);
        }
    }
}
