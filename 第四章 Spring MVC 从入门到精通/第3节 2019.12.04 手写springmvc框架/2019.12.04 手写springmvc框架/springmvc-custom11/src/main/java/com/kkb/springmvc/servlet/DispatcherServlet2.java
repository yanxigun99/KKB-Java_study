package com.kkb.springmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kkb.springmvc.handler.AddUserHandler;
import com.kkb.springmvc.handler.QueryUserHandler;
import com.kkb.springmvc.handler.iface.HttpRequestHandler;
import com.kkb.springmvc.handleradapter.HttpRequestHandlerAdapter;
import com.kkb.springmvc.handleradapter.iface.HandlerAdapter;

/**
 * 前端控制器：接收请求、分发请求、响应结果
 * 
 * @author 灭霸詹
 *
 */
public class DispatcherServlet2 extends AbstractServlet {
	private static final long serialVersionUID = 1L;

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
		if (handler instanceof HttpRequestHandler) {
			return new HttpRequestHandlerAdapter();
		}
		// else if.....
		return null;
	}

	/**
	 * 查找处理器
	 * 
	 * @param request
	 * @return
	 */
	private Object getHandler(HttpServletRequest request) {
		String uri = request.getRequestURI();

		// 抽象思维：将查找处理器的工作抽象到一个类中HandlerMapping类

		// 策略模式（主要就是为了解决if太多，不容易扩展的问题）
		// 方式1：直接写if判断（这种方式不可取）

		Object handler = getHandler1(uri);
		if (handler != null) {
			return handler;
		}

		// 方式2：讲uri和处理器类的映射关系，配置到xml文件中 // 配置文件的写法<bean name="uri" class="处理器类">标签
		handler = getHandler2(uri);
		if (handler != null) {
			return handler;
		}

		// ..........

		return null;
	}

	private Object getHandler2(String uri) {
		// 取出BeanDefinition对象中的beanName进行处理，如果是uri则获取该BeanDefinition对应的实例
		// 建立map集合中的映射关系key：uri value：处理器的实例
		return null;
	}

	private Object getHandler1(String uri) {
		if ("/queryUser".equals(uri)) {
			return new QueryUserHandler();
		} else if ("/addUser".equals(uri)) {
			return new AddUserHandler();
		}
		return null;
	}

}
