package com.kkb.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import com.kkb.springmvc.annotation.Controller;
import com.kkb.springmvc.annotation.RequestMapping;
import com.kkb.springmvc.annotation.ResponseBody;
/***
 * 注意：一个请求对应一个Handler
 * 一个Controller包含多个请求对应的方法，所以说Controller不是Handler类
 * 真正的Handler其实指的是【Controller类和指定请求URL对应的方法】
 * 我们使用封装的类去表示注解方式下的Handler，这个类就叫HandlerMethod（Controller类、Method方法）
 * 总结：在注解开发方式下，一个请求对应一个HandlerMethod(其实就是Handler类)
 * @author 灭霸詹
 *192.168.122.1
 */
@Controller
public class UserController {

	@RequestMapping("queryUser3")
	@ResponseBody
	public Map<String, Object> query(Integer id, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		return map;
	}

	@RequestMapping("saveUser3")
	@ResponseBody
	public String save() {
		return "添加成功";
	}
}
