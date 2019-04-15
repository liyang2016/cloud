```shell
#查看文件
head -10 .profile
cat .profile
tail -f -n 100 .profile
#查看进程状态
ps auxw|head -1;ps auxw|sort -rn -k3|head -10
ps auxw|head -1;ps auxw|sort -rn -k4|head -10
#查看网络状态
netstat -altpn

#统计
wc -l log.text
wc -w log.text
grep -C 5 'error' log.text
grep -c 'error' log.text
grep -o 'error' log.text|wc -l
#文件夹下文件个数
ls -l|grep '^-'|wc -l
#文件夹下文件夹个数 包括子文件夹
ls -lR|grep '^d'|wc -l
#查看文件下文件 包括子文件
ls -lR
find -type f -name '*.java'|xargs grep 'audit'
#JVM
java -Xmx512m -Xss256m -jar app.jar
#查看JVM进程状态
jps
#查看JAVA线程状态
jstack 23789
#打印JVM堆快照
jmap -dump:format=b,file=heap.hprof 23789
#打印JVM运行参数
jinfo -flag 23789
#查看类加载信息、垃圾收集
jstat -gc 23789 1000 10
jstat -class 23789
```