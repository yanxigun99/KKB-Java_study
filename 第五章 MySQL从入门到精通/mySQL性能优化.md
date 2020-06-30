# 一条复杂SQL的加锁分析

如图中的SQL，会加什么锁？假定在**Repeatable Read**隔离级别下

在详细分析这条SQL的加锁情况前，还需要有一个知识储备，那就是一个SQL中的where条件如何拆分？在这里，我直接给出分析后的结果：

**Index key\****：**pubtime > 1 and puptime < 20。此条件，用于确定SQL在idx_t1_pu索引上的查询范围。

**Index Filter\****：**userid = ‘hdc’ 。此条件，可以在idx_t1_pu索引上进行过滤，但不属于Index Key。**Table Filter***\*：**comment is not NULL。此条件，在idx_t1_pu索引上无法过滤，只能在聚簇索引上过滤。

在where条件过滤时，先过滤index key（索引列为范围查询，起始条件为index First Key，截至条件为index Last key），再过滤Index Filter（索引列），最后过滤Table Filter（非索引列）。在ICP过程中， 下推Index Filter。

结论：

在Repeatable Read隔离级别下，针对一个复杂的SQL，首先需要提取其where条件。

- Index Key确定的范围，需要加上GAP锁；
- Index Filter过滤条件，视MySQL版本是否支持ICP，若支持ICP，则不满足Index Key和Index Filter的记录，不加X锁，否则需要X锁；
- Table Filter过滤条件，无论是否满足，都需要加X锁。 server层

# 死锁原理与分析

本文前面的部分，基本上已经涵盖了MySQL/InnoDB所有的加锁规则。深入理解MySQL如何加锁，有两个比较重要的作用：

可以根据MySQL的加锁规则，写出不会发生死锁的SQL； 可以根据MySQL的加锁规则，定位出线上产生死锁的原因；

案例1（记录锁产生）

案例2（间隙锁产生）

**结论：**

**如何解决死锁呢？**

MySQL默认会主动探知死锁，并回滚某一个影响最小的事务。等另一事务执行完成之后，再重新执行该 事务。

如何避免死锁

1、注意程序的逻辑

根本的原因是程序逻辑的顺序，最常见的是交差更新

Transaction 1: 更新表A -> 更新表B Transaction 2: 更新表B -> 更新表A Transaction获得两个资源

2、保持事务的轻量

越是轻量的事务，占有越少的锁资源，这样发生死锁的几率就越小

3、提高运行的速度

避免使用子查询，尽量使用主键等等

4、尽量快提交事务，减少持有锁的时间越早提交事务，锁就越早释放

**课堂主题**

MySQL性能分析和性能优化

**课堂目标**

会使用和分析慢查询日志会使用和分析proﬁle

理解服务器层面优化思路掌握表设计层面优化

掌握SQL层面优化技术

**性能分析和性能优化篇**

# 性能分析的思路

1. 首先需要使用【慢查询日志】功能，去获取所有查询时间比较长的SQL语句
2. 其次【查看执行计划】查看有问题的SQL的执行计划 explain
3. 最后可以使用【show proﬁle[s]】 查看有问题的SQL的性能使用情况

**慢查询日志**

开启：

slow_query_log=ON long_query_time=3

slow_query_log_file=/var/lib/mysql/slow-log.log

**慢查询日志介绍开启慢查询功能**

**慢查询日志格式**

**分析慢查询日志的工具**

**使用\****mysqldumpslow***\*工具**

得到按照时间排序的前10条里面含有左连接的查询语句：

**常用参数说明：**

-s：是表示按照何种方式排序

-t：是top n的意思，即为返回前面多少条的数据

-g：后边可以写一个正则匹配模式，大小写不敏感的

**使用\****percona-toolkit***\*工具**

percona-toolkit是一组高级命令行工具的集合，可以查看当前服务的摘要信息，磁盘检测，分析慢查询 日志，查找重复索引，实现表同步等等。

下载

