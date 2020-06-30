## 课堂主题

Mysql元数据锁、行锁、MySQL事务介绍、InnoDB架构

## 课堂目标

理解MySQL元数据锁的意义和使用场景理解MySQL行锁的意义和使用场景

掌握MySQL记录锁和间隙锁的使用区别掌握死锁的原理和死锁场景

理解事务的概念和四大特征（ACID）

掌握InnoDB的架构和组件作用

理解预写机制、双写机制、RedoLog的作用和日志落盘

# 一、元数据锁

## 元数据锁介绍

MDL (metaDataLock) 元数据：表结构

在 MySQL 5.5 版本中引入了 MDL**，**当对一个表做增删改查操作的时候，加 MDL 读锁**；**当要对表做结构变更操作的时候，加 MDL 写锁。

## 元数据锁演示

session1（Navicat）、session2（mysql）

1、session1: begin;--开启事务

select * from mylock;--加MDL读锁

2、session2: alter table mylock add f int; -- 修改阻塞

3、session1：commit; --提交事务 或者 rollback 释放读锁

4、session2：Query OK, 0 rows affected (38.67 sec) --修改完成Records: 0 Duplicates: 0 Warnings: 0

# 二、行级锁

## 行级锁介绍

InnoDB存储引擎实现

InnoDB的行级锁，按照锁定范围来说，分为三种：

记录锁（Record Locks）:锁定索引中一条记录。 主键指定 where id=3

间隙锁（Gap Locks）: 锁定记录前、记录中、记录后的行 RR隔离级 （可重复读）-- MySQL默认隔离级Next-Key 锁: 记录锁 + 间隙锁

## 行级锁分类

按照功能来说，分为两种：

共享读锁（S）：允许一个事务去读一行，阻止其他事务获得相同数据集的排他锁。

排他写锁（X）：允许获得排他写锁的事务更新数据，阻止其他事务取得相同数据集的共享读锁（不是 读）和排他写锁。

1、自动加 DML

对于UPDATE、DELETE和INSERT语句，InnoDB会自动给涉及数据集加排他锁（X)；

2、手动加

InnoDB也实现了表级锁，也就是意向锁，意向锁是mysql内部使用的，不需要用户干预。

意向共享锁（IS）：事务打算给数据行加行共享锁，事务在给一个数据行加共享锁前必须先取得该 表的IS锁。

意向排他锁（IX）：事务打算给数据行加行排他锁，事务在给一个数据行加排他锁前必须先取得该 表的IX锁。

意向锁的主要作用是为了【全表更新数据】时的性能提升。否则在全表更新数据时，需要先检索该 表是否某些记录上面有行锁。

**共享锁**

**（\****S***\*）**

**排他锁**

**（\****X***\*）**

**意向共享锁**

**（\****IS***\*）**

**意向排他锁**

**（\****IX***\*）**

共享锁（S）

兼容

冲突

兼容

冲突

排他锁（X）

冲突

冲突

冲突

冲突

意向共享锁

（IS）

兼容

冲突

兼容

兼容

意向排他锁

（IX）

冲突

冲突

兼容

兼容

## 两阶段锁（2PL）

锁操作分为两个阶段：加锁阶段与解锁阶段， 加锁阶段与解锁阶段不相交。

加锁阶段：只加锁，不放锁。解锁阶段：只放锁，不加锁。

## 行锁演示

**InnoDB\****行锁**是通过给索引上的**索引项加锁来实现的**，因此InnoDB这种行锁实现特点意味着：只有通过 索引条件检索的数据，InnoDB才使用行级锁，否则，InnoDB将使用表锁！

where 索引 行锁 否则 表锁

### 行读锁

session1（Navicat）、session2（mysql）

Innodb_row_lock_current_waits： 当 前 正 在 等 待 锁 定 的 数 量 ； Innodb_row_lock_time： 从 系 统 启 动 到 现 在 锁 定 总 时 间 长 度 ； Innodb_row_lock_time_avg： 每 次 等 待 所 花 平 均 时 间 ； Innodb_row_lock_time_max：从系统启动到现在等待最常的一次所花的时间； Innodb_row_lock_waits：系统启动后到现在总共等待的次数；

