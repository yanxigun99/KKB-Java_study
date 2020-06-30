package com.kkb.springmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kkb.springmvc.handler.iface.HttpRequestHandler;

public class AddUserHandler implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain;charset=utf8");
		response.getWriter().write("添加成功AddUserHandler");
	}

}
