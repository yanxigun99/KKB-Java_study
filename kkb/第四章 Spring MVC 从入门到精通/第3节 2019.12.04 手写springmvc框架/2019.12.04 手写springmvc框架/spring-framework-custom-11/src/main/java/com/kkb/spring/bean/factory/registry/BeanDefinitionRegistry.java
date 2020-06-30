package com.kkb.spring.bean.factory.registry;

import java.util.List;

import com.kkb.spring.bean.definition.BeanDefinition;

/**
 * 管理BeanDefinition的注册
 * 
 * @author 灭霸詹
 *
 */
public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String name);

	List<BeanDefinition> getBeanDefinitions();

	void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