[https://www.percona.com/downloads/percona-toolkit/3.0.11/binary/tarball/percona-toolkit-3.0.1 1_x86_64.tar.gz](https://www.percona.com/downloads/percona-toolkit/3.0.11/binary/tarball/percona-toolkit-3.0.11_x86_64.tar.gz)

安装

调错

Can't locate ExtUtils/MakeMaker.pm in @INC 错误的解决方式: 先执行再安装

Can't locate Time/HiRes.pm in @INC

Can't locate Digest/MD5.pm in @INC

使用pt-query-digest查看慢查询日志

pt-query-digest /var/lib/mysql/localhost-slow.log

**pt-query-digest\****语法及重要选项**

**分析\****pt-query-digest***\*输出结果**

第一部分：总体统计结果 Overall：总共有多少条查询 Time range：查询执行的时间范围unique：唯一查询数量，即对查询条件进行参数化以后，总共有多少个不同的查询 total：总计min：最小 max：最大 avg：平均 95%：把所有值从小到大排列，位置位于95%的那个数，这个数一般最具有参考价值 median：中位数，把所有值从小到大排列，位置位于中间那个数

\# 该工具执行日志分析的用户时间，系统时间，物理内存占用大小，虚拟内存占用大小

\# 340ms user time, 140ms system time, 23.99M rss, 203.11M vsz # 工具执行时间

\# Current date: Fri Nov 25 02:37:18 2016 # 运行分析工具的主机名

\# Hostname: localhost.localdomain # 被分析的文件名

\# Files: slow.log

\# 语句总数量，唯一的语句数量，QPS，并发数

\# Overall: 2 total, 2 unique, 0.01 QPS, 0.01x concurrency

\# 日志记录的时间范围

\# Time range: 2016-11-22 06:06:18 to 06:11:40

\#

\#

属性

Attribute

总计

total

最小

min

最大

max

平均

avg

95% 标准 中等

95% stddev

median

\#

\#

============

语句执行时间

=======

=======

=======

=======

======= =======

=======

\#

\#

Exec time

锁占用时间

3s

640ms

2s

1s

2s 999ms

1s

\#

Lock time

1ms

0

1ms

723us

1ms 1ms

723us

\#

发送到客户端的行数

\#

\#

Rows sent

select语句扫描行数

5

1

4

2.50

4

2.12

2.50

\#

\#

Rows examine

查询的字符数

186.17k

0

186.17k

93.09k

186.17k

131.64k

93.09k

\#

Query size

455

15

440

227.50

440

300.52

227.50

第二部分：查询分组统计结果 Rank：所有语句的排名，默认按查询时间降序排列，通过--order- by指定 Query ID：语句的ID，（去掉多余空格和文本字符，计算hash值） Response：总的响应时间 time：该查询在本次分析中总的时间占比 calls：执行次数，即本次分析总共有多少条这种类型的查询语句 R/Call：平均每次执行的响应时间 V/M：响应时间Variance-to-mean的比率 Item： 查询对象

第三部分：每一种查询的详细统计结果 由下面查询的详细统计结果，最上面的表格列出了执行次数、最大、最小、平均、95%等各项目的统计。 ID：查询的ID号，和上图的Query ID对应Databases：数据库名 Users：各个用户执行的次数（占比） Query_time distribution ：查询时间分布, 长短体现区间占比，本例中1s-10s之间查询数量是10s以上的两倍。 Tables：查询中涉及到的表 Explain：SQL语句

\# Query 1: 0 QPS, 0x concurrency, ID 0xF9A57DD5A41825CA at byte 802

\# This item is included in the report because it matches --limit. # Scores: V/M = 0.00

\# Time range: all events occurred at 2016-11-22 06:11:40

\# Attribute pct total min max avg 95% stddev median # ============ === ======= ======= ======= ======= ======= ======= =======

\# Count 50 1

\# Exec time 76 2s 2s 2s 2s 2s 0 2s # Lock time 0 0 0 0 0 0 0 0

\# Rows sent 20 1 1 1 1 1 0 1

\# Rows examine 0 0 0 0 0 0 0 0

\# Query size 3 15 15 15 15 15 0 15

\# String:

\# Databases test

\# Hosts 192.168.8.1

\# Users mysql

\# Query_time distribution # 1us

\# 10us # 100us # 1ms

\# 10ms # 100ms

\# 1s ################################################################ # 10s+

\# EXPLAIN /*!50100 PARTITIONS*/

select sleep(2)\G

## 用法示例

1. 直接分析慢查询文件:
2. 分析最近12小时内的查询：

pt-query-digest --since=12h slow.log > slow_report2.log

1. 分析指定时间范围内的查询：

\> slow_report3.log " src="data:image/png;base64,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" v:shapes="_x0000_s1230">

1. 分析指含有select语句的慢查询

{fingerprint} =~ m/^select/i' slow.log> slow_report4.log" src="data:image/png;base64,R0lGODlh4AJBAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQABAgoAhQAAAA0LGw0LFBQLDRsLDRsLGxsLFBQTDRQLFA0ZJw0TIRsZJxsTIRsnMxQhJxQhLRshIRsnLRsnJxshJyETDScZDSEZIS0hFDMnGy0nGyczLSczMzMzMyEtMzMtISczJy0zLS0tISEtLSctIS0zMyEhJzMzJzMtLS0zJzMnJzMzLS0hISEtJycnJy0tLSctLS0tMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj8ikcikMaCjNzYVJrVIbnGxlGJBGs1OrNcHZEh1QsXotFHQ4noEQq+V62fhjN1ztwuV5aw98EIB9G2CBR4NRfABkZlR+HI54k5WKXE+ZnEhOaZ1Nm4EBHwRVDXyfnAogoJNxoZqgYgohhqWnsrtLq0QCIrS8RQEjgAokkWKpa77ExkmtwleYecykGiVZGAB0WSa6RmQcJ66rCiinbtsApQWIGG58CbFHyBwpT/dlQ/flUOs4cAPwIAu/bgY5gFsTMIsKKPuUdQvjTaG6NwLbfXiX0ZOpIanQDAnI7UE9RiRFWSBBCaHBhUYYGckFoCGHhwAiqmQZxp+r/5oYubmD1y4bO40cueksurJlRZg0Oz1YsQFDQYkNIkWwcEtIwTpAj87hk7INxjJPw3XbsrTJRyRfD44dMq4MsFfQklx7ZNCMTwoy7Sj96UzPhi0JHp5LNxFohS7gFGTIyfiup8OPcDqOghgnMzeP3xIpzCbqrw5YVY2aCxoyAcljTJQA9Rn1KgEsTtXe0iVOgoWkY7YcLdr0SNRRfIPr0hnKbtewuwyGAn2yWTO9B/w+Rdo4kbiJljww8e5C4JzW0a/VqLYxaJB8ds+pJ2oa7OMSlXifmzNZZgpZIVPBfXr15F9iFDD3H0HDFQVQB1M405By3D2xGAGTgOEdMwkMNP+hdsCN8t4jISaIiEFTYDHQLEh8+CF7RowIH4tfoLgfFcjEkqFT3HTYzomJXOjgcQbRVxBFMLnV3mZDokPAdg7ueIFxt0EIYxEjChmcdFV8xQ1uag2Sy3kRpGGZLXKoaEdC8YUh5Y8SBVfmafkpoWYR19AjB2iDJNDCBQlUQ0SesYAGpTNHDvlecEToGeUmTirIhWhCoGNAMEkcKiJyJFZoYp2J1qfIfjLyNyRnxC1ZhQQLxCFpPyhcSh1mo0HKGKPigOXkpEtmaeuTJb56paiLUnqdqLt2Z+wQ4HGwAjR0VCKmKYER6OMckSQq7IxwEkMrjf2kd6waofLnqKEYSFD/wAgTTINnGOeipiko4zyGGYKnIiENQYpZuMFCDdC3XwMurGiPfw9oNqKA/DpHX6Mr4moFqZxym283Ai+7hAAHAKDuRwEbMkfBID2s5WpwdcTrylhyeiHDCTuMC6UK4ksxdr9ePGwm0xIQ2JxUXBuyEXsNPajJKAMANH5sXMsfwwgG8EIICLDQwinIGMwt1A/BjFNBEWsAA1guJVnEVytsgoUJDFR21pTL7qsE2ptGMk7aD6K4j9l0mC3Gze8O+s1FNmq8hDdmNASvXIo3yVjZqp7XT1dKxuhyzndvojiVYoMFuOO69K2LZVLB7TO8Whux9+hvW9x4WOGJ/kjqpS6xZfqg8/QF1BQmyZH1EXvVZUbmgFUisSRJr7FXJ7sO4/zzQ0igKvQ4Us7840wcz4n2mYBJPRvefy8+Ec0Tk/zf56PyBycBaTb++/C/j8XDDGHkfvbpb08U9ZLHn0T//gtF+9wFgCAAADs=" v:shapes="_x0000_s1231">

1. 针对某个用户的慢查询

{user} || "") =~ m/^root/i' slow.log> slow_report5.log" src="data:image/png;base64,R0lGODlh4AJBAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQD6AQoAhQAAAA0LGw0LFBQLDRsLDRQLFBsLGxsLFBQTDQ0ZJw0TIRsZJxsTIRsnMxQhLRQhJxshIRsnLRsnJxshJyETDScZDSEZIS0hFDMnGy0nGyczLSczMzMzMyEtMzMtISczJy0zLS0tISEtLSctIS0zMyEhJzMzJzMtLS0zJzMnJzMzLS0hISEtJycnJy0tLSctLS0tMwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj8ikcikMaCjNzYVJrVYbnGwlOgUENtmu1ZrgbIsO8/ABHbvfQkGH4xnAlV+6PRn4EIh9f0gOHgUdYoSGU2Vid3AOYhB7jlSQXEVlZ1R5HI1wnJ6URU5toqZIpKejT6aBVA1iqacKIKUAcrFgeqqArG8KIZO8QwIitkauQ8lHDhgAsEPNz4jOw0sBI3sKJJqUsqPZSrTHr6F30KJOJVnOWFkcJoJGZRwntakKKH9y7F4fBmAw4BqSoA6SbRxSPEGoRghCe1D4caiWRosQd1niwZGYRQUFht2mXXwHT5BEZ30ATuTjh4i0IbDYEJuzktAeS7dootRggUSn/2ckNRrBOaplE1b5/lRseNKfymr+5B1ZFjVJgikNNF191g2dKgcrNmBI01VTBAvBmvwLKIReQ7dbMJYkkhUAyKJS0bzrKgZusVLYhBXxCgCuQ58QiUZxNs6LL1QbtiTwiE+fSDkVvsRTkMGu5b+QJXuM0+HMF9FQoGGmepTcG9aYB8d6fHHK6g2bO1eJTdBEiVKqS6cSwEJQ8Mwb6iTQ+G3QT0BGHUNJyvq4ZgKclUWfup21o4KChSwNU8kEwAtEszvsXNcLbuydt6GWX3h08/VEeFfxLtIut/oUZCWfekh4Rd9kFJwGIABpzBbRIdLN9I5yzD1RGQGchFHdItVwRP/hH7LwthyIFupCHhZQtZaEhwOwWFV+pcmmDG0ZdsLfNbT5Z1CNWDmTQDU8XhghaRNO0uBFQqmYFFBQBbmdMmKxJNWNv1hWST/E5QVJIERF0AZowNhBFXg5VUBmbPcB4CWMITGBohHonFkaJAm0cMFWSXglp5kVlnLkcDGmSQSZpFyooHZ55XOAMVb1SdoZI0p3qF4OtvKkfrXNaMukL46RpgQL1MGpZ4u2wamQgs5jkWfyCCneT6c+2Sl3U8p6x5JDkcTBCuG4E8qWfuBE4I8wbTGmQZjtOeQQBD76yHOZFoZsaT9KYMAIE7gGU1/T8kliKfQgh9qyRzTmAGUlatT/gEFq5fWMCykaQd+5X8bo3xb0PsMuJk3m+Mml9m6r6WD7UrliwAIgAIC1La0rWAPw0lUwUpalGs1K0LWarlTE6juJdwZTZfAYZH51AZddrInHdgd61DIU96k8U5tVdBztywG8EEIBLLTwxzbx9ncvgPOOlka/MKwK1FzMZLECK1iYwMBnNNkoa2NKVPR0vVo5zQpHFzCU5NJjuwFbwNGOxDTYIw+SIkZngN1WQ0RqSLFxGbnLoDlh0gXP1Nj5tHbVJ8sKmpQZq0KYKcASYInNiA+619xKu6MR5M5aIbZUek4+kE3+BU2YYYV5TYFi5L7mrxuLm4KrNbDHjgSmC+stQ7vmaclu8axtU4F57FnefkTwwhefxOu9aLvf6lfsYopEoxkvvTVY0Dy9FVjsq0oeQeOVOCVldA876saTfz3wNEVvRBAAOw==" v:shapes="_x0000_s1232">

