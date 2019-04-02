package com.leon.cloud.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Log4j2
public class AuditApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(AuditApplication.class,args);
        String[] beanNames=context.getBeanDefinitionNames();
        for (String beanName:beanNames){
            log.info(beanName);
        }
    }
}
