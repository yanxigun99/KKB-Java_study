package com.kkb.spring.bean.factory.support;

import com.kkb.spring.bean.definition.BeanDefinition;
import com.kkb.spring.bean.factory.BeanFactory;
import com.kkb.spring.bean.factory.registry.support.DefaultSingletonBeanRegistry;

/**
 * 定义bean的获取流程
 * 
 * @author 灭霸詹
 *
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

	/**
	 * 指定获取bean的流程
	 */
	@Override
	public Object getBean(String name) {
		// 从缓存中获取要找的对象
		Object singleton = getSingleton(name);
		if (singleton != null) {
			return singleton;
		}
		// 找不到，则获取指定名称的BeanDefinition对象
		// 此处使用到的就是抽象模板方法，我此处只定流程，不去实现，我也不懂如何实现，这不是我干的事情
		BeanDefinition bd = getBeanDefinition(name);

		// 根据BeanDefinition中的信息，判断是单例还是多例（原型）
		if (bd.isSingleton()) { // 单例
			// 根据BeanDefinition对象，完成bean的创建
			singleton = createBean(bd);
			// 缓存已经创建的单例bean实例
			addSingleton(name, singleton);
		} else if (bd.isPrototype()) {// 原型
			// 根据BeanDefinition对象，完成bean的创建
			singleton = createBean(bd);

		}
		return singleton;
	}

	public abstract Object createBean(BeanDefinition bd);

	/**
	 * 如何获取BeanDefinition，交给子类去完成
	 * 
	 * @param name
	 * @return
	 */
	public abstract BeanDefinition getBeanDefinition(String name);
}
