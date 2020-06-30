**NIO** **网络编程框架** **Netty**

**课程讲义**

**主讲：\****Reythor** **雷**

**2020**

**专题：** **NIO** **网络编程框架** **Netty**

# 第1章 Netty 入门

## 1.1 Netty 概述

- **Netty** **简介**

Netty 官网上可以看到最权威的介绍：

- Netty 是一个异步事件驱动的网络应用程序框架，用于快速开发可维护的高性能服务器和客户端。

l Netty 是一个 NIO 客户机-服务器框架，它支持快速、简单地开发网络应用程序，如服务器和客户机。它大大简化了网络编程，如 TCP 和 UDP 套接字服务器。

- “快速和简单”并不意味着生成的应用程序将受到可维护性或性能问题的影响。Netty 经过精心设计，并积累了许多协议（如 ftp、smtp、http）的实施经验，以及各种二进制和基于文本的遗留协议。因此，Netty 成功地找到了一种方法，在不妥协的情况下实现了易于开发、性能、稳定性和灵活性。

## 1.1.2 谁在使用 Netty

Dubbo、zk、RocketMQ、ElasticSearch、Spring5(对 HTTP 协议的实现)、GRpc、Spark 等大型开源项目都在使用 Netty 作为底层通讯框架。

## 1.1.3 Netty 中的核心概念

- **Channel**

管道，其是对 Socket 的封装，其包含了一组 API，大大简化了直接与 Socket 进行操作的复杂性。

## （2） EventLoopGroup

EventLoopGroup 是一个 EventLoop 池，包含很多的 EventLoop。

Netty 为每个 Channel 分配了一个 EventLoop，用于处理用户连接请求、对用户请求的处理等所有事件。EventLoop 本身只是一个线程驱动，在其生命周期内只会绑定一个线程，让该线程处理一个 Channel 的所有 IO 事件。

一个 Channel 一旦与一个 EventLoop 相绑定，那么在 Channel 的整个生命周期内是不能改变的。一个 EventLoop 可以与多个 Channel 绑定。即 Channel 与 EventLoop 的关系是 n:1， 而 EventLoop 与线程的关系是 1:1。

## （3） ServerBootStrap

用于配置整个Netty 代码，将各个组件关联起来。服务端使用的是 ServerBootStrap，而客户端使用的是则 BootStrap。

## （4） ChannelHandler 与 ChannelPipeline

ChannelHandler 是对 Channel 中数据的处理器，这些处理器可以是系统本身定义好的编解码器，也可以是用户自定义的。这些处理器会被统一添加到一个 ChannelPipeline 的对象中， 然后按照添加的顺序对 Channel 中的数据进行依次处理。

## （5） ChannelFuture

Netty 中所有的 I/O 操作都是异步的，即操作不会立即得到返回结果，所以 Netty 中定义了一个 ChannelFuture 对象作为这个异步操作的“代言人”，表示异步操作本身。如果想获取到该异步操作的返回值，可以通过该异步操作对象的 addListener()方法为该异步操作添加监

听器，为其注册回调：当结果出来后马上调用执行。

Netty 的异步编程模型都是建立在 Future 与回调概念之上的。

## 1.1.4 Netty 执行流程

- **牛刀小试** **01-primary**

通过该程序达到的目的是，对Netty 编程的基本结构及流程有所了解。

该程序是通过Netty 实现 HTTP 请求的处理，即接收 HTTP 请求，返回 HTTP 响应。

## 1.2.1 创建工程

创建一个普通的 Maven 的Java 工程。

## 1.2.2 导入依赖

仅导入一个 netty-all 依赖即可。

<**dependencies**>

_<!--_ *netty-all* *依赖* *-->*

<**dependency**>