查看行锁状态 show STATUS like 'innodb_row_lock%';

1、session1: begin;--开启事务未提交

select * from mylock where ID=1 lock in share mode; --手动加id=1的行读

锁,使用索引

2、session2：update mylock set name='y' where id=2; -- 未锁定该行可以修改

3、session2：update mylock set name='y' where id=1; -- 锁定该行修改阻塞

ERROR 1205 (HY000): Lock wait timeout exceeded; try restarting transaction

-- 锁定超时

4、session1: commit; --提交事务 或者 rollback 释放读锁

5、session2：update mylock set name='y' where id=1; --修改成功Query OK, 1 row affected (0.00 sec)

Rows matched: 1 Changed: 1 Warnings: 0

注：使用索引加行锁 ，未锁定的行可以访问

### 行读锁升级为表锁

session1（Navicat）、session2（mysql）

### 行写锁

session1（Navicat）、session2（mysql）

1、session1: begin;--开启事务未提交

--手动加id=1的行写锁,

select * from mylock where id=1 for update;

2、session2：select * from mylock where id=2 ; -- 可以访问

3、session2: select * from mylock where id=1 ; -- 可以读 不加锁

4、session2: select * from mylock where id=1 lock in share mode ; -- 加读锁被阻塞

5、session1：commit; -- 提交事务 或者 rollback 释放写锁

5、session2：执行成功主键索引产生记录锁

### 间隙锁

#### 间隙锁防止两种情况

1、防止插入间隙内的数据

2、防止已有数据更新为间隙内的数据

#### 间隙的范围

update news set number=3 where number=4; number : 2 3 4

id:1 2 3 4 5

间隙情况：

id、number均在间隙内id、number均在间隙外

id在间隙内、number在间隙外

id在间隙外，number在间隙内id、number为边缘数据

