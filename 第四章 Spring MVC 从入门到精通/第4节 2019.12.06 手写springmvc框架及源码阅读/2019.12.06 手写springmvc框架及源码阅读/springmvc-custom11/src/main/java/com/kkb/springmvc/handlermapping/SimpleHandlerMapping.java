package com.kkb.springmvc.handlermapping;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kkb.springmvc.handler.AddUserHandler;
import com.kkb.springmvc.handler.QueryUserHandler;
import com.kkb.springmvc.handlermapping.iface.HandlerMapping;

public class SimpleHandlerMapping implements HandlerMapping {

	private Map<String, Object> urlHandlers = new HashMap<String, Object>();

	public void init() {
		// TODO
		urlHandlers.put("/queryUser", new QueryUserHandler());
		urlHandlers.put("/addUser", new AddUserHandler());
	}

	@Override
	public Object getHandler(HttpServletRequest request) {
		String uri = request.getRequestURI();
		Object handler = urlHandlers.get(uri);
		return handler;
	}

}
