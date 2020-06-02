package com.kkb.springmvc.handler;

import java.lang.reflect.Method;

/**
 * 注解方式下真正意义的Handler类
 * 
 * @author 灭霸詹
 *
 */
public class HandlerMethod {

	private Object controller; 
	
	private Method method;

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public HandlerMethod(Object controller, Method method) {
		super();
		this.controller = controller;
		this.method = method;
	}
	
	
}
