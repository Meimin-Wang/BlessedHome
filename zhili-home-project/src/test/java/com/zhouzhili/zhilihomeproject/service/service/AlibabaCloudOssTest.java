package com.zhouzhili.zhilihomeproject.service.service;

import com.aliyun.oss.OSSClient;
import com.zhouzhili.zhilihomeproject.config.alibaba.AlibabaCloudOssCredentials;
import com.zhouzhili.zhilihomeproject.properties.AlibabaCloudOssProperties;
import com.zhouzhili.zhilihomeproject.service.impl.AlibabaCloudOssServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;

/**
 * @ClassName AlibabaCloudOssTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/6 : 20:02
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@DisplayName("阿里云OSS存储测试类")
@Slf4j
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
    @DisplayName("测试OSSClient对象是否能够注入")
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

    @Test
    @DisplayName("测试OSS文件上传")
    public void testUploadFile() {
        URL uploadedFileUrl = alibabaCloudOssService.uploadFile(new File("/Users/blessed/Downloads/lenna.png"), "aaa");
        log.info("{}", uploadedFileUrl);
    }

    @Test
    @DisplayName("测试OSS文件删除")
    public void testDeleteFile() {
        String key = "blog/98c-409c-ad23-4d4aee5aff0alenna.png";
        alibabaCloudOssService.deleteFile(key);
    }
}
