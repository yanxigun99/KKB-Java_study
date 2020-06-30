## 课堂主题

MySQL集群、主从复制、读写分离、分库分表概念

**课堂目标**

理解主从复制的原理会配置主从复制

会对主从复制进行修复

会使用Mysql-proxy进行读写分离理解垂直切分和水平切分

了解分库分表的问题和解决方案

**MySQL\****集群篇**

**集群搭建之主从复制**

**主从复制原理**

主对外工作，从对内备份。

**binlog\****介绍和***\*relay\****日志**

**关闭主从机器的防火墙**

**主服务器配置**

**第一步：修改\****my.cnf***\*文件**

在[mysqld]段下添加：

\#启用二进制日志

log-bin=mysql-bin

\#服务器唯一ID，一般取IP最后一段server-id=133

\#指定复制的数据库(可选) binlog-do-db=kkb2 binlog-ignore-db=kkb

\#指定不复制的数据库(可选,，mysql5.7)

replicate-ignore-db=kkb

\#指定忽略的表（可选，mysql5.7） replicate-ignore-table = db.table1

**第二步：重启\****mysql***\*服务**

**第三步：主机给从机授备份权限**

**第四步：刷新权限**

**第五步：查询\****master***\*的状态**

show master status; +------------------+----------+--------------+------------------+--------------- ----+ | File | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set | +------------------+----------+--------------+------------------+--------------- ----+ " src="data:image/png;base64,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" v:shapes="_x0000_s1160">

主从备份： mysqldump

**从服务器配置**

**第一步：修改\****my.cnf***\*文件**

第二步：重启并登录到MySQL进行配置从服务器以前是启动状态

show slave status \G； 先关闭 stop slave

同步初始化 master_log_ﬁle 、 master_log_pos 以主机状态为主 show master status

mysql>change master to master_host='192.168.56.101', master_port=3306, master_user='root', master_password='root', master_log_file='mysql-bin.000059', master_log_pos=394;

### 第四步：启动从服务器复制功能

start slave;" src="data:image/png;base64,R0lGODlh4AIrAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgB9AAoAhQAAAA0LGxQLDRsLGxsLDQ0LFBsLFA0LOw0ZJw0LJg0TIQ0KUA0IYxsnMxQhLScZFCcZDSETDTULDSILDTULOzULJiIGdTUEhy0hFDMnGyczMzMzMy0hISEtMzMtITMtLS0zLS0zMzMzJy0zJyEtLSczJzMzLSczLS0tLUcKDVcIDVcAdVcAY0cDY0cDdVcAh0cDh2cGJncEO2cEO3cDUHcEUHcAY2cAY2cDdXcAh2cAhwECAwECAwECAwECAwECAwb/QIBwSCwajwHNZoM5Op/QqFR4eOVysal2ywU0mtzDKtWljsvCSxbtdHA0Gcfm0QEDEB6BfAkRIpYfIBFDX0IOdkMWVzkqABeLOTYSQopXWQcsFFYyjpCSTpWMRGpEoVkWNBOUWQkwV5wACiFxIgMaGIcKIwQABSQRASW8Q0l9CCaDQoWGTEOYk0RiZEcMklWSCzNm007PR6RG1RLSAAktk6StjbJxGMEEh15NCBnKG/V+IrwBJ8nyRXLAPIK1rUiVK9ZYQHN2BspAI+CoWImEjhMDTgcXrTHkTlg8XQZ+EQl4Jw8Afv6WDfmzoQ8lLAWdvWi04MY4hQYbRlG00dHGhCo0bQKoWcEFGaBtOsID0wAFPiL0YgkC4AAZITtynhK5GLOgOG/RdEbhOiQiOXFpcBC8kIoIu0Pv4klNJmuJPmYbOPS7yrHIAh0IFz6i+DJHDRg2KOAsMviTkb+BR/WsdPgTA1FCWmmMNeuJyigo2YgeTfqJ3CINNpicErq069dldBEIAgA7" v:shapes="_x0000_s1163">

