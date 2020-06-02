package com.kkb.mybatis.sqlnode;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装了sql信息、可以封装入参信息
 * 
 * @author 灭霸詹
 *
 */
public class DynamicContext {

	private StringBuffer sb;

	private Map<String, Object> bindings = new HashMap<String, Object>();

	/**
	 * 将传入的入参对象，封装到Map集合中
	 * 
	 * @param parameter
	 */
	public DynamicContext(Object parameter) {
		this.bindings.put("_parameter", parameter);
	}

	public String getSql() {
		return sb.toString();
	}

	public void appendSql(String sql) {
		sb.append(sql);
		sb.append(" ");
	}

	public Map<String, Object> getBindings() {
		return bindings;
	}

}
