package com.fugui.AppointmentRegistration.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 富贵
 * @Date 2024/4/20 19:34
 * @Version 1.0
 */
public interface FileService {
    //上传文件到阿里云oss
    String upload(MultipartFile file);
}
