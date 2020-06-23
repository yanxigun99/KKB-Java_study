# 调优案例

到这⾥，我们了解了监控⼯具的使⽤以及会调整哪些参数，知道这些都是理论基础，接下来我们理 论联系实际通过⼀些案例的分析，具体讲述怎么进⾏JVM调优。

# 1 内存优化示例

### 1 监控分析

当JVM运⾏稳定之后，通过以下命令得到dump⽇志

拿到如下FullGC信息:

以上gc⽇志中，在发⽣fullGC之时，整个应⽤的堆占⽤以及GC时间。为了更加精确需多次收集，计 算平均值。或者是采⽤耗时最⻓的⼀次FullGC来进⾏估算。上图中，⽼年代空间占⽤在93168kb（约93MB），以此定为⽼年代空间的活跃数据。

### 2 判断

因FullGC的时间1.25秒⼤于1秒，故需要进⾏调优。

### 3 确定⽬标

则其他堆空间的分配，基于以下规则来进⾏。

⽼年代的空间⼤⼩为 93MB

java heap：参数-Xms和-Xmx，建议扩⼤⾄3-4倍FullGC后的⽼年代空间占⽤。93 * (3-4) = (279-372)MB ，设置heap⼤⼩为372MB；

元空间：参数-XX:MetaspaceSize=N和-XX:MaxMetaspaceSize=N，建议扩⼤⾄1.2-1.5倍FullGc后 的永久带空间占⽤。

3135K*(1.2-1.5)=(3762-4702)K，设置元空间⼤⼩为5MB;

新⽣代：参数-Xmn，建议扩⼤⾄1-1.5倍FullGC之后的⽼年代空间占⽤。

93M*(1-1.5)=(93-139.5)M，设置新⽣代⼤⼩为140MB；

### 4 调整参数

- **对⽐差异**

收集FullGC⽇志，发现FullGC的时间0.28秒，已经⼩于1秒，并且频率不⾼。已经达到调优⽬标， 应⽤到所有服务器配置。

# 2 年轻代设定示例

确定young代的⼤⼩是通过评估垃圾回收的统计信息以及观察MinorGC的消耗时间和频率，下⾯举 例说明如何通过垃圾回收的统计信息来确定young代的⼤⼩。

尽管MinorGC消耗的时间和young代⾥⾯的存活的对象数量有直接关系，但是⼀般情况下，更⼩young代空间，更短的MinorGC时间。如果不考虑MinorGC的时间消耗，减少young代的⼤⼩会导致MinorGC变得更加频繁，由于更⼩的空间，⽤完空间会⽤更少的时间。同理，提⾼young代的⼤⼩会降 低MinorGC的频率。

当测试垃圾回收数据的时候，发现MinorGC的时间太⻓了，正确的做法就是减少young代的空间⼤

⼩。如果MinorGC太频繁了就增加young代的空间⼤⼩。

### 1 监控分析

下图是⼀个展示了MinorGC的例⼦，这个例⼦是运⾏在如下的HotSpot VM命令参数下的。

通过以配置以下参数⽣成dump⽂件

得到以下GC⽇志

上图显示了MinorGC平均的消耗时间是0.05秒，平均的频率是2.417秒1次。当计算MinorGC的消 耗时间和频率的时候，越多的数据参与计算，准确性会越⾼。并且应⽤要处于稳定运⾏状态下来收集MinorGC信息也是⾮常重要的。

下⼀步是⽐较MinorGC的平均时间和系统对延迟的要求，如果MinorGC的平均时间⼤于了系统的要求，减少young代的空间⼤⼩，然后继续测试，再收集数据以及重新评估。 如果MinorGC的频率⼤于了系统的要求，就增加young代的空间⼤⼩，然后继续测试，再收集以及重新评估。也许需要数次重复才 能够让系统达到延迟要求。当你改变young代的空间⼤⼩的时候，尽量保持old代的空间⼤⼩不要改变。

### 2 判断

从上图的垃圾回收信息来看，如果应⽤的延迟要求是40毫秒的话，观察到的MinorGC的延迟是50 毫秒，⽐系统的要求⾼出了不少。

### 3 调整参数

意味着old代的空间⼤⼩是4096M，减⼩young代的空间⼤⼩的10%⽽且要保持old代的空间⼤⼩不 变，可以使⽤如下选项。

注意的是young代的空间⼤⼩从2048M减少到1843M，整个Java堆的⼤⼩从6144M减少到5939M， 两者都是减少了205m。

### 4 重复调整

⽆论是young的空间调⼤还是调⼩，都需要重新收集垃圾回收信息和重新计算MinorGC的平均时间和 频率，以达到应⽤的延迟要求，可能需要⼏个轮回来达到这个要求。

