package com.blessed.home.properties;

import com.blessed.home.config.alibaba.AlibabaCloudOssConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AlibabaCloudOssProperties
 * @Description 阿里云中OSS对象存储的属性配置类 {@link AlibabaCloudOssConfig}
 * @Author blessed
 * @Date 2021/11/6 : 19:30
 * @Email blessedwmm@gmail.com
 */
@Component
@ConfigurationProperties(prefix = "alibaba.cloud.oss")
@Data
public class AlibabaCloudOssProperties {

    /**
     * 阿里云OSS的access key
     */
    private String accessKey;

    /**
     * 阿里云OSS的secret key
     */
    private String secretKey;

    /**
     * 阿里云OSS的端点路径
     */
    private String endpoint;

    /**
     * 创建的存储空间
     */
    private String bucketName;

}
