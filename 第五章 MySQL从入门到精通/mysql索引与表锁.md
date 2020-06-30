## 课堂主题

Mysql组合索引、索引失效分析、表级锁介绍

**课堂目标**

掌握索引使用场景

理解组合索引的结构和掌握使用原则使用explain查看sql执行计划

掌握select_type、type、extra等参数意义理解索引失效口诀

编写使用索引的sql

掌握MySQL的锁的分类理解表级锁

**一、索引使用场景**

**哪些情况需要创建索引**

1、主键自动建立唯一索引

2、频繁作为查询条件的字段应该创建索引 where

3、多表关联查询中，关联字段应该创建索引 on 两边都要创建索引

4、查询中排序的字段，应该创建索引 B + tree 有顺序

5、覆盖索引 好处是？ 不需要回表 组合索引

6、统计或者分组字段，应该创建索引

**哪些情况不需要创建索引**

1、表记录太少 索引是要有存储的开销

2、频繁更新 索引要维护

3、查询字段使用频率不高

**为什么使用组合索引**

由多个字段组成的索引 使用顺序就是创建的顺序

在一颗索引树上由多个字段

优势： 效率高、省空间、容易形成覆盖索引使用：

遵循最左前缀原则

1、前缀索引

like 常量% 使用索引 like %常量 不使用索引

2、最左前缀

从左向右匹配直到遇到范围查询 > < between 索引失效

|

t1

|

1 |

1

NULL

|

|

idx_a_b_c_d |

NULL | YES

|

BTREE

2

|

|

b

|

|

A

|

|

|

t1

|

1

|

idx_a_b_c_d |

3

|

c

|

A

|

1 |

NULL

|

NULL | YES

|

BTREE

|

|

|

|

t1

|

1

|

idx_a_b_c_d |

4

|

d

|

A

|

1 |

NULL

|

NULL | YES

|

BTREE

|

|

|

mysql> explain select * from t1 where a=1 and b=1 and c=1 and d=1 ;

- - - - +

\+ + +

- - +

| id | select_type | table | type | possible_keys | key | key_len | ref

| rows | Extra |

- - - - +

\+ + +

- - +

| 1 | SIMPLE | t1 | ref | idx_a_b_c_d | idx_a_b_c_d | 20 | const,const,const,const | 1 | Using index |

- - - - +

\+ + +

- - +

1 row in set (0.00 sec)

mysql> explain select * from t1 where a=1 and b=1 and c>1 and d=1 ;

- - - - +

\+ + +

- - +

| id | select_type | table | type | possible_keys | key | key_len | ref

| rows | Extra |

- - - - +

\+ + +

- - +

| 1 | SIMPLE | t1 | range | idx_a_b_c_d | idx_a_b_c_d | 15 | NULL | 1 | Using index condition |

- - - - +

\+ + +

- - +

1 row in set (0.01 sec)

mysql> explain select * from t1 where a=1 and b=1 and d=1 and c>1 ;

- - - - +

\+ + +

- - +

| id | select_type | table | type | possible_keys | key | key_len | ref

| rows | Extra |

- - - - +

\+ + +

- - +

| 1 | SIMPLE | t1 | ref | idx_a_b_c_d | idx_a_b_c_d | 15 | const,const | 1 | Using where; Using index |

- - - - +

\+ + +

- - +

1 row in set (0.00 sec)

mysql> drop index idx_a_b_c_d on t1; Query OK, 0 rows affected (0.00 sec)

Records: 0 Duplicates: 0 Warnings: 0

mysql> alter table t1 add index idx_a_b_c_d(a,b,d,c); Query OK, 0 rows affected (0.01 sec)

Records: 0 Duplicates: 0 Warnings: 0

mysql> explain select * from t1 where a=1 and b=1 and d=1 and c>1 ;

- - - - +

\+ + +

- - +

| id | select_type | table | type | possible_keys | key | key_len | ref

| rows | Extra |

- - - - +

\+ + +

- - +

| 1 | SIMPLE | t1 | ref | idx_a_b_c_d | idx_a_b_c_d | 20 | const,const,const | 1 | Using where; Using index |

# 二、索引失效

## 查看执行计划

### 参数说明

explain出来的信息有10列，分别是

**案例表**

**id**

每个 SELECT语句都会自动分配的一个唯一标识符.

表示查询中操作表的顺序，有三种情况：

