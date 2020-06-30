**分布式** **RPC** **系统框架**

**Dubbo(2.7)**

**课程讲义**

**主讲：\****Reythor** **雷**

**2019**

**分布式** **RPC** **系统框架** **Dubbo**

第1章 **Dubbo** **概述**

# 1.1 分布技术图谱

- **系统架构的发展**
- **单体系统架构**

当站点功能与流量都很小时，只需一个应用，将所有功能都集中在一个工程中，并部署在一台服务器上，以减少部署节点和成本。例如，将用户模块、订单模块、支付模块等都做在一个工程中，以一个应用的形式部署在一台服务器上。

# 1.2.2 集群架构

当站点流量增加而单机已无法应对其访问量时，可通过搭建集群增加主机的方式提升系统的性能。这种方式称为水平扩展。

# 1.2.3 分布式架构

当访问量逐渐增大，集群架构的水平扩展所带来的效率提升越来越小。此时可以将项目拆分成多个功能相对独立的子工程以提升效率。例如用户工程、订单工程、支付工程等。这种称为垂直扩展。

# 1.2.4 微服务架构

当子工程越来越多时，发现它们可能同时都拥有某功能相同或相似的模块，于是将这些在整个项目中冗余的功能模块抽取出来作为独立的工程，这些工程就是专门为那些调用它们的工程服务的。那么这些抽取出的功能就称为微服务，微服务应用称为服务提供者，而调用微服务的应用就称为服务消费者。

# 1.2.5 流动计算架构

随着功能的扩张，微服务就需要越来越多；随着 PV 的增涨，消费者工程就需要越来越多；随着消费者的扩张，为其提供服务的提供者服务器就需要越来越多，且每种提供者都要求创建为集群。这样的话，消费者对于提供者的访问就不能再采用直连方式进行了，此时就需要服务注册中心了。提供者将服务注册到注册中心，而消费者通过注册中心进行消费，消费者无需再与提供者绑定了。提供者的宕机，对消费者不会产生直接的影响。

随着服务的增多，在一些特殊时段（例如双 11）就会出现服务资源浪费的问题：有些服务的 QPS 很低，但其还占用着很多系统资源，而有些 QPS 很高，已经出现了资源紧张， 用户体验骤降的情况。此时就需要服务治理中心了。让一些不重要的服务暂时性降级，或为其分配较低的权重等，对整个系统资源进行统一调配。

这里的资源调配分为两种：预调配与实时调配。

预调配是根据系统架构师的“系统容量预估”所进行的调配，是一种经验，是一种预处理，就像每年双 11 期间的 PV 与 UV 都会很高，就需要提前对各服务性能进行调配。

实时调配指的是根据服务监控中心所提供的基于访问压力的实时系统容量评估数据，对各服务性能进行实时调配的方案。

# 1.3 小插曲-架构师的基本素养

- **常用术语**
- **系统容量与系统容量预估**

系统容量指系统所能承受的最大访问量，而系统容量预估则是在峰值流量到达之前系统架构师所给出的若干技术指标值。常用的技术指标值有：QPS、PV、UV、并发量、带宽、CPU 使用率、内存硬盘占用率等。系统容量预估是架构师必备的技能之一。

# （2） QPS

QPS，Query Per Second，每秒查询量。在分布式系统中 QPS 的定义是，单个进程每秒请求服务器的成功次数。QPS 一般可以通过压力测试工具测得，例如 LoadRunner、Apache JMeter、NeoLoad、http_load 等。

QPS = 总请求数 / 进程总数 / 请求时间 = 总请求数 / ( 进程总数 * 请求时间 )

# （3） UV

Unique Visitor，独立访客数量，指一定时间范围内站点访问所来自的 IP 数量。同一 IP

多次访问站点只计算一次。一般以 24 小时计算。

# （4） PV

Page View，页面访问量，指一定时间范围内打开或刷新页面的次数。一般以 24 小时计算。

# 1.3.2 系统容量预估基本计算

- **带宽计算**

平均带宽的计算公式为：

假设某站点的日均 PV 是 10w，页面平均大小 0.4 M，那么其平均带宽需求是： 平均带宽 = （10w * 0.4M * 8） / （60 * 60 * 24）= 3.7 Mbps

以上计算的仅仅是平均带宽，我们在进行容量预估时需要的是峰值带宽，即必须要保证站点在峰值流量时能够正常运转。假设，峰值流量是平均流量的 5 倍，这个 5 倍称为峰值因子。按照这个计算，实际需要的带宽大约在 3.7 Mbps * 5=18.5 Mbps 。

带宽需求 = 平均带宽 * 峰值因子

# （2） 并发量计算

并发量，也称为并发连接数，一般是指单台服务器每秒处理的连接数。平均并发连接数的计算公式是：

例如，一个由 5 台 web 主机构成的集群，其日均 PV 50w，每个页面平均 30 个衍生连接，则其平均并发连接数为：

平均并发量 = (50w * 30) / (60 * 60 * 24 * 5) = 35

若峰值因子为 6，则峰值并发量为：

峰值并发量 = 平均并发量 * 峰值因子

= 35 * 6 = 210

# （3） 服务器预估量

根据往年同期活动获得的日均 PV、并发量、页面衍生连接数，及公司业务扩展所带来的流量增涨率，就可以计算出服务器预估值。

注意，今年的页面衍生连接数与往年的可能不一样。

注：统计时间，即 PV 的统计时间，一般为一天

# 1.4 Dubbo 简介

- **官网简介**

