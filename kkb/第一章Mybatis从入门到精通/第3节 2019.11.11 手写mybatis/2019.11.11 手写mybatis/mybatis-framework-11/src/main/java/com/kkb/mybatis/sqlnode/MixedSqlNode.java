package com.kkb.mybatis.sqlnode;

import java.util.List;

import com.kkb.mybatis.sqlnode.iface.SqlNode;

/**
 * 封装了所有解析出来的sqlnode节点信息，方便统一处理
 * 
 * @author 灭霸詹
 *
 */
public class MixedSqlNode implements SqlNode {
	private List<SqlNode> sqlNodes;

	public MixedSqlNode(List<SqlNode> sqlNodes) {
		this.sqlNodes = sqlNodes;
	}

	@Override
	public void apply(DynamicContext context) {
		for (SqlNode sqlNode : sqlNodes) {
			sqlNode.apply(context);
		}
	}

}
