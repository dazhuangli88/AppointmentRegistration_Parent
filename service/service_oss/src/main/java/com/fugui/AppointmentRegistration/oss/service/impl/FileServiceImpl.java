package com.fugui.AppointmentRegistration.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.fugui.AppointmentRegistration.oss.service.FileService;
import com.fugui.AppointmentRegistration.oss.utils.ConstantOssPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        String endpoint = ConstantOssPropertiesUtils.EDNPOINT;
        String accessKeyId = ConstantOssPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantOssPropertiesUtils.SECRECT;
        String bucketName = ConstantOssPropertiesUtils.BUCKET;
        OSS ossClient =null;
        try {
            CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
            ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
            InputStream inputStream = file.getInputStream();// 上传文件流。
            String fileName = file.getOriginalFilename();
            //生成随机唯一值，使用uuid，添加到文件名称里面，防止相同文件名导致文件覆盖，因为用户上传的文件名可能会一致
            String uuid = UUID.randomUUID().toString().replaceAll("-",""); //把生成的uuid里面的-替换掉
            fileName = uuid+fileName;
            //按照当前日期，创建文件夹2021/02/02/，上传到创建的文件夹里面, 结果形式为：2021/02/02/uuid01.jpg。
            //因为前面引入了依赖joda-time，所以转日期可以直接.toString("yyyy/MM/dd")
            String timeUrl = new DateTime().toString("yyyy/MM/dd");
            fileName = timeUrl+"/"+fileName;
            //调用方法实现上传
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //上传之后文件路径  格式要和阿里云上面的一致
            // https://yygh-atguigu.oss-cn-beijing.aliyuncs.com/uuid01.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            //返回
            return url;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        } finally {
            // 关闭OSSClient。
            if(null!=ossClient) {
                ossClient.shutdown();
            }
        }
    }
}