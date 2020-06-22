package com.kkb.rabbitMQ_exchange.topic;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Topic_Producer {

    public static void main(String[] args) throws Exception {

        // 1. 创建 ConnetionFactory 连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.86.53");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        // 2. 获取 Connection 连接对象
        Connection connection = factory.newConnection();

        // 3. 创建 Channel 信道
        Channel channel = connection.createChannel();

        // 4. 声明交换机
        String exchangeName = "topic_exchange";
        String exchangeType = "topic";
        channel.exchangeDeclare(exchangeName, exchangeType, true);

        // 5. 发布消息
        String msg = "hello, topic_exchange";
        String routingKey1 = "hello.cuihua";
        String routingKey2 = "hello.cuicui";
        String routingKey3 = "hello.huahua.didi";

        channel.basicPublish(exchangeName, routingKey1, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKey2, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKey3, null, msg.getBytes());

        // 6. 关闭资源
        //channel.close();
        //connection.close();

    }

}
