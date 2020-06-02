package com.kkb.controller;

import com.kkb.pojo.User;
import com.kkb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{name}")
    public String sayHello(@PathVariable String name) throws Exception {

        // System.out.println("接收到的名字：" + name);

        User user = userService.findUserByName(name);

        return "Hello..."+ user.getName() +" 你好呀！";

    }

    @GetMapping("/findEmail")
    public String findEmail() throws Exception {

        String email = userService.findEmail();

        return email;

    }
}