id相同：执行顺序由上到下

id不同：如果是子查询，id号会自增，**id\****越大，优先级越高**。id相同的不同的同时存在

id列为null的就表示这是一个结果集，不需要使用它来进行查询。

**select_type\****（重要）**

**查询类型**，主要用于区别**普通查询、联合查询\****(union***\*、\****union all)***\*、子查询等复杂查询**。**simple**

表示不需要union操作或者不包含子查询的简单select查询。有连接查询时，外层的查询为simple，且 只有一个

**primary**

一个需要union操作或者含有子查询的select，位于最外层的单位查询的select_type即为primary。且只 有一个

**subquery**

除了from字句中包含的子查询外，其他地方出现的子查询都可能是subquery

**dependent subquery**

与dependent union类似，表示这个subquery的查询要受到外部表查询的影响

**union**

union连接的两个select查询，第一个查询是PRIMARY，除了第一个表外，第二个以后的表select_type

都是union

|

1 |

PRIMARY

|

tuser |

ALL |

NULL

|

NULL |

NULL

|

NULL

|

2

| Using

where

|

|

2 |

UNION

|

tuser |

ALL |

NULL

|

NULL |

NULL

|

NULL

|

2

| Using

where

|

|

NULL

| UNION

RESULT

| <union1,2>

| ALL

| NULL

| NULL

| NULL

|

**dependent union**

与union一样，出现在union 或union all语句中，但是这个查询要受到外部查询的影响

**union result**

包含union的结果集，在union和union all语句中,因为它不需要参与查询，所以id字段为null

**derived**

from字句中出现的子查询，也叫做派生表，其他数据库中可能叫做内联视图或嵌套select

+

|

1

|

+

PRIMARY

+

| | ALL | NULL

| NULL | NULL

| NULL |

2

|

NULL

|

|

2

|

DERIVED

| tuser | ALL | NULL

| NULL | NULL

| NULL |

2

|

Using where

|

**table**

显示的查询表名，如果查询使用了别名，那么这里显示的是别名如果不涉及对数据表的操作，那么这显示为null

如果显示为尖括号括起来的就表示这个是临时表，后边的N就是执行计划中的id，表示结果来自于 这个查询产生。

如果是尖括号括起来的****，与类似，也是一个临时表，表示这个结果来自于union查询的id为M,N的结果集。

**type\****（重要）**

依次从好到差：

**除了\****all***\*之外，其他的\****type***\*都可以使用到索引，除了\****index_merge***\*之外，其他的\****type***\*只可以用到一个索** **引**

优化器会选用最优索引 一个

**注意事项：**

**system**

表中只有一行数据或者是空表。

**const\****（重要）**

使用**唯一索引或者主键**，返回记录一定是1行记录的等值where条件时，通常type是const。其他数据库 也叫做唯一索引扫描

**eq_ref\****（重要）**

关键字:连接字段**主键或者唯一性索引**。

此类型通常出现在多表的 join 查询, 表示对于前表的每一个结果, **都只能匹配到后表的一行结果**. 并且查询的比较操作通常是 '=', 查询效率较高.

select * from a,b where a.id=b.id (等值连接) select * from a where name='zs' (条件查询)

**ref\****（重要）**

**针对非唯一性索引**，使用**等值（\****=***\*）查询**非主键。或者是使用了**最左前缀规则索引的查询**。

--非唯一索引

mysql> explain select * from tuser where dep=1;

- - - - - - - - +-
  - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

- - - - - - - - +-
  - +

| 1 | SIMPLE | tuser | ref | idx_dep | idx_dep | 5 | const |

1 | NULL |

- - - - - - - - +-
  - +

--等值非主键连接

mysql> explain select a.id from tuser a left join tdep b on a.name=b.name;

\+ + + + + + +

- - - +

| id | select_type | table | type | possible_keys | key | key_len

| ref | rows | Extra |

\+ + + + + + +

- - - +

| 1 | SIMPLE | a | index | NULL | idx_name_age_sex | 312

| NULL | 2 | Using index |

| 1 | SIMPLE | b | ref | ind_name | ind_name | 72

| demo1.a.name | 1 | Using where; Using index |

\+ + + + + + +

- - - +

--最左前缀

mysql> explain select * from tuser where name = 'zhaoyun';

\+ + + + + + +

- - - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

**fulltext**

