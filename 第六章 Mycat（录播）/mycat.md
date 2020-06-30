# 课堂主题

###### MyCat介绍、MyCat核心概念、MyCat分片规则、MyCat读写分离

**课堂目标**

理解MyCat介绍和核心概念掌握MyCat分片规则

掌握MyCat读写分离

能够使用MyCat进行分库分表和读写分离

**Mycat\****介绍**

**什么是\****Mycat***\*？**

官方网站：[http://www.mycat.org.cn](http://www.mycat.org.cn/)/ http://www.mycat.io/ db proxy Mycat

**Mycat\****架构**

**Mycat\****核心概念**

**Schema**：由它指定逻辑数据库（相当于MySQL的database数据库） **Table**： 逻 辑 表 （ 相 当 于 MySQL 的 table 表 ） **DataNode**： 真 正 存 储 数 据 的 物 理 节 点 **DataHost**：存储节点所在的数据库主机（指定MySQL数据库的连接信息） **User**：MyCat的用户（类似于MySQL的用户，支持多用户）

**Mycat\****主要解决的问题**

**海量数据存储查询优化**

**Mycat\****对多数据库的支持**

**Mycat\****分片策略**

###### MyCAT支持水平分片与垂直分片：

水平分片：一个表格的数据分割到多个节点上，按照行分隔。

垂直分片：一个数据库中多个表格A，B，C，A存储到节点1上，B存储到节点2上，C存储到节点3 上。

MyCAT通过定义表的分片规则来实现分片，每个表格可以捆绑一个分片规则，每个分片规则指定一个分 片字段并绑定一个函数，来实现动态分片算法。

1. Schema

   ：逻辑库，与MySQL中的Database（数据库）对应，一个逻辑库中定义了所包括的Table。

   1. **Table**：表，即物理数据库中存储的某一张表，与传统数据库不同，这里的表格需要声明其所存储的 逻辑数据节点DataNode。**在此可以指定表的分片规则。**
   2. **DataNode**：MyCAT的逻辑数据节点，是存放table的具体物理节点，也称之为分片节点，通过DataHost来关联到后端某个具体数据库上

2. **DataHost**：定义某个物理库的访问地址，用于捆绑到Datanode上

**Mycat\****安装**

**注意：需要先安装\****jdk***\*（操作系统如果是\****64***\*位，必须安装\****64***\*位的\****JDK***\*）**

###### 第一步：下载MyCat

第二步：解压缩，得到mycat目录

第三步：进入mycat/bin，启动MyCat

第四步：访问Mycat

**Mycat\****分片**

**配置\****schema.xml**

#### **schema.xml\****介绍**

schema.xml作为Mycat中重要的配置文件之一，**管理着\****Mycat***\*的逻辑库、表、分片规则、\****DataNode**

**以及\****DataHost***\*之间的映射关系。**弄懂这些配置，是正确使用Mycat的前提。

###### schema 标签用于定义MyCat实例中的逻辑库

Table 标签定义了MyCat中的逻辑表

dataNode 标签定义了MyCat中的数据节点，也就是我们通常说所的数据分片。

dataHost标签在mycat逻辑库中也是作为最底层的标签存在，直接定义了具体的数据库实例、读写分离配置和心跳语句。

**schema.xml\****配置**

<!-- <dataNode name="dn1$0-743" dataHost="localhost1" database="db$0-743"

/> -->

<dataHost name="localhost1" maxCon="1000" minCon="10" balance="0" writeType="0" dbType="mysql" dbDriver="native" switchType="1"

slaveThreshold="100">

select user()



## **配置Server.xml**

#### **server.xml\****介绍**

###### server.xml几乎保存了所有mycat需要的系统配置信息。最常用的是在此配置用户名、密码及权限。

**server.xml\****配置**

**配置\****rule.xml**

rule.xml里面就定义了我们对表进行拆分所涉及到的规则定义。我们可以灵活的对表使用不同的分片算 法，或者对表使用相同的算法但具体的参数不同。这个文件里面主要有tableRule和function这两个标签。在具体使用过程中可以按照需求添加tableRule和function。

此配置文件可以不用修改，使用默认即可。

tableRule 标签配置说明：

**name** 属性指定唯一的名字，用于标识不同的表规则

**rule** 标签则指定对物理表中的哪一列进行拆分和使用什么路由算法。

**columns** 内指定要拆分的列名字。

**algorithm** 使用 function 标签中的 name 属性。连接表规则和具体路由算法。当然，多个表规则可以连接到同一个路由算法上。 table 标签内使用。让逻辑表使用这个规则进行分片。

function 标签配置说明：

**name** 指定算法的名字。

**class** 制定路由算法具体的类名字。

**property** 为具体算法需要用到的一些属性。

路由算法的配置可以查看算法章节。

**十个常用的分片规则**

**连续分片**

**一、日期列分区法**

**配置说明：**

tableRule标签：

：标识将要分片的表字段

：指定分片函数

function 标 签 ： dateFormat ：日期格式sBeginDate ：开始日期

sPartionDay ：分区天数，即默认从开始日期算起，分隔10天一个分区

**二、范围约定**

**配置说明：**

###### tableRule标签：

：标识将要分片的表字段

：指定分片函数

function标签：

mapFile ：指定分片函数需要的配置文件名称

###### **autopartition-long.txt\****文件内容：**

所有的节点配置都是从0开始，及0代表节点1，此配置非常简单，即预先制定可能的id范围对应某个分 片

\# range start-end

,data node

index

\# K=1000,M=10000.

0-500M=0

0-100

0

500M-1000M=1

101-200

1

201-300

2

优势：扩容无需迁移数据缺点：热点数据，并发受限**离散分片**

**一、枚举法**

**配置说明：**

tableRule标签：

：标识将要分片的表字段

：指定分片函数

function标签：

mapFile ：指定分片函数需要的配置文件名称

###### type ：默认值为0，0表示Integer，非零表示String

点1。

：指定默认节点，小于0表示不设置默认节点，大于等于0表示设置默认节点，0代表节

默认节点的作用：枚举分片时，如果碰到不识别的枚举值，就让它路由到默认节点。

如果不配置默认节点（defaultNode值小于0表示不配置默认节点），碰到不识别的枚举值 就会报错：

can't ﬁnd datanode for sharding column:column_name val:ﬀﬀﬀﬀ

**partition-hash-int.txt** **配置：**

**二、求模法**

此种配置非常明确，即根据id与count（你的结点数）进行求模运算，相比方式1，此种在批量插入时需 要切换数据源，id不连续

**配置说明：**

tableRule标签：

：标识将要分片的表字段

：指定分片函数

function标签：

count ：节点数量

**三、字符串拆分\****hash***\*解析**

##### 配置说明：

###### tableRule标签：

：标识将要分片的表字段

：指定分片函数

function标签：

length ：代表字符串hash求模基数

count ：分区数

###### ： hash预算位，即根据子字符串 hash运算

"2" -> (0,2)

"1:2" -> (1,2

"1:" -> (1,0)

"-1:" -> (-1,0)

":-1" -> (0,-1)

":" -> (0,0)

**四、固定分片\****hash***\*算法**

##### 配置说明：

###### tableRule标签：

：标识将要分片的表字段

：指定分片函数

function标签：

partitionCount ：指定分片个数列表

partitionLength ： 分片范围列表，分区长度:默认为最大2^n=1024 ,即最大支持1024分区约束 ：

count,length 两个数组的长度必须是一致的。 **1024 = sum((count[i]\*length[i]))**

##### 用法例子：

**如果需要平均分配设置：平均分为\****4***\*分片，\****partitionCount*partitionLength=1024**

**五、一致性\****hash**

**一致性\****hash 0-2***\*的\****32***\*次方减\****1** **预算有效解决了分布式数据的扩容问题，前\****1-9***\*中\****id***\*规则都多少存在数据扩容难题，而\****10***\*规则解决了数据扩容难点**

##### 六、编程指定

**配置说明：**

###### tableRule标签：

：标识将要分片的表字段

：指定分片函数

function标签：

startIndex ：字符串截取的起始索引位置

size ：截取的位数

partitionCount ：分区数量

defaultPartition ：默认分区

###### 11010419800101

此方法为直接根据字符子串（必须是数字）计算分区号（由应用传递参数，显式指定分区号）。 例如id=05-100000002

在此配置中代表根据id中从startIndex=0，开始，截取siz=2位数字即05，05就是获取的分区，如果没传默认分配到defaultPartition

优点：数据分布均匀，并发能力强缺点：移植性差、扩容性差

**综合分片**

**一、通配取模**

**配置说明：**

tableRule标签：

：标识将要分片的表字段

：指定分片函数

function标签：

patternValue ：求模基数

defaultNode ：默认节点，如果不配置了默认，则默认是0即第一个结点

mapFile ：配置文件路径

###### **partition-pattern.txt\****文件内容***\*:**

配置文件中， 1-32 即代表id%256 后分布的范围，如果在1-32则在分区1，其他类推，如果id非数字数据，则会分配在defaultNode 默认节点

**二、\****ASCII***\*码求模通配**

##### 配置说明：

###### tableRule标签：

：标识将要分片的表字段

：指定分片函数

function 标 签 ： patternValue ：求模基数prefixLength ：ASCII 截取的位数mapFile ：配置文件路径**partition-pattern.txt\****文件内容：**

配置文件中， 1-32 即代表id%256 后分布的范围，如果在1-32则在分区1，其他类推

此种方式类似方式6，只不过采取的是将列中前preﬁxLength位所有ASCII码的和与patternValue 进行求模，即sum%patternValue ,获取的值在通配范围内的，即分片数。

ASCII编码：

48-57=0-9阿拉伯数字

64、65-90=@、A-Z

97-122=a-z

**测试分片**

**需求**

把商品表分片存储到三个数据节点上。

**创建表**

配置完毕后，重新启动mycat。使用mysql客户端连接mycat，创建表。

**分片测试**

分片策略指定为“auto-sharding-long” 分片规则指定为“mod-long”

**Mycat\****读写分离**

MyCat的读写分离是建立在**MySQL\****主从复制基础**之上实现的，所以必须先搭建MySQL的主从复制。 数据库读写分离对于大型系统或者访问量很高的互联网应用来说，是必不可少的一个重要功能。对于

MySQL来说，标准的读写分离是主从模式，一个写节点Master后面跟着多个读节点，读节点的数量取

决于系统的压力，通常是1-3个读节点的配置

Mycat实现的读写分离和自动切换机制，需要mysql的主从复制机制配合。

**Mycat\****配置**

Mycat 1.4 支持MySQL主从复制状态绑定的读写分离机制，让读更加安全可靠，配置如下：

- **设置** **balance="1"\****与***\*writeType="0"**

Balance参数设置：

1. balance=“0”, 所有读操作都发送到当前可用的writeHost上。
2. balance=“1”，所有读操作都随机的发送到readHost。
3. balance=“2”，所有读操作都随机的在writeHost、readhost上分发

WriteType参数设置：

1. writeType=“0”, 所有写操作都发送到可用的writeHost上。
2. writeType=“1”，所有写操作都随机的发送到readHost。
3. writeType=“2”，所有写操作都随机的在writeHost、readhost分上发。

“readHost是从属于writeHost的，即意味着它从那个writeHost获取同步数据，因此，当它所属的writeHost宕机了，则它也不会再参与到读写分离中来，即“不工作了”，这是因为此时，它的数据已

经“不可靠”了。基于这个考虑，目前mycat 1.3和1.4版本中，若想支持MySQL一主一从的标准配置，并且在主节点宕机的情况下，从节点还能读取数据，则需要在Mycat里配置为两个writeHost并设置banlance=1。”

- **设 置** **switchType="2"** **与** **slaveThreshold="100"** **switchType** **目前有三种选择：**

##### -1：表示不自动切换1：默认值，自动切换

**2\****：基于***\*MySQL\****主从同步的状态决定是否切换**

Mycat心跳检查语句配置为 show slave status ，dataHost 上定义两个新属性： **switchType="2"** **与\****slaveThreshold="100"**，此时意味着开启MySQL主从复制状态绑定的读写分离与切换机制。Mycat心 跳 机 制 通 过 检 测 show slave status 中 的 \**"Seconds_Behind_Master", "Slave_IO_Running", "Slave_SQL_Running"** 三个字段来确定当前主从同步的状态以及Seconds_Behind_Master主从复制时延。

**项目案例**

案例图：

schema.xml

<mycat:schema xmlns:mycat=["http://io.mycat/](http://io.mycat/)">

<!-- global table is auto cloned to all defined data nodes ,so can join with any table whose sharding node is in the same data node

joinKey ： 外键

parentKey ： 指向外键的主键（tuser表）

-->

<childTable name="tuaddress" primaryKey="ID" joinKey="uid"

parentKey="id" />

<dataHost name="storeHost" maxCon="1000" minCon="10" balance="1" writeType="0" dbType="mysql" dbDriver="native"

switchType="2" slaveThreshold="100">

select user()

<!-- can have multi write hosts -->

<!-- can have multi read hosts -->

<dataHost name="userHost1" maxCon="1000" minCon="10" balance="0" writeType="0" dbType="mysql" dbDriver="native" switchType="1"

slaveThreshold="100">

select user()

<!-- can have multi write hosts -->

<dataHost name="userHost2" maxCon="1000" minCon="10" balance="0" writeType="0" dbType="mysql" dbDriver="native"

switchType="1" slaveThreshold="100">

select user()

<!-- can have multi write hosts -->

<writeHost host="user_host2" url="192.168.24.129:3306"

user="root"

password="root">



###### rule.xml

id

func1

user_id

func1

sharding_id

hash-int

id

rang-long

id

mod-long

id

murmur

id

crc32slot

create_time

partbymonth

calldate

latestMonth

id

rang-mod

id

jump-consistent-hash

id

mod-long

0

2

160

<!-- weightMapFile 节点的权重， 没有指定权重的节点默认是1。以properties文件的格式填写，以从0开始到count-1的整数值也就是节点索 引为key，以节点权重值为值。所有权重值必须是正整数，否则以1代替 -->

<!-- /etc/mycat/bucketMapPath

用于测试时观察各物理节点与虚拟节点的分布情况，如果指定了这个属性，会把虚拟节点的

murmur hash值与物理节点的映射按行输出到这个文件，没有默认值，如果不指定，就不会输出任何东西 --

> 

<function name="crc32slot"

class="io.mycat.route.function.PartitionByCRC32PreSlot">

2

partition-hash-int.txt

autopartition-long.txt

<!-- how many data nodes -->

2

8

128

###### server.xml

<!-- - - Licensed under the Apache License, Version 2.0 (the "License");

- you may not use this file except in compliance with the - You may obtain a copy of the License at - -

http://www.apache.org/licenses/LICENSE-2.0

\- - Unless required by applicable law or agreed to in writing, software - distributed under the License is distributed on an "AS IS" BASIS, - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. - See the License for the specific language governing permissions and - limitations under the License. -->

<mycat:server xmlns:mycat=["http://io.mycat/](http://io.mycat/)">

0

0

议-->

2

0

0

1

1m

1k

0

384m

true

<!-- 全局SQL防火墙设置 -->

###### 步骤：

**作业**

**用户购物下单实践**

### 需求

需求：把订单信息分片，商家要查询出售的订单，用户要查询自己的订单。表设计：用户、商家订单、订单明细、用户订单

分片规则：

“用户表”---user_id 取模，“商家订单”--- seller_user_id 取模

“订单明细”----“商家订单”ER 分片，“买家订单”-- buyer_user_id 取模

**业务表**

**服务器分配**

mysql：

主

192.168.10.135

主

192.168.10.136

主

192.168.10.137

mycat：

**表主键生成策略**

[使用mycat全局序列生成（mycat 全局序列号：http://blog.csdn.net/convict_eva/article/details/5191 7499）](http://blog.csdn.net/convict_eva/article/details/51917499)

添加mycat全局序列号：

在dn_master结点执行：

重启mycat测试：

**rule.xml\****配置**

**配置\****function** **标签**

找到function 标签name="mod-long" 的function 配置，修改总结点数为3（和使用的mysql 结点数一致）。

**配置\****tableRule***\*标签**

**注意事项：\****name** **要全局唯一**

###### rule.xml 配置添加如下配置

**schema.xml** **配置**

<mycat:schema xmlns:mycat=["http://org.opencloudb/](http://org.opencloudb/)">

<!-- auto sharding by id (long) -->

<!-- 全局表，使用type属性指定，多个结点要都执行建表语句。所有结点的数据一致。 -->

<!-- 配置表所在的分片结点，指定主键和分片规则。指定主键是为了使用主键查询时mycat什么缓存主键对应的dn，提高查询效率。 -->

primaryKey="user_id" dataNode="dn_master,dn_master2,dn_master3" />



<!-- 配置ER 分片，子表的存储依赖于主表，并且物理上紧邻存放。 -->

<childTable name="tb_order_detail" primaryKey="order_detail_id" joinKey="order_id"

parentKey="order_id" />

<!-- 配置数据结点 -->

<!-- ddata node host 配置 -->

<dataHost name="master" maxCon="1000" minCon="10" balance="3"

writeType="0" dbType="mysql" dbDriver="native" switchType="1" slaveThreshold="100">

<!-- 主从心跳语句配置 -->

show slave status

<!-- 从库 -->

select user()



select user()





重启mycat，使用SQLyog连接到mycat，并执行建表语句。

**测试**

**插入\****user**

VALUES ('name-11',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-12',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-13',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-14',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-15',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-16',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-17',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-18',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-19',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-20',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-21',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-22',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

INSERT INTO `tb_user`(`login_name`,`user_id`,`TYPE`,`passwd`)

VALUES ('name-23',NEXT VALUE FOR MYCATSEQ_USER_ID_SQUE,1,'passwd-A');

###### 查看插入的数据是否按照id取模分片。

**下单测试**

上面创建的是商家用户，下面创建买家用户。

所有的用户如下： mysql> select * from tb_user order by login_name; +------------+---------+------+-------

---+ | login_name | user_id | TYPE | passwd | +------------+---------+------+----------+ | buyer-1 |

248 | 2 | passwd-A | | buyer-2 | 249 | 2 | passwd-A | | buyer-3 | 250 | 2 | passwd- A | | name-1 | 225 | 1 | passwd-A | | name-10 | 234 | 1 | passwd-A | | name-11 |

235 | 1 | passwd-A | | name-12 | 236 | 1 | passwd-A | | name-13 | 237 | 1 | passwd-A | | name-14 | 238 | 1 | passwd-A | | name-15 | 239 | 1 | passwd-A | | name-16 | 240 | 1 | passwd-A | | name-17 | 241 | 1 | passwd-A | | name-18 | 242 | 1 | passwd-A | | name-19 | 243 | 1 | passwd-A | | name-2 | 226 | 1 | passwd-A | | name-20 | 244 | 1 | passwd-A | | name-21 | 245 | 1 | passwd-A | | name-22 | 246 | 1 | passwd-A | | name-23 | 247 | 1 | passwd-A | | name-3 |

227 | 1 | passwd-A | | name-4 | 228 | 1 | passwd-A | | name-5 | 229 | 1 | passwd-A | | name-6 | 230 | 1 | passwd-A | | name-7 | 231 | 1 | passwd-A | | name-8 | 232 | 1 | passwd-A | | name-9 | 233 | 1 | passwd-A | +------------+---------+---

---+----------+

下单：

说明：商家225 在109.128 上，tb_seller_order表根据seller_user_id 取模分片，所有此订单数据存储在与user id为225 的商家同一分片 tb_order_detail 表使用的是与tb_seller_order ER 分片，使用order_id 关联，所以tb_order_detail 存储的分片与相同的order_id 的tb_seller_order 的数据在同一分片。

再测试一条数据：user_id为238 的用户存储在109.131 分片上。

测试结果： 109.128数据为： mysql> select * from tb_user; +------------+---------+------+----------- + |

login_name | user_id | TYPE | passwd | +------------+---------+------+----------+ | name-1 | 225 | 1 | passwd-A | | name-4 | 228 | 1 | passwd-A | | name-7 | 231 | 1 | passwd-A | | name-10 | 234 | 1 | passwd-A | | name-13 | 237 | 1 | passwd-A | | name-16 | 240 | 1 | passwd-A | | name-19 | 243 | 1 | passwd-A | | name-22 | 246 |----------------- 1 | passwd-A | | buyer-2 | 249 | 2 | passwd-A | +------------+---------+------+--- + 9 rows in set

(0.00 sec)

mysql> select * from tb_seller_order; +----------------+---------------+----------+-------+------- + |

seller_user_id | buyer_user_id | order_id | price | STATUS | +----------------+---------------+----------+------

-+--------+ | 225 | 248 | 201 | 1222 | NULL | +----------------+---------------+----------+-----

--+------- + 1 row in set (0.00 sec)

mysql> select * from tb_order_detail; +----------------+-----------------+----------+----------+-------------+------+--

----------+ | seller_user_id | order_detail_id | order_id | goods_id | goods_name | cnt | unit_price

| +----------------+-----------------+----------+----------+-------------+------+------------+ | 225 | 201 |

201 | 11 | goods_name | 1 | 1220 | | 225 | 202 | 201 | 11 | goods_name2 | 1 | 2 | +----------------+-----------------+----------+----------+-------------+------+------------

\+ 2 rows in set (0.00 sec)

109.131数据为： mysql> select * from tb_user; +------------+---------+------+----------- + | login_name |

user_id | TYPE | passwd | +------------+---------+------+----------+ | name-2 | 226 | 1 | passwd-A

| | name-5 | 229 | 1 | passwd-A | | name-8 | 232 | 1 | passwd-A | | name-11 | 235 | 1 | passwd-A | | name-14 | 238 |------------- 1 | passwd-A | | name-17 | 241 | 1 | passwd-A | | name-20 | 244 |------------------- 1 | passwd-A | | name-23 | 247 | 1 | passwd-A | | buyer-3 | 250 | 2 | passwd-A | +------------+---------+------+ + 9 rows in set (0.00 sec)

mysql> select * from tb_seller_order; +----------------+---------------+----------+-------+------- + |

seller_user_id | buyer_user_id | order_id | price | STATUS | +----------------+---------------+----------+------

-+--------+ | 238 | 248 | 203 | 1222 | NULL | +----------------+---------------+----------+-----

--+------- + 1 row in set (0.00 sec)

mysql> select * from tb_order_detail; +----------------+-----------------+----------+----------+-------------+------+--

----------+ | seller_user_id | order_detail_id | order_id | goods_id | goods_name | cnt | unit_price

| +----------------+-----------------+----------+----------+-------------+------+------------+ | 238 | 203 |

203 | 11 | goods_name | 1 | 1220 | | 238 | 204 | 203 | 11 | goods_name2 | 1 | 2 | +----------------+-----------------+----------+----------+-------------+------+------------

\+ 2 rows in set (0.00 sec)