create table news (id int, number int,primary key (id)); mysql> insert into news values(1,2); ...... --加非唯一索引 mysql> alter table news add index idx_num(number); " src="data:image/png;base64,R0lGODlh4AKbAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AEAC3AWAAhwAAAA0LFA0LGxQLDRQTGxsTDRsLDRQZGxsZGxQLGxQLFBsZFA0TFBQTFA0ZGxsLGxsLFA4LDRAdDQ0LGA4dDQ0ZJw0ZIQ0TIQ0LOw0LJg0LLRsTIQ0dKw0LIQ0LSQ0KUA0KYw0IYw0IfBsEqhA9GBAtDRsnLRQhLRsnMxQhJxQhIRsnJxshJw49OxA9KxFZKxFLIQ5LRBFmMxFmOxBmMxFmRBBmRCETDScZDSETGycZFCETFCEZGyEKDTULDScIDSILDS0GLTULOyEZITULJjUKUCIGdSEIYzMAfDMDYy0AfDUEhycAkycAqi0AqjMAqiEDqjMnGycnGychFC0nGychGyEhGy0hFCEtLSctIS0tISczMzMzMycnLTMtITMzLSEtMzMzJzMnISEnISEhJy0tLScnJy0zMyczJyctLS0zJyczLS0hITMtLS0zLTMnJyEtJycnMy0tM0cKDVcIDUcIUFcAdVcAY0cDY0cDdVcAh0cDh1cEh2cGJncEO2cEO3cDUHcEUHcAY2cAdWcAY3cAdWcDdXcAh2cAhwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEIBBAAy42BCCtEQQhAQJYBACwcFHhBC0SGDCtexMixo8ePIEOKHEmypMmTKFOqXMmypcuXMGMK2MKlpk0uCwWa6GLTywABUgQWvHHiJpcvBwOAMeqFQIGYUKNKnUq1qtWrWLNCHQqgAg6hJr42DHphCkEsOYIOPBHGwMADG7XKnUu3rt27ePOSVIoTRc0vOhAIZGugKBcxYxIEreBFwYKZNqlArHAzil+bYvVq3sy5s+fPVynjRHihipUAVgCk2JFYrQUeBQzbvILRK+jbuHPr3t2ZL5evlHMyUGAlBRkdYxS0HqiisNHMSq94rUB7YAWkvLNr3869O0bIXK6c/7D8N6mVBl1xBFju4EHQFDeAprdO2/aJ6mO969/Pv39eFGWYkdNAqDX0UACPBTUTbbLVJJZCAtkGwHj+VWjhhRi2dMEZOEwXHoFWCICGWwLJN+FXMy3E2GQqdihWgRNil+GMNNboH2U+zRfjQQKkoQaJAPhV3Qk6gNHWQBohhIKMNjbp5JNQRinllFRWaeWVWGap5ZZcdunll2CGKeaYZJZp5plopqnmmmy26eabcMYp55x01mnnnXjmqeeefPbp55+ABirooIQWauihiCaq6KKMNuroo5BGKumklFZq6aWYZqrpppx26umnoG4HHn4YYmDHHAhhoMchh/QBWlFiLf/43RoTceRBE0F0JgCtL92aq1ayYrVrrS7BWuIWpJZIK2WkMqsVCsnKZSqqJk37kbUYLeFqb2BEy9GwK3nARA/A8qqVuOR2B65L0Ym0LoUIwcvQCWxsEUVRReK3omwP1tSGG7VCO5i3DGWwB6uH0AEABncIsaofAhnMKsQAGIGwq0sgfIggPgAg8SEUZ6sxxxVfvLAddSACMkLaIvRxyAjtOsQZHxK0FE5clfjQhJh5JO+GDiJJ8xvmMgSCE0888YNA4h6BdK4jJJ00EkCCJDPNtIF7wY/02mvs1aPZXNNCIj5A04AIHZ300kwz4fQTUEv9BNXfrkHG2A2hYfZoQP//tla99/otW9Z6n91uhDkypDWHYuPcrXWJzztg35kBTXTAyQpM0Rn3hmG2eFds7dZQIlY9E3BMas4zwQItAQgQqerB8Qd/tO6qwQoPFALJ2Nru8R65Y9Q7Qrv7oOrrHwxCLQAtD9Qy7t9t4VMFRwqsFA4o4LCh9lTkXTVH1zMUfkNboE4sQxpAwfatSQwgAt3omjTT9G1pzbXnyN5Hvk8XABwkbdebSVsu0L2OpI9tAGCf++A3ro+kCAD9i88WBlhAm2WGLZ/Tn3XqN0EDEBCCPzrL+ZR1gxUJxHpg0F4Icya+FLYwVuXrCpNMCLmLbOheVyid/gQGoSCFrStHWtf//+ZVM4aoKngCYVjHBqIqjbmqiRvrWO+gyKptCe9UsWMVx6xlMCs2b2GrMpnieCU68NQEdBUwg3Sq4xe0zep846OeW4SIkQO2jVwgUIJb4lcS+xnAj/fRYdZ4FR0zhqd0IbHjHQGQxz020CPgKuSIMDK+CeVwRPoDT/0mqaT6uHEgu+rCgAxJGx5+koQvjFAQiyY60oQwXpcsDG22BgGDxOtDJhSi6iIUNCPqAYkLu8MSk/jL2CnsA4SQIhaJCUyOTLGYyDQeFqHnO2Y6kIw/Ol1CorCCB2SBBcQqCsHoaEFVzrFodVTfIhuZwEeSxI+AjGUmCZlCbYKSkx9RZDvxqP/HfV4zKfXEJ4FcODBBarOMAgVhLUd4rDIciXyZoYgaFtoRcsZxlbVqJZJeuRZ5srEMn4RQBHc0ENUV5ZTOe12qhMkQ18EuiVgs3smWxzyVXmuZMwVA8Y6o00Is74s1fWndDtJKFESuR1pQABzM8L0eUpKgAxXL9khqQHX6k518TIjfhgrCP9JKgLIUJERleBCjbgSRINFn/LDqTl7C0HxoRUglA4nJHPJKjnEtKUghuayHmpUhAEJpOeVK0KmeYIaR60ribkhXWVLEfxCkGRceahg2FE1zGvzIx1oVzGFG7GBVLNkhArEHkmUsir8TI0dOSzKLjba0QrgDH1iFRKD/btaKoMTm6G4WnnadIEd9eyhH5CUUqFYWneiz6lr7CYCozQ1IlIlozHTrwzBsgGsezVsceumbQyaUI2ptIDubOzUSRVdZcghaXot7wewaRgxGesB3R1pRXhmru9Whb0eIO1ieccGymAMsfm7okV3+E1KVvIsJGHqhBaNyLgZWSYRTaRKnRsiNmVUSFxJ74Ej5RbpysfCMnEpOq/iFwyg5cVwKvNWQBEerbtRoqGZM4xrb+MY4zrGOd8zjHvv4x0AOspCHTOQiG/nISE6ykpfM5CY7+clQjrKUp0zlKlv5yljOspa3zOUue/nLYA6zmMdM5jKDalQnURVu7aJmGkFx/80sMZZKnGVmjEwYL8NbSZ63A9SVHE4gMZBBBEASgxrUYAYSWItgidy1wOWrhvziJRf+FWBLhuQDKksYE1Gmsm25VtOpXRnDHLay02rRswjBNG1hmjLOmhq1IUBYM5U1syL6JgosdMgAIo0RI9jUCK76NG6/aK1ohppinw6evFpQAxfQYNAemQAFBNKCF0B7iJtbNI8xmL/QrdAgedXmdSq9upBQE4yv2ylLWXY74KlqdrXLqbmBR8x08w6nmFZYCHz6LekNQI4nBGAKs7c9Aq7XiNPEg2dlWs2cGvt57l53VBnCgWePpAUwSMhiOWfkxu7Qkyf8IcB1mSxxapbeMP9F1cNXRkwnKhHhNPUINYudTHnrVKXnni5RQ0jK+6RxjT5c9BIgFgKKQZFkDae58cIY2ozBjJwVv/ZHqi11GSPZ47OcqC3XgssckZwhogHxQM6t9IFY7InFZKLEUy6SmWPR2L0LAc5RPsadzzGG1uGmN8HJdYIhkwh5QBVPjZ30tyeTp73mbEOQG/WQxIAEGLH6kbF+wr2CfSEjPSy5Tzrv4JVddxRzqdpRvWeOuF3lNe9dvnsac1Rq9K8lSkNSl1o1EbPMEEaPKdKZZ8VpvbtjosdI0dlLcYtbp8UAeDxHaEjgIlMegpANLomOW+kMn9zzhveBqlEb6la9vKWnvj6q2wnP2o7FetVc1Sh+fcs/yQpX+KAWLWmRDtSMCaIINb/t9ne/bEP7vwRuNRAdYAP+Z20lNWAc52R39kZHlmAd4WAgIWLWh2QqNhIlNmQf5hG2B3Y/JFHfU2cgGIIiOIIkWIImeIIomIIquIIs2IIu+IIwGIMyOIM0WIM2eIM4mIM6uIM82IM++INAmCHNl22kkYBEKDRoM4RByChK2IRGGFlJ+IRKuISJEhAAOw==" v:shapes="_x0000_s1030">

