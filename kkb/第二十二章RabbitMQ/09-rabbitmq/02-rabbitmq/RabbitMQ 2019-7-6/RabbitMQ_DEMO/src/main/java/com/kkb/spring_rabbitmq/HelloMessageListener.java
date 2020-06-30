package com.kkb.spring_rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class HelloMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println("接收到的消息：" + msg );
    }
}
