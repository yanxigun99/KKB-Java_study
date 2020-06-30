**VM调优⼯具**

通过上⼀章讲解我们对JVM调优有了整体的认知，接下来我们对在调优过程中所使⽤的⼯具逐⼀介绍。

# 1 jdk命令⾏

## 1.1 jps

jps:Java Virtual Machine Process Status Tool

查看Java进程 ，相当于Linux下的ps命令，只不过它只列出Java进程。

## 1.2 jstat

jstat:JVM Statistics Monitoring Tool

jstat可以查看Java程序运⾏时相关信息，可以通过它查看堆信息的相关情况

#### 示例⼀：

下⾯输出的是GC信息，34784 是 进程ID ，采样时间间隔为250ms，采样数为4。

#### 

示例⼆：

**示例三：**

## 1.3 jinfo

jinfo：Java Conﬁguration Info

jinfo可以⽤来查看正在运⾏的java程序的扩展参数，甚⾄⽀持运⾏时，修改部分参数

#### 示例⼀：

**示例⼆：**

**示例三：**

## 1.4 jmap

jmap:Memory Map

jmap⽤来查看堆内存使⽤状况，⼀般结合jhat使⽤。jmap语法格式如下：

#### 参数：

**示例⼀：\****heap**

命令：jmap -heap pid

描述：显示Java堆详细信息

打印⼀个堆的摘要信息，包括使⽤的GC算法、堆配置信息和各内存区域内存使⽤信息

#### 示例⼆：histo[:live]

命令：jmap -histo:live pid

描述：显示堆中对象的统计信息

其中包括每个Java类、对象数量、内存⼤⼩(单位：字节)、完全限定的类名。打印的虚拟机内部的类名 称将会带有⼀个’*’前缀。如果ja指定了live⼦选项，则只计算活动的对象。

#### 示例三：clstats

命令：jmap -clstats pid

描述：打印类加载器信息

-clstats是-permstat的替代⽅案，在JDK8之前，-permstat⽤来打印类加载器的数据

打印Java堆内存的永久保存区域的类加载器的智能统计信息。对于每个类加载器⽽⾔，它的名称、活跃 度、地址、⽗类加载器、它所加载的类的数量和⼤⼩都会被打印。此外，包含的字符串数量和⼤⼩也会 被打印。

#### 示例四：ﬁnalizerinfo

命令：jmap -ﬁnalizerinfo pid

描述：打印等待终结的对象信息

Number of objects pending for ﬁnalization: 0 说明当前F-QUEUE队列中并没有等待Fializer线程执⾏

ﬁnal

#### 示例五：dump:

命令：jmap -dump:format=b,ﬁle=heapdump.phrof pid

描述：⽣成堆转储快照dump⽂件。

以hprof⼆进制格式转储Java堆到指定ﬁlename的⽂件中。live⼦选项是可选的。如果指定了live⼦选项，堆中只有活动的对象会被转储。想要浏览heap dump，你可以使⽤jhat( Java堆分析⼯具)读取⽣成的⽂件。

这个命令执⾏，JVM会将整个heap的信息dump写⼊到⼀个⽂件，heap如果⽐较⼤的话，就会导致这个 过程⽐较耗时，并且执⾏的过程中为了保证dump的信息是可靠的，所以会暂停应⽤， 线上系统慎⽤。

## 1.5 jhat

jhat:Java Heap Analysis Tool，jhat 命令解析Java堆转储⽂件,并启动⼀个 web server. 然后⽤浏览器来查看/浏览 dump 出来的 heap. jhat 命令⽀持预先设计的查询, ⽐如显示某个类的所有实例. 还⽀持对象查询语⾔(OQL, Object Query Language)。 OQL有点类似SQL,专⻔⽤来查询堆转储。 OQL相关的帮助信息可以在 jhat 命令所提供的服务器⻚⾯最底部. 如果使⽤默认端⼝, 则OQL帮助信息⻚⾯为: http:

//localhost:7000/oqlhelp/ Java⽣成堆转储的⽅式有多种:

使⽤ 选项可以在JVM运⾏时获取 heap dump.

使⽤ 选项通过 HotSpotDiagnosticMXBean 从运⾏时获得堆转储。

在虚拟机启动时如果指定了

**OutOfMemoryError** 时, 会⾃动执⾏堆转储。

选项, 则抛出

使⽤ 命令。

参数:

***options\*** 可选命令⾏参数,请参考下⾯的 [Options](https://github.com/cncounter/translation/blob/master/tiemao_2014/jhat/jhat.md%23Options)

***heap-dump-ﬁle\*** 要查看的⼆进制Java堆转储⽂件( Java binary heap dump ﬁle)。 如果某个转储⽂

件中包含了多份 heap dumps, 可在⽂件名之后加上 的⽅式指定解析哪⼀个 dump, 如:

\##Options

#### -stack false|true

关闭对象分配调⽤栈跟踪(tracking object allocation call stack)。 如果分配位置信息在堆转储中不可⽤. 则必须将此标志设置为 false. 默认值为 true .

#### -refs false|true

关闭对象引⽤跟踪(tracking of references to objects)。 默认值为 true . 默认情况下, 返回的指针是指向其他特定对象的对象,如反向链接或输⼊引⽤(referrers or incoming references), 会统计/计算堆中的所有对象。

#### -port port-number

设置 jhat HTTP server 的端⼝号. 默认值 7000 .

#### -exclude exclude-ﬁle

指定对象查询时需要排除的数据成员列表⽂件(a ﬁle that lists data members that should be excluded

from the reachable objects query)。 例如, 如果⽂件列列出了

某个特定对象 Object o 计算可达的对象列表时, 引⽤路径涉及除。

#### -baseline exclude-ﬁle

, 那么当从

的都会被排

指定⼀个基准堆转储(baseline heap dump)。 在两个 heap dumps 中有相同 object ID 的对象会被标记为不是新的(marked as not being new). 其他对象被标记为新的(new). 在⽐较两个不同的堆转储时很有⽤.

#### -debug int

设置 debug 级别.

#### -version

表示不输出调试信息。 值越⼤则表示输出更详细的 debug 信息.

启动后只显示版本信息就退出

#### -h

显示帮助信息并退出. 同

#### -help

显示帮助信息并退出. 同

#### -J< ﬂag >

因为 jhat 命令实际上会启动⼀个JVM来执⾏, 通过 **-J** 可以在启动JVM时传⼊⼀些启动参数. 例如,

则指定运⾏ jhat 的Java虚拟机使⽤的最⼤堆内存为 512 MB. 如果需要使⽤多个JVM启动参数, 则传⼊多个 -Jxxxxxx.

#### 示例⼀：

利⽤jhat分析刚刚jmap输出的堆⽂件:

这样就启动起来了⼀个简易的HTTP服务，端⼝号是7000，尝试⼀下⽤浏览器访问⼀下它，本地的 可以通过[http://localhost:7000就可以得到这样的⻚⾯：](http://localhost:7000就可以得到这样的⻚⾯：/)

jhat 启动后显示的 html ⻚⾯中包含有:

All classes including platform:显示出堆中所包含的所有的类Show all members of the rootset :从根集能引⽤到的对象

Show instance counts for all classes (including platform/excluding platform):显示平台包括的所有类的实例数量

Show heap histogram:堆实例的分布表Show ﬁnalizer summary:Finalizer 摘要

Execute Object Query Language (OQL) query:执⾏对象查询语句（OQL）

## 1.6 jstack

jstack：Java Stack Trace，jstack是java虚拟机⾃带的⼀种堆栈跟踪⼯具。jstack⽤于⽣成java虚拟机当前时刻的线程快照。线程快照是当前java虚拟机内每⼀条线程正在执⾏的⽅法堆栈的集合，⽣成线 程快照的主要⽬的是定位线程出现⻓时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的⻓ 时间等待等。 线程出现停顿的时候通过jstack来查看各个线程的调⽤堆栈，就可以知道没有响应的线程到底在后台做什么事情，或者等待什么资源。 如果java程序崩溃⽣成core⽂件，jstack⼯具可以⽤来获得core⽂件的java stack和native stack的信息，从⽽可以轻松地知道java程序是如何崩溃和在程序何处发⽣问题。另外，jstack⼯具还可以附属到正在运⾏的java程序中，看到当时运⾏的java程序的java stack和native stack的信息, 如果现在运⾏的java程序呈现hung的状态，jstack是⾮常有⽤的。

在thread dump中，要留意下⾯⼏种状态

死锁，Deadlock（重点关注）

等待资源，Waiting on condition（重点关注）

等待获取监视器，Waiting on monitor entry（重点关注） 阻塞，Blocked（重点关注）

执⾏中，Runnable 暂停，Suspended

对象等待中，Object.wait() 或 TIMED_WAITING

停⽌，Parked

#### 使⽤⽅式

**可选参数说明**

#### 示例⼀：

将指定进程的当前堆栈情况记录到某个⽂件中：

#### 示例⼆：

统计线程数

## 1.7 jconsole

Jconsole:Java Monitoring and Management Console，Java 5引⼊，⼀个内置 Java 性能分析器， 可以从命令⾏或在 GUI shell 中运⾏。您可以轻松地使⽤ JConsole来监控 Java 应⽤程序性能和跟踪Java 中的代码。

#### 如何启动JConsole

如果是从命令⾏启动，使 JDK 在 PATH 上，运⾏ jconsole 即可。

如果从 GUI shell 启动，找到 JDK 安装路径，打开 bin ⽂件夹，双击 jconsole 。

当分析⼯具弹出时（取决于正在运⾏的 Java 版本以及正在运⾏的 Java 程序数量），可能会出现⼀个对话框，要求输⼊⼀个进程的 URL 来连接，也可能列出许多不同的本地 Java 进程（有时包含JConsole 进程本身）来连接。如下图所示：想分析那个程序就双击那个进程。

#### 如何设置JAVA程序运⾏时可以被JConsolse连接分析

本地程序（相对于开启JConsole的计算机），⽆需设置任何参数就可以被本地开启的JConsole连接

（Java SE 6开始⽆需设置，之前还是需要设置运⾏时参数 -Dcom.sun.management.jmxremote ）

#### JConsole如何连接远程机器的JAVA程序

也可以在已经打开的JConsole界⾯操作 连接->新建连接->选择远程进程->输⼊远程主机IP和端⼝号-

> 点击“连接”，如下图：

#### 示例⼀：

进⼊视图后包括这六个标签：

Overview: Displays overview information about the Java VM and monitored values. Memory: 显示内存使⽤信息

Threads: 显示线程使⽤信息

Classes: 显示类装载信息

_VM Summary:_显示java VM信息MBeans: 显示 MBeans.

上图描述有我们需要的信息，同时点击右键可以保存数据到CSV⽂件。

内存⻚签相对于可视化的jstat 命令，⽤于监视受收集器管理的虚拟机内存。

“线程”⻚签的功能相当于可视化的jstack命令，遇到线程停顿时可以使⽤这个⻚签进⾏监控分析。线程⻓ 时间停顿的主要原因主要有： 等待外部资源 （数据库连接、⽹络资源、设备资

源等）、 死循环 、 锁等待 （活锁和死锁）

下⾯2个⽅法分别等待控制台输⼊、线程锁等待演示

## 1.8 hprof

hprof:Heap/CPU Proﬁling Tool 能够展现CPU使⽤率，统计堆内存使⽤情况。

J2SE中提供了⼀个简单的命令⾏⼯具来对java程序的cpu和heap进⾏ proﬁling，叫做HPROF。HPROF实际上是JVM中的⼀个native的库，它会在JVM启动的时候通过命令⾏参数来动态加载，并成为 JVM进程的⼀部分。若要在java进程启动的时候使⽤HPROF，⽤户可以通过各种命令⾏参数类型来使⽤HPROF对java进程的heap或者 （和）cpu进⾏proﬁling的功能。HPROF产⽣的proﬁling数据可以是⼆进制的，也可以是⽂本格式的。这些⽇志可以⽤来跟踪和分析 java进程的性能问题和瓶颈，解决内存使

⽤上不优的地⽅或者程序实现上的不优之处。⼆进制格式的⽇志还可以被JVM中的HAT⼯具来进⾏浏览和分析，⽤ 以观察java进程的heap中各种类型和数据的情况。在J2SE 5.0以后的版本中，HPROF已经被并⼊到⼀个叫做Java Virtual Machine Tool Interface（JVM TI）中。

#### 语法格式如下

**完整的命令选项如下：**

来⼏个官⽅指南上的实例。

CPU Usage Sampling Proﬁling(cpu=samples)的例⼦：

上⾯每隔20毫秒采样CPU消耗信息，堆栈深度为3，⽣成的proﬁle⽂件名称是java.hprof.txt，在当前

⽬录。

CPU Usage Times Proﬁling(cpu=times)的例⼦，它相对于CPU Usage Sampling Proﬁle能够获得更加细粒度的CPU消耗信息，能够细到每个⽅法调⽤的开始和结束，它的实现使⽤了字节码注⼊技术

（BCI）：

Heap Allocation Proﬁling(heap=sites)的例⼦：

Heap Dump(heap=dump)的例⼦，它⽐上⾯的Heap Allocation Proﬁling能⽣成更详细的Heap Dump信息：

虽然在JVM启动参数中加⼊-Xrunprof:heap=sites参数可以⽣成CPU/Heap Proﬁle⽂件，但对JVM性能影响⾮常⼤，不建议在线上服务器环境使⽤。

**示例：**统计⽅法耗时：

⽣成 java.hprof.txt ⽂件

# 2

# Linux

## 2.1 TOP

Linux中的top命令显示系统上正在运⾏的进程。它是系统管理员最重要的⼯具之⼀。被⼴泛⽤于监 视服务器的负载。在本篇中，我们会探索top命令的细节。top命令是⼀个交互命令。在运⾏top的时候 还可以运⾏很多命令。

（译注：不同发⾏版的top命令在各种细节有不同，如果发现不同时，请读你的帮助⼿册和命令内的帮 助。）

top的使⽤⽅式 top [-d number] | top [-bnp]

#### 使⽤⽅式

显示进程信息

显示完整命令

以批处理模式显示程序信息

以累积模式显示程序信息

设置信息更新次数

设置信息更新时间

显示指定的进程信息

显示更新⼗次后退出

使⽤者将不能利⽤交谈式指令来对⾏程下命令

#### 示例⼀：

Top命令输出，默认运⾏时，top命令会显示如下输出：

前⼏⾏⽔平显示了不同系统参数的概括，接下来是进程和它们在列中的属性。

- 系统运⾏时间和平均负载：

top命令的顶部显示与uptime命令相似的输出。这些字段显示：

当前时间

系统已运⾏的时间当前登录⽤户的数量

相应最近5、10和15分钟内的平均负载

可以使⽤’l’命令切换uptime的显示。

- 任务:

第⼆⾏显示的是任务或者进程的总结。进程可以处于不同的状态。这⾥显示了全部进程的数量。除 此之外，还有正在运⾏、睡眠、停⽌、僵⼫进程的数量（僵⼫是⼀种进程的状态）。这些进程概括信息 可以⽤’t’切换显示。

- CPU 状态:

下⼀⾏显示的是CPU状态。 这⾥显示了不同模式下的所占CPU时间的百分⽐。这些不同的CPU时间表示:

us, user： 运⾏(未调整优先级的) ⽤户进程的CPU时间

sy，system: 运⾏内核进程的CPU时间ni，niced：运⾏已调整优先级的⽤户进程的CPU时间wa，IO wait: ⽤于等待IO完成的CPU时间 hi：处理硬件中断的CPU时间

si: 处理软件中断的CPU时间

st：这个虚拟机被hypervisor偷去的CPU时间（译注：如果当前处于⼀个hypervisor下的vm，实 际上hypervisor也是要消耗⼀部分CPU处理时间的）。

可以使⽤’t’命令切换显示。

- 内存使⽤：

接下来两⾏显示内存使⽤率，有点像’free’命令。第⼀⾏是物理内存使⽤，第⼆⾏是虚拟内存使⽤

(交换空间)。

物理内存显示如下:全部可⽤内存、已使⽤内存、空闲内存、缓冲内存。相似地：交换部分显示的 是：全部、已使⽤、空闲和缓冲交换空间。

内存显示可以⽤’m’命令切换。

- 字段/列：

在横向列出的系统属性和状态下⾯，是以列显示的进程。不同的列代表下⾯要解释的不同属性。 默认上，top显示这些关于进程的属性：

PID 进程ID，进程的唯⼀标识符

USER 进程所有者的实际⽤户名。

PR 进程的调度优先级。这个字段的⼀些值是’rt’。这意味这这些进程运⾏在实时态。

NI 进程的nice值（优先级）。越⼩的值意味着越⾼的优先级。

VIRT 进程使⽤的虚拟内存。

RES 驻留内存⼤⼩。驻留内存是任务使⽤的⾮交换物理内存⼤⼩。

SHR 是进程使⽤的共享内存。

S 这个是进程的状态。它有以下不同的值: D – 不可中断的睡眠态。

R – 运⾏态

S – 睡眠态

T – 被跟踪或已停⽌

Z – 僵⼫态

%CPU ⾃从上⼀次更新时到现在任务所使⽤的CPU时间百分⽐。

%MEM 进程使⽤的可⽤物理内存百分⽐。

TIME+ 任务启动后到现在所使⽤的全部CPU时间，精确到百分之⼀秒。

COMMAND 运⾏进程所使⽤的命令。

还有许多在默认情况下不会显示的输出，它们可以显示进程的⻚错误、有效组和组ID和其他更多的信 息。

## 2.2 vmstat

vmstat是Virtual Meomory Statistics（虚拟内存统计）的缩写，可对操作系统的虚拟内存、进程、CPU活动进⾏监控。是对系统的整体情况进⾏统计，不⾜之处是⽆法对某个进程进⾏深⼊分析。怎 样通过vmstat来发现系统中的瓶颈呢？在回答这个问题前，还是让我们回顾⼀下Linux中关于虚拟内存 相关内容。

#### 物理内存和虚拟内存区别

我们知道，直接从物理内存读写数据要⽐从硬盘读写数据要快的多，因此，我们希望所有数据的读 取和写⼊都在内存完成，⽽内存是有限的，这样就引出了物理内存与虚拟内存的概念。

物理内存就是系统硬件提供的内存⼤⼩，是真正的内存，相对于物理内存，在linux下还有⼀个虚拟 内存的概念，虚拟内存就是为了满⾜物理内存的不⾜⽽提出的策略，它是利⽤磁盘空间虚拟出的⼀块逻 辑内存，⽤作虚拟内存的磁盘空间被称为交换空间（**Swap Space**）。

作为物理内存的扩展，linux会在物理内存不⾜时，使⽤交换分区的虚拟内存，更详细的说，就是内 核会将暂时不⽤的内存块信息写到交换空间，这样以来，物理内存得到了释放，这块内存就可以⽤于其 它⽬的，当需要⽤到原始的内容时，这些信息会被重新从交换空间读⼊物理内存。

linux的内存管理采取的是分⻚存取机制，为了保证物理内存能得到充分的利⽤，内核会在适当的时 候将物理内存中不经常使⽤的数据块⾃动交换到虚拟内存中，⽽将经常使⽤的信息保留到物理内存。

要深⼊了解linux内存运⾏机制，需要知道下⾯提到的⼏个⽅⾯：

⾸先，Linux系统会不时的进⾏⻚⾯交换操作，以保持尽可能多的空闲物理内存，即使并没有什么 事情需要内存，Linux也会交换出暂时不⽤的内存⻚⾯。这可以避免等待交换所需的时间。

其次，linux进⾏⻚⾯交换是有条件的，不是所有⻚⾯在不⽤时都交换到虚拟内存，linux内核根 据”最近最经常使⽤“算法，仅仅将⼀些不经常使⽤的⻚⾯⽂件交换到虚拟内存，有时我们会看到这么⼀ 个现象：linux物理内存还有很多，但是交换空间也使⽤了很多。其实，这并不奇怪，例如，⼀个占⽤很

⼤内存的进程运⾏时，需要耗费很多内存资源，此时就会有⼀些不常⽤⻚⾯⽂件被交换到虚拟内存中， 但后来这个占⽤很多内存资源的进程结束并释放了很多内存时，刚才被交换出去的⻚⾯⽂件并不会⾃动 的交换进物理内存，除⾮有这个必要，那么此刻系统物理内存就会空闲很多，同时交换空间也在被使

⽤，就出现了刚才所说的现象了。关于这点，不⽤担⼼什么，只要知道是怎么⼀回事就可以了。

最后，交换空间的⻚⾯在使⽤时会⾸先被交换到物理内存，如果此时没有⾜够的物理内存来容纳这 些⻚⾯，它们⼜会被⻢上交换出去，如此以来，虚拟内存中可能没有⾜够空间来存储这些交换⻚⾯，最 终会导致linux出现假死机、服务异常等问题，linux虽然可以在⼀段时间内⾃⾏恢复，但是恢复后的系统已经基本不可⽤了。

因此，合理规划和设计linux内存的使⽤，是⾮常重要的。

#### 虚拟内存原理

在系统中运⾏的每个进程都需要使⽤到内存，但不是每个进程都需要每时每刻使⽤系统分配的内存 空间。当系统运⾏所需内存超过实际的物理内存，内核会释放某些进程所占⽤但未使⽤的部分或所有物 理内存，将这部分资料存储在磁盘上直到进程下⼀次调⽤，并将释放出的内存提供给有需要的进程使

⽤。

在Linux内存管理中，主要是通过“调⻚Paging”和“交换Swapping”来完成上述的内存调度。调⻚算 法是将内存中最近不常使⽤的⻚⾯换到磁盘上，把活动⻚⾯保留在内存中供进程使⽤。交换技术是将整 个进程，⽽不是部分⻚⾯，全部交换到磁盘上。

分⻚(Page)写⼊磁盘的过程被称作Page-Out，分⻚(Page)从磁盘重新回到内存的过程被称作Page- In。当内核需要⼀个分⻚时，但发现此分⻚不在物理内存中(因为已经被Page-Out了)，此时就发⽣了分

⻚错误（Page Fault）。

当系统内核发现可运⾏内存变少时，就会通过Page-Out来释放⼀部分物理内存。经管Page-Out不 是经常发⽣，但是如果Page-out频繁不断的发⽣，直到当内核管理分⻚的时间超过运⾏程式的时间时， 系统效能会急剧下降。这时的系统已经运⾏⾮常慢或进⼊暂停状态，这种状态亦被称作thrashing(颠 簸)。

#### ⽤法

vmstat [-a] [-n] [-S unit] [delay [ count]] vmstat [-s] [-n] [-S unit]

vmstat [-m] [-n] [delay [ count]]

vmstat [-d] [-n] [delay [ count]]

vmstat [-p disk partition] [-n] [delay [ count]] vmstat [-f]

vmstat [-V]

-a：显示活跃和⾮活跃内存

-f：显示从系统启动⾄今的fork数量 。

-m：显示slabinfo

-n：只在开始时显示⼀次各字段名称。

-s：显示内存相关统计信息及多种系统活动数量。 delay：刷新时间间隔。如果不指定，只显示⼀条结果。 count：刷新次数。如果不指定刷新次数，但指定了刷新时间间隔，这时刷新次数为⽆穷。

-d：显示磁盘相关统计信息。

-p：显示指定磁盘分区统计信息

-S：使⽤指定单位显示。参数有 k 、K 、m 、M ，分别代表1000、1024、1000000、1048576字节

（byte）。默认单位为K（1024 bytes）

-V：显示vmstat版本信息。

#### 示例⼀：

每3秒输出⼀条结果

字段说明：

#### Procs（进程）：

r: 运⾏队列中进程数量，这个值也可以判断是否需要增加CPU。（⻓期⼤于1）

b: 等待IO的进程数量

#### Memory（内存）：

swpd: 使⽤虚拟内存⼤⼩

注意：如果swpd的值不为0，但是SI，SO的值⻓期为0，这种情况不会影响系统性能。

free: 空闲物理内存⼤⼩buﬀ: ⽤作缓冲的内存⼤⼩cache: ⽤作缓存的内存⼤⼩

注意：如果cache的值⼤的时候，说明cache处的⽂件数多，如果频繁访问到的⽂件都能被cache处，那 么磁盘的读IO bi会⾮常⼩。

#### Swap：

si: 每秒从交换区写到内存的⼤⼩，由磁盘调⼊内存

so: 每秒写⼊交换区的内存⼤⼩，由内存调⼊磁盘

注意：内存够⽤的时候，这2个值都是0，如果这2个值⻓期⼤于0时，系统性能会受到影响，磁盘IO和CPU资源都会被消耗。有些朋友看到空闲内存（free）很少的或接近于0时，就认为内存不够⽤了，不能 光看这⼀点，还要结合si和so，如果free很少，但是si和so也很少（⼤多时候是0），那么不⽤担⼼，系 统性能这时不会受到影响的。

**IO：**（现在的Linux版本块的⼤⼩为1kb）

bi: 每秒读取的块数

bo: 每秒写⼊的块数

注意：随机磁盘读写的时候，这2个值越⼤（如超出1024k)，能看到CPU在IO等待的值也会越⼤。

#### 系统：

in: 每秒中断数，包括时钟中断。

cs: 每秒上下⽂切换数。

注意：上⾯2个值越⼤，会看到由内核消耗的CPU时间会越⼤。

**CPU**（以百分⽐表示）：

us: ⽤户进程执⾏时间百分⽐(user time)

注意： us的值⽐较⾼时，说明⽤户进程消耗的CPU时间多，但是如果⻓期超50%的使⽤，那么我们就该考虑优化程序算法或者进⾏加速。

sy: 内核系统进程执⾏时间百分⽐(system time)

注意：sy的值⾼时，说明系统内核消耗的CPU资源多，这并不是良性表现，我们应该检查原因。

wa: IO等待时间百分⽐

注意：wa的值⾼时，说明IO等待⽐较严重，这可能由于磁盘⼤量作随机访问造成，也有可能磁盘出现 瓶颈（块操作）。

#### 备注：

如果r经常⼤于4，id经常少于40，表示cpu的负荷很重。 如果bi，bo⻓期不等于0，表示内存不⾜。

如果disk经常不等于0，且在b中的队列⼤于3，表示io性能不好。

Linux在具有⾼稳定性、可靠性的同时，具有很好的可伸缩性和扩展性，能够针对不同的应⽤和硬件环 境调整，优化出满⾜当前应⽤需要的最佳性能。因此企业在维护Linux系统、进⾏系统调优时，了解系 统性能分析⼯具是⾄关重要的。

#### 示例⼆：

显示活跃和⾮活跃内存

使⽤-a选项显示活跃和⾮活跃内存时，所显示的内容除增加inact和active外，其他显示内容与例⼦1相 同。

#### 字段说明：

**Memory\****（内存）：**

inact: ⾮活跃内存⼤⼩（当使⽤-a选项时显示）

active: 活跃的内存⼤⼩（当使⽤-a选项时显示）

#### 示例三：

显示从系统启动⾄今的fork数量

vmstat -f 【 linux下创建进程的系统调⽤是fork】

**说明\****:** 信息是从/proc/stat中的processes字段⾥取得的

#### 示例四：

查看细信息

vmstat -s 【显示内存相关统计信息及多种系统活动数量】

**说明：**这些vvmstat的分别来⾃于/proc/meminfo,/proc/stat和/proc/vmstat **示例五：**

vmstat -d 【查看磁盘的读写】

**说明：**这些信息主要来⾃于/proc/diskstats. **示例六：**

查看/dev/sda1磁盘的读/写

**vmstat -p /dev/sda1** 【显示指定磁盘分区统计信息】

**说明：**这些信息主要来⾃于/proc/diskstats. reads:来⾃于这个分区的读的次数。

read sectors:来⾃于这个分区的读扇区的次数。

writes:来⾃于这个分区的写的次数。

requested writes:来⾃于这个分区的写请求次数。

## 2.3 iostat

iostat：I/O statistics（输⼊/输出统计）的缩写，iostat⼯具将对系统的磁盘操作活动进⾏监视。它的特点是汇报磁盘活动统计情况，同时也会汇报出CPU使⽤情况。iostat也有⼀个弱点，就是它不能对某个进程进⾏深⼊分析，仅对系统的整体情况进⾏分析。

iostat 安装

#### 示例⼀：

单独执⾏iostat，显示的结果为从系统开机到当前执⾏时刻的统计信息。 以上输出中，包含三部分：

**选项**

**说明**

第⼀⾏

最上⾯指示系统版本、主机名和当前⽇期

avg-cpu

总体cpu使⽤情况统计信息，对于多核cpu，这⾥为所有cpu的平均值

Device

各磁盘设备的IO统计信息

avg-cpu中各列参数含义如下：

**选项**

**说明**

%user

CPU在⽤户态执⾏进程的时间百分⽐。

%nice

CPU在⽤户态模式下，⽤于nice操作，所占⽤CPU总时间的百分⽐

%system

CPU处在内核态执⾏进程的时间百分⽐

%iowait

CPU⽤于等待I/O操作占⽤CPU总时间的百分⽐

%steal

管理程序(hypervisor)为另⼀个虚拟进程提供服务⽽等待虚拟CPU的百分⽐

%idle

CPU空闲时间百分⽐

1. 若 %iowait 的值过⾼，表示硬盘存在I/O瓶颈
2. 若 %idle 的值⾼但系统响应慢时，有可能是CPU等待分配内存，此时应加⼤内存容量
3. 若 %idle 的值持续低于1，则系统的CPU处理能⼒相对较低，表明系统中最需要解决的资源是 CPU

Device中各列参数含义如下：

**选项**

**说明**

Device

设备名称

tps

每秒向磁盘设备请求数据的次数，包括读、写请求，为rtps与wtps的和。出于效率 考虑，每⼀次IO下发后并不是⽴即处理请求，⽽是将请求合并(merge)，这⾥tps指 请求合并后的请求计数。

kB_read/s

每秒从设备（drive expressed）读取的数据量；

kB_wrtn/s

每秒向设备（drive expressed）写⼊的数据量；

kB_read

读取的总数据量；

kB_wrtn

写⼊的总数量数据量；

我们可以使⽤-c选项单独显示avg-cpu部分的结果，使⽤-d选项单独显示Device部分的信息。

#### 定时显示所有信息

**显示指定磁盘信息**

#### 显示tty和Cpu信息

**以\****M***\*为单位显示所有信息**

#### 查看设备使⽤率（%util）、响应时间（await）

**示例⼆：**

以上命令输出Device的信息，采样时间为2秒，采样3次，若不指定采样次数，则iostat会⼀直输出采样 信息，直到按”ctrl+c”退出命令。注意，第1次采样信息与单独执⾏iostat的效果⼀样，为从系统开机到 当前执⾏时刻的统计信息。

#### 示例三

以kB为单位显示读写信息(-k选项)/以mB为单位显示读写信息(-m选项)

我们可以使⽤-k选项，指定iostat的部分输出结果以kB为单位，⽽不是以扇区数为单位：

以上输出中，kB_read/s、kB_wrtn/s、kB_read和kB_wrtn的值均以kB为单位，相⽐以扇区数为单位， 这⾥的值为原值的⼀半(1kB=512bytes*2)

#### 示例四

为显示更详细的io设备统计信息，我们可以使⽤-x选项，在分析io瓶颈时，⼀般都会开启-x选项：

以上各列的含义如下：

**选项**

**说明**

rrqm/s

每秒对该设备的读请求被合并次数，⽂件系统会对读取同块(block)的请求进⾏合并

wrqm/s

每秒对该设备的写请求被合并次数

r/s

每秒完成的读次数

w/s

每秒完成的写次数

rkB/s

每秒读数据量(kB为单位)

wkB/s

每秒写数据量(kB为单位)

avgrq-sz

平均每次IO操作的数据量(扇区数为单位)

avgqu-sz

平均等待处理的IO请求队列⻓度

await

平均每次IO请求等待时间(包括等待时间和处理时间，毫秒为单位)

svctm

平均每次IO请求的处理时间(毫秒为单位)

%util

采⽤周期内⽤于IO操作的时间⽐率，即IO队列⾮空的时间⽐率

#### 示例五

实际查看时，⼀般结合着多个选项查看: 如 iostat -dxm 3

## 2.4 pidstat

pidstat是sysstat⼯具的⼀个命令，⽤于监控全部或指定进程的cpu、内存、线程、设备IO等系统资 源的占⽤情况。pidstat⾸次运⾏时显示⾃系统启动开始的各项统计信息，之后运⾏pidstat将显示⾃上次运⾏该命令以后的统计信息。⽤户可以通过指定统计的次数和时间来获得所需的统计信息。

#### pidstat 安装

pidstat 是sysstat软件套件的⼀部分，sysstat包含很多监控linux系统状态的⼯具，它能够从⼤多数

linux发⾏版的软件源中获得。

在Debian/Ubuntu系统中可以使⽤下⾯的命令来安装: apt-get install sysstat

CentOS/Fedora/RHEL版本的linux中则使⽤下⾯的命令： yum install sysstat

#### ⽤法

常⽤的参数：

-u：默认的参数，显示各个进程的cpu使⽤统计

-r：显示各个进程的内存使⽤统计

-d：显示各个进程的IO使⽤情况

-p：指定进程号

-w：显示每个进程的上下⽂切换情况

-t：显示选择任务的线程的统计信息外的额外信息

-T { TASK | CHILD | ALL }

这个选项指定了pidstat监控的。TASK表示报告独⽴的task，CHILD关键字表示报告进程下所有线 程统计信息。ALL表示报告独⽴的task和task下⾯的所有线程。

注意：task和⼦线程的全局的统计信息和pidstat选项⽆关。这些统计信息不会对应到当前的统计 间隔，这些统计信息只有在⼦线程kill或者完成的时候才会被收集。

-V：版本号

-h：在⼀⾏上显示了所有活动，这样其他程序可以容易解析。

-I：在SMP环境，表示任务的CPU使⽤率/内核数量

-l：显示命令名和所有参数

#### 示例⼀：

查看所有进程的 CPU 使⽤情况（ -u -p ALL）

pidstat 和 pidstat -u -p ALL 是等效的。

pidstat 默认显示了所有进程的cpu使⽤率。

#### 详细说明

PID：进程ID

%usr：进程在⽤户空间占⽤cpu的百分⽐

%system：进程在内核空间占⽤cpu的百分⽐

%guest：进程在虚拟机占⽤cpu的百分⽐

%CPU：进程占⽤cpu的百分⽐CPU：处理进程的cpu编号Command：当前进程对应的命令

#### 示例⼆：

cpu使⽤情况统计(-u)

使⽤-u选项，pidstat将显示各活动进程的cpu使⽤统计，执⾏”pidstat -u”与单独执⾏”pidstat”的效果⼀样。

#### 示例三：

内存使⽤情况统计(-r)

使⽤-r选项，pidstat将显示各活动进程的内存使⽤统计：

PID：进程标识符

Minﬂt/s:任务每秒发⽣的次要错误，不需要从磁盘中加载⻚Majﬂt/s:任务每秒发⽣的主要错误，需要从磁盘中加载⻚VSZ：虚拟地址⼤⼩，虚拟内存的使⽤KB RSS： 常 驻 集 合 ⼤ ⼩ ， ⾮ 交 换 区 五 ⾥ 内 存 使 ⽤ KB Command：task命令名

#### 示例四：

显示各个进程的IO使⽤情况（-d）

报告IO统计显示以下信息：

PID： 进 程 id kB_rd/s：每秒从磁盘读取的KB

kB_wr/s：每秒写⼊磁盘KB

kB_ccwr/s：任务取消的写⼊磁盘的KB。当任务截断脏的pagecache的时候会发⽣。COMMAND:task的命令名

#### 示例五：

显示每个进程的上下⽂切换情况（-w）

PID:进程id

Cswch/s:每秒主动任务上下⽂切换数量Nvcswch/s:每秒被动任务上下⽂切换数量Command:命令名

#### 示例六：

显示选择任务的线程的统计信息外的额外信息 (-t)

TGID:主线程的表示TID:线程id

%usr：进程在⽤户空间占⽤cpu的百分⽐

%system：进程在内核空间占⽤cpu的百分⽐

%guest：进程在虚拟机占⽤cpu的百分⽐

%CPU：进程占⽤cpu的百分⽐CPU：处理进程的cpu编号Command：当前进程对应的命令

示例七：

TASK表示报告独⽴的task。

CHILD关键字表示报告进程下所有线程统计信息。ALL表示报告独⽴的task和task下⾯的所有线程。

注意：task和⼦线程的全局的统计信息和pidstat选项⽆关。这些统计信息不会对应到当前的统计间隔， 这些统计信息只有在⼦线程kill或者完成的时候才会被收集。

PID:进程id

Usr-ms:任务和⼦线程在⽤户级别使⽤的毫秒数。System-ms:任务和⼦线程在系统级别使⽤的毫秒数。

Guest-ms:任务和⼦线程在虚拟机(running a virtual processor)使⽤的毫秒数。Command:命令名

# 3 第三⽅

## 3.1 VisualVM

开发⼤型 Java 应⽤程序的过程中难免遇到内存泄露、性能瓶颈等问题，⽐如⽂件、⽹络、数据库的连接未释放，未优化的算法等。随着应⽤程序的持续运⾏，可能会造成整个系统运⾏效率下降，严重 的则会造成系统崩溃。为了找出程序中隐藏的这些问题，在项⽬开发后期往往会使⽤性能分析⼯具来对 应⽤程序的性能进⾏分析和优化。

VisualVM 是⼀款免费的性能分析⼯具。它通过 jvmstat、JMX、SA（Serviceability Agent）以及Attach API 等多种⽅式从程序运⾏时获得实时数据，从⽽进⾏动态的性能分析。同时，它能⾃动选择更快更轻量级的技术尽量减少性能分析对应⽤程序造成的影响，提⾼性能分析的精度。

#### 性能分析的主要⽅式

- 监视：监视是⼀种⽤来查看应⽤程序运⾏时⾏为的⼀般⽅法。通常会有多个视图（View）分别 实时地显示 CPU 使⽤情况、内存使⽤情况、线程状态以及其他⼀些有⽤的信息，以便⽤户能很快地发现问题的关键所在。
- 转储：性能分析⼯具从内存中获得当前状态数据并存储到⽂件⽤于静态的性能分析。Java 程序是通过在启动 Java 程序时添加适当的条件参数来触发转储操作的。它包括以下三种：

系统转储：JVM ⽣成的本地系统的转储，⼜称作核⼼转储。⼀般的，系统转储数据量⼤，需要平台相关的⼯具去分析，如 Windows 上的 windbg 和 Linux 上的 gdb。

Java 转储：JVM 内部⽣成的格式化后的数据，包括线程信息，类的加载信息以及堆的统计数据。通常也⽤于检测死锁。

堆转储：JVM 将所有对象的堆内容存储到⽂件。

- 快照：应⽤程序启动后，性能分析⼯具开始收集各种运⾏时数据，其中⼀些数据直接显示在监 视视图中，⽽另外⼤部分数据被保存在内部，直到⽤户要求获取快照，基于这些保存的数据的统计 信息才被显示出来。快照包含了应⽤程序在⼀段时间内的执⾏信息，通常有 CPU 快照和内存快照两种类型。

CPU 快照：主要包含了应⽤程序中函数的调⽤关系及运⾏时间，这些信息通常可以在 CPU 快照视图中进⾏查看。

内存快照：主要包含了内存的分配和使⽤情况、载⼊的所有类、存在的对象信息及对象间的引⽤关 系等。这些信息通常可以在内存快照视图中进⾏查看。

- 性能分析：性能分析是通过收集程序运⾏时的执⾏数据来帮助开发⼈员定位程序需要被优化的 部分，从⽽提⾼程序的运⾏速度或是内存使⽤效率，主要有以下三个⽅⾯：

CPU 性能分析：CPU 性能分析的主要⽬的是统计函数的调⽤情况及执⾏时间，或者更简单的情况就是统计应⽤程序的 CPU 使⽤情况。通常有 CPU 监视和 CPU 快照两种⽅式来显示 CPU 性能分析结果。

内存性能分析：内存性能分析的主要⽬的是通过统计内存使⽤情况检测可能存在的内存泄露问题及 确定优化内存使⽤的⽅向。通常有内存监视和内存快照两种⽅式来显示内存性能分析结果。

线程性能分析：线程性能分析主要⽤于在多线程应⽤程序中确定内存的问题所在。⼀般包括线程的 状态变化情况，死锁情况和某个线程在线程⽣命期内状态的分布情况等

- **VisualVM** **安装**

VisualVM 是⼀个性能分析⼯具，⾃从 JDK 6 Update 7 以后已经作为 Oracle JDK 的⼀部分，位于JDK 根⽬录的 bin ⽂件夹下。VisualVM ⾃身要在 JDK6 以上的版本上运⾏，但是它能够监控 JDK1.4 以上版本的应⽤程序。

- JDK8 如下图：
- 官⽹安装：

VisualVM 项⽬的官⽅⽹站⽬前提供英⽂版本和多语⾔⽀持版本下载。多语⾔版本主要⽀持英语、⽇语以及中⽂三种语⾔。如果下载安装多语⾔版本的 VisualVM，安装程序会依据操作系统的当前语⾔环境去安装相应 VisualVM 的语⾔版本。最新 VisualVM 版本主要⽀持的操作系统包括：Microsoft Windows (7, Vista, XP, Server)、Linux、Sun Solaris、Mac OS X、HP-UX 11i。

从 VisualVM 项⽬的官⽅⽹站上下载 VisualVM 安装程序。将 VisualVM 安装程序解压缩到本地系统。

导航⾄ VisualVM 安装⽬录的 bin ⽬录，然后启动 jvisualvm。

#### 安装 Viscd pualVM 上的插件

VisualVM 插件中⼼提供很多插件以供安装向 VisualVM 添加功能。可以通过 VisualVM 应⽤程序安装，或者从 VisualVM 插件中⼼⼿动下载插件，然后离线安装。另外，⽤户还可以通过下载插件分发⽂件 (.nbm ⽂件 ) 安装第三⽅插件为 VisualVM 添加功能。

从 VisualVM 插件中⼼安装插件安装步骤 :

从主菜单中选择“⼯具”>“插件”。

在“可⽤插件”标签中，选中该插件的“安装”复选框。单击“安装”。 逐步完成插件安装程序。

如下图： VisualVM 插件管理器

根据 .nbm ⽂件安装第三⽅插件安装步骤 :

从主菜单中选择“⼯具”>“插件”。

在“已下载”标签中，点击"添加插件"按钮，选择已下载的插件分发⽂件 (.nbm) 并打开。选中打开的插件分发⽂件，并单击"安装"按钮，逐步完成插件安装程序。

### 2.3.1.2 内存分析

VisualVM 通过检测 JVM 中加载的类和对象信息等帮助

我们分析内存使⽤情况，我们可以通过 VisualVM 的监视标签和 Proﬁler 标签对应⽤程序进⾏内存分析。

在监视标签内，我们可以看到实时的应⽤程序内存堆以及永久保留区域的使⽤情况。

#### 内存堆使⽤情况

**永久保留区域使⽤情况**

此外，我们也可以通过 Applications 窗⼝右击应⽤程序节点来启⽤“在出现 OOME 时⽣成堆

Dump”功能，当应⽤程序出现 OutOfMemory 例外时，VisualVM 将⾃动⽣成⼀个堆转储。

开启“在出现 OOME 时⽣成堆”功能

在 Proﬁler 标签，点击“内存”按钮将启动⼀个内存分析会话，等 VisualVM 收集和统计完相关性能数据信息，将会显示在性能分析结果。通过内存性能分析结果，我们可以查看哪些对象占⽤了较多的内 存，存活的时间⽐较⻓等，以便做进⼀步的优化。

此外，我们可以通过性能分析结果下⽅的类名过滤器对分析结果进⾏过滤。 内存分析结果

### 2.3.1.3 CPU分析

VisualVM 能够监控应⽤程序在⼀段时间的 CPU 的使⽤情况，显示 CPU 的使⽤率、⽅法的执⾏效率和频率等相关数据帮助我们发现应⽤程序的性能瓶颈。我们可以通过 VisualVM 的监视标签和Proﬁler 标签对应⽤程序进⾏ CPU 性能分析。

在监视标签内，我们可以查看 CPU 的使⽤率以及垃圾回收活动对性能的影响。过⾼的 CPU 使⽤率可能是由于我们的项⽬中存在低效的代码，可以通过 Proﬁler 标签的 CPU 性能分析功能进⾏详细的分析。如果垃圾回收活动过于频繁，占⽤了较⾼的 CPU 资源，可能是由内存不⾜或者是新⽣代和旧⽣代分配不合理导致的等。

在 Proﬁler 标签，点击“CPU”按钮启动⼀个 CPU 性能分析会话 ,VisualVM 会检测应⽤程序所有的被调⽤的⽅法。当进⼊⼀个⽅法时，线程会发出⼀个“method entry”的事件，当退出⽅法时同样会发出⼀个“method exit”的事件，这些事件都包含了时间戳。然后 VisualVM 会把每个被调⽤⽅法的总的执⾏时间和调⽤的次数按照运⾏时⻓展示出来。

此外，我们也可以通过性能分析结果下⽅的⽅法名过滤器对分析结果进⾏过滤。

CPU 性能分析结果

### 2.3.1.4 线程分析

Java 语⾔能够很好的实现多线程应⽤程序。当我们对⼀个多线程应⽤程序进⾏调试或者开发后期做性能调优的时候，往往需要了解当前程序中所有线程的运⾏状态，是否有死锁、热锁等情况的发⽣，从

⽽分析系统可能存在的问题。

在 VisualVM 的监视标签内，我们可以查看当前应⽤程序中所有活动线程和守护线程的数量等实时信息。

#### 

活跃线程情况

VisualVM 的线程标签提供了三种视图，默认会以时间线的⽅式展现。另外两种视图分别是表视图和详细信息视图。

时间线视图上⽅的⼯具栏提供了缩⼩，放⼤和⾃适应三个按钮，以及⼀个下拉框，我们可以选择将 所有线程、活动线程或者完成的线程显示在视图中。

线程时间线视图

### 2.3.1.5 快照功能

我们可以使⽤ VisualVM 的快照功能⽣成任意个性能分析快照并保存到本地来辅助我们进⾏性能分析。快照为捕获应⽤程序性能分析数据提供了⼀个很便捷的⽅式因为快照⼀旦⽣成可以在任何时候离 线打开和查看，也可以相互传阅。

VisualVM 提供了两种类型的快照：

1、Proﬁler 快照：当有⼀个性能分析会话（内存或者 CPU）正在进⾏时，我们可以通过性能分析结果

⼯具栏的“快照”按钮⽣成 Proﬁler 快照捕获当时的性能分析数据。

2、应⽤程序快照：我们可以右键点击左侧 Applications 窗⼝中应⽤程序节点，选择“应⽤程序快照”为

⽣成⼀个应⽤程序快照。应⽤程序快照会收集某⼀时刻的堆转储，线程转储和 Proﬁler 快照，同时也会捕获 JVM 的⼀些基本信息。

### 2.3.1.6 转储功能

线程转储的⽣成与分析

VisualVM 能够对正在运⾏的本地应⽤程序⽣成线程转储，把活动线程的堆栈踪迹打印出来，

线程标签及线程转储功能

当 VisualVM 统计完应⽤程序内线程的相关数据，会把这些信息显示新的线程转储标签。线程转储结果

#### 堆转储的⽣成与分析

VisualVM 能够⽣成堆转储，统计某⼀特定时刻 JVM 中的对象信息，帮助我们分析对象的引⽤关系、是否有内存泄漏情况的发⽣等。

监视标签及堆转储功能

当 VisualVM 统计完堆内对象数据后，会把堆转储信息显示在新的堆转储标签内，我们可以看到摘要、类、实例数等信息以及通过 OQL 控制台执⾏查询语句功能。

堆转储的摘要包括转储的⽂件⼤⼩、路径等基本信息，运⾏的系统环境信息，也可以显示所有的线 程信息。

堆转储的摘要视图

通过实例数视图可以获得每个实例内部各成员变量的值以及该实例被引⽤的位置。⾸先需要在类视 图选择需要查看实例的类。

实例数视图

线程转储和堆转储均可以另存成⽂件，以便进⾏离线分析。 转储⽂件的导出

#### 总结

本节⾸先简要列举了⼀些性能分析相关的背景知识。然后介绍了 VisualVM 的下载和安装。最后从

内存性能、CPU 性能、线程分析、快照功能以及转储功能五个⽅⾯展开，进⼀步说明了如何使⽤

VisualVM 进⾏性能分析。

## 3.2 MAT

### 3.2.1 介绍

MAT是⼀个强⼤的内存分析⼯具，可以快捷、有效地帮助我们找到内存泄露，减少内存消耗分析⼯ 具，下⽂将进⾏讲解。

MAT是Memory Analyzer tool的缩写，是⼀种快速，功能丰富的Java堆分析⼯具，能帮助你查找内存泄漏和减少内存消耗。很多情况下，我们需要处理测试提供的hprof⽂件，分析内存相关问题，那么MAT也绝对是不⼆之选。

使⽤MAT，可以轻松实现以下功能：

找到最⼤的对象，因为MAT提供显示合理的累积⼤⼩（retained size）

探索对象图，包括inbound和outbound引⽤，即引⽤此对象的和此对象引出的 查找⽆法回收的对象，可以计算从垃圾收集器根到相关对象的路径

找到内存浪费，⽐如冗余的String对象，空集合对象。

### 3.2.2 安装

MAT安装有两种⽅式，⼀种是以eclipse插件⽅式安装，⼀种是独⽴安装。在MAT的官⽅⽂档中有相 应的安装⽂件下载，下载地址为：https://www.eclipse.org/mat/downloads.php

[若使⽤eclipse插件安装，help -> install new soft点击ADD，在弹出框中添加插件地址：http://do wnload.eclipse.org/mat/1.9.0/update-site/，也可以直接在下载⻚⾯下载离线插件包，以离线⽅](http://download.eclipse.org/mat/1.9.0/update-site/) 式安装。

独⽴安装，选择如下的版本，进⾏安装。

### 2.3.2.3 MAT相关概念说明

#### 1 内存泄漏与内存溢出

内存泄露：对象已经没⽤了（不被任何程序逻辑所需要），还存在被根元素引⽤的情况，⽆法通过 垃圾收集器进⾏⾃动回收，需要通过找出泄漏的代码位置和原因，才好确定解决⽅案；

内存溢出：内存中的对象都还存活着，JVM的堆分配空间不⾜，需要检查堆设置⼤⼩（-Xmx与- Xms），代码是否存在对象⽣命周期太⻓、持有状态时间过⻓的情况。

#### 2 引⽤（强引⽤，软引⽤，弱引⽤，虚引⽤）

Strong Ref(强引⽤)：强可达性的引⽤，对象保存在内存中，只有去掉强可达，对象才被回收，通常我们编写的代码都是Strong Ref。

Soft Ref(软引⽤)：对应软可达性，只要有⾜够的内存，就⼀直保持对象，直到发现内存吃紧且没有Strong Ref时才回收对象。⼀般可⽤来实现缓存，通过java.lang.ref.SoftReference类实现。Weak Ref(弱引⽤)：⽐Soft Ref更弱，当发现不存在Strong Ref时，⽴刻回收对象⽽不必等到内存吃紧的时候。通过java.lang.ref.WeakReference和java.util.WeakHashMap类实现。

Phantom Ref(虚引⽤)：根本不会在内存中保持任何对象，你只能使⽤Phantom Ref本身。⼀般⽤于在进⼊ﬁnalize()⽅法后进⾏特殊的清理过程，通过 java.lang.ref.PhantomReference实现。

#### 3 shallow heap及retained heap

shallow heap：对象本身占⽤内存的⼤⼩，也就是对象头加成员变量（不是成员变量的值）的总和，如⼀个引⽤占⽤32或64bit，⼀个integer占4bytes，Long占8bytes等。如简单的⼀个类⾥⾯只有⼀个成员变量int i，那么这个类的shallo size是12字节，因为对象头是8字节，成员变量int是4 字节。常规对象（⾮数组）的Shallow size有其成员变量的数量和类型决定，数组的shallow size 有数组元素的类型（对象类型、基本类型）和数组⻓度决定。

retained heap：如果⼀个对象被释放掉，那会因为该对象的释放⽽减少引⽤进⽽被释放的所有的对象（包括被递归释放的）所占⽤的heap⼤⼩，即对象X被垃圾回收器回收后能被GC从内存中移除的所有对象之和。相对于shallow heap，Retained heap可以更精确的反映⼀个对象实际占⽤的

⼤⼩（若该对象释放，retained heap都可以被释放）。

#### 4 outgoing references与incoming references

outgoing references ：表示该对象的出节点（被该对象引⽤的对象）。incoming references ：表示该对象的⼊节点（引⽤到该对象的对象）。**5 Dominator Tree**

将对象树转换成Dominator Tree能帮助我们快速的发现占⽤内存最⼤的块，也能帮我们分析对象之间的依赖关系。Dominator Tree有以下⼏个定义：

对象X Dominator（⽀配）对象Y，当且仅当在对象树中所有到达Y的路径都必须经过X

对象Y的直接Dominator，是指在对象树中距离Y最近的Dominator

Dominator tree利⽤对象树构建出来。在Dominator tree中每⼀个对象都是他的直接Dominator

的⼦节点。

对象树和Dominator tree的对应关系如下:

如上图，因为A和B都引⽤到C，所以A释放时，C内存不会被释放。所以这块内存不会被计算到A或 者B的Retained Heap中，因此，对象树在转换成Dominator tree时，会A、B、C三个是平级的。

#### 6 Garbage Collection Roots（GC root）

在执⾏GC时，是通过对象可达性来判断是否回收对象的，⼀个对象是否可达，也就是看这个对象的引⽤连是否和GC Root相连。⼀个GC root指的是可以从堆外部访问的对象，有以下原因可以使⼀个对象成为GC root对象。

System Class: 通过bootstrap/system类加载器加载的类，如rt.jar中的java.util.* JNI Local: JNI⽅法中的变量或者⽅法形参

JNI Global：JNI⽅法中的全局变量

Thread Block：线程⾥⾯的变量，⼀个活着的线程⾥⾯的对象肯定不能被回收Thread：处于激活状态的线程

Busy Monitor：调⽤了wait()、notify()⽅法，或者是同步对象，例如调⽤synchronized(Object)

或者进⼊⼀synchronized⽅法后的当前对象

Java Local：本地变量，例如⽅法的输⼊参数或者是⽅法内部创建的仍在线程堆栈⾥⾯的对象Native Stack：Java ⽅法中的变量或者⽅法形参.

Finalizable：等待运⾏ﬁnalizer的对象

Unﬁnalized：有ﬁnalize⽅法，但未进⾏ﬁnalized，且不在ﬁnalizer队列的对象。Unreachable：通过其它root都不可达的对象，MAT会把它标记为root以便于分析回收。Java Stack Frame：java栈帧

### 2.3.2.4 MAT⼯具使⽤

#### 1、示例代码

list=new ArrayList<>(); int i=1; while (true){ User u = new User(); u.setAge(i); u.setName("name is "+i); i++; list.add(u); } } } ' src="data:image/png;base64,R0lGODlhCgMEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgBsAgIAgQAAAPn5+fj4+AECAwIjjI6py+0Po5y02ouz3rz7D4biSJbm2QHoyrbuC8fyTNf23RYAOw==" v:shapes="_x0000_s1044">

