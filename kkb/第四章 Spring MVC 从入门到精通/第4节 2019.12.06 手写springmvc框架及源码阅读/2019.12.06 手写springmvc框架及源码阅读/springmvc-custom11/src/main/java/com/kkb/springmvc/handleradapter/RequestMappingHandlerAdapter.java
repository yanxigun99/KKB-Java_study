package com.kkb.springmvc.handleradapter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kkb.springmvc.annotation.ResponseBody;
import com.kkb.springmvc.handler.HandlerMethod;
import com.kkb.springmvc.handleradapter.iface.HandlerAdapter;
import com.kkb.springmvc.utils.JsonUtils;

/**
 * 用来将HandlerMethod类型适配成HandlerAdapter类型
 * 
 * @author 灭霸詹
 *
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof HandlerMethod);
	}

	@Override
	public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Object controller = handlerMethod.getController();
		Method method = handlerMethod.getMethod();
		// 处理参数
		Object[] args = resolveParameter(request, method);
		// 反射调用Controller类中的方法
		Object returnValue = method.invoke(controller, args);
		// 处理返回值
		handleReturnValue(returnValue, method, response);
	}

	private void handleReturnValue(Object returnValue, Method method, HttpServletResponse response) throws Exception {
		if (method.isAnnotationPresent(ResponseBody.class)) {
			// TODO 策略模式 针对不同的返回值类型，去提供不同的处理策略
			if (returnValue instanceof String) {
				response.setContentType("text/plain;charset=utf8");
				response.getWriter().write(returnValue.toString());
			} else if (returnValue instanceof Map || returnValue instanceof List) {
				response.setContentType("application/json;charset=utf8");
				response.getWriter().write(JsonUtils.object2Json(returnValue));
			} // ....其他类型
		} else {
			// 视图的处理方式
		}
	}

	/**
	 * 将url中的key-value参数，封装到Controller类中方法的形参类型
	 * 
	 * @param request
	 * @param method
	 * @return
	 */
	private Object[] resolveParameter(HttpServletRequest request, Method method) {
		List<Object> args = new ArrayList<Object>();

		// 获取url中的参数集合
		Map<String, String[]> parameterMap = request.getParameterMap();

		// 获取Controller类中方法的参数集合
		Parameter[] parameters = method.getParameters();

		for (Parameter parameter : parameters) {
			// 此处获取需要获取真正的name值，需要经过特殊处理
			String name = parameter.getName();
			Class<?> type = parameter.getType();

			String[] stringValue = parameterMap.get(name);

			Object valueToUse = convertValue(stringValue, type);

			args.add(valueToUse);
		}

		return args.toArray();
	}

	private Object convertValue(String[] stringValue, Class<?> type) {
		// TODO 建议使用策略模式去优化
		if (type == Integer.class) {
			return Integer.parseInt(stringValue[0]);
		} else if (type == String.class) {
			return stringValue[0];
		} // .....
		return null;
	}

}