1. 查询所有所有的全表扫描或full join的慢查询

{Full_scan} || "") eq "yes") ||(($event-> {Full_join} || "") eq "yes")' slow.log> slow_report6.log " src="data:image/png;base64,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" v:shapes="_x0000_s1233">

1. 把查询保存到query_review表
2. 把查询保存到query_history表
3. 通过tcpdump抓取mysql的tcp协议数据，然后再分析

mysql.tcp.txt pt-query-digest --type tcpdump mysql.tcp.txt> slow_report9.log" src="data:image/png;base64,R0lGODlh4AJCAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALA0AGQABAgoAhQAAAA0LGxsLDRQLDQ0LFBsLGxsLFBQLGw0TIQ0ZJxsTIRsnMxQhLSETDScZDScZFCETFCEZIS0hFDMnGy0nGyczLSczMzMzMzMzJzMtITMzLS0zMyEtMyEhJzMnJy0zJy0hISctIS0zLTMtLSchISczJyEtLS0tLS0tMzMnIQECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaiYFK48hsOp/QqHRKrVqv2KwwYJFov+CweEwuby2Xi9dcTi6xbjZ2kcYIuOnMAIC/6IUMaRpLfX9yh3BKSIpDcY2MiJFPC3ZDCBsXg0KXmW+cmm2QTIGdgIKemKAAdBeVklQLa0yOYLSzkKx1AgAEHGkXDkkdaRObmB5KcQgfAgwgFhOBwRXDF8VTpMBNDK58IXuN36N6AeJVCdoIIm9QSRGYXu7wVOgXI+tSua0C+nb9/L/2SeGkbduveBXeqeklK4EhKL1+OTgSiASzLYqW3cnITNlFI8KICamnLUCJAmiK/XvC4CGAlgM87uK2a5usbc+iXXjAoaGe/2wTAdS7x27VzSIrI6aZVu3akX/oJqprsNLWgodcik3lMjHBIJncUEpgICErgKlSTNZswhWJuS1vkcaLO8UrBBNFn3DRk8DO3gF91+q10FVVO1G2Eoua0ivok8B/A2vkhXfKVXBOJgut1DgwLw4OPDc+YnbqpcINuNhBQAFj3iax5G7e1bgtSzVOwnYhO5kAXrVICAs1HJutqMuNoJ3Fd6uo3cp8EEOy7fqzBM9uwJYt0aysIoZHlPrZQ8cpaWoi8YBSzy7iGvZNxBsKdJSI/JgcNy7RXOQ+9sVC3BddUYoRCKB9vuQBzmhsobGPTEYJZR6Cv5AjHBSaObQHQxpa1/8heCB919OHoAG3yGv9+SKLeyP9wVBI1txWHyDbdRdhAteUN8R/RRXXXHKODWidcXnR94iB7FAnZGeVZJcfWWpBKWKQURhJmnIMzkaEZ0TAtGM3UgQyoV75QRhFh7aQieSJbFqRJUjCaQThMgZAF4WSTWTIGWii8bknldV15mKJ3BWR5jYuwdRnMFiC5kQ9gL5UI1lnfVBnUUaiedyMRwLJ5puGLiZmp6QKgdwZqJ320lfJWGAHlNxBKZxXKD425hCxZUnrloa91M2udWVwwIV3lpnfQPgw0KsTBbZZnZuO/gjZsauccKsTp2b2kUzcUmvorIOoSquJpbIEJk0Q5hrmrRGj2hTlGgtYawSOyy2hbI+yXGKeVViBu98GE9ri0LBBpcnKngclgcJSplwAgiJ0YKAAM7B2pzDDA2Ei0CwOekFQUB/jespZGkfahGdRpWXsfh9VmcbDtTJxcE0zm6qLzRtLAWoRpKTAAQYFUIsWFeLNmA3Dn6BSCslLh7hwQSRNRK7IOSM18ipXJ31GGpxSytKkawxNkCukwEyEj/paLVDRFxeUttp2oFxQzcXGTMWhr+Stt8p27y2Jj35rgXfgfgPO7IGCI6734FUwTvjjeTsOeRl0uDT53YpfLkflmKkZSt9aBAEAOw==" v:shapes="_x0000_s1236">

