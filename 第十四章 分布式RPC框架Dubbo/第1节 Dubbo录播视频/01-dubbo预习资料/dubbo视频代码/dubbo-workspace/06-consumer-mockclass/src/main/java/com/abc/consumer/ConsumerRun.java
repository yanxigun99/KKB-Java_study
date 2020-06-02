package com.abc.consumer;

import com.abc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerRun {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-consumer.xml");
        UserService service = (UserService) ac.getBean("userService");

        // 无论是否有返回值，其结果均可进行自定义
        String username = service.getUsernameById(3);
        System.out.println("username = " + username);
        service.addUser("China");
    }
}
