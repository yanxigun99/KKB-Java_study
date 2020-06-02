package com.kkb.springmvc.servlet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kkb.spring.bean.factory.support.DefaultListableBeanFactory;
import com.kkb.spring.bean.resource.ClasspathResource;
import com.kkb.spring.bean.resource.Resource;
import com.kkb.spring.bean.xml.XmlBeanDefinitionReader;
import com.kkb.springmvc.handleradapter.iface.HandlerAdapter;
import com.kkb.springmvc.handlermapping.iface.HandlerMapping;

/**
 * 前端控制器：接收请求、分发请求、响应结果
 * 
 * @author 灭霸詹
 *
 */
public class DispatcherServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

	private List<HandlerMapping> handlerMappings = new ArrayList<>();
	private List<HandlerAdapter> handlerAdapters = new ArrayList<>();

	private DefaultListableBeanFactory beanFactory;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String location = config.getInitParameter(CONTEXT_CONFIG_LOCATION);
		initSpringContainer(location);
		initHandlerMappings();
		initHandlerAdapters();
	}

	private void initSpringContainer(String location) {
		// 创建BeanFactory
		beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		Resource resource = new ClasspathResource(location);
		InputStream inputStream = resource.getResource();
		// 加载BeanDefinition
		reader.loadBeanDefinitions(inputStream);
	}

	private void initHandlerAdapters() {
		// TODO
		// handlerAdapters.add(new HttpRequestHandlerAdapter());
		handlerAdapters = beanFactory.getBeansByType(HandlerAdapter.class);
	}

	private void initHandlerMappings() {

		// TODO
		// handlerMappings.add(new SimpleHandlerMapping());
		// handlerMappings.add(new BeanNameUrlHandlerMapping());
		handlerMappings = beanFactory.getBeansByType(HandlerMapping.class);
	}

	/**
	 * 分发请求
	 */
	public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 根据请求查找处理器
		Object handler = getHandler(request);
		if (handler == null) {
			return;
		}
		// 根据处理器查找对应的处理器适配器
		HandlerAdapter ha = getHandlerAdapter(handler);
		if (ha == null) {
			return;
		}
		// 统一调用不同类型的处理器
		ha.handleRequest(handler, request, response);
	}

	/**
	 * 根据处理器查找对应的处理器适配器
	 * 
	 * @param handler
	 * @return
	 */
	private HandlerAdapter getHandlerAdapter(Object handler) {
		if (handlerAdapters != null) {
			for (HandlerAdapter ha : handlerAdapters) {
				if (ha.supports(handler)) {
					return ha;
				}
			}
		}
		return null;
	}

	/**
	 * 查找处理器
	 * 
	 * @param request
	 * @return
	 */
	private Object getHandler(HttpServletRequest request) {

		// 此处使用到了策略模式完成了优化
		if (handlerMappings != null) {
			for (HandlerMapping handlerMapping : handlerMappings) {
				Object handler = handlerMapping.getHandler(request);
				if (handler != null) {
					return handler;
				}
			}
		}
		return null;
	}

}
