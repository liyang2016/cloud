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
2. 线程池中线程数量大于核心线程数，队列没满进入队列，队列满，且线程数小于最大线程数，创建线程
3. 线程池中线程数量大于核心线程数，大于最大线程数，按照reject策略执行，默认抛出异常
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



/**
* EagerThreadPoolExecutor
* 类似Tomcat线程池，dubbo中eager线程池的实现
* 当线程数超过核心线程数时，继续创建新的线程进行处理
* 当线程数超过最大线程数时，将线程压入任务队列中
* 重写workQueue实现，继承LinkedBlockingQueue
*/
public EagerThreadPoolExecutor(int corePoolSize,
                                   int maximumPoolSize,
                                   long keepAliveTime,
                                   TimeUnit unit, TaskQueue<Runnable> workQueue,
                                   ThreadFactory threadFactory,
                                   RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
```


## 锁
### AbstractQueuedSynchronizer抽象队列同步器
属性参数，通过CAS控制state状态，从head为头，tail为尾的队列中获取、添加、删除工作任务执行
```java
/**
     * Head of the wait queue, lazily initialized.  Except for
     * initialization, it is modified only via method setHead.  Note:
     * If head exists, its waitStatus is guaranteed not to be
     * CANCELLED.
     */
    private transient volatile Node head;

    /**
     * Tail of the wait queue, lazily initialized.  Modified only via
     * method enq to add new wait node.
     */
    private transient volatile Node tail;

    /**
     * The synchronization state.
     */
    private volatile int state;
```
JDK中使用AbstractQueuedSynchronizer的有CountDownLatch、ReentrantLock、ReentrantReadWriteLock、Semaphore、ThreadPoolExecutor
### CountDownLatch