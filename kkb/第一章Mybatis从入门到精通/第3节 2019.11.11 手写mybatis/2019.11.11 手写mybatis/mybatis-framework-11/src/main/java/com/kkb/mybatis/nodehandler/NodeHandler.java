package com.kkb.mybatis.nodehandler;

import java.util.List;

import org.dom4j.Element;

import com.kkb.mybatis.sqlnode.iface.SqlNode;

/**
 * 处理select标签的子标签
 * 
 * @author 灭霸詹
 *
 */
public interface NodeHandler {

	void handleNode(Element nodeToHandle, List<SqlNode> contents);
}
