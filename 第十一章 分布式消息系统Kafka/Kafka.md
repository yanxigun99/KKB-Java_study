**分布式消息系统** **Kafka**

**课程讲义**

**主讲：\****Reythor** **雷**

**2019**

**分布式消息系统** **Kafka**

# 第1章 Kafka 概述

## 1.1 kafaka 简介

Apache Kafka 是一个快速、可扩展的、高吞吐的、可容错的分布式“发布-订阅”消息系统， 使用 Scala 与Java 语言编写，能够将消息从一个端点传递到另一个端点，较之传统的消息中间件（例如 ActiveMQ、RabbitMQ），Kafka 具有高吞吐量、内置分区、支持消息副本和高容错的特性，非常适合大规模消息处理应用程序。

Kafka 官网： http://kafka.apache.org/

## 1.2 Kafa 系统架构

- **应用场景**

Kafka 的应用场景很多，这里就举几个最常见的场景。

## 1.3.1 用户的活动追踪

用户在网站的不同活动消息发布到不同的主题中心，然后可以对这些消息进行实时监测

实时处理。当然，也可加载到 Hadoop 或离线处理数据仓库，对用户进行画像。像淘宝、京东这些大型的电商平台，用户的所有活动都是要进行追踪的。

## 1.3.2 日志聚合

- **限流削峰**
- **kafka** **高吞吐率实现**

Kafka 与其它 MQ 相比，其最大的特点就是高吞吐率。为了增加存储能力，Kafka 将所有的消息都写入到了低速大容的硬盘。按理说，这将导致性能损失，但实际上，kafka 仍可保持超高的吞吐率，性能并未受到影响。其主要采用了如下的方式实现了高吞吐率。

- 顺序读写：Kafka 将消息写入到了分区 partition 中，而分区中消息是顺序读写的。顺序读写要远快于随机读写。
- 零拷贝：生产者、消费者对于kafka 中消息的操作是采用零拷贝实现的。
- 批量发送：Kafka 允许使用批量消息发送模式。
- 消息压缩：Kafka 支持对消息集合进行压缩。

# 第2章 Kafka 工作原理与工作过程

## 2.1 Kafka 基本术语

对于Kafka 基本原理的介绍，可以通过对以下基本术语的介绍进行。

## 2.1.1 Topic

主题。在 Kafka 中，使用一个类别属性来划分消息的所属类，划分消息的这个类称为 topic。topic 相当于消息的分类标签，是一个逻辑概念。

## 2.1.2 Partition

分区。topic 中的消息被分割为一个或多个 partition，其是一个物理概念，对应到系统上就是一个或若干个目录。

若一个 Topic 包含多个 partition，消息在写入到 partition 时是无法保证其写入顺序与生产顺序的一致性的。若需要严格保证这个一致性，则 partition 数量设置为 1。

若要使消费者严格按照进入到kafka 中的消息顺序进行消费，则要使 partition 设置为1。

## 2.1.3 segment

段。将 partition 进一步细分为了若干的 segment，每个 segment 文件的最大大小相等。

## 2.1.4 Broker

Kafka 集群包含一个或多个服务器，每个服务器节点称为一个 broker。

假设某个 topic 中有 N 个 partition，集群中有 M 个 broker，则 broker 与 patition 的数量关系是：

- 若 N>=M，且N%M=0，每个 broker 上会平均分配 N/M 个 partition
  - 若 N>M，但 N%M !=0，此时会出现各个 broker 上 partition 数量不平均的情况，此时各个 broker 的消息负载是不均衡的。该情况要避免。
    - 若 N<M，此时会出现某些 broker 上没有 partition，此时各个 broker 的消息负载是不均衡的。该情况要避免。

## 2.1.5 Producer

生产者。即消息的发布者，其会将某 topic 的消息发布到相应的 partition 中。

## 2.1.6 Consumer

消费者。可以从 broker 中读取消息。对于消费者需要注意：

- 一个消费者可以消费多个 topic 的消息
- 一个消费者也可以消费同一个 topic 中的多个 partition 中的消息
- 反过来说也成立，即一个partiton 允许多个无关的消费者（不同组的消费者）同时消费

## 2.1.7 Consumer Group

consumer group 是kafka 提供的可扩展且具有容错性的消费者机制。组内可以有多个消费者，它们共享一个公共的 ID，即 group ID。组内的所有消费者会协调在一起平均消费订阅主题的所有分区。

