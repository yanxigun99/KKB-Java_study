package com.kkb.spring.bean.factory.support;

import com.kkb.spring.bean.factory.BeanFactory;

/**
 * 定义bean的获取流程
 * @author 灭霸詹
 *
 */
public abstract class AbstractBeanFactory implements BeanFactory{

	@Override
	public Object getBean(String name) {
		// TODO 会调用AbstractAutowireCapableBeanFactory的createBean功能
		return null;
	}
}
