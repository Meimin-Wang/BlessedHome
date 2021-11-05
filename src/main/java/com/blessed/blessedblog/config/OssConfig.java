package com.blessed.blessedblog.config;

import com.aliyun.oss.OSSClient;
import com.blessed.blessedblog.property.OssProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName OssConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/27 : 10:36 下午
 * @Email blessedwmm@gmail.com
 */
@Configuration
public class OssConfig {

    final OssProperties ossProperties;

    public OssConfig(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Bean OSSClient ossClient() {
        return new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }
}