Kafka 可以保证在稳定状态下，一个 partition 中的消息只能被同一个 consumer group 中的一个consumer 消费，而一个组内 consumer 只会消费某一个或几个特定的 partition。当然， 一个消息可以同时被多个 consumer group 消费。

总结：

组内 consumer 与 partition 的关系是 1:n，partition 与组内 consumer 的关系则是 1:1

也就是说，在稳定状态下，一旦为某组内 consumer 分配了某一个/几个 partition 后，就不会变化了。反过来说，一旦为某 partiton 分配了组内 cosumer，就不会再为其分配其它组内consumer 了。

这种设计方案最大的好处：简单。但也存在不足：组内 consumer 消息的不平均。组中 consumer 数量与 partition 数量的对应关系如下。

## 2.1.8 Replicas of partition

分区副本。副本是一个分区的备份，是为了防止消息丢失而创建的分区的备份。

## 2.1.9 Partition Leader

每个 partition 有多个副本，其中有且仅有一个作为 Leader，Leader 是当前负责消息读写的 partition。即所有读写操作只能发生于 Leader 分区上。

broker controller 负责 partiton leader 的选举。

## 2.1.10 Partition Follower

所有 Follower 都需要从Leader 同步消息，Follower 与Leader 始终保持消息同步。

partition leader 与 follower 是主备关系，非主从关系。

## 2.1.11 ISR

ISR，In-Sync Replicas，是指副本同步列表。

AR，Assigned Replicas，指定的副本。在最初时，AR = ISR，但在运行过程中，若出现了ISR 中的某些follower 同步超时，则 leader 会将这些 follower 从 ISR 列表中踢除，进入到 OSR 集合。即 AR = ISR + OSR

OSR，Outof-Sync Replicas

## 2.1.12 offset

偏移量。每条消息都有一个当前 Partition 下唯一的 64 字节的 offset，它是相对于当前分区第一条消息的偏移量。

## 2.1.13 offset commit

Consumer 从 partition 中取出一批消息写入到 buffer 对其进行消费，在规定时间内消费完消息后，会自动将其消费消息的 offset 提交给 broker，以让 broker 记录下哪些消息是消费过的。当然，若在时限内没有消费完毕，其是不会提交 offset 的。

## 2.1.14 Rebalance

当消费者组中消费者数量发生变化，或 Topic 中的 partition 数量发生了变化时，partition 的所有权会在消费者间转移，即 partition 会重新分配，这个过程称为再均衡 Rebalance。

再均衡能够给消费者组及 broker 集群带来高可用性和伸缩性，但在再均衡期间消费者是无法读取消息的，即整个 broker 集群有一小段时间是不可用的。因此要避免不必要的再均衡。

## 2.1.15 Broker Controller

Kafka 集群的多个 broker 中，有一个会被选举为 controller，负责管理整个集群中 partition

和副本 replicas 的状态。

## 2.1.16 Zookeeper

Zookeeper 负责维护和协调 broker，负责 Broker Controller 的选举。总结对比：

- partition leader 是由 broker controller 负责选举的，选举算法是“论资排辈”。
- broker controller 是由 zk 负责选举的，选举算法是我们曾在 zk 的典型应用场景中讲过的

Master 选举

## 2.1.17 Coordinator

Coordinator 一般指的是运行在每个 broker 上的 group Coordinator 进程，用于管理Consumer Group 中的各个成员，主要用于 offset 位移管理和 Rebalance。一个 Coordinator 可以同时管理多个消费者组。

## 2.2 Kafka 工作原理与过程

- **消息路由策略**

在通过 API 方式发布消息时，生产者是以 Record 为消息进行发布的。Record 中包含 key 与 value，value 才是我们真正的消息本身，而 key 用于路由消息所要存放的 Partition。消息要写入到哪个 Partition 并不是随机的，而是有路由策略的。

- 若指定了 partition，则直接写入到指定的 partition；
  - 若未指定 partition 但指定了 key，则通过对 key 的 hash 值与 partition 数量取模，该取模结果就是要选出的 partition 索引；
- 若 partition 和 key 都未指定，则使用轮询算法选出一个 partition。

## 2.2.2 消息写入算法

消息生产者将消息发送给 broker，并形成最终的可供消费者消费的 log，是一个比较复杂的过程。

- producer 向 broker 集群提交连接请求，其所连接上的任意 broker 都会向其发送 broker controller 的通信 URL，即 broker controller 主机配置文件中的 listeners 地址
- 当 producer 指定了要生产消息的 topic 后，其会向 broker controller 发送请求，请求当前

topic 中所有 partition 的 leader 列表地址

