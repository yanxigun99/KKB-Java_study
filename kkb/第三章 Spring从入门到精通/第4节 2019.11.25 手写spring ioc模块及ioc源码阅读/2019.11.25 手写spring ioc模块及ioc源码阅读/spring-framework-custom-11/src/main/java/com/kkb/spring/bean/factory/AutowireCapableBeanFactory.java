package com.kkb.spring.bean.factory;

/**
 * 具备对bean实例进行装配功能的工厂
 * 
 * @author 灭霸詹
 *
 */
public interface AutowireCapableBeanFactory extends BeanFactory{

	/**
	 * 创建bean实例的功能
	 * 
	 * @param beanDefinition
	 * @return
	 */
	Object createBean(Class<?> type);
}
