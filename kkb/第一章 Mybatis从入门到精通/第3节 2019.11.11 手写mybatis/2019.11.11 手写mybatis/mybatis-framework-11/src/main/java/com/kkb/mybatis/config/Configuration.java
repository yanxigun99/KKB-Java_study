package com.kkb.mybatis.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * 封装了全局配置文件和映射文件中的信息
 * 
 * @author 灭霸詹
 *
 */
public class Configuration {

	private DataSource dataSource;

	private Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
		this.mappedStatements.put(statementId, mappedStatement);
	}

	public MappedStatement getMappedStatementById(String statementId) {
		return this.mappedStatements.get(statementId);
	}

}