#### 非唯一索引等值

**结论：只要\****number***\*（\****where***\*后面的）在间隙里（\****2 3 4***\*），不包含最后一个数（\****5***\*）则不管\****id***\*是多少都会阻塞。**

**主键索引范围**

1 and id <6; session 2: start transaction ; insert into news value(2,3);#（均在间隙内，阻塞） insert into news value(7,8);#（均在间隙外，成功） insert into news value(2,8);#（id在间隙内，number在间隙外，阻塞） insert into news value(4,8);#（id在间隙内，number在间隙外，阻塞） insert into news value(7,3);#（id在间隙外，number在间隙内，成功） --id无边缘数据，因为主键不能重复 " src="data:image/png;base64,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" v:shapes="_x0000_s1157">

**结论：只要\****id***\*（在\****where***\*后面的）在间隙里\****(2 4 5)***\*，则不管\****number***\*是多少都会阻塞。\****非唯一索引无穷大**

session1（Navicat）、session2（mysql）

update news set number=3 where number=13 ;

session 2:

start transaction ;

insert into news value(11,5);#(执行成功) insert into news value(12,11);#(执行成功) insert into news value(14,11);#( 阻 塞 ) insert into news value(15,12);#(阻塞)

检索条件number=13,向左取得最靠近的值11作为左区间，向右由于没有记录因此取得无穷大作为右区间，因 此，session 1的间隙锁的范围（11，无穷大）

