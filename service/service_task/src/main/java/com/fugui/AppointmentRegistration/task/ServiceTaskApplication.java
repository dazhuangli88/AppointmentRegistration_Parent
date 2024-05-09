package com.fugui.AppointmentRegistration.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 富贵
 * @Date 2024/4/24 14:15
 * @Version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.fugui"})
@ComponentScan(basePackages = "com.fugui")
public class ServiceTaskApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceTaskApplication.class, args);
    }
}
