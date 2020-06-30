# 课程目标

掌握mongodb的router server、conﬁg server、data server工作原理掌握mongodb的replica set（副本集）工作原理

掌握mongodb的分片策略以及shard和chunk的理解掌握mongodb的常用命令

掌握mongodb的spring data mongodb应用掌握mongodb的主从搭建方式

掌握mongodb的副本集集群搭建方式 掌握mongodb的混合方式集群搭建方式掌握docker下搭建mongodb的混合集群

**知识要点**

**课程主题课程目标知识要点**

**MongoDB\****中的基本概念及原理**

MongoDB介绍MongoDB概念解析

数据库文档集合

合法的集合名

capped collections

元数据

MongoDB 数据类型

ObjectId 字 符 串 时 间 戳 日期

**MongoDB\****安装和常用命令**

安装启动

连接客户端常用客户端常用命令

创建数据库语法实例

删除数据库语法实例

创建集合

实例删除集合插入文档

删除文档查询文档

limit与skip方法limit() 方法

语法实例

skip() 方法

语法实例

文档排序

MongoDB 索引创建索引

语法实例1 实例2

查看集合索引

查看集合索引大小删除集合所有索引删除集合指定索引

聚合查询

$group

$sum

$avg

$min

$max

$match

$sort

$limit

$project

$lookup

**MongoDB Java\****客户端**

mongodb-java-API POM依赖

测试代码

Filters BasicDBObject

spring-data-mongod pom依赖

spring配置文件

perperties文件po类

dao接口和实现类测试类

**MongoDB\****架构**

RDBMS 与 MongoDB的区别MongoDB Wiredtiger存储引擎实现原理

Transport Layer业务层写请求

Journaling

一致性

**MongoDB\****集群**

MongoDB的高可用主从复制原理副本集集群

副本集与分片混合部署片键

以范围为基础的分片基于哈希的分片

**MongoDB\****主从搭建**

新建目录主机配置从机配置测试

读写分离

**MongoDB\****副本集集群**

新建目录节点1配置节点2配置节点3配置

配置主备和仲裁测试

无仲裁副本集节点1配置

节点2配置节点3配置

**MongoDB\****混合方式集群**

部署图

数据服务器配置

副本集1的配置副本集2的配置

配置服务器配置(先启动配置集再启动数据副本集) 配置副本集

路由服务器配置

关联切片和路由

**MongoDB\****数据稳定性**MongoDB丢失数据问题如何解决

恢复日志（journaling） 写关注(Write Concern) 配置方法

**MongoDB\****的应用场景和不适用场景**

适用场景不适用场景

**MongoDB\****文档设计**

文档设计（Collection）

_id的生成

字段名的选取

Collection结构设计

在同一个Collection中，内嵌文档

Collection关联，引用

**MongoDB\****在项目中遇到的问题**

大量删除数据大量数据空洞

**大厂常见面试题解析**

# MongoDB中的基本概念及原理

## MongoDB介绍

官网地址：https://www.mongodb.com/

MongoDB 是一个基于【分布式文件存储】的数据库，它属于NoSQL数据库。由 C++ 语言编写。旨在为 WEB 应用提供【可扩展】的【高性能】数据存储解决方案。

MongoDB是一个介于**非系数据库**和非关系数据库之间的产品，是非关系数据库当中功能最丰富，最像 关系数据库的。它支持的数据结构非常松散，是类似**json**的**bson**格式，因此可以存储比较复杂的数据 类型。Mongo最大的特点是它支持的查询语言非常强大，其语法有点类似于面向对象的查询语言，几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立**索引**。

**MongoDB\****概念解析**

不管我们学习什么数据库都应该学习其中的基础概念，在mongodb中基本的概念是文档、集合、数据库，下面我们挨个介绍。

下表将帮助您更容易理解Mongo中的一些概念：

**SQL\****术语***\*/\****概念**

**MongoDB\****术语***\*/\****概念**

**解释\****/***\*说明**

database

database

数据库

table

collection

数据库表/集合

row

document

数据记录行/文档

column

ﬁeld

数据字段/域

index

index

索引

table joins

表连接,MongoDB不支持

primary key

primary key

主键,MongoDB自动将_id字段设置为主键

通过下图实例，我们也可以更直观的了解Mongo中的一些概念：

**数据库**

一个mongodb中可以建立多个数据库。

MongoDB的默认数据库为"db"，该数据库存储在data目录中（**安装时，可以默认，可以指定，但是必须该目录是存在的**）。

MongoDB的单个实例可以容纳多个独立的数据库，每一个都有自己的集合和权限，不同的数据库也放置在不同的文件中。

**"show dbs"** 命令可以显示所有数据的列表。

show dbs local 0.078GB test 0.078GB > " src="data:image/png;base64,R0lGODlh4AKxAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAwAEwDJAGwAhQAAAA0LGxQLDRsLGxsLDQ0LFBQTDRQLGxsLFBQLFA0ZJw0TIRsZJxQZJxQhJxQhLRsnMxsnLRsnJxshJyETDScZDSETGyEZIS0hFDMnGy0nGyczMzMzMzMtISctIS0hISczLS0zMzMtLS0zLTMzJyEhJzMzLSczJyEtMy0zJyEtJyctLS0tISEnMyEtLS0tLTMnJycnJycnMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIAQ4KAMj8jk8IFROp/QqHRKrVqv2CwysOFwOgJswBPWms/otHothWCKWkgF8PhsMg/OXOHVAwIgFyEcTQALgyIjRgB8XnNskJGSaQokJYtXCxpCDyQDGxhMh3smFFxglQRcpEajjKWTsbKzT4dgWBGLTAEnBEwKtwUoFYCtKQSpfyBGwGHCj7TR0pISDLdVmksYvL4YzQDPxYbHyeLfz9Pp6loFBgASA71OhxlIudrcoiGs4gvHrg9guVIAa53Bg1Eg9IGWhN4RBfXw9WLCaKEyYwQqcviwTEgjPwhDikQiIWOVAipMsvGncqTLl1koqhHmpSDMmzhz6tzJs6fP/59AgwodSrSo0aNIkypdyrSp06dQo0qdSrWq1atYs2rdyrWr169gw4odS7as2bNo06pdy7at27dw48qdu04cFC5eCuElpI5LoaJcIkK4tmYvX8MMpdht81eIm6d5ONhsOGiyEoUcSKhacSzAChZhPs4BJIivIUSKNPoR59cc4SSRVwcaVIgmh4iOGwN4/OTBNYq26/H6dJuTxUOOhogWQpr2EIdSfJfZ0pFlb81DxrSooOADmYGlTglIteoVBfCmOtLpcKDjA9xJuB0Rn+wxutxIeDsRhzKj/WFcaJaNfEjch94fG6CC3WnwPaEfEsncF98GDI3RQAYRWEDGOQBWR/8OdsVwSIx6wBzQhWkOFsech6qcmNcQD+6mWxIQ1AMRgn0QQiCMKg5xn4gXjdNSFDTNqFwwKBipTAleCOeBiextiOSIGJWzjIh2vSdOJ0PCdhiL5dGoW4wNpYCAC0aEyZw8Xv7145RBWneFdEpEOMx+d4ADIBlujCEAev38sw8dpQBa3T6sqQfFjYH69xp+R5BJ4wu4DTbdjhDiZuCgBKXJohDQRcElg9l9SiqMTYRDBnOrLteoRhwxYxFiODZZyyCZZfSqbSjGKCmEIOnZxzZsgorrgnpCs1ycxxjbYH41LRLqaZJhMu1eSmYhJ11U2WYZt+CGK+645JZr7rnopqv/7rrstuvuu/DGK++89NZr77345qvvvvz26++/AAcs8L9+DVyXopIshobCVDBcFK97zDrbl6XVhoKtwjqCmRcC4gpNc6YhlwgmkeaI3bKnOrFxrhlHFNscKyNLlKXPcRpeguNpRh92/8H8GsMS7twiK/spemDKRZPcM6ZBIqUmI1M2KrWLOlJIHcnJlloliAjPpyiQVSxmGF8KwefwUE9jCSaYVmfXttYF3rni1rp2DbdHcIat6NNLmHb2UDQby8/aGO32WuBzx/0xiwB9m7jg5jkbhcKIPxTR30Lx2oSrhAuZMV+aO8axSUF/2kisKbKM8rQqj/45BsixLLrs8m5rxvA63mJ9++689+7778AHL/zwxBdv/PHIJ6/88sw37/zz0Ecv/fTUV3885loUgz3v24uxTPfwyiSqxBV7tFABaGa3KmUcwPD9xC5bJG8e2f5RLIIKEoCeHKNkMyE/OFuAIpg2L/qlCDeNEtEvYuANI1kpPUYQRhPKdq9GJAYJBkzglCACDw9MQHeM4BoEs2ac+qUrD89yAqMYZzNTfCYBKohBl04zh4CYwmqdstz8TEgtlr1qORJcz3Qw6IVY8UIGyYmdzNoVBAA7" v:shapes="_x0000_s1738">

执行 **"db\****"** 命令可以显示当前数据库对象或集合。

db test > " src="data:image/png;base64,R0lGODlh4AKcAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAwAEgDJAGkAhQAAAA0LGxQLDRsLGxsLDQ0LFBQTDRQLGxsLFA0ZJw0TIRsZJxQZJxQhJxQhLRsnMxsnLRsnJyETDScZDSETGyEZIS0hFDMnGy0nGyczMzMzMzMtISctIS0hISczLS0zMzMtLS0zLTMzJyEhJzMzLSczJyEtMy0zJyEtJyctLS0tISEnMyEtLS0tLTMnJwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIAQ0JAMj8jk0GFROp/QqHRKrVqv2CwykNFoNgJsgBPWms/otHotfViK2scE4OhkLg7NPOHVAwIeFR8aTQAKgyAhRgB8XnNskJGSaQkiI4tXChhCDiIDGRZMh3skElxglQRcpEajjKWTsbKzT4dgWBCLTAElBEwJtwUmE4CtJwSpfx5GwGHCj7TR0pIRC7dVmksWvL4WzQDPxYbHyeLfz9Pp6loFBgARA71OhxdIudrcoh+s4grHrg5guUoAa53Bg1Ee9IGWhN6RBPXw9WLCaKEyYwQqauiwTEgjPwhDikQSIWOVAihMsvGncqTLl1koqhHmpSDMmzhz6tzJs6fP/59AgwodSrSo0aNIkypdyrSp06dQo0qdSrWq1atYs2rdyrWr169gw4odS7as2bNo06pdy7at27dw48qdS7fuFXFQuHgppJeQOi6FinKJ+ODamr5+ETOUgrdNYCFunubRYLPhoMpKFGoQoSrFsQApVIT5OAeQIL+GECnS6EccYHOGk0xuHWhQIZoaIkJ+DCDyEwfXKOKux+tTbk4WDzkaQlqIadtDHEoBXmZLR5a/OQ8Zs2JCgg5kBpY6JSDVqlcSxJvqSGfDgY4OdCfhdoR8ssjodiPx7UQcyoz4DcMFZ9nQh0R+6v2RASrapSbfE/whkUx+82XA0BgMXAABBWScI//gdeRoV4yHxLAHzAFdoAbhcc6BqEqKew0RYW+8JfFAPRAp2AchBsrI4hD5kXjROC1FQVONzAVjApLKjOAFcRyg6F6HSpaIUTnLkIhXfOJ0UqRsibl4no28zdjQCQiwYMSYzskDZmBBVjkkdldQp8SEw/R3BzgCkuHGGAKo188/+9BRiqDX7eMae1DkOCiAsel3hJk2tqBbYdX1KKFuCBZK0JouCiFdFF46uF2opsrYRDhkONdqc49qxBEzFimm45O1DLJZRrHipuKMlEoIEp99bOOmqLo2yCc0zc15DLIP7lfTIqOmRhkm1fbFZBZ02iUVbph5K+645JZr7rnopqv/7rrstuvuu/DGK++89NZr77345qvvvvz26++/AAcs8MBnAEbwX4xK0hgaC1PRcFG+7lFrbWGedpsJuBLriGZeEKgrNM+hplwimEy6o3bNpuoEx7tqHNFsc7CsLFGYRufpeAuWx5l92gUYc2wNU8jzi6z0x2iCKhtdss+aDokUm4xU+ejUMPJooXUlL3vqlSImXB+jQlbRGGJ+KSTfw0NBraWYYl69ndtbH5hni1zz6nXcHskpNqNQL4Ea2kPVjCw/bGPUW2yC0y03yC4CFK7ig6MHbRQLJ/5QRIAL5WsTsBZOpMZ+bQ5ZxyYJHWojs67YcsrVrkw66BYo1/Los8vbVe3B6oCbNe689+7778AHL/zwxBdv/PHIJ6/88sw37/zz0Ecv/fTUV2/9vIBQcPf1eXmg/e7PyxSFci54T3G0zeexrYKkUJCzAqtNr74TWH7P5/rGBwEAOw==" v:shapes="_x0000_s1739">

运行"**use**"命令，可以连接到一个指定的数据库。

use local switched to db local > db local > " src="data:image/png;base64,R0lGODlh4AKFAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgCMAEYAhQAAAA0LGxsLDQ0LFBQLDRsLGxQLFA0TIQ0ZJxsTIRQTIRsnMxQhLRsnJxshJyETDScZDSEZIS0hFDMnGy0nGyczMzMzMyczLS0zJyEtMyczJy0zMzMzLTMtITMzJzMnISEtLSctITMnJy0zLScnJyctLS0tISEtJy0hIScnMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj0hjoGKxSJJQ4rL5jFqv2Kx2G11UuUYveEwuMr7JwOUBOGAEAEbTAhEOMs1J94uY1wF3fnFzaGJlh2NyaEprbW8BGnBEYnd/SIYHG3UIHGwLHQRHCB6SAIaIqFuKSGpsbnALFnpCU4RQhgiggBkQS5a0TBakQ6epxlZ9dEetjqWrvli4upXQQ9CvxIvH20Rys6xrS8NDCLOfoVaYmgCcnrq0jaOlxdz1Z1exHglvmU3jgVRu8RG0y5YcCx8yjKNXr6HDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJ0+IS/21QmGERWq8V0TTBgGqppXRoozRPrxwtMnVpuKjpmm4Rc5DOABBsaIVQsKHJn1hzhgGkoyZC2SoAZ/UTgXXSHGFwkilDMveqW1lZyRFcW6drIQmQSpmCkIkxBTu8rGE9Z60CKHmmnlCrsKkT1LBt1rVbxpldpyWgDowAfalKY9Pu0BXBLERMrG9nEJCQgABuZHigqwFvVosK5qqSQecKVUnUMKON7mjNLGT5rl6lpQQbR13IqnINCoRwELb5cGC/mL0Szu55XSlRrZuf7f4Bs/mtq0/jxf7am2xElANAACWYYMAJJEgy31GUDYdNg6EV5hk4oL02mhGvMXBaaRcmoalOZ7El1948EvSD1y5PMPAOfmiphQcV6v23VopNoPCeXSfq9YsZNYajQQpmBVbdYC86MQhCCknCUE9MNunkk1BGKeWUVFZp5ZVYZqnlllx26eWXYIYp5phklmnmmWimqeaabLbp5ptwegQda3FaMeeb91jRl30X/PWNmqsgBSJqBKhGZ5qBGnHcVWxI52YyO1ZX332/AQpYEhluCOKaeUZxkI32/RjkmkEAADs=" v:shapes="_x0000_s1740">

以上实例命令中，"local" 是你要链接的数据库。

在下一个章节我们将详细讲解MongoDB中命令的使用。

数据库也通过名字来标识。数据库名可以是满足以下条件的任意UTF-8字符串。不能是空字符串（"")。

不得含有' '（空格)、.、$、/、\和\0 (空字符)。应全部小写。

最多64字节。

有一些数据库名是保留的，可以直接访问这些有特殊作用的数据库。

**admin**： 从权限的角度来看，这是"root"数据库。要是将一个用户添加到这个数据库，这个用户自动继承所有数据库的权限。一些特定的服务器端命令也只能从这个数据库运行，比如列出所有的 数据库或者关闭服务器。

**local:** 这个数据永远不会被复制，可以用来存储限于本地单台服务器的任意集合

**conﬁg**: 当Mongo用于分片设置时，conﬁg数据库在内部使用，用于保存分片的相关信息。

**文档**

文档是一组键值(key-value)对(即 BSON)。**MongoDB** **的文档不需要设置相同的字段，并且相同的字段不需要相同的数据类型，这与关系型数据库有很大的区别，也是** **MongoDB** **非常突出的特点。**

一个简单的文档例子如下：

下表列出了 RDBMS 与 MongoDB 对应的术语：

**RDBMS**

**MongoDB**

数据库

数据库

表格

集合

行

文档

列

字段

表联合

嵌入文档

主键

主键 (MongoDB 提供了 key 为 _id )

数据库服务和客户端

Mysqld/Oracle

mongod

mysql/sqlplus

mongo

需要注意的是：

1. 文档中的键/值对是有序的。
2. 文档中的值不仅可以是在双引号里面的字符串，还可以是其他几种数据类型（甚至可以是整个嵌入 的文档)。
3. MongoDB区分类型和大小写。
4. MongoDB的文档不能有重复的键。
5. 文档的键是字符串。除了少数例外情况，键可以使用任意UTF-8字符。

文档键命名规范：

键不能含有\0 (空字符)。这个字符用来表示键的结尾。

.和$有特别的意义，只有在特定环境下才能使用。以下划线"_"开头的键是保留的(不是严格要求的)。

**集合**

集合就是 MongoDB 文档组，类似于 RDBMS （关系数据库管理系统：Relational Database Management System)中的表格。

**集合存在于数据库中，集合没有固定的结构，这意味着你在对集合可以插入不同格式和类型的数据，但** **通常情况下我们插入集合的数据都会有一定的关联性。**

比如，我们可以将以下不同数据结构的文档插入到集合中：

当第一个文档插入时，集合就会被创建。

**合法的集合名**

集合名不能是空字符串""。

集合名不能含有\0字符（空字符)，这个字符表示集合名的结尾。集合名不能以"system."开头，这是为系统集合保留的前缀。

用户创建的集合名字不能含有保留字符。有些驱动程序的确支持在集合名里面包含，这是因为某些 系统生成的集合中包含该字符。除非你要访问这种系统创建的集合，否则千万不要在名字里出现

$。

如下实例：

**capped collections**

Capped collections 就是固定大小的collection。

它有很高的性能以及队列过期的特性(过期按照插入的顺序). 有点和 "RRD" 概念类似。

Capped collections 是高性能自动的维护对象的插入顺序。它非常适合类似记录日志的功能和标准的collection 不同，你必须要显式的创建一个capped collection，指定一个 collection 的大小，单位是字节。collection 的数据存储空间值提前分配的。

Capped collections 可以按照文档的插入顺序保存到集合中，而且这些文档在磁盘上存放位置也是按照插入顺序来保存的，所以当我们更新Capped collections 中文档的时候，更新后的文档不可以超过之前文档的大小，这样话就可以确保所有文档在磁盘上的位置一直保持不变。

由于 Capped collection 是按照文档的插入顺序而不是使用索引确定插入位置，这样的话可以提高增添数据的效率。MongoDB 的操作日志文件 oplog.rs 就是利用 Capped Collection 来实现的。

要注意的是指定的存储大小包含了数据库的头信息。

在 capped collection 中，你能添加新的对象。

能进行更新，然而，对象不会增加存储空间。如果增加，更新就会失败 。

使用 Capped Collection 不能删除一个文档，可以使用 drop() 方法删除 collection 所有的行。删除之后，你必须显式的重新创建这个 collection。

在32bit机器中，capped collection 最大存储为 1e9( 1X10的9次方)个字节。

**元数据**

数据库的信息是存储在集合中。它们使用了系统的命名空间：

在MongoDB数据库中名字空间 .system.* 是包含多种系统信息的特殊集合(Collection)，如下:

**集合命名空间**

**描述**

dbname.system.namespaces

列出所有名字空间。

dbname.system.indexes

列出所有索引。

dbname.system.proﬁle

包含数据库概要(proﬁle)信息。

dbname.system.users

列出所有可访问数据库的用户。

dbname.local.sources

包含复制对端（slave）的服务器信息和状态。

对于修改系统集合中的对象有如下限制。

在{{system.indexes}}插入数据，可以创建索引。但除此之外该表信息是不可变的(特殊的drop index命令将自动更新相关信息)。

{{system.users}}是可修改的。 {{system.proﬁle}}是可删除的。

**MongoDB** **数据类型**

下表为MongoDB中常用的几种数据类型:

**数据类型**

**说明**

**解释**

**举例**

String

字符串

UTF-8 编码的字符串才是合法的。

{“v”:“kkb”}

Integer

整型数值

根据你所采用的服务器，可分为

32 位或 64 位。

{“v”:1}

Boolean

布尔值

用于存储布尔值（真/假）。

{“v”:true}

Double

双精度浮点值

用于存储浮点值

{“v”:3.14}

**ObjectID**

对象

ID

用于创建文档的ID

{“ id”:ObjectId(“123123”)}

Array

数组

用于将数组或列表或多个值存储为一个键

{“arr”:[“a”,“b”]}

Timestamp

时间戳

从开始纪元开始的毫秒数

Object

内嵌文档

文档可以作为文档中某个key的

value

{“o”:{“foo”:“bar”}}

Null

空值

表示空值或者未定义的对象

{“v”:null}

Date

日期

日期时间，用Unix日期格式来存储当前日期或时间。

{“date”:new Date()}

Regular

正则表达式

文档中可以包含正则表达式，遵循JS语法

{“v”:/kkb/i}

Code

代码

可以包含JS代码

{“x”:function(){}}

File

文件

1、二进制转码(Base64)后存储(<16M) 2、GridFS(>16M)

GridFS 用两个集合来存储一个文件：fs.ﬁles与fs.chunks

下面说明下几种重要的数据类型。

**ObjectId**

ObjectId 类似唯一主键，可以很快的去生成和排序，包含 12 bytes，含义是：

前 4 个字节表示创建 **unix** 时间戳,格林尼治时间 **UTC** 时间，比北京时间晚了 8 个小时接下来的 3 个字节是机器标识码

紧接的两个字节由进程 id 组成 PID

最后三个字节是随机数

MongoDB 中存储的文档必须有一个 _id 键。这个键的值可以是任何类型的，默认是个 ObjectId 对象

由于 ObjectId 中保存了创建的时间戳，所以你不需要为你的文档保存时间戳字段，你可以通过

getTimestamp 函数来获取文档的创建时间:

var newObject = ObjectId() > newObject.getTimestamp() ISODate("2017-11-25T07:21:10Z") ' src="data:image/png;base64,R0lGODlh4AJYAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgDGACwAhQAAAA0LGxsLDQ0LFBQLDRsLGxsLFBQLFA0ZJw0TIRQhLRsnMxshLRsnJxshJyETDScZDSETGyEZIS0hFDMnGy0nGyczLTMzLTMzMzMzJyczMyEtMzMtISczJy0zMyEtLSctIS0zJzMnJyEtJy0nJy0zLScnJy0hIS0tLSctLS0tITMnIScnMzMtLSEhJy0tMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj8ikclkMWB5HxAXKrFqVTqpRqr1es0UFBoLATLzotHeBwWQEQrDaKJ8P2W543T6sBzRteUR1AxtnQwoUQgqHSgocBItnYm1nAR0FgIpHThIeZkKFbRQDH1oBIAybCJAAnZ+KooIAspuyY3htb3aJQqwErxi2G7pwtMTCoci4gbMJIcYACZ8iT4ibRAuHYo2c1gMj0QAIb39vCRVIf5DkcNrHEAsQ0/MkGhBx964akAklWs+MvSuEb0GrPtYcNQM1SN8+COsI+OsSUMjADQUPxkk4pOLDcVOU4SvyCxHDIwsUIdj0p9glcd6gBGwZaAIjBCYmIDiRkKCr/2+GOkLb12zCn5EIu6iRQxBMoW4VaVY62oSjEI/tflLZc3WorzZIizwzYOrjzA4w6Vib6dAXhQYFQDiI4JCqU4xC9bT9OMjqEUo1m9S9dzds1L1U+yrFuksOV2lDxWBLsgAFS2vtXi4BU9FgpDgpVBwYYUJAr3GtqHLJa1EjAM+K+SxaBUl1SNavXcNOKtYrPQAKbvskCYmRlTJIKa3YkKEA2s1rh96a8BR4Kzy7XHVgAZaIx+nHAr8uxge7nu3dvXsFD358Hqm7KPHUcjpbN9n4829xrb8/kZL+KTGcWwEWaEcZ6RmoH4JjKKgEG2Qk4+CEFFZo4YUYZqjhhhx26P/hhyCGKOKIJJZo4okopqjiiiy26OKLMMYo44w01mjjjTjmqOOOPPbo449ABinkkEQWaeSRSCap5JJMNunkk1BGKeWUVFZp5ZU4PgaSUin+cR+JYkT4JYjmadWflmkwtUwb/OGHphrVXeOFcdB5wlAtpZySCm3AWGBnLMhkV4sygRTUTHZGTINBC/+El0wuznglzScNAneCBhSE6egmgBkaSHziKXBppmM44cIochbxzhXcYMEPAVldFM88HtQzGESvToSbrLl9xlsStkHBK1ceDTGcAs1pYNMZsmoW2xbxJbvsH4roOuAQALJ6UlUyQSOVGTfltFNPGN3VyGFFJfbeLBJZZfGtJX4VK5IkLzHy7niT7UFTfEahZS9Q8HIpbxUIhsUbW2GtBJdcdI10VGGsqcvXulE09oTEZqoH07H9mmbUXiYd4oc+Fdnrr1FA4fPYwApNpla37mgUQGijlXbaL8Huqht/e0yDzW/BCesasZLOC1zHxu1G0mWnYMZvvR/js9q1vrSJBJ1YRAfHdNU9EkmZl3BXaVfGsLdMI2X6/F8b8znKUJmQbYzX0VC7PYGis7gHKgbLvWGyx068kGB9d4zZYrYGsjzjY9euJCODBusny21Z+nXHGGW4zEQQADs=" v:shapes="_x0000_s1756">

ObjectId 转为字符串

newObject.str 5a1919e63df83ce79df8b38f" src="data:image/png;base64,R0lGODlh4AJBAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQBpAAoAhQAAAA0LGxsLDQ0LFBQLDRsLGxQLFA0ZJw0TIRsnMxQhLRshLRsnJxshJyETDScZDSETGyEZIS0hFDMnGy0nGyczLTMzLTMzMzMzJy0zMyEtMyEtLSctITMtISczJyczMy0nJy0zLS0zJycnJy0hISctLS0tISEtJycnMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj0hjoOI4HizNpHRKrVqvgMTlghEAllFsEiz+MpMKCXUZyVzUgIFmOxlswgHOYiI8dAhsbnxyW11ChBd8cXNbD1lbhV5HCpCOWpBdAR4FH4kAlHBIAR9/B4YJanIPCQ8IGa0gH45fsqN/CCFhCCKSqIuWf0VkYx6SQ8O0XQgUQ6CiZ7sCo5BvaQcjEgckZ79kcqHRtNQSo7NEyEhaikLImsZDB41HZNHlRAcTDAUcDRCy7LXOqBoSzt6xf8K4TXFmBk8xIpTWzYPGK0swdiVMGDgxQoACRX4A/Xuiq6JFAkQSXDymEIArifAUtXsoJM0ail4Qvfn2KdglJ0OaUMgjaFJnKkZvhvz08pKgGy7GlrorQ7VMyKpYs2qtEm/o1q9BAAA7" v:shapes="_x0000_s1757">

**字符串**

**BSON** **字符串都是** **UTF-8** **编码。**

**时间戳**

BSON 有一个特殊的时间戳类型用于 MongoDB 内部使用，与普通的 日期 类型不相关。 时间戳值是一个 64 位的值。其中：

前32位是一个 time_t 值（与Unix新纪元相差的秒数） 后32位是在某秒中操作的一个递增的 序数

在单个 mongod 实例中，时间戳值通常是唯一的。

在复制集中， oplog 有一个 ts 字段。这个字段中的值使用BSON时间戳表示了操作时间。

BSON 时间戳类型主要用于 MongoDB 内部使用。在大多数情况下的应用开发中，你可以使用

BSON 日期类型。

**日期**

表示当前距离 Unix新纪元（1970年1月1日）的毫秒数。日期类型是有符号的, 负数表示 1970 年之前的日期。

- var mydate1 = new Date() //格林尼治时间
- mydate1

ISODate("2018-03-04T14:58:51.233Z")

- typeof mydate1 object
  - var mydate2 = ISODate() //格林尼治时间
  - mydate2

ISODate("2018-03-04T15:00:45.479Z")

- typeof mydate2 object

var mydate1str = mydate1.toString() > mydate1str Sun Mar 04 2018 14:58:51 GMT+0000 (UTC) > typeof mydate1str string " src="data:image/png;base64,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" v:shapes="_x0000_s1760">

这样创建的时间是日期类型，可以使用 JS 中的 Date 类型的方法。返回一个时间类型的字符串：

或者

Date() Sun Mar 04 2018 1 " src="data:image/png;base64,R0lGODlh4AJCAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgB3ACEAhQAAAA0LGw0LFBsLDRQLDRsLGxQTDRQLGw0ZJw0TIRQZJxQhLRsnMxsnLScZDSETDSETGycZFCEZIS0hFDMnGy0nGzMzMyczMyczLSEtMzMzJzMtIS0zJy0zMzMnITMzLSctLS0tISczJychISctISEnMy0hIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj8gkYGFxICwTpXRKrVqNgYtlG0UGMI+pINMVLijXtNq6KCe/4WyUyQUwtlvNADDeos1/RAxua4WGZlBKcGYbBEMIegCLQ4N8GQ6WmEUIjYeeh0yEQ5OcBFl5e5OSWnhRqkMJHHuftGlPTUeTZ1mYsalgoxeao8BFvrXIU0yBucAJHQ6LkL9hlJ1DY8OP18ndRW1Tp1uadB4ZkXcWkX11S8xCld7y81bZRAjv9Pr7RXdOFvn4CRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkxiftSqU5Q8DboVUfqhmJhKSdDOFyKTJwKYQqXGiBOl5tcjXFwkdEmEBISsAiBCO6CQKIKKAloA/nclC5LOIAAOMTGkdwGTEVkq9zn6zYHbAGDdFm15oNA0LiRJOTJBw9GjoBT0JKiSpaw/fMSlnABC+pFOt4kbgkhxO52ps3FlEApBQQKEBhL2rUFHFPLjT28AADidZ0KmUpS6qQ+eU7JjX5ct2D8w9sJdX6qYiSCNZHCEDHguzj8RTvI7xb8xFodEeEAQAOw==" v:shapes="_x0000_s1761">

**SQL\****术语***\*/\****概念**

