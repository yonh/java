package com.yonh.config;

import com.yonh.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {
    private String sql;
    private List<ParameterMapping> params = new ArrayList<ParameterMapping>();

    public BoundSql(String sql, List<ParameterMapping> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParams() {
        return params;
    }

    public void setParams(List<ParameterMapping> params) {
        this.params = params;
    }
}
