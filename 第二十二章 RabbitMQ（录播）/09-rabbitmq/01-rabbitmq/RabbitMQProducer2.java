package com.kkb.FastDFS_DEMO;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitMQProducer2 {

    public static void main(String[] args) throws Exception {

        // 1. 创建 ConnetionFactory 连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //factory.setUsername("guest");
        //factory.setPassword("guest");
        factory.setHost("192.168.86.53");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        // 2. 获取 Connection 连接对象
        Connection connection = factory.newConnection();

        // 3. 创建 Channel 信道
        Channel channel = connection.createChannel();

        // 声明交换机
        String exchangeName = "aa";
        channel.exchangeDeclare(exchangeName, "direct", true);

        // 4. 发布消息
        String msg = "hello, cuihua...";
        String routingKey = "msg02";

        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        // 5. 关闭资源
        //channel.close();
       // connection.close();

    }

}
