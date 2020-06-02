package com.kkb.FastDFS_DEMO;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RabbitMQConsumer {


    public static void main(String[] args) throws Exception{

        // 1. 创建一个 ConnectionFactory 工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.86.53");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        // 2. 创建一个 Connection
        Connection conn = factory.newConnection();

        // 3. 获取一个信道 Channel
        Channel channel = conn.createChannel();


        // 4. 声明一个队列
        // 参数1：队列名
        // 参数2：指明是否消息是否要持久化到队列上，关机也不会丢
        // 参数3：是否独占，类似加了一把锁，只有一个 Channel 能监听，保证了 消费顺序
        // 参数4：是否自动删除，如果队列跟交换机没有绑定关系了，是否自动删除
        // 参数5：扩展参数
        String queueName = "xiao";
        // 交换机声明
        channel.exchangeDeclare("cc", BuiltinExchangeType.DIRECT,true);
        // 队列声明
        channel.queueDeclare(queueName, true , false, false, null);
        // 队列绑定
        channel.queueBind(queueName,"cc","hello");

        // 异步获取投递消息
        // 就相当于根据 路由key 获取 信封中的数据
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
                System.out.println(message);
            } finally {
                System.out.println(" [x] Done");
            }
        };
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> { });

        System.in.read();

    }

}
