package com.kkb.springmvc.handler.iface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 制定Handler的编写规范（方式1）
 * @author 灭霸詹
 *
 */
public interface HttpRequestHandler {

	void handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