- broker controller 在接收到请求后，会从zk 中查找到指定topic 的所有partition 的leader，

并返回给 producer

- producer 在接收到 leader 列表地址后，根据消息路由策略找到当前要发送消息所要发送的 partition leader，然后将消息发送给该 leader
- leader 将消息写入本地 log，并通知 ISR 中的 followers
- ISR 中的followers 从 leader 中同步消息后向 leader 发送 ACK
  - leader 收到所有 ISR 中的 followers 的 ACK 后，增加 HW，表示消费者已经可以消费到该位置了
- 若 leader 在等待 followers 的 ACK 时超时了，发现还有 follower 没有发送 ACK，则 leader

会将这些 follower 从 ISR 中清除，然后再增加 HW

## 2.2.3 HW 机制

HW，HighWatermark，高水位，表示 Consumer 可以消费到的最高 partition 偏移量。HW 保证了 Kafka 集群中消息的一致性。确切地说，是在 broker 集群正常运转的状态下，保证了partition 的 Follower 与 Leader 间数据的一致性（HW 机制的作用）。

在 HW 机制中还有一个很重要的概念，LEO。

LEO，Log End Offset，日志最后消息的偏移量。消息是被写入到 Kafka 的日志文件中的， 这是当前最后一个写入的消息在 Partition 中的偏移量。

对于 leader 新写入的消息，consumer 是不能立刻消费的。leader 会等待该消息被所有

ISR 中的 partition follower 同步后才会更新 HW，此时消息才能被 consumer 消费。

## 2.2.4 HW 截断机制

如果 partition leader 接收到了新的消息， ISR 中其它 Follower 正在同步过程中，还未同步完毕时leader 挂了。此时就需要选举出新的leader。若没有HW 截断机制，将会导致partition 中 leader 与 follower 数据的不一致。

当原 Leader 宕机后又恢复时，将其 LEO 回退到其宕机时的 HW，然后再与新的 Leader

进行数据同步，这种机制称为HW 截断机制。

注意，这个过程中是有可能会引发消息丢失的。

总结对比：

- HW 机制：是为了保证在 broker 集群正常运转状态下的 partition leader 与 follower 的数据的一致性。
  - HW 截断机制：是为了保证在 broker 集群中 partiiton leader 出现宕机情况下，partition leader 与 follower 的数据的一致性。

## 2.2.5 消息发送的可靠性机制

生产者向kafka 发送消息时，可以选择需要的可靠性级别。通过 acks 参数的值进行设置。

## （1） 0 值

异步发送。生产者向 kafka 发送消息而不需要 kafka 反馈成功 ack。该方式效率最高，但可靠性最低。其可能会存在消息丢失的情况。

## （2） 1 值

默认值。同步发送，默认值。生产者发送消息给 kafka，broker 的 partition leader 在收到消息后马上发送成功 ack（无需等待 ISR 中的 follower 同步完成），生产者收到后知道消息发送成功，然后会再发送消息。如果一直未收到kafka 的 ack，则生产者会认为消息发送失败，会重发消息。其同样存在消息丢失的可能性。

- 问题 1：该方法能否使 producer 确认其发送的消息发送成功（该消息写入到了 broker）？不能。
  - 问题 2：该方法能否使 producer 确认其发送的消息发送失败了？能。只要 producer 没有收到 ack，一定是发送失败了。
- 简单说就是，收到 ack 了，不一定成功，但没有收到 ack，一定失败

## （3） -1 值

同步发送。其值等同于 all。生产者发送消息给 kafka，kafka 收到消息后要等到 ISR 列表

中的所有副本都同步消息完成后，才向生产者发送成功 ack。如果一直未收到 kafka 的 ack， 则认为消息发送失败，会自动重发消息。

该方式存在 follower 重复接收的情况。注意，重复接收，与重复消费是两个概念。

## 2.2.6 消费者消费过程解析

生产者将消息发送到 topic 中，消费者即可对其进行消费，其消费过程如下：

- consumer 向 broker 集群提交连接请求，其所连接上的任意 broker 都会向其发送 broker controller 的通信 URL，即 broker controller 主机配置文件中的 listeners 地址
- 当 consumer 指定了要消费的 topic 后，其会向 broker controller 发送 poll 请求
  - broker controller 会为 consumer 分配一个或几个 partition leader，并将该 partitioin 的当前 offset 发送给 consumer
- consumer 会按照 broker controller 分配的 partition 对其中的消息进行消费

5)

l 若为手动提交：可以是消费完一条消息就提交一个 offset，也可以是消费完这一批消息后，提交最后一个消息的 offset。关键看代码怎么写。

