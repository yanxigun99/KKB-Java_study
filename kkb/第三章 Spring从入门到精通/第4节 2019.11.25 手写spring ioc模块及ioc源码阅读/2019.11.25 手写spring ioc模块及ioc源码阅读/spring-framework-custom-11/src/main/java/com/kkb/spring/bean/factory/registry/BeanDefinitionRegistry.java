package com.kkb.spring.bean.factory.registry;

import com.kkb.spring.bean.definition.BeanDefinition;

/**
 * 管理BeanDefinition的注册
 * @author 灭霸詹
 *
 */
public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String name);
	
	void registerBeanDefinition(String name,BeanDefinition beanDefinition);
}