**2\****、⽣成***\*hprof\****⽂件**

#### 3、Leak Suspects⾃动分析泄漏

当发⽣OOM获取到heapdump.hprof⽂件或者⼿动dump出⽂件后，使⽤MAT打开⽂件。打开后默 认会提示是否进⾏内存泄漏检测报告（如果打开Dump时跳过了的话，也可以从其它⼊⼝进⼊⼯具栏上 的 Run Expect System Test -> Leak Suspects），如下图所示：

选择是后进⼊报告内容，此报告内容会帮我们分析的可能有内存泄露嫌疑的地⽅，它会把累积内存 占⽤较⼤的通过饼状图显示出来。如下图所示：

如上图，报告已指出main占⽤了89.30%的内存，⽽这些内存都在Object[]这个数组中。因此， 很⼤⼀种可能是这个对象中的数组数量过⼤。点击Details 可以查看更详细的内容：

在此详细⽽，Shortest Paths To the Accumulation Point可以显示出到GC roots的最短路径，由此可以分析是由于和哪个GC root相连导致当前Retained Heap占⽤相当⼤的对象⽆法被回收，本示例 中，GC root是线程的本地变量(java local)。Accumulated Objects in Dominator Tree以Dominator Tree为视图，可以⽅便的看出受当前对象“⽀配”的对象中哪个占⽤Retained Heap⽐较⼤。图中可看出对象都在Object数组，数组下是User对象。此可以知道问题出在哪⾥了。需要针对这个位置，查看代码，找出导致这个数组存储的User过量的原因。