<**groupId**>io.netty</**groupId**>

<**artifactId**>netty-all</**artifactId**>

<**version**>4.1.36.Final</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>org.projectlombok</**groupId**>

<**artifactId**>lombok</**artifactId**>

<**version**>1.18.6</**version**>

<**scope**>provided</**scope**>

</**dependency**>

</**dependencies**>

## 1.2.3 定义服务器启动类

该服务器就是用于创建并初始化服务器启动对象 ServerBootStrap。

## 1.2.4 定义管道初始化器

- **定义服务端处理器**
- **Socket** **编程** **02-socket**

前面的工程是一个仅存在服务端的HTTP 请求的服务器，而 Netty 中最为最见的是 C/S

构架的 Socket 代码。所以下面我们就来看一个 Netty 的 Socket 通信代码。

## 1.3.1 创建工程

创建一个普通的 Maven 的Java 工程。

本例要实现的功能是：客户端连接上服务端后，其马上会向服务端发送一个数据。服务端在接收到数据后，会马上向客户端也回复一个数据。客户端每收到服务端的一个数据后， 便会再向服务端发送一个数据。而服务端每收到客户端的一个数据后，便会再向客户端发送一个数据。如此反复，无穷匮也。

## 1.3.2 定义服务端

- **定义服务端启动类**
- **定义服务端处理器**
- **定义客户端**
- **定义客户端启动类**
- **定义客户端处理器**
- **两个处理器的区别**

SimpleChannelInboundHandler 中的channelRead()方法会自动释放接收到的来自于对方的msg

所占有的所有资源。

ChannelInboundHandlerAdapter 中的 channelRead()方法不会自动释放接收到的来自于对方的msg

- 若对方没有向自己发送数据，则自定义处理器建议继承自ChannelInboundHandlerAdapter。因为若继承自 SimpleChannelInboundHandler 需要重写channelRead0()方法。而重写该方法的目的是对来自于对方的数据进行处理。因为对方根本就没有发送数据，所以也就没有必要重写 channelRead0()方法。
- 若对方向自己发送了数据，而自己又需要将该数据再发送给对方，则自定义处理器建议

继承自 ChannelInboundHandlerAdapter。因为 write()方法的执行是异步的，且SimpleChannelInboundHandler 中的 channelRead()方法会自动释放掉来自于对方的 msg。若write()方法中正在处理 msg，而此时 SimpleChannelInboundHandler 中的channelRead()方法执行完毕了，将 msg 给释放了。此时就会报错。

# 第2章 TCP 的拆包与粘包

## 2.1 拆包粘包简介

Netty 在基于 TCP 协议的网络通信中，存在拆包与粘包情况。拆包与粘包同时发生在数据的发送方与接收方两方。

发送方通过网络每发送一批二进制数据包，那么这次所发送的数据包就称为一帧，即Frame。在进行基于 TCP 的网络传输时，TCP 协议会将用户真正要发送的数据根据当前缓存的实际情况对其进行拆分或重组，变为用于网络传输的 Frame。在 Netty 中就是将 ByteBuf 中的数据拆分或重组为二进制的 Frame。而接收方则需要将接收到的 Frame 中的数据进行重组或拆分，重新恢复为发送方发送时的 ByteBuf 数据。

具体场景描述：

l 发送方发送的 ByteBuf 较大，在传输之前会被 TCP 底层拆分为多个 Frame 进行发送，这个过程称为发送拆包；接收方在接收到需要将这些 Frame 进行合并，这个合并的过程称为接收方粘包。

l 发送方发送的 ByteBuf 较小，无法形成一个 Frame，此时 TCP 底层会将很多的这样的小的 ByteBuf 合并为一个 Frame 进行传输，这个合并的过程称为发送方的粘包；接收方在接收到这个 Frame 后需要进行拆包，拆分出多个原来的小的 ByteBuf，这个拆分的过程称为接收方拆包。

l 当一个 Frame 无法放入整数倍个 ByteBuf 时，最后一个 ByteBuf 会会发生拆包。这个ByteBuf 中的一部分入入到了一个 Frame 中，另一部分被放入到了另一个 Frame 中。这个过程就是发送方拆包。但对于将这些 ByteBuf 放入到一个 Frame 的过程，就是发送方粘包；当接收方在接收到两个 Frame 后，对于第一个 Frame 的最后部分，与第二个 Frame 的最前部分会进行合并，这个合并的过程就是接收方粘包。但在将 Frame 中的各个ByteBuf 拆分出来的过程，就是接收方拆包。

## 2.2 发送方拆包

- **需求**

客户端作为发送方，向服务端发送两个大的 ByteBuf 数据包，这两个数据包会被拆分为若干个 Frame 进行发送。这个过程中会发生拆包与粘包。

服务端作为接收方，直接将接收到的 Frame 解码为 String 后进行显示，不对这些 Frame

进行粘包与拆包。

## 2.2.2 创建工程 03-unpacking

复制 02-socket 工程，在其基础上进行修改。

## 2.2.3 定义客户端

- **定义客户端启动类**

由于客户端仅发送数据，不接收服务端数据，所以这里仅需添加 String 的编码器，用于将字符串编码为 ByteBuf，以在 TCP 上进行传输。

## （2） 定义客户端处理器

在 channelActive()中将数据发送了两次。由于客户端不用读取服务器的数据，所以不用重写 channelRead()方法。

为了能够看到拆包效果，message 的值需要设置的很长，取下面的值。

**private** String **message** = **"Netty is a NIO client server framework "** +

**"which enables quick and easy development of network applications "** + **"such as protocol servers and clients. It greatly simplifies and "** + **"streamlines network programming such as TCP and UDP socket server."** + **"'Quick and easy' doesn't mean that a resulting application will "** + **"suffer from a maintainability or a performance issue. Netty has "** + **"this guide and play with Netty.In other words, Netty is an NIO "** + **"framework that enables quick and easy development of network "** +

**"as protocol servers and clients. It greatly simplifies and network "** + **"programming such as TCP and UDP socket server development.'Quick "** + **"not mean that a resulting application will suffer from a maintain"** + **"performance issue. Netty has been designed carefully with the expe "** + **"from the implementation of a lot of protocols such as FTP, SMTP, "** +

**" binary and text-based legacy protocols. As a result, Netty has "** + **"a way to achieve of development, performance, stability, without "** + **"which enables quick and easy development of network applications "** + **"such as protocol servers and clients. It greatly simplifies and "** + **"streamlines network programming such as TCP and UDP socket server."** + **"'Quick and easy' doesn't mean that a resulting application will "** + **"suffer from a maintainability or a performance issue. Netty has "** + **"this guide and play with Netty.In other words, Netty is an NIO "** + **"framework that enables quick and easy development of network "** +

**"as protocol servers and clients. It greatly simplifies and network "** + **"programming such as TCP and UDP socket server development.'Quick "** + **"not mean that a resulting application will suffer from a maintain"** + **"performance issue. Netty has been designed carefully with the expe "** + **"from the implementation of a lot of protocols such as FTP, SMTP, "** + **" binary and text-based legacy protocols. As a result, Netty has "** + **"a way to achieve of development, performance, stability, without "** + **"which enables quick and easy development of network applications "** + **"such as protocol servers and clients. It greatly simplifies and "** + **"streamlines network programming such as TCP and UDP socket server."** + **"'Quick and easy' doesn't mean that a resulting application will "** + **"suffer from a maintainability or a performance issue. Netty has "** + **"this guide and play with Netty.In other words, Netty is an NIO "** + **"framework that enables quick and easy development of network "** +

**"as protocol servers and clients. It greatly simplifies and network "** + **"programming such as TCP and UDP socket server development.'Quick "** + **"not mean that a resulting application will suffer from a maintain"** + **"performance issue. Netty has been designed carefully with the expe "** + **"from the implementation of a lot of protocols such as FTP, SMTP, "** + **" binary and text-based legacy protocols. As a result, Netty has "** + **"a way to achieve of development, performance, stability, without "** + **"which enables quick and easy development of network applications "** + **"such as protocol servers and clients. It greatly simplifies and "** + **"framework that enables quick and easy development of network "** +

**"as protocol servers and clients. It greatly simplifies and network "** + **"programming such as TCP and UDP socket server development.'Quick "** + **"not mean that a resulting application will suffer from a maintain"** + **"performance issue. Netty has been designed carefully with the expe "** + **"from the implementation of a lot of protocols such as FTP, SMTP, "** + **" binary and text-based legacy protocols. As a result, Netty has "** +

**"a way to achieve of development, performance, stability, without "** + **"which enables quick and easy development of network applications "** + **"such as protocol servers and clients. It greatly simplifies and "** + **"framework that enables quick and easy development of network "** +

**"as protocol servers and clients. It greatly simplifies and network "** + **"programming such as TCP and UDP socket server development.'Quick "** + **"not mean that a resulting application will suffer from a maintain"** + **"performance issue. Netty has been designed carefully with the expe "** + **"from the implementation of a lot of protocols such as FTP, SMTP, "** + **" binary and text-based legacy protocols. As a result, Netty has "** + **"a way to achieve of development, performance, stability, without "** + **"which enables quick and easy development of network applications "** + **"such as protocol servers and clients. It greatly simplifies and "** + **"streamlines network programming such as TCP and UDP socket server."** + **"'Quick and easy' doesn't mean that a resulting application will "** + **"suffer from a maintainability or a performance issue. Netty has "** + **"this guide and play with Netty.In other words, Netty is an NIO "** + **"framework that enables quick and easy development of network "** +

**"as protocol servers and clients. It greatly simplifies and network "** + **"programming such as TCP and UDP socket server development.'Quick "** + **"not mean that a resulting application will suffer from a maintain"** + **"performance issue. Netty has been designed carefully with the expe "** + **"from the implementation of a lot of protocols such as FTP, SMTP, "** + **" binary and text-based legacy protocols. As a result, Netty has "** + **"a way to achieve of development, performance, stability, without "** + **"a compromise.====================================================="**;

## 2.2.4 定义服务端

- **定义服务端启动类**

由于服务端仅接收客户端的数据，不发送数据，所以这里仅添加 String 解码器，用于将

ByteBuf 解码为 String。

## （2） 定义服务端处理器

- **发送方粘包**
- **需求**

客户端作为发送方，向服务端发送 100 个小的 ByteBuf 数据包，这 100 个数据包会被合并为若干个 Frame 进行发送。这个过程中会发生粘包与拆包。

服务端作为接收方，直接将接收到的 Frame 解码为 String 后进行显示，不对这些 Frame

进行粘包与拆包。

## 2.3.2 创建工程 03-stickybag

复制 03-unpacking 工程，在其基础上进行修改。

## 2.3.3 定义客户端

- **定义客户端启动类**

启动类没有变化。

## （2） 定义客户端处理器

或者，也可以不使用 String 编码器，使用手工编码。

## 2.3.4 定义服务端

服务端没有变化。

## 2.4 接收方的粘包拆包

为了解决接收方接收到的数据的混乱性，接收方也可以对接收到的 Frame 包进行粘包与拆包。Netty 中已经定义好了很多的接收方粘包拆包解决方案，我们可以直接使用。下面就介绍几个最常用的解决方案。

接收方的粘包拆包实际在做的工作是解码工作。这个解码基本思想是：发送方在发送数据中添加一个分隔标记，并告诉接收方该标记是什么。这样在接收方接收到 Frame 后，其会根据事先约定好的分隔标记，将数据进行拆分或合并，产生相应的 ByteBuf 数据。这个拆分或合并的过程，称为接收方的拆包与粘包。

## 2.5 LineBasedFrameDecoder

基于行的帧解码器，即会按照行分隔符对数据进行拆包粘包，解码出 ByteBuf。

## 2.5.1 创建工程 04-LineBasedFrameDecoder

复制 03-unpacking 工程，在其基础上进行修改。

## 2.5.2 修改客户端处理器

- **修改服务端启动类**
- **DelimiterBasedFrameDecoder**

基于分隔符的帧解码器，即会按照指定分隔符对数据进行拆包粘包，解码出 ByteBuf。

## 2.6.1 创建工程 04-DelimiterBasedFrameDecoder

复制 03-unpacking 工程，在其基础上进行修改。

## 2.6.2 修改客户端处理器

在 message 的字符串中多个位置添加任意的分隔符，这里添加的是“###---###”。

## 2.6.3 修改服务端启动类

- **FixedLengthFrameDecoder**

固定长度帧解码器，即会按照指定的长度对 Frame 中的数据进行拆粘包。

## 2.7.1 创建工程 04-FixedLengthFrameDecoder

复制 03-stickybag 工程，在其基础上进行修改。

## 2.7.2 修改服务端启动类

- **LengthFieldBasedFrameDecoder**

基于长度域的帧解码器，用于对 LengthFieldPrepender 编码器编码后的数据进行解码的。所以，首先要清楚 LengthFieldPrepender 编码器的编码原理。

## 2.8.1 构造器参数

maxFrameLength：要解码的 Frame 的最大长度lengthFieldOffset：长度域的偏移量lengthFieldLength：长度域的长度

lengthAdjustment：要添加到长度域值中的补偿值，长度矫正值。initialBytesToStrip：从解码帧中要剥去的前面字节

## 2.8.2 创建工程 04-LengthFieldBasedFrameDecoder

复制 02-socket 工程，在其基础上进行修改。

## 2.8.3 修改客户端启动类

直接在 02-socket 工程上修改即可。

## 2.8.4 修改服务端启动类

**第\****3***\*章** **Netty** **高级应用**

本章会通过代码实例的方式将Netty 常见应用场景中的知识点进行讲解。

## 3.1 WebSocket 长连接

- **WebSocket** **简介**

WebSocket 是 HTML5 中的协议，是构建在 HTTP 协议之上的一个网络通信协议，其以长连接的方式实现了客户端与服务端的全双工通信。

HTTP/1.1 版本协议中具有 keep-alive 属性，实现的是半双工通信。

## 3.1.2 需求分析

在页面上有两个左右并排的文本域，它们的中间有一个“发送”按钮。在左侧文本域中输入文本内容后，单击发送按钮，会显示到右侧文本域中。

## 3.1.3 定义工程 05-websocket

复制 01-primary 工程，在此基础上进行修改。

## 3.1.4 定义客户端页面

在 src/main 下定义一个目录 webapp。在其中定义 html 页面。

## 3.1.5 定义服务端

- **定义服务端启动类**
- **定义服务端处理器**
- **WebSocket** **握手原理**
- **网络聊天**

该工程是对 socket 编程的一个应用。

## 3.2.1 需求分析

本例要实现一个网络群聊工具。参与聊天的客户端消息是通过服务端进行广播的。

## 3.2.2 创建工程 06-webchat

复制 02-socket 工程，在此基础上进行修改。

## 3.2.3 定义服务端

- **定义服务端启动类**

仅修改如下位置即可。

## （2） 定义服务端处理器

**A\****、 定义** **ChannelGroup**

**B\****、 重写** **channelRead()**

**C\****、 重写** **channel** **激活与钝化方法**

**D\****、重写异常捕获方法**

- **定义客户端**
- **定义客户端启动类**

仅修改如下位置即可。

## （2） 定义客户端处理器

- **读写空闲检测**

当客户端与服务端的连接建立好后，它们之间就可以进行通信了。但是，若某客户端与服务端间长时间没有进行通信，而 Channel 却被长时间占用，就会形成资源浪费。Netty 提供了专门用于进行读写操作空闲检测的处理器可供使用。

## 3.3.1 创建工程 07-idle

复制 06-webchat 工程，在此基础上进行修改。

## 3.3.2 定义服务端

- **定义服务端启动类**

**A\****、 代码**

- **定义服务端处理器**
- **定义客户端**

直接使用“聊天程序”的客户端即可。

## 3.4 手写 Tomcat

确切地说，这里要手写的是一个 Web 容器，一个类似于 Tomcat 的容器，用于处理 HTTP

请求。该 Web 容器没有实现 JavaEE 的 Servlet 规范，不是一个 Servlet 容器。但其是类比着

Tomcat 来写的，这里定义了自己的请求、响应及 Servlet，分别命名为了 NettyRequest， NettyResponse 与 Servnet。

## 3.4.1 Tomcat 具体需求

我们这里要定义一个 Tomcat，这个Web 容器提供给用户后，用户只需要按照使用步骤就可以将其自定义的 Servnet 发布到该 Tomcat 中。我们现在给出用户对于该 Tomcat 的使用步骤：

- 用户只需将自定义的 Servnet 放入到指定的包中。例如，abc.webapp 包中。
- 用户在访问时，需要将自定义的 Servnet 的简单类名全小写后的字符串作为该 Servnet

的 Name 进行访问。

- 若没有指定的 Servnet，则访问默认的 Servnet。

真正的 Tomcat：

第一个 map：key 为指定的 Servlet 的名称，value 为该 Servlet 实例

第二个 map：key 为指定的 Servlet 的名称，value 为该 Servlet 的全限定性类名

## 3.4.2 创建工程 08-tomcat

- **创建工程**

创建一个普通的 Maven 的Java 工程。

## （2） 导入依赖

<**properties**>

<**project.build.sourceEncoding**>UTF-8</**project.build.sourceEncoding**>

<**maven.compiler.source**>1.8</**maven.compiler.source**>

<**maven.compiler.target**>1.8</**maven.compiler.target**>

</**properties**>

<**dependencies**>

_<!--_ *netty-all* *依赖* *-->*

<**dependency**>

<**groupId**>io.netty</**groupId**>

<**artifactId**>netty-all</**artifactId**>

<**version**>4.1.36.Final</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>org.projectlombok</**groupId**>

<**artifactId**>lombok</**artifactId**>

<**version**>1.18.6</**version**>

<**scope**>provided</**scope**>

</**dependency**>

</**dependencies**>

## 3.4.3 定义 Servnet 规范

- **定义请求接口** **NettyRequest**
- **定义响应接口** **NettyResponse**
- **定义\****Servnet** **规范**
- **定义** **Tomcat** **服务器**
- **定义** **DefaultNettyRequest** **类**
- **定义** **DefaultNettyResponse** **类**
- **定义** **DefaultServnet** **类**
- **定义服务器类** **TomcatServer**
- **定义服务器端处理器**
- **定义启动类** **TomcatStarter**
- **定义业务** **Servnet**
- **心跳机制**

所谓心跳, 即在 TCP 长连接中, 客户端和服务器之间定期发送的一种特殊的数据包, 通知对方自己还“活着”, 以确保 TCP 连接的有效性。

## 3.5.1 需求

下面要实现的需求是：Client 端连接到 Server 端后，会循环执行一个定时任务：随机等待几秒，然后 ping 一下 Server 端，即发送一个心跳。当 Server 端在等待了指定时间后没有读取到 Client 端发送的心跳，Server 端会主动断开连接。

## 3.5.2 创建工程 09-heartBeat

复制 07-webchat 工程，在此基础上进行修改。

## 3.5.3 定义服务端

- **定义服务端启动类**
- **定义读操作处理器**
- **定义客户端**
- **定义客户端启动类**

注意，该类中不能关闭 Channel，不能关闭 eventLoopGroup。

## （2） 定义随机发送心跳处理器

- **客户端重连服务端**
- **修改处理器**

在客户端修改心跳处理器。只需要添加如下内容：

## （2） 修改客户端启动类

仅在启动类中添加如下语句即可。

## 3.6 手写 RPC 框架

- **RPC** **简介**

RPC，Remote Procedure Call，远程过程调用，是一种通过网络从远程计算机上请求服务，而不需要了解底层网络技术的协议。在 OSI 网络通信模型中，RPC 跨越了传输层（第四层， 传输协议 TCP/UDP，通过 IP+port 进行通信）和应用层（第七层，传输协议 HTTP、HTTPS、FTP 等）。RPC 使得开发分布式系统应用变得更加容易。

RPC 采用 C/S 模式。请求程序就是 Client，而服务提供程序就是 Server。首先，Client 发送一个带有请求参数的调用请求到 Server，然后等待响应。在 Server 端，进程一直处于睡眠状态直到接收到 Client 的调用请求。当一个调用请求到达，Server 会根据请求参数进行计算，并将计算结果发送给 Client，然后等待下一个调用请求。Client 接收到响应信息，即获取到调用结果，然后根据情况继续发出下一次调用。

## 3.6.2 RPC 框架具体需求

我们这里要定义一个 RPC 框架，这个框架提供给用户后，用户只需要按照使用步骤就可以完成RPC 远程调用。我们现在给出用户对于该 RPC 框架的使用步骤：

- 用户需要将业务接口通知到 Server 与 Client，因为业务接口是服务名称。
  - 用户只需将业务接口的实现类写入到 Server 端的指定包下，那么这个包下的实现类就会被 Server 发布。
- Client 端只需根据业务接口名就可获取到 Server 端发布的服务提供者，然后就可以调用

到远程 Server 端的实现类方法的执行。

## 3.6.3 定义api 工程 10-rpc-api

该 api 工程中用于存放业务接口、常量类、工具类等将来服务端与客户端均会使用到的一个接口与类。

## （1） 创建工程

创建一个普通的 Maven 的Java 工程。

## （2） 导入依赖

仅导入 lombok 依赖即可。

UTF-81.81.8org.projectlomboklombok1.18.6provided " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJrjI+py+0Po5y02ouz3rz7D4biSJYMYKBqsLbs68bwLNf0bef4rvf87wsCh8Ii8WhMIpfKJvPpjEKn0uqQBchqt9yu9wsOi8fksvmMTqvX7Lb7DY/L5/S6/Y7nwvL8vv8PGCg4SFhoeIgYUAAAOw==" v:shapes="_x0000_s1393">

## （3） 定义业务接口

- **定义常量类**
- **定义服务端工程** **10-rpc-server**
- **创建工程**

创建一个普通的 Maven 的Java 工程。

## （2） 导入依赖

<**properties**>

<**project.build.sourceEncoding**>UTF-8</**project.build.sourceEncoding**>

<**maven.compiler.source**>1.8</**maven.compiler.source**>

<**maven.compiler.target**>1.8</**maven.compiler.target**>

</**properties**>

<**dependencies**>

_<!--_ *netty-all* *依赖* *-->*

<**dependency**>

<**groupId**>io.netty</**groupId**>

<**artifactId**>netty-all</**artifactId**>

<**version**>4.1.36.Final</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>com.abc</**groupId**>

<**artifactId**>10-rpc-api</**artifactId**>

<**version**>1.0-SNAPSHOT</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>org.projectlombok</**groupId**>

<**artifactId**>lombok</**artifactId**>

<**version**>1.18.6</**version**>

<**scope**>provided</**scope**>

</**dependency**>

</**dependencies**>

## （3） 定义业务接口实现类

- **定义服务器类**

**A\****、 定义** **publish()**

**B\****、 定义** **getProviderClass()**

**C\****、 定义** **doRegister()**

**D\****、定义** **starter()**

- **定义服务端处理器**
- **定义服务器启动类**
- **定义客户端工程** **10-rpc-client**
- **创建工程**

创建一个普通的 Maven 的Java 工程。

## （2） 导入依赖

<**properties**>

<**project.build.sourceEncoding**>UTF-8</**project.build.sourceEncoding**>

<**maven.compiler.source**>1.8</**maven.compiler.source**>

<**maven.compiler.target**>1.8</**maven.compiler.target**>

</**properties**>

<**dependencies**>

_<!--_ *netty-all* *依赖* *-->*

<**dependency**>

<**groupId**>io.netty</**groupId**>

<**artifactId**>netty-all</**artifactId**>

<**version**>4.1.36.Final</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>com.abc</**groupId**>

<**artifactId**>10-rpc-api</**artifactId**>

<**version**>1.0-SNAPSHOT</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>org.projectlombok</**groupId**>

<**artifactId**>lombok</**artifactId**>

<**version**>1.18.6</**version**>

<**scope**>provided</**scope**>

</**dependency**>

</**dependencies**>

## （3） 定义动态代理类

在客户端创建一个动态代理类，用于动态代理服务端的提供者对象。

## （4） 定义客户端处理器

- **定义消费者类**
- **手写** **Dubbo** **框架**
- **原理**

Dubbo 框架本身就是一个RPC 框架，不同的是，消费者要连接的服务端 IP 与端口号不是硬编码在服务端的，而是从zk 中读取到的。那么 zk 中的这些信息是从哪里来的呢？

一个Dubbo 应用中会存在很多服务提供者与消费者。每个提供者都是一个 Netty Server， 其会对外暴露自己所在主机的 IP 与 Port。每个消费者都是一个 Netty Client，其会通过连接相应主机的 IP 与 Port 来获取相应的服务。

具体的注册步骤：

l 在 zk 中创建一个 Dubbo 的持久根节点，例如/mydubbo

l 在根节点下创建以接口名(服务名称)为名称的持久节点，例如

/mydubbo/com.abc.SomeService

l 在接口名节点下再创建提供者主机节点临时节点，以 IP:port 为名称，例如

/mydubbo/com.abc.SomeService/192.168.59.109:8888

服务提供者的 IP 与 Port 是如何对外暴露的呢？其会为自己所提供的服务起一个服务名称，一般为业务接口名。然后将该服务名称与对应提供者主机的 IP 与 Port 相绑定，注册到zk 中。

服务消费者会从服务注册中心zk 中查找自己所需要的服务名称，一般为业务接口名， 然后获取到该服务名称对应的所有提供者主机信息，并通过负载均衡方式选取一个主机进行连接，获取相应服务。

具体消费过程：

从 zk 的根节点/mydubbo 下查找指定服务名称的子节点

为该服务名称节点添加 watcher 监听，以监听其子节点列表变更

获取该服务名称节点下的所有子节点，即该服务名称的所有提供者主机的 ip:port

通过负载均衡选择一个提供者主机的 ip:port

消费者将其调用信息发送给已经选择好的提供者主机提供者主机将其执行结果返回给消费者

## 3.7.2 新增需求

当客户端通过负载均衡策略选择了某一提供者主机后，我们这里新增了一个需求：提供者主机中提供同一服务名称（接口名）的实现类有多个。这样，消费者可以指定其要调用的实现类。若消费者没有指定要调用的实现类，其会调用到注册到中第一个注册的实现类。

为了实现提供者端业务接口可以有多个实现类供客户端选择，这里要求实现类名必须是一个前辍 prefix 后是业务接口名。这样，消费者在进行消费时，可以通过前辍来指定要调用的是哪个实现类。

## 3.7.3 定义aip 工程 11-dubbo-api

- **创建工程**

复制 10-rpc-api，在其基础上进行修改。

## （2） 修改InvokeMesssage 类

新增如下属性。

## （3） 增加常量类

保持原有的业务接口类及 InvokeMessage 类，再增加一个 zk 相关的常量类。

## 3.7.4 定义服务端工程 11-dubbo-server

- **创建工程**

复制 10-rpc-server 工程，在其基础上进行修改。

## （2） 添加依赖

由于当前工程要作为 zk 的客户端对 zk 进行操作，所以这里导入了 Curator 依赖。

org.apache.curatorcurator-framework2.12.0org.apache.curatorcurator-recipes2.12.0 " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AgMAgAAAAAAAAAJLRIynyesNn4x02oqvznz7Dn5iSI5miZ5qyq5uC79yeQD2jef6zvf+DwwKh8Si8YhMKpfMpvMJjUqn1GpuYc1qt9yu9wsOi8fkcqAAADs=" v:shapes="_x0000_s1423">

## （3） 接口实现类

将原来的实现类删除，添加两个新的实现类。

## （4） 定义注册中心注册规范

# （5） 定义 zk 注册中心实现

- **修改服务器类**

**A\****、 修改** **publish()\****方法**

**B\****、** **getProviderClass()\****未修改**

未发生任何变化。

**C\****、 修改** **doRegister()**

**D\****、修改** **start()**

- **修改服务器启动类**
- **修改服务端处理器**

后面代码未改变。

# 3.7.5 定义客户端工程 11-dubbo-client

- **创建工程**

复制 10-rpc-client 工程，在其基础上进行修改。

# （2） 添加依赖

由于当前工程要作为 zk 的客户端对 zk 进行操作，所以这里新增了 Curator 依赖。

__

<**dependency**>

<**groupId**>org.apache.curator</**groupId**>

<**artifactId**>curator-framework</**artifactId**>

<**version**>2.12.0</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.apache.curator</**groupId**>

<**artifactId**>curator-recipes</**artifactId**>

<**version**>2.12.0</**version**>

</**dependency**>

# （3） 未发生变化的类

复制来的工程所包含的类中，客户端处理器类 RpcClientHandler 未发生变化，可以直接使用。

# （4） 定义负载均衡接口

- **定义随机负载均衡器**
- **定义服务发现规范**
- **定义** **zk** **服务发现实现类**

- **修改消费者类** **RpcConsumer**
- **修改** **RpcProxy**

- **Netty** **与** **Spring Boot** **整合**

这里将前面的手写 Dubbo 框架修改为 Spring Boot 工程。

# 3.8.1 定义服务端工程 11-springboot-dubbo-server

- **创建工程**

复制 11-dubbo-server 工程，在其基础上进行修改。

# （2） 修改pom

该 pom 文件共修改了四处地方：

- 添加了 spring boot 父工程
- 添加了 spring boot starter 依赖
- 添加了 spring boot 的 maven 插件
- 删除了 lombok 依赖的版本号

org.springframework.bootspring-boot-starter-parent2.1.7.RELEASEorg.springframework.bootspring-boot-starterorg.springframework.bootspring-boot-maven-plugin " src="data:image/png;base64,R0lGODlhzQJdAncAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AuEBhwAAAAAAAAgKDQ0KCAMAAAQIDQ0LCwgDAAAECggDAw0LCgQABAoLDQ0IBAoEAwgICAADCAsLDQMGCwgGAwsLCwAABAsGAwoEAAMEBAgEAAQAAAAAAwQAAwMAAwQICgMDCAsLCgMABAoLCgMGCggDBAgKCgMECgQDAwQEBAoGBAQGCggGBAoKCAMGCAQGCAgEBAQDBAgKCwoIBA0LOQ0LJQAANQAAYAAAgAAAlAAApw0kTA05XwA1uQBgqgBgzDkLDSULDTUAACULOTkLJSULJTUAlDUAgDUApzU1hjlMTCVMcDlMcDlegTVgpzWGuTWG3TWGzjWGzF85DUwkDV85OUwkOUw5TExMOUxMX19MTF9ecExMcExegV9wTExwX0xwcExwgV+BX1+BcF+BgWAAAGAAYGAAgHBMJXBMOXBeOXBMTHBeX3BwTHBwcGBggGBglGBgp3CBX3CBcHCBgWCGuWCq3WCq74Y1AIY1NYY1gIFeOYFeTIFwTIGBX4GBcIGBgYaGp4aquYbO3YbOzIbO76pgAKpggKrOuarv3arvzKrv786GNc6GlM7v3c7vzM7v7++qYO+qp+/Ohu/Oue/vqu/vzO/vzu/v3e/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEEEEhwoMGCCA8qTMhwocOGEB9KjEhxosWKGC9qzMhxo8eOID+KDElypMmSKE+qTMlypcuQL2O2nCmzJs2bNnPi3Kmz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" v:shapes="_x0000_s1487">

# （3） 修改SomeServiceImpl

- **修改** **ZKRegistryCenter**
- **修改** **RpcServer**
- **创建\****Spring Boot** **启动类**

在以上类所在包的公共包下创建 Spring Boot 的启动类。

# 3.8.2 定义客户端工程 11-springboot-dubbo-client

- **创建工程**

复制 11-dubbo-client 工程，在其基础上进行修改。

# （2） 修改pom

该 pom 文件共修改了四处地方：

- 添加了 spring boot 父工程
- 添加了 spring boot starter 依赖
- 添加了 spring boot 的 maven 插件
- 删除了 lombok 依赖的版本号

org.springframework.bootspring-boot-starter-parent2.1.7.RELEASEorg.springframework.bootspring-boot-starterorg.springframework.bootspring-boot-maven-plugin " src="data:image/png;base64,R0lGODlhzQJdAncAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AuEBhwAAAAAAAAgKDQ0KCAMAAAQIDQ0LCwgDAAAECggDAw0LCgQABAoLDQ0IBAoEAwgICAADCAsLDQMGCwgGAwsLCwAABAsGAwoEAAMEBAgEAAQAAAAAAwQAAwMAAwQICgMDCAsLCgMABAoLCgMGCggDBAgKCgMECgQDAwQEBAoGBAQGCggGBAoKCAMGCAQGCAgEBAQDBAgKCwoIBA0LOQ0LJQAANQAAYAAAgAAAlAAApw0kTA05XwA1uQBgqgBgzDkLDSULDTUAACULOTkLJSULJTUAlDUAgDUApzU1hjlMTCVMcDlMcDlegTVgpzWGuTWG3TWGzjWGzF85DUwkDV85OUwkOUw5TExMOUxMX19MTF9ecExMcExegV9wTExwX0xwcExwgV+BX1+BcF+BgWAAAGAAYGAAgHBMJXBMOXBeOXBMTHBeX3BwTHBwcGBggGBglGBgp3CBX3CBcHCBgWCGuWCq3WCq74Y1AIY1NYY1gIFeOYFeTIFwTIGBX4GBcIGBgYaGp4aquYbO3YbOzIbO76pgAKpggKrOuarv3arvzKrv786GNc6GlM7v3c7vzM7v7++qYO+qp+/Ohu/Oue/vqu/vzO/vzu/v3e/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEEEEhwoMGCCA8qTMhwocOGEB9KjEhxosWKGC9qzMhxo8eOID+KDElypMmSKE+qTMlypUuQLWO+nCmzJs2bNnPi3Kmz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" v:shapes="_x0000_s1488">

# （3） 修改 RandomLoadBalance

- **修改\****ServiceDiscoveryImpl**
- **修改** **RpcProxy**
- **创建\****Spring Boot** **启动类**

在以上类所在包的公共包下创建 Spring Boot 的启动类。

**第\****4***\*章** **Netty** **源码解析**

# 4.1 Netty 服务端启动

- **创建服务端** **Channel**
- **重要点**

在创建 Channel 时完成了以下几个重要任务：

- 生成了 Channel 的 Id
- 创建了真正的数据传输对象 Unsafe
- 创建了与 Channel 绑定的 ChannelPipeline
- 为 Channel 创建了其配置类 ChannelConfig

# （2） 初始化构造器

- **创建\****Channel**

- **NioServerSocketChannel()\****构造器功能**
- **初始化服务端** **Channel**

**A\****、 设置** **options**

**B\****、 设置** **attr**

**C\****、 添加处理器**

- **将** **Channel** **注册给** **Selector**
- **原理**
- **应用模型**

继续回到 register()方法。

跟踪 register()方法。

# 4.1.4 端口绑定

- **NioEventLoop**
- **NioEventLoop** **的创建**
- **NioEventLoopGroup** **的创建**

继续跟踪。

# （2） 创建 NioEventLoop

跟踪 newChild()方法。

首先跟踪一下第 166 行的 newTaskQueue()。

继续跟踪 SingleThreadEventExecutor()构造器的 apply()方法。

重新返回前面的 apply()方法。

# （3） 定义 NioEventLoop 线程名称

其中 getClass()即 this.getClass()，获取到当前类的 Class，即 EventExecutorGroup 的Class，即 EventLoopGroup 的 Class。

继续跟踪构造器。

# 4.2.2 向 NioEventLoop 添加任务

重新返回 SingleThreadEventExecutor 的execute()方法。

重新返回 SingleThreadEventExecutor 的execute()方法。

# 4.2.3 NioEventLoop 任务的执行

跟踪run()方法。

在 switch()位置添加断点，查看 switch()中的参数运算结果，其值为 0，与下面所有 case

均不匹配。所以直接运行后面的代码。

返回runAllTasks()方法。

# 4.3 Pipeline

- **Pipeline** **的创建**

前面已经分析过了。

# 4.3.2 处理器的添加

第 382-386 行用于判断，若传来的 handlers 是一个数组，只要发现有 null 元素，则直接结束添加，即其只会添加数组中 null 元素之前的所有处理器。

跟踪 addLast()方法，其主要完成了以下几件事情：

# （1） 检查处理器是否被多次添加

- **创建新节点**
- **插入新节点**
- **执行\****handlerAdded()***\*回调**
- **处理器的删除**

重新返回到 DefaultChannelPipeline 的 remove()方法。

重新返回到 DefaultChannelPipeline 的 remove()方法。

# 4.4 Channel 的 inBound 与 outBound 处理器

- **Inbound** **事件的传递** **12-channelInboundHandler**
- **创建工程**

复制 02-socket 工程，在其基础上进行修改。

# （2） 删除类

将其客户端代码全部删除，再将服务端原来自定义的处理器类删除。

# （3） 定义服务端处理器

在这里定义三个处理器。

# （4） 定义服务端启动类

- **Outbound** **事件的传递** **12-channelOutboundHandler**
- **创建工程**

复制 12-channelInboundHandler 工程，在其基础上进行修改。

# （2） 删除类

删除之前的三个 ChannelInboundHandler 类。

# （3） 定义服务端处理器

在这里定义三个处理器。

# （4） 修改服务端启动类