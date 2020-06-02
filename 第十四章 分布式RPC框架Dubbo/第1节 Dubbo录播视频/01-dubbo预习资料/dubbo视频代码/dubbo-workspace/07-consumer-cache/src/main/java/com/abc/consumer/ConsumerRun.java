package com.abc.consumer;

import com.abc.service.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerRun {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-consumer.xml");
        SomeService service = (SomeService) ac.getBean("someService");

        System.out.println(service.hello("Tom"));
        System.out.println(service.hello("Jerry"));
        System.out.println(service.hello("Tom"));
        System.out.println(service.hello("Jerry"));
    }
}
