package com.kkb.mybatis.config;

import com.kkb.mybatis.sqlsource.iface.SqlSource;

/**
 * 用来封装映射文件中的CRUD标签，比如select标签
 * 
 * @author 灭霸詹
 *
 */
public class MappedStatement {
	private String statementId;
	private Class<?> parameterTypeClass;
	private Class<?> resultTypeClass;
	private String statementType;
	 private SqlSource sqlSource;

	public MappedStatement(String statementId, Class<?> parameterTypeClass, Class<?> resultTypeClass,
			String statementType, SqlSource sqlSource) {
		this.statementId = statementId;
		this.parameterTypeClass = parameterTypeClass;
		this.resultTypeClass = resultTypeClass;
		this.statementType = statementType;
		this.sqlSource = sqlSource;
	}

	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}

	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

	public Class<?> getResultTypeClass() {
		return resultTypeClass;
	}

	public void setResultTypeClass(Class<?> resultTypeClass) {
		this.resultTypeClass = resultTypeClass;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public SqlSource getSqlSource() {
		return sqlSource;
	}

	public void setSqlSource(SqlSource sqlSource) {
		this.sqlSource = sqlSource;
	}
	
	

}