结论：id和number同时满足

注：非主键索引产生间隙锁，主键范围产生间隙锁

### 死锁

两个 session 互相等等待对方的资源释放之后，才能释放自己的资源,造成了死锁

session1（Navicat）、session2（mysql）

1、session1: begin;--开启事务未提交

--手动加行写锁 id=1 ，使用索引

update mylock set name='m' where id=1; 2、session2：begin;--开启事务未提交

--手动加行写锁 id=2 ，使用索引

update mylock set name='m' where id=2;

3、session1: update mylock set name='nn' where id=2; -- 加写锁被阻塞

4、session2：update mylock set name='nn' where id=1; -- 加写锁会死锁，不允许操作

ERROR 1213 (40001): Deadlock found when trying to get lock; try restarting transaction

# 四、事务

## 事务介绍

在MySQL中的事务是由**存储引擎**实现的，而且支持事务的存储引擎不多，我们主要讲解**InnoDB**存储引 擎中的事务。

事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。事务用来管理DDL、DML、DCL 操作，比如 insert,update,delete 语句，默认是自动提交的。

## 事务四大特性(ACID)

Atomicity（原子性）：构成事务的的所有操作必须是一个逻辑单元，要么全部执行，要么全部不 执行。

Consistency（一致性）：数据库在事务执行前后状态都必须是稳定的或者是一致的。

#### Isolation（隔离性）：事务之间不会相互影响。由锁机制和MVCC机制来实现的

**MVCC(\****多版本并发控制***\*)\****：优化读写性能（读不加锁、读写不冲突）**

Durability（持久性）：事务执行成功后必须全部写入磁盘。

## 事务开启

BEGIN 或 START TRANSACTION`；显式地开启一个事务；

COMMIT 也可以使用COMMIT WORK ，不过二者是等价的。COMMIT会提交事务，并使已对数据库进行的所有修改称为永久性的；

ROLLBACK 有可以使用 ROLLBACK WORK`，不过二者是等价的。回滚会结束用户的事务，并撤销正在进行的所有未提交的修改；

## InnoDB架构图

上图详细显示了InnoDB存储引擎的体系架构，从图中可见，InnoDB存储引擎由**内存池，后台线程和磁 盘文件**三大部分组成。接下来我们就来简单了解一下内存相关的概念和原理。

**InnoDB\****内存结构***\*Buﬀer Pool** **缓 冲 池** 处理数据

数据页和索引页

Page是Innodb存储的最基本结构，也是Innodb磁盘管理的最小单位

做增删改时 缓存里的数据页和磁盘里的数据页不一致，该数据页为脏页插入缓冲(Insert Buﬀer)

复杂 ： 主键排序 索引 树状 插入算法 。。。

自适应哈希索引(Adaptive Hash Index) hash结构 k-v

InnoDB会根据访问的频率和模式，为热点页建立哈希索引，来提高查询效率 锁信息(lock info)

行锁、 表锁 。。。

数据字典信息(Data Dictionary)

元数据信息 包括表结构、数据库名或表名、字段的数据类型、视图、索引、表字段信息、存储过程、触发器等内容

#### Redo log Buﬀer重做日志缓冲

重做日志： Redo Log 如果要存储数据则先存储数据的日志 ， 一旦内存崩了 则可以从日志找

重做日志保证了数据的可靠性，InnoDB采用了**Write Ahead Log\****（预写日志）**策略，即当事务提交

时，先写重做日志，然后再择时将脏页写入磁盘。如果发生宕机导致数据丢失，就通过重做日志进行数 据恢复。

insert into xxxx

commit; Redo Log File 写成功 则Commit；

Redo Log ： ib_logﬁle0 ib_logﬁle1 默认为8MB。 可通过配置参数innodb_log_buffer_size 控制

**Force Log at Commit**机制实现事务的持久性，即当事务提交时，必须先将该事务的所有日志写入到重做日志文件进行持久化，然后事务的提交操作完成才算完成。为了确保每次日志都写入到重做日志文 件，在每次将重做日志缓冲写入重做日志后，必须调用一次**fsync**操作（操作系统），将缓冲文件从文 件系统缓存中真正写入磁盘。

