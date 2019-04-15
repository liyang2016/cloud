package com.leon.cloud.audit.aop;

import com.leon.cloud.common.db.CloudRouterDataSourceHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    //定义切点
    @Pointcut("!@annotation(com.leon.cloud.common.annotation.Master)" +
            "&&(execution(* com.leon.cloud.audit.service..*get*(..))" +
            "||execution(* com.leon.cloud.audit.service..*select*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.leon.cloud.common.annotation.Master)" +
            "||(execution(* com.leon.cloud.audit.service..*update*(..))" +
            "||execution(* com.leon.cloud.audit.service..*insert*(..)))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        CloudRouterDataSourceHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        CloudRouterDataSourceHolder.master();
    }
}
