package com.kkb.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import com.kkb.springmvc.annotation.Controller;
import com.kkb.springmvc.annotation.RequestMapping;
import com.kkb.springmvc.annotation.ResponseBody;

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