另外⼀些调整young代的空间需要注意的事项：

1、old代的空间⼀定不能⼩于活动对象的⼤⼩的1.5倍。

2、young代的空间⾄少要有Java堆⼤⼩的10%，太⼩的Java空间会导致过于频繁的MinorGC。

3、当提⾼Java堆⼤⼩的时候，不要超过JVM可以使⽤的物理内存⼤⼩。如果使⽤过多的物理内存，会 导致使⽤交换区，这个会严重影响性能。

如果在仅仅是MinorGC导致了延迟的情况下，你⽆法通过调整young代的空间来满⾜系统的需求， 那么你需要重新修改应⽤程序、修改JVM部署模型把应⽤部署到多个JVM上⾯（通常得要多机器了）或者重新评估系统的需求。

# 3 死锁案例

所谓死锁，是指多个进程(线程)在运⾏过程中因争夺资源⽽造成的⼀种僵局，当进程(线程)处于这种僵持 状态时，若⽆外⼒作⽤，它们都将⽆法再向前推进。 因此我们举个例⼦来描述，如果此时有⼀个线程A，按照先锁a再获得锁b的的顺序获得锁，⽽在此同时⼜有另外⼀个线程B，按照先锁b再锁a的顺序获得锁。如下图所示：

## 3.1 死锁产⽣条件

1. 互斥条件：进程(线程)要求对所分配的资源进⾏排它性控制，即在⼀段时间内某资源仅为⼀进程 (线程)所占⽤。
   1. 请求和保持条件：当进程(线程)因请求资源⽽阻塞时，对已获得的资源保持不放。
   2. 不剥夺条件：进程(线程)已获得的资源在未使⽤完之前，不能剥夺，只能在使⽤完时由⾃⼰释

放。

1. 环路等待条件：在发⽣死锁时，必然存在⼀个进程(线程)--资源的环形链。

## 3.2 源代码

package com.example.demo;

import java.util.concurrent.TimeUnit; import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReentrantLock; public class DeathLock {

private static Lock lock1 = new ReentrantLock(); private static Lock lock2 = new ReentrantLock();

public static void deathLock() { Thread t1 = new Thread() {

@Override

public void run() { try {

lock1.lock(); TimeUnit.SECONDS.sleep(1); lock2.lock();

} catch (InterruptedException e) { e.printStackTrace();

}

}

};

Thread t2 = new Thread() { @Override

public void run() { try {

lock2.lock(); TimeUnit.SECONDS.sleep(1); lock1.lock();

} catch (InterruptedException e) { e.printStackTrace();

}

}

};

t1.setName("mythread1"); t2.setName("mythread2"); t1.start();

t2.start();

}

public static void main(String[] args) { deathLock();

}

}

## 3.3 查看进程号

- **jstack\****检测**

jstack是java虚拟机⾃带的⼀种堆栈跟踪⼯具。Jstack⼯具可以⽤于⽣成java虚拟机当前时刻的线程快照。线程快照是当前java虚拟机内每⼀条线程正在执⾏的⽅法堆栈的集合，⽣成线程快照的主要⽬的是 定位线程出现⻓时间停顿的原因，如线程间死锁 、 死循环 、 请求外部资源导致的⻓时间等待 等。 线程出现停顿的时候通过jstack来查看各个线程的调⽤堆栈，就可以知道没有响应的线程到底在后台做什么事 情，或者等待什么资源。

## 3.5 jconsole 检测

Jconsole是JDK⾃带的监控⼯具，在JDK/bin⽬录下可以找到。它⽤于连接正在运⾏的本地或者远 程的JVM，对运⾏在Java应⽤程序的资源消耗和性能进⾏监控，并画出⼤量的图表，提供强⼤的可视化 界⾯。⽽且本身占⽤的服务器内存很⼩，甚⾄可以说⼏乎不消耗。

## 3.6 死锁预防

### 1、以确定的顺序获得锁

如果必须获取多个锁，那么在设计的时候需要充分考虑不同线程之前获得锁的顺序。按照上⾯的例⼦， 两个线程获得锁的时序图如下：

### 2、超时放弃

当使⽤synchronized关键词提供的内置锁时，只要线程没有获得锁，那么就会永远等待下去，然⽽Lock 接⼝提供了 boolean tryLock(long time, TimeUnit unit) throws InterruptedException ⽅法，该⽅法可以按照固定时⻓等待锁，因此线程可以在获取锁超时以后，主动释放之前已经获得的所有 的锁。通过这种⽅式，也可以很有效地避免死锁。 还是按照之前的例⼦，时序图如下：