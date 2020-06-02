package com.kkb.mybatis.config;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import com.kkb.mybatis.utils.DocumentUtils;

/**
 * 专门用来解析全局配置文件的类
 * 
 * @author 灭霸詹
 *
 */
public class XMLConfigBuilder {

	private Configuration configuration;

	public XMLConfigBuilder() {
		this.configuration = new Configuration();
	}

	/**
	 * 从根标签开始解析全局配置文件
	 * 
	 * @param inputStream
	 * @return
	 */
	public Configuration parse(InputStream inputStream) {
		Document document = DocumentUtils.readDocument(inputStream);
		// <configuration>
		Element rootElement = document.getRootElement();
		parserConfiguration(rootElement);
		return configuration;
	}

	/**
	 * 
	 * @param rootElement
	 *            <configuration>
	 */
	private void parserConfiguration(Element rootElement) {
		Element environmentsElement = rootElement.element("environments");
		parseEnvironmentsElement(environmentsElement);

		Element mappersElement = rootElement.element("mappers");
		parseMappersElement(mappersElement);
	}

	private void parseMappersElement(Element mappersElement) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param environmentsElement
	 *            <environments>
	 */
	private void parseEnvironmentsElement(Element environmentsElement) {
		String defaultEnvId = environmentsElement.attributeValue("default");
		if (defaultEnvId == null || defaultEnvId.equals("")) {
			return;
		}
		List<Element> elements = environmentsElement.elements("environment");
		for (Element envElement : elements) {
			String id = envElement.attributeValue("id");
			if (defaultEnvId.equals(id)) {
				parseDataSource(envElement.element("dataSource"));
			}
		}
	}

	/**
	 * 
	 * @param dbElement <dataSource>
	 */
	private void parseDataSource(Element dbElement) {
		String dbType = dbElement.attributeValue("type");
		if ("DBCP".equals(dbType)) {
			BasicDataSource dataSource = new BasicDataSource();
			
			Properties properties = new Properties();
			
			List<Element> propertyElements = dbElement.elements();
			for (Element prop : propertyElements) {
				String name = prop.attributeValue("name");
				String value = prop.attributeValue("value");
				properties.put(name, value);
			}
			
			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
			
			configuration.setDataSource(dataSource);
		}
	}

}