1. 分析binlog
2. 分析general log

**pt-query-digest\****语法及重要选项**

**proﬁle\****分析语句**

Query Proﬁler是MySQL自带的一种**query\****诊断分析工具**，通过它可以分析出一条SQL语句的**硬件性能瓶颈**在什么地方。比如CPU，IO等，以及该SQL执行所耗费的时间等。不过该工具只有在MySQL 5.0.37 以及以上版本中才有实现。默认的情况下，MYSQL的该功能没有打开，需要自己手动启动。

**开启\****Proﬁle***\*功能**

Proﬁle 功能由MySQL会话变量 : **proﬁling**控制,默认是**OFF**关闭状态。查看是否开启了Proﬁle功能:

开启proﬁle功能

示例

mysql> select @@profiling;

+-------------+

| @@profiling |

+-------------+

| 0 |

+-------------+

1 row in set, 1 warning (0.00 sec)

mysql> set profiling=1;

Query OK, 0 rows affected, 1 warning (0.00 sec)

mysql> select @@profiling;

+-------------+

| @@profiling |

+-------------+

| 1 |

+-------------+

1 row in set, 1 warning (0.00 sec)

mysql> select count(id) from tuser; ERROR 1046 (3D000): No database selected mysql> use kkb_2;

Reading table information for completion of table and column names You can turn off this feature to get a quicker startup with -A

