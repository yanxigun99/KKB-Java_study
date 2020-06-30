package com.kkb.mybatis.sqlsource;

import com.kkb.mybatis.sqlnode.iface.SqlNode;
import com.kkb.mybatis.sqlsource.iface.SqlSource;

public class DynamicSqlSource implements SqlSource {

	/**
	 * 只是封装了解析过程中产生的SqlNode解析信息
	 */
	private SqlNode rootSqlNode;

	public DynamicSqlSource(SqlNode rootSqlNode) {
		this.rootSqlNode = rootSqlNode;
	}

	@Override
	public BoundSql getBoundSql(Object paramObject) {
		// TODO执行阶段再去做该部分
		// TODO 其实此处就是要去解析SqlSource
		return null;
	}

}