**第五步：检查从服务器复制功能状态**

show slave status \G； ……………………(省略部分) Slave_IO_Running: Yes //此状态必须YES Slave_SQL_Running: Yes //此状态必须YES ……………………(省略部分) " src="data:image/png;base64,R0lGODlh4AKcAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEQAEAWgAhwAAAA0LGwoLDRQLDRsLGxsLDQ0LFAgZDQsLDRsLFBsTDRQLFBQLGxQTFBsZDQ0TGw0TFA0ZGxsZGw0LOw0LLQ0ZJw0LJg0TIRsTIRQTIRQZIQ0ZIQ0KUA0LSQ0IYw0ZYwQmDQMzLRsnMxQhLRshIRshJxQhIRQhJxsnJxshLRsnLQA+SQM+SQ0mfAszkwo+kwo+qghJfABJYwRJYwhJqgBUqgRUqgNUqicZFCcZDSETDSILDTULDSETGyETFCEZDScZGyEZGzULOzULJiILJiEZISEZJzUKUCIGdTUEYzUGYzUEhy0hFDMnGycnGy0nGy0hGychGyEhGychFCEhFCczMzMzMy0hISEtMzMtITMtLS0zLTMzJy0zJyEtLSczJzMzLSczLS0tLS0zMy0tISEnJyctMyEnLS0tMycnLSEtJycnISEnISEnMyctIScnJycnMzMtJyEhITMnIUcKDVcIDUcIUFcDUFcAdUcDdVcAY0cDY1cEY1cDdVcAh0cDh2cGJncEO2cEO3cDUGcDUHcEUHcAY2cAY3cAdWcDdXcAh2cAhwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEIHEiwoMGDAapYscLkoMOHE/DQeUix4sAJfhQpAmSxo8ePICu6CBGypMmTKD+KaJgSQMSJLS0u4RjT48uKN2NSiCGg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" v:shapes="_x0000_s1164">

测试

搭建成功之后，往主机中插入数据，看看从机中是否有数据

**主从延时**

1、因为SQLThread和IOThread是默认单线程，当主机的tps(每秒事务处理数)高于从机的Thread所能承受范围，则会出现从机复制延时

解决方案： 将thread改成多线程模式 MySQL5.6改表，MySQL5.7改GTID 2、网络延时

解决方案： 主和从在一个网内

3、IO延时

slave server硬件升级判断延时：

show slave status中Seconds_Behind_Master=0则不延时

建表加时间戳(timestamp)，看时间差

解决方案： 利用分库分表中间件 Mycat、 sharding JDBC

强制读取主库

**集群搭建之读写分离**

**读写分离的理解**

为什么要有读写分离集群？

主从集群的问题：只有主对外工作，从不对外工作。主既要负责写操作，也要负责读操作。 对于主从集群来说，只是保证了数据的安全备份。

主：负责部分读 、写从：负责读

名词解释：

**注意事项：**

**读写分离演示需求**

**MySQL-Proxy\****安装**

下载

解压缩

**MySQL-Proxy\****配置**

创建mysql-proxy.cnf文件

[mysql-proxy]

user=root

admin-username=root admin-password=root

proxy-address=192.168.10.137:4040

proxy-backend-addresses=192.168.10.135:3306

proxy-read-only-backend-addresses=192.168.10.136:3306

proxy-lua-script=/root/mysql-proxy/share/doc/mysql-proxy/rw-splitting.lua log-file=/root/mysql-proxy/logs/mysql-proxy.log

log-level=debug keepalive=true daemon=true

修改mysql-proxy.cnf文件的权限

修改rw-splitting.lua脚本

**MySQL-Proxy\****启动域测试**

启动命令

**注意事项：**

在其他客户端，通过mysql命令去连接MySQL Proxy机器

