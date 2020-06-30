 **微服务框架** **SpringCloud** **(H** **版\****)**

**课程讲义**

**主讲：\****Reythor** **雷**

**2020**

**微服务框架** **Spring Cloud**

第1章 **Spring Cloud** **入门**

# 1.1 Spring Cloud 简介

- **官网简介**

打开 Spring 官网 [http://spring.io](http://spring.io/) 首页的中部，可以看到 Spring Cloud 的简介。

【原文】Building distributed systems doesn't need to be complex and error-prone(易错). Spring Cloud offers a simple and accessible( 易 接 受 的 ) programming model to the most common distributed system patterns(模式), helping developers build resilient(有弹性的), reliable(可靠的), and coordinated(协调的) applications. Spring Cloud is built on top of Spring Boot, making it easy for developers to get started and become productive quickly.

【翻译】构建分布式系统不需要复杂和容易出错。Spring Cloud 为最常见的分布式系统模式提供了一种简单且易于接受的编程模型，帮助开发人员构建有弹性的、可靠的、协调的应用程序。Spring Cloud 构建于 Spring Boot 之上，使得开发者很容易入手并快速应用于生产中。

# 1.1.2 百度百科

Spring Cloud 是一系列框架的有序集合。它利用 Spring Boot 的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用 Spring Boot 的开发风格做到一键启动和部署。Spring Cloud 并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来， 通过 Spring Boot 风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者提供了一 套简单易懂、易部署和易维护的分布式系统开发工具包。

# 1.1.3 总结

Spring Cloud 是什么？阿里高级框架师、Dubbo 项目的负责人刘军说，Spring Cloud 是微服务系统架构的一站式解决方案。

Spring Cloud 与 Spring Boot 是什么关系呢？Spring Boot 为 Spring Cloud 提供了代码实现环境，使用Spring Boot 将其它组件有机融合到了 Spring Cloud 的体系架构中了。所以说，Spring Cloud 是基于 Spring Boot 的、微服务系统架构的一站式解决方案。

# 1.2 Spring Cloud 的国内使用情况

- **Spring Cloud** **在线资源**
- **Spring Cloud** **官网**

https://projects.spring.io/spring-cloud/

# 1.3.2 Spring Cloud 中文网

https://springcloud.cc/

# 1.3.3 Spring Cloud 中国社区

http://springcloud.cn/

# 1.4 Spring Cloud 版本

- **版本号来源**

Spring Cloud 的版本号并不是我们通常见的数字版本号，而是一些很奇怪的单词。这些单词均为英国伦敦地铁站的站名。同时根据字母表的顺序来对应版本时间顺序，比如：最早的 Release 版本 Angel（天使），第二个 Release 版本Brixton（英国地名），然后是 Camden、 Dalston、Edgware，目前使用较多的是 Finchley（英国地名）版本，而最新版本为 Hoxton（英国地名），而我们这里要使用的是 Greenwich（格林威治）。

# 1.4.2 Spring Cloud 与 Spring Boot 版本

某一版本的 Spring Cloud 要求必须要运行在某一特定 Spring Boot 版本下。它们的对应关系在 Spring Cloud 官网可以看到版本对应说明。

# 1.5 Spring Cloud 与 Dubbo 技术选型

Spring Cloud 与 Dubbo 均为微服务框架，开发团队在进行技术选型时，总会将它们进行对比，考虑应该选择哪一个。可以从以下几方面考虑：

- 架构完整度
- 社区活跃度
- 通讯协议
- 技术改造与微服务开发

# 1.6 第一个服务提供者/消费者项目

本例实现了消费者对提供者的调用，但并未使用到Spring Cloud，但其为后续Spring Cloud

的运行测试环境。使用 MySQL 数据库，使用 Spring Data JPA 作为持久层技术。

# 1.6.1 创建提供者工程 01-provider-8081

- **创建工程**

创建一个 Spring Initializr 工程，并命名为 01-provider-8081。导入 Lombok、Web、JPA 及

MySQL 驱动依赖。

# （2） 导入 Druid 依赖

com.alibabadruid1.1.10mysql " src="data:image/png;base64,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" v:shapes="_x0000_s1271">

mysql-connector-java 5.1.47 runtime " src="data:image/png;base64,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" v:shapes="_x0000_s1082">

- **定义实体类**
- **定义** **Repository** **接口**
- **定义\****Service** **接口**
- **定义\****Service** **实现类**

## A、 添加数据

**B\****、 删除数据**

**C\****、 修改数据**

**D\****、根据** **id** **查询**

**E\****、 查询所有**

- **定义处理器**
- **修改配置文件**
- **创建消费者工程** **01-consumer-8080**
- **创建工程**

创建一个 Spring Initializr 工程，并命名为 01-consumer-8080，导入 Lombok 与 Web 依赖。

# （2） 定义实体类

- **定义\****JavaConfig** **容器类**
- **定义处理器类**

## A、 添加数据

**B\****、 删除与修改数据**

**C\****、 两个查询**

第2章 **微服务中心** **Eureka**

# 2.1 Eureka 概述

- **Eureka** **简介**

Eureka 是 Netflix 开发的服务发现框架，本身是一个基于REST 的服务，主要用于定位运行在AWS 域中的中间层服务，以达到负载均衡和中间层服务故障转移的目的。SpringCloud 将它集成在其子项目 spring-cloud-netflix 中，实现 SpringCloud 的服务发现功能。

其实，Eureka 就是一个专门用于服务发现的服务器，一些服务注册到该服务器，而另一些服务通过该服务器查找其所要调用执行的服务。可以充当服务发现服务器的组件很多，例如 Zookeeper、Consul、Eureka 等。

# 2.1.2 Eureka 体系架构

- **CAP** **定理**
- **概念**

CAP 定理指的是在一个分布式系统中，Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性），三者不可兼得。

- 一致性（C）：分布式系统中多个主机之间是否能够保持数据一致的特性。即，当系统数据发生更新操作后，各个主机中的数据仍然处于一致的状态。
- 可用性（A）：系统提供的服务必须一直处于可用的状态，即对于用户的每一个请求，系统总是可以在有限的时间内对用户做出响应。
- 分区容错性（P）：分布式系统在遇到任何网络分区故障时，仍能够保证对外提供满足一致性和可用性的服务。

# （2） 定理

CAP 定理的内容是：对于分布式系统，网络环境相对是不可控的，出现网络分区是不可避免的，因此系统必须具备分区容错性。但系统不能同时保证一致性与可用性。即要么 CP， 要么 AP。

# 2.1.4 Eureka 与 Zookeeper 对比

Eureka 与 Zookeeper 都可以充当服务中心，那么它们有什么区别呢？它们的区别主要体现在对于 CAP 原则的支持的不同。

- Eureka：AP
- zk：CP

# 2.1.5 Eureka 的闭源谣言

Eureka 官网的 wiki 中公布了如下内容：

Eureka 1.x is a core part of Netflix's service discovery system and is still an active project.

Additionally（另外）, extension work（扩展工作） on eureka 1.x has moved（推进） internally

（内部） within Netflix.

**【\****翻译】**现在的关于 eureka 2.0 的开源工作已经终止。已经发布的现存库中的关于 2.x 分支部分的代码库与工程，你的使用将自负风险。

Erueka 1.x 是 Netflix 服务发现系统的核心部分，其仍是一个活跃项目。另外，在 Eureka

1.x 上的扩展工作已经在 Netflix 内部推进。

# 2.2 创建Eureka 服务中心 00-eurekaserver-8000

这里创建的 Eureka 服务中心，即服务发现服务器。

# 2.2.1 创建工程

创建一个 Spring Initializr 工程，命名为 00-eurekaserver-8000，仅导入 Eureka Server 依赖即可。

工程创建完毕后，在 pom 文件中可以看到如下的版本信息。

# 2.2.2 导入依赖

若你使用的是 JDK6、7、8，那么这些依赖无需导入。而 JDK9 及其以上版本需要导入。

# 2.2.3 创建并配置 yml 文件

- **定义\****spring boot** **启动类**
- **创建提供者工程** **02-provider-8081**
- **创建工程**

复制 01-provider-8081，并重命名为 02-provider-8081。

# 2.3.2 添加依赖管理及依赖

- **修改** **yml** **文件**
- **创建消费工程** **02-consumer-8080**

消费者将使用提供者暴露的服务名称(spring.application.name)来消费服务。

# 2.4.1 创建工程

复制 01-consumer-8080，并重命名为 02-consumer-8080。

# 2.4.2 添加依赖管理及依赖

- **修改** **yml** **文件**
- **修改处理器**
- **修改\****JavaConfig** **类**
- **修改启动类**
- **服务发现** **Discovery**

直接修改处理器。

# 2.6 Eureka 的自我保护机制

- **自我保护机制**

在 Eureka 服务页面中看到如下红色字体内容，表示当前 EurekaServer 启动了自我保护机制，进入了自我保护模式。

【原文】Emergency (紧急情况) ! Eureka may be incorrectly claiming(判断) instances(指微服务主机) are up when they're not. Renewals(续约，指收到的微服务主机的心跳) are lesser than threshold(阈值) and hence(从此) the instances are not being expired(失效) just to be(只是为了) safe.

【翻译】紧急情况！当微服务主机联系不上时，Eureka 不能够正确判断它们是否处于 up 状态。当续约数量（指收到的微服务主机的心跳）小于阈值时，为了安全，微服务主机将不再失效。85%

默认情况下，EurekaServer 在 90 秒内没有检测到服务列表中的某微服务，则会自动将该微服务从服务列表中删除。但很多情况下并不是该微服务节点（主机）出了问题，而是由 于网络抖动等原因使该微服务无法被 EurekaServer 发现，即无法检测到该微服务主机的心跳。若在短暂时间内网络恢复正常，但由于 EurekaServer 的服务列表中已经没有该微服务，所以该微服务已经无法提供服务了。

在短时间内若EurekaServer 丢失较多微服务，即 EurekaServer 收到的心跳数量小于阈值， 为了保证系统的可用性（AP），给那些由于网络抖动而被认为宕机的客户端“重新复活”的机会，Eureka 会自动进入自我保护模式：服务列表只可读取、写入，不可执行删除操作。当EurekaServer 收到的心跳数量恢复到阈值以上时，其会自动退出 Self Preservation 模式。

# 2.6.2 默认值修改

启动自我保护的阈值因子默认为 0.85，即 85%。即 EurekaServer 收到的心跳数量若小于应该收到数量的 85%时，会启动自我保护机制。

自我保护机制默认是开启的，可以通过修改 EurekaServer 中配置文件来关闭。但不建议关闭。

# 2.6.3 自我保护启动阈值

查看前面Eureka 的启动界面，可以看到 Renews threshold 与Renews(last min)两个数值。这两个值都是瞬时值，而非平均值。就像汽车的瞬时速度与平均速度一样。

# 2.7 服务下架

- **准备工作**

为 Eureka Client 添加actuator 依赖。

# 2.7.2 关闭服务

- **修改配置文件**
- **运行测试**

在 Restlet 中提交如下 POST 请求即可关闭该应用。

# 2.7.3 服务平滑上下线

前面的“关闭服务”方式存在一个不足是，若还需要再启用该服务，则必须再次启动该应用。我们也可以通过修改服务的状态为 UP 或 DOWN 来设置提供者是否可用，而无需重启应用。这种方式通常称为服务的平滑上下线。

# （1） 修改配置文件

- **运行测试**

在 Restlet 中提交如下 POST 请求，然后再查看 Eureka 页面，发现服务状态已经变为了

DOWN。

# 2.8 EurekaServer 集群

这里要搭建的 EurekaServer 集群中包含三个 EurekaServer 节点，其端口号分别为 8100、8200 与 8300。

# 2.8.1 设置域名

在 C:\Windows\System32\drivers\etc 的 host 文件中添加如下域名映射信息。

# 2.8.2 创建 00-eurekaserver-8100

- **复制工程**

复制 00-eurekaserver-8000 工程，并重命名为 00-eurekaserver-8100。

# （2） 修改pom

- **修改配置文件**

将上面的 localhost 更换为 eureka8100.com、eureka8200.com、eureka8300.com。

# 2.8.3 创建 00-eurekaserver-8200

再以相同的方式再复制出 00-eurekaserver-8200。

# 2.8.4 创建 00-eurekaserver-8300

再以相同的方式再复制出 00-eurekaserver-8300。

# 2.8.5 修改客户端配置文件

修改客户端工程的配置文件，指定 Eureka 集群的各个主机即可。这里以提供者工程

02-provider-8081 为例。

将上面的 localhost 更换为 eureka8100.com、eureka8200.com、eureka8300.com。

# 2.9 Eureka Client 源码解析

- **基础知识**
- **InstanceInfo**

就代表一个 Eureka Client。

- lastDirtyTimestamp：记录当前 intance 在 Client 端的最后修改时间。
- lastUpdatedTimestamp：记录当前 instance 在 Server 端的最后修改时间。
- status：当前 instance 的存活状态（服务状态）
- overriddenStatus：可覆盖状态，是用户修改 instance 时使用的状态。

# （2） Application

instancesMap 的 key 为 intanceId，value 为对应的 intanceInfo。

# （3） Applications

其是从 Eureka Server 下载的注册表保存在 Client 端的形式。我们简称“客户端注册表”。

appNameApplicationMap 的 key 为微服务名称，value 为对应的 Application。

# （4） Jersey 框架

JAX-RS

Resource

# 2.9.2 自动配置类的加载

- **微服务注册**
- **总思路**
- **CloudEurekaClient** **构造器跟踪**
- **获取客户端注册表**

## A、 任务

最终执行的操作是，通过 Jersey 框架提交一个GET 请求，然后获取到 Applications 实例。

## B、 解析

从这里开始跟踪。

跟踪 getAndStoreFullRegistry()方法。

# （4） 客户端注册

## A、 任务

其最终执行的操作是，通过 Jersey 框架提交一个 POST 请求，将自己的 InstanceInfo 发送给 Eureka Server。

## B、 解析

重新返回 DiscoveryClient 的构造器。

跟踪 registrer()方法。

# （5） 初始化定时任务

重新返回 DiscoveryClient 的构造器。

## A、 定时更新应用列表对象

跟踪fetchRegistry()方法。

跟踪 getDelta()方法。

返回 getAndUpdateDelta()方法。

跟踪 updateDelta()方法

**B\****、 定时发送心跳**

重新返回 DiscoveryClient 类 initScheduledTasks()方法。

**a\****、 解析**

**b\****、** **404** **的问题**

在后面的“定时发送更新的续约信息”中，若 instanceId 发生了变更，则 Client 会发出register()请求。此时也会使用新的 instanceId 发送心跳。若由于网络原因，在这个 register() 请求还未到达 Eureka Server 之前，心跳先到了，此时的心跳请求中的 instanceId 在 Eureka Server 的注册表中是找不到的，所以 Server 会返回 404。

## C、 定时更新 Client 信息给 Server

重新返回 initScheduledTasks()方法。

# 2.9.4 总结

Eureka Client 向Eureka Server 提交 register()请求的情况：

- 直接提交 register()
- 定时发送心跳时，若 Server 返回 404，则 Client 会提交 register()请求。
- 定时更新 Client 信息给 Server。只要 Client 信息发生了变更，就会提交 register()请求。

# 2.9.5 微服务下架

重新返回到 EurekaClientAutoConfiguration 类的如下@Bean 方法，该@Bean 方法就是前面分析微服务注册时的入口方法。

# 2.10 Eureka Server 源码解析

- **处理客户端状态修改请求**

InstanceResource 类的 statusUpdate()方法用于处理 Client 的状态修改请求。

重新返回 updateStatus()方法。

重新返回如下的 statusUpdate()方法。

# 2.10.2 处理客户端注册请求

ApplicationResource 类的 addInstance()方法用于处理微服务注册请求。

# 2.10.3 处理客户端续约请求

InstanceResource 类的 renewLease()方法用于处理 Client 的续约请求。

# 2.10.4 处理客户端下架请求

InstanceResource 类中的 cancelLease()方法用于处理 Client 的下架请求。

# 2.10.5 处理客户端全量下载请求

查找readWriteCacheMap 的初始化。

# 2.10.6 处理客户端增量下载请求

查找readWriteCacheMap 的初始化。

# 2.10.7 定时清除过期 Client

跟踪 initEurekaServerContext()方法。

第3章 **OpenFeign** **与** **Ribbon**

# 3.1 概述

- **OpenFeign** **简介**
- **官网简介**
- **综合说明**

OpenFeign 可以将提供者提供的Restful 服务伪装为接口进行消费，消费者只需使用“feign 接口 + 注解”的方式即可直接调用提供者提供的 Restful 服务，而无需再使用 RestTemplate。

# 3.1.2 OpenFeign 与 Feign

Spring Cloud D 版及之前的版本使用的是 Feign，而该项目现已更新为了 OpenFeign。所以后续使用的依赖也发生了变化。

# 3.1.3 Ribbon 与 OpenFeign

说到 OpenFeign，不得不提的就是 Ribbon。Ribbon 是Netflix 公司的一个开源的负载均衡项目，是一个客户端负载均衡器，运行在消费者端。

OpenFeign 也是运行在消费者端的，使用 Ribbon 进行负载均衡，所以 OpenFeign 直接内置了Ribbon。即在导入 OpenFeign 依赖后，无需再专门导入 Ribbon 依赖了。

# 3.2 声明式 Rest 客户端 OpenFeign

- **创建消费者工程** **03-consumer-feign-8080**

这里无需修改提供者工程，只需修改消费者工程即可。

# （1） 创建工程

复制 02-consumer-8080，并重命名为 03-consumer-feign-8080。

# （2） 添加openfeign 依赖

org.springframework.cloud " src="data:image/png;base64,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" v:shapes="_x0000_s1502">

spring-cloud-starter-openfeign " src="data:image/png;base64,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" v:shapes="_x0000_s1101">

- **定义** **Feign** **接口**
- **修改\****JavaConfig** **类**
- **修改处理器**
- **修改启动类**
- **超时设置**

Feign 连接提供者、对于提供者的调用均可设置超时时限。

# （1） 修改配置文件

在 03-consumer-feign-8080 工程的配置文件中直接添加如下内容：

# （2） 创建提供者工程 03-provider-8081

## A、 创建工程

复制工程 02-provider-8081，并重命名为 03-provider-8081。

## B、 修改 Service 接口实现类

- **Gzip** **压缩设置**

Feign 支持对请求（Feign 客户端向提供者的请求）和响应（Feign 客户端向客户端浏览器的响应）进行 Gzip 压缩以提高通信效率。

在 03-consumer-feign-8080 工程的配置文件中直接添加如下内容：

# 3.3 Ribbon 负载均衡

- **系统结构**
- **创建提供者** **03-provider-8082**
- **复制提供者工程** **8081**

复制 02-provider-8081 工程，并重命名为 03-provider-8082。

# （2） 修改配置文件

- **修改处理器**
- **创建提供者** **03-provider-8083**

以相同的方式创建提供者工程 03-provider-8083。

# 3.3.4 创建提供者 03-provider-8084

以相同的方式创建提供者工程 03-provider-8084。

# 3.4 更换负载均衡策略 03-consumer-loadbalance-8080

- **内置负载均衡策略**
- **RoundRobinRule**

轮询策略。Ribbon 默认采用的策略。若经过一轮轮询没有找到可用的 provider，其最多轮询 10 轮。若最终还没有找到，则返回 null。

# （2） RandomRule

随机策略，从所有可用的 provider 中随机选择一个。

# （3） RetryRule

重试策略。先按照 RoundRobinRule 策略获取 provider，若获取失败，则在指定的时限内重试。默认的时限为 500 毫秒。

# （4） BestAvailableRule

最可用策略。选择并发量最小的 provider，即连接的消费者数量最少的 provider。

# （5） AvailabilityFilteringRule

可用过滤算法。该算法规则是：先采用轮询方式选择一个 Server，然后判断其是否处于熔断状态，是否已经超过连接极限。若没有，则直接选择。否则再重新按照相同的方式进行再选择。最多重试 10 次。

若 10 次后仍没有找到，则重新将所有 Server 进行判断，挑选出所有未熔断，未超过连接极限的 Server，然后再采用轮询方式选择一个。若还没有符合条件的，则返回 null。

# （6） ZoneAvoidanceRule

H 版中已经没有了该策略。

zone 回避策略。根据 provider 所在 zone 及 provider 的可用性，对 provider 进行选择。

# （7） WeightedResponseTimeRule

H 版中已经没有了该策略。

“权重响应时间”策略。根据每个 provider 的平均响应时间计算其权重，响应时间越快权重越大，被选中的机率就越高。在刚启动时采用轮询策略。后面就会根据权重进行选择了。

# 3.4.2 更换内置策略

Ribbon 默认采用的是 RoundRobinRule，即轮询策略。但通过修改消费者工程的配置文件，或修改消费者的启动类或 JavaConfig 类可以实现更换负载均衡策略的目的。

# （1） 创建工程

复制 03-consumer-feign-8080 工程，并重命名为 03-consumer-loadbalance-8080。

# （2） 方式一：修改配置文件

修改配置文件，在其中添加如下内容：

# （3） 方式二：修改 JavaConfig 类

在 JavaConfig 类中添加负载负载 Bean 方法。

# 3.4.3 自定义负载均衡策略

直接在 03-consumer-loadbalance-8080 工程上修改。

# （1） 定义 CustomRule 类

Ribbon 支持自定义负载均衡策略。负载均衡算法类需要实现 IRule 接口。

该负载均衡策略的思路是：从所有可用的 provider 中排除掉指定端口号的 provider，剩余 provider 进行随机选择。

# （2） 修改JavaConfig 类

将原来的负载均衡 Bean 方法注释掉，添加新的负载均衡策略方法。

# 3.5 OpenFeign 源码解析

- **重要类与接口解析**
- **@EnableFeignClients**
- **@FeignClient**
- **FeignClientSpecification** **类**

FeignClientSpecification 是一个 Feign Client 的生成规范。

# （4） BeanDefinition 接口

BeanDefinition 是一个 Bean 定义器。

# （5） BeanDefinitionRegistry 接口

BeanDefinitionRegistry 是一个 BeanDefinition 注册器。

# （6） FeignContext 类

FeignContext 是一个为Feign Client 创建所准备的上下文对象。

configurations 这个 map 中包含两类元素：

第一类只有一个，其 key 为 default.启动类名，value 为@EnableFeignClients 的

defaultConfiguration 的值。

第二类有多个，每个 Feign Clieng 有一个。其 key 为@FeignClient 的value 属性值，即微服务名称，value 为@FeignClient 的 configuration 属性值

# 3.5.2 Feign Client 的创建

- **完成配置注册**

## A、 registerDefaultConfiguration()

**B\****、** **registerFeignClients\****()**

返回registerBeanDefinitions()方法。

# （2） 完成自动配置

contexts：key 为@FeignClient 的 name 属性，即微服务名称

value 为对应的一个 Spring 子容器

configurations： key 有两种类型，而对应的value 均为 configuration 类。

key 的一种类型为 default + 启动类的全限定性类名，对应的 value 为@EnableFeignClients 注解的 defaultConfiguration 的属性；

key 的另一种类型为@FeignClient 的 name 属性名，value 为@FeignClient 的 configuration 属性

# （3） 生成 Feign Client

- **发出网络请求**

跟踪 ReflectiveFeign.FeignInvocationHandler 类的 Invoke()方法。

# 3.5.4 负载均衡

注意负载均衡的时机：在生成了 Feign Client 代理对象时，还没有完成负载均衡。在真正进行方法调用时才进行的负载均衡。

跟踪 ReflectiveFeign.FeignInvocationHandler 类的 Invoke()方法。

# 第4章 Hystrix 服务熔断与服务降级

## 4.1 前置概念

- **服务熔断**
- **雪崩效应**

在复杂的系统中，经常会出现A 依赖于 B，B 依赖于 C，C 依赖于 D，……这种依赖将会产生很长的调用链路，这种复杂的调用链路称为 1->N 的扇出。

如果在 A 的调用链路上某一个或几个被调用的子服务不可用或延迟较高，则会导致调用

A 服务的请求被堵住。

堵住的 A 请求会消耗占用系统的线程、IO 等资源，当对 A 服务的请求越来越多，占用的计算机资源越来越多的时候，会导致系统瓶颈出现，造成其他的请求同样不可用，最终导致业务系统崩溃，这种现象称为雪崩效应。

## （2） 服务雪崩

雪崩效应发生在分布式 SOA 系统中，则称为服务雪崩。

上图是用户请求的多个服务（A,H,I,P）均能正常访问并返回的情况。

上图为请求服务 I 出现问题时，一个用户请求被阻塞的情况。

上图为大量用户请求服务 I 出现异常全部陷入阻塞的的情况，即服务发生雪崩的情况。

## （3） 熔断机制

熔断机制是服务雪崩的一种有效解决方案。常见的熔断有两种：

- 预熔断
- 即时熔断

## 4.1.2 服务降级

服务降级是请求发生问题后的一种增强用户体验的方式。

## 4.2 Hystrix 简介

Spring Cloud 是通过 Hystrix 来实现服务熔断与降级的。

## 4.2.1 官网Wiki

【原文】In a distributed environment, inevitably（不可避免地） some of the many service dependencies will fail. Hystrix is a library that helps you control the interactions（交互） between these distributed services by adding latency tolerance（延迟容忍） and fault tolerance logic（容错逻辑）. Hystrix does this by isolating points of access between the services, stopping cascading failures（级联错误） across them（跨服务）, and providing fallback options（回退选项）, all of which improve your system’s overall resiliency（弹性）.

【翻译】在分布式环境中，许多服务依赖中的一些服务发生失败是不可避免的。Hystrix 是一个库，通过添加延迟容忍和容错逻辑，帮助你控制这些分布式服务之间的交互。Hystrix 通过隔离服务之间的访问点、停止跨服务的级联故障以及提供回退选项来实现这一点，所有这些都可以提高系统的整体弹性。

## 4.2.2 综合说明

Hystrix 是一种开关装置，类似于熔断保险丝。在消费者端安装一个 Hystrix 熔断器，当Hystrix 监控到某个服务发生故障后熔断器会开启，将此服务访问链路断开。不过Hystrix 并不会将该服务的消费者阻塞，或向消费者抛出异常，而是向消费者返回一个符合预期的备选响应（FallBack）。通过 Hystrix 的熔断与降级功能，避免了服务雪崩的发生，同时也考虑到了用户体验。故Hystrix 是系统的一种防御机制。

## 4.3 fallbackMethod 服务降级

Hystrix 对于服务降级的实现方式有两种：fallbackMethod 服务降级，与 fallbackFactory

服务降级。首先来看 fallbackMethod 服务降级。

## 4.3.1 创建消费者工程 04-consumer-fallbackmethod-8080

- **创建工程**

复制 02-consumer-8080 工程，并重命名为 04-consumer-fallbackmethod-8080。该工程的运行说明 hystrix 本身与 feign 是没有关系的。

## （2） 添加hystrix 依赖

org.springframework.cloudspring-cloud-starter-netflix-hystrix " src="data:image/png;base64,R0lGODlhzQKiAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6An8AhwAAAAAAAAgKDQ0KCAMAAAQIDQ0LCwgDAAAECggDAwAABAoLDQ0LCgQABA0IBAoEAwgICAADCAsLDQMGCwgGAwsLCwsGAwoEAAMEBAgEAAQAAAAAAwQAAwMAAwQICgMDCAsLCgMABAoLCgMGCggDBAMECgQDAwoIBAoGBAQGCggGBAoKCAMGCAQGCAgEBAQDBAsGBAQEAwQDCAQEBA0LOQ0LJQAANQAAYAAAlAAAgAAApw0kTA05XwA1uQBgqgBgzDkLDSULDTUAACULJTUAgDUAlDU1hiVMTDlMTCVMcDlegTVgpzWGuTWGzjWG3UwkDV85DUxMOUxMcF9egV9wTExwX0xwcExwgV+BX1+BgWAAAGAAYGAAgHBMJXBMOXBMTHBwTHBwX2BglGBggHCBX3CBcHCBgWCGuWCq74Y1AIY1NYY1gIFeOYFwTIGBX4GBcIGBgYaGp4aquYbO3YbOzIbO76pgAKpggKrOuarv3arv786GNc6GlM7v3c7vzM7v7++qYO+qp+/Ohu/Oue/vqu/vzu/vzO/v3e/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEEGEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKVAigpMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmz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" v:shapes="_x0000_s1515">

- **修改处理器类**
- **在启动类添加注解\****@EnableCircuitBreaker**

这一个类上添加的注解太多了，为了避免这种情况的发生，Spring Cloud 专门定义了一个组合注解@SpringCloudApplication，就包含了这三个注解。所以可以用它直接替换掉那三个注解。

## 4.3.2 创建消费者工程 04-consumer-feign-fallbackmethod-8080

- **创建工程**

复制03-consumer-feign-8080 工程，并重命名为04-consumer-feign-fallbackmethod-8080。该工程是Hystrix 与 Feign 结合使用。当然，一般情况下都是这样使用的。

## （2） 添加hystrix 依赖

org.springframework.cloudspring-cloud-starter-netflix-hystrix " src="data:image/png;base64,R0lGODlhzQKjAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6An8AhwAAAAAAAAsLDQ0LCwgDAAADCA0KCAMAAAMGCwoEAAsGAwAECg0IBA0LCgQAAAQIDQMABAoLDQAABAQICgAAAwgKDQAECAMAAwgKCwQICwQAAwsLCwMDCAQGCAMGCgQABAoLCwgEBAQECAgDAwgGBAoKCAQGCwoEAwgKCgsLCgQEAwMEBAgICAsIBAADBAQDAAMDBA0LJQ0LOQAANQAAYAAAgAAAlAAApw05Xw0kTAA1hgA1uQBgqgBgzCULDTkLDTUAACULOTUAYDUAgDU1lDU1pyVMXyVMcDlMcDlecDlegTVgpzWGzjWGzDWG3V85DUwkDV85JV9eOUxeTExwcExwgV+BX1+BcF+BgWAANWAAgHBMJXBMOXBMTHBwTHCBX3CBcHCBgWCGuWCqzGCq74Y1AIY1gIFeOYFwTIFwcIGBX4GBcIGBgYaGlIbO76pgAKpggKpglKqGlKrOuarvzKrv3arv786GNc6GlM7Ouc7vzM7v3c7v7++qYO+qp+/Ohu/Oue/vqu/v3e/vzO/vzu/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEEEEhwoMGCCA8qTMhwocOGEB9KjEhxosWKGC9qzMhxo8eOID+KDElypMmSKE+qTMlypcuQAwHInEmzps2bOHPq3Mmzp8+fQIMKHUq0qNGjSJMqXcq0qdOnUKPWPCi1qtWrWLNq3cq1q9evYMMGiBm2rNmzaNOqXcu2LdqxbuPKnUu3rt27eIfCzcu3r9+/gAML1kl18FI2MxHjVCyTseHHkCNLdrt3MlHHjmliZpPZsufPoEMjrQz0CBofQmNUeZJVBpYzQBVzBsC5duPas2WL3s27t++ZpH8qgS1UxhUoR5Vs8emaeM/MnRNLp/27uvXrggvnxHF6pmrWQnN8//lh9DvUzTZx31YvfDn29/DjYw1uU4ka8jONIxfKHQln98rJ5Bp4R9QGm2u4rYFcDl4EgQUbCgKQQxhsOAeAEt1NaOFsjVHnIXWI2TadTxOCJ9+JKKZIFH35vWYTDvcNVSBs3KF2BHE5gIEcjPjRJF6PAOCQho4XbhHDFD7UmN9xrrnXoW4gThciiBwCVaKKWGap5VRk0RSDEWyYSNONQ6nWnZJKmoYajzapOWaYMgUok5sz4cBFFU5GSZtsmO2p25T8sZHnloQWap12NNkH5ExyyjQhZzFKSCEbMQ44J3H6xUDFfgAUCCejg9JZU6MyqTbokx52lpueiD1KKX6uRv865xpRGGrrrb2xOKesSP0IgHm/rkZmTTnuB2ypeN50bLBnJMFpYoB+KN2f0vpkJmq4ZqvtZLruCiQOEfLXnX6MdmHFohKOh2ye5NqknwxSMBukhR9OCV2IUIYXxqnb9utvX91eShO4z/40rK8y4fBpkLWFmzCk5CFc36sTjotFnoxVyae00f7U7r8gh2wXol4NF9bG046YcXQit+yyXwFjJWpXLKd3W001v6zzznHFLJVqbHTH89BEE+1z0UgnrXRSJC/t9NNQA3V01FRXvfTUVmet9ctYb+3119k2DfbYZIfdZdlop00oi4W07fbbcMct99x012333XgXovbefNP/xHbegAcu+OB292142k0TrvjijNd9+ONk/203HDfY0TjelFt++d2Qd+615HVnvvnklY9euOeoVw16IFm4Ebfops8Ne+yF9KEDH26nrvvTibf9BxBMuC2IGTUUX3ohcBhv+SBaNEF8DWS4nXwNlTPvfPHRFzJ88dRrPn31zT+ffSF+cN9DIXhA3zYgQzhRyBs0aL77/Er/3UcA4w9fuuiZ628H8zVwHxxs4Lr+meEGdNBCAP3nP+Ttr3L6S+ACD2i59LnPbew7H/II2LY7zMB19Auh0c4mk0J40HVuK98FM5fBtqWPDMzbAe7KR4YWoq8GY9CCDB1oBxW2jYVD0GD6/3K4QwPu8G2ZY54G23Y/MojwiTpjESHK8MG3vfCHlWMf97AXwxkGUItbJCLuMndFHoKRe2LkYRfhRkMavu13OhAAFOcIst41MYXqM2P74LbGF7Lvgm1bY+bcqEdAFkKQEDTDEd2mRDgs8oR6o6Mk+yW53wWvEC1MXyKPF0gd8qGLDexkEbMYxBtuUnOH9CQPb2hIF2LPbfCT3yRniavVta5t5avBDuRQuu0VT4YA/CXutPc8XepBlaLL5S57WcwdHHOUmktf8ZbIvuPZbpiRpKU210ZCAFxujbRj3B/pts1yZql3jANnOBU3u7iZ850pAp3i1LnOwKWPg+SEpz7hI//PevqTcfsM6HX6+c+CCk6gCM0VQRLK0Hd2raEQTd1DI0rRx020ohhVm9gyylHIXbSjIP1cN0NKUo2OtKQoBdtGlWLQfza0pTDNZkI/+pOYrvOlNi0oRGnqk5zSDqc+redOT8rSoAaunbljywAIUAA5DsUABxgLAtiSgKkSRQFjWcBRGNDUu73hkkZ9HSff1tCVJiWsbfOlMOGG1La1ZalNHUoDHPCAuFT1KArQqlG4KgC7/UEIqDSoWnWJTSwGNqkM5WlP3sY6FNp0eItEYuWuSdbIGAACEbCrVYuS16121W2NfdsdwBpTyBZWeqWjrFvLStSz+g54jFQg9wS4gzz/DKEGGvQlBxGZwOvlkZiLVGvpYpm7pY5FApkFgAIQgNUATNW4YwlAXXECXekCYKkTcEAAKFAB5TJXqjJprl6vS4Dsbre71bUuAC6b3PB+17kyScBxM8sACxAgANnlbnzHEtfOzqSq0EWue5tr1bsuVb84YUB0N6vc8QY4s0udLl/3y9++vpa0oe3kFmlrW9wSs3i7RSb4fJs90wqvmMcjrkwRqlieMBF/JwamFjQ4wCLY4YWgpCBvFShAfJqYmA/UHCQLsVSrKqC/AWiqAS5QgQRotcg68e9MjFvXIwsAq0pm8kykfODudtbJ5J1qc6MrYCwLYMkVUMB0E9BUBT8g/wEUwAAB6gpmAIB5wvHNAAGMjOQsdxfABBjvTRiA4Jr4F8rKbWqEZTLhOvP1xePrIA9i/MkZ/9AGNh6iJ/2HyP/xeIOu+3EDZwfJsi40KiasYmzdZ+LkRU+FhFThjndIyB/7cJWQLgR7ZdIADXg5rr2W83SlfBMFCHjKc+a1r60MgF53N7wOTjYAuLoBaUt511sGtq9pwle+5rXIzpYJew3AgSsjYKkdwKyyfy3HcCfAA4HeyV2LrVdsO3vR025quPPd1yG7bYrjYx6rFTlG9ZWPCHmUtYg9TWv12TqAhn1bE4dalSmqOq0EV2LE3VZGhY8ygQ2/4I/LODs48gHPi/8mtp2fvOediFcm+Ha2yrccbQk3FcyIXq+6aV4TqEa3zU399p59Hl3uNuADIAiBCDYwAhLElbxVFvR+C30TfNvEvyifM775im2+WtyxTLwdpTWO6xtmz+NjBF/IgTtMko8Vjk6daWuRkmvpce+I7Yw19GaNuzI+PHuwG7K9t61y+WbVJ0tl+XTZO3NoI9vmAjB8AASNbccj26rdDvoCwL1tmhy9BCY4AQo+UIKdh3vmVSX0s6vecnqL2/S+3rqSd27lurftq2y9+zBnp3cYLryPDic4LvNI6ipSXCpus+T6hhDpjYuSDwwkePkQ6Elw/jiT3Xtf/NyGaDbLkdhHb6//5x1wbEMrPr5abXyDH8/oAqTgA+KfSeXXP5O51nWuQL/y5lte5ymPQAUPwAArwAHVVmD9JXV2JmYINlflZ2zxZ3ndF1dgBlWK1nIK9lmFoHwZCFhvwz7NV3Y5lkgyNH0MV2k7dH2lpEmao2KmNndHwVi3hDxbBE18JFvHo0y8JEFrNVgNp0u8ZAeq5VZzVWGWt2XRNXn1R37JVV1xVV16pXLpdX78NmZI+Hrip3IKtl0sUG7etn/PdV+HZ2fINVdaNYRJ5lSoZ2TWxYBXuGDkdYRlqF1nKG5RJQElUG7rFVUF0AIYWAihdQeTlnu6V3appDzD54Pgo3s86EWIaDlB/3h8qCY3t3Zri0NPjfMTaMZrdAUUVocUmdhsm6gtcpNheHRBlDhPqrQ5kAgVc1NGZVSJqaiKPqF64qZlP9GJW4Vgn5gtgOOKv4WKkQVQrFUVdDM9r9Q4liiMPjFmVNcTuIhXRbd6vAg4xviLwHhai9OCxIhWl8hQ3PhTw5hS4og2LTaO5sg1LniO6lg0ZrWO7jhC7xiPvJOO8liP/1KO9piPKtKO+tiPtUSPRPGNYeWPayGQBrliPQOQQ3GQPkWQasGQ3zgyCikUEGlTDpkWFYlWdcGPRvFPoXSI1rhObYWQQwFXcfcUL7dXT2cdRCdmYQhzTHWSzihtVmGSOYE3uP+3OR8Jkh9IOyMpkdt4OUj1kTY0OLOziGw1VohlFDZZFer3E3j2E/M2FFO5FVWpidNFE8TWlLdIk0RxldQVkzfpVxwoOENJQR1YSoQDO0gpWYe1WnSBj2PZNqR4VEopiSGZN2cZjIYVhCQZF085iyvpE2AplQymFWA5f0XIlF5JlYdZFDAIdiZEWtR4l3jZk5gzVj+WlI4odnA5F3KJE8kHWxgHYijkSB12PtI0iN+DSoRUmjWAT+RjPqu5Vmx3YtwzXNvnVtVVV9ilXQi2XASmXC/5m+cFk0eYlTaRhfDFc8gZAAIme04leYNJE71JnHD4X/PlXcNJhVVoZ0QonOD/FZbmVWiGF3RH+J2KSX/YOV5QVWDlZ50EoGfH+YmZeJ3emX7vpYbqeQDwqTkaCFox+GGxWUC1dVuquUWEJYPZh0f58zyymUseVpsLupnCtYK7uZGnxoq1A2NuEweuwzz7g2k3Rnx3mUwKukMgmkqlY0GcCTeihpaBp2rsiZxV1mdnZotcRgD65V8413qD1owQ2HpWJp0r134y6Zw1cZVqFl/o6WfxdZh1BmZmtos10WX0N6V6VZXe2YBSR2xoZqXyaV2dhWj+1Xhceoa7CKZMhma25zaA+DYrKqJ2UGMlCngneoMpygdzqgUtCnEvSmkYF2RwanwbOZFBkWqS6ZYy//hqgNpWvJeXPLSZzodxR3Rrs3NHyhWfMUd42vZsOwp5+PaUhUl/g1cB0rlvUWlo8RmlOeFtn+qq9dd5jBerrAd5+7ZriblzWvmlX+oCL/CY7Mdv/DaqrZqAWmmrNapcwDpViho3ACc7leNqs7lCeRpYr+mWlFqpD2etgTVxh1pxVCSZZ0Sogcqowxdp5VqC52qpbWeiqGRy4RWGneplCMieRvqjU0V00dmYzpl1DyCdXRdX/JpcKSmr8hdVFYamDEZ0Y8FdM1ewUIekDnucu/qAKgeFYkmH82WkzkZucXewyKqk5nelYvl1ceOX6wqp1/o2r7muydhOIwevbwQEcf8UrsjXoePDPjTIsm8ZqTs7BNC0rXs5TIQ0oygkn1K4a40XqkgKni9pEzlXsjqXXPcmbVxHe9UptfGGsGGGpAzreZ1HsjmRqmP7X4+5nhnrq/3HtZAXpcvFtePFpfe6rHkFZm/6PqTFs2lXpy3broTEt5MqfO36d85XanG5oU8xmpfUQiRIiC4km6jlmr/luNnnolYkubeJSSmYfSq2YmUqbXXWtDWHpOHXEw7oel9rZxKoVRQoAFB2gUl6dePFbFgJippXbIPZtssalm/Lu4kmk2rrq9w2hqHotk+7XhcAAw/Inrbbu4vJAMb7AIybfGW5fOdDgm2FB5pLiIFbSo//i7mZC3YomKAplqE4G4l0GYPSdANtYK5v40sytkUclK3t+77RZD6wKUNIiYOT5Zltw4Ry5IQkG4UTS6xUWLfzCl4GbIZP53N2iIc+t4db+4ZE+Jx6lYUUsIX6x3r0CoZIGJjDOmEEbMHuyatRaMBuxmjqZbJvS17R9nNxB13pJ3UqrF4KNj4ZFqdwc7/TepfyW2n0i0Kv6cP5O024qUt7UEwL6r+diU1AmbMZaVC+yxSfaH/69IxBCTd1OcXgGJeIChReTMW3yhS0qLzSWE7PixVj3JCJG8Y11cb+VMVMwYxprE1YdaxWIccWmb4cysfhdJFoAcgwFcWCfMi2EpqI/7zI3KK4jPzIJ6LIkDzJgSHJlHzJfGHJmLzJb8zJnpwrcPzJonwXBEXIgTzK8ljKphw7qByP6LTKQtXK76jKb5OT6PpPI3nKsryOtOw71zu5BpXLX7zL52hLkjlaciPMpqPMi/OIxFzMjuxNF8bFA3qhP2SI1iM+15x92XyM1tyaqURieDRNZTRO2idLz2yO9uOhohWIQGY5/HNKANRjbmBAiegE0Rdk9uxpE/SngGRDA+RYiJvO48g2/vZvZTA+mEpKQrR3qlQ+YqCWOLZwCz0HEu3QQ0u4hkV2bgOuBC2OUjSuKQvAbmcHZ8RFD10DS3BbW+R70OR2Fq2gLt239OnURtYIdx+dUnbEzrdHWkdLSq0EfBEd1BRtohZN1BkdWY0UWQOd0yVVSaS5gYGFfaf0Nl0UQ0s8Vp1G1XWAlrFFg+LrSr/Igk6NUsYsaXHjxARKWMFUoc1kgn07m42oVjJGgzeExJzLSc5c1iRVyl0cOMkYVuY8N3zd19FcT4FtVMpc2H3dTYgdi2h1T4sKN4wdUr0My91Y2RylyZrd2T7BkZ4d2k3B2aJd2jZB2qad2gLxEqzdEq7d2rD92rId27Q927Zd27h927qd27y9277t270d3MA93MJd3MR93Mad3Mi93Mrd3CEREAA7" v:shapes="_x0000_s1516">

- **修改处理器**
- **在启动类添加注解\****@SpringCloudApplication**
- **fallbackFactory** **服务降级**

当一个服务同时存在类级别与方法级别的降级时，方法级别的降级优先级高。

## 4.4.1 创建消费者工程 04-consumer-fallbackfactory-8080

- **创建工程**

复制 04-consumer-hystrix-8080 工程，并重命名为 04-consumer-fallbackfactory-8080。

## （2） 定义降级处理类

- **修改** **Feign** **接口**
- **创建消费者工程** **04-consumer-fallbackfeign-8080**

该实现方案会直接将 fallbackMethod 的服务降级给屏蔽掉。

## （1） 创建工程

复制04-consumer-fallbackfactory-8080 工程，并重命名为 04-consumer-fallbackfeign-8080。

## （2） 定义降级处理类

- **修改** **Feign** **接口**

将原来的 fallbackFactory 属性更换为 fallback 属性。

## 4.5 Hystrix 高级属性配置

- **执行隔离策略**

对依赖的请求数量进行限制的这种机制，称为执行隔离。 执行隔离策略有两大作用：防止服务熔断，防止服务雪崩。

## （1） 类型

隔离请求的方式有两种类型：

- 线程隔离 thread：Hystrix 的默认隔离策略。系统会创建一个依赖线程池，为每个依赖请求分配一个独立的线程，而每个依赖所拥有的线程数量是有上限的。当对该依赖的调用请求数量达到上限后再有请求，则该请求阻塞。所以对某依赖的并发量取决于为该依赖所分配的线程数量。

l 信号量隔离：对依赖的调用所使用的线程仍为请求线程，即不会为依赖请求再新创建新的线程。但系统会为每种依赖分配一定数量的信号量，而每个依赖请求分配一个信号号。当对该依赖的调用请求数量达到上限后再有请求，则该请求阻塞。所以对某依赖的并发量取决于为该依赖所分配的信号数量。

## （2） 修改策略

若是在配置文件中，则可以通过以下设置修改：

- command.default.execution.isolation.strategy=thread
- command.default.execution.isolation.strategy=semaphore

若是在代码中，则可通过以下语句修改。

- Setter().withExecutionIsolationStrategy(ExecutionIsolationStrate gy.THREAD)
- Setter().withExecutionIsolationStrategy(ExecutionIsolationStrate gy.SEMAPHORE)

## （3） 默认值

在 HystrixCommandProperties 类的构造器中设置有这些高级属性的默认值。

## 4.5.2 执行隔离其它属性

- **线程执行超时时限**

在默认的线程执行隔离策略中，关于线程的执行时间，可以为其设置超时时限。当然， 首先通过下面的属性开启该超时时限，该属性默认是开启的，即默认值为 true。若要关闭， 则可以配置文件中设置该属性的值为 false。

hystrix.command.default.execution.timeout.enabled

在开启了执行线程超时时限后，可以通过以下属性设置时限长度。

hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds

其默认值为 1000 毫秒。这就是前面引入时为什么 sleep(4)是也报超时异常的原因，只要超过了 1 秒就会超时。

## （2） 超时中断

当线程执行超时时是否中断线程的执行。默认为 true，即超时即中断。通过以下属性进行设置。

hystrix.command.default.execution.isolation.thread.interruptOnTimeout

## （3） 取消中断

在线程执行过程中，若请求取消了，当前执行线程是否结束呢？由该值设置。默认为 false， 即取消后不中断。通过以下属性进行设置。

hystrix.command.default.execution.isolation.thread.interruptOnCancel

## （4） 信号量数量

若采用信号量执行隔离策略，则可通过以下属性修改信号量的数量，即对某一依赖所允许的请求的最高并发量。hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests

## 4.5.3 服务熔断属性

- **熔断功能开关**

设置当前应用是否开启熔断器功能，默认值为 true。

hystrix.command.default.circuitBreaker.enabled

## （2） 熔断器开启阈值

当在时间窗内（10 秒）收到的请求数量超过该设置的数量后，将开启熔断器。默认值为 20。

注意，开启熔断器是指将拒绝所有请求；关闭熔断器是指将使所有请求通过。

hystrix.command.default.circuitBreaker.requestVolumeThreshold

## （3） 熔断时间窗

当熔断器开启该属性设置的时长后，会尝试关闭熔断器，以恢复被熔断的服务。默认值为 5000 毫秒。hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds

## （4） 熔断开启错误率

当请求的错误率高于该百分比时，开启熔断器。默认值为 50，即 50%。

hystrix.command.default.circuitBreaker.errorThresholdPercentage

## （5） 强制开启熔断器

设置熔断器无需条件开启，拒绝所有请求。默认值为 false。

hystrix.command.default.circuitBreaker.forceOpen

## （6） 强制关闭熔断器

设置熔断器无需条件的关闭，通过所有请求。默认值为 false。

hystrix.command.default.circuitBreaker.forceClosed

## 4.5.4 线程池相关属性

关于执行线程的线程池，可以通过以下的这些属性设置。

## 4.6 Dashboard 监控仪表盘

Hystrix Dashboard 仪表盘用于以 GUI 的形式展示消费者的执行情况，包括其处理器方法与 Service 方法的调用执行情况，及熔断器 CircuitBreaker 的状态等。当然，这些显示出的数据都是在指定时间窗内的执行情况及状态信息。

## 4.6.1 定义消费者工程 04-consumer-dashboard-8080

Hystrix-dashboard 用于监控 Hystrix 服务降级情况，所以应添加在消费者工程中。

## （1） 创建工程

本例完全可以直接在 04-consumer-fallbackfeign-8080 工程中进行修改，为了便于演示， 这里又新建了一个工程。复制工程 04-consumer-fallbackfeign-8080，并重命名为

04-consumer-dashboard-8080。

## （2） 添加依赖

org.springframework.cloudspring-cloud-starter-netflix-hystrix-dashboardorg.springframework.bootspring-boot-starter-actuator " src="data:image/png;base64,R0lGODlhzQI/AXcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AvwAhwAAAAAAAAgKDQ0KCAMAAAQIDQ0LCwgDAAAECggDAwAABAoLDQ0LCgQABA0IBAoEAwgICAADCAsLDQMGCwgGAwsLCwsGAwoEAAMEBAgEAAQAAAAAAwQAAwMAAwQICgMDCAsLCgMABAoLCgMGCggDBAMECgQDAwoIBAoGBAQGCggGBAoKCAMGCAQGCAgEBAQDBAoLCwsGBAQEAwQDCAQEBAgKCg0LOQ0LJQAANQAAYAAAlAAAgAAApw0kTA05XwA1uQBgqgBgzDkLDSULDTUAACULJTUAgDUAlDU1hiVMTDlMTCVMcDlegTVgpzWGuTWGzjWG3TWGzEwkDV85DUw5TExMOV9MTF9ecExMcF9egV9wTExwX0xwcExwgV+BX1+BcF+BgWAAAGAAYGAAgHBMJXBMOXBMTHBwTHBwX2BglGBggHCBX3CBcHCBgWCGuWCq74Y1AIY1NYY1gIFeOYFwTIGBX4GBcIGBgYaGp4aquYbO3YbOzIbO76pgAKpggKrOhqrOuarv3arv786GNc6GlM7v3c7vzM7v7++qYO+qp+/Ohu/Oue/vqu/vzu/vzO/v3e/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEIHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEMmDBAAQMmTJlOiXKmyJcuXLmPCnCmzJs2bNnPi3Kmz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" v:shapes="_x0000_s1520">

- **修改配置文件**

在配置文件中添加如下内容，用于开启 actuator 的所有 web 终端，并调整 Hystrix 隔离线程执行的超时时限。

## （4） 修改启动类

- **定义提供者工程** **04-provider-8081**
- **创建工程**

复制 03-provider-8081 工程，并重命名为 04-provider-8081。因为 03-provider-8081 工程的 Service 接口实现类中的 getDepartById()方法中具有 sleep()休眠功能。

## （2） 修改Service 实现类

这里仅修改 getDepartById()方法。

为了测试方便，这里将 id 值设置为 sleep()的时间，这样设置没有业务上的意义。

为了演示效果，这里测试时 id 的取值为 2、4。当取 2 时，不会超时，但当取 4 时，隔离线程执行会超时。

## 4.6.3 仪表盘

- **GUI** **介绍**
- **关于** **Turbine**

前面的方式是对单个消费者进行监控，我们也可以对集群进行整体监控。此时就需要使用 Turbine 技术了。Turbine 能够汇集监控信息，并将聚合后的信息提供给 Hystrix Dashboard 来集中展示和监控。

使用Turbine 对集群进行监控的实现步骤很简单，只需三步：

- 导入Turbine 依赖
- 在配置文件中配置 turbine
- 在启动类上添加@EnableTurbine 注解

由于时间关系，我们这里就不再讲解 Turbine 的使用了，大家在百度上直接搜索“Hystrix Turbine”，有非常多的文章可参考。

## 4.7 服务降级报警机制

无论哪种原因启用了服务降级，系统都应该向管理员发出警报通知管理员，例如向管理员发送短信。这种发生服务降级后向管理员发出警报的机制称为服务降级报警机制。

## 4.7.1 创建工程 04-consumer-fallbackalarm-8080

复制前面任意的服务熔断消费者工程，这里复制 04-consumer-feign-fallbackmethod-8080

工程，并重命名为 04-consumer-fallbackalarm-8080。

## 4.7.2 添加依赖

org.springframework.bootspring-boot-starter-data-redis " src="data:image/png;base64,R0lGODlhzQKiAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AoAAhwAAAAAAAAgKDQ0KCAMAAAQIDQ0LCwgDAAAECggDAw0LCgQABAoLDQ0IBAoEAwgICAADCAsLDQMGCwgGAwsLCwAABAsGAwoEAAMEBAgEAAQAAAAAAwQAAwMAAwQICgMDCAsLCgMABAoLCgMGCggDBAgKCgMECgQDAwQEBAoGBAQGCggGBAoKCAMGCAQGCAgEBAQDBAQICw0LOQ0LJQAANQAAYAAAlAAAgAAApw0kTA05XwA1uQBgqgBgzDkLDSULDTUAADUAgDUAlDU1hjlMTCVMcDlegTVgpzWGuTWGzjWG3TWGzEwkDV85DV85OUw5TExMOUxwcExwgV+BX1+BcF+BgWAAAGAAYGAAgHBMJXBMOXBwTGBglGBggHCBX3CBcHCBgWCGuWCq74Y1AIY1NYY1gIFeOYFeTIFwTIGBX4GBcIGBgYaGp4aquYbOzIbO3YbO76pgAKpggKrOuarv786GNc6GlM7v3c7v7++qYO+qp+/Ohu/Oue/vqu/vzu/vzO/v3e/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEIHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEMmDBAAQMmTJlOiXKmyJcuXLmPCnCmzJs2bNnPi3Kmz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" v:shapes="_x0000_s1522">

- **修改配置文件**
- **修改处理器**

修改处理器，首先创建一个线程池，包含 5 个线程，然后再修改服务降级方法。

然后再定义两个方法。

第5章 **微服务网关** **Zuul**

## 5.1 简介

- **网关简介**

网关是系统唯一对外的入口，介于客户端与服务器端之间，用于对请求进行鉴权、限流、路由、监控等功能。

## 5.1.2 Zuul 官网简介

【原文】Zuul is the front door for all requests from devices and web sites（设备和 web 站点） to the backend of the Netflix streaming application（Netflix 流应用后端）. As an edge service application（边界服务应用）, Zuul is built to enable dynamic routing, monitoring, resiliency and security. It also has the ability to route requests to multiple *Amazon Auto Scaling Groups* as appropriate（视情况而定， 酌情）.

【翻译】ZUUL 是从设备和 web 站点到 Netflix 流应用后端的所有请求的前门。作为边界服务应用，ZUUL 是为了实现动态路由、监视、弹性和安全性而构建的。它还具有根据情况将请求路由到多个 Amazon Auto Scaling Groups 的能力。

## 5.1.3 Zuul 综合说明

Zuul 主要提供了对请求的路由与过滤功能。

- 路由：将外部请求转发到具体的微服务实例上，是外部访问微服务的统一入口。
- 过滤：对请求的处理过程进行干预，对请求进行校验、 鉴权等处理。

将官方的架构图再进一步抽象，就变为了下图。

## 5.2 基本环境搭建

这里需要一个 EurekaServer，一个 zuul 网关，及两个消费者。

## 5.2.1 创建工程 05-zuul-consumer--8080

- **创建工程**

复制工程 04-consumer-feign-fallbackmethod-8080，并重命名为 05-zuul-consumer--8080。

## （2） 修改配置文件

- **修改处理器**
- **创建工程** **05-zuul-consumer--8090**
- **创建工程**

复制工程 05-zuul-consumer--8080，并重命名为 05-zuul-consumer--8090。

## （2） 修改配置文件

- **修改处理器**
- **服务路由** **00-zuul-9000**

当用户提交某请求后，该请求应交给哪个微服务的哪个主机来处理，通过配置可以完成。

## 5.3.1 创建zuul 网关工程

- **创建工程**

创建一个Spring Initializr 工程，命名为 00-zuul-9000，导入 Eureka Discovery 与Zuul 依赖。其中 Eureka Discovery 依赖即为 Eureka Client 依赖。

## （2） 修改启动类

- **定义配置文件**
- **路由策略配置**

**（\****1***\*） 修改配置文件**

前面的访问方式，需要将微服务名称暴露给用户，会存在安全性问题。所以，可以自定义路径来替代微服务名称，即自定义路由策略。

在配置文件中添加如下配置。

## 5.3.3 路由前辍

在配置路由策略时，可以为路由路径配置一个统一的前辍，以便为请求归类。在前面的配置文件中增加如下配置。

## 5.3.4 服务名屏蔽

前面的设置方式可以使用指定的路由路径访问到相应微服务，但使用微服务名称也可以访问到，为了防止服务侵入，可以将服务名称屏蔽。

在配置文件中添加如下内容：

## 5.3.5 路径屏蔽

可以指定屏蔽掉的路径 URI，即只要用户请求中包含指定的 URI 路径，那么该请求将无法访问到指定的服务。通过该方式可以限制用户的权限。

## 5.3.6 敏感请求头屏蔽

默认情况下，像 Cookie、Set-Cookie 等敏感请求头信息会被 zuul 屏蔽掉，我们可以将这些默认屏蔽去掉，当然，也可以添加要屏蔽的请求头。

## （1） 修改 05-zuul-consumer-8080 工程

修改该工程处理器中的方法。

## （2） 修改 00-zuul-9000 配置文件

- **负载均衡**

用户提交的请求被路由到一个指定的微服务中，若该微服务名称的主机有多个，则默认采用负载均衡策略是轮询。

## （1） 创建三个消费者工程

这里要创建三个工程。这三个工程均复制于 04-consumer-fallbackmethod-8080 工程。

### A、 创建 05-consumer-8080

修改处理器：

### B、 创建 05-consumer-8081

修改配置文件：

修改处理器：

### C、 创建 05-consumer-8082

修改配置文件：

修改处理器：

## （2） 修改配置文件

在配置文件中添加如下内容：

## （3） 修改负载均衡策略

修改00-zuul-9000 的启动类，在其中直接添加如下代码，更换负载均衡策略为随机算法。

## 5.3.8 服务降级

当消费者调用提供者时由于各种原因出现无法调用的情况时，消费者可以进行服务降级。那么，若客户端通过网关调用消费者无法调用时，是否可以进行服务降级呢？当然可以，zuul

具有服务降级功能。

## （1） 创建工程 00-zuul-fallback-9000

复制 00-zuul-9000 工程，并重命名为 00-zuul-fallback-9000。

## （2） 定义 fallback 类

这里仅仅是对名称为 abcmsc-consumer-depart-8080 的微服务进行降级处理。

## （3） 修改配置文件

这里其实并没有对配置文件进行设置，仅仅是将一些不必要的设置给删除了。

## 5.4 请求过滤

在服务路由之前、中、后，可以对请求进行过滤，使其只能访问它应该访问到的资源， 增强安全性。此时需要通过 ZuulFilter 过滤器来实现对外服务的安全控制。

## 5.4.1 路由过滤架构

- **路由过滤实现** **00-zuul-filter-9000**
- **创建工程**

复现 00-zuul-9000 工程，并重命名为 00-zuul-filter-9000。

## （2） 定义 RouteFilter 类

该过滤的条件是，只有请求参数携带有 user 的请求才可访问端口号为/abc8080 工程， 否则返回 401，未授权。当然，对/abc8090 工程的访问没有限制。简单来说就是，只有当访问/abc8080 且 user 为空时是通不过过滤的，其它请求都可以。

## 5.5 令牌桶限流

通过对请求限流的方式避免系统遭受“雪崩之灾”。

我们下面的代码使用 Guava 库的RateLimit 完成限流的，而其底层使用的是令牌桶算法实现的限流，所以我们先来学习一下令牌桶限流算法。

## 5.5.1 原理

- **令牌桶算法**
- **扩展：漏斗限流算法**
- **实现** **00-zuul-tokenbucket-9000**
- **创建工程**

复制工程 00-zuul-filter-9000，并命名为 00-zuul-tokenbucket-9000。

## （2） 修改 RouteFilter 类

这里导入的 RateLimiter 是Google 的类。这里为了测试的方便，使令牌桶每秒仅生成 2

个令牌。即每秒可以处理 2 个请求。

## 5.6 多维请求限流

- **原理**

使用Guava 的RateLimit 令牌桶算法可以实现对请求的限流，但其限流粒度有些大。有个老外使用路由过滤，针对 Zuul 编写了一个限流库(spring-cloud-zuul-ratelimit)，提供多种细粒度限流策略，在导入该依赖后我们就可以直接使用了。

## 5.6.2 实现 00-zuul-ratelimit-9000

- **创建工程**

复制工程 00-zuul-tokenbucket-9000，并命名为 00-zuul-ratelimit-9000。

## （2） 删除 RouteFilter

删除 RouteFilter 类，该工程中不使用。

## （3） 添加依赖

若要使用 spring-cloud-zuul-ratelimit 限流库，首先需要导入该依赖，然后再在配置文件中对其进行相关配置。

com.marcosbarbero.cloudspring-cloud-zuul-ratelimit2.0.5.RELEASE " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJWhI+py+0Po5y02ouz3rz7D4biSJZNEADpqrbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uoQlTJpt9yu9wsOi8fkcpllTqvX7Lb7DY+3AwUAOw==" v:shapes="_x0000_s1541">

## （4） 修改配置文件

在配置文件中添加如下配置。

## （5） 添加异常处理页面

在 src/main/resources 目录下再定义新的目录 public/error，必须是这个目录名称。然后

在该目录中定义一个异常处理页面，名称必须是异常状态码，扩展名必须为 html。

## 5.7 灰度发布

- **原理**
- **什么是灰度发布**

灰度发布，又名金丝雀发布，是系统迭代更新、平滑过渡的一种上线发布方式。

## （2） Zuul 灰度发布原理

生产环境中，可以实现灰度发布的技术很多，我们这里要讲的是zuul 对于灰度发布的实现。而其实现是基于 Eureka 元数据的。

Eureka 元数据是指，Eureka 客户端向 Eureka Server 中注册时的描述信息。有两种类型的元数据：

- 标准元数据
- 自定义元数据

## 5.7.2 修改三个消费者工程

- **修改** **05-consumer-8080**
- **创建** **05-consumer-8081**
- **创建** **05-consumer-8082**
- **创建\****zuul** **工程** **00-zuul-gray-9000**
- **创建工程**

复制工程 00-zuul-9000，并重命名为 00-zuul-gray-9000。

## （2） 添加依赖

在 pom 文件中增加新的依赖。

io.jmnarlochribbon-discovery-filter-spring-cloud-starter " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJMjI+py+0Po5y02ouz3rz7D4biSJYMEADqyrbuC8fyTNf2jef6zvf+DwwKh8Si8YhMvgzKpvMJjUqn1Kr1is2mUtmu9wsOi8fkcjhQAAA7" v:shapes="_x0000_s1542">

2.1.0 " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJcBHKJq83vIpyy0msz3rrz74XgKJbkaaboqrbs68bwXMo2jd96zu9+D/wJg8ShsYhEGJbMpvMJjUqn1Kr1is1qt9yu9wsOi8fksvmMTj8Z6rb7DY/L5/S6/Y7HBwoAOw==" v:shapes="_x0000_s1050">

## （3） 修改配置文件

将配置文件内容修改如下（仅仅就是将之前配置的那些多余内容删除了）：

## （4） 定义过滤器

- **定义过滤器\****(***\*另一种灰度发布规则\****)**
- **Zuul** **的高可用**

Zuul 的高可用非常关键，因为外部请求到后端微服务的流量都会经过 Zuul。故而在生产环境中，我们一般都需要部署高可用的 Zuul 以避免单点故障。

作为整个系统入口路由的高可用，需要借助额外的负载均衡器来实现，例如 Nginx、HAProxy、F5 等。在 Zuul 集群的前端部分部署负载均衡服务器。Zuul 客户端将请求发送到负载均衡器，负载均衡器将请求转发到其代理的其中一个 Zuul 节点。这样，就可以实现 Zuul 的高可用。

# 第6章 分布式配置管理 Spring Cloud Config

集群中每一台主机的配置文件都是相同的，对配置文件的更新维护就成为了一个棘手的问题，Spring Cloud Config 是负责 Spring Cloud 中配置文件维护管理的配置中心。

## 6.1 spring cloud config 概述

- **官网介绍**

【 原 文 】 Spring Cloud Config provides server and client-side support for externalized configuration in a distributed system. With the Config Server you have a central place to manage external properties for applications across all environments.

【翻译】Spring Cloud Config 为分布式系统中的外部化配置提供服务器和客户端支持。使用

Config 服务器，可以在中心位置管理所有环境中应用程序的外部属性。

## 6.1.2 统合说明

Spring Cloud Config 就是对微服务的配置文件进行统一管理的。其工作原理是，我们首先需要将各个微服务公共的配置信息推送到GitHub 远程版本库。然后我们再定义一个 Spring Cloud Config Server，其会连接上这个 GitHub 远程库。这样我们就可以定义 Config 版的 Eureka Server、提供者与消费者了，它们都将作为 Spring Cloud Config Client 出现，它们都会通过连接 Spring Cloud Config Server 连接上 GitHub 上的远程库，以读取到指定配置文件中的内容。

## 6.1.3 原理

Config Server 可以组装的最终配置文件格式有三种：yml、properties、json。

## 6.2 创建配置文件工程 00-configserver-9999

- **创建工程**

创建一个 Spring Initializr 工程，命名为 00-configserver-9999，其仅需要一个 Config Server

的依赖。

## 6.2.2 定义配置文件

- **定义启动类**
- **修改\****windows** **的** **host** **文件**

在其中添加如下内容：

## 6.3 定义 Config 版的Eureka 服务器 06-config-eurekaserver

- **创建工程**

复制 00-eurekaserver-8000 工程，并重命名为 06-config-eurekaserver。

## 6.3.2 添加 config 客户端依赖

在原工程依赖的基础上添加 spring cloud config 的客户端依赖。

org.springframework.cloudspring-cloud-starter-config " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJrjI+py+0Po5y02ouz3rz7D4biSJYMYKBqsLbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uqQBchqt9yu9wsOi8fksvmMTqvX7Lb7DY/L5/S6/Y7nwvL8vv8PGCg4SFhoeIgYUAAAOw==" v:shapes="_x0000_s1547">

## 6.3.3 定义bootstrap.yml

- yml 中配置的是应用启动时所必须的配置信息。
- yml 中配置的是应用运行过程中所必须的配置信息
- yml 优先于 application.yml 进行加载。

## 6.4 定义 Config 版的提供者 06-config-provider

- **创建工程**

复制 03-provider-8082 工程，重命名为 06-config-provider。

## 6.4.2 添加 config 客户端依赖

在原工程依赖的基础上添加 spring cloud config 的客户端依赖。

org.springframework.cloudspring-cloud-starter-config " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJrjI+py+0Po5y02ouz3rz7D4biSJYMYKBqsLbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uqQBchqt9yu9wsOi8fksvmMTqvX7Lb7DY/L5/S6/Y7nwvL8vv8PGCg4SFhoeIgYUAAAOw==" v:shapes="_x0000_s1548">

## 6.4.3 定义bootstrap.yml

- **定义** **Config** **版的消费者** **06-config-consumer**
- **创建工程**

复制 04-consumer-hystrix-8080 工程，并重命名为 06-config-consumer。

## 6.5.2 添加 config 客户端依赖

在原工程依赖的基础上添加 spring cloud config 的客户端依赖。

## 6.5.3 定义bootstrap.yml

- **配置自动更新**
- **Webhooks**

GitHub 中提供了Webhooks 功能来确保远程库中的配置文件更新后，客户端中的配置信息也可以实时更新。具体实现方式可参考如下一篇博文：

https://blog.csdn.net/qq_32423845/article/details/79579341

这种方式存在很大的弊端，并不适合生产环境下的使用，而 Spring Cloud Bus 消息总线系统解决了这些问题。所以，生产环境下一般使用的是 Spring Cloud Bus 完成配置文件的自动更新。

这种方式存在的弊端：

- 每个 Config Client 主机都需要在GitHub 中进行注册
  - 每个 Config Client 需要从 GitHub 上更新配置，都需要提交一个 post 请求，即若有 N 多个 Config Client，则需要提交 N 多个请求

## 6.6.2 Spring Cloud Bus 概述

- **官方简介**

【翻译】用于将服务和服务实例与分布式消息系统链接在一起的事件总线。在集群中传播状态更改很有用（例如配置更改事件）。

## （2） 工作原理

消息总线系统整合了 java 的事件处理机制和消息中间件。

## （3） 配置自动更新原理

- **修改远程库中的配置文件**

为了方便后面的测试，这里分别在提供者配置文件 application-provider-config.yml 与消费者配置文件 application-consumer-config.yml 中各添加了一个自定义属性。

## （1） 修改application-provider-config.yml

- **修改\****application-consumer-config.yml**
- **创建提供者工程** **06 -config-provider-bus**
- **创建工程**

复制 06-config-provider，并重命名为 06 -config-provider-bus。

## （2） 导入依赖

" src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AgMAgAAAAAAAAAJLRIynyesNn4x02oqvznz7Dn5iSI5miZ5qyq5uC79yeQD2jef6zvf+DwwKh8Si8YhMKpfMpvMJjUqn1GpuYc1qt9yu9wsOi8fkcqAAADs=" v:shapes="_x0000_s1556">

org.springframework.cloud spring-cloud-starter-bus-kafka org.springframework.boot spring-boot-actuator " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJVhI+py+0Po5y02ouz3rz7D4biSJZOAKBqyq5uC79yTM92jd96zu9+D/wJg8ShsYg8KpPMpbMJfUqjVGHgaspqt9yu9wsOi8fkMsyMTqvX7Lb7Df8GCgA7" v:shapes="_x0000_s1046">

- **修改配置文件**
- **修改接口实现类**
- **创建消费者工程** **06 -config-consumer-bus**
- **创建工程**

复制 06-config-consumer，并重命名为 06 -config-consumer-bus。

## （2） 导入依赖

org.springframework.cloudspring-cloud-starter-bus-kafkaorg.springframework.bootspring-boot-actuator " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJrjI+py+0Po5y02ouz3rz7D4biSJYMYKBqsLbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uqQBchqt9yu9wsOi8fksvmMTqvX7Lb7DY/L5/S6/Y7nwvL8vv8PGCg4SFhoeIgYUAAAOw==" v:shapes="_x0000_s1557">

- **修改配置文件**
- **修改处理器类**

第7章 **调用链跟踪** **Spring Cloud Sleuth+zipkin**

- **S Sleuth** **简介**

打开官网就可以看到对 Sleuth 的一个简单功能介绍。

【翻译】（Spring Cloud Sleuth 可以实现）针对Spring Cloud 应用程序的分布式跟踪，兼容Zipkin、HTrace 和基于日志的（如Elk）跟踪。

【翻译】Spring Cloud Sleuth 为 Spring Cloud 实现了一个分布式跟踪解决方案，大量借鉴了Dapper、Zipkin 和 HTrace。对于大多数用户来说，Sleuth 是不可见的，并且你的当前应用与外部系统的所有交互都是自动检测的。你可以简单地在日志中捕获数据，或者将其发送到远程收集器中。

## 7.2 Sleuth 基本理论

- **Spring Cloud Sleuth** **文档**

Spring Cloud Sleuth 的官方文档中可以查看到服务跟踪的基本理论。

## 7.2.2 三大概念

服务跟踪理论中存在有跟踪单元的概念，而跟踪单元中涉及三个重要概念：trace、span，与 annotation。

## （1） trace 与 span

- trace：跟踪单元是从客户端所发起的请求抵达被跟踪系统的边界开始，到被跟踪系统向客户返回响应为止的过程，这个过程称为一个 trace。
- span：每个 trace 中会调用若干个服务，为了记录调用了哪些服务，以及每次调用所消耗的时间等信息，在每次调用服务时，埋入一个调用记录，这样两个调用记录之间的区域称为一个 span。
- 关系：一个 trace 由若干个有序的 span 组成。

Spring Cloud Sleuth 为服务之间调用提供链路追踪功能。为了唯一的标识 trace 与 span， 系统为每个 trace 与 span 都指定了一个 64 位长度的数字作为 ID，即 traceID 与 spanID。

## （2） annotation

Spring Cloud Sleuth 中有三个重要概念，除了 trace、span 外，还有一个就是 annotation。但需要注意，这个 annotation 并不是我们平时代码中写的@开头的注解，而是这里的一个专有名词，用于及时记录事件的实体，表示一个事件发生的时间点。这些实体本身仅仅是为了原理叙述的方便，对于 Spring Cloud Sleuth 本身并没有什么必要性。这样的实体有多个，常用的有四个：

- cs
- sr
- ss
- cr

## 7.2.3 Sleuth 的日志采样

- **日志生成**

只要在工程中添加了 Spring Cloud Sleuth 依赖， 那么工程在启动与运行过程中就会自动生成很多的日志。Sleuth 会为日志信息打上收集标记，需要收集的设置为 true，不需要的设置为 false。这个标记可以通过在代码中添加自己的日志信息看到。

## （2） 日志采样率

Sleuth 对于这些日志支持抽样收集，即并不是所有日志都会上传到日志收集服务器，日志收集标记就起这个作用。默认的采样比例为: 0.1，即 10%。在配置文件中可以修改该值。若设置为 1 则表示全部采集，即 100%。

日志采样默认使用的是的水塘抽样算法（统计学）。

## 7.3 “跟踪日志”的生产者 Sleuth

- **创建提供者工程** **07-sleuth-provider-8081**
- **创建工程**

复制 02-provider-8081，并重命名为 07-sleuth-provider-8081。

## （2） 导入依赖

org.springframework.cloudspring-cloud-starter-sleuth " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJWhI+py+0Po5y02ouz3rz7D4biSJZNEADpqrbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uoQlTJpt9yu9wsOi8fkcpllTqvX7Lb7DY+3AwUAOw==" v:shapes="_x0000_s1561">

- **修改处理器**

如果有需要的话，可以在业务代码中添加自己的跟踪日志信息。

## （4） 修改配置文件

将配置文件中有关日志的配置注释，否则看不到后面的演示结果。

## 7.3.2 创建消费者工程 07-sleuth-consumer-8080

- **创建工程**

复制 02-consume -8081，并重命名为 07-sleuth-consumer-8081。

## （2） 导入依赖

org.springframework.cloud " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJWhI+py+0Po5y02ouz3rz7D4biSJZNEADpqrbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uoQlTJpt9yu9wsOi8fkcpllTqvX7Lb7DY+3AwUAOw==" v:shapes="_x0000_s1562">

spring-cloud-starter-sleuth " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJqRI6py+0Po5y02ouz3rz7D4biSJYmAxhqsLbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uqRlcpqt9yu9wsOi8fksvmMTqvX7Lb7DY/L5/S6/d6F4ff8vv8PGCg4SFhoaBhQAAA7" v:shapes="_x0000_s1045">

- **修改处理器**
- **zipkin** **工作过程**
- **zipkin** **简介**

zipkin 是 Twitter 开发的一个分布式系统 APM（Application Performance Management，应用性能管理）工具，其是基于Google Dapper 实现的，用于完成日志的聚合。其与 Sleuth 联用，可以为用户提供调用链路监控可视化 UI 界面。

## 7.4.2 zipkin 系统结构

- **服务器组成**

zipkin 服务器主要由 4 个核心组件构成：

- Collector：收集组件，它主要用于处理从外部系统发送过来的跟踪信息，将这些信息转换为 Zipkin 内部处理的 Span 格式，以支持后续的存储、分析、展示等功能。
- Storage：存储组件，它主要用于处理收集器接收到的跟踪信息，默认会将这些信息存储在内存中，也可以修改存储策略，例如，将跟踪信息存储到数据库中。
- API：外部访问接口组件，外部系统通过这里的 API 可以实现对系统的监控。

l UI：用于操作界面组件，基于 API 组件实现的上层应用。通过 UI 组件用户可以方便而有直观地查询和分析跟踪信息。

## （2） 日志发送方式

在 Spring Cloud Sleuth + zipkin 系统中，客户端中一旦发生服务间的调用，就会被配置在微服务中的 Sleuth 的监听器监听，然后生成相应的 Trace 和 Span 等日志信息，并发送给zipkin 服务端。发送的方式主要有两种，一种是通过 via HTTP 报文的方式，也可以通过 Kafka、RabbitMQ 发送。

## 7.5 zipkin 服务端搭建

- **启动\****zipkin** **服务器**
- **下载**
- **启动**
- **访问\****zipkin** **服务器**
- **创建\****zipkin** **客户端工程\****-via**
- **创建提供者** **07-via-sleuth-provider-8081**
- **创建工程**

复制 07-sleuth-provider-8081，并重命名为 07-via-sleuth-provider-8081。

## （2） 导入依赖

删除原来的 sleuth 依赖，导入 zipkin 依赖。

org.springframework.cloud spring-cloud-starter-zipkin " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJMjI+py+0Po5y02ouz3rz7D4biSJYNEADqyrbuC8fyTNf2jef6zvf+DwwKh8Si8YhMugzKpvMJjUqn1Kr1is2ittqu9wsOi8fk8jRQAAA7" v:shapes="_x0000_s1044">

打开 spring-cloud-starter-zipkin 依赖，可以看到其已经包含了 spring-cloud-starter-sleuth

依赖，所以可以将原来导入的 sleuth 依赖删除。

## （3） 修改配置文件

在 spring 属性下注册 zipkin 服务器地址，并设置采样比例。

## 7.6.2 创建消费者工程 07-via-sleuth-consumer-8080

- **创建工程**

复制 07-sleuth-consumer-8081，并重命名为 07-via-sleuth-consumer-8080。

## （2） 导入依赖

删除原来的 sleuth 依赖，导入 zipkin 依赖。

org.springframework.cloudspring-cloud-starter-zipkin " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AgMAgAAAAAAAAAJARI6py+0Po5y02ouz3rz7D4biSJamA6TqyrbuC8fyTNf2jef6zvf+DwwKh8Si8YhsBQzJpvMJjUqn1Kr1is0aCgA7" v:shapes="_x0000_s1567">

## （3） 修改配置文件

在 spring 属性下注册 zipkin 服务器地址，并设置采样比例。

## 7.7sleuth + kafka + zipkin

默认情况下，Sleuth 是通过将调用日志写入到 via 头部信息中的方式实现链路跟踪的， 但在高并发下，这种方式的效率会非常低，会影响链路信息查看的。此时，可以让 Sleuth 将其生成的调用日志写入到Kafka 或RabbitMQ 中，让 zipkin 从这些中间件中获取日志，效率会提高很多。

## 7.7.1 创建提供者工程 07-kafka-sleuth-provider -8081

- **创建工程**

复制 07-sleuth-provider-8081，并重命名为 07-sleuth-provider-kafka-8081。

## （2） 导入依赖

添加kafka 依赖。

org.springframework.kafkaspring-kafka " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJMjI+py+0Po5y02ouz3rz7D4biSJYMEADqyrbuC8fyTNf2jef6zvf+DwwKh8Si8YhMvgzKpvMJjUqn1Kr1is2mUtmu9wsOi8fkcjhQAAA7" v:shapes="_x0000_s1571">

## （3） 修改配置文件

- **创建消费者工程** **07-kafka-sleuth-consumer-8080**
- **创建工程**

复制 07-sleuth-consumer-8081，并重命名为 07-sleuth-consumer-kafka-8081。

## （2） 导入依赖

添加kafka 依赖。

org.springframework.kafkaspring-kafka " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJcBHKJq83vIpyy0msz3rrz74XgKJbkaaboqrbs68bwbNK2jN96zu9+D/wJg8ShsVhEGJbMpvMJjUqn1Kr1is1qt9yu9wsOi8fksvmMTjsZ6rb7DY/L5/S6/Y7PBwoAOw==" v:shapes="_x0000_s1572">

## （3） 修改配置文件

- **启动运行**
- **zk** **启动**
- **kafka** **集群启动**
- **zipkin** **启动**

在命令行启动zipkin

\> java -DKAFKA_BOOTSTRAP_SERVERS=kafkaOS1:9092 –jar zipkin.jar

## （4） 启动应用

- 启动 Eureka
- 启动提供者工程 07-kafka-sleuth-provider -8081
- 启动消费者工程 07-kafka-sleuth-consumer-8080

# 第8章 消息系统整合框架 Spring Cloud Stream

## 8.1 简介

- **官网**

【原文】A lightweight event-driven microservices framework to quickly build applications that can connect to external systems. Simple declarative（声名式的） model to send and receive messages using Apache Kafka or RabbitMQ between Spring Boot apps.

【翻译】一个轻量级的事件驱动微服务框架，用于快速构建可连接到外部系统的应用程序。在 Spring Boot 应用程序之间使用 Kafka 或RabbitMQ 发送和接收消息的简单声明式模型。

## 8.1.2 综合

Spring Cloud Stream 是一个用来为微服务应用构建消息驱动能力的框架。通过使用Spring Cloud Stream，可以有效简化开发人员对消息中间件的使用复杂度，让系统开发人员可以有更多的精力关注于核心业务逻辑的处理。但是目前 Spring Cloud Stream 只支持RabbitMQ 和 Kafka 的自动化配置。

## 8.2 程序模型

spring cloud 官网首页中点击相应版本的参考文档。

应用程序的核心部分（Application Core）通过 inputs 与 outputs 管道，与中间件连接， 而管道是通过绑定器 Binder 与中间件相绑定的。

## 8.3 stream kafka 微服务

- **消息发送给一个主题的生产者**
- **创建工程** **08-spring-clourd-stream-kafka**

任意复制前面的一个提供者或消费者工程，将其中的除启动类之外的其它代码全部删除， 并命名为 08-spring-clourd-stream-kafka。

## （2） 导入依赖

仅需再添加一个 Spring Cloud Stream Kafka 相关的依赖即可。

整个 pom.xml 文件的内容如下：

## （3） 创建发生产者类

- **创建处理器**
- **创建配置文件**
- **消息发送给多个主题的生产者**

前面工程中通过 Source 的 MessageChannel 完成了将消息发送给某个指定主题的功能， 若要将消息发送给多个主题，则需要自定义 Channel。

## （1） 创建工程 08-spring-clourd-stream-kafka2

复制前面的 08-spring-clourd-stream-kafka，重命名为 08-spring-clourd-stream-kafka2。

## （2） 定义Source 接口

模拟 Source 接口自定义一个 Source 接口。

## （3） 修改发布者类

- **修改配置文件**

在配置文件中添加如下输出目标。

## 8.3.3 创建消息消费者 – @PostConstruct 方式

为了简单起见，这里就不再单独创建消费者工程了，都直接定义在前面的生产者工程中。

Spring Cloud Stream 提供了三种创建消费者的方式，这三种方式的都是在消费者类的“消费”方法上添加注解。只要有新的消息写入到了管道，该“消费”方法就会执行。只不过三种注解，其底层的实现方式不同。即当新消息到来后，触发“消费”方法去执行的实现方式不同。

- @PostConstruct：以发布/订阅方式实现
- @ServiceActivator：新的消息激活服务方式实现
- @StreamListener：以监听方式实现

## （1） 创建消费者类

- **修改配置文件**
- **创建消息消费者** **--** **@ServiceActivator** **方式**

该注解所标注的方法是以服务的形式出现的，只要管道中的数据发生了变化就会激活该服务。

## （1） 注释掉前面的消费者

将前面的消费者类注释掉，其将不会出现在 Spring 容器。

## （2） 创建消费者类

- ***\*创建消息消费者** **--** **@SteamListener** **方式\****

该方式是以监听的方式实现，只要管道中的流数据发生变化，其就会触发该注解所标注的方法的执行。

## （1） 注释掉前面的消费者

- ***\*创建消费者类\****