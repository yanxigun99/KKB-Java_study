package com.kkb.spring.bean.factory.registry.support;

import java.util.HashMap;
import java.util.Map;

import com.kkb.spring.bean.factory.registry.SingletonBeanRegistry;

/**
 * 
 * @author 灭霸詹
 *
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	/**
	 * 专门存储单例bean实例的集合
	 */
	private Map<String, Object> singletons = new HashMap<String, Object>();

	@Override
	public Object getSingleton(String name) {
		return singletons.get(name);
	}

	@Override
	public void addSingleton(String name, Object bean) {
		this.singletons.put(name, bean);
	}

}