Database changed

mysql> select count(id) from tuser;

+-----------+

| count(id) |

+-----------+

| 10000000 |

+-----------+

1 row in set (4.62 sec)

mysql> show profiles;

+----------+------------+-----------------------------+

| Query_ID | Duration | Query |

+----------+------------+-----------------------------+

|

1

|

0.00016275

|

select @@profiling

|

|

2

|

0.00009200

|

select count(id) from

tuser

|

|

3

|

0.00014875

|

SELECT DATABASE()

|

|

4

|

0.00066875

|

show databases

|

|

5

|

0.00021050

|

show tables

|

|

6

|

4.61513875

|

select count(id) from

tuser

|

+----------+------------+-----------------------------+

6 rows in set, 1 warning (0.13 sec)

mysql> show profile for query 6;

+----------------------+----------+

| Status | Duration |

+----------------------+----------+

|

starting

|

0.000228

|

|

checking permissions

|

0.000018

|

|

Opening tables

|

0.000035

|

|

init

|

0.000204

|

|

System lock

|

0.000071

|

|

optimizing

|

0.000013

|

|

statistics

|

0.000067

|

|

preparing

|

0.000027

|

|

executing

|

0.000004

|

|

Sending data

|

4.614239

|

|

end

|

0.000045

|

|

query end

