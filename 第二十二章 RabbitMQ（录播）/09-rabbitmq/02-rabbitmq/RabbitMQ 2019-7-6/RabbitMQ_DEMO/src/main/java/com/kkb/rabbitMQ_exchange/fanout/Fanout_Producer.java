package com.kkb.rabbitMQ_exchange.fanout;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class Fanout_Producer {

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
        String exchangeName = "fanout_exchange";
        String exchangeType = "fanout";
        channel.exchangeDeclare(exchangeName, exchangeType, true);

        // 5. 发布消息
        for (int i = 0; i < 10; i++) {

            String msg = "hello, fanout_exchange" + i;
            // 不设置路由键，或者随便设置
            String routingKey = "";

            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        }

        // 6. 关闭资源
        //channel.close();
        //connection.close();

    }

}