注：在原始堆转储⽂件的⽬录下， MAT 已经将报告的内容压缩打包到⼀个zip⽂件，命名

为“xxx_Leak_Suspects.zip”，整个报告是⼀个HTML格式的⽂件，可以⽤浏览器直接打开查看，可以⽅ 便进⾏报告分发、分享。

分析Actions, 它包含了4个部分：

**视图**

**含义**

Histogram

列举内存中对象存在的个数和⼤⼩

Dominator Tree

该视图会以占⽤总内存的百分⽐来列举所有实例对象，注意这个地⽅是对象⽽不 是类了，这个视图是⽤来发现⼤内存对象的

Top Consumers

该视图会显示可能的内存泄漏点

Duplicate Classes

该视图显示重复的类等信息

点击他们能得到不同的视图，下⾯来⼀⼀介绍：

#### 4、histogram视图查看对象个数与⼤⼩

点击Overview⻚⾯Actions区域内的“Histogram视图”或点击⼯具栏的“histogram”按钮，可以显示 直⽅图列表，它以Class类的维度展示每个Class类的实例存在的个数、 占⽤的Shallow heap 和Retained内存⼤⼩，可以分别排序显示。如下图所示：

Shallow Heap与Retained Heap的概念前⾯已经讲过。

对于java的对象，其成员基本都是引⽤。真正的内存都在堆上，看起来是⼀堆原⽣的byte[], char[], int[]，因此如果只看对象本身的内存，数量都很⼩，⽽多数情况下，在Histogram视图看到实例对象数 量⽐较多的类都是⼀些基础类型（通常都排在前⾯），如char[]、String、byte[]，所以仅从这些是⽆法 判断出具体导致内存泄露的类或者⽅法的。从上图中，可以直接看到User对象的数量很多，有时不容易 看出来的，可以使⽤⼯具栏中的group result by 可以以super class,class loader等排序，需要特别注意