|

0.000009

|

|

closing tables

|

0.000026

|

|

freeing items

|

0.000019

|

|

logging slow query

|

0.000124

|

|

cleaning up

|

0.000011

|

+----------------------+----------+

16 rows in set, 1 warning (0.00 sec)

mysql> show profile cpu,block io,swaps for query 6;

+----------------------+----------+----------+------------+--------------+------

---------+-------+

| Status | Duration | CPU_user | CPU_system | Block_ops_in | Block_ops_out | Swaps |

+----------------------+----------+----------+------------+--------------+------

---------+-------+

|

starting

|

0.000228

|

0.000361

|

0.000000

|

0

|

0 | 0 |

|

checking permissions

|

0.000018

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

Opening tables

|

0.000035

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

init

|

0.000204

|

0.000224

|

0.000000

|

0

|

0 | 0 |

|

System lock

|

0.000071

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

optimizing

|

0.000013

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

statistics

|

0.000067

|

0.000131

|

0.000000

|

0

|

0 | 0 |

|

preparing

|

0.000027

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

executing

|

0.000004

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

Sending data

|

4.614239

|

3.648639

|

0.543410

|

55280

|

0 | 0 |

|

end

|

0.000045

|

0.000202

|

0.000000

|

0

|

0 | 0 |

|

query end

|

0.000009

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

closing tables

|

0.000026

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

freeing items

|

0.000019

|

0.000000

|

0.000000

|

0

|

0 | 0 |

|

logging slow query

|

0.000124

|

0.000155

|

0.000000

|

0

|

8 | 0 |

|

cleaning up

|

0.000011

|

0.000000

|

0.000000

|

0

|

0 | 0 |

+----------------------+----------+----------+------------+--------------+------

---------+-------+

Block_ops_in

**服务器层面优化**

**将数据保存在内存中，保证从内存读取数据**

buﬀer pool 默认128M

扩大buﬀer pool 理论上内存的3/4或4/5

怎样确定 innodb_buﬀer_pool_size 足够大。数据是从内存读取而不是硬盘？

