### spring profiles
>1. Mapped "{[/{name}-{profiles}.properties],methods=[GET]}"
>2. Mapped "{[/{name}-{profiles}.yml || /{name}-{profiles}.yaml],methods=[GET]}"
>3. Mapped "{[/{name}/{profiles}/{label:.*}],methods=[GET]}"
>4. Mapped "{[/{name}/{profiles:.*[^-].*}],methods=[GET]}"
>5. Mapped "{[/{label}/{name}-{profiles}.yml || /{label}/{name}-{profiles}.yaml],methods=[GET]}"
>6. Mapped "{[/{name}-{profiles}.json],methods=[GET]}"
>7. Mapped "{[/{label}/{name}-{profiles}.json],methods=[GET]}"
>8. Mapped "{[/{label}/{name}-{profiles}.properties],methods=[GET]}"
>9. Mapped "{[/{name}/{profile}/**],methods=[GET],params=[useDefaultLabel]}"
>10. Mapped "{[/{name}/{profile}/{label}/**],methods=[GET]}"
>11. Mapped "{[/{name}/{profile}/{label}/**],methods=[GET],produces=[application/octet-stream]}"

### bean加载规则
#### Autowired
根据类型查找合适的Bean
#### Resource
根据beanName从上下文中找到合适的Bean

### 设计模式
#### 简单工厂
BeanFactory
#### 单例模式
FactoryBean
#### 单例
#### 适配器
AOP通知功能
数据库连接驱动
#### 包装器
类名中还有Wrapper
#### 观察者
ApplicationListener EventPublishingRunListener
#### 策略模式

### 定时器类型
#### fixedDelay
固定长度时间间隔执行任务，前一个任务执行完后，间隔指定时间执行下一个任务
#### cron
固定周期判断执行任务，在固定的cron表达式指定的时间点，判断当前任务是否执行完毕，没有则在下一次调度时完成下一个任务
#### fixedRate
固定频率执行任务，保证任务在原本指定完成时间的最近时间完成




###Spring MVC

1.DispatcherServlet
采用策略模式、SPI服务
默认执行 BeanNameUrlHandlerMapping RequestMappingHandlerMapping


BeanNameUrlHandlerMapping根据配置的路径与Controller信息，查找到实现Controller接口的Handler，执行handlerRequest方法
SimpleUrlHandlerMapping直接按照url映射到实现Controller接口的Handler，执行handlerRequest方法
RequestMappingHandlerMapping 读取RequestMapping注册的注解生成HandlerMethod包含请求方法、出入参信息，获取包含HandlerMethod的Handler对应的bean

BeanNameUrlHandlerMapping、SimpleUrlHandlerMapping获取的handler为配置的实现Controller接口的类
RequestMappingHandlerMapping获取的handler为HandlerMethod，HandlerMethod中包含有一个bean，对应的是包含指定了RequestMapping方法的bean

HandlerAdapter实现了典型的适配器模式根据不同的handler类型完成不同的处理
RequestMappingHandlerAdapter继承AbstractHandlerMethodAdapter 支持HandlerMethod类的handler
SimpleControllerHandlerAdapter 支持实现Controller接口的handler



###spring事务
PlatformTransactionManager平台事务管理器，提供了DataSourceTransactionManager、HibernateTransactionManager、JpaTransactionManager等具体事务实现  
实现事务的方式包含编程式事务和声明式事务，编程式事务使用TransactionTemplate，方法execute包含执行的详细信息
声明式事务通过Spring AOP实现，在方法执行的过程中加入前切点、后切点、返回前的切点来完成事务的提交和回滚
声明式事务提交规则实现
```java
/**
	 * Process an actual commit.
	 * Rollback-only flags have already been checked and applied.
	 * @param status object representing the transaction
	 * @throws TransactionException in case of commit failure
	 */
private void processCommit(DefaultTransactionStatus status) throws TransactionException {
    try {
        boolean beforeCompletionInvoked = false;

        try {
            boolean unexpectedRollback = false;
            prepareForCommit(status);
            triggerBeforeCommit(status);
            triggerBeforeCompletion(status);
            beforeCompletionInvoked = true;

            if (status.hasSavepoint()) {
                if (status.isDebug()) {
                    logger.debug("Releasing transaction savepoint");
                }
                unexpectedRollback = status.isGlobalRollbackOnly();
                status.releaseHeldSavepoint();
            }
            else if (status.isNewTransaction()) {
                if (status.isDebug()) {
                    logger.debug("Initiating transaction commit");
                }
                unexpectedRollback = status.isGlobalRollbackOnly();
                doCommit(status);
            }
            else if (isFailEarlyOnGlobalRollbackOnly()) {
                unexpectedRollback = status.isGlobalRollbackOnly();
            }

            // Throw UnexpectedRollbackException if we have a global rollback-only
            // marker but still didn't get a corresponding exception from commit.
            if (unexpectedRollback) {
                throw new UnexpectedRollbackException(
                        "Transaction silently rolled back because it has been marked as rollback-only");
            }
        }
        //不处理异常
        catch (UnexpectedRollbackException ex) {
            // can only be caused by doCommit
            triggerAfterCompletion(status, TransactionSynchronization.STATUS_ROLLED_BACK);
            throw ex;
        }
        //指定的处理异常回滚
        catch (TransactionException ex) {
            // can only be caused by doCommit
            if (isRollbackOnCommitFailure()) {
                doRollbackOnCommitException(status, ex);
            }
            else {
                triggerAfterCompletion(status, TransactionSynchronization.STATUS_UNKNOWN);
            }
            throw ex;
        }
        //只在发生错误 或 运行时异常时回滚 并且异常没有被捕获
        catch (RuntimeException | Error ex) {
            if (!beforeCompletionInvoked) {
                triggerBeforeCompletion(status);
            }
            doRollbackOnCommitException(status, ex);
            throw ex;
        }

        // Trigger afterCommit callbacks, with an exception thrown there
        // propagated to callers but the transaction still considered as committed.
        try {
            triggerAfterCommit(status);
        }
        finally {
            triggerAfterCompletion(status, TransactionSynchronization.STATUS_COMMITTED);
        }

    }
    finally {
        cleanupAfterCompletion(status);
    }
}
```
编程式事务提交规则实现
```java
@Override
@Nullable
public <T> T execute(TransactionCallback<T> action) throws TransactionException {
    Assert.state(this.transactionManager != null, "No PlatformTransactionManager set");

    if (this.transactionManager instanceof CallbackPreferringPlatformTransactionManager) {
        return ((CallbackPreferringPlatformTransactionManager) this.transactionManager).execute(this, action);
    }
    else {
        TransactionStatus status = this.transactionManager.getTransaction(this);
        T result;
        try {
            result = action.doInTransaction(status);
        }
        catch (RuntimeException | Error ex) {
            // Transactional code threw application exception -> rollback
            rollbackOnException(status, ex);
            throw ex;
        }
        catch (Throwable ex) {
            // Transactional code threw unexpected exception -> rollback
            rollbackOnException(status, ex);
            throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
        }
        this.transactionManager.commit(status);
        return result;
    }
}
```





> spring-boot四大神器starters、auto-configuration、cli、actuator

## starter



