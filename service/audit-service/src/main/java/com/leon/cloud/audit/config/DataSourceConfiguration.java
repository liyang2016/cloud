package com.leon.cloud.audit.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:db.properties")
@Log4j2
public class DataSourceConfiguration {

//    jdbc.driver=com.mysql.jdbc.Driver
//    jdbc.url=jdbc:mysql://localhost:3306/audit?useUnicode=true&characterEncoding=utf8
//    jdbc.username=root
//    jdbc.password=root
//    jdbc.maxActive=2335
//    jdbc.maxIdel=120
//    jdbc.maxWait=100

    @Value(value = "jdbc.driver")
    private String driver;
    @Value(value = "jdbc.username")
    private String username;
    @Value(value = "jdbc.password")
    private String password;
    @Value(value = "jdbc.url")
    private String url;
    @Value(value = "jdbc.maxActive")
    private Integer maxActive;
    @Value(value = "jdbc.maxIdel")
    private Integer maxIdel;
    @Value(value = "jdbc.maxWait")
    private Integer maxWait;


    @Bean(name = "dataSource")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxIdle(maxIdel);
        log.info(maxIdel);
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

}
