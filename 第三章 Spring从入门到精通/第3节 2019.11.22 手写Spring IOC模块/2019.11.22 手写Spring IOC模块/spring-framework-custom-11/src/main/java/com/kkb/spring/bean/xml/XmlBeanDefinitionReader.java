package com.kkb.spring.bean.xml;

import java.io.InputStream;

import org.dom4j.Document;

import com.kkb.spring.bean.factory.registry.BeanDefinitionRegistry;
import com.kkb.spring.bean.utils.DocumentReader;

/**
 * 对XML文件进行加载，然后获取BeanDefinition信息
 * 
 * @author 灭霸詹
 *
 */
public class XmlBeanDefinitionReader {

	private BeanDefinitionRegistry beanDefinitionRegistry;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
	}

	public void loadBeanDefinitions(InputStream inputStream) {
		Document document = DocumentReader.createDocument(inputStream);

		XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
		documentReader.loadBeanDefinitions(document.getRootElement());
	}
}
