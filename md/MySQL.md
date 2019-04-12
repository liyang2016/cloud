存在排序时，最好在排序字段上创建索引，否则会产生filesort操作

redolog undolog binlog

1. redolog日志
InnoDB存储引擎层的日志，重做日志，保证数据库事务执行过程中崩溃时，可以恢复到崩溃之前。
采用Write Ahead Logging策略，在数据库更新时，先将更新操作记录到日志中，然后并发的更新到磁盘中
2. undolog日志
主要用来做事务回滚和MVCC
3. binlog日志
MySQL Server层记录的日志，记录所有更新且提交了数据或者已经在更新提交了的数据的所有语句，作为主从复制使用

4. 总结
由于事务的原子性，需要保证事务提交时，redolog与binlog都写入成功，update语句执行的过程：
update person set age = 30 where id = 1;
a. 分配事务ID，获取锁，没有获取锁则等待。
b. 执行器先通过存储引擎找到id = 1的数据也