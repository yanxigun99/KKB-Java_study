package com.kkb.spring.bean.factory.support;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.kkb.spring.bean.definition.BeanDefinition;
import com.kkb.spring.bean.factory.registry.BeanDefinitionRegistry;
import com.kkb.spring.bean.resource.ClasspathResource;
import com.kkb.spring.bean.resource.Resource;
import com.kkb.spring.bean.xml.XmlBeanDefinitionReader;

/**
 * Spring的最底层的工厂类 由它使用Bean的管理
 * 
 * @author 灭霸詹
 *
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

	/**
	 * 以beanname为key，以BeanDefinition对象为value的存储集合
	 */
	private Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

	public DefaultListableBeanFactory(String location) {
		// 将资源抽象为一个接口，通过该接口，可以获取不同地方（网络、文件系统、classpath）的资源
		Resource resource = new ClasspathResource(location);
		InputStream inputStream = resource.getResource();

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(this);

		beanDefinitionReader.loadBeanDefinitions(inputStream);
	}

	@Override
	public BeanDefinition getBeanDefinition(String name) {
		return this.beanDefinitions.get(name);
	}

	@Override
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		this.beanDefinitions.put(name, beanDefinition);
	}

}