**MongoDB\****术语***\*/\****概念**

**解释\****/***\*说明**

database

database

数据库

table

collection

数据库表/集合

row

document

数据记录行/文档

column

ﬁeld

数据字段/域

index

index

索引

table joins

表连接,MongoDB不支持

primary key

primary key

主键,MongoDB自动将_id字段设置为主键

总结起来，RDBMS和NoSQL的区别在于三点：事务、表关联、表结构约束

**RDBMS**

**NoSQL**

有事务

无事务（性能提升）

有表关联

数据和数据之间没有关系

有表结构约束

key-value结构，没有约束

举例：

RDBMS中的User表（字段先要预定义）：

MongoDB中的User文档（字段不需要预定义）：

**MongoDB\****安装和常用命令**

**安装**

MongoDB 提供了 linux 各发行版本 64 位的安装包，你可以在官网下载安装包。下载地址：https://www.mongodb.com/download-center#community

下载完安装包，并解压 **tgz**（以下演示的是 64 位 Linux上的安装） 。

wget https://fastdl.mongodb.org/linux/mongodb-linux-x86\_64-rhel70-3.6.11.tgz tar -xf mongodb-linux-x86_64-rhel70-3.6.11.tgz

mv mongodb-linux-x86_64-rhel70-3.6.11 mongodb cd mongodb/bin

\# 一定注意要创建数据目录，默认路径/data/db mkdir -p /data/db

配置环境变量（**可以不配置**）：

vim /etc/proﬁle

source /etc/proﬁle

**启动**

通过配置文件方式启动

说明：

-f是--conﬁg的缩写

\#数据库文件位置dbpath=/root/mongodb/singleton/data #日志文件位置

logpath=/root/mongodb/singleton/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行

fork=true

\#绑定客户端访问的ip 0.0.0.0 不绑定ip bind_ip=192.168.10.135

\# 默认27017

port=27017

配置文件，需要手动创建，参考以下配置即可。创建mongodb.cfg配置文件

创建数据和日志目录：

通过配置文件方式启动：

**连接客户端**

mongo 192.168.10.135:27017

## 常用客户端

自带命令行客户端Navicat for mongodb MongoVUE

Studio 3T Robo 3T RockMongo

**常用命令\****创建数据库***\*语法**

MongoDB 创建数据库的语法格式如下：

如果数据库不存在，则创建数据库，否则切换到指定数据库。

**实例**

以下实例我们创建了数据库 kkb:

use kkb switched to db kkb > db kkb > " src="data:image/png;base64,R0lGODlh4AKFAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgCAAEYAhQAAAA0LGxsLDQ0LFBQLDRsLGxQLFA0TIQ0ZJxsTIRQTIRQhLRsnMxsnJxshJyETDScZDSETGyEZIS0hFDMnGy0nGyczLS0zJzMzMyEtMy0zMyczJzMtISczMy0zLScnLS0tLTMzJzMzLScnMyEtLSctITMnJycnJyctLS0tISEtJy0hIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj0hjwPIgLpvDZ3JKrVqv2CxVGmU6vdqw+LqYVKWHiwCwwLghwkHGTUGiNZB73iLRYOpjgYJDbWZJaGoBG2tEDGZycEpMCBwEAE+UlgEdlQceUIOhYoV2XmlrDH9RHW5uhkVLH4CXFrJdTXKvortXCG9HiIxsGGabkYcdICGMm8rMXpC80lRts8BMm8tECIAMlVOY2uFrxgAIIqDT6oS6SakhCWoHfhjaAHKt7UJSbXtN/YpG/FpHsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzAxbtIHDowVLuue4Dxkk1a6/52CgMLquYXol3RadBqV9LMn0DZvBpAAFaCEAnqRUrXShu/Nkj7E4sxRBWAeBhNLAWh1o83XwCNm0T746sfa0Cbz/JXFQ1eVImFqIeQ9UEHssZ3eLO3jRACBNkf3MuTpAOcc0qND8ppDB4zy5rmMPV3eN+kbJtOhP6kly2YCghOuDUUjTdXzrbKJWOVzTC6tzyGZIh/bJg4btAw0adkivfx3LkJhuTUoUMIBlNm/Fw8PVg548dG0gX/DTrz33OPDnSSz14w9+m11AqBIYUDFCUbYEX8Lf0rt/r1wLMBZKaBoZhlceLCBTjkHXvMAbz5ByCA6ZtWzxnML7EeeVlyNRZJMMJHlMwwGK/i2moXmtJIeEVCVONcGAmGw4m0AeQEQjG/FpOOOPPbo449ABinkkEQWaeSRSCap5JJMNunkk1BGKeWUVFZp5ZVYZqnlllyqtEQEJnb5ZZhKlmFFXBaACZZdTpKCTGUiRJAaeEy6aYSEaR6XHJNuzYgnmLhIJmU1U2gmoJxw0omkmVW0mCaOMkoZBAA7" v:shapes="_x0000_s1782">

如果你想查看所有数据库，可以使用 **show dbs** 命令：

show dbs admin 0.000GB local 0.000GB > " src="data:image/png;base64,R0lGODlh4AJuAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQBqACgAhQAAAA0LGw0LFBQLDRsLDRsLGxQLFA0ZJw0TIRQhLRsnMxsnJxshJyETDScZDSEZIS0hFDMnGy0nGyczLTMzMy0zMyEtLSctITMnJyczJyczMzMtITMzJzMzLS0zLS0zJyEtMycnJyctLS0tISEtJy0hIScnMzMnIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaA5OGcclkIhvPpnRKrVajVusTm+1aE5BqgkJ2AJCPCiUMOJApDoFFKQxcBkuEGpNEqyNCY2VehENjbE4ZBEQBGhsDBxwEemYHHQ0KDpQIEkuNlZeNjwgeUIqFqESHTQoUgHVJAAgfBAePAAIgDmAHIRAHiESRi1uxuWGtr6mFbnBSq1Gztbe5DgcRCwUXDHRFw2d9xrqGa8tdY8pT1+BK0pRtoSIjBiQhi0bvCaEaoN1t6eaeBWOih4wkdrJotXlj5hiABLeWCCrRJ4OJQQUpHAzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fP6p9AgwodSrSo0aNIkypdyrSpUy9cjmgY+FRK1JUFLzXJSoerEK+4QDBsxDDQGzYF+TRIUEJDhDGa1DgDl6bcsmgKj8SShpdA30bpotjCM2TYJ3hrORSYCkZItTqOIG1E9e2xkcq6MFs7WA0iYVhKErwiq7HWwSdgAihqHNYMQmnLBoel2oYaiF+2ceM5pgBQQQik2RyW9g018NVsLPeNzXnc5eabF1XT7FmIAuCxEkgSLCnfpdTIHTtfnmq5HmV/99L669Y6diVRBJ0AIWliEvAEWCtXfy8V2PNE/KeGVrIMSEcuZ5HmCgBBAAA7" v:shapes="_x0000_s1783">

可以看到，我们刚创建的数据库 kkb 并不在数据库的列表中， 要显示它，我们需要向 kkb 数据库插入一些数据。

db.mycollection.insert({"name":"开课吧"}) WriteResult({ "nInserted" : 1 }) > show dbs local 0.078GB kkb 0.078GB test 0.078GB > ' src="data:image/png;base64,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" v:shapes="_x0000_s1784">

**删除数据库**

**语法**

MongoDB 删除数据库的语法格式如下：

删除当前数据库，默认为 test，你可以使用 db 命令查看当前数据库名。

**实例**

show dbs local 0.078GB kkb 0.078GB test 0.078GB " src="data:image/png;base64,R0lGODlh4AJvAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQBqACkAhQAAAA0LGw0LFBQLDRsLDRsLGxQLFA0ZJw0TIRQhLRsnMxsnJxshJxsnLSETDScZDSEZIS0hFDMnGy0nGyczLTMzMy0zMyEtLSctITMnJyczJyczMzMtITMzJzMzLS0zLS0zJyEtMycnJyctLS0tISEtJy0hIScnMzMnIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaAxSHcclkIh3PpnRKrVajVusTm+1aE5FqokJ+AJAQSyUMOJArD8FFKQxgBkuEOpNEqyVCY2VehENjbE4aBEQBGxwDBx0EemYHHg4KD5QIE0uNlZeNjwgfUIqFqESHTQoVgHVJAAggBAePAAIhD2AHIhEHiESRi1uxuWGtr6mFbnBSq1Gztbe5DwcSCwUYDHRFw2d9xrqGa8tdY8pT1+BK0pRtoSMkBiUii0bvCaEboN1t6eaeBWOih4wkdrJotXlj5hiABLeWCDLRR8OJQQUrHAzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fP4J9AgwodSrSo0aNIkypdyrSp06cnG5EZSEhqOacKqKLKyrHgpSZelVgdZJVNLoZjzQzhKqSZM1whGD58E4ztsmgKj8SSRoSTkEwJCTRKF8UWnr9s3llSoiCit40A7Kb6Vm0J5XF1TrV5BOahY4QJ0rE1DPfBpyJWIUtGRdqhkdYhEDXoJvWrAkAFI5QtMpqartNDTvNFHPCy2seLKrdBFM3CrohZoyRQndg5PMaOC1MPiHeRHmXdz2g+w6/0YMRRuNh1q/bs1LkVUITYuBpVWCHf+6r5+s8bXSHurTEWIEEAADs=" v:shapes="_x0000_s1791">

以下实例我们删除了数据库 kkb。首先，查看所有数据库：

接下来我们切换到数据库 kkb：

use kkb switched to db kkb > " src="data:image/png;base64,R0lGODlh4AJXAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgCAACMAhQAAAA0LGxsLDQ0LFBQLDRsLGxQLFA0TIQ0ZJxsTIRQTIRQhLRsnMxsnJxshJyETDScZDSEZIS0hFDMnGy0nGyczLS0zJzMzMyEtMy0zMyczJzMtISczMy0zLScnLS0tLTMzJzMzLScnMyEtLSctITMnJycnJyctLS0tISEtJy0hIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj0hjoPIgLpvDZ3JKrVqv2CxVGmU6vdqw+LqQVKUHiwCwuLghwgHGPUGiM5B7vhLJXOpjgYJDbWZJaGoBGmtEDGZycEpMCBsEAE+UlgEclQcdUIOhYoV2XmlrDH9RHG5uhkVLHoCXFbJdTXKvortXCG9HiIxsF2abkYccHyCMm8rMXpC80lRts8BMm8tECIAMlVOY2uFrxgAIIaDT6oS6SakgCWoHfhfaAHKt7UJSbXtN/YpE/FpHsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzAxbtIHDowVLuue4Dxkk1a6/52CgMLquYXol3RadBqV9LMn0DZvBowAFYCEAnqRUrXShu/Nkj7E4sxRBWDehRJLAWh1o83XwCNm0T746sfa0Cbz/JXFQ1eVImFqIeQ9QEHssZ3eLO3jRACBNkf3MOThAOcc0qND8ppDB4zy5rmMPV3eN+kbJtOhP6kly0YCAhOuDUUjTdXzrbKJWOVzTC6tzyGZIh/bJg4bNAw0adkivfx3LkJhuTUoQMIBlNm/Fw8PVg548dG0gX/DTrz33OPDnSSz14w9+m11ApxAYSCFCUbYEX8Lf0rt/r1wLMBZKaBoZhlceLCBTjkHXvMAbz5ByCA6ZtWzxnML7EeeVlyNRSNMMJHlM8wFKvi2moXmtJIeEVCVOJcGAl2w4m0AeQEQjL8EAQA7" v:shapes="_x0000_s1792">

执行删除命令：

db.dropDatabase() { "dropped" : "kkb", "ok" : 1 } ' src="data:image/png;base64,R0lGODlh4AJCAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgDcACEAhQAAAA0LGw0LFBsLGxsLDRQLDRQLFA0ZJw0TIRsTIRQhLRsnMyETDScZDSEZIS0hFDMnGy0nGyczLTMzMyczMyEtMy0zMzMnJyczJyEhJzMzJzMzLTMtIS0zLS0zJy0hIScnMy0tMzMnIS0tISEtLSchIScnLS0tLQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwOAxIGkmFsCpfOqHRKhVKvRMWkcZg8sOCweEx2Lq1X9DhAmbi/UbVRXh4KKnChAlLv+/sKeVIIFhMXSUgOhXxOhIaIGANtjF1uDQBIGW58UGxwWm8AC25uGgQAoF6YEooTjKmfpJdCd5tDe0YLgn+8vVFau0VslwcbShQcBQgdTHMUxMZspggRAITQx3zLSkl6yUQHpqvNReEEbMnb4KYBGKdFugB3l/NGB9+++fpZqk3m41DuBAPw74w7cN/mBcRj5V4BTG0miFPDptS5bgIhWhTlikhFUl/oIPDwbp/JXpVmlZuICGMFletOGSxJMOHLhQ2s4BpmjeS4/yPPel5kojDoyJLAngT12K3I0ZNQAXWMcg1VtKDFmBBiZM3CJQXRDg6pmpVn2W5VoRRs+tOc2Wjd/g05wHUBPlov7d2NyhdLoCugPkACYUnIVn4TBCsRO1fWqhCFP2655UZEBXGjJJ4CZVnDAAyEJ6OqfDlBIc12KoDUw3VIvL6wY5ukI5tMvbmta+veLYY27zCjuEz9Tby48ePIkytfzry58+fQo0ufTr269evYs2vfzr279+/gw4sfT768+fPo06tfz769+/fw48ufT7++/fv4+9Tam4WDATzCsAUbFAr4B6AfBf4XkoAUCYiKgQNJkeCBczg4YYT6VHQXAiM89OcLH68d4SBfBIKIIRi4xNMgOT9lYaIYKUYoR4y8LCCOFAKQQE47NNnzxQKzOHLIYpJMVclkmdiS5HC1TCVkXD/CZMRhUhwQ5U/XQJElK4sIYaUoUlLxJZBeOqalVwRdOYgFufFTgk9S8HgEY2lgddU01ZB1lTbMsMFnM/EUhU0YVIKxhEPjIIqOMszooyc3DCAqRqGNwBmHWDb2KCZLi9GEqEIuPbBQSBG9sRaLJyFhAleqshqqPp/eJMGqUT01hZ+GWXrFqXLOZVNOLgFLVF48zcVpX2yccGOyywYL66/MauqLrZeeEgQAOw==" v:shapes="_x0000_s1793">

最后，我们再通过 show dbs 命令数据库是否删除成功：

show dbs local 0.078GB test 0.078GB > " src="data:image/png;base64,R0lGODlh4AJuAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQBjACkAhQAAAA0LGw0LFBQLDRsLDRsLGxQLFA0ZJw0TIRQhLRsnMxsnJxshJxsnLSETDScZDSEZIS0hFDMnGy0nGyczLTMzMy0zMyEtLSctITMnJyczJyczMzMtITMzJzMzLS0zLS0zJyEtMycnJyctLS0tISEtJy0hIScnMzMnIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaAxSHcclkIh3PpnRKrR6T1uwzmu0yE5FqokJ+AJAQSyUMOJArD8FFKQxgBkuEOpNEqyVCY2VeVmNsThoERAEbHAMHHQR6ZgceDgoPkwgTS4yUloyOCB9QiYSEhk0KFYB1WAggBAeOAAIhD2AHIhEHh0SQiltYtmGrradWbnBSqVGwsrS2DwcSCwUYDHRFwGd9w7dDqcjMrMmAzrGTbaAjJAYlIopG6gmgG5/abcfjRmBUemQidVPyrM0bM8QAJKC1RJCJPhpODAJYQSC/ixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fP2Z9AgwodSrSo0aNIk4pkRMbXKaZriipwOm4qIYCWmmBVAnUQVDa2DnY1M8SqEGXLaoU4qPCNL7NaXsXqJFeekE1CMAF4xmhflFl48rJRV0mJAobbLAKAm0ygtCXcHtcx1caRv4WBXSlJsM8sYLUPPBWBqphxlc8JjaAOcaiBNqZZFQACGOFrEc/RbokeIrqgYC+Rwal2LLwX71cWcDGcGiVB6cHJ1xlG/Pe5F3SK9BzDfobymXug+wqOwmXxIbRkwzZtWwFFCIumq2y9a2HffH3b3ApRv6YroCAAOw==" v:shapes="_x0000_s1794">

**创建集合**

MongoDB 中使用 **createCollection()** 方法来创建集合。语法格式：

参数说明：

name: 要创建的集合名称

options: 可选参数, 指定有关内存大小及索引的选项

options 可以是如下参数：

**字段**

**类型**

**描述**

capped

布尔

（可选）如果为 true，则创建固定集合。固定集合是指有着固定大小的集合，当达到最大值时，它会自动覆盖最早的文档。 **当该值为** **true** **时，必须指定** **size** **参数。**

autoIndexId

布尔

（可选）如为 true，自动在 _id 字段创建索引。默认为 false。

size

数值

（可选）为固定集合指定一个最大值（以字节计）。 **如果** **capped** **为**

**true\****，也需要指定该字段。**

max

数值

（可选）指定固定集合中包含文档的最大数量。

在插入文档时，MongoDB 首先检查固定集合的 size 字段，然后检查 max 字段。

**实例**

在 test 数据库中创建 kkb 集合：

use test switched to db test > db.createCollection("mycollection") { "ok" : 1 } > ' src="data:image/png;base64,R0lGODlh4AKFAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEwAHAVYAhQAAAA0LGxsLDQ0LFBQLDRsLGxQLFBsLFA0TIQ0ZJxsTIRQTIRQhLRsnMxsnJxshJyETDScZDScZFCEZIS0hFDMnGy0nGyczLS0zJzMzMyEtMzMzJyczJyczMzMtIS0zMyEtLSctITMnJzMzLS0zLScnJyctLS0tISEtJy0hIScnMy0tLTMnISchIScnLQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj8hj4AJJJpdNp3RKrVqv2Kx2y00yKFMoAIERABiZdEQ40KQryEY6vTG330K0GiCf17uAgYKDhIVaaGBPTGNlARxmRA1gbWtKi0OSAJSOkENihqChoqOkXhmJRmJkZnJwQgEdc6dInwCwsmCtRLWlvb6/wFIJe6mLq0OIth2VTrWwzETJtpfB1dbXhmiutEywf0MJrg0eBFK84+VG4a/U2O7v8FJfVHIbCmUIH3SQd2moRn3+9DuVbx8mg/ESKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fP/59AgwodSrSo0aNIi8D6F6adOaeFoPBKGnKqUqiKoljSmkUq1mJcq1ilOkVPhggDQGgNEGKBvrNC+hgcGGHJBH2J+rkqKOKr3Ax/hhE7wrfbhbsZtgGUJdBN4jxz1vz9Rtbco0gR8mW2wEYDs3PkPHUgl+BPpk3LACQYEXYXNc2qWStJvRoCLHIISLT21O60Z05Xd1dOoisPhQQljuf1zHtt6uaMBNyac/zPWHZaE4SmdKS0Ga9N2jB17RwXn8fQh19JFs5BgRAPonDHLhqaqkbPhXifJpw/uO3MqWOdYeEFuFV9R0hznXpJrBOACScYgEIJ/BgIWjr+HYPOELAxIP8bN1rBVhthH6zhoW20fXggJqGp48qCDHL4VmNgMNDifHHN5dgp99mxY41ppPBVjoCZIRhcSOghpG0cqDBYHDpSV1CRB1EZ45VYZqnlllx26eWXYIYp5phklmnmmWimqeaabLbp5ptwxinnnHTWaeedeOap555sgsfRdIkAOshSW6BRiY0GaDAeIIK+EsssgRCKBaKKqgapFn4CA6N+fnRyRCaRLPpUf1iIFw0coIKSKiaiZjUIA6iiAqtxVBRm22H6KFYEXY4UEIsrekHmz3mddobHiEQcCc2qfKASrLFqTCbAlMwoOw1isuqaQC4mptBBBWhIUKl+5NBl6ZPMNjv/xLN0SUstOJGxg61+3IYKWavKrFHbbQTkJtyGjtaBAGfq4qjagGH5Ji5TIqrIbKq+VQIwfUTM1zCKpDUGTYIb+ErBF8ek1cTEF7MKECoR89EixesGWDK/+zW4sjQCfkegJuMqlZ8tl4lm3nQI7/JokE5pVw6ODwc69CzPBLdrgEbjXJcxZfjnxBeczJOJgztHjfR4oE7nT9PkPV2J18z16MQxnCJZxH5+GoygJz07al9qx/BCNs5Moc1M0nbrvPFU8/mt9qZYPzIPGQeolW+yAP4NttJ3D+5U4ZFnWHUSx2izdolnsNb0iPkoNjFwkawsBtxOTWwoESXHxRTEK8dV/zvhLoO+L9U+bmxE4gLMw8cK25Csu8OTs4ihysvjXsnLvDthtPDyEM2kk0iWXvGPqLdM3RlpsKCBaQgNBMaRsllrsr3QQmo+kd/gaK3aZ+j6OwVZJ+Kv98OqL/vJ/JvF+4gVPwOpj35xwBeb5FaFdJlkHXiSg++Ik4HaPRA9fMqgBjfIwQ568IMgDKEIR0jCEprwhChMoQpXyMIWuvCFMIxhNe5gQWR4IFGimgqlFDioWNRQhQg4wfJ+FysQFWFWDsxCAyiDBJHBsHvdqde54KIK3UkRCdozRQs294S6tRCKU7gYFKJ2hSyujYu08OIKl+gpKhjuAi6wXyHY1oxvtQIwCAA7" v:shapes="_x0000_s1796">

如果要查看已有集合，可以使用 show collections 命令：

show collections mycollection system.indexes" src="data:image/png;base64,R0lGODlh4AJYAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQB8ACoAhQAAAA0LGw0LFBQLDRsLDRsLGxQLFBsLFA0ZJw0TIRsnMxQhLRsnJxshJyETDScZDScZFCEZIS0hFDMnGy0nGyczLSczMzMzMy0zMyEtLSctISEtMzMzJyczJzMtITMzLS0zJycnJyctLS0tISEtJy0hIS0tLS0zLQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaAxWHcclsLgOWy0UihEqpziwRitV6v9UkeNxUdIVmMlJJ9i7OzYX08gAgI5ipEDF/CDJsdhoDRAIbfXuIQ2lEjACGUhNCkFIPCnMXHAQACXl0Q3yVdhV4egByonJwRQEdm0NQHgMImp11CB8OCg+2CRRFCrJEtgC4gY5oXWmGdcGEsGJFzELExrGzmq2vRKplF5JhSgkgBAjCzG8IIRIIZ1B1ROaE08nAWFZzEu+s0YUb8PIe/VvDiRyAS+BAiYqjh+C4cuf+IZjAoICGBoHsWIAHKiJHZAfvbdwycku/IfQCMnNoUEg3OQmzTBwljly1XAFEjDBAIsQ2/zTChtw8BseRs0ZBwxmhN5Tlz4lvtHSSoolmwU2hPhmisiDppENXEi2s16gLJT1nsVzKtI1eMUROp7JtQ7eu3bt48+rdy7ev37+AAwseTLiw4cOIEytezLix48eQI0ueTLmy5cuYM2vezLmz58+gQ4seTbq06dOoU6tezbq169et8YkMq5cL7CUgQXoh+HlBCQsT5EDY0EXe2VuKyC4yCxbc8YOYannimLXOnTyrBi/gUMCChDcPHwE6mHTocnvnpx01mVEgvKYWZNH6SfiNtqhpZu7rOO/f+bJVRJHPfuxJ418xHjmFmH2uRDXOAeMRiGB/HxUlEkcaYaiUPwAlKEhGeIcxSEBUB5mQ0HqcYHBLLv+1iCJ5z0DTHlMqFoPThy0ZJiKJCZwQSFpvjRUSel/lI5CRaFD1ilvVWQXiY7rdNtglXknZVxAAOw==" v:shapes="_x0000_s1797">

**删除集合**

集合删除语法格式如下：

以下实例删除了 kkb 数据库中的集合 site：

- use kkb

switched to db kkb

- show tables site
- site.drop() true
  - show tables

> 

**插入文档**

MongoDB 使用 insert() 或 save() 方法向集合中插入文档，语法如下：

以下文档可以存储在 MongoDB 的 kkb 数据库 的 mycollection 集合中：

> db.mycollection.insert({title: 'MongoDB 教程', description: 'MongoDB 是一个 Nosql 数据库', by: '开课吧',

url: '[http://www.kaikeba.com'](http://www.kaikeba.com'/),

tags: ['mongodb', 'database', 'NoSQL'], likes: 100

})

> var document = {title: 'MongoDB 教 程 ', description: 'MongoDB 是一个 Nosql 数据库', by: '开课吧',

url: '[http://www.kaikeba.com'](http://www.kaikeba.com'/),

tags: ['mongodb', 'database', 'NoSQL'], likes: 100

}

> db.mycollection.insert(document)

### 删除文档

MongoDB remove()函数是用来移除集合中的数据。在执行remove()函数前先执行ﬁnd()命令来判断执行的条件是否正确，这是一个比较好的习惯。

remove() 方法的基本语法格式如下所示：

, ) " src="data:image/png;base64,R0lGODlh4AJvAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgCWAEEAhQAAAA0LGw0LFBsLDRsLGxQLDRsLFA0TIQ0ZJxsTIRQhLRsnMxshLRsnLSETDScZDSEZIS0hFDMnGy0nGyczLSczMzMzMyEtMy0zMzMnJzMzJyczJy0hITMtITMzLS0zLSEtJy0zJyEtLS0tLScnMzMtLQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIAQEKA4io6hcslsBioWS0T4jE6b2CyxctV6v+BwVmF5KJFIMXjRFbLVVCN8Tq8PBZfuAWPJGIsQfBJZeFFlAAiGZkNvSo0AhRaDQpFlC4YWGgN7UYuIikQUgVJZARsEUJORgwocFRJkZomdAAodBUIKU6tKCpNbsh5HFbcHH0lNC7dDe8HIAI9uXW94i8q4Q2lK1ULNiMJPtwiaWE+aBxPSkBcPChqoEbreCOByAiAD0LvslPxC4/nQ2MvjpIInRMu4qXN0pYqhKU8OhnrWbxGChOzSHAiRz8mGjlSgPNRlaoCui7i4LRiEYJBDK3GeAZyYRuGSiEpQrvMUTV9I/4k4z8jZ5k9nNY0cy308Y7BXhJInMZrZaEBEkqBCn3lTAK4pvSR7fkFb1g2DM0ZtfLohy4jtxCUK59UDmzTsmaWO2JL8KM/sN2QLRvy6Btdfrigc/mwgQQuA3TsXHn5qvBAtZMnrMF/K1NHmrENI8z2mgveylb0mp3z2lMhTpS6+7MieTbu27SY2b+vezbs3tEO+gwsfTry48ePIkytfzry58+fQo0ufTr269evYs2vfzr279+/gw4sfT768+fPo06tfz769+/fw40vXNbnEsdCZJRE5lQpPl4sMpCWfbGRA5NVcjiVFTUYVnJPORvkIYFUtpAxoR4FDzCQQXQO8JP9FSUu80VIvFVqoxiz/LbNhgh02lU1p3YRQFUWzSGRiGBgakwRXR/zRYD6EkQYSI4INQYZYn9h44xhTkGFBYklcokECSb0G4hI65iJgkkvWoY0dPWHRAEVdivHlHJe4hcWIZbbp5ptwxinnnHTWaeedeOap55589unnn4AGKuighBZqKHVfHSrdmZuRQ6gukZCDn5OHbBaFJrEhVAAgfFzBSy1bLokhM0mFdmU2QwUVUTgFzLTgIqN2GSupAckB4W9iaaPNUbZy5GEbsw6IIhO34ndYQ6m6uGqvLSo5mbPxzVqsj47+M8mXmaKEX5BG6gcnfTF2JCWVm/DBGSOXApkP7lu3vgYqnjopattqigYBADs=" v:shapes="_x0000_s1804">

如果你的 MongoDB 是 2.6 版本以后的，语法格式如下：

, { justOne: , writeConcern: } ) " src="data:image/png;base64,R0lGODlh4AKyAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEwCoAHsAhQAAAA0LGw0LFBsLDRsLGxQLDRsLFA0TIQ0ZJxsTIRQhLRsnMxshLRsnLSETDScZDSEZIS0hFDMnGy0nGyczLSczMzMzMyEtMy0zMzMnJzMzJyczJy0hITMtITMzLS0zLSEtJy0zJyEtLS0tLScnMzMtLS0tITMnIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIAQEKA4io6hcslsBioWS0T4jE6b2CyxctV6v+BwVmF5iJ1G5Pm76Arba6IxTq/XBRf3+oCxZNIUEH0SWXhRZQAIh2ZDcEqOAIYWhEKSZQuHFhoDfFGMiYtyglJZARsEUJSShAocFRJkZoqeAAodBUIKU6tKCpR0T7IeRxW3Bx9JTQu3Q3zCyQCQb11weIzLuENqStZCzonDT7cIm1hPmwcT05EXDwoaqBG63wjhcwIgA9G77ZX9deT0IVGDRw+VCp8SMeu27tGVKoemBFuybQhDBAvbqTkQQp+TDR4PZpIXwdQAXRhxdVtACAEhiFaozAFYbuC9fxQRKknJ7pO0/30HE24RWtEfI57WNnY0B1LJxCG6TKLMaIajARFJnjqdSeebgnA66yXh8ysaM28YnjXSAwnbErfauBpF+0ypPrJOmz46W6skyHlpwSVbMOIXXIs46ZCxwCHNBhK0AOBFHBFU5IZrKcdkVzlalHI9h8xCZFcyhrIml1giKXXK6E+KPq2GWtaO7du4c+u2w3C379/Ag9fBJFS48ePIkytfzry58+fQo0ufTr269evYs2vfzr279+/gw4sfT768+fPo06tfz769+/fw48ufT7++/fv48+tvr8tyCWSl8WIKKpMUJFoHDBi0H3JkSBSWPWMtVY1GFaCjDkf6CIBVLaQsyP9gh4nUBEiEA8AkRWoOJVJWgx4KN0sXPNlEolZE6NVMCFdBY1lxLe7G4jFJfHVEGhXqcxiKjxQG1SRLxNbjb/0t1lgSmGiQwFKrIdkMMrko6OSTxhVFx09YNKAjmL+JeQYmfGXhEppwxinnnHTWaeedeOap55589unnn4AGKuighBZq6KGIJhqoIW0qytwBJmTj6HNaTspcpZYmtwBomTaHYafNYQqqcZBKOipypZ66HJumqurqq7DGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq6x6Ypmn5hcQKbhfUZhoEtJxz4ZBpnmSiEgih7RU+9lJlKRUxChX8IL2Vm1aRBXIIEK8Fm8omWlGyWKknNvHFP1R96ldlW7zVDDiFBAQUAxN5gWLBQNJzzAPQyPNhAkFVPDBLEr37xyfYoLaTNskxXFHJkqLRcZyJFEQUu2w7FMXJW8xbmm5gOjcxt/W/BDIOg0FcM9rvBiXyi1T5XK9QzmlE4Y07wgdzudc+2bKtMV7S2mHrQsGww86EHHESJslqRoWj6wPGbV9mdynnlnZUSfWNjKuZ3EHeEFnCn8R1WORyWsZImHPttgJF8Bjdr9NAp4cT5maiZzflk697OSUV2755ZhnrvnmnHfu+eeghy766KSXbvrpqCttMn3ZihEEADs=" v:shapes="_x0000_s1805">

**参数说明：**

**query** :（可选）删除的文档的条件。

**justOne** : （可选）如果设为 true 或 1，则只删除一个文档，如果不设置该参数，或使用默认值false，则删除所有匹配条件的文档。

**writeConcern** :（可选）抛出异常的级别。

以下文档我们执行**两次插入**操作：

> db.mycollection.insert({title: 'MongoDB 教程', description: 'MongoDB 是一个 Nosql 数据库', by: '开课吧',

url: '[http://www.kaikeba.com'](http://www.kaikeba.com'/),

tags: ['mongodb', 'database', 'NoSQL'], likes: 100

})

使用 ﬁnd() 函数查询数据：

db.mycollection.find() { "_id" : ObjectId("56066169ade2f21f36b03137"), "title" : "MongoDB 教 程 ", "description" : "MongoDB 是 一 个 Nosql 数 据 库 ", "by" : " 开 课 吧 ", "url" : "[http://www.kaikeba.com"](http://www.kaikeba.com"/), "tags" : [ "mongodb", "database", "NoSQL" ], "likes" : 100 } { "_id" : ObjectId("5606616dade2f21f36b03138"), "title" : "MongoDB 教 程 ", "description" : "MongoDB 是 一 个 Nosql 数 据 库 ", "by" : " 开 课 吧 ", "url" : "[http://www.kaikeba.com"](http://www.kaikeba.com"/), "tags" : [ "mongodb", "database", "NoSQL" ], "likes" : 100 } ' src="data:image/png;base64,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" v:shapes="_x0000_s1809">

\# 删除了两条数据

接下来我们移除 title 为 'MongoDB 教程' 的文档：

db.mycollection.remove({'title':'MongoDB 教程'})" src="data:image/png;base64,R0lGODlhsAEUAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgBSAQsAhgAAAA0LFBsLDRQTFBQLDQ0LGxQLGxsLGxQLFBQTDRsLFA0TFA0TIQ0ZJxsZJw0ZIRQZJxsTIRsnMxQhLRQhJxQhIRsnLRshISETDScZDSEZDScZFCETGyETFCEZGyEZISEhGy0hFDMnGychFC0hGy0nGychGyEtLS0zJzMtISEnMzMzMzMtJyczLSczMyEtMzMzJy0tIS0zLSEnISctITMnIS0zMzMnJy0hISczJyctLTMtLTMzLScnJyEtJychISEhISEnJy0tLScnMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwf/gACCg4SFhoeIiYqLjI2Oj5CRkpOUlZaXlAEnGIIMKAKFEikDKisrLCAEmIcFLRitnKuNBS6mIYK0tqu0t7K+jxMrGYIBLyspqgANwou8ibkrvYnLw4vUv9gAwSswDiIN1QC5wwwanSOD1+K1yILL0s2usNmMEvAA9pHz9PyLxfAMYiTrJCOWOFfxDA7Kp4hBQUYOOdESIUjUQFnQoq0zxexQgBkGaBAIVuNFtXIAUKZER1ChJoUTKGq7l9LGihvyWnywKdPQBBwuRATb8EJaA2TFOA5bZiocvnsMASRdIXOqMAkcuQlgYLOjMqUHd2pkleNArarGqGoDKpQZU2YT/9rNlJq2Z8xCBXKAGpR3L1aOMLZ2rfbXVOCF0qb27FuIa09xesXp+FRAh8CvTcPa7MV1xY6HmJnN4zXv6MWaWXmQAPCA09sVPbJKY8w3MiGGwWa7WMrjlQtkEQ9NgGE2xIQQnkC9xCe3Jm+DUStKy1dsmEVC+wZV72TjOS1kDQ4bohWYQQnpdDMML36cq3eEAXyAom4yPV7biLITq48LYajpt2yXiGP50aBCBg3gIJJ7yvT2HQHhCUDLc841+Ip/cRmA0F2GULDTDzIAYQIBFeyFT2AQ2JBCKeKRZaI7zeWmzGGwzPOPcCH0ddxTylA0ISGm2Yfebb1kFM2P2PmnHf9/QVY3T3IuYlcLR8blqNdxTdYnAUUN+DilLvpJ0GJ+Su4Xjn5DGknTIwXQAIEIFnAgUpYZPPlJhAe51o6T/h1lwJRrpiTQBQiA8EAHMyRTQBAe5OABASgFkIAiExECpTtN4VkjfPwVcpyOt3iiwCbrOEUnYv/hspuUTuVZiIB02vlibS8iKcinV4Zwqqik2tqfQil9QmmZQrqaaqm70PBnCiFBuKdJsmqKUKwYijDPcLOa1oAQVC3KEgAVRGBOJxqYRyl+nQgbTE8MTuDgqg30VpNdVgqwIz5C9HQddxQOiWpFzTF3EZoCMhjvhZxASSAu6Ao8CK72ItedhRXlu1DTwALSOiywGaPJI8CnIbLweCLZ0yakEx8sa7vyGuwgQu6NRiw0vcS3FwMjoLTNMQOErDGQyNxLyDY4yJPDEJnN+3C99wa3H5WhORXdx3RBbVUvf4mX8VvDyKo0w7NeDfGOXKfj1dW3Piaoz7cZtlfG+Lj9b9W6DGiD2nyJhIveZXvNVNGx9P1l11/inQ45MnAQ2QIQhqOSIssR2c+xk1dueSRwB8T25ZyLLOwvEvCwUztIgrPNikmzUmaXl2MVcOewx16RV65vLvvkU8l7O5tnmd1TIAA7" v:shapes="_x0000_s1810">

\# 没有数据

WriteResult({ "nRemoved" : 2 })

> db.mycollection.find()

……

如果你只想删除第一条找到的记录可以设置 justOne 为 1，如下所示：

db.COLLECTION_NAME.remove(DELETION_CRITERIA,1)" src="data:image/png;base64,R0lGODlh4AIrAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgBPAQoAhQAAAA0LGxsLDRsLFBQLGxQLDQ0LFBsLGw0ZJw0TIRQZJxQhLRsnMxsnLSETDScZDSETGycZGycZFCEZIS0hFDMnGy0nGyczLTMzMzMzJyczMyEtMzMtIS0zMzMnJzMzLS0zJycnJyctLS0tISczJy0hISchIS0zLSctISEnMyEtJycnMyEtLS0tLTMtLQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQELo6jo8hsOp/QqHRKrVqvWOkC88h6v+CwuInAUKhKpbSMwWQEAEb7bUQS1XdN2w1nuwcbe20cBHpzcHJ8RhpdcY1MAYxCDI0JHV2RFZMcBZF7dGOhAAYbZ4t7Z56HeEKqf4GCnK50iXSRjZROfm+ujbOAghiEhny9p4dClo9PDKBCC5oAC6ZElhgeSEcTHRjRTAgfS0PQQgiydkOsrejl4UwJIHBD8PLkAOadGrTLefuTIZoCiIgncEQndk8CkDigJxqpNpoWlNBQYUsXP10WcHp25mE3IvbqLIlEQZ1JhMniFaH3LBq+SP6+uUtnR2OBeSpxyju5xGZK/3n/vDXZYiLnEAamtlC7dS9cJE4JTogjQoraIl+MeBZhVbUJS51XW2VFkUJTriYByJrFhIJANrIPEJRAcXBqQn0CEliY1HHDgwUZGFKYpqxpEjsGVCDq24jUIzwa3drVutLoz7BX05Z1xKTrHTsMhH69LPLzktBgW7VF+c7yvY3PzNyzlQ1xKUgI8TimnI5YSdYARgdXqdtv2kIPzkJqyyjXtDglUSio0AACXVdWt5IAeqzN4JLbp+Eb5TeOJgQBickuvU6cuUKo2IuNTxps8QfHm/OTP1/YzdSkYXcMbMMB9RxSUQhX4BBsxEXbYUs41tltvWGFX24ocUVhZdyxxP9UZnQhkMEK+6lWgIgkeuTGAcwRch1w2nH3IUcKCSDeRhLCMwALI0myFTuQVcAbf/Vd9uEtaZ044n6e0eQAYB1a9hVPUKamojNeucbSFt4UtoBTkoCzhCXeWASSS+dMliFCZnJYTU72vERXHFw4kSSdcTm0QQR0IZWkOsF1IFSNRTBA4DQ1EnaJYZO04I2h/w0hYXuBXqjmpa1xt6A0aHYypxwltkkpagBuqhWpm6InxKT31FnqawU8V8QWGJSQDQkrtFGJoET44U4tcLgC5h7uuFKnr+JMSQwtyNw541ZzRiIBj+O4YJCJxnZB5h3bFaHid4megRGDrpJHH6e97YFViXr4ETOsuvXNgkiz0fr4DbEQkpfeJ/IoK4il+v5C7TOwldEIrfDyJcrCDFthrCINR4wFqxKH8TCWFYfRgF1kCJXxxyCHLLIToI5s8sm9elxEGd4EAQA7" v:shapes="_x0000_s1811">

如果你想删除所有数据，可以使用以下方式（类似常规 SQL 的 truncate 命令）：

db.mycollection.remove({}) >db.mycollection.find() > " src="data:image/png;base64,R0lGODlh4AJYAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgDAACUAhQAAAA0LGw0LFBQLDRsLDRsLGxsLFA0ZJw0TIRQhLRsnMyETDScZDScZFCEZIS0hFDMnGy0nGyczLSczMzMzMyEtMzMtIS0tISEtLS0zMzMnJy0hITMzJyczJzMzLS0zLS0zJyEtJy0tLScnMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQEJIvjoshsOp/FwIRCeQilVCt0O5RqueCwWJygMISCCsUywLKHh2pYqRzboYqvMG+v3/+Af2l6CBcDRAIYS0QJEEIJekIIGRQaSEcOlI5NCRsTEGUNFV8HbGlUZgBxVGdEfK5fpxSbsmYKqBQcBJOsRKupmJSRXR0FU7RqswCdn2Wtv2cJb8tWspvL11cdBEQB20yvZV9SZwceSRNsCB+LRQkcxg+QCCDciUsK0wCT5eewRa8A8ElzJt+hLkiKEBzCT9U5KWwO6GoiRReCCHuqVWDwLh6kfRn6JUkoIAS3gRsBLOz2jRi3IqUOLpMjkRsdkqM4PfBG4COf/wOOyPl6szIjQC1YUO2c0AphOzQphcRUubEOvZdRWl6ZohQSz49TFypwBNQI1yxGEg5RMDHKpyJX4bCqmXbkkqKMdm77SM+AIrNNVRGNavQf4G5Mo6gdUjRs1YRxmfBEHNjrXiuOz/T9K7TbYkn1WGJlGLrMtYYJHiY2t2TSNcs9tSgQcc0gw5AOnwYsLFBfb5l1FRJuyNpqaNcuAeqDzRc36z201/rGa0Rr9dFS2Xx0R2XDpQ4jeoF8rTe2JHaMk9FEFVhgpIC1NCrdQ6UtXmjB4yLXhj0+cy34SZUKVPNhA5chiPx1VCB27MbggxBGKGF6gRUCnB9wZDMhOGsAt//hhyCG2MQtTd3CBhbZxKGhiCy26OKLMMYo44w01mjjjTjmqOOOPPbo449ABinkkEQWaeSRSCap5JJMNunkk1BGKeWUVFZp5ZVYZqnlllx26eWXYIa54U1PvZgUUlwN84cXEt4iHptgYDgTA3GoaeYln8nooINcyPnhZIH4MQgjK0a4HRG8WDJSJspw4gkoFIhCiinqlcPegoZRRQUy7LlZ3y6UDChgL8HIwUQtFJzDy4ClfpGoWo2AY+eD4nSxmmrqoMdERxPI80Bc9/x2m0iZ8oZSQb75WRRxuA5AF0XWUdUKRM5OJNRz1Anm4YS10kUmVWr+554qQSUGx2CB7YmwJi5LtacsYZkZh11yiEQlryrWJuTnPqGFuAqd+dolrU5fWbHZEp1ldwhe6m4VWMJX5NkYuvdC25+9kIXmrb558jsvhKYNu4xqxO5HTcHQ1aYPs7q994Vt0l0o8XDOPZQxNyZfJ9y0N4MUjT/ZTrXhoUOUQYF3SYAnnsni7qOrpmgFuJbL6c0XH325vHTfpffmDCiFEbfWr9FIP7Iin0iiLaYghJHbpInbrh0IiSqKGAQAOw==" v:shapes="_x0000_s1812">

**查询文档**

MongoDB 查询数据的语法格式如下：

**query** ：可选，使用查询操作符指定查询条件

**projection** ：可选，使用投影操作符指定返回的键。查询时返回文档中所有键值， 只需省略该参数即可（默认省略）。

如果你需要以易读的方式来读取数据，可以使用 pretty() 方法，语法格式如下：

> db.mycollection.find().pretty()

{

"_id" : ObjectId("56063f17ade2f21f36b03133"), "title" : "MongoDB 教程",

"description" : "MongoDB 是一个 Nosql 数据库",

"by" : "开课吧",

"url" : ["http://www.kaikeba.com](http://www.kaikeba.com/)", "tags" : [

"mongodb", "database", "NoSQL"

],

"likes" : 100

}

pretty() 方法以格式化的方式来显示所有文档。以下实例我们查询了集合 mycollection 中的数据：

**limit\****与***\*skip\****方法**

**limit()** **方法**

如果你需要在MongoDB中读取指定数量的数据记录，可以使用MongoDB的Limit方法，limit()方法接受 一个数字参数，该参数指定从MongoDB中读取的记录条数。

**语法**

limit()方法基本语法如下所示：

db.COLLECTION_NAME.find().limit(NUMBER)" src="data:image/png;base64,R0lGODlh4AIrAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgAdAQoAhQAAAA0LGxsLDRsLFBQLGxQLDQ0LFBsLGw0ZJw0TIRQZJxsnMxQhLRsnLSETDScZDSETGycZGycZFCEZIS0hFDMnGyczLTMzMyczMzMzJyEtMzMtIS0zMyczJyEtLTMnJzMzLS0zJycnJyctLS0tIS0hIS0zLSctISEnMycnMzMtLQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQELI6jo8hsOp/QqHRKrVqFi4v2IQxgKFMlkXF5IC7gqFeb7n7V7ymZe603z201Uhw9azMCAFkXgEZIRHxDa3+BfoQDGlqSGwQYkoWDhV50C3RMm1h0CRxcXhVYGwWLhIFOAR2tdnwGGnkMp1MLeXaGS0W0aaCCD2IGHhAYuAupq6WWjEbPZUO3Qgy7AKMXH3sWExwXuEwIIL7WuAjMh4rr7OYA5O9CCSGx2fXnQumqGJieRV78YRFxKsCIegZJqGrnhJakC+W0TTP0Dc08cNx8VSuiK1caiXQYlEg2BySWh4XyufFX7NhBAQkXLtmXqMshBqmGdARApg2o/3heUiUwIQ8AMEQY6GyqWbPXkKNM6NmTGk1p0gAnUJzq5Crr1lInCOzJaqbECZlhYP3SYDUVAkA/yxllOy5nlJ1CaIXMcODLtbx0bcrTq8jrsJYQyCIwi7ZaUzELxMGzy9Pi20BKitX6xHAWW6YMo0mi0HQeviFUPRMLm5RrE6yVHnD9qwurggoNEvMbDeWVvblKD0m97LQ01bt5CPMkDesvcHcA28FuTeyQscS3c59dFExady2U78XyY0ZTtyXKiUB1Y7X6u8ft1hM5bhpzUvZYC7xN8Q/R2f0POPTHAaxRsl1orqilXmBiDGfeEsadBgVezy3nm3PpwWdOfgAiVv4gAQcuwQBcoSkx4lT4kCHOKFwwUE5cS4wizhxjoKPOeyUyRGMR9ImnEk1nCTLRJ0FmYQYutERwVm0h8siBZEYo+BSDwtXDIk9ypTfEPqg9yQSFyl1zYRoZhpZhkWUcRQ+BBTCJVmY5LhEZEfs4N4YWJezRQQpbXCSZI3JlYp8kL0oj1yrTAOrLcatgAk1+VXVVQDQSeGAOAyoo1MV20kwjI0BSAhZcjCniuc5GRFD4KUfJBSZmc2QGdhIr1IgDKSjavBikEZxKUlCnpVingTgU8mLsscj21imtyTZbhZYIQOlsHVpOa0W01mar7bbcTmvkGdJ2m8uQ4o4TzhBBAAA7" v:shapes="_x0000_s1819">

**实例**

集合 mycollection 中的数据如下：

{ "_id" : ObjectId("56066542ade2f21f36b0313a"), "title" : "PHP 教 程 ", "description" : "PHP 是一种创建动态交互性站点的强有力的服务器端脚本语言。", "by" : "开课吧", "url" : ["http://www.kaikeba.com](http://www.kaikeba.com/)", "tags" : [ "php" ], "likes" : 200 }

{ "_id" : ObjectId("56066549ade2f21f36b0313b"), "title" : "Java 教 程 ", "description" : "Java 是由Sun Microsystems公司于1995年5月推出的高级程序设计语言。", "by" : "开课吧", "url" : ["http://www.kaikeba.com](http://www.kaikeba.com/)", "tags" : [ "java" ], "likes" :

150 }

{ "_id" : ObjectId("5606654fade2f21f36b0313c"), "title" : "MongoDB 教程", "description" : "MongoDB 是一个 Nosql 数据库", "by" : "开课吧", "url" : ["http://www.kaikeba.com](http://www.kaikeba.com/)", "tags" : [ "mongodb" ], "likes" : 100 }

以下实例为显示查询文档中的两条记录：

注：如果你们没有指定limit()方法中的参数则显示集合中的所有数据。

**skip()** **方法**

我们除了可以使用limit()方法来读取指定数量的数据外，还可以使用skip()方法来跳过指定数量的数据，

skip方法同样接受一个数字参数作为跳过的记录条数。

**语法**

skip() 方法脚本语法格式如下：

db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)" src="data:image/png;base64,R0lGODlh4AIrAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgB6AQoAhQAAAA0LGxsLDRsLFBQLGxQLDQ0LFBsLGw0ZJw0TIRQZJxsnMxQhLRsnLSETDScZDSETGycZGycZFCEZIS0hFDMnGyczLTMzMyczMzMzJyEtMzMtIS0zMyczJyEtLTMnJzMzLS0zJycnJyctLS0tIS0hISEhJy0zLSctISEnMycnLScnMy0tLTMtLS0tMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQELI6jo8hsOp/QqHRKrVqFi4v2IQxgKFMlkXF5IC7gqFeb7n7V7ymZe603z+2quOjNQ+d2gUR4VEp7UGdaGQIAWReLRkhEh25aj4yJjwMallobBBiWkI6QXnQLdEymWHQJHFxeFVgbBWuKjE4BHbh2ewYaeQyyUwt+vZJFv2mrjQ9iBh4QGMMLtLZlRqG32ZapwleUV8pup86S0NLU1trY3LfX3sMMxq4XH0hHExwXw0wIIEuGfAOAwBqyLgcRBhTyb+GQBCF4AYCIa2DBWhhGpeKTkREqLCJkBRgRcSSJWgmb/OoEsF67fPvS1LsXcCCRYsRk7mvHoMS0/zkuT3WCJMSmF43PopEUYBLlkovhxDCgNQTnEzJbIi1x9SDokCMmtPQz2rFZUghLm4q5qHWSpKkFqqYh02ZVQy+0EpxwKGTcVwx0TFGKetBvEYpEEDMTjCKFrI9NAjR+DAsFAXyNzZRA4TTMrmQaAmOghWCRXYAAfm1kSDWK1b6hi2Y48GUe7FThVH+dbPZctMwINnf+RnjJgn4EW6v6/BUJ29t/ZeldorsLb1RngQuXOmywpOODlNMlWArfOWCqEvoK7Z3vNQrhhCB+GLFtavaWAUNWlf/BR9vFSKZABQ1AwNl7UOgi0X2BSUJRaYyIEd98ULzGYFHw7WLbhQrxcf+QZKD4Zw51vw1Y4IHaLMOOip7ERd+CN/HTnArIcdjWOIeAqN+IqZVIoIEYsfGXJWDYotxE9TG0BYRaPRMbaHksBlh7Hi5kWGJJyleflM5wVtoKq+1WwJcPrKTIAfl9cmBKuTA3RHViPFjeEhNm+YSF1c2j4IbV2dfcQpKNmQGY2YGi5nCmpaQEA0RpCWMR43nBQqM2OgnLh14OyiM6aRKw5hKMRqgeEqFiyQgZ/XAFAAMAnbYVB2O1U9QwUI3qXkKAHGbnfBZZw1kjsvLxaxZmDPNLBJwF+OlhsPLhJnRtUaQqq9Q9GZ6LEzVbBJ5P6qlhGn3mZm2gwJaJHkRoFqDjbGeGKPodcs+5UiNDIjnXaLiAEYRauMOWMQ66ycK3rIS2NgIvLRuOoUUJ+HSwQlbZwmsJaqREyE6rF9PJDjaZoIYkLteMsg25zPAXlxcSeLAQAy2cZF2Q3UTs7IJwOlgfVgzXNK+F8jLBLR3eCsCntcBeIlA/JOdbT6u/GoGiJSJtfGm1/bzWs3w7zblqO+Fa4ALEqyL96ypLJ9F0oNd0J7WTVRsjyNtwxz3JxkbLbfcUfRI0791W5M13FQjsLXd8fv89ReCGJ6744oxfQewZgjd+Z7CSDwH54vFhQXnlBMkoRBAAOw==" v:shapes="_x0000_s1820">

**实例**

以下实例只会显示第二条文档数据

db.col.find({},{"title":1,_id:0}).limit(1).skip(1) { "title" : "Java 教程" } > ' src="data:image/png;base64,R0lGODlh4AJYAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgBsASQAhgAAAA0LGw0LFBQLDRQLFBsLDRsLGxQTFBQLGxQTDRsLFA0TFA0ZJw0TIRsZJw0ZIRQZJxsTIRsnMxQhLRsnLRQhJxQhIRshISETDScZDSEZDSETFCEZGyETGyEZIS0hFDMnGyEhGychFC0hGychGy0nGyczLSczMzMzMyEtMzMtISczJy0tISEtLTMzJy0zMzMzLTMnJychIS0zJy0hISEhJy0zLScnLS0tLScnMy0tMyEnMzMtJyEnISctITMnIScnJyEtJyEhISEnJwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwf/gACCg4QAASYYhxiFjI2NAScoKB+OhBKSKBmCkJSVg4qEE5mCAikoKgOGkaieAKWnqRMqBCmdjpyekJK2jbK0vK3BhbjDiMKbq6kADJOtus2fJ8CP0sKimozM08Kgw9XB147ax4rdx8ES24QBKwXnjN2lvA0syoMCLYue9PYTIADpNhlzNtCSOkH+AB4E6MLdO4L6GDV4gQJGRFf5Qv1Dt/CcOVK1LDXU+NBQwYfyRDoclBDAhGkTUcRAdMgDxY2NXknSxGynwUo6JVmMOcqkTWgxZ+pr+andMKe5oC6jJEHTJUwNiWIDiAnFSEEBQUrayIDq1okbRcmYsfKY1kFJ/weiFbioAdumbRVyFPTWJY0TIML1vSrpK1O1d396EuUT1MQMfQ/VGMsSJ+K8el1CU8XTIiRUDWxctMSK74vOEcNWYpe3FLbPAxg0hITaVYqtm6TmxvyU96eTtnF/BGsroOtKc+EmRnkbWW0AyWW7O767EIPSlVQHR+jCgLSX95rTJURded7r9uDpVoQ+/GvA0EVvJ2QXc3uEzaSbTGQsJbwTuLVXnnaP6DafY2zpB8pHEnzlDU6MNOjbb6OVt1+EnTyzyzv1lTSfgsAJImBI0UDYoSfaUfcSa+CBtNVHw51oHooo4HTIDRAe2F9IMS43o4g7gcjfIhZGEyArA3bEWv8h1CFYgJAX0uejIQZKNGUxFYo3XiFh0eYhdFcKQx2U2YxU3pJSTkgceeKt2E6L80W55Y90ViIKJZDg4KCORDbXI2YdioLTYy55BuAyFkH3AoQSYEcoA4muaVqOaLo3Xn2ETpBokZVWNwgzwlW5DnAWDpcZQNght2ia1hUFlHiZRpqck3hZV1pyirmI0Acs2nLmSUXKKOKtqzbCwD/sOdjkoZD2iRuY9qECJ0uS0EDTCjn4pCiEQVHSk6un4uqppRcGWu1ATPFVD5MZfQouP61c9VWR8q6kWreqptWVq6C2Uh5j1uqDa0yRktLuIKqJK6muLvH6pq9acuWVQ4ftu1X/wsWaVpg7oISjow7aumSZxbl+afLJJ1sI76ijUXDRyijHbLLLquy7p8xyLpMjzsw9m83OOJtapLFA82z00ZdcDEvNOR4b4dJHR0201LdEghMzRVMdL7juZh2zqWBx3bXWZJdt9tlop6322my37fbbcMct99x012333XjnrffefPft99+ABy744IQXbvjhiCeu+OKMN+7445BHLvnklFdu+eWYZ6755px37vnnoIcu+uikl2766ainrvrqrLfu+uuwo9SusKgesIMkPISQXuykv5JqKLOQWPVBzyzki/AnH98Rz4y54AAIDLwYiSYNaMCXCAgFr44uv/P+OMy9/NNl/4hYYriY+Mtbgz5+IR8tQA8I+DCAKD/Aan311yO0PlAHew95p9kwi8Q2NpiuOOhepqiRiATYCoVZh4EMY0wzZKEM8EjwIEQRyggA8IBFfAsFQOhKJ8oCEJ+tQ1T+WxwAgzGcX43mVMaJmFsy9o4iSQcUAghCW/TjiArYRAY2EAIJBmABezUEAi9Qwe1u5gwUphBxEipJC7Vkqi5FAhPp49myNgaQfzitZhNDTj0uQIAQPGADPVBGAIbAgRVwYAD4E0ACHgIJrz2xcLRjIamoGCIrmvBsx/FSh+yigIwIMkzLQAUDcFCjNWJPEBaIgPX4ooEGlICOTryj4VYIESbx8XaFqmnU7jhEQ48YAxQ8lAAONoJKJiJjQ67Q4fXwJ0EVHGCUUVGTJgkHvmPUi2FhC+PC8PUQB+biirZgTP1G0i/2LVNN0YtPB5yygNicZZKye+EuD9fLbQIEBjZhhZeWkQHGKLF9etSmNwt3ie6tk2q6sOM7+xYIADs=" v:shapes="_x0000_s1821">

**注\****:**skip()方法默认参数为 0 。

**文档排序**

在 MongoDB 中使用 sort() 方法对数据（文档）进行排序，sort() 方法可以通过参数指定排序的字段， 并使用 1 和 -1 来指定排序的方式，**其中** **1** **为升序排列，而** **-1** **是用于降序排列。**

sort()方法基本语法如下所示：

db.COLLECTION_NAME.find().sort({KEY:1})" src="data:image/png;base64,R0lGODlh4AIrAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgAdAQoAhQAAAA0LGw0LFBQLDRsLDRsLFBQLGxsLGw0ZJw0TIRQZJxsnMxQhLRsnLSETDScZDSETGyETFCcZGycZFCEZIS0hFDMnGy0nGyczLTMzMyczMyEtMzMtITMzJy0zMyczJy0tITMzLSEtLTMnJy0zJycnJyctLSchIS0zLSctISEnMy0hIScnMzMtLQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQEMI6jo8hsOp/QqHRKrVqFi4z2IQxoKlMlkZF5IDLgq9pJ5goFmwxnsL565XTAOS1ViqNnWh0EAFkZg0ZIRH9Dd4KEgYcFcVpaHAYalYiGiF5uC25MnlhuCR5cXhZYc46HhE4BH69rf3B8AAyqdX2KRLZFCSB5YgghSZmPC3NCDMtNC4i+IktDucy3QqYZI0hHFB4ZukzF1My6CKy9Xerr5XrGTAkkswDyr9Z6rBqboUVe/FhKqApgYh5BYYycwKmUwZi2Muu+ockGjhs1fEQWYGNCZouQSKg+HMikytAjc6JktbOXyN0vlvU86CJzYp4/lRnTkOEzipyX/zkJULh7s+HWKCMaHiRM2HLIr3g2h7A86ilAChUl+y26mtVICgPdrppZkWIAU1g4nW5w83MAgkE94cHRmi9Pk1j0THHxuY9AggvtfK1N+eoIBJxMg0aYJlUmEZiN0n50hmviW0J+FD1dxK7W2qXskFaqcLZe1Gw2PSv9mhQULNYPXDMAo9GqAgsNIJRttXGRZABzA9u73PQs5GfhhqCjMxevv86hoc26wxPZRGbXnxzvokGc6VmBzHTqpnlwkc1I2SYFPZQReqmnv6fnvtptBxZ0u5R9i3+hoAOsXbJbaGjRA5x5Ygw33hLGxceGZcs09xt7UBWGQQRFBfbcUBXeZO8gS2SIoxcuxsS1hCnitDHGOem4w1RCKgITH0z4LGcVHVnk5xWOZSCgCxwSlFXbgEOh6OF5CCpiz4gMyGUeEcs15p1yqozo04TsBHdkS16AcVZCRsJnoHNQzjEbR1qs0M0HLHgUk3eRwMMJZtaVWCeD1kEUJzUwtbLJSTfOd1dZSE3AGDMtCKOfWXlyEWZkBmqZoE0dqXnRlIXc8uhD0YBkxG+FnIQLpsEMo0gqrUDUlJTYMRSKAIcOodEutNZqKxSpunLrrlRo+RGmvCr0ZDaKBhtFQj4aq+yyzDZLa45nAOusrKpigce0ookT7RBBAAA7" v:shapes="_x0000_s1822">

mycollection集合中的数据如下：

{ "_id" : ObjectId("56066542ade2f21f36b0313a"), "title" : "PHP 教 程 ", "description" : "PHP 是一种创建动态交互性站点的强有力的服务器端脚本语言。", "by" : "开课吧", "url" : ["http://www.kaikeba.com](http://www.kaikeba.com/)", "tags" : [ "php" ], "likes" : 200 }

{ "_id" : ObjectId("56066549ade2f21f36b0313b"), "title" : "Java 教 程 ", "description" : "Java 是由Sun Microsystems公司于1995年5月推出的高级程序设计语言。", "by" : "开课吧", "url" : ["http://www.kaikeba.com](http://www.kaikeba.com/)", "tags" : [ "java" ], "likes" :

150 }

{ "_id" : ObjectId("5606654fade2f21f36b0313c"), "title" : "MongoDB 教程", "description" : "MongoDB 是一个 Nosql 数据库", "by" : "开课吧", "url" : ["http://www.kaikeba.com](http://www.kaikeba.com/)", "tags" : [ "mongodb" ], "likes" : 100 }

以下实例演示了 mycollection 集合中的数据按字段 likes 的降序排列：

> db.mycollection.find({},{"title":1,_id:0}).sort({"likes":-1})

{ "title" : "PHP 教程" }

{ "title" : "Java 教程" }

{ "title" : "MongoDB 教程" }

> 

### MongoDB 索引

索引通常能够极大的提高查询的效率，如果没有索引，MongoDB在读取数据时必须扫描集合中的每个文件并选取那些符合查询条件的记录。

这种扫描全集合的查询效率是非常低的，特别在处理大量的数据时，查询可以要花费几十秒甚至几分 钟，这对网站的性能是非常致命的。

索引是特殊的数据结构，索引存储在一个易于遍历读取的数据集合中，索引是对数据库表中一列或多列 的值进行排序的一种结构

**创建索引**

MongoDB使用 createIndex() 方法来创建索引。

注意在 3.0.0 版本前创建索引方法为 db.collection.ensureIndex()，之后的版本使用了db.collection.createIndex() 方法，ensureIndex() 还能用，但只是 createIndex() 的别名。

**语法**

createIndex()方法基本语法格式如下所示：

db.collection.createIndex(keys, options)" src="data:image/png;base64,R0lGODlh4AIrAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgAkAQoAhQAAAA0LGxsLDQ0LFBsLGxQLDRsLFBQLGw0ZJw0TIRQhLRsnMxsnLSETDScZDSETFCEZIS0hFDMnGy0nGyczLSczMzMzMzMzJyEtMy0zMzMnJyczJy0zJy0zLTMtISEhJzMzLSEtLScnLS0tLS0hIScnMzMnIS0tMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQEKI1jo8hsNgMViyUihEqpzqwTitV6v+CweIscDxUWh9LMbme5biJiGlau48xFV6hn3/GAdWVaVlIXAl5/YQMYWIqBkFWDkUwKEkIKe0IJGRYaSEcQnZdOjFJpAHNSDkR9rXumFqSxaQunFoecq0OqqKGdmkW0ahsEUbMYUqRoVwC2p4dCsaQACRyIRhSidNWdn0vTqagJHUuY1GucrNOMXQgeBcyoY72sRx/Kzre5nfPiu9m2YZHHaogrNF2gsEIAIkkFeOTM5YE3RF2qhgY1ueJDhRGrBRSHPPK4KcPChlDgIYjmBGQBkRVyTeAIgCSvaI/62LSGLWWB/5UCFF5cohPDyQchJNrM1uDdS2cdjfKsmTTABmxmLDJ0eCkiU2FGS55c4hOoVaxyQgJAmAonqDLtnlQoKMRpzbA0X8G8FUEokZF47XpMd23L3L9X/0Y5RaWQW4lGFjerVvgrT6BMC01mK3LSERHoJNPpg4CULWpiBBtdExdwQdX2ykw9zWRq3VWY7cDFW8RvXYpLN+aNTJd470nSAgNfLbsyE99GEsMsLnSqIuibKhMWkBvU4SJoUP+BMoIldmsGkhLhHAY267Cuf78kuf0Mt+yIwlc0uRbl4a3doObSfmNlVIRwAxqk1ldE7MTfVvUF2IpaZx1IYRndQebMgpT11LScABYpgFGCvx3w3V10KYHZhk8ZNAJqdVEzR3G1PehfgfF1g+OHcqDmVCaVSEECKBuUABAnqNFCRT16NZkcY3dBqQ8u2CzFZIRINpjMFRVqCSUzJmAQzTMsKanZIfUxMyQ4W06B2YxnoFMGGuu0iYVX3RiCFZxaXEnBCQBNWSZvTFp2jS5UOknJoow26uijWSwlhnBZMKDhF49A2qOmnHbq6adf2EJjFrZw2ERpfiDX6RzUBAEAOw==" v:shapes="_x0000_s1823">

语法中 Key 值为你要创建的索引字段，1 为指定按升序创建索引，如果你想按降序来创建索引指定为 -1

即可。

**实例\****1**

db.col.createIndex({"title":1}) > ' src="data:image/png;base64,R0lGODlh4AJBAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgDjACEAhQAAAA0LGw0LFBQLDRQLFBsLDRsLGxQLGw0ZJw0TIRQhLRsnMyETDScZDSETFCEZIS0hFDMnGy0nGyczLSczMzMzMyEtMzMtITMzJy0tISEtLS0zMzMnJyczJy0zJy0zLSchITMzLS0hIScnMzMnIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQEJowjo8hsNgOUSgXirFqv2KxTqSUqKo2hwFK5DACKC8FCrULbz+j0ml7DsdDyGYCYZ5VcXVgLd4KGQoFuchUYBVeJWWOFCRl7aBEAhIhIj5xFmlYKmKBOC41EAhpLQ6JCCoUACRsVHEhHD7OYTmNSYHy9YUOku2RSDQEdBlG6vBW6X1JUC72Mjs26QgkejkYTuH6ytJzXv2EJH6utRcjcfNJh072N4b6Z1KdCw+TumcGxG88qgNhGhN2nNl/gQAmDIEQSCmbOrWKywMwQWQwdCoP1ySIiCvMk5KMyxh+fU5A0lbxI0AjEAQgaLeSjUaWFjA5UCVm5rkO7/yeexNwcAmkkEZsmi8jCFqslIp9FEHhEMyemI0Dj2MQxKfUMz0wciVIwaVAsNSp5qnWbmDYaS25ctBWwuratn4REg+bD50YvgK9FwZrtFdaJ3HUUmB4W0gcMXaxLvhYcS6Tr36FGFZGFKpYs5cOJZjJZHHfbY1uUi3zBFnjxFr+A/Qr+mLSLayOc31LFhhGNw5kNlyw9OrV38I0XAXa0VFbYVC6nJ2aaqntt0wK9FdSkLvVAaslPfzIpGlt65nzUDSuvHj6qmVdMoImw1WGEsWzrdxarCuwonOGo7AdBc/oRRlUFJFhwSjynNDNHWzJxcph8WRFGVx/BqEMEJZaUIqIFPpIx2I4+AlYBIDT9/aWTf4e06OKLMMbYBXgcymhji5AgwNSNPPboI4/TmDRNej/6mAc2fexY5JJMNunkk1BGKeWUVFZp5ZVYZqnlllx26eWXYIYp5phklmnmmWimqeaabLbp5ptwxinnnHTWaeedeOap55589unnn4AGKuighBZq6KGIJqrooozGCF+jkPaIV6SUyjhppZga0lhtmXZaRBAAOw==" v:shapes="_x0000_s1824">

createIndex() 方法中你也可以设置使用多个字段创建索引（关系型数据库中称作复合索引）。

db.col.createIndex({"title":1,"description":-1}) > ' src="data:image/png;base64,R0lGODlh4AJCAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEgBdASEAhQAAAA0LGw0LFBQLDRQLFBsLDRsLGxQLGw0ZJw0TIRQhLRsnMxsnLSETDScZDSETFCEZIS0hFDMnGy0nGyczLSczMzMzMyEtMzMtITMzJy0tISEtLS0zMzMnJyczJy0zJy0zLSchISEhJzMzLS0hIScnMzMnIS0tMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSAQEKI1jo8hsNgMViyXirFqv2KxTqSUqLI6h4GLBDAAKDOFCrULbz+j0ml7Du8U6m8nFZ99+QoB4eneBRlFmQghzf0h9h00LhpFakFtyFhkFV5dYY4YJGmdCChIAk4JInatFqVamqJREC5uwp69EnpWBu4exuV21nGIbS0OxaJQJHBYdjxQQzadOY1JgAIxSYUPBTdbbAR4GUdTg1F9SVAvXmpznRAkfxEfSjczOq/CMYQkgx8l0eSCWbV0Ydtc24cOGqp0tIcHgFUTFDQAzdBZCzLOCwOAQfM+WSEzHEBwYceQsnFpY8YgIKbgcFmA5RBu2es3adKQYjwO1/yZfNBI0MtBVmy9woIRBMCJJBTP+jjFZoEgIs6VNu82iVRWRwgkQqYypmM3WpVRjP25EZAbBJqXZsqK9gPWBMSFpi4gbyqeVGLpDdgWbS7bIxXhru8BluoTwXlddoXz927LCyn9G/OIFbJEDVqdtHzo5bEXe0MdEEHRFWpaTEi6g4pBVfSavLCtwAxcNnGkOFClm/f6+Bse0qiWm3bp+1Ptoo8xSIYreohmAbcFJm+Mxjkc59OHqGv7MLWg3kev69njKS9s6XS7cs5isGl+QZcQEtTnw/jp9YfuzKWKbN3pVQBZqAOpioEUbQUIefscxWAB/zP33xXjVSYhbdehFF/8Wb/9pUV8WFDoVImsPIkhZYP5BVwR7Ar63yoiH1Keihhd+5BkaTS2WFWkQdXVVXFLlAmSQpBDFF1VJclEiV0mqRc+M8wypgFxdZYPBAQu699+NenHImYtGQYnFkfXxU9qOVzaWZU3UMKkbX16CSCSZK3b2GXyJNXGkn33eSJsClKRDwiMelLCNVT6dR0Z4NpFlZKOOEnejSVSkY8IFtiBkC6aIJFQAn5wYmh5x3qmJxk8fjXKFp8TY1pA7WlUa3miUkrRoNgxVYapIj06x0EOYgoneCbvOSuyYkbpIY0+s5tFOSXfRshUv2Gar7bZWyGqRq9yGmwUDHop7iC/mbnuaCQLRpuvuu/BO1WtDb8a7Lbv29pJhvr2UU5NK/AYs8MAEF2zwwQgnrPDCDDfs8MMQRyzxxBRXbPHFGGes8cYcd+zxxyCHLPLIJJds8skop6zyyiy37PLLMMcs88w012zzzTjnrPPOPPfs889ABy300EQXbfTRSCet9NJMN+3001BHLfXJhE5t9dCsXa21z1lv7fXN+n0tdsxBAAA7" v:shapes="_x0000_s1825">

createIndex() 接收可选参数，可选参数列表如下：

**Parameter**

**Type**

**Description**

background

Boolean

建索引过程会阻塞其它数据库操作，background可指定 以后台方式创建索引，即增加 "background" 可选参数。"background" 默认值为**false**。

unique

Boolean

建立的索引是否唯一。指定为true创建唯一索引。默认值为**false**.

name

string

索引的名称。如果未指定，MongoDB的通过连接索引的字段名和排序顺序生成一个索引名称。

dropDups

Boolean

**3.0+\****版本已废弃。**在建立唯一索引时是否删除重复记录,

指定 true 创建唯一索引。默认值为 **false**.

sparse

Boolean

对文档中不存在的字段数据不启用索引；这个参数需要特别注意，如果设置为true的话，在索引字段中不会查询出不包含对应字段的文档.。默认值为 **false**.

expireAfterSeconds

integer

指定一个以秒为单位的数值，完成 TTL设定，设定集合的生存时间。

v

index version

索引的版本号。默认的索引版本取决于mongod创建索引时运行的版本。

weights

document

索引权重值，数值在 1 到 99,999 之间，表示该索引相对于其他索引字段的得分权重。

default_language

string

对于文本索引，该参数决定了停用词及词干和词器的规则的列表。 默认为英语

language_override

string

对于文本索引，该参数指定了包含在文档中的字段名，语言覆盖默认的language，默认值为 language.

**实例\****2**

在后台创建索引：

通过在创建索引时加 background:true 的选项，让创建工作在后台执行

**查看集合索引**

**查看集合索引大小**

**删除集合所有索引**

**删除集合指定索引**

**聚合查询**

MongoDB中聚合(aggregate)主要用于处理数据(诸如统计平均值,求和等)，并返回计算后的数据结果。 有点类似sql语句中的 count(*)。利用Aggregate聚合管道可以完成。MongoDB的聚合管道将MongoDB文档在一个管道处理完毕后将结果传递给下一个管道处理。管道操作是可以重复的。

表达式：处理输入文档并输出。表达式是无状态的，只能用于计算当前聚合管道的文档，不能处理其它 的文档。

基本语法为：

db.COLLECTION_NAME.aggregate(AGGREGATE_OPERATION)" src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1838">

为了便于理解，先将常见的mongo的聚合操作和mysql的查询做下类比：

**表达式**

**SQL\****操作**

**描述**

$group

group by

分组

$sum

count()、sum()

计算总和。

$avg

avg()

计算平均值

$min

min()

获取集合中所有文档对应值得最小值。

$max

max()

获取集合中所有文档对应值得最大值。

$match

where、having

查询条件

$sort

order by

排序

$limit

limit

取条数

$project

select

选择

$lookup （v3.2 新增）

join

连接

下面以一个案例来演示： 集合中的数据如下

- LIST1.aggregate([])

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854cf"), "name" : "zhaoyun1", "age" : 1, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d0"), "name" : "zhaoyun2", "age" : 2, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d1"), "name" : "zhaoyun3", "age" : 3, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d2"), "name" : "zhaoyun4", "age" : 4, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d3"), "name" : "zhaoyun5", "age" : 5, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d4"), "name" : "zhaoyun6", "age" : 6, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d5"), "name" : "zhaoyun7", "age" : 7, "city" : "TJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d6"), "name" : "zhaoyun8", "age" : 8, "city" : "TJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d7"), "name" : "zhaoyun9", "age" : 9, "city" : "TJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d8"), "name" : "zhaoyun10", "age" : 10, "city" : "TJ"}

**$group**

按照城市分组对年龄进行求和

db.LIST1.aggregate([{$group : {_id : "$city", snum : {$sum : "$age"}}}]) { "_id" : "TJ", "snum" : 34 } { "_id" : "BJ", "snum" : 21 } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1839">

**$sum**

按照城市分组对年龄进行求和

db.LIST1.aggregate([{$group : {_id : "$city", snum : {$sum : "$age"}}}]) { "_id" : "TJ", "snum" : 34 } { "_id" : "BJ", "snum" : 21 } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1840">

按照城市分组求总个数

db.LIST1.aggregate([{$group : {_id : "$city", sc : {$sum : 1}}}]) { "_id" : "TJ", "sc" : 4 } { "_id" : "BJ", "sc" : 6 } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1841">

**$avg**

按照城市分组对年龄进行求平均

**$min**

按照城市分组获得每组最小年龄

db.LIST1.aggregate([{$group : {_id : "$city", min : {$min : "$age"}}}]) { "_id" : "TJ", "min" : 7 } { "_id" : "BJ", "min" : 1 } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1842">

**$max**

按照城市分组获得每组最大年龄

db.LIST1.aggregate([{$group : {_id : "$city", max : {$max : "$age"}}}]) { "_id" : "TJ", "max" : 10 } { "_id" : "BJ", "max" : 6 } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1843">

**$match**

取年龄大于3并且小于10的记录再按照城市分组求个数和

db.LIST1.aggregate( [{ $match : { age : { $gt : 3, $lte : 10 } } },{ $group: { _id: "$city",count: { $sum: 1 } } }] ); { "_id" : "TJ", "count" : 4 } { "_id" : "BJ", "count" : 3 } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1844">

**$sort**

按照年龄的倒序显示

- LIST1.aggregate( [{ $sort:{ age :-1}}] )

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d8"), "name" : "zhaoyun10", "age" : 10, "city" : "TJ"}

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d7"), "name" : "zhaoyun9", "age" : 9, "city" : "TJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d6"), "name" : "zhaoyun8", "age" : 8, "city" : "TJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d5"), "name" : "zhaoyun7", "age" : 7, "city" : "TJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d4"), "name" : "zhaoyun6", "age" : 6, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d3"), "name" : "zhaoyun5", "age" : 5, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d2"), "name" : "zhaoyun4", "age" : 4, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d1"), "name" : "zhaoyun3", "age" : 3, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854d0"), "name" : "zhaoyun2", "age" : 2, "city" : "BJ" }

{ "_id" : ObjectId("5cceaf55e7a4cf28dc5854cf"), "name" : "zhaoyun1", "age" : 1, "city" : "BJ" }

#### $limit

取得前5条数据

db.LIST1.aggregate( { $limit:5} ) { "_id" : ObjectId("5cceaf55e7a4cf28dc5854cf"), "name" : "zhaoyun1", "age" : 1, "city" : "BJ" } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d0"), "name" : "zhaoyun2", "age" : 2, "city" : "BJ" } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d1"), "name" : "zhaoyun3", "age" : 3, "city" : "BJ" } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d2"), "name" : "zhaoyun4", "age" : 4, "city" : "BJ" } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d3"), "name" : "zhaoyun5", "age" : 5, "city" : "BJ" } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1845">

**$project**

不显示_id字段 显示 name和city字段

- LIST1.aggregate( [ {$project :{_id:0,name:1,city:1} } ] )

{ "name" : "zhaoyun1", "city" : "BJ" }

{ "name" : "zhaoyun2", "city" : "BJ" }

{ "name" : "zhaoyun3", "city" : "BJ" }

{ "name" : "zhaoyun4", "city" : "BJ" }

{ "name" : "zhaoyun5", "city" : "BJ" }

{ "name" : "zhaoyun6", "city" : "BJ" }

{ "name" : "zhaoyun7", "city" : "TJ" }

{ "name" : "zhaoyun8", "city" : "TJ" }

{ "name" : "zhaoyun9", "city" : "TJ" }

{ "name" : "zhaoyun10", "city" : "TJ" }

#### $lookup

db.sex.find() { "_id" : 1, "name" : "male" } { "_id" : 2, "name" : "female" } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1846">

用于多文档关联，类似于SQL的多表关联建立新的集合

给LIST1新增字段sex

db.LIST1.update({},{$set:{sex:1}},{multi:1}) WriteResult({ "nMatched" : 10, "nUpserted" : 0, "nModified" : 10 }) > db.LIST1.find() { "_id" : ObjectId("5cceaf55e7a4cf28dc5854cf"), "name" : "zhaoyun1", "age" : 1, "city" : "BJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d0"), "name" : "zhaoyun2", "age" : 2, "city" : "BJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d1"), "name" : "zhaoyun3", "age" : 3, "city" : "BJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d2"), "name" : "zhaoyun4", "age" : 4, "city" : "BJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d3"), "name" : "zhaoyun5", "age" : 5, "city" : "BJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d4"), "name" : "zhaoyun6", "age" : 6, "city" : "BJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d5"), "name" : "zhaoyun7", "age" : 7, "city" : "TJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d6"), "name" : "zhaoyun8", "age" : 8, "city" : "TJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d7"), "name" : "zhaoyun9", "age" : 9, "city" : "TJ", "sex" : 1 } { "_id" : ObjectId("5cceaf55e7a4cf28dc5854d8"), "name" : "zhaoyun10", "age" : 10, "city" : "TJ", "sex" : 1 } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1847">

将两个文档关联

- LIST1.aggregate([{$lookup:

{from:"sex",localField:"sex",foreignField:"_id",as:"docs"}},{$project:

{"_id":0}}])

{ "name" : "zhaoyun1", "age" : 1, "city" : "BJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun2", "age" : 2, "city" : "BJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun3", "age" : 3, "city" : "BJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun4", "age" : 4, "city" : "BJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun5", "age" : 5, "city" : "BJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun6", "age" : 6, "city" : "BJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun7", "age" : 7, "city" : "TJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun8", "age" : 8, "city" : "TJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun9", "age" : 9, "city" : "TJ", "sex" : 1, "docs" : [ { "_id" : 1, "name" : "male" } ] }

{ "name" : "zhaoyun10", "age" : 10, "city" : "TJ", "sex" : 1, "docs" : [ { "_id"

: 1, "name" : "male" } ] }

LIST1中的数据字段sex关联sex中的数据字段_id，在LIST1中显示sex对应的中文，显示在docs中，不显示LIST1中的 id

下面简单介绍一些$lookup中的参数： from：需要关联的表【sex】localField: 【LIST1】表需要关联的键。

foreignField：【sex】的matching key 主键

as：对应的外键集合的数据，【因为是一对多的】

**MongoDB Java\****客户端**

marphia：基于mongodb的ORM框架

spring-data-mongodb：spring提供的mongodb客户端mongodb-java-driver：官方驱动包

**mongodb-java-API**

**POM\****依赖**

**测试代码**

public class MongodbDemo {

private MongoClient client;

@Before

public void init() {

client = new MongoClient("192.168.10.135",27017);

}

@Test

public void connectDB() {

// 连接数据库，你需要指定数据库名称，如果指定的数据库不存在，mongo会自动创建数据库。MongoDatabase database = client.getDatabase("kkb"); System.out.println("connect successful--------------------------------------------- "+database.getName());

}

@Test

public void createCollection() {

MongoDatabase database = client.getDatabase("kkb"); database.createCollection("mycollection"); System.out.println("集合创建成功");

}

@Test

public void getCollection() {

MongoDatabase database = client.getDatabase("kkb"); MongoCollection collection =

database.getCollection("mycollection");

System.out.println(" 获 取 集 合 成 功 ："+collection.getNamespace()); MongoIterable collectionNames = database.listCollectionNames(); for (String string : collectionNames) {

System.out.println("collection name : "+ string);

}

}

@Test

public void insertDocument() {

MongoDatabase database = client.getDatabase("kkb"); MongoCollection collection =

database.getCollection("mycollection");

Document document = new Document("name", "James"); document.append("age", 34);

document.append("sex", "男");

Document document2 = new Document("name", "wade"); document2.append("age", 36); document2.append("sex", "男");

Document document3 = new Document("name", "cp3"); document3.append("age", 32); document3.append("sex", "男");

List documents = new ArrayList<>(); documents.add(document); documents.add(document2); documents.add(document3);

collection.insertMany(documents);

System.out.println("文档插入成功");

}

@Test

public void findDocuments() {

MongoDatabase database = client.getDatabase("kkb"); MongoCollection collection =

database.getCollection("mycollection");

FindIterable iterable = collection.find(); for (Document document : iterable) {

System.out.println(document.toJson());

}

// MongoCursor mongoCursor = iterable.iterator();

// while (mongoCursor.hasNext()) {

// Document document = mongoCursor.next();

// System.out.println(document.toJson());

// }

}

@Test

public void updateDocuments() {

MongoDatabase database = client.getDatabase("kkb"); MongoCollection collection =

database.getCollection("mycollection");

collection.updateMany(Filters.eq("age",18), new Document("$set", new Document("age", 20)));

FindIterable iterable = collection.find(); for (Document document : iterable) {

System.out.println(document.toJson());

}

}

@Test

public void deleteDocuments() {

MongoDatabase database = client.getDatabase("kkb"); MongoCollection collection =

database.getCollection("mycollection");

// 删除符合条件的第一个文档collection.deleteOne(Filters.eq("age",20)); collection.deleteOne(Filters.eq("name","James"));

// 删除符合条件的所有文档

collection.deleteMany(Filters.eq("age",20));

FindIterable iterable = collection.find(); for (Document document : iterable) {

System.out.println(document.toJson());

}

}

/*

\* 查询

*/ @Test