innodb_ﬂush_log_at_trx_commit

#### 重做日志的落盘机制

该参数默认值为1

可以通过innodb_flush_log_at_trx_commit 来控制重做日志刷新到磁盘的策略。该参数默认值为1，表示事务提交必须进行一次fsync操作，还可以设置为0和2。0表示事务提交时不进行写入重做日志 操作，该操作只在主线程中完成，2表示提交时写入重做日志，但是只写入文件系统缓存，不进行fsync 操作。由此可见，设置为0时，性能最高，但是丧失了事务的一致性。

#### Double Write双写

Double Write带给InnoDB存储引擎的是数据页的可靠性

如上图所示，**Double Write\****由两部分组成，一部分是内存中的***\*double write buﬀer\****，大小为***\*2MB\****，** **另一部分是物理磁盘上共享表空间连续的\****128***\*个页，大小也为\****2MB***\*。**在对缓冲池的脏页进行刷新时，并 不直接写磁盘，而是通过memcpy函数将脏页先复制到内存中的double write buﬀer区域，之后通过double write buﬀer再分两次，每次1MB顺序地写入共享表空间的物理磁盘上，然后马上调用fsync函数，同步磁盘，避免操作系统缓冲写带来的问题。在完成double write页的写入后，再讲double wirite buﬀer中的页写入各个表空间文件中。如果操作系统在将页写入磁盘的过程中发生了崩溃，在恢复过程 中，InnoDB存储引擎可以从共享表空间中的double write中找到该页的一个副本，将其复制到表空间文件中，再应用重做日志。

#### CheckPoint： 检查点

检查点，表示脏页写入到磁盘的时机，所以检查点也就意味着脏数据的写入。

1、checkpoint的目的

1、缩短数据库的恢复时间

2、buﬀer pool空间不够用时，将脏页刷新到磁盘3、redolog不可用时，刷新脏页

2、检查点分类

1、sharp checkpoint：

完全检查点 数据库正常关闭时，会触发把所有的脏页都写入到磁盘上

2、fuzzy checkpoint：

正常使用时 模糊检查点，部分页写入磁盘。

master thread checkpoint、ﬂush_lru_list checkpoint、async/sync ﬂush checkpoint、dirty page too much checkpoint。

master thread checkpoint ： 以每秒或每十秒的速度从缓冲池的脏页列表中刷新一定比例的页回磁盘，这个过程是异步的，

ﬂush_lru_list checkpoint ： 读取lru (Least Recently Used) list，找到脏页，写入磁盘。 最近最少使用

async/sync ﬂush checkpoint ： redo log ﬁle快满了，会批量的触发数据页回写，这个事件触发的时候又分为异步和同步，不可被覆盖的redolog占log ﬁle的比值：75%--->异步、90%--->同步。

dirty page too much checkpoint ： 默认是脏页占比75%的时候，就会触发刷盘，将脏页写入磁盘

**InnoDB\****磁盘文件**

#### 系统表空间和用户表空间

innodb_data_file_path 的格式如下：

用户可以通过多个文件组成一个表空间，同时制定文件的属性：

这里将/db/ibdata1和/dr2/db/ibdata2两个文件组成系统表空间。如果这两个文件位于不同的磁盘上系统表空间（共享表空间）

1、数据字典(data dictionary)：记录数据库相关信息

2、doublewrite write buﬀer：解决部分写失败（页断裂）

3、insert buﬀer：内存insert buﬀer数据，周期写入共享表空间，防止意外宕机4、回滚段(rollback segments)

5、undo空间：undo页

用户表空间（独立表空间）

1、每个表的数据和索引都会存在自已的表空间中

2、每个表的结构

3、undo空间：undo页 （需要设置）

4、doublewrite write buﬀer

#### 重做日志文件和归档文件

ib_logﬁle0 和 ib_logﬁle1

在日志组中每个重做日志文件的大小一致，并以【循环写入】的方式运行。

InnoDB存储引擎先写入重做日志文件1，当文件被写满时，会切换到重做日志文件2，再当重做日志文 件2也被写满时，再切换到重做日志文件1。