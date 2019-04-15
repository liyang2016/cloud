package com.leon.cloud.audit.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAop {

    //定义切点
    @Pointcut("execution(* com.leon.cloud.audit.controller..*(..))")
    public void logPointcut() {
    }

    @AfterReturning("logPointcut()")
    public void after(){
        log.info("request...end");
    }

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object[] args=proceedingJoinPoint.getArgs();
        StringBuilder stringBuilder=new StringBuilder();
        for(Object o:args){
            stringBuilder.append(o.toString()).append(" ");
        }
        log.info("请求参数为{}",stringBuilder.toString());
        Object result=null;
        try {
            result=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        assert result != null;
        log.info("返回结果{}",result.toString());
        return result;
    }
}
