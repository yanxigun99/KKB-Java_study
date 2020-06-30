package com.kkb.springmvc.handleradapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kkb.springmvc.handler.iface.HttpRequestHandler;
import com.kkb.springmvc.handleradapter.iface.HandlerAdapter;

/**
 * 将HttpRequestHandler适配成HandlerAdapter接口的实现
 * 
 * @author 灭霸詹
 *
 */
public class HttpRequestHandlerAdapter implements HandlerAdapter {

	/**
	 * 适配功能
	 */
	@Override
	public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		((HttpRequestHandler) handler).handleRequest(request, response);
	}

	/**
	 * 集合遍历时，为了去让每个处理器适配器去匹配处理器获取匹配结果
	 */
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof HttpRequestHandler);
	}

}
