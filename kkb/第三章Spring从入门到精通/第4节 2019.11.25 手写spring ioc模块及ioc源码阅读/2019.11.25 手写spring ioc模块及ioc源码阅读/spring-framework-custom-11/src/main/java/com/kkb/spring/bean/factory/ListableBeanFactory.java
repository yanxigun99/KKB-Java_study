package com.kkb.spring.bean.factory;

import java.util.List;

/**
 * 可以将工厂中的bean或者名称进行列表话展示
 * 
 * @author 灭霸詹
 *
 */
public interface ListableBeanFactory extends BeanFactory{
	
	/**
	 * 根据bean的类型，获取它以及子类型对应的bean实例集合
	 * @param type
	 * @return
	 */
	List<Object> getBeansByType(Class<?> type);
	
	/**
	 * 根据bean的类型，获取它以及子类型对应的bean的名称集合
	 * @param type
	 * @return
	 */
	List<String> getBeanNamesByType(Class<?> type);
}
