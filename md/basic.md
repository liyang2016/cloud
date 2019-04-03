## 线程池
ThreadPoolExecutor类

### 重要属性  
ctl 高三位线程池状态信息、低29位保存线程池数量
works 等待执行的任务集合
corePoolSize 核心线程数
maximumPoolSize 线程池中最多能创建的线程数
threadFactory 创建线程的工厂
queue  缓存任务的阻塞队列（有界 无界LinkedBlockingQueue）
keepAliveTime 非核心线程失效时间
线程数量变化过程：创建一个线程
1. 线程池中线程数量小于核心线程数，创建线程
2. 线程池中线程数量大于核心线程数，队列没满
### 常用线程池
```java
/**
* 固定长度线程池
* 初始化线程池个数与最大线程数相同
* 线程失效时间为0
* 线程等待队列无限长度队列
*/
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}

/**
* 单个线程池
* 初始化线程池个数与最大线程数相同 都为1
* 线程失效时间为0
* 线程等待队列无限长度队列
*/
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}

/**
* 缓存线程池
* 初始化线程池个数为0 最大线程池线程数都为Integer.MAX_VALUE
* 线程失效时间为60
* 线程等待队列为单一队列
*/
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}


/**
* 调度线程池
* 初始化线程池个数为1 最大线程池线程数都为Integer.MAX_VALUE
* 线程失效时间为0
* 线程等待队列为延迟队列
*/
public ScheduledThreadPoolExecutor(int corePoolSize,
                                   ThreadFactory threadFactory) {
    super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
          new DelayedWorkQueue(), threadFactory);
}
```