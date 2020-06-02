package com.kkb.springmvc.handleradapter.iface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编写处理器适配器接口 这个接口也是适配器模式中的目标接口角色
 * 
 * @author 灭霸詹
 *
 */
public interface HandlerAdapter {
	
	//提供一个适配方法
	boolean supports(Object handler);

	void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
