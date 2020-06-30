package com.kkb.spring.bean.factory.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kkb.spring.bean.definition.BeanDefinition;
import com.kkb.spring.bean.factory.ListableBeanFactory;
import com.kkb.spring.bean.factory.registry.BeanDefinitionRegistry;

/**
 * Spring的最底层的工厂类 由它使用Bean的管理
 * 
 * @author 灭霸詹
 *
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
		implements BeanDefinitionRegistry, ListableBeanFactory {

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

	/**
	 * 查找指定类型和子类型对应的实例集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getBeansByType(Class<?> type) {
		List<T> beans = new ArrayList<T>();
		try {
			for (BeanDefinition bd : beanDefinitions.values()) {
				String clazzName = bd.getClazzName();
				// 获取BeanDefinition对应的class的类对象
				Class<?> clazz = Class.forName(clazzName);
				// 如果type是clazz的父类或者是当前类，则返回true
				if (type.isAssignableFrom(clazz)) {
					Object bean = getBean(bd.getBeanName());
					beans.add((T) bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beans;
	}

	@Override
	public <T> List<T> getBeanNamesByType(Class<?> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BeanDefinition> getBeanDefinitions() {
		Collection<BeanDefinition> values = beanDefinitions.values();
		List<BeanDefinition> definitions = new ArrayList<>();
		for (BeanDefinition beanDefinition : values) {
			definitions.add(beanDefinition);
		}
		return definitions;
	}

}
