package com.kkb.spring.bean.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.kkb.spring.bean.definition.BeanDefinition;
import com.kkb.spring.bean.factory.registry.BeanDefinitionRegistry;

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

	@Override
	public BeanDefinition getBeanDefinition(String name) {
		return this.beanDefinitions.get(name);
	}

	@Override
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		this.beanDefinitions.put(name, beanDefinition);
	}

}
