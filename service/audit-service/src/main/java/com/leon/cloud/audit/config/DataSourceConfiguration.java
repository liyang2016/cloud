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

        //Salve
        DruidDataSource salve_1 = dataSource;
        String url_1 = "";
        String url_2 = "";
        salve_1.setUrl(url_1);
        DruidDataSource salve_2 = dataSource;

        salve_2.setUrl(url_2);
        CloudRoutingDataSource cloudRoutingDataSource = new CloudRoutingDataSource();
        cloudRoutingDataSource.setDefaultTargetDataSource(dataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, dataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, salve_1);
        targetDataSources.put(DBTypeEnum.SLAVE2, salve_2);
        cloudRoutingDataSource.setTargetDataSources(targetDataSources);
        return dataSource;
    }

}
