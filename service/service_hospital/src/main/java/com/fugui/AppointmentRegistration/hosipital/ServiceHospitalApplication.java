package com.fugui.AppointmentRegistration.hosipital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 富贵
 * @Date 2024/1/9 19:54
 * @Version 1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.fugui")
public class ServiceHospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospitalApplication.class, args);
    }


}