⾃定义的classLoader，如下图：

通常，如果Histogram视图展示的数量多的实例对象不是基础类型，⽽是⽤户⾃定义的类或者有嫌 疑的类，就要重点关注查看。想进⼀步分析的，可以右键，选择使⽤ List objects 或

Shortest Paths to GC roots 等功能继续钻取数据。其中 list objects 分别有outgoing references 及 incoming references ，可以找出由这个对象出去的引⽤及通过哪些引⽤到这个对象的。 Merge Shortest Paths to GC roots 可以排除掉所有不是强引⽤的，找到这个对象的到GC root 的引⽤路径。最终⽬的就是找到占⽤内存最⼤对象和⽆法回收的对象，计算从垃圾收集器根到相关对象的路径，从⽽根据这个对象路径去检查代码，找出问题所在。

#### 5、Dominator Tree视图

点击Overview⻚⾯Actions区域内的“Dominator Tree视图”或点击⼯具栏的“open Dominator Tree”按钮，可以进⼊ Dominator Tree视图。该视图以实例对象的维度展示当前堆内存中Retained Heap占⽤最⼤的对象，以及依赖这些对象存活的对象的树状结构。如下图：

视图展示了实例对象名、 Shallow Heap ⼤⼩、 Retained Heap ⼤⼩、以及当前对象的Retained Heap 在整个堆中的占⽐。该图是树状结构的，当上⼀级的对象被回收，那么，它引⽤的⼦对象都会被回收，这也是Dominator 的意义，当⽗节点的回收会导致⼦节点也被回收。通过此视图， 可以很⽅便的找出占⽤ Retained Heap 内存最多的⼏个对象，并表示出某些objects的是因为哪些objects的原因⽽存活。本示例中，可以看出是由于Object[] s引⽤的数组存储过量的User 对象。