全文索引检索，要注意，全文索引的优先级很高，若全文索引和普通索引同时存在时，mysql不管代价，优先选择使用全文索引

**ref_or_null**

与ref方法类似，只是增加了null值的比较。实际用的不多。

**unique_subquery**

用于where中的in形式子查询，子查询返回不重复值唯一值

**index_subquery**

用于in形式子查询使用到了辅助索引或者in常数列表，子查询可能返回重复值，可以使用索引将子查询 去重。

**range\****（重要）**

**索引范围扫描**，常见于使用>,<,is null,between ,in ,like等运算符的查询中。

\+ + + + + + +

- - - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

\+ + + + + + +

- - - +

| 1 | SIMPLE | tuser | range | idx_name_age_sex | idx_name_age_sex | 303

| NULL | 1 | Using index condition |

\+ + + + + + +

- - - +

注： like '%a' 不使用索引

mysql> explain select * from tuser where loginname like 'a%';

- - - - - +

\+ +

- - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

- - - - - +

\+ +

- - +

| 1 | SIMPLE | tuser | range | idx_loginname | idx_loginname | 303 | NULL | 1 | Using index condition |

- - - - - +

\+ +

- - +

1 row in set (0.00 sec)

#### index_merge

表示查询使用了两个以上的索引，最后取交集或者并集，常见and ，or的条件使用了不同的索引，官方排序这个在ref_or_null之后，但是实际上由于要读取所个索引，性能可能大部分时间都不如range

**index\****（重要）**

**关键字：条件是出现在索引树中的节点的。可能没有完全匹配索引。**

**索引全表扫描**，把索引从头到尾扫一遍，常见于使用索引列就可以处理不需要读取数据文件的查询、可 以使用索引排序或者分组的查询。

**all\****（重要）**

这个就是全表扫描数据文件，然后再**在\****server***\*层进行过滤**返回符合要求的记录。

主键不要uuid

why？ 无序、辅助索引要存 太长主键： 自增

分布式主键： 雪花算法（sharding jdbc）、 redis生成

**possible_keys**

此次查询中可能选用的索引，一个或多个

**key**

查询真正使用到的索引，select_type为index_merge时，这里可能出现两个以上的索引，其他的

select_type这里只会出现一个。

**key_len**

用于处理查询的索引长度，如果是单列索引，那就整个索引长度算进去，如果是多列索引，那么查 询不一定都能使用到所有的列，具体使用到了多少个列的索引，这里就会计算进去，没有使用到的 列，这里不会计算进去。

留意下这个列的值，算一下你的多列索引总长度就知道有没有使用到所有的列了。

另外，_key_len_只计算_where_条件用到的索引长度，而排序和分组就算用到了索引，也不会计算到

_key_len_中。

看组合索引的使用情况

**ref**

如果是使用的常数等值查询，这里会显示const

如果是连接查询，被驱动表的执行计划这里会显示驱动表的关联字段

如果是条件使用了表达式或者函数，或者条件列发生了内部隐式转换，这里可能显示为func

**rows**

这里是执行计划中估算的扫描行数，不是精确值（InnoDB不是精确的值，MyISAM是精确的值，主要原因是InnoDB里面使用了MVCC并发机制）

**extra\****（重要）**

这个列包含不适合在其他列中显示单十分重要的额外的信息，这个列可以显示的信息非常多，有几十 种，常用的有

**no tables used**

不带from字句的查询或者From dual查询

**使用\****not in()***\*形式子查询或\****not exists***\*运算符的连接查询，这种叫做反连接**

即，一般连接查询是先查询内表，再查询外表，反连接就是先查询外表，再查询内表。

**using ﬁlesort\****（重要）**

排序时无法使用到索引时，就会出现这个。常见于order by和group by语句中说明MySQL会使用一个外部的索引排序，而不是按照索引顺序进行读取。MySQL中无法利用索引完成的排序操作称为“文件排序”

**using index\****（重要）**

查询时**不需要回表查询**，直接通过索引就可以获取查询的数据。

表示相应的SELECT查询中使用到了**覆盖索引（\****Covering Index***\*）**，避免访问表的数据行，效率不错！

如果同时出现Using Where ，说明索引被用来执行查找索引键值

如果没有同时出现Using Where ，表明索引用来读取数据而非执行查找动作。

**using temporary**

表示使用了临时表存储中间结果。

