package com.kkb.spring.bean.xml;

import java.util.List;

import org.dom4j.Element;

import com.kkb.spring.bean.definition.BeanDefinition;
import com.kkb.spring.bean.definition.PropertyValue;
import com.kkb.spring.bean.definition.RuntimeBeanReference;
import com.kkb.spring.bean.definition.TypedStringValue;
import com.kkb.spring.bean.factory.registry.BeanDefinitionRegistry;
import com.kkb.spring.bean.utils.ReflectUtils;

/**
 * 作用：就是根据bean标签的语义去解析Document对象
 * 
 * @author 灭霸詹
 *
 */
public class XmlBeanDefinitionDocumentReader {

	private BeanDefinitionRegistry beanDefinitionRegistry;

	public XmlBeanDefinitionDocumentReader(BeanDefinitionRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
	}

	@SuppressWarnings("unchecked")
	public void loadBeanDefinitions(Element rootElement) {
		// 获取<bean>和自定义标签（比如mvc:interceptors）
		List<Element> elements = rootElement.elements();
		for (Element element : elements) {
			// 获取标签名称
			String name = element.getName();
			if (name.equals("bean")) {
				// 解析默认标签，其实也就是bean标签
				parseDefaultElement(element);
			} else {
				// 解析自定义标签，比如mvc:interceptors标签回去
				parseCustomElement(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void parseDefaultElement(Element beanElement) {
		try {
			if (beanElement == null)
				return;
			// 获取id属性
			String id = beanElement.attributeValue("id");

			// 获取name属性
			String name = beanElement.attributeValue("name");
			// 获取class属性
			String clazzName = beanElement.attributeValue("class");
			if (clazzName == null || "".equals(clazzName)) {
				return;
			}
			Class<?> clazzType = Class.forName(clazzName);

			// 获取init-method属性
			String initMethod = beanElement.attributeValue("init-method");
			// 获取scope属性
			String scope = beanElement.attributeValue("scope");
			scope = scope != null && !scope.equals("") ? scope : "singleton";

			String beanName = id == null ? name : id;
			beanName = beanName == null ? clazzType.getSimpleName() : beanName;
			// 创建BeanDefinition对象
			// 此次可以使用构建者模式进行优化
			BeanDefinition beanDefinition = new BeanDefinition(clazzName, beanName);
			beanDefinition.setInitMethod(initMethod);
			beanDefinition.setScope(scope);
			// 获取property子标签集合
			List<Element> propertyElements = beanElement.elements();
			for (Element propertyElement : propertyElements) {
				parsePropertyElement(beanDefinition, propertyElement);
			}

			// 注册BeanDefinition信息
			this.beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void parsePropertyElement(BeanDefinition beanDefination, Element propertyElement) {
		if (propertyElement == null)
			return;

		// 获取name属性
		String name = propertyElement.attributeValue("name");
		// 获取value属性
		String value = propertyElement.attributeValue("value");
		// 获取ref属性
		String ref = propertyElement.attributeValue("ref");

		// 如果value和ref都有值，则返回
		if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
			return;
		}

		/**
		 * PropertyValue就封装着一个property标签的信息
		 */
		PropertyValue pv = null;

		if (value != null && !value.equals("")) {
			// 因为spring配置文件中的value是String类型，而对象中的属性值是各种各样的，所以需要存储类型
			TypedStringValue typeStringValue = new TypedStringValue(value);

			Class<?> targetType = ReflectUtils.getTypeByFieldName(beanDefination.getClazzName(), name);
			typeStringValue.setTargetType(targetType);

			pv = new PropertyValue(name, typeStringValue);
			beanDefination.addPropertyValue(pv);
		} else if (ref != null && !ref.equals("")) {

			RuntimeBeanReference reference = new RuntimeBeanReference(ref);
			pv = new PropertyValue(name, reference);
			beanDefination.addPropertyValue(pv);
		} else {
			return;
		}
	}

	private void parseCustomElement(Element element) {

	}
}
