package com.leon.cloud.serial.config;

import com.leon.cloud.serial.service.factory.IdServiceBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by leon on 2019/3/14.
 */

@Configuration
public class BeanConfig {

    @Bean(initMethod = "init")
    public IdServiceBeanFactory createIdServiceBeanFactory(){
        return new IdServiceBeanFactory();
    }
}
