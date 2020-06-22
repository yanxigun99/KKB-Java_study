package com.kkb.rabbitmq_message;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;


public class Message_Producer {

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

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("msg1", "cuihua");
        headers.put("msg2", "huahua");

        // 添加额外的属性信息
        AMQP.BasicProperties prop = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 持久化消息
                .contentEncoding("UTF-8")
                .expiration("10000") // 过期时间，会被自动清除
                .headers(headers) // 自定义属性
                .build();

        for (int i = 0; i < 6; i++) {
            String msg = "hello, direct_exchange" + i;
            // String routingKey = "msg_direct" + i;

            channel.basicPublish(exchangeName, "msg_direct", prop, msg.getBytes());
        }


        // 6. 关闭资源
        // channel.close();
        // connection.close();

    }

}
