package com.leon.cloud.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAspectJAutoProxy
@Log4j2
public class AuditApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AuditApplication.class,args);
        ConfigurableApplicationContext context= SpringApplication.run(AuditApplication.class,args);
        String[] beanNames=context.getBeanDefinitionNames();
        for (String beanName:beanNames){
            log.info(beanName);
            Object o =context.getBean(beanName);
            log.info(o);
        }
    }
}
