package com.kkb.mybatis.config;

import java.util.List;

import org.dom4j.Element;

/**
 * 专门来解析映射文件的类
 * 
 * @author 灭霸詹
 *
 */
public class XMLMapperBuilder {
	private Configuration configuration;

	public XMLMapperBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	@SuppressWarnings("unchecked")
	public void parse(Element rootElement) {
		//为了方便管理statement，需要使用statement唯一标识
		String namespace = rootElement.attributeValue("namespace");
		
		List<Element> selectElements = rootElement.elements("select");
		for (Element selectElement : selectElements) {
			XMLStatementBuilder	statementBuilder = new XMLStatementBuilder(configuration);
			statementBuilder.parseStatement(selectElement,namespace);
		}
		
	}


}