mysql> show global status like 'innodb_buffer_pool_pages_%';

+----------------------------------+-------+

| Variable_name | Value |

+----------------------------------+-------+

|

Innodb_buffer_pool_pages_data

|

8190

|

|

Innodb_buffer_pool_pages_dirty

|

0

|

|

Innodb_buffer_pool_pages_flushed

|

12646

|

|

Innodb_buffer_pool_pages_free

|

0

|

0

表示已经被用光

|

Innodb_buffer_pool_pages_misc

|

1

|

|

Innodb_buffer_pool_pages_total

|

8191

|

+----------------------------------+-------+

修 改 my.cnf innodb_buﬀer_pool_size = 750M

**内存预热**

select count(id) from tuser; +-----------+ | count(id) | +-----------+ | 10000000 | +-----------+ 1 row in set (5.03 sec) mysql> select count(id) from tuser; +-----------+ | count(id) | +-----------+ | 10000000 | +-----------+ 1 row in set (2.85 sec) " src="data:image/png;base64,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" v:shapes="_x0000_s1245">

**降低磁盘写入次数**

1、redolog 大 落盘次数少

innodb_log_ﬁle_size 设 置 成 innodb_buﬀer_pool_size * 0.25 2、通用查询日志、慢查询日志 可以不开 bin-log 开

3、写redolog策略 innodb_ﬂush_log_at_trx_commit 0 1 2

**提高磁盘读写**

SSD

**SQL\****设计层面优化**

设计中间表**，一般针对于**统计分析功能，或者实时性不高的需求（OLTP、OLAP）

为减少关联查询，创建合理的**冗余字段**（考虑数据库的三范式和查询性能的取舍，创建冗余字段还 需要注意**数据一致性问题**）

对于字段太多的大表，考虑**拆表**（比如一个表有100多个字段） 人和身份证

对于表中经常不被使用的字段或者存储数据比较多的字段，考虑拆表（比如商品表中会存储商品介 绍，此时可以将商品介绍字段单独拆解到另一个表中，使用商品ID关联）

每张表建议都要有一个主键（**主键索引**），而且主键类型最好是**int\****类型**，建议自增主键（**不考虑 分布式系统的情况下** 雪花算法）。

# SQL语句优化

索引优化

where 字段 、组合索引 （最左前缀） 、 索引下推 （非选择行 不加锁） 、索引覆盖（不回表）

on 两边 排序 分组统计不要用 *

LIMIT优化原SQL

mysql> select * from tuser limit 9999999,1;

+----------+------------+-----------------+------+------+------+---------+

| id | loginname | name | age | sex | dep | address |

+----------+------------+-----------------+------+------+------+---------+

| 10000000 | zy10000000 | zhaoyun10000000 | 23 | 1 | 1 | beijing |

+----------+------------+-----------------+------+------+------+---------+

1 row in set (15.70 sec)

优化：

limit 是可以停止全表扫描的

select * from tuser order by id desc limit 1; +----------+------------+-----------------+------+------+------+---------+ | id | loginname | name | age | sex | dep | address | +----------+------------+-----------------+------+------+------+---------+ | 10000000 | zy10000000 | zhaoyun10000000 | 23 | 1 | 1 | beijing | +----------+------------+-----------------+------+------+------+---------+ 1 row in set (0.10 sec) " src="data:image/png;base64,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" v:shapes="_x0000_s1248">

定位

select * from tuser where id>9999999 limit 1; +----------+------------+-- ---------------+------+------+------+---------+ | id | loginname | name | age | sex | dep | address | +----------+------------+-----------------+------+------+------+---------+ | 10000000 | zy10000000 | zhaoyun10000000 | 23 | 1 | 1 | beijing | +----------+------------+-----------------+------+------+------+---------+ 1 row in set (0.00 sec) " src="data:image/png;base64,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" v:shapes="_x0000_s1249">

SELECT a.id, a.name FROM tuser a JOIN ( SELECT id FROM tuser ORDER BY id LIMIT 90000 ,100 ) b ON a.ID = b.id;

小结果集关联大结果集

其他优化

count (*) 找普通索引 ,找到最小的那棵树来遍历 包含空值

count（字段） 走缓存 不包含空值

count(1) 忽略字段 包含空值

不用 MySQL 内置的函数，因为内置函数不会建立查询缓存。

SELECT * FROM user where birthday = now();