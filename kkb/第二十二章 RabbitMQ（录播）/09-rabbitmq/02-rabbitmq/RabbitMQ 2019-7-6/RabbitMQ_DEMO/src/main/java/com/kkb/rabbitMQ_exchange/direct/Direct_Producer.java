package com.kkb.rabbitMQ_exchange.direct;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Direct_Producer {

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
        String exchangeName = "direct_exchange";
        String exchangeType = "direct";
        channel.exchangeDeclare(exchangeName, exchangeType, true);

        // 5. 发布消息
        String msg = "hello, direct_exchange2";
        String routingKey = "msg_direct";

        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        // 6. 关闭资源
        //channel.close();
        //connection.close();

    }

}
