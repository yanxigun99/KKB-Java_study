package com.kkb.rabbitMQ_exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Direct_Consumer {

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

        // 5. 声明&绑定队列
        String queueName = channel.queueDeclare().getQueue();
        String routingKey = "msg_direct";
        channel.queueBind(queueName, exchangeName, routingKey);

        // 5. 消费消息
        while (true){

            boolean autoAck = false;
            String consumerTag = "";

            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    // 获取 routingKey & contentType
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的 Routing Key：" + routingKey + " \n消费的 Content Type：" + contentType);

                    // 获取传送标签
                    long deliveryTag = envelope.getDeliveryTag();

                    // 确认消息
                    channel.basicAck(deliveryTag, false);

                    System.out.println("消费的 Body：");
                    String bodyMsg = new String(body, "UTF-8");
                    System.out.println(bodyMsg);
                }
            });
        }
    }
}
