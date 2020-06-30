package com.abc.consumer;

import com.abc.service.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerRun2 {
    public static void main(String[] args) {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("spring-consumer.xml");
        SomeService service = (SomeService) ac.getBean("someService");

        for (int i=1; i<=1000; i++) {
            service.hello("i==" + i);
        }
        // 调用提供者，并将其执行结果存放到缓存，根据
        // LRU（最近最少使用）策略，其会将i==1的缓存结果挤出去
        System.out.println(service.hello("Tom"));
        // 由于缓存中已经没有了i==1的缓存，所以其会调用提供者，
        // 并将其执行结果存放到缓存，根据LRU（最近最少使用）策略，其会将i==2的缓存结果挤出去
        System.out.println(service.hello("i==1"));
        // 缓存中存在i==3的内容，所以其不会调用提供者
        System.out.println(service.hello("i==3"));
    }
}
