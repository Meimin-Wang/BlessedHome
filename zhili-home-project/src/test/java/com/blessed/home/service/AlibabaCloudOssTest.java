package com.blessed.home.service;

import com.aliyun.oss.OSSClient;
import com.blessed.home.config.alibaba.AlibabaCloudOssCredentials;
import com.blessed.home.properties.AlibabaCloudOssProperties;
import com.blessed.home.service.impl.AlibabaCloudOssServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;

/**
 * @ClassName AlibabaCloudOssTest
 * @Description 阿里云OSS服务测试类
 * @Author blessed
 * @Date 2021/11/6 : 20:02
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@DisplayName("阿里云OSS存储测试类")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlibabaCloudOssTest {

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AlibabaCloudOssProperties alibabaCloudOssProperties;

    @Autowired
    private AlibabaCloudOssCredentials alibabaCloudOssCredentials;

    @Autowired
    private AlibabaCloudOssServiceImpl alibabaCloudOssService;

    @Test
    @Order(1)
    @DisplayName("1. 测试OSSClient对象获取")
    public void testOssClient() {
        Assertions.assertNotNull(ossClient, "阿里云OSSClient对象为null");
        log.info("ossClient: {}", ossClient.toString());
        log.info("{}", ossClient.getBucketAcl(alibabaCloudOssProperties.getBucketName()));
    }

    @Test
    @DisplayName("测试阿里云OSS的credentials是否成功注入")
    public void testCredentials() {
        Assertions.assertNotNull(alibabaCloudOssCredentials, "alibabaCloudOssCredentials is null");
        log.info("Oss access key id: {}", alibabaCloudOssCredentials.getAccessKeyId());
        log.info("Oss secret key: {}", alibabaCloudOssCredentials.getSecretAccessKey());
    }


}
