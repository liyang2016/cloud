package com.leon.cloud.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AuditApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuditApplication.class,args);
    }
}