#### 6、线程视图查看线程栈运⾏情况

点击⼯具栏的“⻮轮”按钮，可以打开Thread Overview视图，可以查看线程的栈帧信息，包括线程对象/线程栈信息、线程名、Shallow Heap、Retained Heap、类加载器、是否Daemon线程等信息。结合内存Dump分析，看到线程栈帧中的本地变量，在左下⽅的对象属性区域还能看到本地变量的属性值。如按上⾯的示例（shortest paths to GC root），知道是由于线程Thread-12是GC-root占⽤内存

⼤，在线程视图中，就可以着重看它的属性情况，如下图：

由上图可以看到此线程调⽤WithOOM ⽅法，使⽤了变量User，⽽变量使⽤了 userList ，它包含了⼤量的User 对象，占⽤ retained heap 很⼤。

### 2.3.2.5 总结

对于java应⽤的内存分析，需要对java应⽤的内存进⾏dump操作，⽣成内存快照后，使⽤MAT 进

⾏分析，找出⼤对象，找到内存泄漏或溢出的地⽅，从⽽分析代码，解决问题。本⽂对MAT 的使⽤场景，基本概念，安装、使⽤进⾏了详细介绍，⼤家可以⾃⼰安装⼀下，写个⼩示例或者拿本⽂中的示例，实践⼀下。通过本⽂，希望可以帮助⼤家更⽅便，更有效率地分析内存，解决java应⽤的内存故障 问题。

## 3.3 GCViewer

GCViewer是⼀款开源的GC⽇志分析⼯具。项⽬的 GitHub 主⻚对各个指标提供了完整的描述信息需要安装jdk才能使⽤。https://github.com/chewiebug/GCViewer

下载https://github.com/chewiebug/GCViewer/releases，

借助GCViewer⽇志分析⼯具，可以⾮常直观地分析出待调优点。可从以下⼏⽅⾯来分析：

Memory,分析Totalheap、Tenuredheap、Youngheap内存占⽤率及其他指标，理论上内存占⽤率 越⼩越好；

Pause，分析Gc pause、Fullgc pause、Total pause三个⼤项中各指标，理论上GC次数越少越好，GC时⻓越⼩越好；

#### GC⽇志

some support for OpenJDK 9 / 10 uniﬁed logging format -Xlog:gc:, the following conﬁgurations will work

-Xlog:gc:ﬁle="path-to-ﬁle" (uses defaults)

-Xlog:gc=info:ﬁle="path-to-ﬁle":tags,uptime,level (minimum conﬁguration needed)

