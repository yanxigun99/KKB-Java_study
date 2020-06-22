# [第一天]()

## 脑图

![1592836518(1)](1592836518(1).png)

## 01-AMQP协议模型

![01-AMQP协议模型](09-rabbitmq\01-rabbitmq\RabbitMQ_pic\01-AMQP协议模型.png)

## 02-RabbitMQ架构图

![02-RabbitMQ架构图](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\01-rabbitmq\RabbitMQ_pic\02-RabbitMQ架构图.png)

## 03-RabbitMQ消息流转

![03-RabbitMQ消息流转](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\01-rabbitmq\RabbitMQ_pic\03-RabbitMQ消息流转.png)

## demo

### Consumer

```java
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

```

```java
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

```

### Producer

```java
package com.kkb.FastDFS_DEMO;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQProducer {

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

        // 声明一个交换机
        channel.exchangeDeclare("cc", BuiltinExchangeType.DIRECT,true);

        // 4. 通过 Channel 发送消息
        String msg = "cuihuaaaa";
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("cc", "hello", null, msg.getBytes());
            System.out.println(i);
            Thread.sleep(1000);
        }

//        // 5. 关闭资源
//        channel.close();
//        conn.close();


    }

}

```

```java
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

```

# 第二天

## 脑图

![1592837051(1)](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\02-rabbitmq\RabbitMQ 2019-7-6\1592837051(1).png)

## 01-交换机

![01-交换机](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\02-rabbitmq\RabbitMQ 2019-7-6\01-交换机.png)

## 02-Direct Exchange

![02-Direct Exchange](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\02-rabbitmq\RabbitMQ 2019-7-6\02-Direct Exchange.png)

## 03-Topic Exchange

![03-Topic Exchange](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\02-rabbitmq\RabbitMQ 2019-7-6\03-Topic Exchange.png)

## 04-Fanout Exchange

![04-Fanout Exchange](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\02-rabbitmq\RabbitMQ 2019-7-6\04-Fanout Exchange.png)

## 05-消息落库

![05-消息落库](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\02-rabbitmq\RabbitMQ 2019-7-6\05-消息落库.png)

## 06-消息延迟投递

![06-消息延迟投递](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\02-rabbitmq\RabbitMQ 2019-7-6\06-消息延迟投递.png)

## demo

### FastDFS_DEMO

```java
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.net.URLDecoder;

/**
 * Created by apple on 2019/5/8.
 */
public class FastDFSClient {

    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient1 client = null;
    // fdfsclient的配置文件的路径
    private static String CONF_NAME = "/fast_dfs.conf";

    static {
        try {
            // 配置文件必须指定全路径
            String confName = FastDFSClient.class.getResource(CONF_NAME).getPath();
            // 配置文件全路径中如果有中文，需要进行utf8转码
            confName = URLDecoder.decode(confName, "utf8");

            ClientGlobal.init(confName);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = null;
            client = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 上传文件方法
     * <p>
     * Title: uploadFile
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param fileName
     *            文件全路径
     * @param extName
     *            文件扩展名，不包含（.）
     * @param metas
     *            文件扩展信息
     * @return
     * @throws Exception
     */
    public static String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = client.upload_file1(fileName, extName, metas);
        return result;
    }

    public static void main(String[] args) {
        try {
            String uploadFile = uploadFile("C:/Users/hp/Desktop/mieba.jpg", "jpg", null);
            System.out.println("upload:"+uploadFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("upload file to FastDFS failed.");
        }
    }

}

```

```java
package com.kkb.FastDFS_DEMO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastDfsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastDfsDemoApplication.class, args);
	}

}

```



### rabbitMQ_exchange

#### direct

```JAVA
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

```

```JAVA
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

```



#### fanout

```JAVA
package com.kkb.rabbitMQ_exchange.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Fanout_Consumer {

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

        // 5. 声明&绑定队列
        String queueName = channel.queueDeclare().getQueue();
        // 路由键为空
        String routingKey = "abcsbssb";
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

```

```JAVA
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

```



#### topic

```JAVA
package com.kkb.rabbitMQ_exchange.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Topic_Consumer {

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

        // 5. 声明&绑定队列
        String queueName = channel.queueDeclare().getQueue();
        // 使用通配符进行设置
        String routingKey = "hello.*";
        // String routingKey = "hello.#";
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

```

```JAVA
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

```



### rabbitmq_message

```JAVA
package com.kkb.rabbitmq_message;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Message_Consumer {

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

```

```JAVA
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

```



### spring_rabbitmq

```JAVA
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

```

```JAVA
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

```

# 第三天

## 脑图

![1592837952(1)](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\03-rabbitmq\1592837952(1).png)

## SET化架构

![SET化架构](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\03-rabbitmq\pic\SET化架构.png)

## 阿里两地三中心

![阿里两地三中心](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\03-rabbitmq\pic\阿里两地三中心.png)

## 两地三中心

![两地三中心](G:\java\github\第二十二章 RabbitMQ（录播）\09-rabbitmq\03-rabbitmq\pic\两地三中心.png)