l 若为自动提交：提交最后一个消息的 offset。

当消费者消费完该条消息后，消费者会向 broker 发送一个该消息已被消费的反馈，即该消息的 offset

- 当 broker 接到消费者的 offset 后，会更新到相应的 consumer_offset 中
- 以上过程一直重复，直到消费者停止请求消息
- 消费者可以重置 offset，从而可以灵活消费存储在 broker 上的消息

## 2.2.7 Partition Leader 选举范围

当 leader 挂了后 broker controller 会从 ISR 中选一个 follower 成为新的 leader。但，若 ISR 中的所有副本都挂了怎么办？可以通过 unclean.leader.election.enable 的取值来设置 Leader 选举的范围。

## （1） false

必须等待ISR 列表中有副本活过来才进行新的选举。该策略可靠性有保证，但可用性低。

## （2） true

在 ISR 中没有副本的情况下可以选择任何一个没有宕机主机中该 topic 的 partition 副本

（即 OSR 中的 partition 副本）作为新的 leader，该策略可用性高，但可靠性没有保证。

## 2.2.8 重复消费问题及解决方案

以下讨论的重复消费问题都是基于 offset 自动提交的。

最常见的重复消费有两种：

## （1） 同一个 consumer 重复消费

当 Consumer 由于消费能力较低而引发了消费超时时，则可能会形成重复消费。解决方案：

- 减少消费量
- 延长自动提交的时长
- 自动提交变为手动提交

## （2） 不同的 consumer 重复消费

当 Consumer 消费了消息但还未提交 offset 时宕机，则这些已被消费过的消息会被重复消费。

解决方案：

- 自动提交变为手动提交

## 2.3 Kafka 集群搭建

在生产环境中为了防止单点问题，Kafka 都是以集群方式出现的。下面要搭建一个 Kafka

集群，包含三个 Kafka 主机，即三个 Broker。

## 2.3.1 Kafka 的下载

- **安装并配置第一台主机**
- **上传并解压**

将下载好的 Kafka 压缩包上传至 CentOS 虚拟机，并解压。

## （2） 创建软链接

- **修改配置文件**

在 kafka 安装目录下有一个 config/server.properties 文件，修改该文件。

## 2.3.3 再克隆两台Kafka

以 kafkaOS1 为母机再克隆两台 Kafka 主机。在克隆完毕后，需要修改 server.properties

中的 broker.id、listeners 与 advertised.listeners。

## 2.3.4 kafka 的启动与停止

- **启动\****zookeeper**
- **启动** **kafka**

在命令后添加-daemon 参数，可以使 kafka 以守护进程方式启动，即不占用窗口。

## （3） 停止 kafka

- **kafka** **操作**
- **创建** **topic**
- **查看** **topic**
- **发送消息**

该命令会创建一个生产者，然后由其生产消息。

## （4） 消费消息

- **继续生产消费**
- **删除** **topic**
- **日志查看**

我们这里说的日志不是Kafka 的启动日志，启动日志在Kafka 安装目录下的logs/server.log 中。消息在磁盘上都是以日志的形式保存的。我们这里说的日志是存放在/tmp/kafka_logs 目录中的消息日志，即 partition 与 segment。

## 2.4.1 查看分区与备份

- **1** **个分区** **1** **个备份**

我们前面创建的 test 主题是 1 个分区 1 个备份。

## （2） 3 个分区 1 个备份

再次创建一个主题，命名为 one，创建三个分区，但仍为一个备份。 依次查看三台

broker，可以看到每台 broker 中都有一个 one 主题的分区。

## （3） 3 个分区 3 个备份

再次创建一个主题，命名为 two，创建三个分区，三个备份。依次查看三台 broker，可以看到每台 broker 中都有三份 two 主题的分区。

## 2.4.2 查看分区与备份在 zk 中的信息

使用zkCli.sh 命令连接上 zk，可以查看到kafka 在zk 的信息。

## （1） /brokers 目录

- **/brokers/ids** **目录**

存放的是kafka 集群中各个主机的 broker-id 列表。

每个 id 的数据内容为当前主机的信息。

## （3） /brokers/topics

/brokers/topics/city/partitions 中存放的是 city 主题下所包含的 partition。这里的 0、1、2，在/tmp/kafka-logs 目录中即为 city-0，city-1，city-2。

## 2.4.3 查看段segment

- **segment** **文件**

