package com.kkb.mybatis.sqlnode;

import com.kkb.mybatis.sqlnode.iface.SqlNode;

/**
 * 保存了非${}的sql文本信息
 * 
 * @author 灭霸詹
 *
 */
public class StaticTextSqlNode implements SqlNode {

	private String sqlText;

	public StaticTextSqlNode(String sqlText) {
		this.sqlText = sqlText;
	}

	@Override
	public void apply(DynamicContext context) {
		context.appendSql(sqlText);
	}

}