public void testQuery1() {

MongoCollection collection = db.getCollection("LIST1");

//获取第一条记录

Document first = collection.find().first();

//根据key值获得数据System.out.println(first.get("name"));

//查询指定字段

Document qdoc=new Document(); qdoc.append("_id", 0); //0是不包含字段qdoc.append("name", 1);//1是包含字段

FindIterable findIterable = collection.find()

.projection(qdoc);

for (Document document : findIterable) { System.out.println(document.toJson());

}

}

@Test

public void testQuery2() {

MongoCollection collection = db.getCollection("LIST1");

//按指定字段排序

Document sdoc=new Document();

//按年龄的倒序sdoc.append("age", -1);

FindIterable findIterable = collection.find()

.sort(sdoc);

for (Document document : findIterable) { System.out.println(document.toJson());

}

}

}

### Filters

利用Filters可以进行条件过滤，需要引入com.mongodb.client.model.Filters类，比如：eq、ne、gt、 lt、gte、lte、in、nin、and、or、not、nor、exists、type、mod、regex、text、where、all、elemMatch、size、bitsAllClear、bitsAllSet、bitsAnyClear、bitsAnySet、geoWithin、geoWithin、geoWithinBox、geoWithinPolygon、geoWithinCenter、geoWithinCenterSphere、geoIntersects、 near、nearSphere 等。

