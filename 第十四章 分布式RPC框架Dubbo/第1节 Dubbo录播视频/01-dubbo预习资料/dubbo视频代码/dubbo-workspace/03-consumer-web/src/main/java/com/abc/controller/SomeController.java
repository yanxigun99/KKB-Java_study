package com.abc.controller;

import com.abc.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SomeController {
    @Autowired
    private SomeService service;

    @RequestMapping("/some.do")
    public String someHandle() {
        String result = service.hello("China");
        System.out.println("消费者端接收到 = " +  result);
        return "/welcome.jsp";
    }
}