-Xlog:gc*=trace:ﬁle="path-to-ﬁle":tags,time,uptime,level (maximum conﬁguration supported, additional tags ok, but ignored; additional decorations will break parsing)

Oracle JDK 1.8 -Xloggc: [-XX:+PrintGCDetails] [-XX:+PrintGCDateStamps]

Sun / Oracle JDK 1.7 with option -Xloggc: [-XX:+PrintGCDetails] [-XX:+PrintGCDateStamps]

#### ⽤法

顶部的⿊⾊线都代表Full GC，也可以理解为Major GC，是根据⽇志中的CMS GC统计的；底部灰⾊线代表的是Minor GC。

#### 结果分析

**Chart**

GCViewer shows a number of lines etc. in a chart (ﬁrst tab). These are: Full GC Lines:

Black vertical line at every Full GC

Inc GC Lines:

Cyan vertical line at every Incremental GC GC Times Line:

Green line that shows the length of all GCs GC Times Rectangles:

black rectangle at every Full GC

blue rectangle at every inital mark event orange rectangle at every remark event

red rectangle at every vm operation event ("application stopped...") Grey rectangle at every 'normal' GC

Light grey rectangle at every Incremental GC Total Heap:

Red line that shows heap size Tenured Generation:

Magenta area that shows the size of the tenured generation (not available without PrintGCDetails)

Young Generation:

Orange area that shows the size of the young generation (not available without PrintGCDetails)

Used Heap:

Blue line that shows used heap size Initial mark level:

Yellow line that shows the heap usage at "initial-mark" event (only available when the gc algorithm uses concurrent collections, which is the case for CMS and G1)

Concurrent collections

Cyan vertical line for every begin (concurrent-mark-start) and pink vertical line for every end (CMS-concurrent-reset / G1: concurrent-cleanup-end) of a concurrent collection cycle

#### Summary

Footprint:

Maximal amount of memory allocated Max heap after conc GC:

Max used heap after concurrent gc.

Max tenured after conc GC:

Max used tenured heap after concurrent gc (followed by % of max tenured / % of max total heap).

Max heap after full GC:

Max used heap after full gc. Indicates max live object size and can help to determine heap size.

Freed Memory:

Total amount of memory that has been freed Freed Mem/Min:

Amount of memory that has been freed per minute Total Time:

Time data was collected for (only if timestamp was present in log) Acc Pauses:

Sum of all pauses due to GC Throughput:

Time percentage the application was NOT busy with GC Full GC Performance:

Performance of full collections. Note that all collections that include a collection of the tenured generation or are marked with "Full GC" are considered Full GC.

GC Performance:

Performance of minor collections. These are collections that are not full according to the deﬁnition above.

#### Memory

Total heap (usage / alloc max):

Max memory usage / allocation in total heap (the last is the same as "footprint" in Summary)

Tenured heap (usage / alloc max):

Max memory usage / allocation in tenured space Young heap (usage / alloc max):

Max memory usage / allocation in young space Perm heap (usage / alloc max):

Max memory usage / allocation in perm space Max tenured after conc GC:

see in "summary" section Avg tenured after conc GC:

average size of tenured heap after concurrent collection Max heap after conc GC:

see in "summary" section Avg heap after conc GC:

average size of concurrent heap after concurrent collection Max heap after full GC:

see in "summary" section Avg after full GC:

The average heap memory consumption after a full collection Avg after GC:

The average heap memory consumption after a minor collection Freed Memory:

Total amount of memory that has been freed Freed by full GC:

Amount of memory that has been freed by full collections Freed by GC:

Amount of memory that has been freed by minor collections Avg freed full GC:

Average amount of memory that has been freed by full collections Avg freed GC:

Average amount of memory that has been freed by minor collections Avg rel inc after FGC:

Average relative increase in memory consumption between full collections. This is the average diﬀerence between the memory consumption after a full collection to the memory consumption after the next full collection.

Avg rel inc after GC:

Average relative increase in memory consumption between minor collections. This is the average diﬀerence between the memory consumption after a minor collection to the memory consumption after the next minor collection. This can be used as an indicator for the amount of memory that survives minor collections and has to be

moved to the survivor spaces or the tenured generation. This value added to "Avg freed GC" gives you an idea about the size of the young generation in case you don't have PrintGCDetails turned on.

Slope full GC:

Slope of the regression line for the memory consumption after full collections. This can be used as an indicator for the increase in indispensable memory consumption (base footprint) of an application over time.

Slope GC:

Average of the slope of the regression lines for the memory consumption after minor collections in between full collections. That is, if you have two full collections and many minor collections in between, GCViewer will calculate the slope for the minor collections up to the ﬁrst full collection, then the slope of the minor collections between the ﬁrst and the second full collection. Then it will compute a weighted average (each slope wil be weighted with the number of measuring points it was computed with).

initiatingOccFraction (avg / max)

CMS GC kicks in before tenured generation is ﬁlled. InitiatingOccupancyFraction tells you the avg / max usage in % of the tenured generation, when CMS GC started (initial mark). This value can be set manually using -XX:CMSInitiatingOccupancyFraction=.

avg promotion

Promotion means the size of objects that are promoted from young to tenured generation during a young generation collection. Avg promotion shows the average amount of memory that is promoted from young to tenured with each young collection (only available with PrintGCDetails)

total promotion

Total promotion shows the total amount of memory that is promoted from young to tenured with all young collections in a ﬁle (only available with PrintGCDetails)

#### Pause

Acc Pauses:

Sum of all pauses due to any kind of GC Number of Pauses:

Count of all pauses due to any kind of GC Avg Pause:

Average length of a GC pause of any kind Min / max Pause:

Shortest /longest pause of any kind Avg pause interval:

avg interval between two pauses of any kind Min / max pause interval:

Min / max interval between two pauses of any kind Acc full GC:

Sum of all pauses due to full collections Number of full GC pauses:

Count of all pauses due to full collections Acc GC:

Sum of all full GC pauses Avg full GC:

Average length of a full GC pause Min / max full GC pause:

Shortest / longest full GC pause Min / max full GC pause interval:

Min / max interval between two pauses due to full collections Acc GC:

Sum of all pauses due to minor collections Number of GC pauses:

Count of all pauses due to minor collections Avg GC:

Average length of a minor collection pause Min / max GC pause:

Shortest / longest minor GC pause

## 3.4 Arthas