mysql -uroot -proot -h192.168.56.102 -P4040;

注： 关闭防火墙

# MySQL分库分表篇

## 传统项目结构

**数据库性能瓶颈：**

1、数据库连接数有限

MySQL数据库默认100个连接、单机最大1500连接。 2、表数据量

单机 表数量多，成百上千

单表数据，千万级别 5千万 (不超过100字节)

查询问题 索引，命中率问题，索引存磁盘，占空间

3、硬件问题

**数据库性能优化**

1、参数优化

2、缓存、索引

3、读写分离

4、分库分表 （最终方案）

MySQL （关系型数据库）------> NoSQL (Redis、MongoDB)-------- >NewSQL 分布式关系型数据库 TiDB

**分库分表介绍**

**使用背景**

当【表的数量】达到了几百上千张表时，众多的业务模块都访问这个数据库，压力会比较大，考虑对其 进行分库。

当【表的数据】达到了几千万级别，在做很多操作都比较吃力,所以，考虑对其进行分库或者分表

**数据切分（\****sharding***\*）方案**

数据的切分（Sharding）根据其切分规则的类型，可以分为两种切分模式：

**垂直切分：**按照业务模块进行切分，将不同模块的表切分到不同的数据库中。分库

分表

大表拆小表

Blob Clob : 二进制数据 头像 小图片

**水平切分：**将一张大表按照一定的切分规则，按照**行**切分成不同的表或者切分到不同的库中 范围式拆分

好处：数据迁移是部分迁移，扩展性好坏处：热点数据分布不均，压力不能负载

hash式拆分

好处：热点数据分布均匀，访问压力能负载坏处：扩展能力差，数据都要迁移

综合式 ：

先范围后hash

一致性hash环（mycat录播）

**水平切分规则**

**按照\****ID***\*取模：**对ID进行取模，余数决定该行数据切分到哪个表或者库中**按照日期：**按照年月日，将数据切分到不同的表或者库中

**按照范围：**可以对某一列按照范围进行切分，不同的范围切分到不同的表或者数据库中。

**切分原则**

第一原则：能不切分尽量不要切分。

第二原则：如果要切分一定要选择合适的切分规则，提前规划好。 （向上取整）

**第三原则：**数据切分尽量通过数据冗余或表分组（Table Group）来**降低跨库** **Join** 的可能。

**分库分表需要解决的问题**

**分布式事务问题**

分布式事务问题

本地事务：ACID

分布式事务：根据百度百科的定义，CAP定理又称CAP原则，指的是在一个分布式系统中，Consistency（一 致性）、 Availability（可用性）、Partition tolerance（分区容错性）。一致性是强一致性。CAP 理论最多只能同时满足两个。

BASE：基本可用+软状态+最终一致性

补偿事务，例如：TCC 利用记录日志等方式

**分布式主键\****ID***\*问题**

多个库中表的主键冲突

redis incr命令

数据库（生成主键）

UUID （不好） 长、不好排序

snowﬂake算法（https://www.sohu.com/a/232008315_453160）

**跨库\****join***\*问题**

建立全局表（每个库都有一个相同的表） 代码表

E-R分片（将有ER关系的记录都存储到一个库中）

最多支持跨两张表跨库的join

**分库分表实现技术**

阿里的TDDL、Cobar

基于阿里Cobar开发的Mycat 当当网的sharding-jdbc

**Sharding\****案例分析**

用户表

uid、name、city、timestamp、sex、age 5亿条数据

x86 64位机

查询维度单一 uid

问：

分几张表？

PartitionKey如何选择？ uid、city、timestamp ？

分表原则

单行数据大于100字节 则 1千万一张表单行数据小于100字节则5千万一张表用户表单行数据小于100字节

5亿/5千万=10张 上取整为 16张表使用哪个PartitionKey？

city、timestamp 会造成热点数据分布不均匀

使用uid uid%16