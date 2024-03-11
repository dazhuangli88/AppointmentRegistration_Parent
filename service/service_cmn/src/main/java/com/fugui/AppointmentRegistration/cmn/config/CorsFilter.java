package com.fugui.AppointmentRegistration.cmn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 富贵
 * @Date 2024/1/21 21:41
 * @Version 1.0
 */
@Configuration
public class CorsFilter implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("GET","POST","DELETE","OPTIONS")
                .maxAge(3600);
        // 重启下项目试试，这跨域下午才打开，昨天注释掉前端也有数据显示，看到了
    }
}
