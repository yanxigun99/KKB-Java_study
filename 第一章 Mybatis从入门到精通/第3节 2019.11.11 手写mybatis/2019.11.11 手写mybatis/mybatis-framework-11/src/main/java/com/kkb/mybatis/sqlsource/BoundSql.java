package com.kkb.mybatis.sqlsource;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {

	// JDBC可以执行的SQL语句
	private String sql;

	// 参数集合
	private List<ParameterMapping> parameterMappings = new ArrayList<>();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

	public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
		super();
		this.sql = sql;
		this.parameterMappings = parameterMappings;
	}
	
}
