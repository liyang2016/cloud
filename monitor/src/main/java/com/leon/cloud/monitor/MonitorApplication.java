package com.leon.cloud.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * Created by leon on 2019/3/7.
 */


@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
@EnableHystrixDashboard
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }
}
