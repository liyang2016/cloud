package com.leon.cloud.audit.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.leon.cloud.common.db.CloudRoutingDataSource;
import com.leon.cloud.common.db.DBTypeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;
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

    private DruidDataSource commonDataSource(String url){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(Integer.parseInt(maxActive));
        dataSource.setMaxWait(Integer.parseInt(maxWait));
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }


    //Mybatis spring-boot-starter的MybatisAutoConfiguration添加了ConditionalOnSingleCandidate注解
    //当加载多个DataSource类时，自动配置无法生效
    //采用现在的方式，在第一次访问时，Druid会对dataSource进行初始化，可以手动初始化
//    /**
//     * 默认数据库
//     * @return
//     */
//    @Bean(value = "dataSourceMaster")
//    public DruidDataSource dataSource_master(){
//        return commonDataSource(url);
//    }

//    @Bean(value = "dataSourceSlave_1")
//    public DruidDataSource dataSource_slave_1(){
//        return commonDataSource(slave_url_1);
//    }
//
//    @Bean(value = "dataSourceSlave_2")
//    public DruidDataSource dataSource_slave_2(){
//        return commonDataSource(slave_url_2);
//    }

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        CloudRoutingDataSource cloudRoutingDataSource = new CloudRoutingDataSource();
        DruidDataSource master=commonDataSource(url);
        DruidDataSource slave_1=commonDataSource(slave_url_1);
        DruidDataSource slave_2=commonDataSource(slave_url_2);
        cloudRoutingDataSource.setDefaultTargetDataSource(master);
        targetDataSources.put(DBTypeEnum.MASTER,master);
        targetDataSources.put(DBTypeEnum.SLAVE1,slave_1);
        targetDataSources.put(DBTypeEnum.SLAVE2,slave_2);
        cloudRoutingDataSource.setTargetDataSources(targetDataSources);

        //初始化slave datasource 定义多个DataSource导致MyBatis自动配置类失效
        //master与slave数据源不存在Spring IOC容器中
        try {
            master.init();
            slave_1.init();
            slave_2.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cloudRoutingDataSource;
    }

}
