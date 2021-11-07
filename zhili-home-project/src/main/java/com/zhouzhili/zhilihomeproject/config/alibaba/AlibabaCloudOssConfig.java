package com.zhouzhili.zhilihomeproject.config.alibaba;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.zhouzhili.zhilihomeproject.properties.AlibabaCloudOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AlibabaCloudOssConfig
 * @Description 阿里云OSS的配置类
 * @Author blessed
 * @Date 2021/11/6 : 18:13
 * @Email blessedwmm@gmail.com
 */
@Configuration
public class AlibabaCloudOssConfig {

    private final AlibabaCloudOssProperties alibabaCloudOssProperties;

    private final AlibabaCloudOssCredentialsProvider alibabaCloudOssCredentialsProvider;

    private final AlibabaCloudOssClientConfiguration alibabaCloudOssClientConfiguration;

    public AlibabaCloudOssConfig(
            AlibabaCloudOssProperties alibabaCloudOssProperties,
            AlibabaCloudOssCredentialsProvider alibabaCloudOssCredentialsProvider, AlibabaCloudOssClientConfiguration alibabaCloudOssClientConfiguration) {
        this.alibabaCloudOssProperties = alibabaCloudOssProperties;
        this.alibabaCloudOssCredentialsProvider = alibabaCloudOssCredentialsProvider;
        this.alibabaCloudOssClientConfiguration = alibabaCloudOssClientConfiguration;
    }

    @Bean
    public OSSClient ossClient() {
        OSSClient ossClient = new OSSClient(
                alibabaCloudOssProperties.getEndpoint(),
                alibabaCloudOssCredentialsProvider,
                alibabaCloudOssClientConfiguration
        );
        return ossClient;
    }

}