MySQL在对查询结果**order by\****和***\*group by**时使用临时表

临时表可以是内存临时表和磁盘临时表，执行计划中看不出来，需要查看status变量，

used_tmp_table，used_tmp_disk_table才能看出来。

**distinct**

在select部分使用了distinct关键字 （索引字段）

**using where\****（重要）**

表示存储引擎返回的记录并不是所有的都满足查询条件，需要在server层进行过滤。

查询条件中分为限制条件和检查条件，5.6之前，存储引擎只能根据限制条件扫描数据并返回，然 后server层根据检查条件进行过滤再返回真正符合查询的数据。5.6.x之后支持**ICP**特性，可以把检查条件也下推到存储引擎层，不符合检查条件和限制条件的数据，直接不读取，这样就大大减少了 存储引擎扫描的记录数量。extra列显示**using index condition**

## 索引失效分析

#### 1. 全值匹配我最爱

1. **最佳左前缀法则**

组合索引

如果索引了多个列，要遵守最佳左前缀法则。指的是查询从索引的最左前列开始 并且不跳过索引中的列。 正确的示例参考上图。

**错误的示例：**

带头索引死：

中间索引断（带头索引生效，其他索引失效）：

mysql> explain select * from tuser where name='aa' and sex='1' and age=23;

\+ + + + + + +

- - - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

\+ + + + + + +

- - - +

| 1 | SIMPLE | tuser | ref | idx_name_age_sex | idx_name_age_sex | 312

| const,const,const | 1 | Using index condition |

\+ + + + + + +

- - +

比较

mysql> explain select * from tuser where name='aa' and sex=1 and age=23;

\+ + + + + + +

- - - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

\+ + + + + + +

- - - +

| 1 | SIMPLE | tuser | ref | idx_name_age_sex | idx_name_age_sex | 308

| const,const | 1 | Using index condition |

\+ + + + + + +

- - - +

#### 3. 不要在索引上做计算

1. **范围条件右边的列失效**
2. **尽量使用覆盖索引**
3. **索引字段上不要使用不等**
4. **主键索引字段上不可以判断\****null**

主键字段上不可以使用 null

索引字段上使用 is null 判断时，可使用索引

mysql> explain select * from tuser where name is null;

\+ + + + + + +

- - - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

\+ + + + + + +

- - - +

| 1 | SIMPLE | tuser | ref | idx_name_age_sex | idx_name_age_sex | 303

| const | 1 | Using index condition |

\+ + + + + + +

- - - +

1 row in set (0.00 sec)

mysql> explain select * from tuser where loginname is null;

- - - - - +

\+ +

- - +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |

- - - - - +

\+ +

- - +

| 1 | SIMPLE | tuser | ref | idx_loginname | idx_loginname | 303 | const | 1 | Using index condition |

- - - - - +

\+ +

- - +

1 row in set (0.00 sec)

1 row in set (0.00 sec)

主键非空 不使用索引

mysql> explain select * from tuser where id is not null;

- - - - +

-+ +

\+ + + +

| id | select_type | table | type | possible_keys | key | key_len | ref | rows

| Extra |

- - - - +

-+ +

\+ + + +

| 1 | SIMPLE | tuser | ALL | PRIMARY | NULL | NULL | NULL | 2

| Using where |

- - - - +

-+ +

\+ + + +

#### 8. 索引字段使用like不以通配符开头

由结果可知，like以通配符结束相当于范围查找，索引不会失效。与范围条件（bettween、<、>、in 等）不同的是：不会导致右边的索引失效。

**问题：解决\****like ‘%***\*字符串\****%’***\*时，索引失效问题的方法？** 使用覆盖索引可以解决。

#### 9. 索引字段字符串要加单引号

1. **索引字段不要使用\****or**

**总结**

**三、\****MySQL***\*锁篇**

**MySQL\****锁介绍**

**MySQL\****表级锁**

**表级锁介绍**

**由\****MySQL SQL layer***\*层实现**

MySQL的表级锁有两种：

MySQL 实现的表级锁定的争用状态变量：

**表锁介绍**

表锁有两种表现形式：

手动增加表锁

查看表锁情况

删除表锁

**表锁演示**

**环境准备**

**读锁演示**

1、表读锁

session1（Navicat）、session2（mysql）

2、表写锁

session1（Navicat）、session2（mysql）