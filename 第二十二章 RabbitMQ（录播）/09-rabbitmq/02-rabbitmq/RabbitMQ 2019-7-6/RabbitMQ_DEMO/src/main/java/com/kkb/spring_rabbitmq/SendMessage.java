package com.kkb.spring_rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMessage {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);

        template.convertAndSend("small cuicui..");

        // ctx.close();

    }


}