[Arthas是Alibaba开源的Java诊断⼯具，深受开发者喜爱。⽤户⽂档：https://alibaba.github.io/art](https://alibaba.github.io/arthas/)

[has/](https://alibaba.github.io/arthas/)

当你遇到以下类似问题⽽束⼿⽆策时， Arthas 可以帮助你解决：

1. 这个类从哪个 jar 包加载的？为什么会报各种类相关的 Exception？
2. 我改的代码为什么没有执⾏到？难道是我没 commit？分⽀搞错了？
3. 遇到问题⽆法在线上 debug，难道只能通过加⽇志再重新发布吗？
4. 线上遇到某个⽤户的数据处理有问题，但线上同样⽆法 debug，线下⽆法重现！
5. 是否有⼀个全局视⻆来查看系统的运⾏状况？
6. 有什么办法可以监控到JVM的实时运⾏状态？
7. 怎么快速定位应⽤的热点，⽣成⽕焰图？

Arthas⽀持JDK 6+，⽀持Linux/Mac/Winodws，采⽤命令⾏交互模式，同时提供丰富的

⾃动补全功能，进⼀步⽅便进⾏问题的定位和诊断。

#### 启动Demo

arthas-demo 是⼀个简单的程序，每隔⼀秒⽣成⼀个随机数，再执⾏质因数分解，并打印出分解结果。

下载arthas-boot.jar ，然后⽤ java -jar 的⽅式启动：

打印帮助信息：

#### 启动arthas

选择应⽤java进程：

Demo进程是第2个，则输⼊2，再输⼊ 回⻋/enter 。Arthas会attach到⽬标进程上，并输出⽇志：

查看dashboard

输⼊[dashboard](https://alibaba.github.io/arthas/dashboard.html)，按回⻋/enter ，会展示当前进程的信息，按ctrl+c 可以中断执⾏。

$ dashboard

ID NAME GROUP PRIORI STATE %CPU TIME INTERRU DAEMON

17

false

pool-2-thread-1

false

system

5

WAITIN

67

0:0

27

Timer-for-arthas-dashb

system

10

RUNNAB

32

0:0

false

true

11

AsyncAppender-Worker-a

system

9

WAITIN

0

0:0

false

true

9

Attach Listener

system

9

RUNNAB

0

0:0

false

true

3

Finalizer

system

8

WAITIN

0

0:0

false

true

2

Reference Handler

system

10

WAITIN

0

0:0

false

true

4

Signal Dispatcher

system

9

RUNNAB

0

0:0

false

true

26

as-command-execute-dae

system

10

TIMED_

0

0:0

false

true

13

job-timeout

system

9

TIMED_

0

0:0

false

true

1

main

main

5

TIMED_

0

0:0

false

false

14

nioEventLoopGroup-2-1

system

10

RUNNAB

0

0:0

false

false

18

nioEventLoopGroup-2-2

system

10

RUNNAB

0

0:0

false

false

23

nioEventLoopGroup-2-3

system

10

RUNNAB

0

0:0

false

false

15

nioEventLoopGroup-3-1

system

10

RUNNAB

0

0:0

false

false

Memory

used

total

max

usage

GC

heap

32M

155M

1820M

1.77%

gc.ps_scavenge.count

4

ps_eden_space

14M

65M

672M

2.21%

gc.ps_scavenge.time(m

166

ps_survivor_space

4M

5M

5M

s)

ps_old_gen

12M

85M

1365M

0.91%

gc.ps_marksweep.count

0

nonheap

20M

23M

-1

gc.ps_marksweep.time(

0

code_cache

3M

5M

240M

1.32%

ms)

Runtime

os.name

Mac OS X

os.version 10.13.4

java.version 1.8.0_162

java.home /Library/Java/JavaVir tualMachines/jdk1.8.0

_162.jdk/Contents/Hom e/jre

通过thread命令来获取到arthas-demo进程的Main Class

thread 1 会打印线程ID 1的栈，通常是main函数的线程。

通过jad来反编译Main Class

$ jad demo.MathGame

ClassLoader:

+-sun.misc.Launcher$AppClassLoader@3d4eac69

+-sun.misc.Launcher$ExtClassLoader@66350f69

Location:

/tmp/arthas-demo.jar

/*

\* Decompiled with CFR 0_132.

*/

package demo;

import java.io.PrintStream; import java.util.ArrayList; import java.util.Iterator; import java.util.List; import java.util.Random;

import java.util.concurrent.TimeUnit;

public class MathGame {

private static Random random = new Random(); private int illegalArgumentCount = 0;

public static void main(String[] args) throws InterruptedException { MathGame game = new MathGame();

do {

game.run(); TimeUnit.SECONDS.sleep(1L);

} while (true);

}

public void run() throws InterruptedException { try {

int number = random.nextInt();

List primeFactors = this.primeFactors(number); MathGame.print(number, primeFactors);

}

catch (Exception e) {

System.out.println(String.format("illegalArgumentCount:%3d, ", this.illegalArgumentCount) + e.getMessage());

}

}

public static void print(int number, List primeFactors) { StringBuffer sb = new StringBuffer("" + number + "="); Iterator iterator = primeFactors.iterator();

while (iterator.hasNext()) {

int factor = iterator.next(); sb.append(factor).append('*');

}

if (sb.charAt(sb.length() - 1) == '*') { sb.deleteCharAt(sb.length() - 1);

}

System.out.println(sb);

}

public List primeFactors(int number) { if (number < 2) {

++this.illegalArgumentCount;

throw new IllegalArgumentException("number is: " + number + ", need >=

2");

}

ArrayList result = new ArrayList(); int i = 2;

while (i <= number) {

if (number % i == 0) { result.add(i); number /= i;

i = 2;

continue;

}

++i;

}

return result;

}

}

Affect(row-cnt:1) cost in 970 ms.

watch

通过[watch](https://alibaba.github.io/arthas/watch.html)命令来查看demo.MathGame#primeFactors 函数的返回值：

#### 退出

如果只是退出当前的连接，可以⽤quit 或者exit 命令。Attach到⽬标进程上的arthas还会继续运⾏， 端⼝会保持开放，下次连接时可以直接连接上。

如果想完全退出arthas，可以执⾏ stop 命令。

#### 基础命令

help——查看命令帮助信息

[cat](https://alibaba.github.io/arthas/cat.html)——打印⽂件内容，和linux⾥的cat命令类似[grep](https://alibaba.github.io/arthas/grep.html)——匹配查找，和linux⾥的grep命令类似

[tee](https://alibaba.github.io/arthas/tee.html)——复制标准输⼊到标准输出和指定的⽂件，和linux⾥的tee命令类似[pwd](https://alibaba.github.io/arthas/pwd.html)——返回当前的⼯作⽬录，和linux命令类似

cls——清空当前屏幕区域session——查看当前会话的信息

[reset](https://alibaba.github.io/arthas/reset.html)——重置增强类，将被 Arthas 增强过的类全部还原，Arthas 服务端关闭时会重置所有增强过的类

version——输出当前⽬标 Java 进程所加载的 Arthas 版本号

history——打印命令历史

quit——退出当前 Arthas 客户端，其他 Arthas 客户端不受影响stop——关闭 Arthas 服务端，所有 Arthas 客户端全部退出[keymap](https://alibaba.github.io/arthas/keymap.html)——Arthas快捷键列表及⾃定义快捷键

#### jvm相关

[dashboard](https://alibaba.github.io/arthas/dashboard.html)——当前系统的实时数据⾯板[thread](https://alibaba.github.io/arthas/thread.html)——查看当前 JVM 的线程堆栈信息[jvm](https://alibaba.github.io/arthas/jvm.html)——查看当前 JVM 的信息[sysprop](https://alibaba.github.io/arthas/sysprop.html)——查看和修改JVM的系统属性[sysenv](https://alibaba.github.io/arthas/sysenv.html)——查看JVM的环境变量

[vmoption](https://alibaba.github.io/arthas/vmoption.html)——查看和修改JVM⾥诊断相关的option [logger](https://alibaba.github.io/arthas/logger.html)——查看和修改logger

[getstatic](https://alibaba.github.io/arthas/getstatic.html)——查看类的静态属性[ognl](https://alibaba.github.io/arthas/ognl.html)——执⾏ognl表达式[mbean](https://alibaba.github.io/arthas/mbean.html)——查看 Mbean 的信息

[heapdump](https://alibaba.github.io/arthas/heapdump.html)——dump java heap, 类似jmap命令的heap dump功能

#### class/classloader相关

[sc](https://alibaba.github.io/arthas/sc.html)——查看JVM已加载的类信息[sm](https://alibaba.github.io/arthas/sm.html)——查看已加载类的⽅法信息[jad](https://alibaba.github.io/arthas/jad.html)——反编译指定已加载类的源码

[mc](https://alibaba.github.io/arthas/mc.html)——内存编绎器，内存编绎 .java ⽂件为.class ⽂件

[redeﬁne](https://alibaba.github.io/arthas/redefine.html)——加载外部的 .class ⽂件，redeﬁne到JVM⾥

[dump](https://alibaba.github.io/arthas/dump.html)——dump 已加载类的 byte code 到特定⽬录

[classloader](https://alibaba.github.io/arthas/classloader.html)——查看classloader的继承树，urls，类加载信息，使⽤classloader去getResource

#### monitor/watch/trace相关

请注意，这些命令，都通过字节码增强技术来实现的，会在指定类的⽅法中插⼊⼀些切⾯来实现 数据统计和观测，因此在线上、预发使⽤时，请尽量明确需要观测的类、⽅法以及条件，诊断结

束要执⾏

或将增强过的类执⾏

命令。

[monitor](https://alibaba.github.io/arthas/monitor.html)——⽅法执⾏监控[watch](https://alibaba.github.io/arthas/watch.html)——⽅法执⾏数据观测

[trace](https://alibaba.github.io/arthas/trace.html)——⽅法内部调⽤路径，并输出⽅法路径上的每个节点上耗时[stack](https://alibaba.github.io/arthas/stack.html)——输出当前⽅法被调⽤的调⽤路径

[tt](https://alibaba.github.io/arthas/tt.html)——⽅法执⾏数据的时空隧道，记录下指定⽅法每次调⽤的⼊参和返回信息，并能对这些不同的 时间下调⽤进⾏观测

#### proﬁler/⽕焰图

[proﬁler](https://alibaba.github.io/arthas/profiler.html)–使⽤[async-proﬁler](https://github.com/jvm-profiling-tools/async-profiler)对应⽤采样，⽣成⽕焰图

- 启动proﬁler

默认情况下，⽣成的是cpu的⽕焰图，即event为cpu 。可以⽤--event 参数来指定。

2) 获取已采集的sample的数量

3) 查看proﬁler状态

- 停⽌proﬁler

⽣成svg格式结果

5) 查看结果

通过浏览器查看arthas-output下⾯的proﬁler结果

默认情况下，arthas使⽤3658端⼝，则可以打开： http://localhost:3658/arthas-output/ 查看到arthas-output ⽬录下⾯的proﬁler结果：

#### options

[options](https://alibaba.github.io/arthas/options.html)——查看或设置Arthas全局开关

#### 管道

Arthas⽀持使⽤管道对上述命令的结果进⾏进⼀步的处理，如 sm java.lang.String * | grep

grep——搜索满⾜条件的结果plaintext——将命令的结果去除ANSI颜⾊wc——按⾏统计输出结果

#### 后台异步任务

当线上出现偶发的问题，⽐如需要watch某个条件，⽽这个条件⼀天可能才会出现⼀次时，异步后台任 务就派上⽤场了，详情请参考[这⾥](https://alibaba.github.io/arthas/async.html)

使⽤ > 将结果重写向到⽇志⽂件，使⽤ & 指定命令是后台运⾏，session断开不影响任务执⾏（⽣命周期默认为1天）

jobs——列出所有job kill——强制终⽌任务

fg——将暂停的任务拉到前台执⾏bg——将暂停的任务放到后台执⾏

#### ⽤户数据回报

在 3.1.4 版本后，增加了⽤户数据回报功能，⽅便统⼀做安全或者历史数据统计。在启动时，指定stat-url ，就会回报执⾏的每⼀⾏命令，⽐如：

在tunnel server⾥有⼀个示例的回报代码，⽤户可以⾃⼰在服务器上实现

#### 其他特性

[异步命令⽀持](https://alibaba.github.io/arthas/async.html)[执⾏结果存⽇志](https://alibaba.github.io/arthas/save-log.html)[批处理的⽀持](https://alibaba.github.io/arthas/batch-support.html)

[ognl表达式的⽤法说明](https://github.com/alibaba/arthas/issues/11)

## 3.5 GChisto

⼀款分析 GC ⽇志的离线分析⼯具， GChisto 以表格和图形化的⽅式展示 GC 次数、 持续时间等， 提⾼了分析 GC ⽇志的效率。

源码地址：https://github.com/jewes/gchisto

#### How to build gchisto

**How to run it**

#### 应⽤

- 导⼊成功， 切到 GC Pause Stats 选项卡， 可以⼤致看下 GC 的次数、 GC 的时间、 GC 的开

销、 最⼤ GC 时间和最⼩ GC 时间等 。

垃圾收集的开销（Overhead）表示垃圾收集的调优程度。 ⼀般情况， 并发垃圾收集的开销应该

⼩于 10%， 也有可能达到 1% ~ 3%。

- 切到 GC Pause Distribution 选项卡， 可以查看 GC 停顿的详细分布， x 轴表示垃圾收集停顿时间， y 轴表示是停顿次数。
- 切换到 GC Timeline 选项卡， 可以显示整个时间线上的垃圾收集， 以便于按时间去查找应⽤⽇志（tomcat ⽇志等）， 去了解峰值时系统发⽣了什么。

不过这个⼯具似乎没怎么维护了， 存在不少 bug， 使⽤过程发现识别不了 JDK 1.7 GC ⽇志的

Young GC， 还有⼀些 NullPointer 错误。 整体来说， 只能观察某些参数

## 3.6 IBM HeapAnalyzer

IBM HeapAnalyzer是⽤于发现可能的Java堆泄漏的图形⼯具。

#### 下载

[Download: https://public.dhe.ibm.com/software/websphere/appserv/support/tools/HeapAna lyzer/ha457.jar](https://public.dhe.ibm.com/software/websphere/appserv/support/tools/HeapAnalyzer/ha457.jar)

#### 启动

java -Xmx2g -jar ha457.jar

dump⽂件打开后

根据官⽅⽂档的说法，如果服务器存在内存泄漏的可能，那么会出现上图红框所示的“Reference Tree”界⾯。

也就是说，如果你打开dump⽂件后，没有找到这个界⾯，说明你的jvm是正常的。

Reference Tree中列出的是当前堆中占⽐最⼤的对象信息，另外也可以通过菜单栏中的“Tree view”选项查看所有对象的的详细信息

如果你的⽂件打开后出现“Reference Tree”界⾯，在其中选择占⽐最⼤⼀项，⼀层层点开，最后就会看到你熟悉的类了