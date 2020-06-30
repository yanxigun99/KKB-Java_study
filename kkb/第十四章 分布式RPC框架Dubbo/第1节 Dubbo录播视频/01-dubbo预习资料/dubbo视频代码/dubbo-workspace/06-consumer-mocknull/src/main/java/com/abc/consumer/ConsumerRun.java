package com.abc.consumer;

import com.abc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerRun {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-consumer.xml");
        UserService service = (UserService) ac.getBean("userService");

        // 对于有返回值的方法，其返回结果为null
        String username = service.getUsernameById(3);
        System.out.println("username = " + username);
        // 对于没有返回值的方法，其没有任何结果
        service.addUser("China");
    }
}