主要介绍几个常用的过滤操作。

for (Document document : findIterable) { System.out.println(document.toJson());

}

}

@Test

public void testFilters2() {

MongoCollection collection = db.getCollection("LIST1");

//获得age大于3的记录

FindIterable findIterable =collection.find(Filters.gt("age",

3));

for (Document document : findIterable) { System.out.println(document.toJson());

}

}

@Test

public void testFilters3() {

MongoCollection collection = db.getCollection("LIST1");

//获得age大于3的记录 或者 name=zhaoyun1 FindIterable findIterable

=collection.find(Filters.or(Filters.eq("name", "zhaoyun1"),Filters.gt("age", 3)));

for (Document document : findIterable) { System.out.println(document.toJson());

}

}

### BasicDBObject

利用BasicDBObject可以实现模糊查询和分页查询

@Test

public void testLike() {

MongoCollection collection = db.getCollection("LIST1");

//利用正则 模糊匹配 包含zhaoyun1

Pattern pattern = Pattern.compile("^.*zhaoyun1.*$", Pattern.CASE_INSENSITIVE);

BasicDBObject query = new BasicDBObject();

//name 包 含 zhaoyun1 query.put("name",pattern);

FindIterable findIterable = collection

.find(query);

for (Document document : findIterable) { System.out.println(document.toJson());

}

}

