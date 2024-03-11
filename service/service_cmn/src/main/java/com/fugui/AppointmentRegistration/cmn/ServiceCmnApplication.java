package com.fugui.AppointmentRegistration.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 富贵
 * @Date 2024/1/20 17:42
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.fugui"})
public class ServiceCmnApplication {
        public static void main(String[] args) {
            SpringApplication.run(ServiceCmnApplication.class, args);
        }

    }

