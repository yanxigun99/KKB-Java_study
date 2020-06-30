package com.kkb.mybatis.sqlsource;
/**
 * 解析#{}获取到的参数信息，主要包含参数名称（也就是#{}中的名称和参数类型）
 * @author 灭霸詹
 *
 */
public class ParameterMapping {

	private String name;
	
	private Class<?> type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public ParameterMapping(String name) {
		super();
		this.name = name;
	}
	
	
}