@Test

public void testPage() {

MongoCollection collection = db.getCollection("LIST1");

//利用正则 模糊匹配 name包含zhaoyun1

Pattern pattern = Pattern.compile("^.*zhaoyun.*$", Pattern.CASE_INSENSITIVE);

//查询条件

BasicDBObject query = new BasicDBObject(); query.put("name",pattern);

//排序

BasicDBObject sort = new BasicDBObject();

//按年龄倒序sort.put("age", -1);

//获得总条数

System.out.println("共计："+collection.count()+"条记录");

/*

- 取出第1至3条记录
- query : 查询条件
- sort: 排序
- skip : 起始数 从0开始
- limit:取几条

*/

FindIterable findIterable = collection

.find(query).sort(sort).skip(0).limit(3); for (Document document : findIterable) {

System.out.println(document.toJson());

}

}

## spring-data-mongod

### pom依赖

<project xmlns=["http://maven.apache.org/POM/4.0.0](http://maven.apache.org/POM/4.0.0)" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0

http://maven.apache.org/xsd/maven-4.0.0.xsd">

4.0.0

com.kkb

mongodb-demo

0.0.1-SNAPSHOT

org.mongodb

mongo-java-driver

3.10.1

org.springframework.data

spring-data-mongodb

2.1.1.RELEASE

junit

junit

4.12

org.springframework

spring-test

5.0.7.RELEASE

org.apache.maven.plugins

maven-compiler-plugin

3.8.0

1.8

1.8

UTF-8

### spring配置文件

<beans xmlns=["http://www.springframework.org/schema/beans](http://www.springframework.org/schema/beans)" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mongo="http://www.springframework.org/schema/data/mongo" xmlns:repository="http://www.springframework.org/schema/data/repository" xmlns:context="http://www.springframework.org/schema/context"

xsi:schemaLocation=["http://www.springframework.org/schema/beans](http://www.springframework.org/schema/beans) http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-

context.xsd

mongo.xsd

http://www.springframework.org/schema/data/mongo http://www.springframework.org/schema/data/mongo/spring-

http://www.springframework.org/schema/data/repository

http://www.springframework.org/schema/data/repository/spring-repository.xsd">

<!-- 加载mongodb的配置属性文件 -->

<context:property-placeholder location="classpath:mongodb.properties" />

<!-- 扫描持久层 -->

<context:component-scan base-package="com.kkb.mongodb.springdata" />

<!-- 配置mongodb客户端连接服务器的相关信息 -->

<mongo:mongo-client host="${mongo.host}" port="${mongo.port}" id="mongo">

<mongo:client-options

write-concern="${mongo.writeconcern}" connect-timeout="${mongo.connectTimeout}"

socket-keep-alive="${mongo.socketKeepAlive}" />



<!-- mongo:db-factory dbname="database" mongo-ref="mongo" / -->

<mongo:db-factory id="mongoDbFactory" dbname="kkb" mongo-ref="mongo" />

<!-- 配置mongodb的模板类：MongoTemplate -->

### perperties文件

**po\****类**

public class User { private String name; private int age; private String sex;

public String getName() { return name;

}

public void setName(String name) { this.name = name;

}

public int getAge() { return age;

}

public void setAge(int age) { this.age = age;

}

public String getSex() { return sex;

}

public void setSex(String sex) { this.sex = sex;

}

@Override

public String toString() {

return "NBAPlayer [name=" + name + ", age=" + age + ", sex=" + sex +

"]";

}

}

### dao接口和实现类

public interface UserDao {

/**

\* 根据条件查询

*/

public List find(Query query);

/**

\* 根据条件查询一个

*/

public User findOne(Query query);

/**

\* 插入

*/

public void save(User entity);

/**

\* 根据条件 更新

*/

public UpdateResult update(Query query, Update update);

/**

\* 获得所有该类型记录,并且指定了集合名(表的意思)

*/

public List findAll(String collectionName);

/**

\* 根据条件 获得总数

*/

public long count(Query query,String collectionName);

/**

- 根据条件 删除

*

- @param query

*/

public void remove(Query query);

}

@Repository

public class UserDaoImpl implements UserDao {

@Resource

private MongoTemplate template;

@Override

public List find(Query query) { return template.find(query, User.class);

}

@Override

public User findOne(Query query) {

return template.findOne(query, User.class);

}

@Override

public void save(User entity) { template.save(entity);

}

@Override

public UpdateResult update(Query query, Update update) { return template.updateMulti(query, update, User.class);

}

@Override

public List findAll(String collectionName) { return template.findAll(User.class, collectionName);

}

@Override

public long count(Query query,String collectionName) { return template.count(query, collectionName);

}

@Override

public void remove(Query query) { template.remove(query,"user");

}

}

### 测试类

@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration(locations = "classpath:spring-mongodb.xml") public class SpringDataMongodbTest {

@Resource

private UserDao userDao;

@Test

public void test1() {

User user = new User(); user.setName("科比");

user.setAge(26);

userDao.save(user);

User user2 = new User(); user2.setName("詹姆斯"); user2.setAge(33);

userDao.save(user2);

User user3 = new User(); user3.setName("韦德"); user3.setAge(36);

userDao.save(user3);

User user4 = new User(); user4.setName("罗斯"); user4.setAge(30);

userDao.save(user4); System.out.println("插入成功！");

}

@Test

public void test2() {

Query query = new Query(Criteria.where("name").is("科比")); List list = userDao.find(query);

for (User user : list) { System.out.println(user);

}

System.out.println("======================="); List list2 = userDao.find(new Query()); for (User user : list2) {

System.out.println(user);

}

System.out.println("======================="); User user = (User) userDao.findOne(query); System.out.println(user);

}

@Test

public void test3() {

Query query = new Query(Criteria.where("name").is("韦德")); Update update = new Update();

update.set("sex", "纯爷们");

UpdateResult updateResult = userDao.update(query, update); System.out.println(updateResult.getUpsertedId());

}

@Test

public void test4() {

List findAll = userDao.findAll("user"); for (User user : findAll) {

System.out.println(user);

}

}

@Test

public void test5() {

Query query = new Query();

long count = userDao.count(query,"user"); System.out.println("总条数：" + count);

}

@Test

public void test6() {

Query query = new Query(Criteria.where("name").is("科比")); userDao.remove(query);

}

}

# MongoDB架构

MongoDB 是目前主流的 NoSQL (Not Only SQL)数据库之一，与关系型数据库和其它的 NoSQL 不同，

MongoDB 使用了面向文档的数据存储方式，将数据以类似 JSON 的方式（BSON）存储在磁盘上。

MongoDB 其实就与 MySQL 中的架构相差不多，底层都使用了『可插拔』的存储引擎以满足用户的不同需要。

用户可以根据表中的数据特征选择不同的存储引擎，它们可以在同一个 MongoDB 的实例中使用；在最新版本的 MongoDB 中使用了 WiredTiger 作为默认的存储引擎，WiredTiger 提供了不同粒度的并发控制和压缩机制，能够为不同种类的应用提供了最好的性能和存储效率。

在不同的存储引擎上层的就是 MongoDB 的数据模型和查询语言了，与关系型数据库不同，由于MongoDB 对数据的存储与 RDBMS 有较大的差异，所以它创建了一套不同的查询语言；虽然MongoDB 查询语言非常强大，支持的功能也很多，同时也是可编程的，不过其中包含的内容非常繁杂、API 设计也不是非常优雅，所以还是需要一些学习成本的，对于长时间使用 MySQL 的开发者肯定

会有些不习惯。

**RDBMS** **与** **MongoDB\****的区别**

传统的 RDBMS 其实使用 Table 的格式将数据逻辑地存储在一张二维的表中，其中不包括任何复杂的数据结构，但是由于 MongoDB 支持嵌入文档、数组和哈希等多种复杂数据结构的使用，所以它最终将所有的数据以 [BSON](http://bsonspec.org/)的数据格式存储起来。

RDBMS 和 MongoDB 中的概念都有着相互对应的关系，数据库、表、行和索引的概念在两中数据库中

Embedded Document

都非常相似，唯独最后的 JOIN 和 或者 Reference 有着巨大的差别。这一点差

别其实也影响了在使用 MongoDB 时对集合（Collection）Schema 的设计，如果我们在 MongoDB 中遵循了与 RDBMS 中相同的思想对 Collection 进行设计，那么就不可避免的使用很多的 “JOIN” 语句， 而 MongoDB 是不支持 “JOIN” 的，在应用内做这种查询的性能非常非常差，在这时使用嵌入式的文档其实就可以解决这种问题了，嵌入式的文档虽然可能会造成很多的数据冗余导致我们在更新时会很痛 苦，但是查询时确实非常迅速。

举例说明： MySQL： TPeople

**姓名**

**性别**

**年龄**

**住址**

赵云

男

23

1000

TAddress

**编号**

**国家**

**城市**

**街道**

1000

中国

23

海淀区上地三街

MongoDB

{

姓名：“赵云”， 性别：“男”， 年龄：23，

住址:{

编号：1000， 国家：“中国”， 城市："北京"，

街道：“海淀区上地三街”

}

}

每行最大存储量为16M。如果超过16M，则可以存储成GridFS形式。

**MongoDB Wiredtiger\****存储引擎实现原理**

MongoDB2.3后默认采用WiredTiger存储引擎。(之前为MMAPV1引擎)

**Transport Layer\****业务层**

Transport Layer 是处理请求的基本单位。Mongo有专门的 listener 线程，每次有连接进来， listener 会创建一个新的线程conn 负责与客户端交互，它把具体的查询请求交给network 线程，真正到数据库里查询由TaskExecutor 来进行。

**写请求**

WiredTiger的写操作会默认写入Cache ,并持久化到WAL (Write Ahead Log)，每60s或Log文件达到2G 做一次checkpoint ，产生快照文件。WiredTiger初始化时，恢复至最新的快照状态，然后根据WAL恢复数据，保证数据的完整性。 Cache是基于BTree的，节点是一个page，root page是根节点，internal page是中间索引节点，leaf page真正存储数据，数据以page为单位与磁道读写。Wiredtiger采用Copy on write的方式管理修改操作（insert、update、delete），修改操作会先缓存在cache里，持久化

时，修改操作不会在原来的leaf page上进行，而是写入新分配的page，每次checkpoint都会产生一个

新的root page。

**Journaling**

为了在数据库宕机保证 MongoDB 中数据的持久性，MongoDB 使用了 Write Ahead Logging 向磁盘上的 journal 文件预先进行写入；除了 journal 日志，MongoDB 还使用检查点（Checkpoint）来保证数据的一致性，当数据库发生宕机时，我们就需要 Checkpoint 和 journal 文件协作完成数据的恢复工

作：

1. 在数据文件中查找上一个检查点的标识符；
2. 在 journal 文件中查找标识符对应的记录；
3. 重做对应记录之后的全部操作；

MongoDB 会每隔 60s 或者在 journal 数据的写入达到 2GB 时设置一次检查点，当然我们也可以通过在写入时传入 j: true 的参数强制 journal 文件的同步。

**一致性**

1. \1. WiredTiger使用Copy on Write 管理修改操作。修改先放在cache中，并持久化，不直接作用在原leaf page，而是写入新分配的page，每次checkpoint产生新page。 相关文件：

WiredTiger.basecfg: 存储基本配置信息，与ConﬁgServer有关系

WiredTiger.lock: 定义锁操作table*.wt: 存储各张表的数据WiredTiger.wt: 存储table* 的元数据

WiredTiger.turtle: 存储WiredTiger.wt的元数据

journal: 存储WAL

一次Checkpoint的大致流程如下:

对所有的table进行一次Checkpoint，每个table的Checkpoint的元数据更新至WiredTiger.wt 对WiredTiger.wt进行Checkpoint，将该table Checkpoint的元数据更新至临时文件WiredTiger.turtle.set 将WiredTiger.turtle.set重命名为WiredTiger.turtle。 上述过程如中间失败，Wiredtiger在下次连接初始化时，首先将数据恢复至最新的快照状态，然后根据WAL恢复数据，以保证存储可靠性。

**MongoDB\****集群**

**MongoDB\****的高可用**

核心业务99.99%可用，一年宕机时间不超过52分钟。

Mongodb的集群部署方案有**主从部署、副本集（主备）部署、分片部署、副本集与分片混合部署**。

### 主从复制原理

MongoDB Oplog是MongoDB Primary和Secondary在复制建立期间和建立完成之后的复制介质，就是Primary中所有的写入操作都会记录到MongoDB Oplog中，然后从库会来主库一直拉取Oplog并应用到自己的数据库中。这里的Oplog是MongoDB local数据库的一个集合，它是Capped collection，通俗意思就是它是固定大小，循环使用的。如下图：

oplog.rs

\# Primary节点写入数据，Secondary通过读取Primary的oplog得到复制信息，开始复制数据并且将复制信息写入到自己的oplog。

oplog.rs replset.election replset.minvalid

replset.oplogTruncateAfterPoint startup_log

system.replset system.rollback.id

\# 插入的数据

rs001:PRIMARY> db.oplog.rs.find({"op" : "i"}).pretty()

{

"ts" : Timestamp(1561102579, 6), "t" : NumberLong(1),

"h" : NumberLong("-6973786105046479584"), "v" : 2,

"op" : "i",

"ns" : "admin.system.keys",

"ui" : UUID("bdc5bfc9-0038-4f9d-94dd-94d0e3ac6bff"), "wall" : ISODate("2019-06-21T07:36:19.409Z"),

"o" : {

"_id" : NumberLong("6704884522506256385"),

"purpose" : "HMAC",

"key" : BinData(0,"j0z0uU0XF5IomoMXk8rP8pYKTNo="), "expiresAt" : Timestamp(1568878579, 0)

}

}

{

"ts" : Timestamp(1561102580, 1), "t" : NumberLong(1),

"h" : NumberLong("2359103001087607398"), "v" : 2,

"op" : "i",

"ns" : "admin.system.keys",

"ui" : UUID("bdc5bfc9-0038-4f9d-94dd-94d0e3ac6bff"), "wall" : ISODate("2019-06-21T07:36:20.340Z"),

"o" : {

"_id" : NumberLong("6704884522506256386"),

"purpose" : "HMAC",

"key" : BinData(0,"Mg3YjbVxSWqf2hgTvDkEeIoJSvM="), "expiresAt" : Timestamp(1576654579, 0)

}

}

### 副本集集群

##### 对于副本集集群，又有主和从两种角色，写数据和读数据也是不同，写数据的过程是只写到主结点中， 由主 结点以异步的方式同步到从结点中：

**而读数据则只要从任一结点中读取，具体到哪个结点读取是可以指定的：**

**副本集与分片混合部署**

Mongodb的集群部署方案有三类角色：**实际数据存储节点，配置文件存储节点和路由接入节点。实际数据存储节点**的作用就是存储数据，

**路由接入节点**的作用是在分片的情况下起到负载均衡的作用。

**存储配置存储节点**的作用其实存储的是片键与chunk 以及chunk 与server 的映射关系，用上面的数据表 示的配置结点存储的数据模型如下表：

**片键**

对集合进行分片时,你需要选择一个 片键 , shard key 是每条记录都必须包含的,且建立了索引的单个字段或复合字段,MongoDB按照片键将数据划分到不同的 数据块 中,并将 数据块 均衡地分布到所有分片中.为了按照片键划分数据块,MongoDB使用 基于范围的分片方式 或者 基于哈希的分片方式。

**以范围为基础的分片**

对于 基于范围的分片 ,MongoDB按照片键的范围把数据分成不同部分.假设有一个数字的片键:想象一个从负无穷到正无穷的直线,每一个片键的值都在直线上画了一个点.MongoDB把这条直线划分为更短的不重叠的片段,并称之为 数据块 ,每个数据块包含了片键在一定范围内的数据.

**基于哈希的分片**

对于 基于哈希的分片 ,MongoDB计算一个字段的哈希值,并用这个哈希值来创建数据块.

在使用基于哈希分片的系统中,拥有”相近”片键的文档 很可能不会 存储在同一个数据块中,因此数据的分离性更好一些.

map1

**key range**

**chunk**

[0,10}

chunk1

[10,20}

chunk2

[20,30}

chunk3

[30,40}

chunk4

[40,50}

chunk5

map2

**chunk**

**shard**

chunk1

shard1

chunk2

shard2

chunk3

shard3

chunk4

shard4

chunk5

shard5

MongoDB的客户端直接与路由节点相连，从配置节点上查询数据，根据查询结果到实际的存储节点上查询和存储数据。

**副本集与分片混合部署方式如图：**

相同的副本集中的节点存储的数据是一样的，副本集中的节点是分为主节点、从节点、仲裁节点（非必须）三 种角色。【这种设计方案的目的，主要是为了高性能、高可用、数据备份。】

不同的副本集中的节点存储的数据是不一样，【这种设计方案，主要是为了解决高扩展问题，理论上是可以无 限扩展的。】

每一个副本集可以看成一个shard（分片），多个副本集共同组成一个逻辑上的大数据节点。通过对shard上 面进行逻辑分块chunk（块），每个块都有自己存储的数据范围，所以说客户端请求存储数据的时候，会去读 取config server中的映射信息，找到对应的chunk（块）存储数据。

##### 混合部署方式下向MongoDB写数据的流程如图：

**混合部署方式下读\****MongoDB***\*里的数据流程如图：** 按条件查询 查的就是片键 （建立索引）

# MongoDB主从搭建

MongoDB的主从集群，其实官方已经不推荐了，但是要理解主从集群的一些特性：**默认从机是不可操作的，只是作为数据备份的。如果需要从机对外提供读的操作，需要单独发送指令。**

伪分布式搭建：在同一台机器，使用多个不同的端口，去启动多个实例。组成一个分布式系统。

真正的分布式搭建：在不同机器，使用相同的端口，分别启动实例。如果是真正的分布式搭建，一定要 保证网络畅通和防火墙问题。

**新建目录**

**主机配置**

vim /var/mongo-ms/master/mongodb.cfg

logpath=/var/mongo-ms/master/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行

fork=true

\#绑定客户端访问的ip bind_ip=127.0.0.1 # 默认27017

port=27001

\# 主从模式下，指定我自身的角色是主机

master=true

\# 主从模式下，从机的地址信息

source=127.0.0.1:27002

**从机配置**

vim /var/mongo-ms/slave/mongodb.cfg

**测试**

启动服务

连接测试

测试命令

db.isMaster() > use kkb switched to db kkb > db.heros.insert({name:"zhaoyun",age:23}) WriteResult({ "nInserted" : 1 }) > ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1058">

**读写分离**

MongoDB副本集对读写分离的支持是通过Read Preferences特性进行支持的，这个特性非常复杂和灵活。设置读写分离需要先在从节点SECONDARY 设置

show dbs 2019-10-12T01:27:09.718-0700 E QUERY "ok" : 0, ' src="data:image/png;base64,R0lGODlhQwEEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1880">

rs.slaveOk() > show dbs admin 0.000GB config 0.000GB kkb 0.000GB local 0.000GB > use kkb switched to db kkb > db.heros.find() { "_id" : ObjectId("5da18e276f7063c8961ee35b"), "name" : "zhaoyun", "age" : 23 } ' src="data:image/png;base64,R0lGODlhxwIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1057">

**MongoDB\****副本集集群**

副本集中有三种角色：主节点、从节点、仲裁节点仲裁节点不存储数据，主从节点都存储数据。

优点：

主如果宕机，仲裁节点会选举从作为新的主

如果副本集中没有仲裁节点，那么集群的主从切换依然可以进行。缺点：

如果副本集中拥有仲裁节点，那么一旦仲裁节点挂了，集群中就不能进行主从切换了。

**新建目录**

**节点\****1***\*配置**

vim /var/mongo-rs/rs01/node1/mongodb.cfg

\# 数据库文件位置

dbpath=/var/mongo-rs/rs01/node1/data #日志文件位置

logpath=/var/mongo-rs/rs01/node1/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行

fork = true

bind_ip=127.0.0.1 # 默认27017

port = 27003

\#注意：不需要显式的去指定主从，主从是动态选举的

\#副本集集群，需要指定一个名称，在一个副本集下，名称是相同的replSet=rs001

## 节点2配置

vim /var/mongo-rs/rs01/node2/mongodb.cfg

\# 数据库文件位置

dbpath=/var/mongo-rs/rs01/node2/data #日志文件位置

logpath=/var/mongo-rs/rs01/node2/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行fork = true bind_ip=127.0.0.1

\# 默认27017

port = 27004

\#注意：不需要显式的去指定主从，主从是动态选举的

\#副本集集群，需要指定一个名称，在一个副本集下，名称是相同的replSet=rs001

## 节点3配置

vim /var/mongo-rs/rs01/node3/mongodb.cfg

启动副本集节点

**配置主备和仲裁**

需要登录到mongodb的客户端进行配置主备和仲裁角色。

**注意创建\****dbpath***\*和\****logpath**

mongo 127.0.0.1:27003

use admin

cfg={_id:"rs001",members: [

{_id:0,host:"127.0.0.1:27003",priority:2},

{_id:1,host:"127.0.0.1:27004",priority:1},

{_id:2,host:"127.0.0.1:27005",arbiterOnly:true}

]}

rs.initiate(cfg);

说明：

cfg 中 的 _id 的 值 是 【 副 本 集 名 称 】 priority：数字越大，优先级越高。优先级最高的会被选举为主库arbiterOnly:true，如果是仲裁节点，必须设置该参数

**测试**

**无仲裁副本集**

和有仲裁的副本集基本上完全一样，只是在admin数据库下去执行配置的时候，不需要指定优先级和仲裁节点。这种情况，如果节点挂掉，那么他们都会进行选举。

新建目录

[root@localhost var]# mkdir mongo-rs/rs02/node1/data -p [root@localhost var]# mkdir mongo-rs/rs02/node1/logs -p [root@localhost var]# mkdir mongo-rs/rs02/node2/data -p [root@localhost var]# mkdir mongo-rs/rs02/node2/logs -p [root@localhost var]# mkdir mongo-rs/rs02/node3/data -p [root@localhost var]# mkdir mongo-rs/rs02/node3/logs -p

## 节点1配置

vim /var/mongo-rs/rs02/node1/mongodb.cfg

**节点\****2***\*配置**

vim /var/mongo-rs/rs02/node2/mongodb.cfg

\# 数据库文件位置

dbpath=/var/mongo-rs/rs02/node2/data #日志文件位置

logpath=/var/mongo-rs/rs02/node2/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行fork = true bind_ip=127.0.0.1

\# 默认27017

port = 27007

\#注意：不需要显式的去指定主从，主从是动态选举的

\#副本集集群，需要指定一个名称，在一个副本集下，名称是相同的replSet=rs002

## 节点3配置

vim /var/mongo-rs/rs02/node3/mongodb.cfg

\# 数据库文件位置

dbpath=/var/mongo-rs/rs02/node3/data #日志文件位置

logpath=/var/mongo-rs/rs02/node3/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行fork = true bind_ip=127.0.0.1

\# 默认27017

port = 27008

\#注意：不需要显式的去指定主从，主从是动态选举的

\#副本集集群，需要指定一个名称，在一个副本集下，名称是相同的replSet=rs002

# MongoDB混合方式集群

## 部署图

**数据服务器配置**

### 副本集1的配置

在副本集中每个数据节点的mongodb.cfg配置文件【追加】以下内容（**仲裁节点除外**）：

\# 数据库文件位置

dbpath=/var/mongo-rs/rs01/node2/data #日志文件位置

logpath=/var/mongo-rs/rs01/node2/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行

fork = true

bind_ip=127.0.0.1 # 默认27017

port = 27004

\#注意：不需要显式的去指定主从，主从是动态选举的

\#副本集集群，需要指定一个名称，在一个副本集下，名称是相同的replSet=rs001

### 副本集2的配置

在副本集中每个数据节点的mongodb.cfg配置文件【追加】以下内容（**仲裁节点除外**）：

\# 数据库文件位置

dbpath=/var/mongo-rs/rs02/node3/data #日志文件位置

logpath=/var/mongo-rs/rs02/node3/logs/mongodb.log # 以追加方式写入日志

logappend=true

\# 是否以守护进程方式运行

fork = true

bind_ip=127.0.0.1 # 默认27017

port = 27008

\#注意：不需要显式的去指定主从，主从是动态选举的

\#副本集集群，需要指定一个名称，在一个副本集下，名称是相同的replSet=rs002

shardsvr=true

## 配置服务器配置(先启动配置集再启动数据副本集)

配置两个配置服务器，配置信息如下，端口和path单独指定：

**注意创建\****dbpath***\*和\****logpath**

[root@localhost var]# mkdir mongo-conf/node1/logs -p

[root@localhost var]# mkdir mongo-conf/node2/data -p [root@localhost var]# mkdir mongo-conf/node2/logs -p

\#先启动配置集再启动数据副本集#启动配置集

mongod -f /var/mongo-conf/node1/mongodb.cfg mongod -f /var/mongo-conf/node2/mongodb.cfg

\#启动数据副本集--shard1

mongod -f /var/mongo-rs/rs01/node1/mongodb.cfg mongod -f /var/mongo-rs/rs01/node2/mongodb.cfg mongod -f /var/mongo-rs/rs01/node3/mongodb.cfg

\#启动数据副本集--shard2

mongod -f /var/mongo-rs/rs02/node1/mongodb.cfg mongod -f /var/mongo-rs/rs02/node2/mongodb.cfg mongod -f /var/mongo-rs/rs02/node3/mongodb.cfg

**配置副本集**

**路由服务器配置**

路由服务器启动（**注意这里是\****mongos***\*命令而不是\****mongod***\*命令**）

### 关联切片和路由

登录到路由服务器中，执行关联切片和路由的相关操作。

sh.addShard("切片名称/地址")

sh.addShard("rs001/127.0.0.1:27003"); sh.addShard("rs002/127.0.0.1:27006");

use kkb sh.enableSharding("kkb"); #新的集合

\#

# sh.shardCollection(".", { : } ) # 为主键字段的名字。

# 为以下3种：“1”：主键值正向遍历；

\#

# “-1”：主键值反向遍历；

\#

# “hashed”：主键hash值

\#

sh.shardCollection("kkb.citem1",{name:"hashed"});

for(var i=1;i<=1000;i++) db.citem1.insert({name:"iphone"+i,num:i}); #分片效果

mongos> db.citem1.count() 1000

mongo 127.0.0.1:27003

use kkb

rs001:PRIMARY> db.citem1.count() 516

\#从库

mongo 127.0.0.1:27004

use kkb

db.getMongo().setSlaveOk() # 设置从库rs001:SECONDARY> db.citem1.count() 516

mongo 127.0.0.1:27006

use kkb db.citem1.count() 484

# MongoDB数据稳定性

## MongoDB丢失数据问题

坊间流传MongoDB会丢失数据？

已经在MongoDB2.6后得到妥善的解决。

问题产生：MongoDB2.4版本问题或用户配置问题

**如何解决**

**恢复日志（\****journaling***\*）**

类似MySQL的RedoLog作用，为了在数据库宕机保证 MongoDB 中数据的持久性，MongoDB 使用了Write Ahead Logging 向磁盘上的 journal 文件预先进行写入；除了 journal 日志，MongoDB 还使用检查点（Checkpoint）来保证数据的一致性，当数据库发生宕机时，我们就需要 Checkpoint 和journal 文件协作完成数据的恢复工作。这个参数在2.0之前默认是不开启的。

**写关注\****(Write Concern)**

{w:0} : Unacknowledged 不返回是否成功的状态值

{w:1} ： Acknowledged 基于主节点的内存写入

{w:2} : majority 写入大多数节点(推荐)

**配置方法**

{w:2} majority {j:1} journaled

**MongoDB\****的应用场景和不适用场景**

**适用场景**

**更高的写入负载**

默认情况下，MongoDB更侧重高数据写入性能，而非事务安全，MongoDB很适合业务系统中有大 量“低价值”数据的场景。但是应当避免在高事务安全性的系统中使用MongoDB，除非能从架构设计上保证事务安全。

日志、简历、帖子等。

**高可用性**

MongoDB的复副集(ReplSet)配置非常简洁方便，此外，MongoDB可以快速响应的处理单节点故障， 自动、安全的完成故障转移。这些特性使得MongoDB能在一个相对不稳定（如云主机）的环境中，保持高可用性。

**数据量很大或者未来会变得很大**

依赖数据库(MySQL)自身的特性，完成数据的扩展是较困难的事，**在\****MySQL***\*中，当一个单表达到\****5- 10GB***\*时会出现明显的性能降级**，此时需要通过数据的水平和垂直拆分、库的拆分完成扩展，使用 MySQL通常需要借助驱动层或代理层完成这类需求。而MongoDB内建了多种数据分片的特性，可以很好的适应大数据量的需求。

**基于位置的数据查询**

MongoDB支持二维空间索引，因此可以快速及精确的从指定位置获取数据。

**表结构不明确，且数据在不断变大**

在一些传统RDBMS中，增加一个字段会锁住整个数据库/表，或者在执行一个重负载的请求时会明显造成其它请求的性能降级。通常发生在数据表大于1G的时候（当大于1TB时更甚）。 因MongoDB是文档型数据库，为非结构货的文档增加一个新字段是很快速的操作，并且不会影响到已有数据。另外一个好 处当业务数据发生变化时，是将不在需要由DBA修改表结构。

**没有\****DBA***\*支持**

如果没有专职的DBA，并且准备不使用标准的关系型思想（结构化、连接等）来处理数据，那么MongoDB将会是你的首选。MongoDB对于对像数据的存储非常方便，类可以直接序列化成JSON存储 到MongoDB中。 但是需要先了解一些最佳实践，避免当数据变大后，由于文档设计问题而造成的性能缺陷。

**不适用场景**

在某些场景下，MongoDB作为一个非关系型数据库有其局限性。MongoDB不支持事务操作，所以需要用到事务的应用建议不用MongoDB，另外MongoDB目前不支持join操作，需要复杂查询的应用也不建议使用MongoDB。

**MongoDB\****文档设计**

**文档设计（\****Collection***\*）**

文档中的key，禁止使用_以外的特殊字符

设置_id :表示主键 ，如果不设置则自动生成一个12个字节的ObjectID 不要让数组成为查询条件

数据统一大小写

key尽可能短小，可以使用程序做映射

比如: {name:"赵云"}----- > {N:"赵云"}

程序映射:

**_id\****的生成**

MongoDB的ID生成Collection级doc的唯一标识

_id在每个Collection内部唯一

是Collection的主键，必须要有

默认是ObjectID类型，有12个字节（4字节时间戳+3字节机器ID+2字节进程ID+3字节计数器） 存储空间较大

在服务端生成，影响服务器性能

_id的优化：

在客户端生成，在业务层统一生成

可根据业务情况选择合适的ID替代_id,比如oroderId、userId等减少位数，减少存储空间

可使用通用的id生成器 如： snowﬂake

**字段名的选取**

字段名尽可能短做个实验:

16亿条记录通过减少字段名

name--->N、age-->A、sex--->S等存储空间减少243G减少到183G

**Collection\****结构设计**

**在同一个\****Collection***\*中，内嵌文档**

一对较少数据场景

比如用户对地址、电话、考试成绩等

**Collection\****关联，引用**

一对较多场景

比如用户和订单信息、用户和武将牌等

> db.user.find({name:"Jack"}).skip(1).limit(1).pretty()

{

"_id":1000,"name":"Jack","order":{1,2,3}

}

> db.order.find().pretty()

{"_id":1,"orderName":"Iphone","price":8000.00,"orderNum":1}

{"_id":2,"orderName":"Mac","price":18000.00,"orderNum":1}

{"_id":3,"orderName":"IWatch","price":5000.00,"orderNum":1}

日志场景

Hosts和Logs

db.hosts.find.pretty() { "_id":1000, "hostname":"bj_10_10", "ip":"192.168.12.12", "SN":"EQ82003", "addr":"BJM06" } >db.logs.find({hostid:"1000"}).pretty() { "_id":1, "hostid":"1000", "time":2018-10-10 12:00:00, "loginfo":"login" } { "_id":2, "hostid":"1000", "time":2018-10-10 12:00:11, "loginfo":"update goods" } { "_id":3, "hostid":"1000", "time":2018-10-10 12:00:15, "loginfo":"logout" } ' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1918">

总结：优先使用内嵌，如果记录过多则采用关联。

**MongoDB\****在项目中遇到的问题**

**大量删除数据**

问题背景：删除离线IM消息，已读取、未做物理删除，需要删除已读取的历史数据

_id: 主 键 fromUid：发送消息的用户id toUid：接收消息的用户id msgContent：消息内容timestamp：时间戳

ﬂag：离线消息是否读取 0：未读 1：已读

toUid为索引

5000万数据，100G存储空间操作：

执行删除已读的数据

db.message.remove({"flag":1})' src="data:image/png;base64,R0lGODlh4AIEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAAAAAABAAEAgAAAAAECAwICRAEAOw==" v:shapes="_x0000_s1919">

产生问题：

晚上10点后执行到早晨7点还没有执行完成，从库延时大，业务无法响应分析原因：

ﬂag不是索引会造成全表扫描删除速度很慢

冷数据和热数据不断swap，性能急剧下降解决方案：

紧急方案：kill 掉正在执行的op

长期方案：

1、在业务层已读的数据直接做物理删除

2、现有数据做离线删除，在从库导出ﬂag=1的数据，该数据是有主键的(_id)，通过脚本根据主键在主库进行批量删除后从库同步主库数据。

在低峰期（0点-6点）执行脚本并控制删除速度。

**大量数据空洞**

问题背景：数据被大量删除后，swap频繁，MongoDB性能下降。 分析原因：

数据被大量删除后，会形成数据空洞，MongoDB将数据加载到内存中时，会加载这些数据空洞，造成 空间的浪费，而有意义的数据不能加载到内存，会造成大量的内存与硬盘的swap，MongoDB性能会大幅下降。

解决方案：

1、在线压缩数据(Online Compress)

MongoDB提供在线压缩数据的命令compact，是Collection级别的压缩。可以去除Collection的文件碎片。

> db.message.runCommand("compact")

问题：

影响服务性能

压缩效果较差，收缩率33%左右不推荐使用

2、收缩数据库

原理：从库复制主库数据时，不会复制空数据。操作步骤：

1、停掉从库的进程

2、删除从库的数据

3、从库 从主库同步数据

4、把从库提升为主库,对主库做降级

5、把原来的主库停掉

6、删除原来的主库的数据

7、启动原来的主库，并从新的主库从同步数据

问题：

单点操作有风险，最好做一主两从或多从

从库 从主库同步数据会造成主库性能下降，所以该操作适合在业务低峰期(0点--6点)进行收缩效果：

完全无碎片，收缩率100%。

**大厂常见面试题解析**

存储引擎的InnoDB与MyISAM的区别，优缺点，使用场景？ 说说 MySQL 优化之道？

性能分析+性能优化

UndoLog和RedoLog的区别和联系？

MySQL索引的数据结构是什么，及为什么使用这种数据结构？ 索引失效的场景有哪些？

什么是死锁和死锁的排查和解决？

innodb的事务与日志的实现方式？

RC和RR的实现原理及区别和使用场景？ MySQL的复制原理以及流程

什么是MVCC，有什么好处，如何实现？

一个6亿的表a，一个3亿的表b，通过外间tid关联，你如何最快的查询出满足条件的第50000到第50200 中的这200条数据记录。

1、如果A表TID是自增长,并且是连续的,B表的ID为索引

select * from a,b where a.tid = b.id and a.tid>500000 limit 200; 2、如果A表的TID不是连续的,那么就需要使用覆盖索引.TID要么是主键,要么是辅助索引,B表ID也需要有索引。

select * from b , (select tid from a limit 50000,200) a where b.id = a .tid;

分库与分表带来的分布式困境与应对之策？

Redis的事务原理是什么？ Redis事务为什么是“弱”事务？

Redis为什么没有回滚操作？

在Redis中整合lua有几种方式，你认为哪种更好，为什么？

lua如何解决Redis并发问题？ Redis链表实现结构是怎样的？ Redis链表在使用中的优势有哪些？ 如何提高链表的查询性能？

为什么Redis是单线程运行的性能还特别高？

介绍Redis下的分布式锁实现原理、优势劣势和使用场景

Redis-Cluster和Redis主从+哨兵的区别，你认为哪个更好，为什么Redis的主从同步机制是什么?

什么情况下会造成缓存穿透，如何解决？

什么情况下会造成缓存雪崩，如何解决？

什么情况下会造成数据库与Redis缓存数据不一致，如何解决?

MySQL 里有 2000w 数据，Redis 中只存 20w 的数据，如何保证 Redis 中的数据都是热点数据？

MySQL与MongoDB之间最基本的差别是什么? MongoDB的适用场景有哪些？

Mongodb存储特性与内部原理?

什么是副本集?有哪几种有什么区别

Objectld的组成?

MongoDB中的索引是什么?如何使用MongoDB中的分片是如何实现的？ MongoDB如何保证数据的稳定性

什么是MongoDB的混合集群，有什么优势 三高（高可用（主从），高扩展（分片），高性能（读写分离））

# 面试题

##### 1.存储引擎的InnoDB与MyISAM的区别，优缺点，使用场景？

**存储引擎的选型**：

**InnoDB**：支持事务处理，支持外键，支持崩溃修复能力和并发控制。如果需要对事务的完整性要求比较高（比如银行），要求实现并发控制（比如售票），那选择InnoDB有很大的优势。如果需要频繁的更新、删除操作的数据库，也可以选择InnoDB，因为支持事务的提交（commit）和回滚（rollback）。

**MyISAM**：插入数据快，空间和内存使用比较低。如果表主要是用于插入新记录和读出记录，那么选择MyISAM能实现处理高效率。如果应用的完整性、并发性要求比 较低，也可以使用。

**MEMORY**：所有的数据都在内存中，数据的处理速度快，但是安全性不高。如果需要很快的读写速度，对数据的安全性要求较低，不需要持久保存，可以选择MEMOEY。它对表的大小有要求，不能建立太大的表。所以，这类数据库只使用在相对较小的数据库表。

注意，同一个数据库也可以使用多种存储引擎的表。如果一个表要求比较高的事务处理，可以选择InnoDB。这个数据库中可以将查询要求比较高的表选择MyISAM存储。如果该数据库需要一个用于查询的临时表，可以选择MEMORY存储引擎。

##### 2、说说 MySQL 优化之道？

1. 首先需要使用【慢查询日志】功能，去获取所有查询时间比较长的SQL语句
2. 其次【查看执行计划】查看有问题的SQL的执行计划 explain
3. 最后可以使用【show profile[s]】 查看有问题的SQL的性能使用情况
4. 设计中间表，一般针对于统计分析功能，或者实时性不高的需求（OLTP、OLAP）为减少关联查询，创建合理的冗余字段（考虑数据库的三范式和查询性能的取舍，创建冗余字段还需要注意数据一致性问题）
5. 对于字段太多的大表，考虑拆表（比如一个表有100多个字段） 人和身份证对于表中经常不被使用的字段或者存储数据比较多的字段，考虑拆表（比如商品表中会存储商品介绍，此时可以将商品介绍字段单独拆解到另一个表中，使用商品ID关联）
6. 索引优化

where 字段 、组合索引 （最左前缀） 、 索引下推 （非选择行 不加锁） 、索引覆盖（不回表）、on两边、排序 、分组统计 、不要用 * 、 LIMIT优化、小结果集关联大结果集

##### 3. UndoLog和RedoLog的区别和联系？

**Redo log Buffer\****重做日志缓冲**

原子性，持久性和一致性主要是通过redo log、undo log和Force Log at Commit机制机制来完成的。redo log用于在崩溃时恢复数据，undo log用于对事务的影响进行撤销，也可以用于多版本控制。而Force Log at Commit机制保证事务提交后redo log日志都已经持久化。

重做日志： Redo Log 如果要存储数据则先存储数据的日志 ， 一旦内存崩了 则可以从日志找重做日志保证了数据的可靠性，InnoDB采用了Write Ahead Log（预写日志）策略，即当事务提交时，先写重做日志，然后再择时将脏页写入磁盘。如果发生宕机导致数据丢失，就通过重做日志进行数据恢复，数据库崩溃重启后需要从redo log中把未落盘的脏页数据恢复出来，重新写入磁盘，保证用户的数据不丢失。当然，在崩溃恢复中还需要回滚没有提交的事务。由于回滚操作需要undo日志的支持，undo日志的完整性和可靠性需要redo日志来保证，所以崩溃恢复先做redo恢复数据，然后做undo回滚。

##### 4. MySQL索引的数据结构是什么，及为什么使用这种数据结构？

B+树：所有数据在叶子节点，排序直接输出 hash

##### 5. 索引失效的场景有哪些？

##### 6. 什么是死锁和死锁的排查和解决？

MySQL默认会主动探知死锁，并回滚某一个影响最小的事务。等另一事务执行完成之后，再重新执行该事务。

##### 7. RC和RR的实现原理及区别和使用场景？

MVCC使得数据库读不会对数据加锁，普通的SELECT请求不会加锁，提高了数据库的并发处理能力。借助MVCC，数据库可以实现READ COMMITTED，REPEATABLE READ等隔离级别，用户可以查看当前数据的前一个或者前几个历史版本，保证了ACID中的I特性（隔离性)。

\- 快照读，读取的是记录的可见版本 (有可能是历史版本)，不用加锁。(select)

\- 当前读，读取的是记录的最新版本，并且当前读返回的记录，都会加上锁，保证其他事务不会再并发修改这条记录。

##### 8. 分库与分表带来的分布式困境与应对之策？

当【表的数量】达到了几百上千张表时，众多的业务模块都访问这个数据库，压力会比较大，考虑对其进行分库。

当【表的数据】达到了几千万级别，在做很多操作都比较吃力,所以，考虑对其进行分库或者分表

分库分表需要解决的问题：

1.分布式事务问题

本地事务：ACID

分布式事务：根据百度百科的定义，CAP定理又称CAP原则，指的是在一个分布式系统中，Consistency（一致性）、 Availability（可用性）、Partition tolerance（分区容错性）。一致性是强一致性。CAP理论最多只能同时满足两个。

BASE：基本可用+软状态+最终一致性

2.多个库中表的主键冲突：redis incr命令，snowflake算法跨库join问题建立全局表（每个库都有一个相同的表） 代码表E-R分片（将有ER关系的记录都存储到一个库中）

##### 9. Redis的事务原理是什么？

Redis 的事务是通过MULTI 、EXEC 、DISCARD 和WATCH 这四个命令来完成的。

Redis 的单个命令都是原子性的，所以这里需要确保事务性的对象是命令集合。

Redis 将命令集合序列化并确保处于同一事务的命令集合连续且不被打断的执行

Redis 不支持回滚操作。

Redis 不支持事务回滚（为什么呢）

1、大多数事务失败是因为语法错误或者类型错误，这两种错误，在开发阶段都是可以预见的

2、Redis 为了性能方面就忽略了事务回滚。 （回滚记录历史版本）

Redis中使用lua的好处

1、减少网络开销，在Lua脚本中可以把多个命令放在同一个脚本中运行

2、原子操作，redis会将整个脚本作为一个整体执行，中间不会被其他命令插入。换句话说，编写脚本

的过程中无需担心会出现竞态条件

3、复用性，客户端发送的脚本会永远存储在redis中，这意味着其他客户端可以复用这一脚本来完成同样的逻辑

EVAL命令通过执行redis的eval命令，可以运行一段lua脚本。命令说明：

script参数：是一段Lua脚本程序，它会被运行在Redis服务器上下文中，这段脚本不必(也不应该)定义为一个Lua函数。

numkeys参数：用于指定键名参数的个数。key [key ...]参数： 从EVAL的第三个参数开始算起，使用了numkeys个键（key），表示在脚本中所用到的那些Redis键(key)，这些键名参数可以在Lua中通过全局变量KEYS数组，用1为基址的形式访问( KEYS[1] ， KEYS[2] ，以此类推)。arg [arg ...]参数：可以在Lua中通过全局变量ARGV数组访问，访问的形式和KEYS变量类似(

ARGV[1] 、 ARGV[2] ，诸如此类)。

redis.call()：返回值就是redis命令执行的返回值如果出错，则返回错误信息，不继续执行

redis.pcall()：返回值就是redis命令执行的返回值如果出错，则记录错误信息，继续执行注意事项在脚本中，使用return语句将返回值返回给客户端，如果没有return，则返回nil

SCRIPT命令

SCRIPT FLUSH ：清除所有脚本缓存

SCRIPT EXISTS ：根据给定的脚本校验和，检查指定的脚本是否存在于脚本缓存

SCRIPT LOAD ：将一个脚本装入脚本缓存，返回SHA1摘要，但并不立即运行它

SCRIPT KILL ：杀死当前正在运行的脚本

redis-cli –eval：直接执行lua脚本，利用Redis整合Lua，主要是为了性能以及事务的原子性。因为redis帮我们提供的事务功能太差。

##### 10. Redis链表实现结构是怎样的？

##### 11.如何提高链表的查询性能？

跳表

##### 12. 为什么Redis是单线程运行的性能还特别高？

数据结构 Io多路复用，起子进程初始化

##### 13. 介绍Redis下的分布式锁实现原理、优势劣势和使用场景

利用Redis的单线程特性对共享资源进行串行化处理存在问题

单机：无法保证高可用

主--从：无法保证数据的强一致性，在主机宕机时会造成锁的重复获得

分布式锁的实现方式，基于Redis的set实现分布式锁，基于zookeeper 临时节点的分布式锁

##### 14. Redis-Cluster和Redis主从+哨兵的区别，你认为哪个更好，为什么

**Redis-Cluster**

架构细节:

(1)所有的redis主节点彼此互联(PING-PONG机制),内部使用二进制协议优化传输速度和带宽.

(2)节点的fail是通过集群中超过半数的节点检测失效时才生效.

(3)客户端与redis节点直连,不需要中间proxy层.客户端不需要连接集群所有节点,连接集群中任何一个可用节点即可

(4)redis-cluster把所有的物理节点映射到[0-16383]slot上,cluster 负责维护node<->slot<->value，Redis 集群中内置了 16384个哈希槽，当需要在 Redis 集群中放置一个 key-value 时，redis 先对key 使用 crc16 算法算出一个结果，然后把结果对 16384 求余数，这样每个 key 都会对应一个编号在

0-16383 之间的哈希槽，redis 会根据节点数量大致均等的将哈希槽映射到不同的节点

1、主节点投票，如果超过半数的主都认为某主down了，则该主就down了 （主选择单数）

2、主节点投票，选出挂了的主的从升级为主

集群挂了的情况：

1、半数的主挂了，不能投票生效，则集群挂了

2、挂了的主机的从也挂了，造成slot槽分配不连续（16384不能完全分配），集群就挂了

**哨兵进程的作用**

监控( Monitoring ):哨兵( sentinel ) 会不断地检查你的Master 和Slave 是否运作正常。

提醒( Notification )： 当被监控的某个Redis 节点出现问题时, 哨兵( sentinel ) 可以通过 API 向管理员或者其他应用程序发送通知。

自动故障迁移( Automatic failover )：当一个Master 不能正常工作时，哨兵( sentinel ) 会开始一次自动故障迁移操作

##### 15. Redis的主从同步机制是什么?

Redis 的主从同步，分为**全量同步**和**增量同步**。

只有从机第一次连接上主机是全量同步。

断线重连有可能触发全量同步也有可能是增量同步（ master 判断runid 是否一致）。除此之外的情况都是增量同步

同步快照阶段： Master 创建并发送快照RDB给Slave ， Slave 载入并解析快照。Master 同时将此阶段所产生的新的写命令存储到缓冲区。

同步写缓冲阶段： Master 向Slave 同步存储在缓冲区的写操作命令。

同步增量阶段： Master 向Slave 同步写操作命令。

增量同步

Redis 增量同步主要指** Slave 完成初始化后开始正常工作时， Master 发生的写操作同步到Slave`的过程。

通常情况下， Master 每执行一个写命令就会向Slave 发送相同的写命令，然后Slave 接收并执行。

##### 16. 什么情况下会造成缓存穿透，如何解决？

缓存穿透：一般的缓存系统，都是按照key去缓存查询，如果不存在对应的value，就应该去后端系统查找（比如DB）。如果key对应的value是一定不存在的，并且对该key并发请求量很大，就会对后端系统造成很大的压力。

也就是说，对不存在的key进行高并发访问，导致数据库压力瞬间增大，这就叫做【缓存穿透】。

解决方案：对查询结果为空的情况也进行缓存，缓存时间设置短一点，或者该key对应的数据insert了之后清理缓存。

缓存雪崩：当缓存服务器重启或者大量缓存集中在某一个时间段失效，这样在失效的时候，也会给后端系统(比如DB)带来很大压力。突然间大量的key失效了或redis重启，大量访问数据库

解决方案:1、 key的失效期分散开 不同的key设置不同的有效期2、设置二级缓存3、高可用缓存击穿对于一些设置了过期时间的key，如果这些key可能会在某些时间点被超高并发地访问，是一种非常“热点”的数据。这个时候，需要考虑一个问题：缓存被“击穿”的问题，这个和缓存雪崩的区别在于这里针对某一key缓存，前者则是很多key。缓存在某个时间点过期的时候，恰好在这个时间点对这个Key有大量的并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，这个时候大并发的请求可能会瞬间把后端DB压垮。

解决方案：用分布式锁控制访问的线程，使用redis的setnx互斥锁先进行判断，这样其他线程就处于等待状态，保证不会有大并发操作去操作数据库。

数据写：数据不一致的根源 ： 数据源不一样

如何解决：强一致性很难，追求最终一致性，互联网业务数据处理的特点

高吞吐量 低延迟 数据敏感性低于金融业时序控制是否可行？

先更新数据库再更新缓存或者先更新缓存再更新数据库本质上不是一个原子操作，所以时序控制不可行

保证数据的最终一致性(延时双删)：

1、先更新数据库同时删除缓存项(key)，等读的时候再填充缓存

2、2秒后再删除一次缓存项(key)

3、设置缓存过期时间 Expired Time 比如 10秒 或1小时

4、将缓存删除失败记录到日志中，利用脚本提取失败记录再次删除（缓存失效期过长 7*24）

##### 16. MySQL 里有 2000w 数据，Redis 中只存 20w 的数据，如何保证 Redis 中的数据都是热点数据？

LRU

##### 17. MySQL与MongoDB之间最基本的差别是什么?

传统的 RDBMS 其实使用 Table 的格式将数据逻辑地存储在一张二维的表中，其中不包括任何复杂的数据结构，但是由于 MongoDB 支持嵌入文档、数组和哈希等多种复杂数据结构的使用，所以它最终将所有的数据以 BSON的数据格式存储起来。

RDBMS 和 MongoDB 中的概念都有着相互对应的关系，数据库、表、行和索引的概念在两中数据库中都非常相似，唯独最后的 JOIN 和 Embedded Document 或者 Reference 有着巨大的差别。这一点差别其实也影响了在使用 MongoDB 时对集合（Collection）Schema 的设计，如果我们在 MongoDB 中遵循了与 RDBMS 中相同的思想对 Collection 进行设计，那么就不可避免的使用很多的 “JOIN” 语句，而 MongoDB 是不支持 “JOIN” 的，在应用内做这种查询的性能非常非常差，在这时使用嵌入式的文档其实就可以解决这种问题了，嵌入式的文档虽然可能会造成很多的数据冗余导致我们在更新时会很痛苦，但是查询时确实非常迅速。

##### 17. MongoDB的适用场景有哪些？

默认采用WiredTiger存储引擎、

Transport Layer业务层

Transport Layer 是处理请求的基本单位。Mongo有专门的listener 线程，每次有连接进来，listener 会创建一个新的线程conn 负责与客户端交互，它把具体的查询请求交给network 线程，真正到数据库里查询由TaskExecutor 来进行。

WiredTiger的写操作会默认写入Cache ,并持久化到WAL (Write Ahead Log)，每60s或Log文件达到2G做一次checkpoint ，产生快照文件Journaling

为了在数据库宕机保证 MongoDB 中数据的持久性，MongoDB 使用了 Write Ahead Logging 向磁盘上的 journal 文件预先进行写入；除了 journal 日志，MongoDB 还使用检查点（Checkpoint）来保证数据的一致性，当数据库发生宕机时，我们就需要 Checkpoint 和 journal 文件协作完成数据的恢复工作：

一致性WiredTiger使用Copy on Write 管理修改操作。修改先放在cache中，并持久化，不直接作用在原leaf page，而是写入新分配的page，每次checkpoint产生新page。

##### 18.什么是副本集?有哪几种有什么区别

有仲裁和无仲裁，主从不能自动切，副本集可以自动切

##### 19. Objectld的组成?

12个字节（4字节时间戳+3字节机器ID+2字节进程ID+3字节计数器）

##### 20. MongoDB中的索引是什么?如何使用

索引是特殊的数据结构，索引存储在一个易于遍历读取的数据集合中，索引是对数据库表中一列或多列的值进行排序的一种结构

db.collection.createIndex() 方法

##### 21.MongoDB中的分片是如何实现的？

1. 什么是MongoDB的混合集群，有什么优势

三高（高可用（主从），高扩展（分片），高性能（读写分离））