package com.blessed.home.config.alibaba;

import com.aliyun.oss.OSSClient;
import com.blessed.home.properties.AlibabaCloudOssProperties;
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

    public AlibabaCloudOssConfig(AlibabaCloudOssProperties alibabaCloudOssProperties, AlibabaCloudOssCredentialsProvider alibabaCloudOssCredentialsProvider, AlibabaCloudOssClientConfiguration alibabaCloudOssClientConfiguration) {
        this.alibabaCloudOssProperties = alibabaCloudOssProperties;
        this.alibabaCloudOssCredentialsProvider = alibabaCloudOssCredentialsProvider;
        this.alibabaCloudOssClientConfiguration = alibabaCloudOssClientConfiguration;
    }

    /**
     * 向容器中放入一个 {@link OSSClient} Bean
     * @return 在容器启动的时候进行调用，返回一个 {@link OSSClient} 对象用于连接阿里云OSS
     */
    @Bean
    public OSSClient ossClient() {
        return new OSSClient(
                alibabaCloudOssProperties.getEndpoint(),
                alibabaCloudOssCredentialsProvider,
                alibabaCloudOssClientConfiguration
        );
    }

}
