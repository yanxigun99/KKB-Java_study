package com.kkb.spring.bean.factory;

/**
 * 顶级接口 负责bean实例的获取
 * 
 * @author 灭霸詹
 *
 */
public interface BeanFactory {

	/**
	 * 根据bean的名称获取bean的实例
	 * 
	 * @param name
	 * @return
	 */
	Object getBean(String name);
}
