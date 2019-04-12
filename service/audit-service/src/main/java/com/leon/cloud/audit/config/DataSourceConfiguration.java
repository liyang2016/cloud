package com.leon.cloud.audit.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = "classpath:db.properties")
@Log4j2
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.maxActive}")
    private String maxActive;
    @Value("${jdbc.maxWait}")
    private String maxWait;
    @Value("${jdbc.slave_1.url}")
    private String slave_url_1;
    @Value("${jdbc.slave_2.url}")
    private String slave_url_2;


    @Bean
    public DruidDataSource dataSource() {
        //Master
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(Integer.parseInt(maxActive));
        dataSource.setMaxWait(Integer.parseInt(maxWait));
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestOnBorrow(true);
        Map<Object, Object> targetDataSources = new HashMap<>();
        CloudRoutingDataSource cloudRoutingDataSource = new CloudRoutingDataSource();
        cloudRoutingDataSource.setDefaultTargetDataSource(dataSource);
        targetDataSources.put(DBTypeEnum.MASTER, dataSource);
        dataSource.setUrl(slave_url_1);
        targetDataSources.put(DBTypeEnum.SLAVE1, dataSource);
        dataSource.setUrl(slave_url_2);
        targetDataSources.put(DBTypeEnum.SLAVE2, dataSource);
        cloudRoutingDataSource.setTargetDataSources(targetDataSources);
        return dataSource;
    }

}
