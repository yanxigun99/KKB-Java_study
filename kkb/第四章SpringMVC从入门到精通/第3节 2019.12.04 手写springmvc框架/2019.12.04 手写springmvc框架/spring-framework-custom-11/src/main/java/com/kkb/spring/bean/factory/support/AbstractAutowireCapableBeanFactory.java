package com.kkb.spring.bean.factory.support;

import java.util.List;

import com.kkb.spring.bean.aware.Aware;
import com.kkb.spring.bean.aware.BeanFactoryAware;
import com.kkb.spring.bean.definition.BeanDefinition;
import com.kkb.spring.bean.definition.PropertyValue;
import com.kkb.spring.bean.definition.RuntimeBeanReference;
import com.kkb.spring.bean.definition.TypedStringValue;
import com.kkb.spring.bean.factory.AutowireCapableBeanFactory;
import com.kkb.spring.bean.utils.ReflectUtils;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
		implements AutowireCapableBeanFactory {

	@Override
	public Object createBean(BeanDefinition bd) {
		String clazzName = bd.getClazzName();
		Class<?> clazz = resolveClassName(clazzName);
		if (clazz == null) {
			return null;
		}
		// 实例化bean
		// 注意：此时只是new了一个空对象
		Object sinleton = createBeanInstance(clazz);
		// bean的属性填充
		populateBean(sinleton, bd);

		// 初始化bean
		initBean(sinleton, bd);
		return sinleton;
	}

	private void initBean(Object sinleton, BeanDefinition bd) {
		// TODO 完成Aware接口（标记接口）相关的处理,spring mvc代码会用到
		// 1. aware接口的处理
		if (sinleton instanceof Aware) {
			if (sinleton instanceof BeanFactoryAware) {
				((BeanFactoryAware) sinleton).setBeanFactory(this);
			} // ....
		}
		// TODO BeanPostProcessor的前置方法执行
		initMethod(sinleton, bd);
		// TODO BeanPostProcessor的后置方法执行（AOP代理对象产生的入口）
	}

	private void initMethod(Object sinleton, BeanDefinition bd) {
		// TODO 完成InitializingBean接口（标记接口）的处理，调用的是afterPropertySet方法

		// 完成init-metho标签属性对应的方法调用
		ReflectUtils.invokeMethod(sinleton, bd.getInitMethod());
	}

	private void populateBean(Object sinleton, BeanDefinition bd) {
		List<PropertyValue> propertyValues = bd.getPropertyValues();
		for (PropertyValue propertyValue : propertyValues) {
			// 获取属性名称
			String name = propertyValue.getName();
			// 未处理的value对象
			Object value = propertyValue.getValue();
			// 解决之后的value值
			Object valueToUse = null;

			if (value instanceof TypedStringValue) {
				TypedStringValue typedStringValue = (TypedStringValue) value;
				String stringValue = typedStringValue.getValue();
				// 获取参数的类型
				Class<?> targetType = typedStringValue.getTargetType();

				// TODO 建议使用策略模式进行优化
				if (targetType == Integer.class) {
					valueToUse = Integer.parseInt(stringValue);
				} else if (targetType == String.class) {
					valueToUse = stringValue;
				} else {
					// ....
				}

			} else if (value instanceof RuntimeBeanReference) {
				RuntimeBeanReference reference = (RuntimeBeanReference) value;

				// 递归获取指定名称的bean实例
				// TODO 此处可能会发送循环依赖问题
				valueToUse = getBean(reference.getRef());
			} else {
				// ....
			}

			// 利用反射去设置bean的属性
			ReflectUtils.setProperty(sinleton, name, valueToUse);
		}
	}

	private Object createBeanInstance(Class<?> clazz) {
		// TODO 可以根据bean标签的配置选择使用实例工厂去创建Bean
		// TODO 可以根据bean标签的配置选择使用静态工厂去创建Bean

		// 还可以选择使用我们的构造方法去创建Bean
		return ReflectUtils.createObject(clazz);
	}

	private Class<?> resolveClassName(String clazzName) {
		try {
			return Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过AutowireCapableBeanFactory接口对外提供的功能
	 */
	@Override
	public Object createBean(Class<?> type) {
		return createBean(new BeanDefinition(null, null));
	}

}