Dubbo 官网为 [http://dubbo.apache.org](http://dubbo.apache.org/)。该官网是 Dubbo 正式进入 Apache 开源孵化器后改的。Dubbo 原官网为：[http://dubbo.io](http://dubbo.io/) 。

Dubbo 官网已做过了中英文国际化，用户可在中英文间任何切换。

# 1.4.2 什么是 PRC？

RPC（Remote Procedure Call Protocol）——远程过程调用协议，它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。RPC 协议假定某些传输协议的存在，如 TCP 或 UDP，为通信程序之间携带信息数据。在 OSI 网络通信模型（OSI 七层网络模型，OSI，Open System Interconnection，开放系统互联）中，RPC 跨越了传输层和应用层。RPC 使得开发包括网络分布式多程序在内的应用程序更加容易。

RPC 采用客户机/服务器模式（即 C/S 模式）。请求程序就是一个客户机，而服务提供程序就是一个服务器。首先，客户机调用进程发送一个有进程参数的调用信息到服务进程，然后等待应答信息。在服务器端，进程保持睡眠状态直到调用信息到达为止。当一个调用信息到达，服务器获得进程参数，计算结果，发送答复信息，然后等待下一个调用信息，最后， 客户端调用进程接收答复信息，获得进程结果，然后调用执行继续进行。

# 1.4.3 Dubbo 重要时间点

Dubbo 发展过程中的重要时间点：

- 2011 年开源，之后就迅速成为了国内该类开源项目的佼佼者。2011 年时，优秀的、可在生产环境使用的 RPC 框架很少，Dubbo 的出现迅速给人眼前一亮的感觉，迅速受到了开发者的亲睐。
- 2012 年 10 月之后就基本停止了重要升级，改为阶段性维护。
- 2014 年 10 月 30 日发布 4.11 版本后，Dubbo 停止更新。
  - 2017 年 10 月云栖大会上，阿里宣布 Dubbo 被列入集团重点维护开源项目，这也就意味着Dubbo 起死回生，开始重新进入快车道。
- 2018 年 2 月 15 日，大年三十，经过一系列紧张的投票，宣布 Dubbo 正式进入

Apache 孵化器。

# 1.5 Dubbo 四大组件

Dubbo 中存在四大组件：

- **Provider\****：**服务提供者。

- **Consumer\****：**服务消费者。

- Registry**

  ：**服务注册与发现的中心，提供目录服务，亦称为服务注册中心

  - **Monitor\****：**统计服务的调用次数、调用时间等信息的日志服务，并可以对服务设置权限、降级处理等，称为服务管控中心

# 1.6 版本号

- **Dubbo** **版本号与** **zk** **客户端**

Dubbo 在 2.6.0 及其以前版本时，默认使用的客户端为 zkClient。2.6.1 版本，将默认客户端由 zkClient 修改为 curator。至于 curator 的版本，与 Dubbo 及所要连接的 Zookeeper 的版本有关。目前其支持的版本为 2.x.x 版本，最高版本为 2.13.0。

# 1.6.2 Dubbo 与 Spring 的版本号

Dubbo 的使用是基于 Spring 环境下的，即 Dubbo 是依赖于 Spring 框架的。Dubbo2.7.0 依赖的Spring 是4.3.16。所以，在Dubbo 的开发过程中最好使用与该Spring 版本相同的Spring， 这样可以避免可能的版本冲突问题。

第2章 **Dubbo** **系统框架搭建**

# 2.1 第一个 Dubbo 程序（直连式）

- **创建业务接口工程** **00-api**

业务接口名即服务名称。无论是服务提供者向服务注册中心注册服务，还是服务消费者从注册中心索取服务，都是通过接口名称进行注册与查找的。即，提供者与消费者都依赖于业务接口。所以，一般情况下，会将业务接口专门定义为一个工程，让提供者与消费者依赖。

# （1） 创建Maven 的Java 工程

- **创建业务接口**
- **修改\****pom** **文件**

这个 pom 中无需任何依赖。

4.0.0 com.abc 00-api " src="data:image/png;base64,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" v:shapes="_x0000_s1374">

1.0-SNAPSHOT UTF-8 1.8 1.8 " src="data:image/png;base64,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" v:shapes="_x0000_s1142">

# 2.1.2 创建提供者(自建 Spring 容器)01-provider

- **创建工程**

创建一个 Maven 的 Java 工程，并命名为 01-provider。

# （2） 在 pom 中导入依赖

## A、 依赖导入

主要包含三类依赖：

- 业务接口依赖
- Dubbo 依赖(2.7.0 版本)
- Spring 依赖(3.16 版本)

_<!--_ *dubbo* *依赖* *-->*

<**dependency**>

<**groupId**>com.alibaba</**groupId**>

<**artifactId**>dubbo</**artifactId**>

<**version**>2.7.0</**version**>

</**dependency**>

_<!--_ *Spring* *依赖* *-->*

<**dependency**>

<**groupId**>org.springframework</**groupId**>

<**artifactId**>spring-beans</**artifactId**>

<**version**>${spring-version}</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework</**groupId**>

<**artifactId**>spring-core</**artifactId**>

<**version**>${spring-version}</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework</**groupId**>

<**artifactId**>spring-context</**artifactId**>

<**version**>${spring-version}</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework</**groupId**>

<**artifactId**>spring-expression</**artifactId**>

<**version**>${spring-version}</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework</**groupId**>

<**artifactId**>spring-aop</**artifactId**>

<**version**>${spring-version}</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework</**groupId**>

<**artifactId**>spring-aspects</**artifactId**>

<**version**>${spring-version}</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework</**groupId**>

<**artifactId**>spring-tx</**artifactId**>

<**version**>${spring-version}</**version**>

</**dependency**>

<**dependency**>

<**groupId**>

org.springframework

</**groupId**>

# （3） 定义接口实现类

- **定义\****spring-provider** **配置文件**

在 src/main/resources 下定义 spring-provider.xml 配置文件。

# （5） 定义测试类

在/src/test/java 中创建测试类 RunProvider。

# 2.1.3 创建提供者(Main 启动) 01-provider2

使用自建 Spring 容器方式是比较浪费资源的。容器的作用仅仅就是创建一个单例的提供者对象，其本身并不需要 Tomcat 或 JBoss 等 Web 容器的功能。如果硬要用 Web 容器去加载服务提供方，就增加了代码的复杂性，也浪费了资源。

Dubbo 提供了一个 Main.main()方法可以直接创建并启动 Provider，其底层仅仅是加载了一个简单的用于暴露服务的 Spring 容器。该方式要求 Spring 配置文件必须要放到类路径下的 META-INF/spring 目录中，Spring 配置文件名称无所谓。

# （1） 工程创建

复制 01-provider 工程，并修改其工程名 01-provider2。

# （2） 创建目录并移动配置文件

在 resources 目录中创建 META-INF/spring 目录，并将 spring-provider.xml 配置文件拖入其中。

# （3） 修改启动类

- **创建消费者** **01-consumer**
- **创建工程**

创建一个 Maven 的 Java 工程，并命名为 01-consumer。

# （2） 在 pom 中导入依赖

该工程的依赖与 Provider 中的完全相同，直接复制来就可以。

# （3） 定义spring-consumer 配置文件

- **定义消费者类**
- **Zookeeper** **注册中心**

在生产环境下使用最多的注册中心为 Zookeeper，当然，Redis 也可以做注册中心。下面就来学习 Zookeeper 作为注册中心的用法。

# 2.2.1 创建提供者 02-provider-zk

- **导入依赖**

复制前面的提供者工程 01-provider，并更名为 02-provider-zk。修改 pom 文件，并在其中导入 Zookeeper 客户端依赖 curator。

# （2） 修改spring 配置文件

- **创建消费者** **02-consumer-zk**
- **导入依赖**

复制前面的消费者工程 01-consumer，并更名为 02-consumer-zk。修改 pom 文件，并在其中导入 Zookeeper 客户端 curator 依赖。

# （2） 修改 Spring 配置文件

- **添加日志文件**

通过前面的运行可知，无论是提供者还是消费者，控制台给出的提示信息都太少，若想看到更多的信息，可以在提供者与消费者工程的类路径 src/main/resources 下添加日志文件。可以添加 log4j.xml，即使用 log4j2 日志技术；也可以添加 log4j.properties，即使用 log4j 日志技术。我们这里添加 log4j.properties 文件。

# （1） 提供者添加日志文件

在提供者的 src/main/resources 目录中添加 log4j.properties 文件。运行后可以看到如下的日志输出。其中最为重要的是 provider://xxxxx，这里显示的就是当前工程所提供的能够被订阅的服务描述，即服务元数据信息。另外，我们还可以看到当前应用与 qos-server（Quality of Service 服务器，即 Dubbo 的管控平台）进行通信的端口号为 22222。

# （2） 消费者添加日志文件

在提供者的 src/main/resources 目录中添加 log4j.properties 文件。运行后在控制台的日志输出中可以看到报错。其报错内容原因是，消费者连接 qos-server 的端口号被占用了。其与 qos-server 通信的端口号默认也为 22222，已经被提供者给占用了。当然，原因主要是由于消费者与提供者都在同一主机，若分别存在于不同的主机也不会报错。

所以解决方案就是为消费者修改与 qos-server 通信的端口号。有两种修改方式。可以在

src/main/resources 中新建一个 dubbo.properties 文件，文件内容仅需如下一行：

也可以直接在 spring-consumer.xml 文件中修改。

修改端口号后再运行，就没有了报错。

# 2.3 将 Dubbo 应用到 web 工程

前面所有提供者与消费者均是Java 工程，而在生产环境中，它们都应是 web 工程，Dubbo如何应用于 Web 工程中呢？

# 2.3.1 创建提供者 03-provider-web

- **创建工程**

创建 Maven 的web 工程，然后将 02-provider-zk 中的 Service 实现类及 spring-provider

配置文件复制到当前工程中。

# （2） 导入依赖

这里使用的 Spring 的版本为 4.3.16。需要的依赖有：

- 7.0 版本依赖
- zk 客户端 curator 依赖
- servlet 与 jsp 依赖
- spring 相关依赖
- spring 需要的 commons-logging 依赖
- 自定义 00-api 依赖

<**artifactId**>

spring-beans</**artifactId**>

<**version**>

${spring-version}</**version**>

" src="data:image/png;base64,R0lGODlhbAAWAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgBUAA8AhQAAAAgKDQoEAAQIDQMGCw0LCwgDAAAECg0LCgQAAAoLDQsLDQsGAwAABA0KCAMAAA0IBAAAAw0LOQMGJQAEOQ0LJQ0IXg0KTAgGXgQGTAAAXggITAADTAoKTAAAbwMAbwsGbwQEbwMAgAAAgAQAgAgDgAoEgO/v7+7u7gECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QMBJSBwai8ijMslcOpvQ5xB1qlqv2Kx2y+16v1gheEwum8/otBosXrvf8Lj83J7b71iLJ3DX8/FXdQIDeXt9hnECBF0FBotVFyIjI4YSJJMTFx8YkxRVlpgnmpwjnpGTlZcjmZudVRUlriCpE40HWUIICY+iIhMnfpa/kRkiGgqRA8K9A5HHehu+wHvLxMYKficmHAtWyxbHVQwNClgOD4RWIOF+FpPvxYTC7u8jzSKEeiHse/Tw+NMCLLtiggIsT1YgRPhjRBevdeXaIWJ2Yt5Eivr4BcgGCWAwEr+ugOvwgeG4clkaPXI3ABY1EggpzhII80qyaZxalngZ86YfF1jhrMAawa2KgFthoiiVsrQp06dMUAQBADs=" v:shapes="_x0000_s1470">

</**dependency**

> 

org.springframework</**groupId**> spring-core</**artifactId**>

<**version**>

<**artifactId**>

<**groupId**>

${spring-version}</**version**>

" src="data:image/png;base64,R0lGODlhbAAVAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgBUAA8AhQAAAAgKDQoEAAQIDQMGCw0LCwgDAAAECg0LCgQAAAoLDQsLDQsGAwAABA0KCAMAAA0IBAAAAw0LOQMGJQAEOQ0LJQ0IXg0KTAgGXgQGTAAAXggITAADTAoKTAAAbwMAbwsGbwQEbwMAgAAAgAQAgAgDgAoEgO7u7u/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QMBJSBwai8ijMslcOpvQ5xCAqlqv2Kx2y+16v1gqeEwum8/otBosXrvf8Lj83J7b7/j8uK61eAJ3foB6WgIDWIKBf3ICBFpiBQaOVRciIyOLEiSXExcfGJcUVZqcKJ6gI6KVl5mbI52foVUVJbIgrRORB1sICZOmIhMogprClRkiGgqVA8XAA5XKfhvBw3/Ox8kKiSYcC1bOFspVDA0KVwAOD4dWIOOCFpfyyIfF8fIj0CKHfiHvf/fm7bMWwNkVExRoibICIcIgK71+uTsHbxGlgfYsXuTnwR9FgBqZETRoRVyHDw/LnbMCSVKVeANoXSOx8BmKWwVpXhHpB1TMEhIza/L8Q2ucFVojvFURsCtLEAA7" v:shapes="_x0000_s1471">

</**dependency**

> 

<**artifactId**>

spring-jdbc</**artifactId**>

<**version**>

${spring-version}</**version**>

" src="data:image/png;base64,R0lGODlhbAAWAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgBUAA8AhQAAAAgKDQoEAAQIDQMGCw0LCwgDAAAECg0LCgQAAAoLDQsLDQsGAwAABA0KCAMAAA0IBAAAAw0LOQMGJQAEOQ0LJQ0IXg0KTAgGXgQGTAAAXggITAADTAoKTAMEXgAAbwMAbwsGbwQEbwMAgAAAgAQAgAgDgAoEgO/v7+7u7gECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QABKSBwai8ijMslcOpvQ5zCFqlqv2Kx2y+16v1gheEwum8/otBosXrvf8Lj83J7b71jLJ3DX8/FXdQIDeXt9hnECBF0FBotVFyMkJIYSJZMTFyAYkxRVlpgompwknpGTlZckmZudVRUmriGpE40HWUIICY+iIxMofpa/kRkjGgqRA8K9A5HHehu+wHvLxMYKfignHAtWyxbHVQwNClgOD4RWIeF+FpPvxYTC7u8kzSOEeiLse/Tw+NMCLLtyggIsT1YgRPhjRBevdeXaIWKGYt5Eivr4BcgGCWCwEr+ugOsAguG4clkaPXI3ABa1EggpzhII80qyaZxamngZ86YfP1jhrMAiwa2KgFthAKRQOkjpiUkeSgI4RUJDB0mTBiileixZCj9PSUQNkILrVa1+AIAiumBpCHtMCSiduzRFEAA7" v:shapes="_x0000_s1472">

</**dependency**

> 

org.springframework</**groupId**> spring-web</**artifactId**>

<**version**>

<**artifactId**>

<**groupId**>

${spring-version}</**version**>

" src="data:image/png;base64,R0lGODlhbAAVAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgBUAA8AhQAAAAgKDQoEAAQIDQMGCw0LCwgDAAAECg0LCgQAAAoLDQsLDQsGAwAABA0KCAMAAA0IBAAAAw0LOQMGJQAEOQ0LJQ0IXg0KTAgGXgQGTAAAXggITAADTAoKTAAAbwMAbwsGbwQEbwMAgAAAgAQAgAgDgAoEgO/v7+7u7gECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QMBJSBwai8ijMslcOpvQ5xB1qlqv2Kx2y+16v1gheEwum8/otBosXrvf8Lj83J7b71iLJ3DX8/FXdQIDeXt9hnECBF0FBotVFyIjI4YSJJMTFx8YkxRVlpgnmpwjnpGTlZcjmZudVRUlriCpE40HWUIICY+iIhMnfpa/kRkiGgqRA8K9A5HHehu+wHvLxMYKficmHAtWyxbHVQwNClgOD4RWIOF+FpPvxYTC7u8jzSKEeiHse/Tw+NMCLLtiggIsT1YgRPhjRBevdeXaIWJ2Yt5Eivr4BcgGCWAwEr+ugOvwgeG4clkaPXI3ABY1EggpzhII80qyaZxalngZ86YfF1jhrMAawa2KgFthoiiVsrQp06dMUAQBADs=" v:shapes="_x0000_s1473">

</**dependency**

> 

# （3） 定义 web.xml

contextConfigLocationclasspath:spring-*.xml " src="data:image/png;base64,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" v:shapes="_x0000_s1482">

org.springframework.web.context.ContextLoaderListener " src="data:image/png;base64,R0lGODlhzQLxAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6Ar0AhwAAAAAAAAsLDQ0LCwgDAAADCA0KCAMAAAMGCwoEAAQIDQ0IBA0LCgQAAAMABAoLDQsGAwAAAwgKDQsGBAAABAQICgAECgAECAQICwMAAwgKCwQAAwsLCwoKCAMDCAMGCgoLCwADBAQDAAsLCggKCgQABAgEBAQECAgDAwsKCAQGCwoEAwQEAwMEBAgICAsIBAAANQ0LJQ0LOQAAYAAAgAAAlAAApw05Xw0kTAA1hgA1uQA1pwBgpwBgqgBgzDUAACULDTkLDSULOTUAYDUAgDU1lDU1gDU1pyVMXyVMcDlecDlegTWGzjWGzDWG3V85DUwkDV85JUwkJUxeTExwX0xwcExwgV+BX1+BcF+BgWAANWAAgGA1gHBMJXBMOXBwTHCBX3CBcHCBgWCGuWCqzGCq74Y1AIY1gIFeOYFwTIFwcIGBX4GBcIaGlIGBgYbO3YbO76pgAKpggKpglKrv3arvzKrv786GNc6GlM7Ouc7v3c7vzM7v7++qYO+qp+/Ohu/Oue/vqu/v3e/vzO/vzu/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEEGEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKVAigpMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmz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" v:shapes="_x0000_s1060">

- **修改\****spring-provider.xml**
- **创建消费者** **03-consumer-web**
- **创建工程**

创建 Maven 的web 工程，然后将 02-consumer-zk 中的 spring-provider 配置文件复制到当前工程中。

# （2） 导入依赖

与提供者工程中的依赖相同。

# （3） 定义处理器

- **定义欢迎页面**

在 src/main/webapp 目录下定义欢迎页面。

# （5） 定义 web.xml

- **修改\****spring-consumer.xml**
- **部署运行**

为了方便部署测试，这里将提供者与消费者部署到一个 Tomcat 中。

# （1） 设置 Tomcat

对于 Tomcat 的设置，需要注意以下两点：

## A、 修改 Tomcat 默认端口号

由于 Dubbo 管控台端口号为 8080，所以这里将 Tomcat 默认端口号修改为 8081。

## B、 两个应用两个 context

- **Dubbo** **管理控制台**

2019 年初，官方发布了 Dubbo 管理控制台 0.1 版本。结构上采取了前后端分离的方式， 前端使用 Vue 和Vuetify 分别作为 Javascript 框架和 UI 框架，后端采用 Spring Boot 框架。

# 2.4.1 下载

Dubbo 管理控制台的下载地址为：https://github.com/apache/dubbo-admin

下载完毕后会得到一个 zip 文件。

# 2.4.2 配置

这是一个 Spring Boot 工程，在下载的 zip 文件的解压目录的如下目录中修改配置文件。主要就是配置注册中心、配置中心，与元数据中心的地址。当然，这是一个 springboot 工程， 默认端口号为 8080，若要修改端口号，则在配置文件中增加形如 server.port=8888 的配置。

dubbo-admin-develop\dubbo-admin-server\src\main\resources\application.properties

这个配置文件，针对不同版本 Dubbo 应用的管控，有不同的配置方式。

# （1） 管控dubbo2.6 版本

以下配置方式对于 2.6 与 2.7 版本的 Dubbo 应用都可以进行管控。但无法查看应用的元数据信息。

# （2） 管控dubbo2.7 版本

以下配置方式对于 2.7 版本的 Dubbo 应用可正常进行管控。但会导致 2.6 版本应用无法正常启动。原因是该方式的配置会在 zk 中创建一个元数据中心，然后在 2.6 版本应用启动时会查找下 zk 的元数据中心工厂扩展类，会找不到，然后无法启动。

## A、 修改 application.properties

**B\****、** **修改** **zk** **中的配置**

在/dubbo 节点下再手工创建出 config/dubbo/dubbo.properties 子节点，并在

dubbo.properties 节点中如加如下的节点内容。添加完成后，点击上面的保存按钮保存。

# 2.4.3 打包

在命令行窗口中进入到解压目录根目录，执行打包命令，跳过 test 阶段。

当看到以下提示时表示打包成功。

打包结束后，进入到解压目录下的 dubbo-admin-distribution 目录下的 target 目录。该目录下有个 dubbo-admin-0.1.jar 文件。该 Jar 包文件即为 Dubbo 管理控制台的运行文件，可以将其放到任意目录下运行。

# 2.4.4 运行

- **启动** **zk**
- **启动管控台**

将 dubbo-admin-0.1.jar 文件存放到任意目录下，例如 D 盘根目录下，直接运行。

# （3） 访问

在浏览器地址栏中输入 [http://localhost:8080](http://localhost:8080/) ，即可看到 Dubbo 管理控制台界面。

第3章 **Dubbo** **高级配置**

# 3.1 关闭服务检查

- **搭建测试环境**

**（\****1***\*）** **修改工程** **02-consumer-zk**

## A、 修改 ConsumerRun 类

将对消费者方法的调用语句注释掉，使消费者暂时不调用消费者方法。

## B、 运行测试

运行后会报错。错误原因是检查 SomeService 的状态失败。

## C、 修改配置文件

修改如下：

## D、再运行

没有报错。

# 3.1.2 分析

默认情况下，若服务消费者先于服务提供者启动，则消费者端会报错。因为默认情况下消费者会在启动时查检其要消费的服务的提供者是否已经注册，若未注册则抛出异常。可以

在消费者端的 spring 配置文件中添加 check=”false”属性，则可关闭服务检查功能。

只要注意启动顺序，该属性看似可以不使用。但在循环消费场景下是必须要使用的。即A 消费 B 服务，B 消费C 服务，而 C 消费A 服务。这是典型的循环消费。在该场景下必须至少有一方要关闭服务检查功能，否则将无法启动任何一方。

# 3.2 多版本控制

当系统进行升级时，一般都是采用“灰度发布（又称为金丝雀发布）”过程。即在低压力时段，让部分消费者先调用新的提供者实现类，其余的仍然调用老的实现类，在新的实现类运行没有问题的情况下，逐步让所有消费者全部调用成新的实现类。多版本控制就是实现灰度发布的。

# 3.2.1 创建提供者 04-provider-version

- **创建工程**

复制前面的提供者工程 02-provider-zk，并更名为 04-provider-version。

# （2） 定义两个接口实现类

删除原来的 SomeServiceImpl 类，并新建两个实现类

# （3） 修改配置文件

指定版本 0.0.1 对应的是 oldService 实例，而版本 0.0.2 对应的是 newService 实例。

# 3.2.2 创建消费者 04-consumer-version

- **创建工程**

复制前面的消费者工程 02-consumer-zk，并更名为 04-consumer-version。

# （2） 修改配置文件

该工程无需修改消费者类 SomeConsumer。

# 3.3 服务分组

服务分组与多版本控制的使用方式几乎是相同的，只要将 version 替换为 group 即可。但使用目的不同。使用版本控制的目的是为了升级，将原有老版本替换掉，将来不再提供老版本的服务，所以不同版本间不能出现相互调用。而分组的目的则不同，其也是针对相同接口，给出了多种实现类。但不同的是，这些不同实现并没有谁替换掉谁的意思，是针对不同需求，或针对不同功能模块所给出的不同实现。这些实现所提供的服务是并存的，所以它们间可以出现相互调用关系。例如，对于支付服务的实现，可以有微信支付实现与支付宝支付实现等。

# 3.3.1 创建提供者 05-provider-group

- **创建工程**

复制前面的提供者工程 04-provider-version，并更名为 05-provider-group。

# （2） 定义两个接口实现类

删除原来的两个接口实现类，重新定义两个新的实现类。

# （3） 修改配置文件

- **创建消费者** **05-consumer-group**
- **创建工程**

复制前面的提供者工程 04-consumer-version，并更名为 05-consumer-group。

# （2） 修改配置文件

- **修改消费者类**
- **多协议支持**

除了 Dubbo 服务暴露协议Dubbo 协议外，Dubbo 框架还支持另外 8 种服务暴露协议：

RMI 协议、Hessian 协议、HTTP 协议、WebService 协议、Thrift 协议、Memcached 协议、Redis 协议、Rest 协议。但在实际生产中，使用最多的就是 Dubbo 服务暴露协议。

# 3.4.1 各个协议的特点

大数据小并发用短连接协议，小数据大并发用长连接协议。

# （1） dubbo 协议

- Dubbo 默认传输协议
- 连接个数：单连接
- 连接方式：长连接
- 传输协议：TCP
- 传输方式：NIO 异步传输
  - 适用范围：传入传出参数数据包较小（建议小于 100K），消费者比提供者个数多，单一消费者无法压满提供者，尽量不要用 dubbo 协议传输大文件或超大字符串。

# （2） rmi 协议

- 采用 JDK 标准的 rmi.* 实现
- 连接个数：多连接
- 连接方式：短连接
- 传输协议：TCP
- 传输方式：BIO 同步传输
- 适用范围：传入传出参数数据包大小混合，消费者与提供者个数差不多，可传文件。

# （3） hession 协议

- 连接个数：多连接
- 连接方式：短连接
- 传输协议：HTTP
- 传输方式：BIO 同步传输

l 适用范围：传入传出参数数据包较大，提供者比消费者个数多，提供者抗压能力较大， 可传文件

# （4） http 协议

- 连接个数：多连接
- 连接方式：短连接
- 传输协议：HTTP
- 传输方式：BIO 同步传输

l 适用范围：传入传出参数数据包大小混合，提供者比消费者个数多，可用浏览器查看， 可用表单或 URL 传入参数，暂不支持传文件。

# （5） webService 协议

- 连接个数：多连接
- 连接方式：短连接
- 传输协议：HTTP
- 传输方式：BIO 同步传输
- 适用场景：系统集成，跨语言调用

# （6） thrift 协议

Thrift 是 Facebook 捐给 Apache 的一个 RPC 框架，其消息传递采用的协议即为 thrift 协议。当前 dubbo 支持的 thrift 协议是对 thrift 原生协议的扩展。Thrift 协议不支持 null 值的传递。

# （7） memcached 协议与 redis 协议

它们都是高效的 KV 缓存服务器。它们会对传输的数据使用相应的技术进行缓存。

# （8） rest 协议

若需要开发具有 RESTful 风格的服务，则需要使用该协议。

# 3.4.2 同一服务支持多种协议

对于多协议的用法有两种，一种是同一个服务支持多种协议，一种是不同的服务使用不同的协议。首先来看“同一服务支持多种协议”的用法。

# （1） 修改提供者配置文件

直接在 04-provider-version 工程中进行修改。

在提供者中要首先声明新添加的协议，然后在服务dubbo:service/标签中再增加该新的协议。若不指定，默认为 dubbo 协议。

这里需要理解这个服务暴露协议的意义。其是指出，消费者若要连接当前的服务，就需要通过这里指定的协议及端口号进行访问。这里的端口号可以是任意的，不一定非要使用默认的端口号（Dubbo 默认为 20880，rmi 默认为 1099）。这里指定的协议名称及端口号，在当前服务注册到注册中心时会一并写入到服务映射表中。当消费者根据服务名称查找到相应主机时，其同时会查询出消费此服务的协议、端口号等信息。其底层就是一个 Socket 编程， 通过主机名与端口号进行连接。

# （2） 修改消费者配置文件

直接在 04-consumer-version 工程中进行修改。在消费者引用服务时要指出所要使用的协议。

# （3） 应用场景

“同一服务支持多种协议”的应用场景是：系统在使用过程中其使用场景逐渐发生了变化，例如，由原来的消费者数量多于提供者数量，变为了消费者数量与提供者数量差不多了， 并且原来系统不用传输文件，现在的系统需要传输文件了。此时就将将原来默认的 dubbo 协议更换为 rmi 协议。目的是为了兼容老工程，扩展新功能。

# 3.4.3 不同服务使用不同协议

- **应用场景**

同一个系统中不同的业务具有不同的特点，所以它们的传输协议就应该根据它们的特点选择不同的协议。

例如对于前面使用服务分组实现的“微信支付”与“支付宝支付”，就可以针对不同的支付方式，使用不同的协议。

# （2） 修改提供者配置文件

直接在 05-provider-group 工程中进行修改。

首先要修改服务提供者配置文件：声明所要使用的协议；在使用dubbo:service/暴露服务时通过 protocal 属性指定所要使用的服务协议。

# （3） 修改消费者配置文件

直接在 05-consumer-group 工程中进行修改。

然后在消费者端通过dubbo:reference/引用服务时通过添加 protocal 属性指定要使用的服务协议。

# 3.5 负载均衡

- **搭建负载均衡环境**

该负载均衡中同一服务会有三个提供者，它们均复制于原来的 02-provider-zk。消费者不用变。

# （1） 复制提供者 02-provider-zk01

## A、 创建工程

复制前面的 02-provider-zk 工程，并重命名为 02-provider-zk01。

## B、 修改配置文件

**C\****、 修改** **Service** **实现类**

- **复制提供者** **02-provider-zk02**

**A\****、** **创建工程**

复制前面的 02-provider-zk01 工程，并重命名为 02-provider-zk02。

## B、 修改配置文件

**C\****、 修改** **Service** **实现类**

- **复制提供者** **02-provider-zk03**

**A\****、** **创建工程**

复制前面的 02-provider-zk01 工程，并重命名为 02-provider-zk03。

## B、 修改配置文件

**C\****、 修改** **Service** **实现类**

- **负载均衡算法**

若消费者与提供者均设置了负载均衡策略，消费者端设置的优先级高。

若消费者端没有显式的设置，但提供者端显式的设置了，且同一个服务（接口名、版本号、分组都相同）的负载均衡策略相同。消费者调用时会按照提供者设置的策略调用。

若多个提供者端设置的不相同，则最后一个注册的会将前面注册的信息覆盖。

# （1） Dubbo 内置的负载均衡算法

Dubbo 内置了四种负载均衡算法。

## A、 random

随机算法，是 Dubbo 默认的负载均衡算法。存在服务堆积问题。

## B、 roundrobin

轮询算法。按照设定好的权重依次进行调度。

## C、 leastactive

最少活跃度调度算法。即被调度的次数越少，其优选级就越高，被调度到的机率就越高。

## D、consistenthash

一致性 hash 算法。对于相同参数的请求，其会被路由到相同的提供者。

# （2） 指定负载均衡算法

负载均衡算法可以在消费者端指定，也可以在提供者端指定。

## A、 消费者端指定

**B\****、 提供者端指定**

- **集群容错**

集群容错指的是，当消费者调用提供者集群时发生异常的处理方案。

# 3.6.1 Dubbo 内置的容错策略

Dubbo 内置了 6 种集群容错策略。

# （1） Failover

故障转移策略。当消费者调用提供者集群中的某个服务器失败时，其会自动尝试着调用其它服务器。该策略通常用于读操作，例如，消费者要通过提供者从 DB 中读取某数据。但重试会带来服务延迟。

# （2） Failfast

快速失败策略。消费者端只发起一次调用，若失败则立即报错。通常用于非幂等性的写操作，比如新增记录。

# （3） Failsafe

失败安全策略。当消费者调用提供者出现异常时，直接忽略本次消费操作。该策略通常用于执行相对不太重要的服务，例如，写入审计日志等操作。

# （4） Failback

失败自动恢复策略。消费者调用提供者失败后，Dubbo 会记录下该失败请求，然后定时自动重新发送该请求。该策略通常用于实时性要求不太高的服务，例如消息通知操作。

# （5） Forking

并行策略。消费者对于同一服务并行调用多个提供者服务器，只要一个成功即调用结束并返回结果。通常用于实时性要求较高的读操作，但其会浪费较多服务器资源。

# （6） Broadcast

广播策略。广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息。

# 3.6.2 配置集群容错策略

容错策略可以设置在消费者端，也可以设置在提供者端。若消费者与提供者均做了设置， 则消费者端的优先级更高。

# （1） 设置重试次数

Dubbo 默认的容错策略是故障转移策略 Failover，即允许失败后重试。可以通过如下方式来设置重试次数，注意设置的是重试次数，不含第一次正常调用。

## A、 提供者端设置

**B\****、 消费者端设置**

- **容错策略设置**

**A\****、 提供者端**

**B\****、 消费者端**

- **服务降级**
- **服务降级基础（面试题）**
- **什么是服务降级**

服务降级，当服务器压力剧增的情况下，根据当前业务情况及流量对一些服务有策略的降低服务级别，以释放服务器资源，保证核心任务的正常运行。例如，双 11 时 0 点-2 点期间淘宝用户不能修改收货地址，不能查看历史订单，就是典型的服务降级。

# （2） 服务降级方式

能够实现服务降级方式很多，常见的有如下几种情况：

- 部分服务暂停：页面能够访问，但是部分服务暂停服务，不能访问。
  - 部分服务延迟：页面可以访问，当用户提交某些请求时系统会提示该操作已成功提交给了服务器，由于当前服务器繁忙，此操作随后会执行。在等待了若干时间后最终用户可以看到正确的执行结果。
  - 全部服务暂停：系统入口页面就不能访问，提示由于服务繁忙此服务暂停。跳转到一个预先设定好的静态页面。
  - 随机拒绝服务：服务器会按照预先设定好的比例，随机挑选用户，对其拒绝服务。作为用户，其看到的就是请重试。可能再重试就可获得服务。

# （3） 整个系统的服务降级埋点

- **服务降级与** **Mock** **机制**

Dubbo 的服务降级采用的是mock 机制。其具有两种降级处理方式：Mock Null降级处理，与 Class Mock 降级处理。

# 3.7.2 Mock Null 服务降级处理 06-consumer-downgrade

- **创建消费者工程**

直接复制 02-consumer-zk 工程，并命名为 06-consumer-downgrade。

# （2） 定义接口

- **修改\****pom** **文件**

由于这里不再需要 00-api 工程了，所以在 pom 文件中将对00-api 工程的依赖删除即可。

# （4） 修改spring-consumer.xml

- **修改消费者启动类**
- **Class Mock** **服务降级处理** **06-consumer-downgrade2**
- **创建消费者工程**

本例直接在前面的例子基础上进行修改。

# （2） 定义Mock Class

在业务接口所在的包中，本例为 com.abc.service 包，定义一个类，该类的命名需要满足以下规则：业务接口简单类名 + Mock。

# （3） 修改spring-consumer.xml

- **服务调用超时**

前面的服务降级的发生，其实是由于消费者调用服务超时引起的，即从发出调用请求到获取到提供者的响应结果这个时间超出了设定的时限。默认服务调用超时时限为 1 秒。可以

在消费者端与提供者端设置超时时限。

# 3.8.1 创建提供者工程 06-provider-timeout

- **创建工程**

复制 02-provider-zk 工程，并重命名为 06-provider-timeout。

# （2） 修改依赖

由于这里不再需要 00-api 工程了，所以在 pom 文件中将对00-api 工程的依赖删除即可。

# （3） 定义接口

在 com.abc.service 包中定义如下接口。

# （4） 定义接口实现类

在 com.abc.provider 包中定义接口的实现类。该实现类中的业务方法添加一个 2 秒的

Sleep，以延长向消费者返回结果的时间。

# （5） 修改配置文件

- **创建消费者工程** **06-consumer-timeout**
- **创建工程**

复制 06-consumer-downgrade2 工程，并重命名为 06-consumer-timeout。

# （2） 添加日志文件

在 src/main/resources 下添加 log4j.properties 文件。

# 3.9 服务限流

为了防止某个消费者的 QPS 或是所有消费者的 QPS 总和突然飙升而导致的重要服务的失效，系统可以对访问流量进行控制，这种对集群的保护措施称为服务限流。

Dubbo 中能够实现服务限流的方式较多，可以划分为两类：直接限流与间接限流。

- 直接限流：通过对连接的数量直接限制来达到限流的目的。超过限制则会让再来的请求等待，直到等待超时，或获取到相应服务（官方方案）。
- 间接限流：通过一些非连接数量设置的间接手段来达到限流的目的（个人经验）。

# 3.9.1 直接限流

- **executes** **限流\****—***\*仅提供者端**

该属性仅能设置在提供者端。可以设置为接口级别，也可以设置为方法级别。对指定服务（方法）的连接数量进行限制。

# （2） accepts 限流—仅提供者端

该属性仅可设置在提供者端的dubbo:provider/与dubbo:protocol/，是针对指定协议的连接数进行限制。

# （3） actives 限流—两端均可

该限流方式与前两种不同的是，其可以设置在提供者端，也可以设置在消费者端。可以设置为接口级别，也可以设置为方法级别。

## A、 提供者端限流

根据客户端与服务端建立的连接是长连接还是短连接，其意义不同：

- 长连接：当前这个服务上的一个长连接最多能够处理的请求个数。对长连接数量没有限制。
- 短连接：当前这个服务上可以同时处理的短连接数量。

## B、 消费者端限流

根据客户端与服务端建立的连接是长连接还是短连接，其意义不同：

- 长连接：当前这个消费者的一个长连接最多能够提交的请求个数。对长连接数量没有限制。
- 短连接：当前这个消费者可以同时提交的短连接数量。

# （4） connections 限流—两端均可

可以设置在提供者端，也可以设置在消费者端。限定连接的个数。

一般情况下，我们使用的都是默认的服务暴露协议 Dubbo，所以，一般会让 connections

与 actives 联用。connections 限制长连接的数量，而 actives 限制每个长连接上的请求数量。

## A、 提供者端限流

**B\****、 消费者端限流**

- **间接限流**
- **延迟连接**

只要消费者真正调用提供者方法时才创建长连接。

仅可设置在消费者端，且不能设置为方法级别。仅作用于 Dubbo 服务暴露协议。用于减少长连接数量。

# （2） 粘连连接

系统会尽量让同一个消费者调用同一个提供者。其可以限定流向。

仅能设置在消费者端，其可以设置为接口级别，也可以设置为方法级别。仅作用于

Dubbo 服务暴露协议。用于减少长连接数量。粘连连接的开启将自动开启延迟连接。

# （3） 负载均衡

可以设置在消费者端，亦可设置在提供者端；可以设置在接口级别，亦可设置在方法级别。其可以限定流向，但其没有限制了流量。

# 3.10 声明式缓存

为了进一步提高消费者对用户的响应速度，减轻提供者的压力，Dubbo 提供了基于结果的声明式缓存。该缓存是基于消费者端的，所以使用很简单，只需修改消费者配置文件，与提供者无关。该缓存是缓存在消费者端内存中的，一旦缓存创建，即使提供者宕机也不会影响消费者端的缓存。

# 3.10.1 缓存设置 07-consumer-cache

- **创建工程**

直接复制 02-consumer-zk 工程，并命名为 07-consumer-cache。

# （2） 修改消费者配置文件

仅需在dubbo:reference/中添加 cache=”true”属性即可。

当然，若一个接口中只有部分方法需要缓存，则可使用方法级别的缓存。

# （3） 修改RunConsumer 类

- **默认缓存** **1000** **个结果**

声明式缓存中可以缓存多少个结果呢？默认可以缓存 1000 个结果。若超出 1000，将采用 LRU 策略来删除缓存，以保证最热的数据被缓存。注意，该删除缓存的策略不能修改。

直接在 07-consumer-cache 工程中创建 ConsumerRun2 类即可。

# 3.11 多注册中心

- **创建提供者** **08-provider-registers**
- **创建工程**

直接复制 05-provider-group 工程，并命名为 08-provider-registers。

# （2） 修改配置文件

同一个服务注册到不同的中心，使用逗号进行分隔。

# 3.11.2 创建消费者 08-consumer-registers

- **创建工程**

直接复制 05-consumer-group 工程，并命名为 08- consumer-registers。

# （2） 修改配置文件

对于消费者工程，用到哪个注册中心了，就声明哪个注册中心，无需将全部注册中心进行声明。

# 3.12 单功能注册中心

- **仅订阅**
- **概念**

对于某服务来说，其可以发现和调用注册中心中的其它服务，但不能被其它服务发现和调用，这种情形称为仅订阅。

仅可去发现，但不能被发现。

即可以从注册中心下载服务注册表，但其不会将当前配置文件中的服务写入到注册中心的服务注册表。

# （2） 设置方式

对于“仅订阅”注册中心的实现，只需修改提供者配置文件，在dubbo:registry/标签中添加 register=”false”属性。即对于当前服务来说，注册中心不再接受其注册，但该服务可以通过注册中心去发现和调用其它服务。

# 3.12.2 仅注册

- **概念**

对于某服务来说，其可以被注册中心的其它服务发现和调用，但不能发现和调用注册中心中的其它服务，这种情形称为仅注册。

仅可被发现，但不能去发现。

# （2） 设置方式

对于“仅注册”注册中心的实现，只需修改提供者配置文件，在dubbo:registry/标签中添加 subscribe=”false”的属性。即对于当前服务来说，注册中心中的其它服务可以发现和调用当前服务，但其不能发现和调用其它服务。

# 3.13 服务暴露延迟

如果我们的服务启动过程需要warmup 事件，就可以使用 delay 进行服务延迟暴露。只需在服务提供者的dubbo:service/标签中添加 delay 属性。其值可以有三类：

- 正数：单位为毫秒，表示在提供者对象创建完毕后的指定时间后再发布服务。
- 0：默认值，表示当前提供者创建完毕后马上向注册中心暴露服务。
- -1：表示在 Spring 容器初始化完毕后再向注册中心暴露服务。

# 3.14 消费者的异步调用

- **应用场景**

在 Dubbo 简介时，我们分析了 Dubbo 的四大组件工作原理图，其中消费者调用提供者采用的是同步调用方式。其实，消费者对于提供者的调用，也可以采用异步方式进行调用。异步调用一般应用于提供者提供的是耗时性 IO 服务。

# 3.14.2 Future 异步执行原理

异步方法调用执行原理如下图所示，其中实线为同步调用，而虚线为异步调用。

- UserThread：消费者线程
- IOThrea：提供者线程
- Server：对 IO 型操作的真正执行者

# 3.14.3 Future 异步调用

- **创建提供者** **10-provider-async**

## A、 创建工程

直接复制 02-provider-zk 工程，并命名为 10-provider-async。

## B、 定义业务接口

**C\****、 定义实现类**

**D\****、修改配置文件**

- **创建消费者** **10-consumer-async**

**A\****、 创建工程**

直接复制 02-consumer-zk 工程，并命名为 10-consumer-async。

## B、 定义业务接口

**C\****、 修改配置文件**

**D\****、定义同步消费者类** **ConsumerRunSync**

**E\****、 定义异步消费者类** **ConsumerRunAsync**

**F\****、 定义异步消费者类** **ConsumerRunAsync2**

- [**CompletableFuture**](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html) **异步调用**

使用 Future 实现异步调用，对于无需获取返回值的操作来说不存在问题，但消费者若需要获取到最终的异步执行结果，则会出现问题：消费者在使用 Future 的 get()方法获取返回值时被阻塞。为了解决这个问题，Dubbo 又引入了 CompletableFuture 来实现对提供者的异步调用。

# （1） 创建提供者 10-provider-async2

## A、 创建工程

直接复制 10-provider-async 工程，并命名为 10-provider-async2。

## B、 修改业务接口

需要异步调用执行的方法返回 CompletableFuture<>。

## C、 修改实现类

- **创建消费者** **10-consumer-async2**

**A\****、 创建工程**

直接复制 10-consumer-async 工程，并命名为 10-consumer-async2。

## B、 修改业务接口

**C\****、 修改消费者类**

直接删除同步消费者类，修改异步消费者类。

## D、修改配置文件

- **总结**

Futrue 与CompletableFuture 的区别与联系：

对于消费者不用获取提供者所调用的耗时操作结果的情况，使用 Future 与CompletableFuture 效果是区别不大的。但对于需要获取返回值的情况，它们的区别是很大的。

- Future：源自于 JDK5，7.0 之前使用 Future 实现消费者对提供者的异步调用。通过 Future 的 get()获取返回结果，get()方法会阻塞，不好。
  - CompletableFuture：源自于 JDK8，7.0 之后使用 Future 实现消费者对提供者的异步调用。通过 CompletableFutrue 的回调获取返回结果，不会发生阻塞，好用。

# 3.15 提供者的异步执行

从前面“对提供者的异步调用”例子可以看出，消费者对提供者实现了异步调用，消费者线程的执行过程不再发生阻塞，但提供者对 IO 耗时操作仍采用的是同步调用，即 IO 操作仍会阻塞 Dubbo 的提供者线程。

但需要注意，提供者对 IO 操作的异步调用，并不会提升RPC 响应速度，因为耗时操作终归是需要消耗那么多时间后才能给出结果的。

# 3.15.1 创建提供者 10-provider-async3

- **创建工程**

直接复制 10-provider-async2 工程，并命名为 10-provider-async3。

# （2） 修改实现类

- **Spring Boot** **中使用** **Dubbo**
- **定义** **commons** **工程** **11-dubboCommons**
- **创建工程**

这里就创建一个普通的 Maven 的 Java 工程，并命名为 11-dubboCommons。

# （2） 定义pom 文件

com.abc" src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AgMAgAAAAAAAAAJLRIynyesNn4x02oqvznz7Dn5iSI5miZ5qyq5uC79yeQD2jef6zvf+DwwKh8Si8YhMKpfMpvMJjUqn1GpuYc1qt9yu9wsOi8fkcqAAADs=" v:shapes="_x0000_s1806">

- **定义实体类**
- **定义业务接口**
- **将工程安装到本地库**

运行 Maven 的 install 命令，将工程安装到本地版本库，以备其它工程使用。

# 3.16.2 定义提供者 11-provider-springboot

- **创建工程**

创建一个 Spring Boot 工程，并重命名为 11-provider-springboot。

# （2） 定义pom 文件

## A、 添加 dubbo 与 spring boot 整合依赖

**B\****、 添加** **zkClient** **依赖**

**C\****、 其它依赖**

- dubboCommons 依赖
- spring boot 与 redis 整合依赖
- mybatis 与 spring boot 整合依赖
- 数据源 Druid 依赖
- mysql 驱动依赖
- slf4j-log4j12 依赖
- spring-boot-starter-web 依赖

# （3） 定义Service 实现类

- **定义** **Dao** **接口**
- **定义映射文件**

' src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJWhI+py+0Po5y02ouz3rz7D4biSJZNEADpqrbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uoQlTJpt9yu9wsOi8fkcpllTqvX7Lb7DY+3AwUAOw==" v:shapes="_x0000_s1822">

- **修改启动类**

- **修改主配置文件**

  **mapper-locations**: classpath:com/abc/dao/*.xml

### spring:

*# 注册数据源*

**datasource**:

*# 指定数据源类型为* *Druid*

**type**: com.alibaba.druid.pool.DruidDataSource

**driver-class-name**: com.mysql.jdbc.Driver

**url**: jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8

**username**: root

**name**: 11-provider-springboot

*# 指定* *zk 注册中心*

**dubbo**:

**registry**: zookeeper://zkOS:2181

*#* *zk 集群作注册中心*

*# registry: zookeeper://zkOS1:2181?backup=zkOS2:2181,zkOS3:2181*

# 3.16.3 定义消费者 11-consumer-springboot

- **创建工程**

创建一个 Spring Boot 工程，并重命名为 11-consumer-springboot。

# （2） 定义pom 文件

- dubbo 与 spring boot 整合依赖
- zkClient 依赖
- dubboCommons 依赖
- JSP 引擎 jasper 依赖
- slf4j-log4j12 依赖
- spring-boot-starter-web 依赖

# （3） 修改主配置文件

- **创建\****jsp** **页面**

在 src/main/webapp 目录下定义 index.jsp 文件。

# （5） 定义处理器

- **定义** **jsp** **页面**

在 src/main/webapp 目录下定义 welcome.jsp 文件。

# （7） 修改入口类

- **属性配置优先级**

Dubbo 配置文件中各个标签属性配置的优先级总原则是：

- 方法级优先，接口级(服务级)次之，全局配置再次之。
- 如果级别一样，则消费方优先，提供方次之。

另外，还有两个标签需要说明一下：

- dubbo:consumer/设置在消费者端，用于设置消费者端的默认配置，即消费者端的全局设置。
- dubbo:provider/设置在提供者端，用于设置提供者端的默认配置，即提供者端的默认配置。

# 3.18 配置建议

- Provider 端要配置合理的 Provider 端属性
- Provider 端上尽量多配置 Consumer 端属性

第4章 **Dubbo** **的系统架构解析**

- hsf：淘宝
- dubbo：阿里巴巴 B2B
- sofa：蚂蚁金服

# 4.1 Dubbo 的两大设计原则

Double 框架在设计时遵循了两大设计原则：

- Dubbo 使用“微内核+插件”的设计模式。内核只负责组装插件（扩展点），Dubbo 的功能都是由插件实现的，也就是 Dubbo 的所有功能点都可被用户自定义扩展类所替换。
- 采用 URL 作为配置信息的统一格式，所有扩展点都通过传递 URL 携带配置信息。

# 4.2 Dubbo 的三大领域模型

为了对 Dubbo 整体架构叙述的方便，Dubbo 抽象出了三大领域模型。

- Protocol 服务域：是 Invoker 暴露和引用的主功能入口，它负责 Invoker 的生命周期管理。
- Invoker 实体域：是 Dubbo 的核心模型，其它模型都向它靠扰，或转换成它，它代表一个可执行体，可向它发起 invoke 调用，它有可能是一个本地的实现，也可能是一个远程的实现，也可能一个集群实现。
- Invocation 会话域：它持有调用过程中的变量，比如方法名，参数等。

# 4.3 Dubbo 的四大组件

Dubbo 中存在四大组件：

- **Provider\****：**暴露服务方，亦称为服务提供者。
- **Consumer\****：**调用远程服务方，亦称为服务消费者。
- **Registry\****：**服务注册与发现的中心，提供目录服务，亦称为服务注册中心
- **Monitor\****：**统计服务的调用次数、调用时间等信息的日志服务，亦称为服务监控中心

# 4.4 Dubbo 的十层架构

Dubbo 的架构设计划分为了 10 层。图中左边淡蓝色背景为服务 Consumer 使用的接口， 右边淡绿色背景为服务 Provider 使用的接口，位于中轴线的为双方都要用到的接口。对于这10 层，根据其总体功能划分，可以划分为三大层：

# 4.4.1 Business 层

该层仅包含一个 **service 服务层**，该层与实际业务逻辑有关，根据服务消费方和服务提供方的业务设计，实现对应的接口。

# 4.4.2 RPC 层

该层主要负责整个分布式系统中各个主机间的通讯。该层包含了以下 6 层。

# （1） config 配置层

以ServiceConfig 和ReferenceConfig 为中心，用于加载并解析 Spring 配置文件中的 Dubbo

标签。

# （2） proxy 服务代理层

服务接口透明代理，生成服务的客户端 Stub 和服务器端 Skeleton, 以 ServiceProxy 为中心，扩展接口为 ProxyFactory。

Proxy 层封装了所有接口的透明化代理，而在其它层都以 Invoker 为中心，只有到了暴露给用户使用时，才用 Proxy 将 Invoker 转成接口，或将接口实现转成 Invoker，也就是去掉 Proxy 层 RPC 是可以运行的，只是不那么透明，不那么看起来像调本地服务一样调远程服务。

# （3） registry 注册中心层

封装服务地址的注册和发现，以服务 URL 为中心，扩展接口为 RegistryFactory、Registry、RegistryService，可能没有服务注册中心，此时服务提供方直接暴露服务。

# （4） cluster 路由层

封装多个提供者的路由和负载均衡，并桥接注册中心，以 Invoker 为中心，扩展接口为Cluster、Directory、Router 和 LoadBalance，将多个服务提供方组合为一个服务提供方，实现对服务消费通明。只需要与一个服务提供方进行交互。

Dubbo 官方指出，在 Dubbo 的整体架构中，Cluster 只是一个外围概念。Cluster 的目的是将多个 Invoker 伪装成一个 Invoker，这样用户只需关注 Protocol 层 Invoker 即可，加上Cluster 或者去掉 Cluster 对其它层都不会造成影响，因为只有一个提供者时，是不需要Cluster 的。

# （5） monitor 监控层

RPC 调用时间和次数监控，以 Statistics 为中心，扩展接口 MonitorFactory、Monitor 和

MonitorService。

# （6） protocol 远程调用层

封装RPC 调用，以 Invocation 和 Result 为中心，扩展接口为 Protocol、Invoker 和Exporter。Protocol 是服务域，它是 Invoker 暴露和引用的主功能入口，它负责 Invoker 的生命周期管理。Invoker 是实体域，它是 Dubbo 的核心模型，其他模型都是向它靠拢，或转换成它，它代表一个可执行体，可向它发起 Invoker 调用，它有可能是一个本地实现，也有可能是一个远程实现，也有可能是一个集群实现。

在 RPC 中，Protocol 是核心层，也就是只要有 Protocol + Invoker + Exporter 就可以完成非透明的 RPC 调用，然后在 Invoker 的主过程上 Filter 拦截点。

# 4.4.3 Remotting 层

Remoting 实现是 Dubbo 协议的实现，如果我们选择 RMI 协议，整个 Remoting 都不会用上，Remoting 内部再划为 Transport 传输层和 Exchange 信息交换层，Transport 层只负责单向消息传输，是对 Mina, Netty, Grizzly 的抽象，它也可以扩展 UDP 传输，而 Exchange 层是在传输层之上封装了 Request-Response 语义。

具体包含以下三层：

# （1） exchange 信息交换层

封装请求响应模式，同步转异步，以 Request 和 Response 为中心，扩展接口为 Exchanger

和 ExchangeChannel,ExchangeClient 和 ExchangeServer。

# （2） transport 网络传输层

抽象和mina 和netty 为统一接口，以 Message 为中心，扩展接口为Channel、Transporter、Client、Server 和 Codec。

# （3） serialize 数据序列化层

可复用的一些工具，扩展接口为 Serialization、ObjectInput、ObejctOutput 和 ThreadPool。

注：这里解析的源码为 dubbo-2.7.3 版本。

# 4.5 Dubbo 框架架构

- **将** **Dubbo** **源码工程导入** **Idea**

本例以 Dubbo2.7.3 为例进行源码解析。从官网下载到源码的 zip 包后解压后就可以直接导入到 Idea 中。

# 4.5.2 Dubbo2.7 版本与 2.6 版本

Dubbo2.7 版本需要 Java 8 及以上版本。

2.7.0 版本在改造的过程中遵循了一个原则，即保持与低版本的兼容性，因此从功能层面来说它是与 2.6.x 及更低版本完全兼容的。2.7 与 2.6 版本相比，改动最大的就是包名，由原来的 com.alibaba.dubbo 改为了 org.apache.dubbo。

第5章 **Dubbo** **的内核解析**

所谓 Dubbo 的内核是指，Dubbo 中所有功能都是基于它之上完成的，都是由它作为基础的。dubbo 的内核包含四部分：SPI、AOP、IoC，与 Compiler。

# 5.1 JDK 的 SPI 机制

- **简介**

SPI，Service Provider Interface，服务提供者接口，是一种服务发现机制。

# 5.1.2 JDK 的 SPI 规范

JDK 的 SPI 规范规定：

- 接口名：可随意定义
- 实现类名：可随意定义
- 提供者配置文件路径：其查找的目录为 META-INF/services
- 提供者配置文件名称：接口的全限定性类名，没有扩展名。
  - 提供者配置文件内容：该接口的所有实现类的全限类性类名写入到该文件中，一个类名占一行

# 5.1.3 代码举例 12-jdkspi

- **定义工程**

创建一个 Maven 的 Java 工程。

# （2） 定义接口

- **定义两个实现类**
- **创建目录与配置文件**
- **定义配置文件内容**
- **定义服务消费者类**
- **Dubbo** **的** **SPI**

Dubbo 并没有直接使用 JDK 的 SPI，而是在其基础之上对其进行了改进。

# 5.2.1 规范说明

Dubbo 的 SPI 规范是：

- 接口名：可以随意定义
- 实现类名：在接口名前添加一个用于表示自身功能的“标识前辍”字符串
- 提供者配置文件路径：在依次查找的目录为

META-INF/dubbo/internal META-INF/dubbo

META-INF/services

- 提供者配置文件名称：接口的全限定性类名，无需扩展名
- 提供者配置文件内容：文件的内容为 key=value 形式，value 为该接口的实现类的全限类性类名，key 可以随意，但一般为该实现类的“标识前辍”（首字母小写）。一个类名占一行。
- 提供者加载：ExtensionLoader 类相当于 JDK SPI 中的 ServiceLoader 类，用于加载提供者配置文件中所有的实现类，并创建相应的实例。

# 5.2.2 Dubbo 的 SPI 举例 13-dubbospi

下面将实现一个下单功能。其支付方式仅支持支付宝或微信两种方式。即这里要定义一个 SPI 接口，其存在两个扩展类。

# （1） 创建工程

创建一个 Maven 的 Java 工程。

# （2） 导入依赖

导入 Dubbo 的依赖。

junit junit 4.11 test " src="data:image/png;base64,R0lGODlhzQILAXcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AtMAhgAAAAAAAAgKDQ0KCAMAAAQIDQ0LCgQABAoLDQQAAAAAAwoLCggGAwMGCw0LCwgDAAAABAoEAAAECggDAwMDCAsLDQQAAwsGAwMGCAADCA0IBAAEBAgKCwMABAgDBAAANQAAYAAAlAAAgAAApwA1uQBgqgBgzDUAADUAgDUAlDUApzU1hjVgpzWGuTWGzjWG3WAAAGAAYGAAgGBglGBggGCGuWCq3WCq74Y1AIY1NYY1gIaGp4aquYbOzIbO76pgAKpggKrOuarvzKrv3arv786GNc6GlM7v7++qYO+qp+/Ohu/Oue/vqu/vzu/vzO/v3e/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwf/gAABgoSDhoWIh4qJjIuOjZCPkpGUk5aVmJeamZybnp2gn6KhpKOmpainqqmsq66gggGys7S1tre4ubq7vL2+v8DBwsPExcbHyMnKy8zNzs/Q0biEANXW19jZ2tvc3d7f4OHi4+Tl5ufo6err7O3u7/Dx8vP02LL1+Pn6+/z9/v8AAwocSBBRwYMIEypcyLChw4f+7kGcSLGixYsYM2rkdmijx48gQ4ocSbKaxJIoU6pcybLluY7joMicSbOmzZs4c+rcyROny59AgwoFeDJmz6NIkyrVObSp06dQy8EUt7Sq1as+o2rdyrVpUao6i7iomSSED6w4y55Fm7Wr27dw/0NODUfzxw2aTGIQIWuWrU21fmX+GCszruHDiB9+pQulCQ7CM8XOXIJChAizTmRYfgFlSYodlk3IzLzZM2gRojtXvuyDtAjOpkPLfKIjtJG+mTk7LlE4se/fwPvNBQeFCQzIMpvkWEuZc9keMmCjuEF5BBHKN3KrroHCutrmUJ5HV029OxHAQEgcGT0+ifXIIIgEn0+/PrvF4JScuFsTCW+ZRrxX1mmWiVDeXbklUaBl3N2lVoB7DbiggdiFh9l4MwFhAm2p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" v:shapes="_x0000_s1053">

 

 

# （3） 定义 SPI 接口

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **定义两个扩展类**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

- **定义扩展类配置文件**

|      |      |
| ---- | ---- |
|      |      |

 

 

- **定义测试类**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **Adaptive** **机制**

 

Adaptive 机制，即扩展类的自适应机制。即其可以指定想要加载的扩展名，也可以不指定。若不指定，则直接加载默认的扩展类。即其会自动匹配，做到自适应。其是通过@Adaptive 注解实现的。一个 SPI 只能有一个 Adaptive 类。

 

# 5.3.1    @Adaptive 注解

 

@Adaptive 注解可以修饰类与方法，其作用相差很大。

 

# （1） @Adaptive 修饰类

 

被@Adapative 修饰的 SPI 接口扩展类称为 Adaptive 类，表示该 SPI 扩展类会按照该类中指定的方式获取，即用于固定实现方式。其是装饰者设计模式的应用。

 

# （2） @Adaptive 修饰方法

 

被@Adapative 修饰 SPI 接口中的方法称为 Adaptive 方法。在 SPI 扩展类中若没有找到

Adaptive 类，但系统却发现了 Adapative 方法，就会根据 Adaptive 方法自动为该 SPI 接口动

 

态生成一个 Adaptive 扩展类，并自动将其编译。例如 Protocol 接口中就包含两个 Adaptive

方法。

 

# 5.3.2    Adaptive 类代码举例 14-adaptiveclass

 

- **创建工程**

 

复制 13-dubbospi 工程，在其基础之上修改。

 

# （2） 定义adaptive 扩展类

 

|      |      |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **修改扩展类配置文件**

 

- **测试** **1**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **测试** **2**

 

 

- **Adaptive** **方法规范**

 

下面我们准备要定义 Adaptive 方法。那么 Adaptive 方法的定义有什么要求呢？我们通过查看动态生成的 Adaptive 类来总结 Adaptive 方法的要求。

 

# （1） 动态生成Adaptive 类格式

 

 

|      |                                                              |
| ---- | ------------------------------------------------------------ |
|      | ; public class SPI 接口名$Adpative implements SPI 接口 { public adaptiveMethod (arg0, arg1, ...) { // 注意，下面的判断仅对 URL 类型，或可以获取到 URL 类型值的参数进行判断 // 例如，dubbo 的 Invoker 类型中就包含有 URL 属性 if(arg1==null) throw new IllegalArgumentException( 异 常 信 息 )； if(arg1.getUrl()==null) throw new IllegalArgumentException(异常信息)； URL url = arg1.getUrl(); // 其会根据@Adaptive 注解上声明的 Key 的顺序，从 URL 获取Value， // 作为实际扩展类。若有默认扩展类，则获取默认扩展类名；否则获取 // 指定扩展名名。 String extName = url.get 接口名() == null?默认扩展前辍名:url.get 接口名(); if(extName==null) throw new IllegalStateException(异常信息); SPI 接口 extension = ExtensionLoader.getExtensionLoader(SPI 接口.class) .getExtension(extName); return extension. adaptiveMethod(arg0, arg1, ...); } public unAdaptiveMethod( arg0, arg1, ...) { throw new UnsupportedOperationException(异常信息); } } " src="data:image/png;base64,R0lGODlhzQKRAncAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AgoChgAAAAAAAA0LCgQAAwgKDQsGAwAECg0LCwgDAAQIDQMGCw0KCAMAAwoEAwQABAoIBAMAAAAAAwAABAoLDQ0IBAoEAAMABAQAAAADCAsLDQQDCAQEAwMDCAgKCggGBAMGCAMECgQICggDAwoGAwMDAAMDBAQDAAgKCwQDAwADBAQDBAMDAwsLCwAEBAQGCAQICwsIBAQEBD8GAz8LDWsLDWsEA2sIBGsEAGsGA5IKDZIDAJIDA5IGBLgIDbgAA7gAALgDCLgDALgABLgICtwGC9wAANwAA9wDBNwABNwGCNwDANwDA/8AAP8ECv8ABP8AA/8DCAECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwf/gAGCg4SFhoeIiYqLjI2Oj5CRkpOUlZaXmJmam5ydnp+goYgAAQCmp6ipqqusra6vsLGys7S1tre4ubq7vL2+v8DBwsPExauCxsnKy8zNzs/Q0dLT1NWkpdbZ2tvc3d7f4OHQyOLl5ufo6err7Kyl2O3x8vP09fb3puT4sfCkvPrXjr3qt6+gwYMIfQFMmIogwVmDTkV0N5ChxYsYMy7UJWAAAXPYDklUJLGhqkaoEmVcybLluXe/On40VkCQAQAHEAxKYKqAAneE/OVL2QqeQ1cPTQp0ybSp02kbc8kcljPBAgYfGxCoCuDqR59IiR6lmC9o0qEllS59yratW2BR/wU4eCDoZ1cIASLMXIBXwgSZOW/yzTuTwiC9dwXxVFUB8QILE05xzfkTbNFrIoWuNYp5ItrPQzNjo+D3renTqIsmFXABQwavAjRkAFDANWueFBTIrHAz9uzaGQAj4Hm7K2RUNe0CYO3a1OThtJWv1UyUrGaY1AOmVYsWHmvEPZunHk+eJfZUU7meejyBgvjlAza8N8V+AYffP93PVk/7ZqqcAdzEFWmRWQbUdtVtVlJIYnXmWXYMogIgT8CVZ+GFCcXlkSkVVDaIX/qdwlqAp9QkiF/sDVjIYj0FwOJ6ECQAIGE9SZcSMipll2AhQnFGXVJjETQhhkQWuY+GH1XFnv9x7b3XUQcX/LRkfXiRCECIrTQ20ykd8ldjWJ/1c9Z1Sj0k5jGGfPbdlka26SY7cV0gGFYEAlCBX8XlJpNXdd7Znn8iXvDiKlUVQJygXkYHJjljKVidmQlGWtYpdSI335uYZprNeah05IFiHArygQORDYbnhsBVICqpMwZg12ABlNbKjD8lauB0Gy2CYI8nSQoad6lUqOmwxEqDpDAF+OdVND4CNKaPYZ6Uo467SgZdsdhmq8yxwfBG33HORLTQmLj6Su5Io6yyAAjatutuMNwCMyKN79Zr771hnYvvvvz2O0xU/gYs8MC3AEzwwQgnPKnCDDc8MKcORyzxuwZPbPH/xW1WjPHGHKemcccgh8wUxCKXbHJLH5+s8sr2pMzyyzCn43LMNNfMDck256zzzfru7PPPy8wM9NBE9yJ00UgnLQvOSjftNEQ9Py311EdPbXXRVV+t9c5Mb+010ll/LfbLYY9ttslln602x12v7fbKab8tt8Nxz203wnXfrbe/be/tN91R/y34w4EPbji/eR+uuKZ9L+64vYk/LjmRkU9uOXmVX675W41v7rmRmX8uukuhj246RqWfrvpBna/u+lOpvy47PbHPbjuchd+uu0Gt7+57QbX/Lnw3wQ9vvDXFH6/8ONQu73w8yT8vvTHR38MEKtfzkn3TZ8pevT3bmxI+/y7jqyIDETo3qnrvqkwl4oa2uB9O9kzUb//29+dfvyr674+KDD2QhQB8kIN2yC9DZeFRAtO1uLIdcE9YocUBgyGD+jUBJzqwXwABcL5W5A8A+MOeB1NRvvAtoAj1Q59zdOAEGrSPgLPoYFeMUMBdLKsWEwTGrW5hIgHpBFSKAoq4EPSsxzkQfqbIYSyU2IsF0BAANcjBAXQQQCcWUIYjPMX1xlc+LWpxiyIU0Q8CKAAgzIA+RwjCBjsFQ1lgsRpM7MUOa7EsrXBlWXPkDsSK6Li4zKUuyyEVkzwFSJlMxVSRQQUiD4kX8KjKRXYCYiwWgAQXrjCAU0TfG1exxf7RL/+LJBRfKihpyf8RQQYXNAUF6pcEAgpACDZIIX1Q+IQC3kCDA8zBDVRIyhPWb42tMOQfXWUYSMqFLq6Cz0dgRZxhKqCYi2FmIJHJk0dC8pGDcsWSnAMdygTxMjnio+JwxpzXYEUupYJMOWHjEUMK6krSyZNu2ikb2rjGPrPpyn1w+AMonBGDmKQiB1UIShBysqDYK98UWyihHeSAlMsZIwBuUEsB9HMGVizjGWXgz4EmkYAU6CgqLVrFSr7Cna4xzE30s85ztvOd7CmnSq9kG5iqU05XKo1l8InD1uTzORSyUa+q1byi+u1YVUHnIDeU1HZ6BEupgOoB6xPBrlSVFlP/ZMIFpxhACjB0k/wLIf8QCsIunuKWTNigV2lA0it1NJe5BKgiTSpDuBKQqyE9I1dP6tSPHFKdTB2OTELUVL9uiD2EFeyGprLTq84CQD7EjU6F2hBo+YqoguNWh5QaU/httq9QRYVUUfWhAgWgOTW51CSL0IOsMqGWpgDrFzvZP1GyInye9J9oYXuDVPbWrWe0aw13yUH7fVWFcd0lKVd5P2CuwpCLPSxgZ/JZAhRzJ1P5a3tWlF1UKSe1+aQFX2T0Q/DkMYEOCkpRc+c2pApWkJ1NkmIJMFjV0jS8ezrONr2JEwRQFha73KspXyFWg3rxtuID41j/Q0WL3q+FeV2O/ythyFWIQrSuMAwpDi4YYQH2VZlLlW8C6hteEGs3tN39inT4W4suXatE/92OZcV5OHLiFDbv9BNr5kTfvsqzU+/U01NL4ycuKadDMSTjGAV8CtmG0X9cRGgXs0cBXtJwrUkcY1spmgOLXtCKWL7BV1MZ1wEqQcnOdQV0DbvMm/LYnS9K8SDf+T429ycVSJaFoZaDqBd/aVERwqxR96ahT0HySoIIAamOCao1J0ZWMIrVXzb0yFFNwER+wXQiY+Fa9DE5tgRF8BfDmttQLtjBr7VlqDk6A+YOYcI8+KUp0JoEIbjQlz2I60QZ2hUUMoHXwfywdglpzMNW6VR2ntKxJ/+dbLwkQNOP/WGt/PxN62jnRtMa52qQaBqx6tbAB01obZucZljo+htxZEizujedGm+bTW2xX1lNXYspo+IG5T5pG9HNbdThSH2iI2e/TXZub6R7erRjL8IXXo3vMfzhuXA4xCcOkUFT/OLjUDjGNy4MiXP84yMBucjDZfGRm9xoGj+5yvmR8pW7/EDJyAmkzc3tgy9x3+AouL5riIoKZJMeSrXWzF/+j5LbcJ+zeKBHbngLnbtChlbkdAaBecKOrgLDPDc3zpmEC2nqMJm48LkikU70XxSPp0mv+cDjt/Wnh1oWU1xCKme9BKubD7ltD+bWk4WLPIGHF5TJ8y0oACj/fZa47P9ouSzQzuhkchawhv6JoxE5SlrWkLmtLKAvYfvKWDIBfWhNay6J25VK+jKtGHx1DTvf0dMHMPS59kGsUd9rWV+JlVsXe2IQcwARfGqlh7FucxIVad77nkTX/bvgbbFNwyPe7IqPBeEDdc9zwvem1e+x9n8sIjNy0J9brqj3WW1Rf0Yd6xEeqURJyVXSozKvbb0wcn/A4Ra2da3hzzpXfGPPDOSkOcJBIYCyfCJST8Dxf7MRgD0XYzS3JdP3fEZjdLnwgEkUWCN2fczWXyMGWvY1VzQQYQVHSnG1V1iXS3jVUVx1gjNwAA6VVxFGgnhXQLn0glQEgjg3QSly/y08BRZ8xyEMyCTqsYPSQYBYdS0UCIGJpww9CGJ2ohsY6D7VRWKsUEH1A2FvBUNU+Gts1UbEhXUTRQTLlT+tRUUq6IJi6FESJoMEhEpnRQQ2mHXuYyKSFoSQwRVLSIQtciITQId76Gd4CHcvtoRIqAtnh3Tp8V7plIGFJYWqYGGV9IaOuIUFBIMfVUAaxmF2J1eoRFwumIloWGYghYI1eIVwKF2JGIRV4h+J9XNTUoc6mIp49oOuwB9oN4gRF32wwHg3dk45hie7uH0vhRvSEWb2J1FcRoxsRX8zdEVkBkNmhmYSIlAnxFDwJ1H/04xq2GUShX/GCFuBIlmR4Sfqcf+EfAaOLJZT4egX41h4C8hNyuFN8OhfFbgltWiLtsA+XWeIA2Boi1FMivYXw0QcH0Z598ZKtnZ7TKB6E2WQWzh7G4RruiZmlnR6LbRXU5RKEUaRt4ZCsZeNCOmNrpZ7dlFprHItrWIXxcR78thzq9KHi3GS/UUI5PWO8hiPrwIuzmePuHB24GJz0eB08iCIrdCDTKdIOBkLROlY63GUsHCEzaeTtVA8MhcZPgkNQBkPT8kK3sJ1qrBntLCVWdkTP+cKusdNQweVLPcMVamWeScPZRlMFyAIf8cL8zKXHCFIaLkL+JiXJ+dxfPk7fvmXuxOYgmk7e1mYH0eYiOk9uLj/mAynmI65PhIYmRwHmSRXEdTDCJFCY0QkLcBSccZyj0WncQAnYx0HFaK5mdbGme1GLpb1K5cxVLBZMZZZDJBSWdkGCyghm+VSJqCBHUYRFe+QJpjZDNAiJjwSaBHXHZo5Y9hGEsQpLQoEm8lwnM+JI7/CbnpkbT/SHdm5I2oRnRFyJoH2IDCnDr03E+kZm7r5mVHJD2CCLvoAE0eBnUzjnAukncwwnkPFn3oJmt1JnWTim9FJnY1JC/75KN65IAzkEOqVXg9Cn9VinYJmEnsUEBLangfaDFgSWr2SGeD5n0LUoGYhKd1Dn4xiHfoZGtP5Hy8WItZUGsQnn9Pin4Wz/5vsyW4Kl6AnuqCE8qLNEaORMaM02qALqp9mUqITuiPE+aDDKRLKGZ+88qQC6pnrIHh/yJ04+p5k0SwyFiRpwiDliSadyZvkwh8wOpKRBZ97lC7RByQK9Jq7wil0OhEUGhpViqZBqqYauDT+4KCiEXIQ+qUWqpno5aPhyZtokpx4OqC6uaHL4D5MBCT7OZvR4qi3WaZPKi6Mip+8clnWwiJpqkquQaQKqiM2WnRmeqmKCiGXOqaDGqUSAqSzkWf6YaqnepupGqV3WqZz+qeIGqJpwUC/yaJm0aKVtQ4d2oGouqgFmpqu6iCsCqua+qnWOq0WGqwuKqp7yiFrGp+vyf+rUaleytmjAlqf5uKjQXKt20op3Won3wquvxqtzDmdXsqq1jqeGeoZM5ap2wmsA+qak5kNZfmW8NmqA/uv6homTvqdw/qnKUqv7NqdZ0qrkQRIfXqeshmuXPqp2kmt/7qp5MqvzfqwrKCntToIdoGrBtOrAdoo9sqigrowzUqeo0AyZ9GjVDqxyZoOQRd0+VKgudmxL2uymGqsjAqxwCquC0KmjXqyFotkBSCj1Cal82qe7emd6gOrK3ptJcsZwZm0Y4KyTUgbVDuW3OmwEZqf82ma19G21wm2wlqayBmmMruvAwGpybCEQrma7TabUcNHceq2qqmgYsqupQmwFVr/tqRaq9M2bWi7pRVKm+QKIYP7tJh7HheKGQZ6qlqpHKMaj2gLoc9apUzrqJiVs9Gio/N6Wet6uLLas+egHrjqsLhZui4juEYrp10LsPfamboKqpRSGqwxknZxgFVLEXCam1UDsoQaomNat87yt0ZVJ8Xrg+Hhf8mrvM4Koj0inkuKbURUrolqu0SlulybL+mwTWGpqJBCvu5JvQt7tOUrEOk7t8JqcXJ4ZHYhHIPArHIav4vLuUtbnFvrm52LuQskvHkIdozrv4LArKgrv4hLuMPqLHAKLPAbvsILnEarseXAt+z4t++LwFWKsGxrrw/6wR9av8Eauyf8Lw7KnhRM/8AMW5w3sqp0+6pYm7B6OcNpy8JhmyvSeSByq8AkLMCKi69Oaw7riRMiAG9LoRJDHLGTyZqEi5ym+28rjK4obMPb8iwia6RQI8DmCTAhgTNePLnV07JjDKJvbMVaLK0aq7lvC8fiK2ic2sV5S5mId5h+DHG1GcieM8iErDmGfMiTA8iKPD2J3MhGpLeQPJiSPMmG6cOW7DyPfJq9KQ+9CxLQasbxe6NZaywkYanpU8k3Q8efjA6t7A29arfNI7BNq8TZarXPIE7+WjOb/ApP/MSO4qulu0Rrp3RSjKC3W8lriZmymqopPLJim8wrzMSSm5k1ysIxw8jTsKyHh79jHP+gOFTManfMS6OkVXpexEzOUKMrR4qvrUypuKK58AyI3JqyepixNKwa2prNqlwMWBpj82mfD/vKrKBExrycNHvOshhs6iyabRqoxfqle2ynsTm9M0u6sxi1fFq7aTy4KIqsZNPPwyCpa+e7eOqlBC1aotJOT7iPheTSYOd1OfrFYNF41XSTEyBNwiEYVcITvfd7zIzKPFq4AUsmEP3MVsrAZGurpbq9rfu0MBzSyioeHorAGTqlDIweOaYXj/cXPoVj2ZcnZwm9glbTOEUgIZIsYs1s3lIcOSjB9CvEvkq/m4vNGlyyUFvPjOstHJ3CjRrAZIPJ01Cwo+uxJp25sxD/IsKUiIe4gSK2ikjRsEVV09GlfVWxih0hH7Oxiiyry637sTSqwOuazx0N0rOq19a0sk7dzuA8wCfTywWNl0DLvRLqzDEsWgDI0oztWfNEXQpwhwxIoWNC2XbWIewB3COiiitSu+4JplX8rOR51z9CrKic1+7quGY7pKv9vS363DkD26sgwqWM1X+9mWOi2LodYhrY2JA90wJM3CDmHiOwUsOnWFCSH/PR2ew8ykQ9nLVcpBucrrYseKFbk9uduLtMM+DdrvhMw6Ad0Qmt1dXE1b3o1W/2i2str+8teZUNHyRwKOAIQfRFZ9xU2A0indSduiZNtwiO4tOsCtYbJdjb/38s+8y8mz6CDQ3sy5TBvLpsq8T+uCH+uGgBOU1AlJINPcGTzeF2ZieyguQgBhywoo7bbcE1fL7lbccqmsS2TBsqa2Tz2CqqFb1KLtKEkw7inbXBi9iunXFYa+ZJmMNB/MVFDdh3jLt0Di8MOkTVbTMLLhlR7ByBrqG4KYFwXjDjWw5uTLqmnbNW7LWnTC3+2sPbUuj1atpwc+iZPDravOnD8+eebjegHupyM+qkvjadfuq6Y+qqfjas3upj8+qw7jWpPuuvI+uQHOkHC6DspelsgeuNrLoG7Nop3ebYguuDUU0mbpU+hpcMfQ6O1nclbSEGq+JqftsQ670u/uKd4v/sGZPjz7Aseda+eubAMfFh1R5EywwNxL3unQI/U1kNltEqY40LjZXkRrlp0sztAD7Mxt7cr5DulOPrvqAefQuICpClUvFh5Phn7u4M7T7tDUgfJWACy04Tqn3xtYDOr3DwJyzs+Szn2r7tlE4pI1wkpt4RJ/BDfqF7g2F8QJ18W6Lw7VPkXT15TGlNG8iPUAzU0rSVW7mENk0fPQ2v3toT7Bjxy1T0iCaXS08jcfjb/vHTyBd8N+9M0ET0jVbkOu8lFYJklKfTznSxLsJIW09NHBK5uLzvFUGaV07uF5LyHgFUy2GATZ3bW+GHKLki16UYO4aONy9sA2cZf08gCFj/juh4qwhA5SxS+L7IE4jV1IwfLEwOZyG2f3YfHEwVxUty+Ap48z41UyxlUxaOjkHkJZRBIPLk1thnTio2j2vt+C4Z2UMr3czs7/1+LvID7LkM7s7w+f2Wg9G0T7dC8+hR2YGvfQ3u8JU9jvXt2AtQAihAX86eYpi9dNJP/fquKGu2ikI4StMF+O8UhMQveS2dbJBx/XY27z8EdhQQAR9+X9e93hao7k/1/Cn28CyH6V0exOfCH4AQEABAWGh4iJiouMjY6PgIeSgYSVkZKTBAcICQAIBJQFggGCAxsdkJsGBhymlYoUD56Zkp4DCRuvope4pYADsL+skLUGBQ+ApQ/5FAofBKgZFRKPtJMSrYqczsDH3oC0xtHZCgyooqKlgqW2GcbDxMzlt7Sz6diTtRbZ1QD+oNMFxoE7dn0QjlG7XPXjJY/j4VO6aAnyxGgwxVjHTxUcVwkyhy7LgIoKWRJEuadATypMpHwlrJInfvHQRB7CAaDLcMp0Rb93TZExnqF79hBP+1KpbNgD9C/IoaQrpM6a+nsHwScEpowcwA7GDSo3XBWimZNJny/BrMHjmswNr6A0ohhQlYWNnuOio0U9FTEhUuyggAcKKP1hptlIQxEsCUKxs7fsz4sWRg8S50olAq2dhWACjUPITskj1dljVPsNq2G7uhnAWUxjyvhP+K0wNWgJI2GiyqQgtk07bdqyot3YY8H4JtmlpmT5aJfmaOLV1uUPRK407bj51I18vSvY5onekFYwsYZGdKHLbE8JIIF1aUMb5hQoAFI7qIn9HEwPYn+///V2AATtYSKqFVIMgHtgyzySiwNIgQJPx0JkgItqAG0yFaicPabvlEcJtAGYhoyIQbknKLiCR2IxwBL221mYOEIBiAgrQREFooGLDAGYQB/JKPhTe25RWMQ74ojo8gPtROKjNldqJ0HlyT1UwJgfIhdm3lCB9i8xUiH0X0edllfmAukuGYA67JpkX9tdkYk40wWd5tcBr03J16PtWVeSfR6eeeJe2nkpz/g92HkkX0ReblR2LWp2Yixp0paKUrMWopSWkusk5WqwiqTKaVdnrPSaRuKipLfqmEKqVkQvLmoQIq2uWi7zESaqSp7gprrLw+kqt+YQUAoqDy/Aqna4IUe5KyxNqJrH6rqhRstGwea222h/qqbbfefgtuuOKOS25jmJaLbrrqrstuu+6SdO678s5Lb7323jtgvPjuy2+//v677iDcAkxwwQYfjLB/+ibMcMMOP8zwwhBPTHHFFqcr8cUab8xxx3cK7HHIIo9MMmQDl4xyyt6692asZgaocsYqz0xzqv1BGqlgYcoa88nWbojNbuFOpAy2jllldCKEeosaSUuPHOrN/4jOqjN/jtrKkTQ8aQzyuHUu5OktJIkylSVvcdYZNyWq9Wm1K6HmNjF5QZutN02PNNEmy2V69ih7F/rL15DAxDKYLIM0sH0ZxU2xzMgOYyglmzRTdiVne6j2dcSwM+nRw2GZZ0PTWmv354Mq1JsJQld6uX9LWc6Oy7PC3NHVWIdTXJ6N+7wrJicggM4EuW5Y7AEiTGnMQcyCjYjy1G2FDUIAEWQ88uqg0qoitTxA5bFovfTpMQhhMqU4/xzPlZNUkkqqnNtTqb757LMTeekuqgX9Tcs+/2xqvjBZvfQ5z3u5cAD3fpQP7OXvffKTHtqIwQ1kbAhKCzSgIGBBIw7hr/97FjRfMlAhO8Ph7kyJ41mpuMa7VBXIKJ0QgAaiUQBokGiFNjnECrlTqtYZRIYI4AYNwaGPg1zDNclLx1lyYbqlNWQ8ndmMD19TCuohYDO7IWITT1OatfCQiiz6BnHQEo8XQjADuxABAWAyw0zE44hYhEY1kgcNHFaniLfQISEmp57vyDEXF4DG10TnIihikY6LKZyrdKUm21kta5pb1MS6Jq4bTgsenFkAB2BYNi5l5ZJyS1s0eGFHKaKiaUBZxE7mgcT7gQIogKQMZ/bCiU/0BgUu2lpbqMENvmRilrUUWyHsh8tPcsKSmNRQKq+Iw3dw0m5sNNEqYHklt/zCR0D/igAJLpM5aN6lE4DU5nSehhJG0upLryrk7sglyducA0XvWIVINBmTVgjRfKHk4ShNx0IJfbOZpmPl3FxpjpogIxuUw0pTcPJBbSiALZ20yjzH4U7OrFM6oJhfPuPZQn5qaS06mU4nLxqQHkajLh29DTIAKadX9AVaOKuVrBTpHkOU8pEpFFU6S0VJBZbFFZWLH+cyF5QaenIYpOTEQx96yp7084Ef7dAOhclNAyTldU0B6uam2lNg6gWoJ+rKp743rOCRJX2e0CiRngnUCdnRIHKhSzbT+hO8oOcquYzlN1fV0tuJk2qvGuchzElTdKrRJYI0jXMUEZrJdeY5OORT/yGQ45oHtYY4qXHEeqJzowk59paDHWVhezMbTABHc6RZHWh/Q7eGfq6xeDpOZipAUeRA57AlKg1sj8Q2LApNrdp5IHfyeBnw7JaJf1xNeij6jdIcrnZ+DVNeZ/ey/TiOX5AcWmdn9Asa2YhBwLugUfShWGpWKXjqM4eMLqqOwIXPsh4N0oVMhyqgOVV/zFKRSNe2USMZhYdWVa0q4xcj7yYjQe/FUdliyCNUiJdCARCSM+dhJBNZSUkEYFKnJgjh4JEPfvK1R5Y4W9GyPXdns+PrOCEZpk3VdF/TrZvuetGn1KomK4F6DJM6ByAc+wtQMqYEjwXb45HIqWrNtRWtbP9XXRPjeMUsZjKysgcar/oSsVKeDEwYJxks4+tU6yUJl6cMLnBq6lOKE1OtcIafER5jN07GV4uzpWVpDGt5ppxzkE21D1v+J2n+chadnWbnconZy6tDFp8bV+KaKXrRjEbYmxsN6UhLGlyPnrSlL41pQVU605zutKdVkuRPi3rUpL5Um0uN6lR7etOqbrWrI83qV8t61igLNa1vjetGxzrXvO61w3bt62AL+1/AHraxjy0vWyN72cwm9qmbDe1oU/rZ0q62tXlV7Gtre9tsUja3vw1um1E73OQud0mybe50q/sv4163u98toHbDe97mRje9781te+N739XWN7//vWz/bwN84Or2N8EP3muDI3zhtFY4wx+uaoFDfOLNdjjFL75qeWN846O2OLoGrS6i5RnMHNe2xKM1vQj6LUVMVdWdC/WjktSzIJq7cqFLfm2PNybl0TiQO1rO3peXZHLwjMTMbWiPG78Y59LW+Up4zjxRIoKApkCfAUBuCOKt0uoM7h92JTWKYp0oetfgeQCvbo/hdZnp1na6SqDuc5BqDZWs8KFfHioNMcZwRPel4deRrgnCdgImM09j4O/Zj/6yHdknfxzaipLBabac6kQdXSNyusliCpU3y3QrVD9q9sn+N597X3zTNT4guDMkM6WkvOghMdFyhO2dPcU83gsv+rwB/930zHY7taYCeclKttCuR/xjERo2jI6XrJtf/k9pHtS/DzX3Cpkp7wOO+gFB9gLZ/cXeW29bLlaWEci5LW0RkVgE0IWx7NncYzMTWfRSVhalv37AEx2udU4lNC0ZRTYrtCCvBywEJnvf5V0+kiTqZ4AxB2AZZl4CVnkhlnz1Z3+Ml30U82OMkIE2xjk/d3MVGGy+ZzBf1ggkKBk2lwogAIK9d4ET42dC94L/UTR6toLH1ng1iIOMJoI5yIMFs4M9CIT98oNBSIT1coNFiIQbM4RJyIQB04JNCIVG+IRRSIXtcoRViIUEs4RZyIXIsoVt8lx95Vcwo4UuBSsa0Su3Uv9iU2iGIZQoJ0Fie/IykvaF3SaGJORSe7URe8UuRNYeezgKZihChTNCJBaHcHKI+cGHhzQSh7gmyyUwalZr+BcuxhMiZgQfhnRiPGOIiNRuWAcg3HIYiSSIpQhdujKHi8SGnvgeIJOKqriIb4hIevKKhxGL0iJ091KHleAUDNVXZdZcheFcgRiG68SAIfU3c2V0gVNj8AKLo+hcpviMrUgpw0iMYxhSnaBY5yaMRxZvs0iJ54aNiAB3Kyd3zmhis8IlkaOMB7OLlJAjRTeGfjiPg5gSgKiGVnRHCPAZqUN8lqcIryMZOtON/CGNaJhIhWg4d5hPFaB4ieFI3uiKahb/ibdITuEoU48XQdnlgY+iKPERiKfoSft4c6Coi6v4GBOBdTBVkcL4Mp0oktkIeL+kAHKSDx+wQcXCQBg0PpkQGuQwdqZYNSjmEbdyOLeDijkzNeRoVMsRlD+5dq5CZK64howIjgh5lRmJOT0HJPZ0kekIjZTSWEXSP7rARvHzgQGDkd3Siw/JiCsWkjCZigSxIS1gC7liiTCBQxUAIi4EQ3HURxnwR3NTFMWwRyQ3i0P5jW74KtFIitUImVe5CS4QKIdZmEtnJpm5mGAZmY04jn+lkVw5Ix0ZIIn4mLPTPsbgl2NklnR3mFKILsESZ3oFlxOphpNAGEGBQw+RK8A1/5KEghakNzcFAk0Q6SbEqIafKZedSTVGKZnAozbedHjsFpImRpVdM4eVhp1rCVKQd17Wx1e5uUh4+H4FyBupRHXFSS/vyBJbc2hLyYox9Y2PWZFK6QrLwA2+YImjyZ9FQX8rRzQM8U+vwFH68CjiNJFZGY5HKZKM9JLP+QIK2HXrowBQFpNheZ05o4lnuJmMoHrEwHq7t5BWOYrzKR4QJTax15r3gHfriZJxUhPsaEIY6Ya26I26YonGoRX4GQ3OEjz+uUtfdUyg9E/PMAJwBH1mlpgmypif2ZxxeaP41x+nsKMjWRwYcKRKOpWbWaItBZJfQpUlCHwbaRTD55Fjgv9mzBVldCKkmcVGvmiQfcidKNcjIjqNkhiTm+iJaPoefBEWWOp1ssUdewki5ecd5OEnTFIP1wQdWJmYDMqQoviL9vmZvOCba8OoZLilGZqn6RiRbWiVrnUL8cc83yeiXbqGt4kI5TFahppZ4adbg/Gi/8Gelxc+FlqP8CmleAgpqBoKS8c8OpIBQZJ2BLhhHtRhJuWU+iWIivmKymkJaEaiijAMDimYzHpbWjo1YVpd9MiQSamgoXBezNN/gvCQvkomSbYiA1YjBcZgQtKATjoutaqBMQqsoVof4imV2cmZ4nGvTgOQl6KrHbqWUkMJU5mcc+oIMnqckYGqDxqpSpr/aLNqokvaqbT6LvRKrZh4PkLXMrJIqXyKOGqIgD0FsLmIjjGVZocEiQo7j2qaqm9np3kKs3FKniI0oxIbqgpja736reaSsS6LbCY5aYLThRpBsUertL+isUvrtLSatE8rtbQotFNrteXStFertY0YtVvrtSbztWGLMVUrtmUrbmaLttNGbOGqsM8WhpD6sxy6s2Q7sMBIohsqr3CYrvkysBN7H3g7t4GLiHsLMFnrGCW6rXLbsM+IjWHIslKJjnN7Mwg6qXX7rTvzrIahh3fqkglLm295h976sZB7lacGqTDFucjpuepyhb+ynx1rZqg7uUeJuKWYr/IZmTXVsrt7/5r3GLq6CrfW6ZlFpqcSqZBgGYd2+5Z4G5ayapGOmqt7arEtabiamy5tmaQS+ZWCu53UiZsOSrq0+bxlsrMtk6BoSoZIeTUQK754+pXdy6G+ips26223+73HS7PL1bgmgSk+W7XNS7de2LUmEY8mC5kECbip2HiZy76I0bo5+4cUaTUXyhh2a7AXi75MhqGkKB8gCbO2OL8a6rgMHL4OLKXdyr/xJq3zaaM3O54YPK8DfLLtKKueOqPA6LO4S8K4S5/Rqr+fuq+4I8HjuMP62sPRysEh26/ymzVDjK/le6eNkpW1e7BdqpksDLoNqsTk8sCpgr3ka4+Wm8XMWWbK1v/AlOpksuOtuxq9H7mgJYzGSByeSTyt/VrHY+zGF1yLcOwm7ObHWbzBc1zH6IoxMjwSspmW4HpmeLzCYTqiesyyo1sJIaSvSNaYJ1y6JXwuKRTI2ymezplmTpyUBJnJIbvJf2u9inujniyyhVidXSzAguaeNAifNTrFiYvFE1y6waug+sLDFru/RebB5cvLAZy7yBvMiijGjzvMuwy4NPrLSIu6gryyg+zChVwucsKwxGtC0Ki/LYy4JMyk9ljJu8zGwby4e9rCxSvFL/nDQIzL3UuUaUrFL+y368zHdAxd39yz7bG9VUnN6CvQ1VizY2vIklOniYyKkqi8bnycvZv/z8L7sQbrK2tcydU50HGqwFAsyNvyx2fq0MocxpFcwTHbqeKMzyYslP6ss0uqmKfowb5rhQcdCRmCq4ocsUuJueFbzxCdh5kIssAsuBV7xj1txzqdvpx4yRgs0iB9oVK8yLdcymJY0TfMyFsM0zNNLtr8rwCd033Mzk9MowJdwfkKL4oEzBlaz3ll1OBs1mCdt2zt0NacxNxq1czZzvBc1n+L1qJoxHJ9zVvKtitD04/wuq97kWtss/CM1xg9wZK815usu8usy1xauQ4cwpxo1pLNvdtaO/081xlcsX6LKNeYh5ttvkSct+C4x7qc2fXouOECy2lL249Y2LWN24ud/9u7XSnVy9tXO9u/LdxnPdzFDYa3bdxf69vJrbTBzdzPXcPQLd2ghtwKM9RNisBGPNTGPN2KttwprKEHKbpJPdjV3d3+4tyVctgcq9n3+M6im8BoPY3nzWnffaUFAadCrNKl2bnUiNUW3bf0DWvm7QgFrK2rTbhIzYpe7UiOKOAD/nEKAXIxPbJ9DcXRbMNQ/eCXlt578sWlaZyl/cZYPaIeseH1TeC4shuz+dmrm9ojDsMLY98n7oSyLDbvaWQ4/ayvnImauKHMm+I0Li4zztWyCJOqmOB5Hd44DNqnKeQl0+Fwwl0Kvc+qKtUjntKs/bt4/eQjY982HZUKrsijS//mHH3ZL9vlPbPV9oqVO93WUo2d2Y2nGJ7mKfPd6/1yr4y/Kvva6frWWq7adV4zUS7oaDvjhb6Ch47oFajoi857hO7oyh3kkZ6DjU7pbGfpl15ykK7pVpvprubgk2wviv22P97Nok5pCB7UsGl6sX2wMSzKpE3Vl3vmVV7Zqi4qrh7ioz7pika0coxk8b1XM4iYKfk5EAILACzTYv2y8rm7PP6o46s0AQtqn9u+DqtIxI4unL4m50A/Jmu0NiZ5zAKnlfLrVOG9RCm5s36etzCbKuFQVWEZ84s4Y92w1PvB243UjizUooGyXZTuNJvU9IiC297rWecnDdAPBgwgffP/fL9y7jSZ2LKu0yi8WTpGIPiEXfXpziNs5grM0NIbs6HeCBEffRKbZJBt8b/UgdjsNeuVQQlwdmZ5QKjgPFmXP6X3CjE/mX5SFFpXVge0UByWP+1JdzM/LTf/lBFSJp9M3hmudsVuWR2UUa6p8dzhKLFuwU1vyeQJzgQbpb8UR2yE9HSj9PkT8we6vk9PxVFv8OgUmBK/Xz0KFnTkd41KSQuVGZfTKQSxmnvnGm4kQIA5eOvVomV19H23KjeURZ8ikLUMweYMmsanPwhx+FakHhp1F8x3ziKfxz/t+fLsnAtqH4BP9ooPLYxf+HXE8I8b4KTPlMZHgSvD3eoNPN9O/5JzxQ/E9FFPlU8UYE3mIHlXwhbBCT74UFdUjvjKN3687w3F+fgsTYmSXOIzeY7+/g2a/xMc2/XMjMowDvIFSfLSb0qnT/mZ10nQ3/qf2tCN2x+6x01uGS2KXqU6hBqyFFFCg3cr0joxBAP5CQgBghITAgMEAIaICxYTFIKQCQCTlJWUAg4TAIwTBwiSiYeVnJ6Sj5ABkgUKlpWQAKixkwGtlLS2saiTipOltb+WvIqYmpzDogciiK6tt7bMzrCCz7W5ubPQ2NSvr7jW0buZm42+ocuTpJ8Ap5EAq8DStN+34Mze1ruiAOXw/f7/AHEFHEiwIIAKrN7tU2dO2KEFEP8EGbBEAUMGiilMsHK30ZeACzEschpXSBQvThUvtmInCFQicelA8aIEUeKklJYUAnPG0161WfJ+muvFcF2sBCxTXTJ5iBjJY+eadYNlKSjVej2FWqVKDRs9Vz2tTqvHtZLTmPmi1gwwcZ3FnBvhZS1LtyrQutpm8jPIt+9dv4AJFpD0UVXbclDNUWjbqrDZC0gJuTvMsEAAixQkVyDkcFEjxwMdb+7EUJEnVouDQc7J+Be9rz6b2ZWdaPVQg51Fc25q0raubALLgps7++twWbC7Bd2qbbY7i7pJyzx0el3r2i4nFbiuFXZX2t+pgeZV4G3g83Lxol9fyZOgjTUTIOb/jcg0Akhx0UUMQGj7pAoTxVeOJ29VIMgHmXRG0ib7SfbPKSFkMh8B1bn3nn6DFBNRds5JI9U8HXaFEDqN8KUghAnSh10/zM01XHO0EQdjNDR2hZVQz12EonRpVXjfhQxmGCSHHU5T1TwvgjfiguWpxN6Tf0EpJXr+bcJAVFNmWVCVC1zZV1ZGwhgiiNq1lZqW/ix3lZhi1qieNmK5OaM9ah6HT2BcevnlNnKCB9Sdk9000V5oAhZmoYj+AyCJmiTqKDCLLlgQN0kSRJYzI1VA5KNrMlfXpfG8KdxxcBo51ZrN9RkeX5GOZBClIQIEKqMHSbIACJz6dWiuvH4kSARY//L6qK8BACssqwk4deyyzD5JrLHNDqSpstHKKmq12Gar7bbcduvtt+DKSla45JZr7rnopquutruu6+678MYr77zptkvvvfjmq+++/Abkab8AByzwwAR3a2/BCCes8MIMTzpuwxBHLPHE+x5M8cUYZ6zxtv9u7PHHIIc8pcUil2zyySiDlfLKLLf8MckuxyzzzPp2TPPNOOesLsw69+zzz8zyDPTQRBcNpdBGJ6300mley/TTUEeNdNRUV+3z1FZnrbXLWG/t9dcg2wz22GSH/XDZaKctcddqt+12xWe/Lffc+IpN9914v8t23nz3ne3efgcueK6AD2744VLajfjijP9nWXjjkEf+z+OSV255cJdnrvnkTm/ueeaUfy6636GPbvrdpZ+uetuKr+466XG/Ljveqc9ue9a13677063v7vvYuf8u/NDBD2+8zsUfr7zMvS/vvNHJPy/9ydFPb73Z12cPfefaX00mjjjGHTvw43ePvGvOyUjXw+WDXb2W8dW6L1TU/jITuImZqI/Amr55I1c3+t736ue19z2pS4hYkqv8oRBPQAsn2UpM/1qhkPtx6x35w40oLMMW7eSHgh9sz4/406jzKASBARkJku4hQM75aYJfa162ylGlfzTwPmYyD7YScya4pOVbGFSR/giAwgYgQie1QKIlCEWlEAKkSmf/U9xr/vQbKsqCEj2MYfuaZYgT/IgQE1yLKgqkAAO1xBMu8BJOamIsTDzgPacAhRgD4saWvKQYn1HRAv8TCUN4wI7J+GOA9iOJSEWqhnVUSpCUYsi21NCDDRHFHI3yq0VEpI0PKREf7cjGBLZjjntp0kFYURPJzDGRrDBjKk5CyJe8UZETjCJYrugVF3Zoj1szIHvsIwmPrCYdCsiMJm6YgEWlRAAauEiTPoKZDh7zl41IClKO8pGJCPMseSSAgnxYG2sSgkAXcYwwU+KJbxalmuvgDDQdYZFy8qgSQdRmU9bJy0QkM0fDuIAOK3hPUVZwnXs5zTVXQwEFOMYY+swA/worOE9TqNOb74zHN2KDl9fQUqIXJY8OrabL9dSTFzjxBQUiQAJQEFMREGSUglDyFibajynahEk2ecFEhtaHOgwJ6ScUsYASoCCmJeyMTpP1EJ8C1YdQGeoCOKDMDxoDGTiEpCVGopChWigAGxlpSd3ipKHSVB029SpMLSiui7IJfN/xhShxx71qfVQU0lyIeYj5HBi8hYMkXGk0jwKQ3Mi0JPJERE030hkaMmZEmiooQlIqVL4mtozB3ChHIkkAaaLFHZDYzTkgooq44FUyVT0KocDJVSxSUx8jsmkNEZKb/X0qPejD6KG+t5BNcXSLzHorIlJ6k4xshK4fCYlCS//01Jt6hp1OomQkpOlXPAL2q0SyqTnKMdTJJNYASBSqZLdzXSVONqkbXUtbRlLcSqQWPsQtUVU3SigK+La0N9kodCermJbudKyurZQVr7GNWJ21tuQz10dXA5pdQEaYgQIwZjGjGc0OBaG2hUduVjOaxJA1wYUtDUFNWQIVlGQFUZkwh3rqYUOAGISULbB1KNJgwA5mRVVC8GgwbBuiZKcwAzWFQWvcTSsdsS35dChgG7IaJCknNm7Sr3C6otHkVq2j6KnnKDFEwiotSkAMIa0ZETTkkxC3QSXsx4kEEaHnSlKTo9hQhl1yCmiBk7RLMe4iJfNmBEj2u3oE81U3suX/FFHoR/DZ0EEOBBNBr8WckACWlQdJwjmX5I+KjA9IKzkUXiypTUVCMqZrOScm3RlqMuzWIwOWxYDlKVjhunBfavi/71iUTvxFVX9X7FKp4XZbuOwXDPk3XjQL2LWAUSFa/Qun4rAQHP27lRbjtet+ERBgz0J1qoENmGbvUBwBNp+2cxbqbXsbe98ON9duLe5yr43c5k63wrqt7nYnDMrujje64C3veoeL3tFSlb0jpu+bsRteSt53wwLub3S7y6JVNLLaVP0xhrfC2rraL09WKGZqixnb9MI3YDg4qBGa1IkYXfKqRj4Q76ZQT+u5qoO4aRAU/qPW/3C4X26Y2TBT/8LkAHG5P2DuD5nTRL02uerH5aIL4rCv5xbvB8T1ZvBcFfHPtvISzudE8P8GZOrC4jl9C6V1CSd94x0pyi+wzp6uA8PnZfIxAIzoi4WCXNNV7xzaV8IdvbU1Wgv0RXWwLo+JiupscxQlQlRpq1YmEhRmJKol7Xj4gYSSjIRfJGEc8ErEt4SVjKc8IMU+yU7e5ECSbGUgO1gJdhhrjoR//EUG/0nDax6Wlw995iuvYCrL8ZLLOMUHZK+U0U8k2WjWOwIS8vZnkOnorSjlkD0/aEZy0tfx0nhfmKkS4XcWtmdFfi0OSo7hI/iftvrMBSC6VJUEOZ2FGD/6KcJXG4swmP+gJSw91S/M8qdFnA9FPz/wX4h+QofCwMJ9pCFZAxZ+wxR22SFQ8WdgBohO9cdU97dh6QdR+yeByKRM/1dIAQhQdnYRvmQe1rd1O0FRVlcP4mRQTeF/CgWBm8CCtzEv0tcX7tFxQiaCUjFy2rcS9mUKJDV0pVUKneFyKLWD2/RyI5RVPShVYmVcQngISzhdRVFdP4dc9eWBn8BE9vcO1UVzQKJVPviEPKUnQ2iFigeFbLaDoxBNbzGGtTUhRNFBIrWALKI+f6eDTmJBnOByOmd25hKDfsFZFgItOEcpd5KD7IcKvdSBSnhacmYZ0HEIq4WCcgYQo6WI9MVcMLVgGaD/CJG4Zjd3WAlRcziBUqIldowiUnxFVyI0V1nFiEPhiJsIiaDoiWn3H6GYIaPohKU4HWoBAfLxI4JYfITIX4YIX69Yc5qYjHxILv/WLAhBKHxnIyRIcsbYWxqhhPKViQshiWAIbHHlfqX3XpeYjZN4GmwIhDDFD0qVXlTIhpSAhfuRQ06milgkjuuVXNtkjk5IhOkYhTtIXmoYTvuYXG5oXgoAjcWXKq42ghShQzzFjkQRdqywjMzYdJzyYoWBkLBlKsQmKiqGY6AFZDxWhKzVUOhXhDsndiA5TCJ5Y9o4Sud3Tf3oEvwnY+qkgdrEYwMyfCv2GBxSJXuxkjTmkpMI/5MmKZPGVYFCZpPph5MqhhirgZGQoZEs8hdxdwvVcYIo1WKUcGkjMnd9aJGPYiETaYrRiDnHZlZTBWaLNiSO1hkc5GBtJmdguRCJRgBtGT+H1mWQmCGTVix0iVOQ0EyAuUlcRkkhwHvmBAo+gh9UZkobclWK1hZXZmhgBpd+CVeUlmGDmQFzaZjiACGKGVEjQpa1J1XY5yFHQibVESQO1mcTEJexKSS5xnQIAyb9ZnV8c2ofM2qBgZvTqJuAUWoAd3fRNxZxJzmtAn0YU5uvgpwdmShLd3BiKXBRdgGUBjLTSS/PBnDVaZ3geS5+GJ7k6SjNWJ7oWZHpuZ51853s+f+eQeOe8DmfnHKe9HmfwjKe+Lmf/iKf/PmfT6KfADqgduGfBHqgfCGgCEqgCrqgANqgDuoo25kr3Qk8xhmhDBR2yIhifKFz/uCcHOpAufdpBjGhBWSgC0qPY5eQ7OGbwEBz8ogexFk2EFpuprd4zndGYid4ZdR6s2dHzVeGp/R6iHd7dsSjqYcAabRbb+F5qBRHtkcrrIOi61mATIKA7/d98teA9EcI9kdkQuaAi7kiwKSlNmZM0KGC1PcI1pSBC9JkNHqhB5qFqIGGXIhVN5GE40iGQSiGA1lbrcWkZLgOenpSfzqFekWFIbhWZFOj4YYWcaWicqUSoYWICgKLnDj/i/i1W3wFZ3RVHnalTDWXqHFFkVTjqN8mXvE1j1gajte4p2ahjfpYWfxoXCAlWe71qsAFEiLBjqQqWaZqaxj6C9VRaiqWYEF5YCHJgLFalCWZk2E6VgQ2ksrKkuAIi0yZqMcKp40qp/zZmECyl0MimXhJmYxWeJlpXLL5l5gkZwrEluY6rlmmiLCZqI7maU62bMMKD7yZLi7KLKkRrEuDqt62nDZXLiAqLMmGK2lDsNsWbe5iosOCcQ3rrft6seKCsRoboFS6sRjqsB5rnfYZsiELsiS7byZ7svWWsirrbiPbshfLsjCrbjI7s+ZWsykjAD6QA/izs3Tks/JzLxV6/z0vmy1ktx5HCwweCiU6y7MAIQNEgA5G4LT9cAA6wAQ9MApFAAUz8AtQuwtAG3M+m7DwMElIi6fIEm44a0Ms2kTf0rQB8bUEYbVL0ASVcANLwLVeG7WJELY957P/2nMEBS2BcRqXVhAzqj0463uS13yr9Fe+13hpdqSQ56NKIbmJh3mXS6T9sABFwARP4LQUwARMkARj+7mhmwhCYAOkG7U3QLpY27Q3wLcLgAQ04Lmkm7VWOwRAKwCrq7e4i7UA8Lq5q7M8kLvo8LnCuw6ka7o8G0a4tw8iIEjKBSxSmHzRy7g32pVt+wtkKz2Lq4gCSF/YNIDh1KVBxYHwd4DMiv9Q5MeCMZl/CGY/QNC1MsC1AvADWXsDoSsA9QsA9zsD+cu1CzC1AMy3TUsBeisDTZC/WVu7NGC1PTC7k8DACizA+rsJtnvAu/ADdksBTkADDrwOITzC/JsDHqGCpFVPkbh9Kiy+OJUdh0sQ3Jo9RZso1GWnhAW5pfGS1eWF2MinMNWEtCrERVm2tnvBfUu1GizCPivBHLzETSvBSizBVMy1B7ADOXDBVawDWSu3cJvAenvFXdu0ZHVZdJpgU1a23SdHEIhEM+x4YEWizxO+oBCpO+xcbng/pWqJldpHmYipssi9KNkKMgC7IazEcHvIpFvCQEvBYOyzswvBowu7WGv/xV7MxVxbybD7xQjss03LwJQwu4q8szPxWe+EFi2cRDVXDpdlizJoit1jx6vKTeW7x/vDW+tgj1lFjrcxq90obU3cxKWcAxDcxHALxZHMswqMAx+st+/oxQDcBBSsyV1bCcu8xF3cA8XsZXrMEKr6g7K8IGgcj5YQxwEhsMJDyysCTy15EGNaafvzkdU6lM36gs/KfxkWQiBMA8Nbwhl8wv38zyLswZtgwKK8xH2rBFk7wtH8wEUQwuuAvxmMzXar0Gacwf1swv0rgfD8TsYqga15Ey1GXXW3xiPtI2VJWBtEx8tzw4jCD+IaPzuCy72YIXlpmelqDuuqmYW5z7VA/7xJIAT+XMm8y7NCTdTGi7yb8Lk9ALcEnbyNHMHSbLUXrcTBK9G4+9Sg7LOVnLrMywRHvcawuZOOSUnGMtKg+U57ZpeRkNI8qdK0wqg23LFlA9XuErjujA4ol4YHy6/j1ddTOBABO87Ts7Y0g9fr8r0Gm0QR9guNTUGPXQsLu22IPTOKvS4mCrHXmZ2BMbTWA9M2i6CXPdraVtqmPct2ndrzKdqs/Z+o/dqhvdqyzZ6xXdvO49q4TZ+3vdvKY0AQcdK+7bK0/b3DrbYWGxoUe9zxpkugzdzi5txfB93RTdvSK8zU7W263QrqnN3gS9vP7d2nDd7LLd7VXSjhbd42nLLcKcSc6q3dqx3c751uvT3frlPf9n06253fzW3d/H3e/03a/h3gls3eBC5w+H3gm5PgCg46A97g6/3gEE60Ej7hh13hFl7HGJ7hv23gHE7fG/7hxsPgIr44Y9EpKF4nKb7iKt7iLP7iLh7jMD7jMl7jNH7jNp7jOL7jOt7jPP7jPh7kQD7kQl7kRH7kRp7kSL7kxsHkTq7kUP7kUh7lVD7lVl7lWH7lWp7lXL7lXt7lLR4IADs=" v:shapes="_x0000_s1822"> |

 

 

 

- **方法规范**

 

从前面的动态生成的 Adaptive 类中的 adaptiveMethod()方法体可知，其对于要加载的扩展名的指定方式是通过 URL 类型的方法参数指定的。所以对于 Adaptive 方法的定义规范仅一条：其参数包含 URL 类型的参数，或参数可以获取到 URL 类型的值。方法调用者是通过URL 传递要加载的扩展名的。

 

 

# 5.3.4    Adaptive 方法代码举例 14-adaptivemethod

 

- **创建工程**

 

复制 14-adaptiveclass 工程，在其基础之上修改。

 

# （2） 修改 SPI 接口

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **修改两个扩展类**

|      |      |
| ---- | ---- |
|      |      |

 

 

- **删除原来的** **Adaptive** **类**

 

若存在 Adaptive 类，即使定义了 Adaptive 方法，其执行的也是 Adaptive 类，所以这里

 

要首先将 Adaptive 类删除。

 

# （5） 定义扩展类配置文件

 

由于 Adaptive 类已经删除，所以在配置文件中也需要将 Adaptive 类的注册也删除。

 

# （6） 测试 1

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **测试** **2**

 

- **Wrapper** **机制**

 

Wrapper 机制，即扩展类的包装机制。就是对扩展类中的 SPI 接口方法进行增强，进行包装，是 AOP 思想的体现，是 Wrapper 设计模式的应用。一个 SPI 可以包含多个 Wrapper。

 

# 5.4.1    Wrapper 类规范

 

Wrapper 机制不是通过注解实现的，而是通过 Wrapper 类实现的。

Wrapper 类在定义时需要遵循如下规范。

- 该类要实现 SPI 接口
- 该类中要有 SPI 接口的引用
- 在接口实现方法中要调用 SPI 接口引用对象的相应方法
- 该类名称一般以 Wrapper 结尾（不是必需的）

 

# 5.4.2    代码举例 15-wrapper

 

复制 14-adaptivemethod 工程，在其基础之上修改。

 

# （1） 定义两个 wrapper 类

|      |      |
| ---- | ---- |
|      |      |

 

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

- **修改扩展类配置文件**

 

将这两个wrapper 注册到扩展类配置文件中。

 

 

 

 

 

 

 

 

 

 

 

 

# 5.5 Activate 机制

 

用于激活扩展类的。

Activate 机制，即扩展类的激活机制。通过指定的条件来激活当前的扩展类。其是通过

@Activate 注解实现的。

 

 

# 5.5.1    @Activate 注解

 

在@Activate 注解中共有五个属性，其中 before、after 两个属性已经过时，剩余有效属性还有三个。它们的意义为：

- group：为扩展类指定所属的组别，是当前扩展类的一个标识。String[]类型，表示一个扩展类可以属于多个组。

- value：为当前扩展类指定的 key，是当前扩展类的一个标识。String[]类型，表示一个扩展类可以有多个指定的 key。
- order：指定筛选条件相同的扩展类的加载顺序。序号越小，优先级越高。默认值为 0。order 值相同，则按照注册顺序的逆序进行加载。

 

# 5.5.2    代码举例

 

- **创建工程**

 

复制 13-dubbospi 工程，在其基础之上修改。

 

# （2） 修改扩展类 AlipayOrder

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **修改扩展类** **WeChatOrder**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **定义扩展类** **CardOrder**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **定义扩展类** **CashOrder**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **定义扩展类** **CouponOrder**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **注册扩展类**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **测试**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **总结**

 

- 在配置文件中可能会存在四种类：普通扩展类，Adaptive 类，Wrapper 类，及添加了

@Activate 注解的扩展类。它们的共同点是，都实现了 SPI 接口。

- 一个 SPI 接口的 Adaptive 类只能有一个（无论是否是自动生成的），而 Wrapper 类与

Activate 类都可以有多个。

- 只有普通扩展类与 Activate 类属于扩展类，而 Adaptive 与 Wrapper 类均不属于扩展类
- Adaptive、Activate 都是通过注解实现的，而 Wrapper 则不是。

 

# 5.7 Dubbo 的 SPI 源码解析

 

下面以 Spring 容器的获取过程为例来解析 SPI 的执行过程。

 

# 5.7.1    总思路

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **代码解析**

 

- **从** **ServiceConfig** **入手**

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **factory** **实例的** **loader** **的** **extensionFactory** **实例是** **null**

 

这个 objectFactory 实例也是通过 SPI 获取到的。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （2） 获取 factory 的extensionLoader

 

构造器执行完毕，则 ExtensionFactory 的 extensionLoader 实例就创建完毕。重新返回其调用语句。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （3） 创建 Container 的 loader 的 extensionFactory

 

跟踪执行 getAdaptiveExtension()。

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

返回 loadExtensionClasses()方法。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

跟踪 cacheAdaptiveClass()方法。

 

 

 

 

 

 

 

 

 

 

 

 

跟踪 cacheWrapperClass()方法。

 

重新返回 loadClass()方法。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

跟踪 cacheActiveClass()方法。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

跟踪 cacheName()方法。

 

|      |      |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |
|      |      |      |
|      |      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

跟踪 saveInExtensionClass()方法。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

可以看出，其是将当前 name 与扩展类配对后放到了 extensionClasses 中了。

 

# （4） 获取Protocol 的 extensionLoader

 

前面的代码执行完毕，然后一层层的返回，最终就返回到了这里。

 

 

 

 

 

 

 

 

 

 

此时 Protocol 的 ExtensionLoader 的 objectFactory 实例就创建完毕。返回其调用语句。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

最后第 131 行返回这个新创建的 loader，返回给该 getExtensionLoader()方法的调用语句。

 

 

 

 

 

 

 

 

 

 

# （5） 获取Protocol 的默认扩展类实例

 

此时再跟踪第 121 行的 getAdaptiveExtension()方法，是通过刚才获取到的 Protocol 的

laoder 对象获取 Protocol 的自适合实例。其执行过程与前面的相同。

 

# 5.8 Dubbo 的 IoC 源码解析

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |

 

跟踪 getExtension()方法。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

使用 Spring 容器获取。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# 5.9 Dubbo 的 AOP 源码解析

 

- **Dubbo** **中** **AOP** **的跟踪位置**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

那么，它们是如何包装 instance 实例的呢？这就要分析这些 Wrapper 类了。

 

 

# 5.9.2    ProtocolFilterWrapper 解析

 

|      |      |
| ---- | ---- |
|      |      |


下面以 ProtocolFilterWrapper 为例进行解析。

然后我们再查看 Protocol 接口中包含的方法。

然后再逐个查看 ProtocolFilterWrapper 类中对这些方法的重写，即对于原 Protocol 实例的增强。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# 5.10    Dubbo 的动态编译 Compile 源码解析

 

Dubbo 的动态编译器有两种：JavassistCompile 与 JdkCompiler。在 Dubbo 框架中，只要是动态编译，使用的都是默认的 JavassistCompiler，而编译器 JdkCompiler 仅是让用户可以进行自由选择使用的。所以我们在这里只分析 JavassistCompile 编译器。

 

# 5.10.1  Javassist 简介

 

Javassist 是一个开源的分析、编辑和创建 Java 字节码的类库。一般情况下，对字节码文件进行修改是需要使用虚拟机指令的。而使用 Javassist，可以直接使用 java 编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构，或者动态生成类。

 

|      |      |
| ---- | ---- |
|      |      |

 

 

# 5.10.2  手工实现Javassist 动态编译 17-javassist

 

- **创建工程**

 

创建一个 Maven 的 Java 工程。

 

# （2） 导入依赖

 

仅需要一个 Javassist 依赖。

|      |                                                              |
| ---- | ------------------------------------------------------------ |
|      | org.javassistjavassist3.26.0-GA " src="data:image/png;base64,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" v:shapes="_x0000_s1864"> |

 

 

 

# （3） 定义JavassistCompiler

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

- **解析****Dubbo** **的动态编译**

 

下面以获取 Protocol 接口的 adaptive 扩展类实例为例进行解析。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |
|      |      |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

此时查看 code 的值，其为一个字符串，其内容就是动态生成的 Adaptive 扩展类。在其上右击，选择 copy value 即可复制该字符串的内容。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

动态生成 Adaptive 代码后，就可以对其进行编译了。

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

 

第6章 **Dubbo** **的源码解析**

 

 

# 6.1 Dubbo 与 Spring 整合

 

- **查找解析器**

 

 

 

 

 

 

 

 

 

 

 

找到 dubbo 依赖，可以看到如下的三个文件，其中就包含 spring.schemas 文件。

 

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |

 

 

打开 spring.schemas 文件，可以看到如下内容：

 

 

 

 

 

 

 

 

打开 spring.handlers 文件，可以看到如下内容：

 

 

 

 

 

 

 

DubboNamespaceHandler 就是 Dubbo 命名空间处理器。

 

|      |      |
| ---- | ---- |
|      |      |

 

 

# 6.1.2    Dubbo 标签的解析

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **重要接口与类**

 

- **Invocation**

 

其封装了远程调用的具体信息。

 

 

 

# 6.2.2    Invoker

 

其是提供者 provider 的代理对象，在代码中就代表提供者。特别是在消费者进行远程调用时，其通过服务路由、负载均衡、集群容错等机制要查找的就是 Invoker。找到了其需要的 Invoker 实例就可以进行远程调用了。

 

# 6.2.3    Exporter

 

服务暴露对象。其包含一个很重要的方法 getInvoker()，用于获取当前服务暴露实例所包含的远程调用实例 Invoker，即可以进行的远程调用。

而 unexport()方法会使服务不进行服务暴露。

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

# 6.2.4    Directory

 

Directory 中包含一个很重要的方法 list()，其返回结果为一个 List<Invoker>。其实简单来说，可以将 Directory 理解为一个 Invoker 列表。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# 6.2.5    RegistryDirectory 类

 

其维护着一个动态 Invoker 列表。

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

# 6.2.6    MockDirectory 类

 

其维护着一个本地服务降级的 Invoker 列表，是一个静态列表。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# 6.3 服务发布

 

- **什么是服务发布**

 

服务发布主要做了两件工作：

- 服务暴露：将服务暴露于外部以让消费者可以直接调用。具体来说完成了以下几项工作：
  - 形成服务暴露 URL
  - 生成服务暴露实例 Exporter
  - 通过Netty 暴露服务
  - 同步转异步 ExchangeServer
- 服务注册：将提供者主机的服务信息写入到zk 中。将业务接口作为节点，在其下再创

 

建提供者子节点（临时节点），子节点名称为提供者主机的各种元数据信息。

 

# 6.3.2    查找下手点

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **生成提供者代理对象** **Invoker**

 

- **任务**

 

这里的任务是生成提供者代理对象 Invoker，然后进行暴露。Invoker 是根据什么形成的呢？

首先要形成服务发布 URL，然后再使用 URL 形成提供者 Invoker。

通过这个 URL，就可以知道这个服务要发布到哪个注册中心、使用的中什么服务暴露协议，服务的真正执行者是谁，等这些信息都可以从URL 中读取到。

 

# （2） 解析

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **进行本地服务暴露**

 

- **任务**

 

本地服务暴露，即其暴露的服务只能让当前提供者主机中相同 JVM 中的消费者进行调用。

 

# （2） 解析

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **进行远程服务暴露**

 

- **任务**

 

该阶段首先会通过 Protocol$Adaptive 自适应类自动选择 RegistryProtocol 来对服务进行暴露与注册。在 RegistryProtocol 中对服务暴露的 URL 进行解析。解析出注册中心的 URL 与提供者 URL。

 

# （2） 解析

 

重新返回 doExportUrlsFor1Protocol()方法。

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# 6.3.6    完成真正的服务暴露

 

- **任务**

 

- 生成并保存服务暴露对象 exporter
- 获取/创建ExchangeServer 实例。将 Dubbo 中的同步暴露过程转换为 NettyServer 的异步过程。
  - Transport 的默认实现是Netty。故第三项工作是，创建一个 Netty Server 对服务时行真正的暴露。

 

# （2） 解析

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

|      |      |
| ---- | ---- |
|      |      |

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **完成服务注册**

 

- **任务**

 

该阶段的任务是将提供者主机的信息写入到zk 中，即以接口为为节点，在其下再创建用于表示当前提供者服务的子节点（临时节点）。

 

# （2） 解析

 

重新返回到 RegistryProtocol 的 export()方法。

 

|      |      |
| ---- | ---- |
|      |      |

 

# 6.4 服务订阅

 

 

- **获取动态代理对象**

 

- **任务**

 

完成了三项任务：

- 构建出消费者 URL
- 根据消费者的 URL 构建出其要调用的 Invoker
- 通过与 Invoker 绑定生成消费者代理对象

 

# （2） 通过工厂获取 Bean

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **创建代理对象**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **获取** **Invoker**

 

- **远程调用**





- **服务路由**

 

- **什么是服务路由**

 

服务路由包含一条或若干条路由规则，路由规则决定了服务消费者的调用目标，即规定了服务消费者可调用哪些服务提供者。Dubbo 目前提供了三种服务路由实现，分别为条件路由 ConditionRouter、脚本路由 ScriptRouter 和标签路由 TagRouter。其中条件路由是我们最常使用的。

下面将以条件路由为例来讲解服务路由的用法。

 

# 6.6.2    路由规则的设置

 

路由规则是在 Dubbo 管控平台 Dubbo-Admin 中的。

 

# （1） 启动管控平台

 

## A、 启动管控台

 

将 dubbo-admin-0.1.jar 文件存放到任意目录下，例如 D 盘根目录下，直接运行。

|      |      |
| ---- | ---- |
|      |      |


注意这里的 dubbo-admin 是已经配置好的。具体配置详见“2.4Dubbo 管理控制台”。

 

## B、 访问

 

在浏览器地址栏中输入 http://localhost:8080 ，即可看到 Dubbo 管理控制台界面。

 

# （2） 设置路由规则

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

就在这里设置路由规则。

 

 

# 6.6.3    路由规则详解

 

- **初识路由规则**

 

应用粒度路由规则：

 

| scope:force:                                                 | applicationtrue  |      |                 |      |
| ------------------------------------------------------------ | ---------------- | ---- | --------------- | ---- |
| runtime: true enabled: truekey: governance-conditionrouter-consumer conditions:*#* *app1* *的消费者只能消费所有端口为* *20880* *的服务提供实例*- application=app1 => address=*:20880*#* *app2* *的消费者只能消费所有端口为* *20881* *的服务提供实例* |                  |      |                 |      |
| -                                                            | application=app2 | =>   | address=*:20881 |      |
|                                                              |                  |      |                 |      |

 

服务粒度路由规则：

 

| scope:force:                                                 | servicetrue  |      |                 |      |
| ------------------------------------------------------------ | ------------ | ---- | --------------- | ---- |
| runtime: true enabled: truekey: org.apache.dubbo.samples.governance.api.DemoService conditions:*#* *DemoService* *的* *sayHello* *方法只能消费所有端口为* *20880* *的服务提供者实例*- method=sayHello => address=*:20880*#* *DemoService* *的* *sayHi* *方法只能消费所有端口为* *20881* *的服务提供者实例* |              |      |                 |      |
| -                                                            | method=sayHi | =>   | address=*:20881 |      |
|                                                              |              |      |                 |      |

 

 

 

# （2） 属性详解

 

**A** **、** **scope**

 

必填项。表示路由规则的作用粒度，其取值将会决定 key 的取值。其取值范围如下：

- service：表示服务粒度
- application：表示应用粒度

 

**B****、** **Key**

 

必填项。指定规则体将作用于哪个服务或应用。其取值取决于 scope 的取值。

- scope 取值为 application 时，key 取值为 application 名称，即<dubbo:application name=””/>

的值。

- scope 取值为 service 时，key 取值为[group:]service[:version]的组合，即组、接口名称与版本号。

 

**C****、** **enabled**

 

可选项。指定当前路由规则是否立即生效。缺省值为 true，表示立即生效。

 

**D****、****force**

 

可选项。指定当路由结果为空时，是否强制执行。如果不强制执行，路由结果为空则路由规则自动失效，不使用路由。缺省为 false，不强制执行。

 

**E****、** **runtime**

 

可选项。指定是否在每次调用时执行路由规则。若为 false 则表示只在提供者地址列表变更时会预先执行路由规则，并将路由结果进行缓存，消费者调用时直接从缓存中获取路由结果。若为 true 则表示每次调用都要重新计算路由规则，其将会直接影响调用的性能。缺省为 false。

 

## F、 priority

 

可选项。用于设置路由规则的优先级，数字越大，优先级越高，越靠前执行。缺省为 0。

 

# （3） 规则体 conditions

 

 

必填项。定义具体的路由规则内容，由 1 到任意多条规则组成。

 

## A、 格式

 

路由规则由两个条件组成，分别用于对服务消费者和提供者进行匹配。

[服务消费者匹配条件] => [服务提供者匹配条件]

- 当消费者的 URL 满足匹配条件时，对该消费者执行后面的过滤规则。

l  => 之后为提供者地址列表的过滤条件，所有参数和提供者的 URL 进行对比，消费者最终只拿到过滤后的地址列表。

 

例如路由规则为：host = 10.20.153.10 => host = 10.20.153.11。其表示 IP 为 10.20.153.10

 

的服务消费者只可调用 IP 为 10.20.153.11 机器上的服务，不可调用其他机器上的服务。

 

不过，这两个条件存在缺省的情况：

- 服务消费者匹配条件为空，表示不对服务消费者进行限制，所有消费者均将被路由到行后面的提供者。
- 服务提供者匹配条件为空，表示对符合消费者条件的消费者将禁止调用任何提供者。

 

例如路由规则为：=> host != 10.20.153.11，则表示所有消费者均可调用IP 为10.20.153.11

之外的所有提供者。

再如路由规则为：host = 10.20.153.10 => ，则表示 IP 为 10.20.153.10 的提供者不能调用任何提供者。

 

## B、 符号支持

 

- 参数符号：
  - method：将调用方法作为路由规则比较的对象
  - argument：将调用方法参数作为路由规则比较的对象
  - protocol：将调用协议作为路由规则比较的对象
  - host：将 IP 作为路由规则比较的对象
  - port：将端口号作为路由规则比较的对象
  - address：将 IP:端口号作为路由规则比较的对象
  - application：将应用名称作为路由规则比较的对象
- 条件符号：
  - 等于号(=)：将参数类型匹配上参数值作为路由条件
  - 不等号(!=)：将参数类型不匹配参数值作为路由条件
- 取值符号：
  - 逗号(,)：多个取值的分隔符，如：host != 20.153.10,10.20.153.11
  - 星号(*)：通配符，如：host != 10.20.*
  - 美元符号($)：表示引用消费者的参数值，如：host = $host

 

## C、 举例

 

- 白名单

host != 10.20.153.10,10.20.153.11 =>

禁用 IP 为 10.20.153.10 与 10.20.153.11 之外的所有主机。

- 黑名单

host = 10.20.153.10,10.20.153.11 =>

IP 为 10.20.153.10 与 10.20.153.11 的主机将被禁用。

- 只暴露一部分的提供者

=> host = 172.22.3.1*,172.22.3.2*

消费者只可访问 IP 为 172.22.3.1*与 172.22.3.2*的提供者主机。

- 为重要应用提供额外的机器

application != kylin => host != 172.22.3.95,172.22.3.96

应用名称不为 kylin 的应用不能访问 172.22.3.95 与 172.22.3.96 两台提供者主机。即只有

 

名称为 kylin 的消费者可以访问 172.22.3.95 与 172.22.3.96 两台提供者主机。当然，kylin 还可以访问其它提供者主机，而其它消费者也可以访问 172.22.3.95 与 172.22.3.96 之外的所有提供者主机。

- 读写分离

method = find*,list*,get*,is* => host = 172.22.3.94,172.22.3.95,172.22.3.96 method != find*,list*,get*,is* => host = 172.22.3.97,172.22.3.98

find、list、get、is 开头的消费者方法会被路由到 172.22.3.94、172.22.3.95 与 172.22.3.96

三台提供者主机，而其它方法则会被路由到 172.22.3.97 与 172.22.3.98 两台提供者主机。

- 前后台分离

application = bops => host = 172.22.3.91,172.22.3.92,172.22.3.93 application != bops => host = 172.22.3.94,172.22.3.95,172.22.3.96

应用名称的 bops 的消费者会被路由到 172.22.3.91、172.22.3.92 与 172.22.3.93 三台提供者主机，而其它消费者则会被路由到 172.22.3.94、172.22.3.95 与 172.22.3.96 三台提供者。

- 隔离不同机房网段

host != 172.22.3.* => host != 172.22.3.*

不是 172.22.3 网段的消费者是不能访问 172.22.3 网段的提供者的。即只有 172.22.3 网段的消费者才可访问 172.22.3 网段的提供者。当然，172.22.3 网段的消费者也可访问其它网段的提供者。

- 只访问本机的服务

=> host = $host

$host 表示获取消费者请求中的消费者主机 IP。故该规则就表示消费者只能访问本机的服务。

 

 

 

# 6.6.4    源码解析

 

- **添加激活** **Router** **到** **Directory**

 

- **读取** **zk** **中的路由规则**

 

## A、 获取 router 子节点 url

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

**B****、 将路由添加到** **directory**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **服务路由过滤**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **服务降级**

 

- **什么是服务降级**

 

- mock=”force:return null”
- mock=”fail:return null”
- mock=”true”
- mock=降级类的全限定性类名

 

# 6.7.2    修改消费者端代码

 

为了演示服务降级，在调试之前首先修改消费者工程的代码。

 

# （1） 定义Mock 类

 

在 org.apache.dubbo.demo 包中创建如下类：

 

# （2） 修改配置文件

 

 

 

 

 

 

 

 

 

 

 

 

- **服务降级的调用**

 

- **forbidden** **的值**

 

我们前面在进行服务订阅时，跟踪到了如下位置，在这里刷新了 invoker。

 

 

 

 

 

 

 

 

 

 

 

在以上位置添加断点后，连接点击三次 F8，就会看到当前的状态，即 urls 的 size 为 1。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

我们注意到这里为 forbidden 赋值为 true，表示当前禁止进行远程访问。

 

# （2） 找到服务降级代码

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

这里会抛出异常，然后直接跳转到如下代码。

 

# 6.8 集群容错

 

 

- **修改配置文件**

 

 

 

 

 

 

 

 

 

- **容错实例的加载与创建**

 

|      |      |
| ---- | ---- |
|      |      |

 

 

- **容错方案的调用**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

再往下就是具体容错策略的解析了。

 

 

 

# 6.8.4    容错策略的解析

 

- **Failover**

 

故障转移策略。当消费者调用提供者集群中的某个服务器失败时，其会自动尝试着调用其它服务器。而重试的次数是通过retries 属性指定的。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （2） Failfast

 

快速失败策略。消费者端只发起一次调用，若失败则立即报错。通常用于非幂等性的写操作，比如新增记录。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （3） Failsafe

 

失败安全策略。当消费者调用提供者出现异常时，直接忽略本次消费操作。该策略通常用于执行相对不太重要的服务。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （4） Failback

 

失败自动恢复策略。消费者调用提供者失败后，Dubbo 会记录下该失败请求，然后定时

 

自动重新发送该请求。该策略通常用于实时性要求不太高的服务。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （5） Forking

 

并行策略。消费者对于同一服务并行调用多个提供者服务器，只要一个成功即调用结束并返回结果。通常用于实时性要求较高的读操作，但其会浪费较多服务器资源。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （6） Broadcast

 

广播策略。广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息。

 

# 6.9 负载均衡

 

- **获取负载均衡策略**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **负载均衡前的准备过程**

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

- **负载均衡策略解析**

 

Dubbo 内置了四种负载均衡策略。

 

# （1） random

 

加权随机算法，是 Dubbo 默认的负载均衡算法。权重越大，获取到负载的机率就越大。权重相同，则会随机分配。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （2） leastactive

 

加权最小活跃度调度算法。活跃度越小，其优选级就越高，被调度到的机率就越高。活跃度相同，则按照加权随机算法进行负载均衡。

 

 

 

 

# （3） roundrobin

 

加权轮询算法。其实就是权重算法。按照设定好的权重，找权重最大的进行调度。是一种“主机权重 与 方法权重”双权重轮询算法。

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

 

# （4） consistenthash

 

一致性 hash 算法。其是一个方法级别的负载均衡。对于同一调用方法的相同实参的远

 

程调用请求，其会被路由到相同的提供者。其是以调用方法的指定实参的 hash 值为 key 进行提供者选择的。