segment 是一个逻辑概念，其由两类物理文件组成，分别为“.index”文件和“.log”文件。“.log”文件中存放的是消息，而“.index”文件中存放的是“.log”文件中消息的索引。

## （2） 查看segment

对于 segment 中的 log 文件，不能直接通过 cat 命令查找其内容，而是需要通过 kafka

自带的一个工具查看。

一个用户的一个主题会被提交到一个 consumer_offsets 分区中。使用主题字符串的

hash 值与 50 取模，结果即为分区索引。

# 第3章 Kafka API

首先在命令行创建一个名称为 cities 的主题，并创建该主题的订阅者。

## 3.1 使用 kafka 原生 API

- **创建工程**

创建一个 Maven 的 Java 工程，命名为 kafkaDemo。创建时无需导入依赖。为了简单， 后面的发布者与消费者均创建在该工程中。

## 3.1.2 导入依赖

- **创建发布者** **OneProducer**
- **创建发布者类** **OneProducer**
- **创建测试类** **OneProducerTest**
- **创建发布者** **TwoProducer**

前面的方式在消息发送成功后，代码中没有任何提示，这里可以使用回调方式，即发送成功后，会触发回调方法的执行。

## （1） 创建发布者类TwoProducer

复制 OneProducer 类，仅修改 sendMsg()方法。

## （2） 创建测试类TwoProducerTest

- **批量发送消息**
- **创建发布者类** **SomeProducerBatch**

复制前面的发布者类，在其基础上进行修改。

## （2） 创建测试类 ProducerBatchTest

- **消费者组**
- **创建消费者类** **SomeConsumer**
- **创建测试类** **ConsumerTest**
- **消费者同步手动提交**
- **自动提交的问题**

前面的消费者都是以自动提交 offset 的方式对 broker 中的消息进行消费的，但自动提交可能会出现消息重复消费的情况。所以在生产环境下，很多时候需要对 offset 进行手动提交， 以解决重复消费的问题。

## （2） 手动提交分类

手动提交又可以划分为同步提交、异步提交、同异步联合提交。这些提交方式仅仅是doWork()方法不相同，其构造器是相同的。所以下面首先在前面消费者类的基础上进行构造器的修改，然后再分别实现三种不同的提交方式。

## （3） 创建消费者类 SyncManualConsumer

**A\****、 原理**

同步提交方式是，消费者向 broker 提交 offset 后等待 broker 成功响应。若没有收到响应，则会重新提交，直到获取到响应。而在这个等待过程中，消费者是阻塞的。其严重影响了消费者的吞吐量。

**B\****、 修改构造器**

直接复制前面的 SomeConsumer，在其基础上进行修改。

**C\****、 修改** **doWork()\****方法**

- **创建测试类** **SyncManulTest**
- **消费者异步手动提交**
- **原理**

手动同步提交方式需要等待 broker 的成功响应，效率太低，影响消费者的吞吐量。异步提交方式是，消费者向 broker 提交 offset 后不用等待成功响应，所以其增加了消费者的吞吐量。

## （2） 创建消费者类 AsyncManualConsumer

复制前面的 SyncManualConsumer 类，在其基础上进行修改。

## （3） 创建测试类 AsyncManulTest

- **消费者同异步手动提交**
- **原理**

同异步提交，即同步提交与异步提交组合使用。一般情况下，在异步手动提交时，若偶尔出现提交失败，其也不会影响消费者的消费。因为后续提交最终会将这次提交失败的 offset 给提交了。

但异步手动提交会产生重复消费，为了防止重复消费，可以将同步提交与异常提交联合使用。

## （2） 创建消费者类 SyncAsyncManualConsumer

复制前面的 AsyncManualConsumer 类，在其基础上进行修改。

## 3.2 Spring Boot Kafka

为了简单，以下代码是将消息发布者与订阅者定义到了一个工程中的。

## 3.2.1 创建工程

创建一个 Spring Boot 工程，导入如下依赖。

## 3.2.2 定义发布者

Spring 是通过 KafkaTemplate 来完成对 Kafka 的操作的。

## （1） 修改配置文件

- **定义发布者处理器**

Spring Kafka 通过KafkaTemplate 完成消息的发布。

## 3.2.3 定义消费者

Spring 是通过监听方式实现消费者的。

## （1） 修改配置文件

在配置文件中添加如下内容。注意，Spring 中要求必须为消费者指定组。

## （2） 定义消费者

Spring Kafka 是通过 KafkaListener 监听方式来完成消息订阅与接收的。当监听到有指定主题的消息时，就会触发@KafkaListener 注解所标注的方法的执行。