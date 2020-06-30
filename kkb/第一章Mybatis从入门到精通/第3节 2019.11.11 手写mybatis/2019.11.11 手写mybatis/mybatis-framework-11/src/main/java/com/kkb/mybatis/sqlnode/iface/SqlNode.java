package com.kkb.mybatis.sqlnode.iface;

import com.kkb.mybatis.sqlnode.DynamicContext;

/**
 * 封装不同的sql脚本，提供sql脚本处理功能
 * @author 灭霸詹
 *
 */
public interface SqlNode {

	void apply(DynamicContext context);
}
