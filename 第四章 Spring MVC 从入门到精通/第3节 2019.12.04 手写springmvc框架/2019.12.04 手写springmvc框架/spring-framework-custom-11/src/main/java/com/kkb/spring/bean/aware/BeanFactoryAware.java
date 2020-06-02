package com.kkb.spring.bean.aware;

import com.kkb.spring.bean.factory.BeanFactory;

/**
 * 谁实现该接口，谁就可以被注入一个BeanFactory实例
 * 
 * @author 灭霸詹
 *
 */
public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory beanFactory);
}
