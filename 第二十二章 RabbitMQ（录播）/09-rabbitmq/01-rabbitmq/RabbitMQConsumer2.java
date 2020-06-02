package com.kkb.FastDFS_DEMO;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitMQConsumer2 {

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

        // 4. 声明队列
        String queueName = channel.queueDeclare().getQueue();
        String routingKey = "msg02";
        channel.queueBind(queueName, exchangeName, routingKey);

        // 5. 消费消息
        while (true){

            boolean autoAck = false;
            String consmerTag = "";
            channel.basicConsume(queueName, autoAck, consmerTag, new DefaultConsumer(channel){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // super.handleDelivery(consumerTag, envelope, properties, body);

                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey + " 消费的内容类型：" + contentType);

                    long deliveryTag = envelope.getDeliveryTag();
                    // 确认消息
                    channel.basicAck(deliveryTag, false);

                    System.out.println("消费的消息体：");
                    String bodyMsg = new String(body, "UTF-8");
                    System.out.println(bodyMsg);

                }
            });
        }


    }
}
