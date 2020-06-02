package com.kkb.mybatis.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import com.kkb.mybatis.nodehandler.NodeHandler;
import com.kkb.mybatis.sqlnode.IfSqlNode;
import com.kkb.mybatis.sqlnode.MixedSqlNode;
import com.kkb.mybatis.sqlnode.StaticTextSqlNode;
import com.kkb.mybatis.sqlnode.TextSqlNode;
import com.kkb.mybatis.sqlnode.iface.SqlNode;
import com.kkb.mybatis.sqlsource.DynamicSqlSource;
import com.kkb.mybatis.sqlsource.RawSqlSource;
import com.kkb.mybatis.sqlsource.iface.SqlSource;

/**
 * 用来解析映射文件中的select/insert等CRUD标签
 * 
 * @author 灭霸詹
 *
 */
public class XMLStatementBuilder {
	private Configuration configuration;

	private boolean isDynamic = false;

	private Map<String, NodeHandler> nodeHandlerMap = new HashMap<String, NodeHandler>();

	public XMLStatementBuilder(Configuration configuration) {
		this.configuration = configuration;

		initNodeHandlers();

	}

	private void initNodeHandlers() {
		nodeHandlerMap.put("if", new IfNodeHandler());
		// nodeHandlerMap.put("where", new WhereNodeHandler());
	}

	public void parseStatement(Element selectElement, String namespace) {
		String statementId = selectElement.attributeValue("id");

		if (statementId == null || selectElement.equals("")) {
			return;
		}
		// 一个CURD标签对应一个MappedStatement对象
		// 一个MappedStatement对象由一个statementId来标识，所以保证唯一性
		// statementId = namespace + "." + CRUD标签的id属性
		statementId = namespace + "." + statementId;

		String parameterType = selectElement.attributeValue("parameterType");
		Class<?> parameterClass = resolveType(parameterType);

		String resultType = selectElement.attributeValue("resultType");
		Class<?> resultClass = resolveType(resultType);

		String statementType = selectElement.attributeValue("statementType");
		statementType = statementType == null || statementType == "" ? "prepared" : statementType;

		// 解析SQL信息
		SqlSource sqlSource = createSqlSource(selectElement);

		// TODO 建议使用构建者模式去优化
		MappedStatement mappedStatement = new MappedStatement(statementId, parameterClass, resultClass, statementType,
				sqlSource);
		configuration.addMappedStatement(statementId, mappedStatement);
	}

	private SqlSource createSqlSource(Element selectElement) {

		// 解析动态标签
		MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);
		SqlSource sqlSource = null;
		// 先判断是否包含${}
		if (isDynamic) {
			sqlSource = new DynamicSqlSource(rootSqlNode);
		} else {
			sqlSource = new RawSqlSource(rootSqlNode);
		}

		return sqlSource;
	}

	private MixedSqlNode parseDynamicTags(Element selectElement) {
		List<SqlNode> contents = new ArrayList<>();
		// 使用nodeCount会统计文本节点，而使用elements获取到的都是元素子节点
		int nodeCount = selectElement.nodeCount();
		for (int i = 0; i < nodeCount; i++) {
			Node node = selectElement.node(i);
			if (node instanceof Text) {
				String sqlText = node.getText().trim();
				if (sqlText == null || sqlText.equals("")) {
					continue;
				}
				TextSqlNode textSqlNode = new TextSqlNode(sqlText);
				if (textSqlNode.isDynamic()) {
					isDynamic = true;
					contents.add(textSqlNode);
				} else {
					contents.add(new StaticTextSqlNode(sqlText));
				}

			} else if (node instanceof Element) {
				// 此处通过NodeHandler去处理不同的标签
				Element nodeToHandle = (Element) node;
				String name = nodeToHandle.getName();
				// 怎么去查找对应的标签处理器呢，需要通过标签名称
				NodeHandler nodeHandler = nodeHandlerMap.get(name);
				nodeHandler.handleNode(nodeToHandle, contents);

				isDynamic = true;
			}
		}
		return new MixedSqlNode(contents);
	}

	private Class<?> resolveType(String parameterType) {
		try {
			Class<?> clazz = Class.forName(parameterType);
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 专门来解析if标签的标签处理器
	 * 
	 * @author 灭霸詹
	 *
	 */
	class IfNodeHandler implements NodeHandler {

		/**
		 * nodeToHandler：if标签
		 */
		@Override
		public void handleNode(Element nodeToHandler, List<SqlNode> contents) {
			String test = nodeToHandler.attributeValue("test");

			MixedSqlNode parseDynamicTags = parseDynamicTags(nodeToHandler);

			contents.add(new IfSqlNode(test, parseDynamicTags));
		}

	}
}
